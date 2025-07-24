package cn.jcodenest.wiki.common.enums;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * 订单状态枚举
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
public enum OrderStatusEnum {

    /**
     * 待支付
     */
    PENDING_PAYMENT(1, "待支付", "订单待支付"),

    /**
     * 已支付
     */
    PAID(2, "已支付", "订单已支付"),

    /**
     * 已取消
     */
    CANCELLED(3, "已取消", "订单已取消"),

    /**
     * 已退款
     */
    REFUNDED(4, "已退款", "订单已退款"),

    /**
     * 部分退款
     */
    PARTIAL_REFUNDED(5, "部分退款", "订单部分退款");

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
     * @return OrderStatusEnum枚举，如果不存在则返回null
     */
    public static OrderStatusEnum getByCode(Integer code) {
        if (code == null) {
            return null;
        }
        
        for (OrderStatusEnum status : values()) {
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
     * @return OrderStatusEnum枚举，如果不存在则返回null
     */
    public static OrderStatusEnum getByName(String name) {
        if (name == null || name.trim().isEmpty()) {
            return null;
        }
        
        for (OrderStatusEnum status : values()) {
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
     * 判断订单是否待支付
     *
     * @param code 状态码
     * @return true-待支付，false-非待支付
     */
    public static boolean isPendingPayment(Integer code) {
        return PENDING_PAYMENT.getCode().equals(code);
    }

    /**
     * 判断订单是否已支付
     *
     * @param code 状态码
     * @return true-已支付，false-未支付
     */
    public static boolean isPaid(Integer code) {
        return PAID.getCode().equals(code);
    }

    /**
     * 判断订单是否已取消
     *
     * @param code 状态码
     * @return true-已取消，false-未取消
     */
    public static boolean isCancelled(Integer code) {
        return CANCELLED.getCode().equals(code);
    }

    /**
     * 判断订单是否已退款
     *
     * @param code 状态码
     * @return true-已退款，false-未退款
     */
    public static boolean isRefunded(Integer code) {
        return REFUNDED.getCode().equals(code);
    }

    /**
     * 判断订单是否部分退款
     *
     * @param code 状态码
     * @return true-部分退款，false-非部分退款
     */
    public static boolean isPartialRefunded(Integer code) {
        return PARTIAL_REFUNDED.getCode().equals(code);
    }

    /**
     * 判断订单是否可以支付
     *
     * @param code 状态码
     * @return true-可支付，false-不可支付
     */
    public static boolean canPay(Integer code) {
        return isPendingPayment(code);
    }

    /**
     * 判断订单是否可以取消
     *
     * @param code 状态码
     * @return true-可取消，false-不可取消
     */
    public static boolean canCancel(Integer code) {
        return isPendingPayment(code);
    }

    /**
     * 判断订单是否可以退款
     *
     * @param code 状态码
     * @return true-可退款，false-不可退款
     */
    public static boolean canRefund(Integer code) {
        return isPaid(code) || isPartialRefunded(code);
    }

    /**
     * 判断订单是否为最终状态
     *
     * @param code 状态码
     * @return true-最终状态，false-非最终状态
     */
    public static boolean isFinalStatus(Integer code) {
        return isPaid(code) || isCancelled(code) || isRefunded(code);
    }

    /**
     * 获取所有状态码
     *
     * @return 状态码数组
     */
    public static Integer[] getAllCodes() {
        OrderStatusEnum[] values = values();
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
        OrderStatusEnum[] values = values();
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
        return String.format("OrderStatusEnum{code=%d, name='%s', description='%s'}", code, name, description);
    }
}
