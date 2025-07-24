-- =============================================
-- 用户管理模块表结构 (usr_)
-- 版本: 1.0.0
-- 创建时间: 2025-07-23
-- 描述: 用户管理相关的所有表结构
-- =============================================

USE `jcode_wiki`;

-- 用户基础信息表
DROP TABLE IF EXISTS `usr_user`;
CREATE TABLE `usr_user` (
    `id` BIGINT NOT NULL COMMENT '用户ID，雪花算法生成',
    `username` VARCHAR(50) NOT NULL COMMENT '用户名，唯一标识',
    `email` VARCHAR(100) NOT NULL COMMENT '邮箱地址',
    `phone` VARCHAR(20) DEFAULT NULL COMMENT '手机号码',
    `password` VARCHAR(255) NOT NULL COMMENT '密码，BCrypt加密',
    `nickname` VARCHAR(100) DEFAULT NULL COMMENT '昵称',
    `avatar` VARCHAR(500) DEFAULT NULL COMMENT '头像URL',
    `gender` TINYINT DEFAULT 0 COMMENT '性别：0-未知，1-男，2-女',
    `birthday` DATE DEFAULT NULL COMMENT '生日',
    `bio` TEXT DEFAULT NULL COMMENT '个人简介',
    `status` TINYINT DEFAULT 1 COMMENT '状态：0-禁用，1-正常，2-待激活',
    `last_login_time` DATETIME DEFAULT NULL COMMENT '最后登录时间',
    `last_login_ip` VARCHAR(50) DEFAULT NULL COMMENT '最后登录IP',
    `login_count` INT DEFAULT 0 COMMENT '登录次数',
    `email_verified` TINYINT DEFAULT 0 COMMENT '邮箱是否验证：0-未验证，1-已验证',
    `phone_verified` TINYINT DEFAULT 0 COMMENT '手机是否验证：0-未验证，1-已验证',
    `created_at` DATETIME DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
    `updated_at` DATETIME DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
    `is_deleted` TINYINT DEFAULT 0 COMMENT '是否删除：0-未删除，1-已删除',
    PRIMARY KEY (`id`),
    UNIQUE KEY `uk_username` (`username`),
    UNIQUE KEY `uk_email` (`email`),
    KEY `idx_phone` (`phone`),
    KEY `idx_status` (`status`),
    KEY `idx_created_at` (`created_at`),
    KEY `idx_is_deleted` (`is_deleted`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci COMMENT='用户基础信息表';

-- 角色定义表
DROP TABLE IF EXISTS `usr_role`;
CREATE TABLE `usr_role` (
    `id` BIGINT NOT NULL COMMENT '角色ID',
    `role_code` VARCHAR(50) NOT NULL COMMENT '角色编码，如：ADMIN、USER、VIP等',
    `role_name` VARCHAR(100) NOT NULL COMMENT '角色名称',
    `description` TEXT DEFAULT NULL COMMENT '角色描述',
    `sort_order` INT DEFAULT 0 COMMENT '排序顺序',
    `status` TINYINT DEFAULT 1 COMMENT '状态：0-禁用，1-启用',
    `created_at` DATETIME DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
    `updated_at` DATETIME DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
    `is_deleted` TINYINT DEFAULT 0 COMMENT '是否删除：0-未删除，1-已删除',
    PRIMARY KEY (`id`),
    UNIQUE KEY `uk_role_code` (`role_code`),
    KEY `idx_status` (`status`),
    KEY `idx_sort_order` (`sort_order`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci COMMENT='角色定义表';

-- 用户角色关联表
DROP TABLE IF EXISTS `usr_user_role`;
CREATE TABLE `usr_user_role` (
    `id` BIGINT NOT NULL COMMENT '关联ID',
    `user_id` BIGINT NOT NULL COMMENT '用户ID',
    `role_id` BIGINT NOT NULL COMMENT '角色ID',
    `created_at` DATETIME DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
    `updated_at` DATETIME DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
    `is_deleted` TINYINT DEFAULT 0 COMMENT '是否删除：0-未删除，1-已删除',
    PRIMARY KEY (`id`),
    UNIQUE KEY `uk_user_role` (`user_id`, `role_id`),
    KEY `idx_user_id` (`user_id`),
    KEY `idx_role_id` (`role_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci COMMENT='用户角色关联表';

-- 权限定义表
DROP TABLE IF EXISTS `usr_permission`;
CREATE TABLE `usr_permission` (
    `id` BIGINT NOT NULL COMMENT '权限ID',
    `permission_code` VARCHAR(100) NOT NULL COMMENT '权限编码',
    `permission_name` VARCHAR(100) NOT NULL COMMENT '权限名称',
    `resource_type` VARCHAR(50) DEFAULT NULL COMMENT '资源类型：menu-菜单，button-按钮，api-接口',
    `resource_path` VARCHAR(200) DEFAULT NULL COMMENT '资源路径',
    `parent_id` BIGINT DEFAULT 0 COMMENT '父权限ID，0表示顶级权限',
    `sort_order` INT DEFAULT 0 COMMENT '排序顺序',
    `status` TINYINT DEFAULT 1 COMMENT '状态：0-禁用，1-启用',
    `created_at` DATETIME DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
    `updated_at` DATETIME DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
    `is_deleted` TINYINT DEFAULT 0 COMMENT '是否删除：0-未删除，1-已删除',
    PRIMARY KEY (`id`),
    UNIQUE KEY `uk_permission_code` (`permission_code`),
    KEY `idx_parent_id` (`parent_id`),
    KEY `idx_resource_type` (`resource_type`),
    KEY `idx_status` (`status`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci COMMENT='权限定义表';

-- 角色权限关联表
DROP TABLE IF EXISTS `usr_role_permission`;
CREATE TABLE `usr_role_permission` (
    `id` BIGINT NOT NULL COMMENT '关联ID',
    `role_id` BIGINT NOT NULL COMMENT '角色ID',
    `permission_id` BIGINT NOT NULL COMMENT '权限ID',
    `created_at` DATETIME DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
    `updated_at` DATETIME DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
    `is_deleted` TINYINT DEFAULT 0 COMMENT '是否删除：0-未删除，1-已删除',
    PRIMARY KEY (`id`),
    UNIQUE KEY `uk_role_permission` (`role_id`, `permission_id`),
    KEY `idx_role_id` (`role_id`),
    KEY `idx_permission_id` (`permission_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci COMMENT='角色权限关联表';

-- 用户画像表
DROP TABLE IF EXISTS `usr_profile`;
CREATE TABLE `usr_profile` (
    `id` BIGINT NOT NULL COMMENT '画像ID',
    `user_id` BIGINT NOT NULL COMMENT '用户ID',
    `total_study_time` INT DEFAULT 0 COMMENT '总学习时长（分钟）',
    `total_content_count` INT DEFAULT 0 COMMENT '创建内容总数',
    `total_like_count` INT DEFAULT 0 COMMENT '获得点赞总数',
    `total_comment_count` INT DEFAULT 0 COMMENT '评论总数',
    `total_share_count` INT DEFAULT 0 COMMENT '分享总数',
    `total_purchase_amount` DECIMAL(10,2) DEFAULT 0.00 COMMENT '总消费金额',
    `total_revenue_amount` DECIMAL(10,2) DEFAULT 0.00 COMMENT '总收益金额',
    `favorite_categories` JSON DEFAULT NULL COMMENT '偏好分类（JSON格式）',
    `favorite_tags` JSON DEFAULT NULL COMMENT '偏好标签（JSON格式）',
    `activity_score` INT DEFAULT 0 COMMENT '活跃度评分',
    `level` TINYINT DEFAULT 1 COMMENT '用户等级',
    `experience` INT DEFAULT 0 COMMENT '经验值',
    `created_at` DATETIME DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
    `updated_at` DATETIME DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
    `is_deleted` TINYINT DEFAULT 0 COMMENT '是否删除：0-未删除，1-已删除',
    PRIMARY KEY (`id`),
    UNIQUE KEY `uk_user_id` (`user_id`),
    KEY `idx_level` (`level`),
    KEY `idx_activity_score` (`activity_score`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci COMMENT='用户画像表';

-- 登录日志表
DROP TABLE IF EXISTS `usr_login_log`;
CREATE TABLE `usr_login_log` (
    `id` BIGINT NOT NULL COMMENT '日志ID',
    `user_id` BIGINT NOT NULL COMMENT '用户ID',
    `login_type` TINYINT DEFAULT 1 COMMENT '登录类型：1-账号密码，2-手机验证码，3-第三方登录',
    `login_ip` VARCHAR(50) DEFAULT NULL COMMENT '登录IP',
    `login_location` VARCHAR(100) DEFAULT NULL COMMENT '登录地点',
    `user_agent` TEXT DEFAULT NULL COMMENT '用户代理信息',
    `device_type` VARCHAR(50) DEFAULT NULL COMMENT '设备类型：PC、Mobile、Tablet',
    `browser` VARCHAR(100) DEFAULT NULL COMMENT '浏览器信息',
    `os` VARCHAR(100) DEFAULT NULL COMMENT '操作系统',
    `login_status` TINYINT DEFAULT 1 COMMENT '登录状态：0-失败，1-成功',
    `failure_reason` VARCHAR(200) DEFAULT NULL COMMENT '失败原因',
    `created_at` DATETIME DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
    PRIMARY KEY (`id`),
    KEY `idx_user_id` (`user_id`),
    KEY `idx_login_ip` (`login_ip`),
    KEY `idx_login_status` (`login_status`),
    KEY `idx_created_at` (`created_at`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci COMMENT='登录日志表';

-- 第三方登录表
DROP TABLE IF EXISTS `usr_third_auth`;
CREATE TABLE `usr_third_auth` (
    `id` BIGINT NOT NULL COMMENT '授权ID',
    `user_id` BIGINT NOT NULL COMMENT '用户ID',
    `provider` VARCHAR(50) NOT NULL COMMENT '第三方平台：wechat、qq、github、alipay等',
    `open_id` VARCHAR(100) NOT NULL COMMENT '第三方平台用户标识',
    `union_id` VARCHAR(100) DEFAULT NULL COMMENT '第三方平台统一标识',
    `nickname` VARCHAR(100) DEFAULT NULL COMMENT '第三方平台昵称',
    `avatar` VARCHAR(500) DEFAULT NULL COMMENT '第三方平台头像',
    `access_token` TEXT DEFAULT NULL COMMENT '访问令牌',
    `refresh_token` TEXT DEFAULT NULL COMMENT '刷新令牌',
    `expires_at` DATETIME DEFAULT NULL COMMENT '令牌过期时间',
    `status` TINYINT DEFAULT 1 COMMENT '状态：0-禁用，1-启用',
    `created_at` DATETIME DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
    `updated_at` DATETIME DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
    `is_deleted` TINYINT DEFAULT 0 COMMENT '是否删除：0-未删除，1-已删除',
    PRIMARY KEY (`id`),
    UNIQUE KEY `uk_provider_openid` (`provider`, `open_id`),
    KEY `idx_user_id` (`user_id`),
    KEY `idx_provider` (`provider`),
    KEY `idx_status` (`status`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci COMMENT='第三方登录表';
