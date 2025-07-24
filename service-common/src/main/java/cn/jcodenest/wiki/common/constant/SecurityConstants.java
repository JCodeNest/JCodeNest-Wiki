package cn.jcodenest.wiki.common.constant;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;

/**
 * 安全相关常量类
 *
 * @author JCodeNest
 * @version 1.0.0
 * @since 2025/7/24
 * <p>
 * Copyright (c) 2025 JCodeNest-Wiki
 * All rights reserved.
 */
@NoArgsConstructor(access = AccessLevel.PRIVATE)
public final class SecurityConstants {

    /**
     * Token相关常量
     */
    @NoArgsConstructor(access = AccessLevel.PRIVATE)
    public static final class Token {
        /** Token前缀 */
        public static final String TOKEN_PREFIX = "Bearer ";
        
        /** Token请求头名称 */
        public static final String TOKEN_HEADER = "Authorization";
        
        /** Token参数名称 */
        public static final String TOKEN_PARAM = "token";
        
        /** Token密钥 */
        public static final String TOKEN_SECRET = "jcodenest-wiki-secret-key-2025";
        
        /** Token过期时间（2小时，单位：秒） */
        public static final long TOKEN_EXPIRE_TIME = 2 * 60 * 60L;
        
        /** 刷新Token过期时间（7天，单位：秒） */
        public static final long REFRESH_TOKEN_EXPIRE_TIME = 7 * 24 * 60 * 60L;
        
        /** Token最小长度 */
        public static final int TOKEN_MIN_LENGTH = 32;
    }

    /**
     * 用户相关常量
     */
    @NoArgsConstructor(access = AccessLevel.PRIVATE)
    public static final class User {
        /** 用户ID请求头名称 */
        public static final String USER_ID_HEADER = "X-User-Id";
        
        /** 用户名请求头名称 */
        public static final String USERNAME_HEADER = "X-Username";
        
        /** 用户角色请求头名称 */
        public static final String USER_ROLES_HEADER = "X-User-Roles";
        
        /** 匿名用户名 */
        public static final String ANONYMOUS_USER = "anonymous";
        
        /** 系统用户名 */
        public static final String SYSTEM_USER = "system";
        
        /** 默认用户头像 */
        public static final String DEFAULT_AVATAR = "/static/images/default-avatar.png";
    }

    /**
     * 角色相关常量
     */
    @NoArgsConstructor(access = AccessLevel.PRIVATE)
    public static final class Role {
        /** 角色前缀 */
        public static final String ROLE_PREFIX = "ROLE_";
        
        /** 管理员角色 */
        public static final String ADMIN = "ADMIN";
        
        /** 内容创作者角色 */
        public static final String CREATOR = "CREATOR";
        
        /** VIP用户角色 */
        public static final String VIP = "VIP";
        
        /** 普通用户角色 */
        public static final String USER = "USER";
        
        /** 游客角色 */
        public static final String GUEST = "GUEST";
    }

    /**
     * 权限相关常量
     */
    @NoArgsConstructor(access = AccessLevel.PRIVATE)
    public static final class Permission {
        /** 权限分隔符 */
        public static final String PERMISSION_SEPARATOR = ",";
        
        /** 所有权限 */
        public static final String ALL_PERMISSION = "*:*:*";
        
        /** 用户管理权限 */
        public static final String USER_MANAGE = "user:manage";
        
        /** 内容管理权限 */
        public static final String CONTENT_MANAGE = "content:manage";
        
        /** 订单管理权限 */
        public static final String ORDER_MANAGE = "order:manage";
        
        /** 系统管理权限 */
        public static final String SYSTEM_MANAGE = "system:manage";
    }

    /**
     * 登录相关常量
     */
    @NoArgsConstructor(access = AccessLevel.PRIVATE)
    public static final class Login {
        /** 最大登录失败次数 */
        public static final int MAX_LOGIN_FAIL_COUNT = 5;
        
        /** 登录失败锁定时间（30分钟，单位：秒） */
        public static final long LOGIN_FAIL_LOCK_TIME = 30 * 60L;
        
        /** 验证码长度 */
        public static final int CAPTCHA_LENGTH = 4;
        
        /** 验证码过期时间（5分钟，单位：秒） */
        public static final long CAPTCHA_EXPIRE_TIME = 5 * 60L;
        
        /** 短信验证码长度 */
        public static final int SMS_CODE_LENGTH = 6;
        
        /** 短信验证码过期时间（5分钟，单位：秒） */
        public static final long SMS_CODE_EXPIRE_TIME = 5 * 60L;
        
        /** 邮箱验证码长度 */
        public static final int EMAIL_CODE_LENGTH = 6;
        
        /** 邮箱验证码过期时间（10分钟，单位：秒） */
        public static final long EMAIL_CODE_EXPIRE_TIME = 10 * 60L;
    }

    /**
     * 密码相关常量
     */
    @NoArgsConstructor(access = AccessLevel.PRIVATE)
    public static final class Password {
        /** 密码最小长度 */
        public static final int MIN_LENGTH = 6;
        
        /** 密码最大长度 */
        public static final int MAX_LENGTH = 20;
        
        /** 密码加密轮数 */
        public static final int BCRYPT_ROUNDS = 12;
        
        /** 密码重置Token过期时间（1小时，单位：秒） */
        public static final long RESET_TOKEN_EXPIRE_TIME = 60 * 60L;
        
        /** 密码历史记录数量 */
        public static final int HISTORY_COUNT = 5;
    }

    /**
     * 加密相关常量
     */
    @NoArgsConstructor(access = AccessLevel.PRIVATE)
    public static final class Encrypt {
        /** AES密钥 */
        public static final String AES_KEY = "jcodenest-wiki-aes-key-2025-32bit";
        
        /** AES算法 */
        public static final String AES_ALGORITHM = "AES";
        
        /** AES转换模式 */
        public static final String AES_TRANSFORMATION = "AES/ECB/PKCS5Padding";
        
        /** MD5算法 */
        public static final String MD5_ALGORITHM = "MD5";
        
        /** SHA256算法 */
        public static final String SHA256_ALGORITHM = "SHA-256";
        
        /** RSA算法 */
        public static final String RSA_ALGORITHM = "RSA";
        
        /** RSA密钥长度 */
        public static final int RSA_KEY_SIZE = 2048;
    }

    /**
     * 限流相关常量
     */
    @NoArgsConstructor(access = AccessLevel.PRIVATE)
    public static final class RateLimit {
        /** 默认限流次数 */
        public static final int DEFAULT_COUNT = 100;
        
        /** 默认限流时间窗口（1分钟，单位：秒） */
        public static final long DEFAULT_PERIOD = 60L;
        
        /** 登录接口限流次数 */
        public static final int LOGIN_COUNT = 10;
        
        /** 登录接口限流时间窗口（1分钟，单位：秒） */
        public static final long LOGIN_PERIOD = 60L;
        
        /** 注册接口限流次数 */
        public static final int REGISTER_COUNT = 5;
        
        /** 注册接口限流时间窗口（1分钟，单位：秒） */
        public static final long REGISTER_PERIOD = 60L;
        
        /** 发送验证码限流次数 */
        public static final int SEND_CODE_COUNT = 3;
        
        /** 发送验证码限流时间窗口（1分钟，单位：秒） */
        public static final long SEND_CODE_PERIOD = 60L;
    }

    /**
     * 会话相关常量
     */
    @NoArgsConstructor(access = AccessLevel.PRIVATE)
    public static final class Session {
        /** 会话超时时间（30分钟，单位：秒） */
        public static final long SESSION_TIMEOUT = 30 * 60L;
        
        /** 最大会话数量 */
        public static final int MAX_SESSION_COUNT = 10;
        
        /** 会话ID长度 */
        public static final int SESSION_ID_LENGTH = 32;
        
        /** 踢出其他会话 */
        public static final boolean KICK_OUT_AFTER = true;
    }

    /**
     * 跨域相关常量
     */
    @NoArgsConstructor(access = AccessLevel.PRIVATE)
    public static final class Cors {
        /** 允许的源 */
        public static final String ALLOWED_ORIGINS = "*";
        
        /** 允许的方法 */
        public static final String ALLOWED_METHODS = "GET,POST,PUT,DELETE,OPTIONS";
        
        /** 允许的头部 */
        public static final String ALLOWED_HEADERS = "*";
        
        /** 是否允许凭证 */
        public static final boolean ALLOW_CREDENTIALS = true;
        
        /** 预检请求缓存时间（1小时，单位：秒） */
        public static final long MAX_AGE = 60 * 60L;
    }

    /**
     * 白名单相关常量
     */
    @NoArgsConstructor(access = AccessLevel.PRIVATE)
    public static final class Whitelist {
        /** 不需要认证的URL */
        public static final String[] PERMIT_ALL_URLS = {
            "/api/auth/login",
            "/api/auth/register",
            "/api/auth/captcha",
            "/api/auth/forgot-password",
            "/api/public/**",
            "/doc.html",
            "/swagger-ui/**",
            "/swagger-resources/**",
            "/v3/api-docs/**",
            "/webjars/**",
            "/favicon.ico",
            "/error"
        };
        
        /** 静态资源URL */
        public static final String[] STATIC_RESOURCES = {
            "/static/**",
            "/images/**",
            "/css/**",
            "/js/**",
            "/fonts/**"
        };
    }
}
