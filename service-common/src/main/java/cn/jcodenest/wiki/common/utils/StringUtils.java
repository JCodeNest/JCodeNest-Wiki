package cn.jcodenest.wiki.common.utils;

import cn.jcodenest.wiki.common.constant.CommonConstants;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import java.security.SecureRandom;
import java.util.UUID;

/**
 * 字符串处理工具类
 *
 * @author JCodeNest
 * @version 1.0.0
 * @since 2025/7/24
 * <p>
 * Copyright (c) 2025 JCodeNest-Wiki
 * All rights reserved.
 */
@Slf4j
@NoArgsConstructor(access = AccessLevel.PRIVATE)
public final class StringUtils extends org.apache.commons.lang3.StringUtils {

    /**
     * 随机字符串字符集
     */
    private static final String RANDOM_CHARS = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789";
    private static final String RANDOM_NUMBERS = "0123456789";
    private static final String RANDOM_LETTERS = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz";

    /**
     * 安全随机数生成器
     */
    private static final SecureRandom SECURE_RANDOM = new SecureRandom();

    /**
     * 判断字符串是否为空或null
     *
     * @param str 字符串
     * @return true-空或null，false-非空
     */
    public static boolean isEmpty(String str) {
        return str == null || str.isEmpty();
    }

    /**
     * 判断字符串是否非空且非null
     *
     * @param str 字符串
     * @return true-非空，false-空或null
     */
    public static boolean isNotEmpty(String str) {
        return !isEmpty(str);
    }

    /**
     * 判断字符串是否为空白（null、空字符串或只包含空白字符）
     *
     * @param str 字符串
     * @return true-空白，false-非空白
     */
    public static boolean isBlank(String str) {
        return str == null || str.trim().isEmpty();
    }

    /**
     * 判断字符串是否非空白
     *
     * @param str 字符串
     * @return true-非空白，false-空白
     */
    public static boolean isNotBlank(String str) {
        return !isBlank(str);
    }

    /**
     * 安全地获取字符串，如果为null则返回空字符串
     *
     * @param str 字符串
     * @return 非null的字符串
     */
    public static String nullToEmpty(String str) {
        return str == null ? CommonConstants.String.EMPTY : str;
    }

    /**
     * 安全地获取字符串，如果为空则返回默认值
     *
     * @param str          字符串
     * @param defaultValue 默认值
     * @return 非空字符串或默认值
     */
    public static String emptyToDefault(String str, String defaultValue) {
        return isEmpty(str) ? defaultValue : str;
    }

    /**
     * 安全地获取字符串，如果为空白则返回默认值
     *
     * @param str          字符串
     * @param defaultValue 默认值
     * @return 非空白字符串或默认值
     */
    public static String blankToDefault(String str, String defaultValue) {
        return isBlank(str) ? defaultValue : str;
    }

    /**
     * 生成随机字符串（包含字母和数字）
     *
     * @param length 长度
     * @return 随机字符串
     */
    public static String randomString(int length) {
        if (length <= 0) {
            return CommonConstants.String.EMPTY;
        }
        
        StringBuilder sb = new StringBuilder(length);
        for (int i = 0; i < length; i++) {
            sb.append(RANDOM_CHARS.charAt(SECURE_RANDOM.nextInt(RANDOM_CHARS.length())));
        }
        return sb.toString();
    }

    /**
     * 生成随机数字字符串
     *
     * @param length 长度
     * @return 随机数字字符串
     */
    public static String randomNumbers(int length) {
        if (length <= 0) {
            return CommonConstants.String.EMPTY;
        }
        
        StringBuilder sb = new StringBuilder(length);
        for (int i = 0; i < length; i++) {
            sb.append(RANDOM_NUMBERS.charAt(SECURE_RANDOM.nextInt(RANDOM_NUMBERS.length())));
        }
        return sb.toString();
    }

    /**
     * 生成随机字母字符串
     *
     * @param length 长度
     * @return 随机字母字符串
     */
    public static String randomLetters(int length) {
        if (length <= 0) {
            return CommonConstants.String.EMPTY;
        }
        
        StringBuilder sb = new StringBuilder(length);
        for (int i = 0; i < length; i++) {
            sb.append(RANDOM_LETTERS.charAt(SECURE_RANDOM.nextInt(RANDOM_LETTERS.length())));
        }
        return sb.toString();
    }

    /**
     * 生成UUID字符串（去除横线）
     *
     * @return UUID字符串
     */
    public static String uuid() {
        return UUID.randomUUID().toString().replace(CommonConstants.String.HYPHEN, CommonConstants.String.EMPTY);
    }

    /**
     * 生成带横线的UUID字符串
     *
     * @return 带横线的UUID字符串
     */
    public static String uuidWithHyphen() {
        return UUID.randomUUID().toString();
    }

    /**
     * 驼峰命名转下划线命名
     *
     * @param camelCase 驼峰命名字符串
     * @return 下划线命名字符串
     */
    public static String camelToUnderscore(String camelCase) {
        if (isBlank(camelCase)) {
            return camelCase;
        }
        
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < camelCase.length(); i++) {
            char c = camelCase.charAt(i);
            if (Character.isUpperCase(c)) {
                if (i > 0) {
                    sb.append(CommonConstants.String.UNDERSCORE);
                }
                sb.append(Character.toLowerCase(c));
            } else {
                sb.append(c);
            }
        }
        return sb.toString();
    }

    /**
     * 下划线命名转驼峰命名
     *
     * @param underscore 下划线命名字符串
     * @return 驼峰命名字符串
     */
    public static String underscoreToCamel(String underscore) {
        if (isBlank(underscore)) {
            return underscore;
        }
        
        StringBuilder sb = new StringBuilder();
        boolean nextUpperCase = false;
        
        for (int i = 0; i < underscore.length(); i++) {
            char c = underscore.charAt(i);
            if (c == '_') {
                nextUpperCase = true;
            } else {
                if (nextUpperCase) {
                    sb.append(Character.toUpperCase(c));
                    nextUpperCase = false;
                } else {
                    sb.append(c);
                }
            }
        }
        return sb.toString();
    }

    /**
     * 首字母大写
     *
     * @param str 字符串
     * @return 首字母大写的字符串
     */
    public static String capitalize(String str) {
        if (isBlank(str)) {
            return str;
        }
        
        return Character.toUpperCase(str.charAt(0)) + str.substring(1);
    }

    /**
     * 首字母小写
     *
     * @param str 字符串
     * @return 首字母小写的字符串
     */
    public static String uncapitalize(String str) {
        if (isBlank(str)) {
            return str;
        }
        
        return Character.toLowerCase(str.charAt(0)) + str.substring(1);
    }

    /**
     * 截取字符串，超出长度用省略号表示
     *
     * @param str       字符串
     * @param maxLength 最大长度
     * @return 截取后的字符串
     */
    public static String truncate(String str, int maxLength) {
        return truncate(str, maxLength, "...");
    }

    /**
     * 截取字符串，超出长度用指定后缀表示
     *
     * @param str       字符串
     * @param maxLength 最大长度
     * @param suffix    后缀
     * @return 截取后的字符串
     */
    public static String truncate(String str, int maxLength, String suffix) {
        if (isBlank(str) || maxLength <= 0) {
            return str;
        }
        
        if (str.length() <= maxLength) {
            return str;
        }
        
        String safeSuffix = nullToEmpty(suffix);
        int truncateLength = maxLength - safeSuffix.length();
        
        if (truncateLength <= 0) {
            return safeSuffix.substring(0, Math.min(maxLength, safeSuffix.length()));
        }
        
        return str.substring(0, truncateLength) + safeSuffix;
    }

    /**
     * 掩码处理，隐藏敏感信息
     *
     * @param str        字符串
     * @param startIndex 开始位置
     * @param endIndex   结束位置
     * @param maskChar   掩码字符
     * @return 掩码后的字符串
     */
    public static String mask(String str, int startIndex, int endIndex, char maskChar) {
        if (isBlank(str) || startIndex < 0 || endIndex >= str.length() || startIndex > endIndex) {
            return str;
        }
        
        StringBuilder sb = new StringBuilder(str);
        for (int i = startIndex; i <= endIndex; i++) {
            sb.setCharAt(i, maskChar);
        }
        return sb.toString();
    }

    /**
     * 手机号掩码处理
     *
     * @param mobile 手机号
     * @return 掩码后的手机号
     */
    public static String maskMobile(String mobile) {
        if (isBlank(mobile) || mobile.length() != 11) {
            return mobile;
        }
        return mask(mobile, 3, 6, '*');
    }

    /**
     * 邮箱掩码处理
     *
     * @param email 邮箱
     * @return 掩码后的邮箱
     */
    public static String maskEmail(String email) {
        if (isBlank(email) || !email.contains("@")) {
            return email;
        }
        
        int atIndex = email.indexOf('@');
        String username = email.substring(0, atIndex);
        String domain = email.substring(atIndex);
        
        if (username.length() <= 2) {
            return email;
        }
        
        String maskedUsername = username.charAt(0) + 
            "*".repeat(username.length() - 2) + 
            username.charAt(username.length() - 1);
        
        return maskedUsername + domain;
    }

    /**
     * 身份证号掩码处理
     *
     * @param idCard 身份证号
     * @return 掩码后的身份证号
     */
    public static String maskIdCard(String idCard) {
        if (isBlank(idCard) || idCard.length() < 8) {
            return idCard;
        }
        return mask(idCard, 4, idCard.length() - 5, '*');
    }
}
