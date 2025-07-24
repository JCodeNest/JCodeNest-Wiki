package cn.jcodenest.wiki.common.constant;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;

/**
 * Redis相关常量类
 *
 * @author JCodeNest
 * @version 1.0.0
 * @since 2025/7/24
 * <p>
 * Copyright (c) 2025 JCodeNest-Wiki
 * All rights reserved.
 */
@NoArgsConstructor(access = AccessLevel.PRIVATE)
public final class RedisConstants {

    /**
     * 键前缀
     */
    @NoArgsConstructor(access = AccessLevel.PRIVATE)
    public static final class KeyPrefix {
        /** 系统前缀 */
        public static final String SYSTEM = "jcodenest:wiki:";
        
        /** 用户相关前缀 */
        public static final String USER = SYSTEM + "user:";
        
        /** 内容相关前缀 */
        public static final String CONTENT = SYSTEM + "content:";
        
        /** 订单相关前缀 */
        public static final String ORDER = SYSTEM + "order:";
        
        /** 学习相关前缀 */
        public static final String LEARNING = SYSTEM + "learning:";
        
        /** 社交相关前缀 */
        public static final String SOCIAL = SYSTEM + "social:";
        
        /** 文件相关前缀 */
        public static final String FILE = SYSTEM + "file:";
        
        /** 缓存前缀 */
        public static final String CACHE = SYSTEM + "cache:";
        
        /** 锁前缀 */
        public static final String LOCK = SYSTEM + "lock:";
        
        /** 限流前缀 */
        public static final String RATE_LIMIT = SYSTEM + "rate_limit:";
        
        /** 会话前缀 */
        public static final String SESSION = SYSTEM + "session:";
        
        /** 验证码前缀 */
        public static final String CAPTCHA = SYSTEM + "captcha:";
    }

    /**
     * 用户相关键
     */
    @NoArgsConstructor(access = AccessLevel.PRIVATE)
    public static final class UserKey {
        /** 用户信息缓存 */
        public static final String USER_INFO = KeyPrefix.USER + "info:";
        
        /** 用户权限缓存 */
        public static final String USER_PERMISSIONS = KeyPrefix.USER + "permissions:";
        
        /** 用户角色缓存 */
        public static final String USER_ROLES = KeyPrefix.USER + "roles:";
        
        /** 用户登录Token */
        public static final String USER_TOKEN = KeyPrefix.USER + "token:";
        
        /** 用户登录失败次数 */
        public static final String LOGIN_FAIL_COUNT = KeyPrefix.USER + "login_fail:";
        
        /** 用户在线状态 */
        public static final String USER_ONLINE = KeyPrefix.USER + "online:";
        
        /** 用户画像缓存 */
        public static final String USER_PROFILE = KeyPrefix.USER + "profile:";
    }

    /**
     * 内容相关键
     */
    @NoArgsConstructor(access = AccessLevel.PRIVATE)
    public static final class ContentKey {
        /** 内容信息缓存 */
        public static final String CONTENT_INFO = KeyPrefix.CONTENT + "info:";
        
        /** 内容浏览量缓存 */
        public static final String CONTENT_VIEW_COUNT = KeyPrefix.CONTENT + "view_count:";
        
        /** 内容点赞量缓存 */
        public static final String CONTENT_LIKE_COUNT = KeyPrefix.CONTENT + "like_count:";
        
        /** 内容评论量缓存 */
        public static final String CONTENT_COMMENT_COUNT = KeyPrefix.CONTENT + "comment_count:";
        
        /** 热门内容缓存 */
        public static final String HOT_CONTENT = KeyPrefix.CONTENT + "hot";
        
        /** 推荐内容缓存 */
        public static final String RECOMMEND_CONTENT = KeyPrefix.CONTENT + "recommend:";
        
        /** 分类内容缓存 */
        public static final String CATEGORY_CONTENT = KeyPrefix.CONTENT + "category:";
        
        /** 标签内容缓存 */
        public static final String TAG_CONTENT = KeyPrefix.CONTENT + "tag:";
    }

    /**
     * 订单相关键
     */
    @NoArgsConstructor(access = AccessLevel.PRIVATE)
    public static final class OrderKey {
        /** 订单信息缓存 */
        public static final String ORDER_INFO = KeyPrefix.ORDER + "info:";
        
        /** 订单支付状态 */
        public static final String ORDER_PAYMENT_STATUS = KeyPrefix.ORDER + "payment_status:";
        
        /** 订单超时处理 */
        public static final String ORDER_TIMEOUT = KeyPrefix.ORDER + "timeout:";
        
        /** 用户购买记录 */
        public static final String USER_PURCHASE = KeyPrefix.ORDER + "user_purchase:";
    }

    /**
     * 学习相关键
     */
    @NoArgsConstructor(access = AccessLevel.PRIVATE)
    public static final class LearningKey {
        /** 学习进度缓存 */
        public static final String LEARNING_PROGRESS = KeyPrefix.LEARNING + "progress:";
        
        /** 课程信息缓存 */
        public static final String COURSE_INFO = KeyPrefix.LEARNING + "course:";
        
        /** 考试记录缓存 */
        public static final String EXAM_RECORD = KeyPrefix.LEARNING + "exam_record:";
        
        /** 学习统计缓存 */
        public static final String LEARNING_STATS = KeyPrefix.LEARNING + "stats:";
    }

    /**
     * 社交相关键
     */
    @NoArgsConstructor(access = AccessLevel.PRIVATE)
    public static final class SocialKey {
        /** 用户关注列表 */
        public static final String USER_FOLLOWING = KeyPrefix.SOCIAL + "following:";
        
        /** 用户粉丝列表 */
        public static final String USER_FOLLOWERS = KeyPrefix.SOCIAL + "followers:";
        
        /** 用户收藏列表 */
        public static final String USER_FAVORITES = KeyPrefix.SOCIAL + "favorites:";
        
        /** 用户点赞记录 */
        public static final String USER_LIKES = KeyPrefix.SOCIAL + "likes:";
        
        /** 用户积分缓存 */
        public static final String USER_SCORE = KeyPrefix.SOCIAL + "score:";
        
        /** 消息队列 */
        public static final String MESSAGE_QUEUE = KeyPrefix.SOCIAL + "message_queue:";
    }

    /**
     * 文件相关键
     */
    @NoArgsConstructor(access = AccessLevel.PRIVATE)
    public static final class FileKey {
        /** 文件信息缓存 */
        public static final String FILE_INFO = KeyPrefix.FILE + "info:";
        
        /** 文件上传进度 */
        public static final String UPLOAD_PROGRESS = KeyPrefix.FILE + "upload_progress:";
        
        /** 文件下载次数 */
        public static final String DOWNLOAD_COUNT = KeyPrefix.FILE + "download_count:";
    }

    /**
     * 缓存相关键
     */
    @NoArgsConstructor(access = AccessLevel.PRIVATE)
    public static final class CacheKey {
        /** 系统配置缓存 */
        public static final String SYSTEM_CONFIG = KeyPrefix.CACHE + "system_config";
        
        /** 数据字典缓存 */
        public static final String DICT_DATA = KeyPrefix.CACHE + "dict_data:";
        
        /** 分类树缓存 */
        public static final String CATEGORY_TREE = KeyPrefix.CACHE + "category_tree";
        
        /** 标签列表缓存 */
        public static final String TAG_LIST = KeyPrefix.CACHE + "tag_list";
        
        /** 统计数据缓存 */
        public static final String STATISTICS = KeyPrefix.CACHE + "statistics:";
    }

    /**
     * 锁相关键
     */
    @NoArgsConstructor(access = AccessLevel.PRIVATE)
    public static final class LockKey {
        /** 用户操作锁 */
        public static final String USER_OPERATION = KeyPrefix.LOCK + "user_operation:";
        
        /** 订单处理锁 */
        public static final String ORDER_PROCESS = KeyPrefix.LOCK + "order_process:";
        
        /** 内容发布锁 */
        public static final String CONTENT_PUBLISH = KeyPrefix.LOCK + "content_publish:";
        
        /** 支付处理锁 */
        public static final String PAYMENT_PROCESS = KeyPrefix.LOCK + "payment_process:";
    }

    /**
     * 限流相关键
     */
    @NoArgsConstructor(access = AccessLevel.PRIVATE)
    public static final class RateLimitKey {
        /** 用户操作限流 */
        public static final String USER_OPERATION = KeyPrefix.RATE_LIMIT + "user_operation:";
        
        /** IP访问限流 */
        public static final String IP_ACCESS = KeyPrefix.RATE_LIMIT + "ip_access:";
        
        /** 接口调用限流 */
        public static final String API_CALL = KeyPrefix.RATE_LIMIT + "api_call:";
        
        /** 短信发送限流 */
        public static final String SMS_SEND = KeyPrefix.RATE_LIMIT + "sms_send:";
        
        /** 邮件发送限流 */
        public static final String EMAIL_SEND = KeyPrefix.RATE_LIMIT + "email_send:";
    }

    /**
     * 过期时间常量（秒）
     */
    @NoArgsConstructor(access = AccessLevel.PRIVATE)
    public static final class ExpireTime {
        /** 1分钟 */
        public static final long ONE_MINUTE = 60L;
        
        /** 5分钟 */
        public static final long FIVE_MINUTES = 5 * ONE_MINUTE;
        
        /** 10分钟 */
        public static final long TEN_MINUTES = 10 * ONE_MINUTE;
        
        /** 30分钟 */
        public static final long THIRTY_MINUTES = 30 * ONE_MINUTE;
        
        /** 1小时 */
        public static final long ONE_HOUR = 60 * ONE_MINUTE;
        
        /** 2小时 */
        public static final long TWO_HOURS = 2 * ONE_HOUR;
        
        /** 6小时 */
        public static final long SIX_HOURS = 6 * ONE_HOUR;
        
        /** 12小时 */
        public static final long TWELVE_HOURS = 12 * ONE_HOUR;
        
        /** 1天 */
        public static final long ONE_DAY = 24 * ONE_HOUR;
        
        /** 3天 */
        public static final long THREE_DAYS = 3 * ONE_DAY;
        
        /** 7天 */
        public static final long SEVEN_DAYS = 7 * ONE_DAY;
        
        /** 30天 */
        public static final long THIRTY_DAYS = 30 * ONE_DAY;
    }
}
