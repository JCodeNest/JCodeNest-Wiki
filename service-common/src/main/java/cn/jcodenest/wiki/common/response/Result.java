package cn.jcodenest.wiki.common.response;

import cn.jcodenest.wiki.common.exception.ErrorCode;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;

import java.io.Serial;
import java.io.Serializable;
import java.time.LocalDateTime;

/**
 * 统一响应结果封装类
 *
 * @author JCodeNest
 * @version 1.0.0
 * @since 2025/7/24
 * <p>
 * Copyright (c) 2025 JCodeNest-Wiki
 * All rights reserved.
 */
@Data
@JsonInclude(JsonInclude.Include.NON_NULL)
public class Result<T> implements Serializable {

    @Serial
    private static final long serialVersionUID = 1L;

    /**
     * 响应状态码
     */
    private Integer code;

    /**
     * 响应消息
     */
    private String message;

    /**
     * 响应数据
     */
    private T data;

    /**
     * 响应时间戳
     */
    private LocalDateTime timestamp;

    /**
     * 请求追踪ID
     */
    private String traceId;

    /**
     * 私有构造函数
     */
    private Result() {
        this.timestamp = LocalDateTime.now();
    }

    /**
     * 私有构造函数
     *
     * @param code    状态码
     * @param message 消息
     * @param data    数据
     */
    private Result(Integer code, String message, T data) {
        this();
        this.code = code;
        this.message = message;
        this.data = data;
    }

    /**
     * 成功响应（无数据）
     *
     * @param <T> 数据类型
     * @return Result对象
     */
    public static <T> Result<T> success() {
        return new Result<>(ErrorCode.SUCCESS.getCode(), ErrorCode.SUCCESS.getMessage(), null);
    }

    /**
     * 成功响应（带数据）
     *
     * @param data 响应数据
     * @param <T>  数据类型
     * @return Result对象
     */
    public static <T> Result<T> success(T data) {
        return new Result<>(ErrorCode.SUCCESS.getCode(), ErrorCode.SUCCESS.getMessage(), data);
    }

    /**
     * 成功响应（自定义消息）
     *
     * @param message 响应消息
     * @param <T>     数据类型
     * @return Result对象
     */
    public static <T> Result<T> success(String message) {
        return new Result<>(ErrorCode.SUCCESS.getCode(), message, null);
    }

    /**
     * 成功响应（自定义消息和数据）
     *
     * @param message 响应消息
     * @param data    响应数据
     * @param <T>     数据类型
     * @return Result对象
     */
    public static <T> Result<T> success(String message, T data) {
        return new Result<>(ErrorCode.SUCCESS.getCode(), message, data);
    }

    /**
     * 失败响应（使用ErrorCode）
     *
     * @param errorCode 错误码枚举
     * @param <T>       数据类型
     * @return Result对象
     */
    public static <T> Result<T> error(ErrorCode errorCode) {
        return new Result<>(errorCode.getCode(), errorCode.getMessage(), null);
    }

    /**
     * 失败响应（使用ErrorCode和数据）
     *
     * @param errorCode 错误码枚举
     * @param data      响应数据
     * @param <T>       数据类型
     * @return Result对象
     */
    public static <T> Result<T> error(ErrorCode errorCode, T data) {
        return new Result<>(errorCode.getCode(), errorCode.getMessage(), data);
    }

    /**
     * 失败响应（自定义状态码和消息）
     *
     * @param code    状态码
     * @param message 响应消息
     * @param <T>     数据类型
     * @return Result对象
     */
    public static <T> Result<T> error(Integer code, String message) {
        return new Result<>(code, message, null);
    }

    /**
     * 失败响应（自定义状态码、消息和数据）
     *
     * @param code    状态码
     * @param message 响应消息
     * @param data    响应数据
     * @param <T>     数据类型
     * @return Result对象
     */
    public static <T> Result<T> error(Integer code, String message, T data) {
        return new Result<>(code, message, data);
    }

    /**
     * 判断是否成功
     *
     * @return true-成功，false-失败
     */
    public boolean isSuccess() {
        return ErrorCode.SUCCESS.getCode().equals(this.code);
    }

    /**
     * 判断是否失败
     *
     * @return true-失败，false-成功
     */
    public boolean isError() {
        return !isSuccess();
    }

    /**
     * 设置追踪ID
     *
     * @param traceId 追踪ID
     * @return Result对象
     */
    public Result<T> traceId(String traceId) {
        this.traceId = traceId;
        return this;
    }
}
