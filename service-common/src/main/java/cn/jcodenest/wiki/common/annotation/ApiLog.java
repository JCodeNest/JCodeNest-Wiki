package cn.jcodenest.wiki.common.annotation;

import cn.jcodenest.wiki.common.enums.LogLevel;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * 接口日志注解
 * 用于标记需要记录操作日志的接口方法
 *
 * @author JCodeNest
 * @version 1.0.0
 * @since 2025/7/24
 * <p>
 * Copyright (c) 2025 JCodeNest-Wiki
 * All rights reserved.
 */
@Target({ElementType.METHOD, ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface ApiLog {

    /**
     * 操作描述
     *
     * @return 操作描述
     */
    String value() default "";

    /**
     * 操作类型
     *
     * @return 操作类型
     */
    String operationType() default "";

    /**
     * 业务模块
     *
     * @return 业务模块
     */
    String module() default "";

    /**
     * 是否记录请求参数
     *
     * @return true-记录，false-不记录
     */
    boolean logRequest() default true;

    /**
     * 是否记录响应结果
     *
     * @return true-记录，false-不记录
     */
    boolean logResponse() default true;

    /**
     * 是否记录异常信息
     *
     * @return true-记录，false-不记录
     */
    boolean logException() default true;

    /**
     * 是否记录执行时间
     *
     * @return true-记录，false-不记录
     */
    boolean logExecutionTime() default true;

    /**
     * 敏感参数名称（不记录到日志中）
     *
     * @return 敏感参数名称数组
     */
    String[] sensitiveParams() default {"password", "pwd", "token", "secret", "key"};

    /**
     * 日志级别
     *
     * @return 日志级别
     */
    LogLevel level() default LogLevel.INFO;

    /**
     * 是否异步记录日志
     *
     * @return true-异步，false-同步
     */
    boolean async() default true;
}
