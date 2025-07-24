package cn.jcodenest.wiki.common.exception;

import java.io.Serial;

/**
 * 业务异常类
 *
 * @author JCodeNest
 * @version 1.0.0
 * @since 2025/7/24
 * <p>
 * Copyright (c) 2025 JCodeNest-Wiki
 * All rights reserved.
 */
public class BusinessException extends BaseException {

    @Serial
    private static final long serialVersionUID = 1L;

    /**
     * 构造函数
     *
     * @param message 错误消息
     */
    public BusinessException(String message) {
        super(ErrorCode.BUSINESS_RULE_VIOLATION.getCode(), message);
    }

    /**
     * 构造函数
     *
     * @param message 错误消息
     * @param cause   原因异常
     */
    public BusinessException(String message, Throwable cause) {
        super(ErrorCode.BUSINESS_RULE_VIOLATION.getCode(), message, cause);
    }

    /**
     * 构造函数
     *
     * @param code    错误码
     * @param message 错误消息
     */
    public BusinessException(Integer code, String message) {
        super(code, message);
    }

    /**
     * 构造函数
     *
     * @param code    错误码
     * @param message 错误消息
     * @param cause   原因异常
     */
    public BusinessException(Integer code, String message, Throwable cause) {
        super(code, message, cause);
    }

    /**
     * 构造函数
     *
     * @param errorCode 错误码枚举
     */
    public BusinessException(ErrorCode errorCode) {
        super(errorCode);
    }

    /**
     * 构造函数
     *
     * @param errorCode 错误码枚举
     * @param cause     原因异常
     */
    public BusinessException(ErrorCode errorCode, Throwable cause) {
        super(errorCode, cause);
    }

    /**
     * 构造函数
     *
     * @param errorCode 错误码枚举
     * @param message   自定义错误消息
     */
    public BusinessException(ErrorCode errorCode, String message) {
        super(errorCode, message);
    }

    /**
     * 构造函数
     *
     * @param errorCode 错误码枚举
     * @param message   自定义错误消息
     * @param cause     原因异常
     */
    public BusinessException(ErrorCode errorCode, String message, Throwable cause) {
        super(errorCode, message, cause);
    }

    /**
     * 创建业务异常（使用默认业务错误码）
     *
     * @param message 错误消息
     * @return BusinessException实例
     */
    public static BusinessException of(String message) {
        return new BusinessException(message);
    }

    /**
     * 创建业务异常
     *
     * @param errorCode 错误码枚举
     * @return BusinessException实例
     */
    public static BusinessException of(ErrorCode errorCode) {
        return new BusinessException(errorCode);
    }

    /**
     * 创建业务异常
     *
     * @param errorCode 错误码枚举
     * @param message   自定义错误消息
     * @return BusinessException实例
     */
    public static BusinessException of(ErrorCode errorCode, String message) {
        return new BusinessException(errorCode, message);
    }

    /**
     * 创建业务异常
     *
     * @param code    错误码
     * @param message 错误消息
     * @return BusinessException实例
     */
    public static BusinessException of(Integer code, String message) {
        return new BusinessException(code, message);
    }
}
