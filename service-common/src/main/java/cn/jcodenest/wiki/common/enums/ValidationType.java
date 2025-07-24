package cn.jcodenest.wiki.common.enums;

/**
 * 校验类型枚举
 *
 * @author JCodeNest
 * @version 1.0.0
 * @since 2025/7/24
 * <p>
 * Copyright (c) 2025 JCodeNest-Wiki
 * All rights reserved.
 */
public enum ValidationType {
    /**
     * 无特殊校验
     */
    NONE,

    /**
     * 邮箱格式校验
     */
    EMAIL,

    /**
     * 手机号格式校验
     */
    MOBILE,

    /**
     * 身份证号格式校验
     */
    ID_CARD,

    /**
     * 用户名格式校验
     */
    USERNAME,

    /**
     * 密码格式校验
     */
    PASSWORD,

    /**
     * IP地址格式校验
     */
    IP_ADDRESS,

    /**
     * URL格式校验
     */
    URL,

    /**
     * 日期格式校验
     */
    DATE,

    /**
     * 数字格式校验
     */
    NUMBER,

    /**
     * 正整数校验
     */
    POSITIVE_INTEGER,

    /**
     * JSON格式校验
     */
    JSON,

    /**
     * 自定义校验
     */
    CUSTOM
}
