package cn.jcodenest.wiki.common.annotation;

import cn.jcodenest.wiki.common.enums.ValidationType;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * 参数校验注解
 * 用于标记需要进行参数校验的方法或参数
 *
 * @author JCodeNest
 * @version 1.0.0
 * @since 2025/7/24
 * <p>
 * Copyright (c) 2025 JCodeNest-Wiki
 * All rights reserved.
 */
@Target({ElementType.METHOD, ElementType.PARAMETER, ElementType.FIELD})
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface ValidateParam {

    /**
     * 校验规则描述
     *
     * @return 校验规则描述
     */
    String value() default "";

    /**
     * 是否必需（非空校验）
     *
     * @return true-必需，false-可选
     */
    boolean required() default true;

    /**
     * 最小长度（适用于字符串）
     *
     * @return 最小长度
     */
    int minLength() default 0;

    /**
     * 最大长度（适用于字符串）
     *
     * @return 最大长度
     */
    int maxLength() default Integer.MAX_VALUE;

    /**
     * 最小值（适用于数值）
     *
     * @return 最小值
     */
    long minValue() default Long.MIN_VALUE;

    /**
     * 最大值（适用于数值）
     *
     * @return 最大值
     */
    long maxValue() default Long.MAX_VALUE;

    /**
     * 正则表达式模式
     *
     * @return 正则表达式
     */
    String pattern() default "";

    /**
     * 校验类型
     *
     * @return 校验类型
     */
    ValidationType type() default ValidationType.NONE;

    /**
     * 允许的值列表
     *
     * @return 允许的值数组
     */
    String[] allowedValues() default {};

    /**
     * 校验失败时的错误消息
     *
     * @return 错误消息
     */
    String message() default "";

    /**
     * 校验分组
     *
     * @return 校验分组
     */
    String group() default "default";

    /**
     * 是否启用校验
     *
     * @return true-启用，false-禁用
     */
    boolean enabled() default true;

    /**
     * 校验顺序（数值越小越先执行）
     *
     * @return 校验顺序
     */
    int order() default 0;
}
