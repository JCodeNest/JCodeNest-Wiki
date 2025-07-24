-- =============================================
-- 社交互动模块表结构 (soc_)
-- 版本: 1.0.0
-- 创建时间: 2025-07-23
-- 描述: 社交互动相关的所有表结构
-- =============================================

USE `jcode_wiki`;

-- 评论表
DROP TABLE IF EXISTS `soc_comment`;
CREATE TABLE `soc_comment` (
    `id` BIGINT NOT NULL COMMENT '评论ID，雪花算法生成',
    `content_id` BIGINT NOT NULL COMMENT '内容ID',
    `content_type` VARCHAR(50) NOT NULL COMMENT '内容类型：article-文章，video-视频，course-课程',
    `user_id` BIGINT NOT NULL COMMENT '评论用户ID',
    `parent_id` BIGINT DEFAULT 0 COMMENT '父评论ID，0表示顶级评论',
    `reply_to_user_id` BIGINT DEFAULT NULL COMMENT '回复的用户ID',
    `comment_content` TEXT NOT NULL COMMENT '评论内容',
    `comment_html` TEXT DEFAULT NULL COMMENT '评论HTML内容',
    `images` JSON DEFAULT NULL COMMENT '评论图片（JSON数组）',
    `like_count` INT DEFAULT 0 COMMENT '点赞数',
    `reply_count` INT DEFAULT 0 COMMENT '回复数',
    `is_top` TINYINT DEFAULT 0 COMMENT '是否置顶：0-否，1-是',
    `is_hot` TINYINT DEFAULT 0 COMMENT '是否热门：0-否，1-是',
    `status` TINYINT DEFAULT 1 COMMENT '状态：0-已删除，1-正常，2-待审核，3-审核失败',
    `ip_address` VARCHAR(50) DEFAULT NULL COMMENT 'IP地址',
    `user_agent` TEXT DEFAULT NULL COMMENT '用户代理',
    `created_at` DATETIME DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
    `updated_at` DATETIME DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
    `is_deleted` TINYINT DEFAULT 0 COMMENT '是否删除：0-未删除，1-已删除',
    PRIMARY KEY (`id`),
    KEY `idx_content_id` (`content_id`),
    KEY `idx_user_id` (`user_id`),
    KEY `idx_parent_id` (`parent_id`),
    KEY `idx_reply_to_user_id` (`reply_to_user_id`),
    KEY `idx_status` (`status`),
    KEY `idx_created_at` (`created_at`),
    KEY `idx_like_count` (`like_count`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci COMMENT='评论表';

-- 点赞表
DROP TABLE IF EXISTS `soc_like`;
CREATE TABLE `soc_like` (
    `id` BIGINT NOT NULL COMMENT '点赞ID',
    `user_id` BIGINT NOT NULL COMMENT '用户ID',
    `target_id` BIGINT NOT NULL COMMENT '目标ID（内容ID或评论ID）',
    `target_type` VARCHAR(50) NOT NULL COMMENT '目标类型：content-内容，comment-评论',
    `like_type` TINYINT DEFAULT 1 COMMENT '点赞类型：1-点赞，2-点踩',
    `created_at` DATETIME DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
    `updated_at` DATETIME DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
    `is_deleted` TINYINT DEFAULT 0 COMMENT '是否删除：0-未删除，1-已删除',
    PRIMARY KEY (`id`),
    UNIQUE KEY `uk_user_target` (`user_id`, `target_id`, `target_type`),
    KEY `idx_target_id` (`target_id`),
    KEY `idx_target_type` (`target_type`),
    KEY `idx_like_type` (`like_type`),
    KEY `idx_created_at` (`created_at`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci COMMENT='点赞表';

-- 收藏表
DROP TABLE IF EXISTS `soc_favorite`;
CREATE TABLE `soc_favorite` (
    `id` BIGINT NOT NULL COMMENT '收藏ID',
    `user_id` BIGINT NOT NULL COMMENT '用户ID',
    `content_id` BIGINT NOT NULL COMMENT '内容ID',
    `content_type` VARCHAR(50) NOT NULL COMMENT '内容类型',
    `folder_name` VARCHAR(100) DEFAULT '默认收藏夹' COMMENT '收藏夹名称',
    `tags` JSON DEFAULT NULL COMMENT '收藏标签（JSON数组）',
    `remark` TEXT DEFAULT NULL COMMENT '收藏备注',
    `created_at` DATETIME DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
    `updated_at` DATETIME DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
    `is_deleted` TINYINT DEFAULT 0 COMMENT '是否删除：0-未删除，1-已删除',
    PRIMARY KEY (`id`),
    UNIQUE KEY `uk_user_content` (`user_id`, `content_id`),
    KEY `idx_user_id` (`user_id`),
    KEY `idx_content_id` (`content_id`),
    KEY `idx_content_type` (`content_type`),
    KEY `idx_folder_name` (`folder_name`),
    KEY `idx_created_at` (`created_at`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci COMMENT='收藏表';

-- 关注表
DROP TABLE IF EXISTS `soc_follow`;
CREATE TABLE `soc_follow` (
    `id` BIGINT NOT NULL COMMENT '关注ID',
    `follower_id` BIGINT NOT NULL COMMENT '关注者ID',
    `following_id` BIGINT NOT NULL COMMENT '被关注者ID',
    `follow_type` TINYINT DEFAULT 1 COMMENT '关注类型：1-关注用户，2-关注话题，3-关注分类',
    `status` TINYINT DEFAULT 1 COMMENT '状态：0-已取消，1-已关注',
    `created_at` DATETIME DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
    `updated_at` DATETIME DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
    `is_deleted` TINYINT DEFAULT 0 COMMENT '是否删除：0-未删除，1-已删除',
    PRIMARY KEY (`id`),
    UNIQUE KEY `uk_follower_following` (`follower_id`, `following_id`, `follow_type`),
    KEY `idx_follower_id` (`follower_id`),
    KEY `idx_following_id` (`following_id`),
    KEY `idx_follow_type` (`follow_type`),
    KEY `idx_status` (`status`),
    KEY `idx_created_at` (`created_at`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci COMMENT='关注表';

-- 消息表
DROP TABLE IF EXISTS `soc_message`;
CREATE TABLE `soc_message` (
    `id` BIGINT NOT NULL COMMENT '消息ID',
    `sender_id` BIGINT DEFAULT NULL COMMENT '发送者ID，NULL表示系统消息',
    `receiver_id` BIGINT NOT NULL COMMENT '接收者ID',
    `message_type` TINYINT NOT NULL COMMENT '消息类型：1-系统通知，2-私信，3-评论回复，4-点赞通知，5-关注通知',
    `title` VARCHAR(200) DEFAULT NULL COMMENT '消息标题',
    `content` TEXT NOT NULL COMMENT '消息内容',
    `related_id` BIGINT DEFAULT NULL COMMENT '关联ID（如评论ID、内容ID等）',
    `related_type` VARCHAR(50) DEFAULT NULL COMMENT '关联类型',
    `is_read` TINYINT DEFAULT 0 COMMENT '是否已读：0-未读，1-已读',
    `read_time` DATETIME DEFAULT NULL COMMENT '阅读时间',
    `created_at` DATETIME DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
    `updated_at` DATETIME DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
    `is_deleted` TINYINT DEFAULT 0 COMMENT '是否删除：0-未删除，1-已删除',
    PRIMARY KEY (`id`),
    KEY `idx_sender_id` (`sender_id`),
    KEY `idx_receiver_id` (`receiver_id`),
    KEY `idx_message_type` (`message_type`),
    KEY `idx_is_read` (`is_read`),
    KEY `idx_created_at` (`created_at`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci COMMENT='消息表';

-- 通知表
DROP TABLE IF EXISTS `soc_notification`;
CREATE TABLE `soc_notification` (
    `id` BIGINT NOT NULL COMMENT '通知ID',
    `user_id` BIGINT NOT NULL COMMENT '用户ID',
    `notification_type` TINYINT NOT NULL COMMENT '通知类型：1-系统通知，2-学习提醒，3-营销消息，4-安全提醒',
    `title` VARCHAR(200) NOT NULL COMMENT '通知标题',
    `content` TEXT NOT NULL COMMENT '通知内容',
    `action_url` VARCHAR(500) DEFAULT NULL COMMENT '操作链接',
    `action_text` VARCHAR(50) DEFAULT NULL COMMENT '操作按钮文本',
    `priority` TINYINT DEFAULT 1 COMMENT '优先级：1-低，2-中，3-高，4-紧急',
    `is_read` TINYINT DEFAULT 0 COMMENT '是否已读：0-未读，1-已读',
    `read_time` DATETIME DEFAULT NULL COMMENT '阅读时间',
    `expire_time` DATETIME DEFAULT NULL COMMENT '过期时间',
    `created_at` DATETIME DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
    `updated_at` DATETIME DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
    `is_deleted` TINYINT DEFAULT 0 COMMENT '是否删除：0-未删除，1-已删除',
    PRIMARY KEY (`id`),
    KEY `idx_user_id` (`user_id`),
    KEY `idx_notification_type` (`notification_type`),
    KEY `idx_priority` (`priority`),
    KEY `idx_is_read` (`is_read`),
    KEY `idx_expire_time` (`expire_time`),
    KEY `idx_created_at` (`created_at`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci COMMENT='通知表';

-- 分享记录表
DROP TABLE IF EXISTS `soc_share`;
CREATE TABLE `soc_share` (
    `id` BIGINT NOT NULL COMMENT '分享记录ID',
    `user_id` BIGINT NOT NULL COMMENT '分享用户ID',
    `content_id` BIGINT NOT NULL COMMENT '分享内容ID',
    `content_type` VARCHAR(50) NOT NULL COMMENT '内容类型',
    `share_platform` VARCHAR(50) NOT NULL COMMENT '分享平台：wechat-微信，qq-QQ，weibo-微博，link-链接',
    `share_title` VARCHAR(200) DEFAULT NULL COMMENT '分享标题',
    `share_description` TEXT DEFAULT NULL COMMENT '分享描述',
    `share_image` VARCHAR(500) DEFAULT NULL COMMENT '分享图片',
    `share_url` VARCHAR(500) DEFAULT NULL COMMENT '分享链接',
    `ip_address` VARCHAR(50) DEFAULT NULL COMMENT 'IP地址',
    `user_agent` TEXT DEFAULT NULL COMMENT '用户代理',
    `created_at` DATETIME DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
    PRIMARY KEY (`id`),
    KEY `idx_user_id` (`user_id`),
    KEY `idx_content_id` (`content_id`),
    KEY `idx_share_platform` (`share_platform`),
    KEY `idx_created_at` (`created_at`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci COMMENT='分享记录表';

-- 用户积分表
DROP TABLE IF EXISTS `soc_user_score`;
CREATE TABLE `soc_user_score` (
    `id` BIGINT NOT NULL COMMENT '积分记录ID',
    `user_id` BIGINT NOT NULL COMMENT '用户ID',
    `score_type` TINYINT NOT NULL COMMENT '积分类型：1-获得，2-消费，3-过期，4-调整',
    `score_source` VARCHAR(50) NOT NULL COMMENT '积分来源：login-登录，content-发布内容，comment-评论，like-点赞等',
    `score_change` INT NOT NULL COMMENT '积分变化（正数为增加，负数为减少）',
    `current_score` INT NOT NULL COMMENT '当前总积分',
    `related_id` BIGINT DEFAULT NULL COMMENT '关联ID',
    `related_type` VARCHAR(50) DEFAULT NULL COMMENT '关联类型',
    `description` VARCHAR(200) DEFAULT NULL COMMENT '积分描述',
    `expire_time` DATETIME DEFAULT NULL COMMENT '积分过期时间',
    `created_at` DATETIME DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
    `updated_at` DATETIME DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
    `is_deleted` TINYINT DEFAULT 0 COMMENT '是否删除：0-未删除，1-已删除',
    PRIMARY KEY (`id`),
    KEY `idx_user_id` (`user_id`),
    KEY `idx_score_type` (`score_type`),
    KEY `idx_score_source` (`score_source`),
    KEY `idx_expire_time` (`expire_time`),
    KEY `idx_created_at` (`created_at`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci COMMENT='用户积分表';
