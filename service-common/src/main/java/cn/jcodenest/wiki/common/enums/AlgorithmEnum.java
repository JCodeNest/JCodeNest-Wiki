package cn.jcodenest.wiki.common.enums;

/**
 * 限流算法枚举
 *
 * @author JCodeNest
 * @version 1.0.0
 * @since 2025/7/24
 * <p>
 * Copyright (c) 2025 JCodeNest-Wiki
 * All rights reserved.
 */
public enum AlgorithmEnum {
    /**
     * 固定窗口算法
     */
    FIXED_WINDOW,

    /**
     * 滑动窗口算法
     */
    SLIDING_WINDOW,

    /**
     * 令牌桶算法
     */
    TOKEN_BUCKET,

    /**
     * 漏桶算法
     */
    LEAKY_BUCKET
}
