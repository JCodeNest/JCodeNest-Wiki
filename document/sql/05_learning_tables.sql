-- =============================================
-- 学习管理模块表结构 (lrn_)
-- 版本: 1.0.0
-- 创建时间: 2025-07-23
-- 描述: 学习管理相关的所有表结构
-- =============================================

USE `jcode_wiki`;

-- 课程表
DROP TABLE IF EXISTS `lrn_course`;
CREATE TABLE `lrn_course` (
    `id` BIGINT NOT NULL COMMENT '课程ID，雪花算法生成',
    `course_title` VARCHAR(200) NOT NULL COMMENT '课程标题',
    `course_subtitle` VARCHAR(300) DEFAULT NULL COMMENT '课程副标题',
    `description` TEXT DEFAULT NULL COMMENT '课程描述',
    `cover_image` VARCHAR(500) DEFAULT NULL COMMENT '课程封面图',
    `instructor_id` BIGINT NOT NULL COMMENT '讲师用户ID',
    `category_id` BIGINT DEFAULT NULL COMMENT '课程分类ID',
    `difficulty_level` TINYINT DEFAULT 1 COMMENT '难度等级：1-入门，2-进阶，3-高级，4-专家',
    `course_type` TINYINT DEFAULT 1 COMMENT '课程类型：1-免费课程，2-付费课程，3-VIP课程',
    `price` DECIMAL(10,2) DEFAULT 0.00 COMMENT '课程价格',
    `original_price` DECIMAL(10,2) DEFAULT 0.00 COMMENT '课程原价',
    `total_duration` INT DEFAULT 0 COMMENT '总时长（分钟）',
    `chapter_count` INT DEFAULT 0 COMMENT '章节数量',
    `student_count` INT DEFAULT 0 COMMENT '学员数量',
    `completion_rate` DECIMAL(5,2) DEFAULT 0.00 COMMENT '完成率（百分比）',
    `rating` DECIMAL(3,2) DEFAULT 0.00 COMMENT '评分（1-5分）',
    `rating_count` INT DEFAULT 0 COMMENT '评分人数',
    `prerequisites` TEXT DEFAULT NULL COMMENT '前置要求',
    `learning_objectives` TEXT DEFAULT NULL COMMENT '学习目标',
    `target_audience` TEXT DEFAULT NULL COMMENT '目标受众',
    `status` TINYINT DEFAULT 1 COMMENT '状态：0-草稿，1-已发布，2-已下架',
    `is_recommend` TINYINT DEFAULT 0 COMMENT '是否推荐：0-否，1-是',
    `sort_order` INT DEFAULT 0 COMMENT '排序顺序',
    `created_at` DATETIME DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
    `updated_at` DATETIME DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
    `is_deleted` TINYINT DEFAULT 0 COMMENT '是否删除：0-未删除，1-已删除',
    PRIMARY KEY (`id`),
    KEY `idx_instructor_id` (`instructor_id`),
    KEY `idx_category_id` (`category_id`),
    KEY `idx_difficulty_level` (`difficulty_level`),
    KEY `idx_course_type` (`course_type`),
    KEY `idx_status` (`status`),
    KEY `idx_is_recommend` (`is_recommend`),
    KEY `idx_created_at` (`created_at`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci COMMENT='课程表';

-- 学习进度表
DROP TABLE IF EXISTS `lrn_progress`;
CREATE TABLE `lrn_progress` (
    `id` BIGINT NOT NULL COMMENT '进度记录ID',
    `user_id` BIGINT NOT NULL COMMENT '用户ID',
    `content_id` BIGINT NOT NULL COMMENT '内容ID（课程、文章等）',
    `content_type` VARCHAR(50) NOT NULL COMMENT '内容类型：course-课程，article-文章',
    `chapter_id` BIGINT DEFAULT NULL COMMENT '章节ID',
    `section_id` BIGINT DEFAULT NULL COMMENT '小节ID',
    `progress_percent` DECIMAL(5,2) DEFAULT 0.00 COMMENT '进度百分比',
    `study_duration` INT DEFAULT 0 COMMENT '学习时长（分钟）',
    `last_position` INT DEFAULT 0 COMMENT '最后学习位置（秒）',
    `is_completed` TINYINT DEFAULT 0 COMMENT '是否完成：0-未完成，1-已完成',
    `completion_time` DATETIME DEFAULT NULL COMMENT '完成时间',
    `last_study_time` DATETIME DEFAULT NULL COMMENT '最后学习时间',
    `study_count` INT DEFAULT 0 COMMENT '学习次数',
    `created_at` DATETIME DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
    `updated_at` DATETIME DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
    `is_deleted` TINYINT DEFAULT 0 COMMENT '是否删除：0-未删除，1-已删除',
    PRIMARY KEY (`id`),
    UNIQUE KEY `uk_user_content` (`user_id`, `content_id`, `chapter_id`, `section_id`),
    KEY `idx_user_id` (`user_id`),
    KEY `idx_content_id` (`content_id`),
    KEY `idx_content_type` (`content_type`),
    KEY `idx_is_completed` (`is_completed`),
    KEY `idx_last_study_time` (`last_study_time`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci COMMENT='学习进度表';

-- 考试表
DROP TABLE IF EXISTS `lrn_exam`;
CREATE TABLE `lrn_exam` (
    `id` BIGINT NOT NULL COMMENT '考试ID',
    `exam_title` VARCHAR(200) NOT NULL COMMENT '考试标题',
    `description` TEXT DEFAULT NULL COMMENT '考试描述',
    `content_id` BIGINT DEFAULT NULL COMMENT '关联内容ID',
    `exam_type` TINYINT DEFAULT 1 COMMENT '考试类型：1-练习，2-测试，3-考试',
    `difficulty_level` TINYINT DEFAULT 1 COMMENT '难度等级：1-简单，2-中等，3-困难',
    `total_questions` INT NOT NULL COMMENT '题目总数',
    `total_score` INT NOT NULL COMMENT '总分',
    `pass_score` INT NOT NULL COMMENT '及格分数',
    `time_limit` INT DEFAULT 0 COMMENT '考试时长（分钟），0表示不限时',
    `attempt_limit` INT DEFAULT 0 COMMENT '答题次数限制，0表示不限制',
    `is_random` TINYINT DEFAULT 0 COMMENT '是否随机出题：0-否，1-是',
    `show_answer` TINYINT DEFAULT 1 COMMENT '是否显示答案：0-否，1-是',
    `show_score` TINYINT DEFAULT 1 COMMENT '是否显示分数：0-否，1-是',
    `status` TINYINT DEFAULT 1 COMMENT '状态：0-禁用，1-启用',
    `created_at` DATETIME DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
    `updated_at` DATETIME DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
    `is_deleted` TINYINT DEFAULT 0 COMMENT '是否删除：0-未删除，1-已删除',
    PRIMARY KEY (`id`),
    KEY `idx_content_id` (`content_id`),
    KEY `idx_exam_type` (`exam_type`),
    KEY `idx_difficulty_level` (`difficulty_level`),
    KEY `idx_status` (`status`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci COMMENT='考试表';

-- 题目表
DROP TABLE IF EXISTS `lrn_question`;
CREATE TABLE `lrn_question` (
    `id` BIGINT NOT NULL COMMENT '题目ID',
    `exam_id` BIGINT NOT NULL COMMENT '考试ID',
    `question_type` TINYINT NOT NULL COMMENT '题目类型：1-单选，2-多选，3-判断，4-填空，5-简答，6-编程',
    `question_title` TEXT NOT NULL COMMENT '题目标题',
    `question_content` LONGTEXT DEFAULT NULL COMMENT '题目内容',
    `question_image` VARCHAR(500) DEFAULT NULL COMMENT '题目图片',
    `difficulty_level` TINYINT DEFAULT 1 COMMENT '难度等级：1-简单，2-中等，3-困难',
    `score` INT DEFAULT 1 COMMENT '题目分值',
    `sort_order` INT DEFAULT 0 COMMENT '排序顺序',
    `explanation` TEXT DEFAULT NULL COMMENT '题目解析',
    `created_at` DATETIME DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
    `updated_at` DATETIME DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
    `is_deleted` TINYINT DEFAULT 0 COMMENT '是否删除：0-未删除，1-已删除',
    PRIMARY KEY (`id`),
    KEY `idx_exam_id` (`exam_id`),
    KEY `idx_question_type` (`question_type`),
    KEY `idx_difficulty_level` (`difficulty_level`),
    KEY `idx_sort_order` (`sort_order`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci COMMENT='题目表';

-- 答案表
DROP TABLE IF EXISTS `lrn_answer`;
CREATE TABLE `lrn_answer` (
    `id` BIGINT NOT NULL COMMENT '答案ID',
    `question_id` BIGINT NOT NULL COMMENT '题目ID',
    `answer_content` TEXT NOT NULL COMMENT '答案内容',
    `answer_image` VARCHAR(500) DEFAULT NULL COMMENT '答案图片',
    `is_correct` TINYINT DEFAULT 0 COMMENT '是否正确答案：0-否，1-是',
    `sort_order` INT DEFAULT 0 COMMENT '排序顺序',
    `created_at` DATETIME DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
    `updated_at` DATETIME DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
    `is_deleted` TINYINT DEFAULT 0 COMMENT '是否删除：0-未删除，1-已删除',
    PRIMARY KEY (`id`),
    KEY `idx_question_id` (`question_id`),
    KEY `idx_is_correct` (`is_correct`),
    KEY `idx_sort_order` (`sort_order`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci COMMENT='答案表';

-- 考试记录表
DROP TABLE IF EXISTS `lrn_exam_record`;
CREATE TABLE `lrn_exam_record` (
    `id` BIGINT NOT NULL COMMENT '考试记录ID',
    `exam_id` BIGINT NOT NULL COMMENT '考试ID',
    `user_id` BIGINT NOT NULL COMMENT '用户ID',
    `start_time` DATETIME NOT NULL COMMENT '开始时间',
    `end_time` DATETIME DEFAULT NULL COMMENT '结束时间',
    `duration` INT DEFAULT 0 COMMENT '答题时长（分钟）',
    `total_questions` INT NOT NULL COMMENT '题目总数',
    `answered_questions` INT DEFAULT 0 COMMENT '已答题数',
    `correct_answers` INT DEFAULT 0 COMMENT '正确答案数',
    `total_score` INT DEFAULT 0 COMMENT '总分',
    `user_score` INT DEFAULT 0 COMMENT '用户得分',
    `pass_status` TINYINT DEFAULT 0 COMMENT '通过状态：0-未通过，1-已通过',
    `exam_status` TINYINT DEFAULT 1 COMMENT '考试状态：1-进行中，2-已完成，3-已超时',
    `answer_data` JSON DEFAULT NULL COMMENT '答题数据（JSON格式）',
    `created_at` DATETIME DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
    `updated_at` DATETIME DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
    `is_deleted` TINYINT DEFAULT 0 COMMENT '是否删除：0-未删除，1-已删除',
    PRIMARY KEY (`id`),
    KEY `idx_exam_id` (`exam_id`),
    KEY `idx_user_id` (`user_id`),
    KEY `idx_exam_status` (`exam_status`),
    KEY `idx_pass_status` (`pass_status`),
    KEY `idx_start_time` (`start_time`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci COMMENT='考试记录表';

-- 学习日志表
DROP TABLE IF EXISTS `lrn_study_log`;
CREATE TABLE `lrn_study_log` (
    `id` BIGINT NOT NULL COMMENT '日志ID',
    `user_id` BIGINT NOT NULL COMMENT '用户ID',
    `content_id` BIGINT NOT NULL COMMENT '内容ID',
    `content_type` VARCHAR(50) NOT NULL COMMENT '内容类型',
    `action_type` VARCHAR(50) NOT NULL COMMENT '操作类型：start-开始学习，pause-暂停，resume-继续，complete-完成',
    `study_duration` INT DEFAULT 0 COMMENT '本次学习时长（秒）',
    `current_position` INT DEFAULT 0 COMMENT '当前位置（秒）',
    `device_type` VARCHAR(50) DEFAULT NULL COMMENT '设备类型',
    `ip_address` VARCHAR(50) DEFAULT NULL COMMENT 'IP地址',
    `user_agent` TEXT DEFAULT NULL COMMENT '用户代理',
    `created_at` DATETIME DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
    PRIMARY KEY (`id`),
    KEY `idx_user_id` (`user_id`),
    KEY `idx_content_id` (`content_id`),
    KEY `idx_action_type` (`action_type`),
    KEY `idx_created_at` (`created_at`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci COMMENT='学习日志表';
