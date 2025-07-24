package cn.jcodenest.wiki.common.enums;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * 支付方式枚举
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
public enum PaymentMethodEnum {

    /**
     * 支付宝
     */
    ALIPAY("alipay", "支付宝", "支付宝支付"),

    /**
     * 微信支付
     */
    WECHAT("wechat", "微信支付", "微信支付"),

    /**
     * 银行卡
     */
    BANK_CARD("bank_card", "银行卡", "银行卡支付"),

    /**
     * 余额支付
     */
    BALANCE("balance", "余额支付", "账户余额支付"),

    /**
     * 积分支付
     */
    POINTS("points", "积分支付", "积分兑换支付");

    /**
     * 支付方式代码
     */
    private final String code;

    /**
     * 支付方式名称
     */
    private final String name;

    /**
     * 支付方式描述
     */
    private final String description;

    /**
     * 根据支付方式代码获取枚举
     *
     * @param code 支付方式代码
     * @return PaymentMethodEnum枚举，如果不存在则返回null
     */
    public static PaymentMethodEnum getByCode(String code) {
        if (code == null || code.trim().isEmpty()) {
            return null;
        }
        
        for (PaymentMethodEnum method : values()) {
            if (method.getCode().equals(code.trim())) {
                return method;
            }
        }
        return null;
    }

    /**
     * 根据支付方式名称获取枚举
     *
     * @param name 支付方式名称
     * @return PaymentMethodEnum枚举，如果不存在则返回null
     */
    public static PaymentMethodEnum getByName(String name) {
        if (name == null || name.trim().isEmpty()) {
            return null;
        }
        
        for (PaymentMethodEnum method : values()) {
            if (method.getName().equals(name.trim())) {
                return method;
            }
        }
        return null;
    }

    /**
     * 判断是否为有效支付方式代码
     *
     * @param code 支付方式代码
     * @return true-有效，false-无效
     */
    public static boolean isValidCode(String code) {
        return getByCode(code) != null;
    }

    /**
     * 判断是否为支付宝支付
     *
     * @param code 支付方式代码
     * @return true-支付宝，false-非支付宝
     */
    public static boolean isAlipay(String code) {
        return ALIPAY.getCode().equals(code);
    }

    /**
     * 判断是否为微信支付
     *
     * @param code 支付方式代码
     * @return true-微信支付，false-非微信支付
     */
    public static boolean isWechat(String code) {
        return WECHAT.getCode().equals(code);
    }

    /**
     * 判断是否为银行卡支付
     *
     * @param code 支付方式代码
     * @return true-银行卡，false-非银行卡
     */
    public static boolean isBankCard(String code) {
        return BANK_CARD.getCode().equals(code);
    }

    /**
     * 判断是否为余额支付
     *
     * @param code 支付方式代码
     * @return true-余额支付，false-非余额支付
     */
    public static boolean isBalance(String code) {
        return BALANCE.getCode().equals(code);
    }

    /**
     * 判断是否为积分支付
     *
     * @param code 支付方式代码
     * @return true-积分支付，false-非积分支付
     */
    public static boolean isPoints(String code) {
        return POINTS.getCode().equals(code);
    }

    /**
     * 判断是否为第三方支付
     *
     * @param code 支付方式代码
     * @return true-第三方支付，false-非第三方支付
     */
    public static boolean isThirdParty(String code) {
        return isAlipay(code) || isWechat(code) || isBankCard(code);
    }

    /**
     * 判断是否为内部支付
     *
     * @param code 支付方式代码
     * @return true-内部支付，false-非内部支付
     */
    public static boolean isInternal(String code) {
        return isBalance(code) || isPoints(code);
    }

    /**
     * 判断是否需要跳转到第三方页面
     *
     * @param code 支付方式代码
     * @return true-需要跳转，false-不需要跳转
     */
    public static boolean needRedirect(String code) {
        return isAlipay(code) || isWechat(code) || isBankCard(code);
    }

    /**
     * 获取所有支付方式代码
     *
     * @return 支付方式代码数组
     */
    public static String[] getAllCodes() {
        PaymentMethodEnum[] values = values();
        String[] codes = new String[values.length];
        for (int i = 0; i < values.length; i++) {
            codes[i] = values[i].getCode();
        }
        return codes;
    }

    /**
     * 获取所有支付方式名称
     *
     * @return 支付方式名称数组
     */
    public static String[] getAllNames() {
        PaymentMethodEnum[] values = values();
        String[] names = new String[values.length];
        for (int i = 0; i < values.length; i++) {
            names[i] = values[i].getName();
        }
        return names;
    }

    /**
     * 获取第三方支付方式
     *
     * @return 第三方支付方式数组
     */
    public static PaymentMethodEnum[] getThirdPartyMethods() {
        return new PaymentMethodEnum[]{ALIPAY, WECHAT, BANK_CARD};
    }

    /**
     * 获取内部支付方式
     *
     * @return 内部支付方式数组
     */
    public static PaymentMethodEnum[] getInternalMethods() {
        return new PaymentMethodEnum[]{BALANCE, POINTS};
    }

    /**
     * 转换为字符串
     *
     * @return 字符串表示
     */
    @Override
    public String toString() {
        return String.format("PaymentMethodEnum{code='%s', name='%s', description='%s'}", code, name, description);
    }
}
