package cn.jcodenest.wiki.common.exception;

import lombok.Getter;

import java.io.Serial;
import java.util.List;
import java.util.Map;

/**
 * 参数校验异常类
 *
 * @author JCodeNest
 * @version 1.0.0
 * @since 2025/7/24
 * <p>
 * Copyright (c) 2025 JCodeNest-Wiki
 * All rights reserved.
 */
@Getter
public class ValidationException extends BaseException {

    @Serial
    private static final long serialVersionUID = 1L;

    /**
     * 校验错误详情
     */
    private final Map<String, List<String>> errors;

    /**
     * 构造函数
     *
     * @param message 错误消息
     */
    public ValidationException(String message) {
        super(ErrorCode.PARAMETER_INVALID.getCode(), message);
        this.errors = null;
    }

    /**
     * 构造函数
     *
     * @param message 错误消息
     * @param cause   原因异常
     */
    public ValidationException(String message, Throwable cause) {
        super(ErrorCode.PARAMETER_INVALID.getCode(), message, cause);
        this.errors = null;
    }

    /**
     * 构造函数
     *
     * @param message 错误消息
     * @param errors  校验错误详情
     */
    public ValidationException(String message, Map<String, List<String>> errors) {
        super(ErrorCode.PARAMETER_INVALID.getCode(), message);
        this.errors = errors;
    }

    /**
     * 构造函数
     *
     * @param errorCode 结果码枚举
     */
    public ValidationException(ErrorCode errorCode) {
        super(errorCode);
        this.errors = null;
    }

    /**
     * 构造函数
     *
     * @param errorCode  结果码枚举
     * @param message    自定义错误消息
     */
    public ValidationException(ErrorCode errorCode, String message) {
        super(errorCode, message);
        this.errors = null;
    }

    /**
     * 构造函数
     *
     * @param errorCode  结果码枚举
     * @param message    自定义错误消息
     * @param errors     校验错误详情
     */
    public ValidationException(ErrorCode errorCode, String message, Map<String, List<String>> errors) {
        super(errorCode, message);
        this.errors = errors;
    }

    /**
     * 是否有校验错误详情
     *
     * @return true-有详情，false-无详情
     */
    public boolean hasErrors() {
        return errors != null && !errors.isEmpty();
    }

    /**
     * 创建参数校验异常
     *
     * @param message 错误消息
     * @return ValidationException实例
     */
    public static ValidationException of(String message) {
        return new ValidationException(message);
    }

    /**
     * 创建参数校验异常
     *
     * @param message 错误消息
     * @param errors  校验错误详情
     * @return ValidationException实例
     */
    public static ValidationException of(String message, Map<String, List<String>> errors) {
        return new ValidationException(message, errors);
    }

    /**
     * 创建参数校验异常
     *
     * @param errorCode 结果码枚举
     * @return ValidationException实例
     */
    public static ValidationException of(ErrorCode errorCode) {
        return new ValidationException(errorCode);
    }

    /**
     * 创建参数校验异常
     *
     * @param errorCode  结果码枚举
     * @param message    自定义错误消息
     * @return ValidationException实例
     */
    public static ValidationException of(ErrorCode errorCode, String message) {
        return new ValidationException(errorCode, message);
    }

    /**
     * 创建参数为空异常
     *
     * @param paramName 参数名称
     * @return ValidationException实例
     */
    public static ValidationException parameterNull(String paramName) {
        return new ValidationException(String.format("参数 [%s] 不能为空", paramName));
    }

    /**
     * 创建参数格式错误异常
     *
     * @param paramName 参数名称
     * @return ValidationException实例
     */
    public static ValidationException parameterFormat(String paramName) {
        return new ValidationException(String.format("参数 [%s] 格式错误", paramName));
    }

    /**
     * 创建参数长度错误异常
     *
     * @param paramName 参数名称
     * @param minLength 最小长度
     * @param maxLength 最大长度
     * @return ValidationException实例
     */
    public static ValidationException parameterLength(String paramName, int minLength, int maxLength) {
        return new ValidationException(String.format("参数 [%s] 长度必须在 %d 到 %d 之间", paramName, minLength, maxLength));
    }

    /**
     * 创建参数范围错误异常
     *
     * @param paramName 参数名称
     * @param minValue  最小值
     * @param maxValue  最大值
     * @return ValidationException实例
     */
    public static ValidationException parameterRange(String paramName, Object minValue, Object maxValue) {
        return new ValidationException(String.format("参数 [%s] 值必须在 %s 到 %s 之间", paramName, minValue, maxValue));
    }
}
