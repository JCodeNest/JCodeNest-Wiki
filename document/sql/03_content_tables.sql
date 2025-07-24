-- =============================================
-- 内容管理模块表结构 (cnt_)
-- 版本: 1.0.0
-- 创建时间: 2025-07-23
-- 描述: 内容管理相关的所有表结构
-- =============================================

USE `jcode_wiki`;

-- 内容主表
DROP TABLE IF EXISTS `cnt_content`;
CREATE TABLE `cnt_content` (
    `id` BIGINT NOT NULL COMMENT '内容ID，雪花算法生成',
    `title` VARCHAR(200) NOT NULL COMMENT '内容标题',
    `subtitle` VARCHAR(300) DEFAULT NULL COMMENT '副标题',
    `summary` TEXT DEFAULT NULL COMMENT '内容摘要',
    `content` LONGTEXT DEFAULT NULL COMMENT '内容正文（Markdown格式）',
    `content_html` LONGTEXT DEFAULT NULL COMMENT '内容HTML（渲染后）',
    `content_type` VARCHAR(50) NOT NULL COMMENT '内容类型：article-文章，video-视频，audio-音频，course-课程',
    `author_id` BIGINT NOT NULL COMMENT '作者用户ID',
    `category_id` BIGINT DEFAULT NULL COMMENT '分类ID',
    `cover_image` VARCHAR(500) DEFAULT NULL COMMENT '封面图片URL',
    `status` TINYINT DEFAULT 1 COMMENT '状态：0-草稿，1-已发布，2-审核中，3-审核失败，4-已下架',
    `visibility` TINYINT DEFAULT 1 COMMENT '可见性：1-公开，2-登录可见，3-VIP可见，4-付费可见，5-私有',
    `price` DECIMAL(10,2) DEFAULT 0.00 COMMENT '价格（元）',
    `original_price` DECIMAL(10,2) DEFAULT 0.00 COMMENT '原价（元）',
    `view_count` INT DEFAULT 0 COMMENT '浏览次数',
    `like_count` INT DEFAULT 0 COMMENT '点赞次数',
    `comment_count` INT DEFAULT 0 COMMENT '评论次数',
    `share_count` INT DEFAULT 0 COMMENT '分享次数',
    `favorite_count` INT DEFAULT 0 COMMENT '收藏次数',
    `purchase_count` INT DEFAULT 0 COMMENT '购买次数',
    `word_count` INT DEFAULT 0 COMMENT '字数统计',
    `reading_time` INT DEFAULT 0 COMMENT '预计阅读时间（分钟）',
    `is_top` TINYINT DEFAULT 0 COMMENT '是否置顶：0-否，1-是',
    `is_hot` TINYINT DEFAULT 0 COMMENT '是否热门：0-否，1-是',
    `is_recommend` TINYINT DEFAULT 0 COMMENT '是否推荐：0-否，1-是',
    `sort_order` INT DEFAULT 0 COMMENT '排序顺序',
    `seo_title` VARCHAR(200) DEFAULT NULL COMMENT 'SEO标题',
    `seo_keywords` VARCHAR(500) DEFAULT NULL COMMENT 'SEO关键词',
    `seo_description` TEXT DEFAULT NULL COMMENT 'SEO描述',
    `published_at` DATETIME DEFAULT NULL COMMENT '发布时间',
    `created_at` DATETIME DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
    `updated_at` DATETIME DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
    `is_deleted` TINYINT DEFAULT 0 COMMENT '是否删除：0-未删除，1-已删除',
    PRIMARY KEY (`id`),
    KEY `idx_author_id` (`author_id`),
    KEY `idx_category_id` (`category_id`),
    KEY `idx_content_type` (`content_type`),
    KEY `idx_status` (`status`),
    KEY `idx_visibility` (`visibility`),
    KEY `idx_published_at` (`published_at`),
    KEY `idx_created_at` (`created_at`),
    KEY `idx_view_count` (`view_count`),
    KEY `idx_like_count` (`like_count`),
    KEY `idx_is_top_hot_recommend` (`is_top`, `is_hot`, `is_recommend`),
    FULLTEXT KEY `ft_title_content` (`title`, `content`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci COMMENT='内容主表';

-- 内容分类表
DROP TABLE IF EXISTS `cnt_category`;
CREATE TABLE `cnt_category` (
    `id` BIGINT NOT NULL COMMENT '分类ID',
    `category_name` VARCHAR(100) NOT NULL COMMENT '分类名称',
    `category_code` VARCHAR(50) NOT NULL COMMENT '分类编码',
    `description` TEXT DEFAULT NULL COMMENT '分类描述',
    `parent_id` BIGINT DEFAULT 0 COMMENT '父分类ID，0表示顶级分类',
    `level` TINYINT DEFAULT 1 COMMENT '分类层级',
    `path` VARCHAR(500) DEFAULT NULL COMMENT '分类路径，如：/1/2/3/',
    `icon` VARCHAR(200) DEFAULT NULL COMMENT '分类图标',
    `cover_image` VARCHAR(500) DEFAULT NULL COMMENT '分类封面图',
    `sort_order` INT DEFAULT 0 COMMENT '排序顺序',
    `content_count` INT DEFAULT 0 COMMENT '内容数量',
    `status` TINYINT DEFAULT 1 COMMENT '状态：0-禁用，1-启用',
    `created_at` DATETIME DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
    `updated_at` DATETIME DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
    `is_deleted` TINYINT DEFAULT 0 COMMENT '是否删除：0-未删除，1-已删除',
    PRIMARY KEY (`id`),
    UNIQUE KEY `uk_category_code` (`category_code`),
    KEY `idx_parent_id` (`parent_id`),
    KEY `idx_level` (`level`),
    KEY `idx_sort_order` (`sort_order`),
    KEY `idx_status` (`status`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci COMMENT='内容分类表';

-- 标签表
DROP TABLE IF EXISTS `cnt_tag`;
CREATE TABLE `cnt_tag` (
    `id` BIGINT NOT NULL COMMENT '标签ID',
    `tag_name` VARCHAR(50) NOT NULL COMMENT '标签名称',
    `tag_code` VARCHAR(50) NOT NULL COMMENT '标签编码',
    `description` TEXT DEFAULT NULL COMMENT '标签描述',
    `color` VARCHAR(20) DEFAULT '#1890ff' COMMENT '标签颜色',
    `icon` VARCHAR(200) DEFAULT NULL COMMENT '标签图标',
    `usage_count` INT DEFAULT 0 COMMENT '使用次数',
    `sort_order` INT DEFAULT 0 COMMENT '排序顺序',
    `status` TINYINT DEFAULT 1 COMMENT '状态：0-禁用，1-启用',
    `created_at` DATETIME DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
    `updated_at` DATETIME DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
    `is_deleted` TINYINT DEFAULT 0 COMMENT '是否删除：0-未删除，1-已删除',
    PRIMARY KEY (`id`),
    UNIQUE KEY `uk_tag_name` (`tag_name`),
    UNIQUE KEY `uk_tag_code` (`tag_code`),
    KEY `idx_usage_count` (`usage_count`),
    KEY `idx_sort_order` (`sort_order`),
    KEY `idx_status` (`status`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci COMMENT='标签表';

-- 内容标签关联表
DROP TABLE IF EXISTS `cnt_content_tag`;
CREATE TABLE `cnt_content_tag` (
    `id` BIGINT NOT NULL COMMENT '关联ID',
    `content_id` BIGINT NOT NULL COMMENT '内容ID',
    `tag_id` BIGINT NOT NULL COMMENT '标签ID',
    `created_at` DATETIME DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
    `updated_at` DATETIME DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
    `is_deleted` TINYINT DEFAULT 0 COMMENT '是否删除：0-未删除，1-已删除',
    PRIMARY KEY (`id`),
    UNIQUE KEY `uk_content_tag` (`content_id`, `tag_id`),
    KEY `idx_content_id` (`content_id`),
    KEY `idx_tag_id` (`tag_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci COMMENT='内容标签关联表';

-- 内容版本表
DROP TABLE IF EXISTS `cnt_content_version`;
CREATE TABLE `cnt_content_version` (
    `id` BIGINT NOT NULL COMMENT '版本ID',
    `content_id` BIGINT NOT NULL COMMENT '内容ID',
    `version_number` VARCHAR(20) NOT NULL COMMENT '版本号，如：v1.0.0',
    `title` VARCHAR(200) NOT NULL COMMENT '版本标题',
    `content` LONGTEXT DEFAULT NULL COMMENT '版本内容',
    `change_log` TEXT DEFAULT NULL COMMENT '变更日志',
    `author_id` BIGINT NOT NULL COMMENT '修改者ID',
    `file_size` BIGINT DEFAULT 0 COMMENT '内容大小（字节）',
    `word_count` INT DEFAULT 0 COMMENT '字数统计',
    `is_current` TINYINT DEFAULT 0 COMMENT '是否当前版本：0-否，1-是',
    `created_at` DATETIME DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
    `updated_at` DATETIME DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
    `is_deleted` TINYINT DEFAULT 0 COMMENT '是否删除：0-未删除，1-已删除',
    PRIMARY KEY (`id`),
    UNIQUE KEY `uk_content_version` (`content_id`, `version_number`),
    KEY `idx_content_id` (`content_id`),
    KEY `idx_author_id` (`author_id`),
    KEY `idx_is_current` (`is_current`),
    KEY `idx_created_at` (`created_at`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci COMMENT='内容版本表';

-- 内容审核表
DROP TABLE IF EXISTS `cnt_content_audit`;
CREATE TABLE `cnt_content_audit` (
    `id` BIGINT NOT NULL COMMENT '审核ID',
    `content_id` BIGINT NOT NULL COMMENT '内容ID',
    `audit_type` TINYINT DEFAULT 1 COMMENT '审核类型：1-发布审核，2-修改审核，3-举报审核',
    `audit_status` TINYINT DEFAULT 0 COMMENT '审核状态：0-待审核，1-审核通过，2-审核拒绝',
    `auditor_id` BIGINT DEFAULT NULL COMMENT '审核员ID',
    `audit_reason` TEXT DEFAULT NULL COMMENT '审核意见',
    `audit_time` DATETIME DEFAULT NULL COMMENT '审核时间',
    `submit_time` DATETIME DEFAULT CURRENT_TIMESTAMP COMMENT '提交时间',
    `created_at` DATETIME DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
    `updated_at` DATETIME DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
    `is_deleted` TINYINT DEFAULT 0 COMMENT '是否删除：0-未删除，1-已删除',
    PRIMARY KEY (`id`),
    KEY `idx_content_id` (`content_id`),
    KEY `idx_audit_status` (`audit_status`),
    KEY `idx_auditor_id` (`auditor_id`),
    KEY `idx_audit_type` (`audit_type`),
    KEY `idx_submit_time` (`submit_time`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci COMMENT='内容审核表';

-- 章节表
DROP TABLE IF EXISTS `cnt_chapter`;
CREATE TABLE `cnt_chapter` (
    `id` BIGINT NOT NULL COMMENT '章节ID',
    `content_id` BIGINT NOT NULL COMMENT '内容ID',
    `chapter_title` VARCHAR(200) NOT NULL COMMENT '章节标题',
    `chapter_number` INT NOT NULL COMMENT '章节序号',
    `description` TEXT DEFAULT NULL COMMENT '章节描述',
    `content` LONGTEXT DEFAULT NULL COMMENT '章节内容',
    `video_url` VARCHAR(500) DEFAULT NULL COMMENT '视频URL',
    `audio_url` VARCHAR(500) DEFAULT NULL COMMENT '音频URL',
    `duration` INT DEFAULT 0 COMMENT '时长（秒）',
    `is_free` TINYINT DEFAULT 1 COMMENT '是否免费：0-付费，1-免费',
    `is_trial` TINYINT DEFAULT 0 COMMENT '是否试看：0-否，1-是',
    `trial_duration` INT DEFAULT 0 COMMENT '试看时长（秒）',
    `sort_order` INT DEFAULT 0 COMMENT '排序顺序',
    `status` TINYINT DEFAULT 1 COMMENT '状态：0-禁用，1-启用',
    `created_at` DATETIME DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
    `updated_at` DATETIME DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
    `is_deleted` TINYINT DEFAULT 0 COMMENT '是否删除：0-未删除，1-已删除',
    PRIMARY KEY (`id`),
    KEY `idx_content_id` (`content_id`),
    KEY `idx_chapter_number` (`chapter_number`),
    KEY `idx_sort_order` (`sort_order`),
    KEY `idx_status` (`status`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci COMMENT='章节表';

-- 小节表
DROP TABLE IF EXISTS `cnt_section`;
CREATE TABLE `cnt_section` (
    `id` BIGINT NOT NULL COMMENT '小节ID',
    `chapter_id` BIGINT NOT NULL COMMENT '章节ID',
    `section_title` VARCHAR(200) NOT NULL COMMENT '小节标题',
    `section_number` INT NOT NULL COMMENT '小节序号',
    `description` TEXT DEFAULT NULL COMMENT '小节描述',
    `content` LONGTEXT DEFAULT NULL COMMENT '小节内容',
    `video_url` VARCHAR(500) DEFAULT NULL COMMENT '视频URL',
    `audio_url` VARCHAR(500) DEFAULT NULL COMMENT '音频URL',
    `duration` INT DEFAULT 0 COMMENT '时长（秒）',
    `is_free` TINYINT DEFAULT 1 COMMENT '是否免费：0-付费，1-免费',
    `is_trial` TINYINT DEFAULT 0 COMMENT '是否试看：0-否，1-是',
    `trial_duration` INT DEFAULT 0 COMMENT '试看时长（秒）',
    `sort_order` INT DEFAULT 0 COMMENT '排序顺序',
    `status` TINYINT DEFAULT 1 COMMENT '状态：0-禁用，1-启用',
    `created_at` DATETIME DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
    `updated_at` DATETIME DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
    `is_deleted` TINYINT DEFAULT 0 COMMENT '是否删除：0-未删除，1-已删除',
    PRIMARY KEY (`id`),
    KEY `idx_chapter_id` (`chapter_id`),
    KEY `idx_section_number` (`section_number`),
    KEY `idx_sort_order` (`sort_order`),
    KEY `idx_status` (`status`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci COMMENT='小节表';
