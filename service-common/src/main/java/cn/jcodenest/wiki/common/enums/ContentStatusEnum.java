package cn.jcodenest.wiki.common.enums;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * 内容状态枚举
 *
 * @author JCodeNest
 * @version 1.0.0
 * @since 2025/7/24
 * <p>
 * Copyright (c) 2025 JCodeNest-Wiki
 * All rights reserved.
 */
@Getter
@AllArgsConstructor(access = AccessLevel.PROTECTED)
public enum ContentStatusEnum {

    /**
     * 草稿
     */
    DRAFT(0, "草稿", "内容处于草稿状态"),

    /**
     * 已发布
     */
    PUBLISHED(1, "已发布", "内容已发布"),

    /**
     * 审核中
     */
    UNDER_REVIEW(2, "审核中", "内容正在审核中"),

    /**
     * 审核失败
     */
    REVIEW_FAILED(3, "审核失败", "内容审核失败"),

    /**
     * 已下架
     */
    OFFLINE(4, "已下架", "内容已下架");

    /**
     * 状态码
     */
    private final Integer code;

    /**
     * 状态名称
     */
    private final String name;

    /**
     * 状态描述
     */
    private final String description;

    /**
     * 根据状态码获取枚举
     *
     * @param code 状态码
     * @return ContentStatusEnum枚举，如果不存在则返回null
     */
    public static ContentStatusEnum getByCode(Integer code) {
        if (code == null) {
            return null;
        }
        
        for (ContentStatusEnum status : values()) {
            if (status.getCode().equals(code)) {
                return status;
            }
        }
        return null;
    }

    /**
     * 根据状态名称获取枚举
     *
     * @param name 状态名称
     * @return ContentStatusEnum枚举，如果不存在则返回null
     */
    public static ContentStatusEnum getByName(String name) {
        if (name == null || name.trim().isEmpty()) {
            return null;
        }
        
        for (ContentStatusEnum status : values()) {
            if (status.getName().equals(name.trim())) {
                return status;
            }
        }
        return null;
    }

    /**
     * 判断是否为有效状态码
     *
     * @param code 状态码
     * @return true-有效，false-无效
     */
    public static boolean isValidCode(Integer code) {
        return getByCode(code) != null;
    }

    /**
     * 判断内容是否为草稿状态
     *
     * @param code 状态码
     * @return true-草稿，false-非草稿
     */
    public static boolean isDraft(Integer code) {
        return DRAFT.getCode().equals(code);
    }

    /**
     * 判断内容是否已发布
     *
     * @param code 状态码
     * @return true-已发布，false-未发布
     */
    public static boolean isPublished(Integer code) {
        return PUBLISHED.getCode().equals(code);
    }

    /**
     * 判断内容是否在审核中
     *
     * @param code 状态码
     * @return true-审核中，false-非审核中
     */
    public static boolean isUnderReview(Integer code) {
        return UNDER_REVIEW.getCode().equals(code);
    }

    /**
     * 判断内容审核是否失败
     *
     * @param code 状态码
     * @return true-审核失败，false-审核未失败
     */
    public static boolean isReviewFailed(Integer code) {
        return REVIEW_FAILED.getCode().equals(code);
    }

    /**
     * 判断内容是否已下架
     *
     * @param code 状态码
     * @return true-已下架，false-未下架
     */
    public static boolean isOffline(Integer code) {
        return OFFLINE.getCode().equals(code);
    }

    /**
     * 判断内容是否可以编辑
     *
     * @param code 状态码
     * @return true-可编辑，false-不可编辑
     */
    public static boolean canEdit(Integer code) {
        return isDraft(code) || isReviewFailed(code);
    }

    /**
     * 判断内容是否可以发布
     *
     * @param code 状态码
     * @return true-可发布，false-不可发布
     */
    public static boolean canPublish(Integer code) {
        return isDraft(code) || isReviewFailed(code);
    }

    /**
     * 判断内容是否可以下架
     *
     * @param code 状态码
     * @return true-可下架，false-不可下架
     */
    public static boolean canOffline(Integer code) {
        return isPublished(code);
    }

    /**
     * 获取所有状态码
     *
     * @return 状态码数组
     */
    public static Integer[] getAllCodes() {
        ContentStatusEnum[] values = values();
        Integer[] codes = new Integer[values.length];
        for (int i = 0; i < values.length; i++) {
            codes[i] = values[i].getCode();
        }
        return codes;
    }

    /**
     * 获取所有状态名称
     *
     * @return 状态名称数组
     */
    public static String[] getAllNames() {
        ContentStatusEnum[] values = values();
        String[] names = new String[values.length];
        for (int i = 0; i < values.length; i++) {
            names[i] = values[i].getName();
        }
        return names;
    }

    /**
     * 转换为字符串
     *
     * @return 字符串表示
     */
    @Override
    public String toString() {
        return String.format("ContentStatusEnum{code=%d, name='%s', description='%s'}", code, name, description);
    }
}
