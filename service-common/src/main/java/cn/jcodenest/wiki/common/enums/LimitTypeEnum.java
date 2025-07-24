package cn.jcodenest.wiki.common.enums;

/**
 * 限流类型枚举
 *
 * @author JCodeNest
 * @version 1.0.0
 * @since 2025/7/24
 * <p>
 * Copyright (c) 2025 JCodeNest-Wiki
 * All rights reserved.
 */
public enum LimitTypeEnum {
    /**
     * 默认策略（根据请求者IP）
     */
    DEFAULT,

    /**
     * 根据请求者IP
     */
    IP,

    /**
     * 根据用户ID
     */
    USER,

    /**
     * 根据自定义key
     */
    CUSTOM,

    /**
     * 全局限流
     */
    GLOBAL
}
