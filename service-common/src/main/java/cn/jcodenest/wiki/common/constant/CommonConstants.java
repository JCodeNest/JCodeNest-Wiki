package cn.jcodenest.wiki.common.constant;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;

/**
 * 通用常量类
 *
 * @author JCodeNest
 * @version 1.0.0
 * @since 2025/7/24
 * <p>
 * Copyright (c) 2025 JCodeNest-Wiki
 * All rights reserved.
 */
@NoArgsConstructor(access = AccessLevel.PRIVATE)
public final class CommonConstants {

    /**
     * 系统相关常量
     */
    @NoArgsConstructor(access = AccessLevel.PRIVATE)
    public static final class System {
        /** 系统名称 */
        public static final java.lang.String SYSTEM_NAME = "JCodeNest-Wiki";
        
        /** 系统版本 */
        public static final java.lang.String SYSTEM_VERSION = "1.0.0";
        
        /** 系统编码 */
        public static final java.lang.String CHARSET_UTF8 = "UTF-8";
        
        /** 默认时区 */
        public static final java.lang.String DEFAULT_TIMEZONE = "Asia/Shanghai";
        
        /** 默认语言 */
        public static final java.lang.String DEFAULT_LOCALE = "zh_CN";
        
        /** 系统管理员用户名 */
        public static final java.lang.String ADMIN_USERNAME = "admin";
        
        /** 系统用户ID */
        public static final Long SYSTEM_USER_ID = 0L;
        
        /** 默认密码 */
        public static final java.lang.String DEFAULT_PASSWORD = "123456";
    }

    /**
     * 数字相关常量
     */
    @NoArgsConstructor(access = AccessLevel.PRIVATE)
    public static final class Number {
        /** 零 */
        public static final int ZERO = 0;
        
        /** 一 */
        public static final int ONE = 1;
        
        /** 二 */
        public static final int TWO = 2;
        
        /** 三 */
        public static final int THREE = 3;
        
        /** 十 */
        public static final int TEN = 10;
        
        /** 一百 */
        public static final int HUNDRED = 100;
        
        /** 一千 */
        public static final int THOUSAND = 1000;
        
        /** 一万 */
        public static final int TEN_THOUSAND = 10000;
        
        /** 负一 */
        public static final int NEGATIVE_ONE = -1;
    }

    /**
     * 字符串相关常量
     */
    @NoArgsConstructor(access = AccessLevel.PRIVATE)
    public static final class String {
        /** 空字符串 */
        public static final java.lang.String EMPTY = "";
        
        /** 空格 */
        public static final java.lang.String SPACE = " ";
        
        /** 逗号 */
        public static final java.lang.String COMMA = ",";
        
        /** 分号 */
        public static final java.lang.String SEMICOLON = ";";
        
        /** 冒号 */
        public static final java.lang.String COLON = ":";
        
        /** 点 */
        public static final java.lang.String DOT = ".";
        
        /** 下划线 */
        public static final java.lang.String UNDERSCORE = "_";
        
        /** 横线 */
        public static final java.lang.String HYPHEN = "-";
        
        /** 斜杠 */
        public static final java.lang.String SLASH = "/";
        
        /** 反斜杠 */
        public static final java.lang.String BACKSLASH = "\\";
        
        /** 换行符 */
        public static final java.lang.String LINE_SEPARATOR = java.lang.System.lineSeparator();
        
        /** 是 */
        public static final java.lang.String YES = "Y";
        
        /** 否 */
        public static final java.lang.String NO = "N";
        
        /** 成功 */
        public static final java.lang.String SUCCESS = "success";
        
        /** 失败 */
        public static final java.lang.String FAIL = "fail";
        
        /** 未知 */
        public static final java.lang.String UNKNOWN = "unknown";
    }

    /**
     * 状态相关常量
     */
    @NoArgsConstructor(access = AccessLevel.PRIVATE)
    public static final class Status {
        /** 启用 */
        public static final Integer ENABLED = 1;
        
        /** 禁用 */
        public static final Integer DISABLED = 0;
        
        /** 正常 */
        public static final Integer NORMAL = 1;
        
        /** 异常 */
        public static final Integer ABNORMAL = 0;
        
        /** 成功 */
        public static final Integer SUCCESS = 1;
        
        /** 失败 */
        public static final Integer FAILURE = 0;
        
        /** 是 */
        public static final Integer YES = 1;
        
        /** 否 */
        public static final Integer NO = 0;
        
        /** 删除 */
        public static final Integer DELETED = 1;
        
        /** 未删除 */
        public static final Integer NOT_DELETED = 0;
    }

    /**
     * 分页相关常量
     */
    @NoArgsConstructor(access = AccessLevel.PRIVATE)
    public static final class Page {
        /** 默认页码 */
        public static final Long DEFAULT_CURRENT = 1L;
        
        /** 默认每页大小 */
        public static final Long DEFAULT_SIZE = 10L;
        
        /** 最大每页大小 */
        public static final Long MAX_SIZE = 1000L;
        
        /** 页码参数名 */
        public static final java.lang.String CURRENT_PARAM = "current";
        
        /** 每页大小参数名 */
        public static final java.lang.String SIZE_PARAM = "size";
    }

    /**
     * 时间相关常量
     */
    @NoArgsConstructor(access = AccessLevel.PRIVATE)
    public static final class Time {
        /** 一秒的毫秒数 */
        public static final long SECOND_MILLIS = 1000L;
        
        /** 一分钟的毫秒数 */
        public static final long MINUTE_MILLIS = 60 * SECOND_MILLIS;
        
        /** 一小时的毫秒数 */
        public static final long HOUR_MILLIS = 60 * MINUTE_MILLIS;
        
        /** 一天的毫秒数 */
        public static final long DAY_MILLIS = 24 * HOUR_MILLIS;
        
        /** 一周的毫秒数 */
        public static final long WEEK_MILLIS = 7 * DAY_MILLIS;
        
        /** 默认日期时间格式 */
        public static final java.lang.String DEFAULT_DATETIME_FORMAT = "yyyy-MM-dd HH:mm:ss";
        
        /** 默认日期格式 */
        public static final java.lang.String DEFAULT_DATE_FORMAT = "yyyy-MM-dd";
        
        /** 默认时间格式 */
        public static final java.lang.String DEFAULT_TIME_FORMAT = "HH:mm:ss";
        
        /** 紧凑日期时间格式 */
        public static final java.lang.String COMPACT_DATETIME_FORMAT = "yyyyMMddHHmmss";
        
        /** 紧凑日期格式 */
        public static final java.lang.String COMPACT_DATE_FORMAT = "yyyyMMdd";
    }

    /**
     * 文件相关常量
     */
    @NoArgsConstructor(access = AccessLevel.PRIVATE)
    public static final class File {
        /** 默认文件大小限制（10MB） */
        public static final long DEFAULT_MAX_SIZE = 10 * 1024 * 1024L;
        
        /** 图片文件扩展名 */
        public static final java.lang.String[] IMAGE_EXTENSIONS = {
            "jpg", "jpeg", "png", "gif", "bmp", "webp", "svg"
        };
        
        /** 视频文件扩展名 */
        public static final java.lang.String[] VIDEO_EXTENSIONS = {
            "mp4", "avi", "mov", "wmv", "flv", "mkv", "webm"
        };
        
        /** 音频文件扩展名 */
        public static final java.lang.String[] AUDIO_EXTENSIONS = {
            "mp3", "wav", "flac", "aac", "ogg", "wma"
        };
        
        /** 文档文件扩展名 */
        public static final java.lang.String[] DOCUMENT_EXTENSIONS = {
            "pdf", "doc", "docx", "xls", "xlsx", "ppt", "pptx", "txt", "md"
        };
    }

    /**
     * 正则表达式常量
     */
    @NoArgsConstructor(access = AccessLevel.PRIVATE)
    public static final class Regex {
        /** 邮箱正则 */
        public static final java.lang.String EMAIL = "^[a-zA-Z0-9._%+-]+@[a-zA-Z0-9.-]+\\.[a-zA-Z]{2,}$";
        
        /** 手机号正则 */
        public static final java.lang.String MOBILE = "^1[3-9]\\d{9}$";
        
        /** 身份证号正则 */
        public static final java.lang.String ID_CARD = "^[1-9]\\d{5}(18|19|20)\\d{2}((0[1-9])|(1[0-2]))(([0-2][1-9])|10|20|30|31)\\d{3}[0-9Xx]$";
        
        /** 用户名正则（字母、数字、下划线，4-20位） */
        public static final java.lang.String USERNAME = "^[a-zA-Z0-9_]{4,20}$";
        
        /** 密码正则（至少包含字母和数字，6-20位） */
        public static final java.lang.String PASSWORD = "^(?=.*[a-zA-Z])(?=.*\\d)[a-zA-Z\\d@$!%*?&]{6,20}$";
        
        /** IP地址正则 */
        public static final java.lang.String IP = "^((25[0-5]|2[0-4]\\d|[01]?\\d\\d?)\\.){3}(25[0-5]|2[0-4]\\d|[01]?\\d\\d?)$";
        
        /** URL正则 */
        public static final java.lang.String URL = "^(https?|ftp)://[^\\s/$.?#].[^\\s]*$";
    }
}
