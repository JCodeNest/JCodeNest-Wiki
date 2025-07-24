package cn.jcodenest.wiki.common.utils;

import cn.jcodenest.wiki.common.constant.CommonConstants;
import cn.jcodenest.wiki.common.exception.ValidationException;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import java.util.Collection;
import java.util.regex.Pattern;

/**
 * 参数校验工具类
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
public final class ValidationUtils {

    /**
     * 编译后的正则表达式模式
     */
    private static final Pattern EMAIL_PATTERN = Pattern.compile(CommonConstants.Regex.EMAIL);
    private static final Pattern MOBILE_PATTERN = Pattern.compile(CommonConstants.Regex.MOBILE);
    private static final Pattern ID_CARD_PATTERN = Pattern.compile(CommonConstants.Regex.ID_CARD);
    private static final Pattern USERNAME_PATTERN = Pattern.compile(CommonConstants.Regex.USERNAME);
    private static final Pattern PASSWORD_PATTERN = Pattern.compile(CommonConstants.Regex.PASSWORD);
    private static final Pattern IP_PATTERN = Pattern.compile(CommonConstants.Regex.IP);
    private static final Pattern URL_PATTERN = Pattern.compile(CommonConstants.Regex.URL);

    /**
     * 校验参数不为null
     *
     * @param value     参数值
     * @param paramName 参数名称
     * @throws ValidationException 参数为null时抛出异常
     */
    public static void notNull(Object value, String paramName) {
        if (value == null) {
            throw ValidationException.parameterNull(paramName);
        }
    }

    /**
     * 校验字符串不为空
     *
     * @param value     字符串值
     * @param paramName 参数名称
     * @throws ValidationException 字符串为空时抛出异常
     */
    public static void notEmpty(String value, String paramName) {
        if (StringUtils.isEmpty(value)) {
            throw ValidationException.parameterNull(paramName);
        }
    }

    /**
     * 校验字符串不为空白
     *
     * @param value     字符串值
     * @param paramName 参数名称
     * @throws ValidationException 字符串为空白时抛出异常
     */
    public static void notBlank(String value, String paramName) {
        if (StringUtils.isBlank(value)) {
            throw ValidationException.parameterNull(paramName);
        }
    }

    /**
     * 校验集合不为空
     *
     * @param collection 集合
     * @param paramName  参数名称
     * @throws ValidationException 集合为空时抛出异常
     */
    public static void notEmpty(Collection<?> collection, String paramName) {
        if (collection == null || collection.isEmpty()) {
            throw ValidationException.parameterNull(paramName);
        }
    }

    /**
     * 校验数组不为空
     *
     * @param array     数组
     * @param paramName 参数名称
     * @throws ValidationException 数组为空时抛出异常
     */
    public static void notEmpty(Object[] array, String paramName) {
        if (array == null || array.length == 0) {
            throw ValidationException.parameterNull(paramName);
        }
    }

    /**
     * 校验字符串长度
     *
     * @param value     字符串值
     * @param minLength 最小长度
     * @param maxLength 最大长度
     * @param paramName 参数名称
     * @throws ValidationException 长度不符合要求时抛出异常
     */
    public static void length(String value, int minLength, int maxLength, String paramName) {
        if (value == null) {
            throw ValidationException.parameterNull(paramName);
        }
        
        int length = value.length();
        if (length < minLength || length > maxLength) {
            throw ValidationException.parameterLength(paramName, minLength, maxLength);
        }
    }

    /**
     * 校验数值范围
     *
     * @param value     数值
     * @param minValue  最小值
     * @param maxValue  最大值
     * @param paramName 参数名称
     * @throws ValidationException 数值超出范围时抛出异常
     */
    public static void range(Number value, Number minValue, Number maxValue, String paramName) {
        if (value == null) {
            throw ValidationException.parameterNull(paramName);
        }
        
        double doubleValue = value.doubleValue();
        double min = minValue.doubleValue();
        double max = maxValue.doubleValue();
        
        if (doubleValue < min || doubleValue > max) {
            throw ValidationException.parameterRange(paramName, minValue, maxValue);
        }
    }

    /**
     * 校验正整数
     *
     * @param value     数值
     * @param paramName 参数名称
     * @throws ValidationException 不是正整数时抛出异常
     */
    public static void positiveInteger(Number value, String paramName) {
        if (value == null) {
            throw ValidationException.parameterNull(paramName);
        }
        
        if (value.longValue() <= 0) {
            throw new ValidationException(String.format("参数 [%s] 必须是正整数", paramName));
        }
    }

    /**
     * 校验邮箱格式
     *
     * @param email     邮箱地址
     * @param paramName 参数名称
     * @throws ValidationException 邮箱格式错误时抛出异常
     */
    public static void email(String email, String paramName) {
        notBlank(email, paramName);
        
        if (!EMAIL_PATTERN.matcher(email).matches()) {
            throw ValidationException.parameterFormat(paramName);
        }
    }

    /**
     * 校验手机号格式
     *
     * @param mobile    手机号
     * @param paramName 参数名称
     * @throws ValidationException 手机号格式错误时抛出异常
     */
    public static void mobile(String mobile, String paramName) {
        notBlank(mobile, paramName);
        
        if (!MOBILE_PATTERN.matcher(mobile).matches()) {
            throw ValidationException.parameterFormat(paramName);
        }
    }

    /**
     * 校验身份证号格式
     *
     * @param idCard    身份证号
     * @param paramName 参数名称
     * @throws ValidationException 身份证号格式错误时抛出异常
     */
    public static void idCard(String idCard, String paramName) {
        notBlank(idCard, paramName);
        
        if (!ID_CARD_PATTERN.matcher(idCard).matches()) {
            throw ValidationException.parameterFormat(paramName);
        }
    }

    /**
     * 校验用户名格式
     *
     * @param username  用户名
     * @param paramName 参数名称
     * @throws ValidationException 用户名格式错误时抛出异常
     */
    public static void username(String username, String paramName) {
        notBlank(username, paramName);
        
        if (!USERNAME_PATTERN.matcher(username).matches()) {
            throw new ValidationException(String.format("参数 [%s] 格式错误，用户名只能包含字母、数字、下划线，长度4-20位", paramName));
        }
    }

    /**
     * 校验密码格式
     *
     * @param password  密码
     * @param paramName 参数名称
     * @throws ValidationException 密码格式错误时抛出异常
     */
    public static void password(String password, String paramName) {
        notBlank(password, paramName);
        
        if (!PASSWORD_PATTERN.matcher(password).matches()) {
            throw new ValidationException(String.format("参数 [%s] 格式错误，密码必须包含字母和数字，长度6-20位", paramName));
        }
    }

    /**
     * 校验IP地址格式
     *
     * @param ip        IP地址
     * @param paramName 参数名称
     * @throws ValidationException IP地址格式错误时抛出异常
     */
    public static void ipAddress(String ip, String paramName) {
        notBlank(ip, paramName);
        
        if (!IP_PATTERN.matcher(ip).matches()) {
            throw ValidationException.parameterFormat(paramName);
        }
    }

    /**
     * 校验URL格式
     *
     * @param url       URL地址
     * @param paramName 参数名称
     * @throws ValidationException URL格式错误时抛出异常
     */
    public static void url(String url, String paramName) {
        notBlank(url, paramName);
        
        if (!URL_PATTERN.matcher(url).matches()) {
            throw ValidationException.parameterFormat(paramName);
        }
    }

    /**
     * 校验正则表达式
     *
     * @param value     字符串值
     * @param pattern   正则表达式模式
     * @param paramName 参数名称
     * @throws ValidationException 不匹配正则表达式时抛出异常
     */
    public static void matches(String value, Pattern pattern, String paramName) {
        notBlank(value, paramName);
        
        if (pattern == null || !pattern.matcher(value).matches()) {
            throw ValidationException.parameterFormat(paramName);
        }
    }

    /**
     * 校验正则表达式
     *
     * @param value     字符串值
     * @param regex     正则表达式
     * @param paramName 参数名称
     * @throws ValidationException 不匹配正则表达式时抛出异常
     */
    public static void matches(String value, String regex, String paramName) {
        notBlank(value, paramName);
        
        if (StringUtils.isBlank(regex) || !value.matches(regex)) {
            throw ValidationException.parameterFormat(paramName);
        }
    }

    /**
     * 校验布尔值
     *
     * @param condition 条件
     * @param message   错误消息
     * @throws ValidationException 条件为false时抛出异常
     */
    public static void isTrue(boolean condition, String message) {
        if (!condition) {
            throw new ValidationException(message);
        }
    }

    /**
     * 校验布尔值
     *
     * @param condition 条件
     * @param message   错误消息
     * @throws ValidationException 条件为true时抛出异常
     */
    public static void isFalse(boolean condition, String message) {
        if (condition) {
            throw new ValidationException(message);
        }
    }

    /**
     * 校验对象相等
     *
     * @param obj1      对象1
     * @param obj2      对象2
     * @param paramName 参数名称
     * @throws ValidationException 对象不相等时抛出异常
     */
    public static void equals(Object obj1, Object obj2, String paramName) {
        if (obj1 == null && obj2 == null) {
            return;
        }
        
        if (obj1 == null || !obj1.equals(obj2)) {
            throw new ValidationException(String.format("参数 [%s] 值不匹配", paramName));
        }
    }

    /**
     * 校验字符串包含指定值
     *
     * @param value       字符串值
     * @param validValues 有效值数组
     * @param paramName   参数名称
     * @throws ValidationException 不包含指定值时抛出异常
     */
    public static void in(String value, String[] validValues, String paramName) {
        notBlank(value, paramName);
        
        if (validValues == null || validValues.length == 0) {
            return;
        }
        
        for (String validValue : validValues) {
            if (value.equals(validValue)) {
                return;
            }
        }
        
        throw new ValidationException(String.format("参数 [%s] 值必须是 %s 中的一个", 
            paramName, String.join(", ", validValues)));
    }

    /**
     * 校验数值包含指定值
     *
     * @param value       数值
     * @param validValues 有效值数组
     * @param paramName   参数名称
     * @throws ValidationException 不包含指定值时抛出异常
     */
    public static void in(Number value, Number[] validValues, String paramName) {
        notNull(value, paramName);
        
        if (validValues == null || validValues.length == 0) {
            return;
        }
        
        for (Number validValue : validValues) {
            if (value.equals(validValue)) {
                return;
            }
        }
        
        throw new ValidationException(String.format("参数 [%s] 值必须是指定值中的一个", paramName));
    }
}
