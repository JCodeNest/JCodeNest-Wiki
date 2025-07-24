package cn.jcodenest.wiki.common.enums;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * 用户状态枚举
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
public enum UserStatusEnum {

    /**
     * 禁用
     */
    DISABLED(0, "禁用", "用户账户已被禁用"),

    /**
     * 正常
     */
    NORMAL(1, "正常", "用户账户状态正常"),

    /**
     * 待激活
     */
    PENDING_ACTIVATION(2, "待激活", "用户账户待激活");

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
     * @return UserStatusEnum枚举，如果不存在则返回null
     */
    public static UserStatusEnum getByCode(Integer code) {
        if (code == null) {
            return null;
        }
        
        for (UserStatusEnum status : values()) {
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
     * @return UserStatusEnum枚举，如果不存在则返回null
     */
    public static UserStatusEnum getByName(String name) {
        if (name == null || name.trim().isEmpty()) {
            return null;
        }
        
        for (UserStatusEnum status : values()) {
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
     * 判断用户是否正常状态
     *
     * @param code 状态码
     * @return true-正常，false-非正常
     */
    public static boolean isNormal(Integer code) {
        return NORMAL.getCode().equals(code);
    }

    /**
     * 判断用户是否被禁用
     *
     * @param code 状态码
     * @return true-禁用，false-未禁用
     */
    public static boolean isDisabled(Integer code) {
        return DISABLED.getCode().equals(code);
    }

    /**
     * 判断用户是否待激活
     *
     * @param code 状态码
     * @return true-待激活，false-非待激活
     */
    public static boolean isPendingActivation(Integer code) {
        return PENDING_ACTIVATION.getCode().equals(code);
    }

    /**
     * 获取所有状态码
     *
     * @return 状态码数组
     */
    public static Integer[] getAllCodes() {
        UserStatusEnum[] values = values();
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
        UserStatusEnum[] values = values();
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
        return String.format("UserStatusEnum{code=%d, name='%s', description='%s'}", code, name, description);
    }
}
