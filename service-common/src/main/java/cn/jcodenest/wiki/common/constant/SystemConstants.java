package cn.jcodenest.wiki.common.constant;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;

/**
 * 系统常量类
 *
 * @author JCodeNest
 * @version 1.0.0
 * @since 2025/7/24
 * <p>
 * Copyright (c) 2025 JCodeNest-Wiki
 * All rights reserved.
 */
@NoArgsConstructor(access = AccessLevel.PRIVATE)
public final class SystemConstants {

    /**
     * 环境相关常量
     */
    @NoArgsConstructor(access = AccessLevel.PRIVATE)
    public static final class Environment {
        /** 开发环境 */
        public static final String DEV = "dev";
        
        /** 测试环境 */
        public static final String TEST = "test";
        
        /** 预生产环境 */
        public static final String STAGING = "staging";
        
        /** 生产环境 */
        public static final String PROD = "prod";
    }

    /**
     * 配置相关常量
     */
    @NoArgsConstructor(access = AccessLevel.PRIVATE)
    public static final class Config {
        /** 系统配置前缀 */
        public static final String SYSTEM_CONFIG_PREFIX = "system.";
        
        /** 用户配置前缀 */
        public static final String USER_CONFIG_PREFIX = "user.";
        
        /** 内容配置前缀 */
        public static final String CONTENT_CONFIG_PREFIX = "content.";
        
        /** 支付配置前缀 */
        public static final String PAYMENT_CONFIG_PREFIX = "payment.";
        
        /** 文件配置前缀 */
        public static final String FILE_CONFIG_PREFIX = "file.";
        
        /** 邮件配置前缀 */
        public static final String EMAIL_CONFIG_PREFIX = "email.";
        
        /** 短信配置前缀 */
        public static final String SMS_CONFIG_PREFIX = "sms.";
    }

    /**
     * 日志相关常量
     */
    @NoArgsConstructor(access = AccessLevel.PRIVATE)
    public static final class Log {
        /** 操作日志类型 */
        public static final String OPERATION_LOG = "OPERATION";
        
        /** 登录日志类型 */
        public static final String LOGIN_LOG = "LOGIN";
        
        /** 错误日志类型 */
        public static final String ERROR_LOG = "ERROR";
        
        /** 系统日志类型 */
        public static final String SYSTEM_LOG = "SYSTEM";
        
        /** 业务日志类型 */
        public static final String BUSINESS_LOG = "BUSINESS";
        
        /** 日志级别 - 调试 */
        public static final String DEBUG = "DEBUG";
        
        /** 日志级别 - 信息 */
        public static final String INFO = "INFO";
        
        /** 日志级别 - 警告 */
        public static final String WARN = "WARN";
        
        /** 日志级别 - 错误 */
        public static final String ERROR = "ERROR";
    }

    /**
     * 消息队列相关常量
     */
    @NoArgsConstructor(access = AccessLevel.PRIVATE)
    public static final class MQ {
        /** 用户相关主题 */
        public static final String USER_TOPIC = "user-topic";
        
        /** 内容相关主题 */
        public static final String CONTENT_TOPIC = "content-topic";
        
        /** 订单相关主题 */
        public static final String ORDER_TOPIC = "order-topic";
        
        /** 支付相关主题 */
        public static final String PAYMENT_TOPIC = "payment-topic";
        
        /** 通知相关主题 */
        public static final String NOTIFICATION_TOPIC = "notification-topic";
        
        /** 邮件相关主题 */
        public static final String EMAIL_TOPIC = "email-topic";
        
        /** 短信相关主题 */
        public static final String SMS_TOPIC = "sms-topic";
        
        /** 用户注册标签 */
        public static final String USER_REGISTER_TAG = "user-register";
        
        /** 用户登录标签 */
        public static final String USER_LOGIN_TAG = "user-login";
        
        /** 内容发布标签 */
        public static final String CONTENT_PUBLISH_TAG = "content-publish";
        
        /** 订单创建标签 */
        public static final String ORDER_CREATE_TAG = "order-create";
        
        /** 订单支付标签 */
        public static final String ORDER_PAID_TAG = "order-paid";
        
        /** 支付成功标签 */
        public static final String PAYMENT_SUCCESS_TAG = "payment-success";
        
        /** 支付失败标签 */
        public static final String PAYMENT_FAILED_TAG = "payment-failed";
    }

    /**
     * 任务调度相关常量
     */
    @NoArgsConstructor(access = AccessLevel.PRIVATE)
    public static final class Task {
        /** 数据统计任务组 */
        public static final String STATISTICS_GROUP = "statistics-group";
        
        /** 数据清理任务组 */
        public static final String CLEANUP_GROUP = "cleanup-group";
        
        /** 备份任务组 */
        public static final String BACKUP_GROUP = "backup-group";
        
        /** 监控任务组 */
        public static final String MONITOR_GROUP = "monitor-group";
        
        /** 每日统计任务 */
        public static final String DAILY_STATISTICS = "daily-statistics";
        
        /** 每周统计任务 */
        public static final String WEEKLY_STATISTICS = "weekly-statistics";
        
        /** 每月统计任务 */
        public static final String MONTHLY_STATISTICS = "monthly-statistics";
        
        /** 日志清理任务 */
        public static final String LOG_CLEANUP = "log-cleanup";
        
        /** 临时文件清理任务 */
        public static final String TEMP_FILE_CLEANUP = "temp-file-cleanup";
        
        /** 数据库备份任务 */
        public static final String DATABASE_BACKUP = "database-backup";
        
        /** 文件备份任务 */
        public static final String FILE_BACKUP = "file-backup";
    }

    /**
     * 监控相关常量
     */
    @NoArgsConstructor(access = AccessLevel.PRIVATE)
    public static final class Monitor {
        /** 系统健康检查 */
        public static final String HEALTH_CHECK = "health-check";
        
        /** 数据库连接监控 */
        public static final String DATABASE_MONITOR = "database-monitor";
        
        /** Redis连接监控 */
        public static final String REDIS_MONITOR = "redis-monitor";
        
        /** 消息队列监控 */
        public static final String MQ_MONITOR = "mq-monitor";
        
        /** 磁盘空间监控 */
        public static final String DISK_MONITOR = "disk-monitor";
        
        /** 内存使用监控 */
        public static final String MEMORY_MONITOR = "memory-monitor";
        
        /** CPU使用监控 */
        public static final String CPU_MONITOR = "cpu-monitor";
        
        /** 网络监控 */
        public static final String NETWORK_MONITOR = "network-monitor";
    }

    /**
     * 通知相关常量
     */
    @NoArgsConstructor(access = AccessLevel.PRIVATE)
    public static final class Notification {
        /** 系统通知 */
        public static final String SYSTEM = "SYSTEM";
        
        /** 用户通知 */
        public static final String USER = "USER";
        
        /** 订单通知 */
        public static final String ORDER = "ORDER";
        
        /** 支付通知 */
        public static final String PAYMENT = "PAYMENT";
        
        /** 学习通知 */
        public static final String LEARNING = "LEARNING";
        
        /** 社交通知 */
        public static final String SOCIAL = "SOCIAL";
        
        /** 邮件通知 */
        public static final String EMAIL = "EMAIL";
        
        /** 短信通知 */
        public static final String SMS = "SMS";
        
        /** 推送通知 */
        public static final String PUSH = "PUSH";
        
        /** 站内信通知 */
        public static final String MESSAGE = "MESSAGE";
    }

    /**
     * 缓存相关常量
     */
    @NoArgsConstructor(access = AccessLevel.PRIVATE)
    public static final class Cache {
        /** 本地缓存 */
        public static final String LOCAL = "local";
        
        /** Redis缓存 */
        public static final String REDIS = "redis";
        
        /** 分布式缓存 */
        public static final String DISTRIBUTED = "distributed";
        
        /** 缓存管理器名称 */
        public static final String CACHE_MANAGER = "cacheManager";
        
        /** 默认缓存名称 */
        public static final String DEFAULT_CACHE = "default";
        
        /** 用户缓存名称 */
        public static final String USER_CACHE = "user";
        
        /** 内容缓存名称 */
        public static final String CONTENT_CACHE = "content";
        
        /** 系统配置缓存名称 */
        public static final String CONFIG_CACHE = "config";
    }

    /**
     * 线程池相关常量
     */
    @NoArgsConstructor(access = AccessLevel.PRIVATE)
    public static final class ThreadPool {
        /** 核心线程池名称 */
        public static final String CORE_EXECUTOR = "coreExecutor";
        
        /** 异步任务线程池名称 */
        public static final String ASYNC_EXECUTOR = "asyncExecutor";
        
        /** 定时任务线程池名称 */
        public static final String SCHEDULED_EXECUTOR = "scheduledExecutor";
        
        /** IO密集型线程池名称 */
        public static final String IO_EXECUTOR = "ioExecutor";
        
        /** CPU密集型线程池名称 */
        public static final String CPU_EXECUTOR = "cpuExecutor";
        
        /** 默认核心线程数 */
        public static final int DEFAULT_CORE_POOL_SIZE = Runtime.getRuntime().availableProcessors();
        
        /** 默认最大线程数 */
        public static final int DEFAULT_MAX_POOL_SIZE = DEFAULT_CORE_POOL_SIZE * 2;
        
        /** 默认队列容量 */
        public static final int DEFAULT_QUEUE_CAPACITY = 1000;
        
        /** 默认线程存活时间（秒） */
        public static final int DEFAULT_KEEP_ALIVE_SECONDS = 60;
    }

    /**
     * 事件相关常量
     */
    @NoArgsConstructor(access = AccessLevel.PRIVATE)
    public static final class Event {
        /** 用户注册事件 */
        public static final String USER_REGISTER = "user.register";
        
        /** 用户登录事件 */
        public static final String USER_LOGIN = "user.login";
        
        /** 用户注销事件 */
        public static final String USER_LOGOUT = "user.logout";
        
        /** 内容发布事件 */
        public static final String CONTENT_PUBLISH = "content.publish";
        
        /** 内容更新事件 */
        public static final String CONTENT_UPDATE = "content.update";
        
        /** 订单创建事件 */
        public static final String ORDER_CREATE = "order.create";
        
        /** 订单支付事件 */
        public static final String ORDER_PAID = "order.paid";
        
        /** 系统启动事件 */
        public static final String SYSTEM_STARTUP = "system.startup";
        
        /** 系统关闭事件 */
        public static final String SYSTEM_SHUTDOWN = "system.shutdown";
    }
}
