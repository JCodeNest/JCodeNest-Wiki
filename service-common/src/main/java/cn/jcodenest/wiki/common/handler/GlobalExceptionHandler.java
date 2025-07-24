package cn.jcodenest.wiki.common.handler;

import cn.jcodenest.wiki.common.exception.BusinessException;
import cn.jcodenest.wiki.common.exception.ErrorCode;
import cn.jcodenest.wiki.common.exception.SystemException;
import cn.jcodenest.wiki.common.exception.ValidationException;
import cn.jcodenest.wiki.common.response.Result;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.validation.ConstraintViolation;
import jakarta.validation.ConstraintViolationException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.validation.BindException;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.HttpMediaTypeNotSupportedException;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.MissingServletRequestParameterException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException;
import org.springframework.web.multipart.MaxUploadSizeExceededException;
import org.springframework.web.servlet.NoHandlerFoundException;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * 全局异常处理器
 *
 * @author JCodeNest
 * @version 1.0.0
 * @since 2025/7/24
 * <p>
 * Copyright (c) 2025 JCodeNest-Wiki
 * All rights reserved.
 */
@Slf4j
@RestControllerAdvice
public class GlobalExceptionHandler {

    /**
     * 处理业务异常
     *
     * @param e       异常
     * @param request 请求
     * @return 响应结果
     */
    @ExceptionHandler(BusinessException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public Result<Void> handleBusinessException(BusinessException e, HttpServletRequest request) {
        log.warn("业务异常: uri={}, code={}, message={}", request.getRequestURI(), e.getCode(), e.getMessage());
        return Result.error(e.getCode(), e.getMessage());
    }

    /**
     * 处理系统异常
     *
     * @param e       异常
     * @param request 请求
     * @return 响应结果
     */
    @ExceptionHandler(SystemException.class)
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    public Result<Void> handleSystemException(SystemException e, HttpServletRequest request) {
        log.error("系统异常: uri={}, code={}, message={}", request.getRequestURI(), e.getCode(), e.getMessage(), e);
        return Result.error(e.getCode(), e.getMessage());
    }

    /**
     * 处理参数校验异常
     *
     * @param e       异常
     * @param request 请求
     * @return 响应结果
     */
    @ExceptionHandler(ValidationException.class)
    @ResponseStatus(HttpStatus.UNPROCESSABLE_ENTITY)
    public Result<Map<String, List<String>>> handleValidationException(ValidationException e, HttpServletRequest request) {
        log.warn("参数校验异常: uri={}, code={}, message={}", request.getRequestURI(), e.getCode(), e.getMessage());
        return Result.error(e.getCode(), e.getMessage(), e.getErrors());
    }

    /**
     * 处理方法参数校验异常
     *
     * @param e       异常
     * @param request 请求
     * @return 响应结果
     */
    @ExceptionHandler(MethodArgumentNotValidException.class)
    @ResponseStatus(HttpStatus.UNPROCESSABLE_ENTITY)
    public Result<Map<String, List<String>>> handleMethodArgumentNotValidException(MethodArgumentNotValidException e, HttpServletRequest request) {
        log.warn("方法参数校验异常: uri={}, message={}", request.getRequestURI(), e.getMessage());
        return Result.error(ErrorCode.PARAMETER_INVALID.getCode(), "参数校验失败", extractErrors(e.getBindingResult()));
    }

    /**
     * 处理绑定异常
     *
     * @param e       异常
     * @param request 请求
     * @return 响应结果
     */
    @ExceptionHandler(BindException.class)
    @ResponseStatus(HttpStatus.UNPROCESSABLE_ENTITY)
    public Result<Map<String, List<String>>> handleBindException(BindException e, HttpServletRequest request) {
        log.warn("绑定异常: uri={}, message={}", request.getRequestURI(), e.getMessage());
        return Result.error(ErrorCode.PARAMETER_INVALID.getCode(), "参数绑定失败", extractErrors(e.getBindingResult()));
    }

    /**
     * 处理约束违反异常
     *
     * @param e       异常
     * @param request 请求
     * @return 响应结果
     */
    @ExceptionHandler(ConstraintViolationException.class)
    @ResponseStatus(HttpStatus.UNPROCESSABLE_ENTITY)
    public Result<Map<String, List<String>>> handleConstraintViolationException(ConstraintViolationException e, HttpServletRequest request) {
        log.warn("约束违反异常: uri={}, message={}", request.getRequestURI(), e.getMessage());
        
        Map<String, List<String>> errors = new HashMap<>();
        Set<ConstraintViolation<?>> violations = e.getConstraintViolations();
        for (ConstraintViolation<?> violation : violations) {
            String fieldName = violation.getPropertyPath().toString();
            String errorMessage = violation.getMessage();
            errors.computeIfAbsent(fieldName, k -> new ArrayList<>()).add(errorMessage);
        }
        
        return Result.error(ErrorCode.PARAMETER_INVALID.getCode(), "参数约束违反", errors);
    }

    /**
     * 处理缺少请求参数异常
     *
     * @param e       异常
     * @param request 请求
     * @return 响应结果
     */
    @ExceptionHandler(MissingServletRequestParameterException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public Result<Void> handleMissingServletRequestParameterException(MissingServletRequestParameterException e, HttpServletRequest request) {
        log.warn("缺少请求参数异常: uri={}, parameter={}", request.getRequestURI(), e.getParameterName());
        String message = String.format("缺少必需的请求参数: %s", e.getParameterName());
        return Result.error(ErrorCode.BAD_REQUEST.getCode(), message);
    }

    /**
     * 处理方法参数类型不匹配异常
     *
     * @param e       异常
     * @param request 请求
     * @return 响应结果
     */
    @ExceptionHandler(MethodArgumentTypeMismatchException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public Result<Void> handleMethodArgumentTypeMismatchException(MethodArgumentTypeMismatchException e, HttpServletRequest request) {
        log.warn("方法参数类型不匹配异常: uri={}, parameter={}, value={}", request.getRequestURI(), e.getName(), e.getValue());
        String message = String.format("参数 %s 的值 %s 类型不正确", e.getName(), e.getValue());
        return Result.error(ErrorCode.BAD_REQUEST.getCode(), message);
    }

    /**
     * 处理HTTP消息不可读异常
     *
     * @param e       异常
     * @param request 请求
     * @return 响应结果
     */
    @ExceptionHandler(HttpMessageNotReadableException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public Result<Void> handleHttpMessageNotReadableException(HttpMessageNotReadableException e, HttpServletRequest request) {
        log.warn("HTTP消息不可读异常: uri={}, message={}", request.getRequestURI(), e.getMessage());
        return Result.error(ErrorCode.BAD_REQUEST.getCode(), "请求体格式错误");
    }

    /**
     * 处理HTTP请求方法不支持异常
     *
     * @param e       异常
     * @param request 请求
     * @return 响应结果
     */
    @ExceptionHandler(HttpRequestMethodNotSupportedException.class)
    @ResponseStatus(HttpStatus.METHOD_NOT_ALLOWED)
    public Result<Void> handleHttpRequestMethodNotSupportedException(HttpRequestMethodNotSupportedException e, HttpServletRequest request) {
        log.warn("HTTP请求方法不支持异常: uri={}, method={}", request.getRequestURI(), e.getMethod());
        String message = String.format("不支持的请求方法: %s", e.getMethod());
        return Result.error(ErrorCode.METHOD_NOT_ALLOWED.getCode(), message);
    }

    /**
     * 处理HTTP媒体类型不支持异常
     *
     * @param e       异常
     * @param request 请求
     * @return 响应结果
     */
    @ExceptionHandler(HttpMediaTypeNotSupportedException.class)
    @ResponseStatus(HttpStatus.UNSUPPORTED_MEDIA_TYPE)
    public Result<Void> handleHttpMediaTypeNotSupportedException(HttpMediaTypeNotSupportedException e, HttpServletRequest request) {
        log.warn("HTTP媒体类型不支持异常: uri={}, contentType={}", request.getRequestURI(), e.getContentType());
        return Result.error(415, "不支持的媒体类型");
    }

    /**
     * 处理文件上传大小超限异常
     *
     * @param e       异常
     * @param request 请求
     * @return 响应结果
     */
    @ExceptionHandler(MaxUploadSizeExceededException.class)
    @ResponseStatus(HttpStatus.PAYLOAD_TOO_LARGE)
    public Result<Void> handleMaxUploadSizeExceededException(MaxUploadSizeExceededException e, HttpServletRequest request) {
        log.warn("文件上传大小超限异常: uri={}, maxUploadSize={}", request.getRequestURI(), e.getMaxUploadSize());
        return Result.error(ErrorCode.FILE_SIZE_EXCEEDED);
    }

    /**
     * 处理404异常
     *
     * @param e       异常
     * @param request 请求
     * @return 响应结果
     */
    @ExceptionHandler(NoHandlerFoundException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public Result<Void> handleNoHandlerFoundException(NoHandlerFoundException e, HttpServletRequest request) {
        log.warn("404异常: uri={}, method={}", request.getRequestURI(), e.getHttpMethod());
        return Result.error(ErrorCode.NOT_FOUND);
    }

    /**
     * 处理其他未知异常
     *
     * @param e       异常
     * @param request 请求
     * @return 响应结果
     */
    @ExceptionHandler(Exception.class)
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    public Result<Void> handleException(Exception e, HttpServletRequest request) {
        log.error("未知异常: uri={}, message={}", request.getRequestURI(), e.getMessage(), e);
        return Result.error(ErrorCode.SYSTEM_ERROR);
    }

    /**
     * 提取绑定结果中的错误信息
     *
     * @param bindingResult 绑定结果
     * @return 字段名到错误消息列表的映射
     */
    private Map<String, List<String>> extractErrors(BindingResult bindingResult) {
        Map<String, List<String>> errors = new HashMap<>();
        bindingResult.getAllErrors().forEach(error -> {
            String fieldName = ((FieldError) error).getField();
            String errorMessage = error.getDefaultMessage();
            errors.computeIfAbsent(fieldName, k -> new ArrayList<>()).add(errorMessage);
        });
        return errors;
    }
}
