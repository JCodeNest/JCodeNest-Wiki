package cn.jcodenest.wiki.common.utils;

import cn.jcodenest.wiki.common.constant.CommonConstants;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;

import java.time.Instant;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.time.temporal.ChronoUnit;
import java.util.Date;

/**
 * 日期时间工具类
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
public final class DateUtils {

    /**
     * 默认时区
     */
    private static final ZoneId DEFAULT_ZONE_ID = ZoneId.of(CommonConstants.System.DEFAULT_TIMEZONE);

    /**
     * 常用日期时间格式化器
     */
    private static final DateTimeFormatter DEFAULT_DATETIME_FORMATTER = DateTimeFormatter.ofPattern(CommonConstants.Time.DEFAULT_DATETIME_FORMAT);
    private static final DateTimeFormatter DEFAULT_DATE_FORMATTER = DateTimeFormatter.ofPattern(CommonConstants.Time.DEFAULT_DATE_FORMAT);
    private static final DateTimeFormatter DEFAULT_TIME_FORMATTER = DateTimeFormatter.ofPattern(CommonConstants.Time.DEFAULT_TIME_FORMAT);
    private static final DateTimeFormatter COMPACT_DATETIME_FORMATTER = DateTimeFormatter.ofPattern(CommonConstants.Time.COMPACT_DATETIME_FORMAT);
    private static final DateTimeFormatter COMPACT_DATE_FORMATTER = DateTimeFormatter.ofPattern(CommonConstants.Time.COMPACT_DATE_FORMAT);

    /**
     * 获取当前时间
     *
     * @return 当前LocalDateTime
     */
    public static LocalDateTime now() {
        return LocalDateTime.now(DEFAULT_ZONE_ID);
    }

    /**
     * 获取当前日期
     *
     * @return 当前LocalDate
     */
    public static LocalDate today() {
        return LocalDate.now(DEFAULT_ZONE_ID);
    }

    /**
     * 获取当前时间戳（毫秒）
     *
     * @return 当前时间戳
     */
    public static long currentTimeMillis() {
        return System.currentTimeMillis();
    }

    /**
     * 获取当前时间戳（秒）
     *
     * @return 当前时间戳（秒）
     */
    public static long currentTimeSeconds() {
        return Instant.now().getEpochSecond();
    }

    /**
     * LocalDateTime转换为Date
     *
     * @param localDateTime LocalDateTime对象
     * @return Date对象，转换失败返回null
     */
    public static Date toDate(LocalDateTime localDateTime) {
        if (localDateTime == null) {
            return null;
        }

        try {
            return Date.from(localDateTime.atZone(DEFAULT_ZONE_ID).toInstant());
        } catch (Exception e) {
            log.error("LocalDateTime转Date失败: {}", localDateTime, e);
            return null;
        }
    }

    /**
     * Date转换为LocalDateTime
     *
     * @param date Date对象
     * @return LocalDateTime对象，转换失败返回null
     */
    public static LocalDateTime toLocalDateTime(Date date) {
        if (date == null) {
            return null;
        }

        try {
            return date.toInstant().atZone(DEFAULT_ZONE_ID).toLocalDateTime();
        } catch (Exception e) {
            log.error("Date转LocalDateTime失败: {}", date, e);
            return null;
        }
    }

    /**
     * 时间戳转换为LocalDateTime
     *
     * @param timestamp 时间戳（毫秒）
     * @return LocalDateTime对象
     */
    public static LocalDateTime toLocalDateTime(long timestamp) {
        try {
            return LocalDateTime.ofInstant(Instant.ofEpochMilli(timestamp), DEFAULT_ZONE_ID);
        } catch (Exception e) {
            log.error("时间戳转LocalDateTime失败: {}", timestamp, e);
            return null;
        }
    }

    /**
     * LocalDateTime转换为时间戳
     *
     * @param localDateTime LocalDateTime对象
     * @return 时间戳（毫秒），转换失败返回0
     */
    public static long toTimestamp(LocalDateTime localDateTime) {
        if (localDateTime == null) {
            return 0L;
        }

        try {
            return localDateTime.atZone(DEFAULT_ZONE_ID).toInstant().toEpochMilli();
        } catch (Exception e) {
            log.error("LocalDateTime转时间戳失败: {}", localDateTime, e);
            return 0L;
        }
    }

    /**
     * 格式化日期时间（默认格式）
     *
     * @param localDateTime LocalDateTime对象
     * @return 格式化后的字符串，格式化失败返回null
     */
    public static String format(LocalDateTime localDateTime) {
        return format(localDateTime, DEFAULT_DATETIME_FORMATTER);
    }

    /**
     * 格式化日期（默认格式）
     *
     * @param localDate LocalDate对象
     * @return 格式化后的字符串，格式化失败返回null
     */
    public static String format(LocalDate localDate) {
        return format(localDate, DEFAULT_DATE_FORMATTER);
    }

    /**
     * 格式化时间（默认格式）
     *
     * @param localTime LocalTime对象
     * @return 格式化后的字符串，格式化失败返回null
     */
    public static String format(LocalTime localTime) {
        return format(localTime, DEFAULT_TIME_FORMATTER);
    }

    /**
     * 格式化日期时间（自定义格式）
     *
     * @param localDateTime LocalDateTime对象
     * @param pattern       格式模式
     * @return 格式化后的字符串，格式化失败返回null
     */
    public static String format(LocalDateTime localDateTime, String pattern) {
        if (localDateTime == null || StringUtils.isBlank(pattern)) {
            return null;
        }

        try {
            return localDateTime.format(DateTimeFormatter.ofPattern(pattern));
        } catch (Exception e) {
            log.error("格式化LocalDateTime失败: localDateTime={}, pattern={}", localDateTime, pattern, e);
            return null;
        }
    }

    /**
     * 格式化日期时间（使用格式化器）
     *
     * @param localDateTime LocalDateTime对象
     * @param formatter     格式化器
     * @return 格式化后的字符串，格式化失败返回null
     */
    public static String format(LocalDateTime localDateTime, DateTimeFormatter formatter) {
        if (localDateTime == null || formatter == null) {
            return null;
        }

        try {
            return localDateTime.format(formatter);
        } catch (Exception e) {
            log.error("格式化LocalDateTime失败: localDateTime={}, formatter={}", localDateTime, formatter, e);
            return null;
        }
    }

    /**
     * 格式化日期（使用格式化器）
     *
     * @param localDate LocalDate对象
     * @param formatter 格式化器
     * @return 格式化后的字符串，格式化失败返回null
     */
    public static String format(LocalDate localDate, DateTimeFormatter formatter) {
        if (localDate == null || formatter == null) {
            return null;
        }

        try {
            return localDate.format(formatter);
        } catch (Exception e) {
            log.error("格式化LocalDate失败: localDate={}, formatter={}", localDate, formatter, e);
            return null;
        }
    }

    /**
     * 格式化时间（使用格式化器）
     *
     * @param localTime LocalTime对象
     * @param formatter 格式化器
     * @return 格式化后的字符串，格式化失败返回null
     */
    public static String format(LocalTime localTime, DateTimeFormatter formatter) {
        if (localTime == null || formatter == null) {
            return null;
        }

        try {
            return localTime.format(formatter);
        } catch (Exception e) {
            log.error("格式化LocalTime失败: localTime={}, formatter={}", localTime, formatter, e);
            return null;
        }
    }

    /**
     * 解析日期时间字符串（默认格式）
     *
     * @param dateTimeStr 日期时间字符串
     * @return LocalDateTime对象，解析失败返回null
     */
    public static LocalDateTime parseDateTime(String dateTimeStr) {
        return parseDateTime(dateTimeStr, DEFAULT_DATETIME_FORMATTER);
    }

    /**
     * 解析日期字符串（默认格式）
     *
     * @param dateStr 日期字符串
     * @return LocalDate对象，解析失败返回null
     */
    public static LocalDate parseDate(String dateStr) {
        return parseDate(dateStr, DEFAULT_DATE_FORMATTER);
    }

    /**
     * 解析时间字符串（默认格式）
     *
     * @param timeStr 时间字符串
     * @return LocalTime对象，解析失败返回null
     */
    public static LocalTime parseTime(String timeStr) {
        return parseTime(timeStr, DEFAULT_TIME_FORMATTER);
    }

    /**
     * 解析日期时间字符串（自定义格式）
     *
     * @param dateTimeStr 日期时间字符串
     * @param pattern     格式模式
     * @return LocalDateTime对象，解析失败返回null
     */
    public static LocalDateTime parseDateTime(String dateTimeStr, String pattern) {
        if (StringUtils.isBlank(dateTimeStr) || StringUtils.isBlank(pattern)) {
            return null;
        }

        try {
            return LocalDateTime.parse(dateTimeStr, DateTimeFormatter.ofPattern(pattern));
        } catch (DateTimeParseException e) {
            log.error("解析日期时间字符串失败: dateTimeStr={}, pattern={}", dateTimeStr, pattern, e);
            return null;
        }
    }

    /**
     * 解析日期时间字符串（使用格式化器）
     *
     * @param dateTimeStr 日期时间字符串
     * @param formatter   格式化器
     * @return LocalDateTime对象，解析失败返回null
     */
    public static LocalDateTime parseDateTime(String dateTimeStr, DateTimeFormatter formatter) {
        if (StringUtils.isBlank(dateTimeStr) || formatter == null) {
            return null;
        }

        try {
            return LocalDateTime.parse(dateTimeStr, formatter);
        } catch (DateTimeParseException e) {
            log.error("解析日期时间字符串失败: dateTimeStr={}, formatter={}", dateTimeStr, formatter, e);
            return null;
        }
    }

    /**
     * 解析日期字符串（使用格式化器）
     *
     * @param dateStr   日期字符串
     * @param formatter 格式化器
     * @return LocalDate对象，解析失败返回null
     */
    public static LocalDate parseDate(String dateStr, DateTimeFormatter formatter) {
        if (StringUtils.isBlank(dateStr) || formatter == null) {
            return null;
        }

        try {
            return LocalDate.parse(dateStr, formatter);
        } catch (DateTimeParseException e) {
            log.error("解析日期字符串失败: dateStr={}, formatter={}", dateStr, formatter, e);
            return null;
        }
    }

    /**
     * 解析时间字符串（使用格式化器）
     *
     * @param timeStr   时间字符串
     * @param formatter 格式化器
     * @return LocalTime对象，解析失败返回null
     */
    public static LocalTime parseTime(String timeStr, DateTimeFormatter formatter) {
        if (StringUtils.isBlank(timeStr) || formatter == null) {
            return null;
        }

        try {
            return LocalTime.parse(timeStr, formatter);
        } catch (DateTimeParseException e) {
            log.error("解析时间字符串失败: timeStr={}, formatter={}", timeStr, formatter, e);
            return null;
        }
    }

    /**
     * 计算两个日期时间之间的天数差
     *
     * @param start 开始时间
     * @param end   结束时间
     * @return 天数差，计算失败返回0
     */
    public static long daysBetween(LocalDateTime start, LocalDateTime end) {
        if (start == null || end == null) {
            return 0L;
        }

        try {
            return ChronoUnit.DAYS.between(start.toLocalDate(), end.toLocalDate());
        } catch (Exception e) {
            log.error("计算天数差失败: start={}, end={}", start, end, e);
            return 0L;
        }
    }

    /**
     * 判断是否为今天
     *
     * @param date 日期
     * @return true-今天，false-不是今天
     */
    public static boolean isToday(LocalDateTime date) {
        if (date == null) {
            return false;
        }
        return date.toLocalDate().equals(today());
    }

    /**
     * 获取友好的时间描述
     *
     * @param dateTime 日期时间
     * @return 友好的时间描述
     */
    public static String getFriendlyTime(LocalDateTime dateTime) {
        if (dateTime == null) {
            return "未知时间";
        }

        LocalDateTime now = now();
        long seconds = ChronoUnit.SECONDS.between(dateTime, now);

        if (seconds < 0) {
            return "未来时间";
        }

        if (seconds < 60) {
            return "刚刚";
        }

        long minutes = seconds / 60;
        if (minutes < 60) {
            return minutes + "分钟前";
        }

        long hours = minutes / 60;
        if (hours < 24) {
            return hours + "小时前";
        }

        long days = hours / 24;
        if (days < 30) {
            return days + "天前";
        }

        return format(dateTime.toLocalDate());
    }
}
