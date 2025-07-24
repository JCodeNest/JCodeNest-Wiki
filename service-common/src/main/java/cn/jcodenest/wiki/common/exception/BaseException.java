package cn.jcodenest.wiki.common.exception;

import lombok.Getter;

import java.io.Serial;

/**
 * 基础异常类
 *
 * @author JCodeNest
 * @version 1.0.0
 * @since 2025/7/24
 * <p>
 * Copyright (c) 2025 JCodeNest-Wiki
 * All rights reserved.
 */
@Getter
public class BaseException extends RuntimeException {

    @Serial
    private static final long serialVersionUID = 1L;

    /**
     * 错误码
     */
    private final Integer code;

    /**
     * 错误消息
     */
    private final String message;

    /**
     * 构造函数
     *
     * @param code    错误码
     * @param message 错误消息
     */
    public BaseException(Integer code, String message) {
        super(message);
        this.code = code;
        this.message = message;
    }

    /**
     * 构造函数
     *
     * @param code    错误码
     * @param message 错误消息
     * @param cause   原因异常
     */
    public BaseException(Integer code, String message, Throwable cause) {
        super(message, cause);
        this.code = code;
        this.message = message;
    }

    /**
     * 构造函数
     *
     * @param errorCode 错误码枚举
     */
    public BaseException(ErrorCode errorCode) {
        super(errorCode.getMessage());
        this.code = errorCode.getCode();
        this.message = errorCode.getMessage();
    }

    /**
     * 构造函数
     *
     * @param errorCode 错误码枚举
     * @param cause     原因异常
     */
    public BaseException(ErrorCode errorCode, Throwable cause) {
        super(errorCode.getMessage(), cause);
        this.code = errorCode.getCode();
        this.message = errorCode.getMessage();
    }

    /**
     * 构造函数
     *
     * @param errorCode 错误码枚举
     * @param message   自定义错误消息
     */
    public BaseException(ErrorCode errorCode, String message) {
        super(message);
        this.code = errorCode.getCode();
        this.message = message;
    }

    /**
     * 构造函数
     *
     * @param errorCode 错误码枚举
     * @param message   自定义错误消息
     * @param cause     原因异常
     */
    public BaseException(ErrorCode errorCode, String message, Throwable cause) {
        super(message, cause);
        this.code = errorCode.getCode();
        this.message = message;
    }

    /**
     * 重写getMessage方法
     *
     * @return 错误消息
     */
    @Override
    public String getMessage() {
        return this.message;
    }

    /**
     * 获取详细错误信息
     *
     * @return 详细错误信息
     */
    public String getDetailMessage() {
        return String.format("错误码: %d, 错误消息: %s", this.code, this.message);
    }

    /**
     * 转换为字符串
     *
     * @return 字符串表示
     */
    @Override
    public String toString() {
        return String.format("%s: [%d] %s", 
            this.getClass().getSimpleName(), this.code, this.message);
    }
}
