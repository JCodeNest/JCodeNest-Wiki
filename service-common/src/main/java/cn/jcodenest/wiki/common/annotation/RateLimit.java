package cn.jcodenest.wiki.common.annotation;

import cn.jcodenest.wiki.common.constant.SecurityConstants;
import cn.jcodenest.wiki.common.enums.AlgorithmEnum;
import cn.jcodenest.wiki.common.enums.LimitTypeEnum;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * 限流注解
 * 用于控制接口访问频率，防止恶意请求
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
public @interface RateLimit {

    /**
     * 限流key前缀
     *
     * @return 限流key前缀
     */
    String key() default "";

    /**
     * 限流次数
     *
     * @return 限流次数
     */
    int count() default SecurityConstants.RateLimit.DEFAULT_COUNT;

    /**
     * 限流时间窗口（秒）
     *
     * @return 限流时间窗口
     */
    long period() default SecurityConstants.RateLimit.DEFAULT_PERIOD;

    /**
     * 限流类型
     *
     * @return 限流类型
     */
    LimitTypeEnum limitType() default LimitTypeEnum.DEFAULT;

    /**
     * 限流失败时的提示消息
     *
     * @return 提示消息
     */
    String message() default "访问过于频繁，请稍后再试";

    /**
     * 是否启用限流
     *
     * @return true-启用，false-禁用
     */
    boolean enabled() default true;

    /**
     * 限流算法类型
     *
     * @return 限流算法类型
     */
    AlgorithmEnum algorithm() default AlgorithmEnum.SLIDING_WINDOW;
}
