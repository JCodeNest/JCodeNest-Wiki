package cn.jcodenest.wiki.common.exception;

import java.io.Serial;

/**
 * 系统异常类
 *
 * @author JCodeNest
 * @version 1.0.0
 * @since 2025/7/24
 * <p>
 * Copyright (c) 2025 JCodeNest-Wiki
 * All rights reserved.
 */
public class SystemException extends BaseException {

    @Serial
    private static final long serialVersionUID = 1L;

    /**
     * 构造函数
     *
     * @param message 错误消息
     */
    public SystemException(String message) {
        super(ErrorCode.SYSTEM_ERROR.getCode(), message);
    }

    /**
     * 构造函数
     *
     * @param message 错误消息
     * @param cause   原因异常
     */
    public SystemException(String message, Throwable cause) {
        super(ErrorCode.SYSTEM_ERROR.getCode(), message, cause);
    }

    /**
     * 构造函数
     *
     * @param code    错误码
     * @param message 错误消息
     */
    public SystemException(Integer code, String message) {
        super(code, message);
    }

    /**
     * 构造函数
     *
     * @param code    错误码
     * @param message 错误消息
     * @param cause   原因异常
     */
    public SystemException(Integer code, String message, Throwable cause) {
        super(code, message, cause);
    }

    /**
     * 构造函数
     *
     * @param errorCode 结果码枚举
     */
    public SystemException(ErrorCode errorCode) {
        super(errorCode);
    }

    /**
     * 构造函数
     *
     * @param errorCode  结果码枚举
     * @param cause      原因异常
     */
    public SystemException(ErrorCode errorCode, Throwable cause) {
        super(errorCode, cause);
    }

    /**
     * 构造函数
     *
     * @param errorCode  结果码枚举
     * @param message    自定义错误消息
     */
    public SystemException(ErrorCode errorCode, String message) {
        super(errorCode, message);
    }

    /**
     * 构造函数
     *
     * @param errorCode  结果码枚举
     * @param message    自定义错误消息
     * @param cause      原因异常
     */
    public SystemException(ErrorCode errorCode, String message, Throwable cause) {
        super(errorCode, message, cause);
    }

    /**
     * 创建系统异常（使用默认系统错误码）
     *
     * @param message 错误消息
     * @return SystemException实例
     */
    public static SystemException of(String message) {
        return new SystemException(message);
    }

    /**
     * 创建系统异常
     *
     * @param errorCode 结果码枚举
     * @return SystemException实例
     */
    public static SystemException of(ErrorCode errorCode) {
        return new SystemException(errorCode);
    }

    /**
     * 创建系统异常
     *
     * @param errorCode  结果码枚举
     * @param message    自定义错误消息
     * @return SystemException实例
     */
    public static SystemException of(ErrorCode errorCode, String message) {
        return new SystemException(errorCode, message);
    }

    /**
     * 创建系统异常
     *
     * @param code    错误码
     * @param message 错误消息
     * @return SystemException实例
     */
    public static SystemException of(Integer code, String message) {
        return new SystemException(code, message);
    }

    /**
     * 创建数据库异常
     *
     * @param message 错误消息
     * @return SystemException实例
     */
    public static SystemException database(String message) {
        return new SystemException(ErrorCode.DATABASE_ERROR, message);
    }

    /**
     * 创建缓存异常
     *
     * @param message 错误消息
     * @return SystemException实例
     */
    public static SystemException cache(String message) {
        return new SystemException(ErrorCode.CACHE_ERROR, message);
    }

    /**
     * 创建消息队列异常
     *
     * @param message 错误消息
     * @return SystemException实例
     */
    public static SystemException messageQueue(String message) {
        return new SystemException(ErrorCode.MESSAGE_QUEUE_ERROR, message);
    }

    /**
     * 创建第三方服务异常
     *
     * @param message 错误消息
     * @return SystemException实例
     */
    public static SystemException thirdPartyService(String message) {
        return new SystemException(ErrorCode.THIRD_PARTY_SERVICE_ERROR, message);
    }
}
