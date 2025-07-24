-- =============================================
-- 文件管理和系统管理模块表结构 (fil_, sys_)
-- 版本: 1.0.0
-- 创建时间: 2025-07-23
-- 描述: 文件管理和系统管理相关的所有表结构
-- =============================================

USE `jcode_wiki`;

-- =============================================
-- 文件管理模块 (fil_)
-- =============================================

-- 文件信息表
DROP TABLE IF EXISTS `fil_file`;
CREATE TABLE `fil_file` (
    `id` BIGINT NOT NULL COMMENT '文件ID，雪花算法生成',
    `file_name` VARCHAR(255) NOT NULL COMMENT '文件名称',
    `original_name` VARCHAR(255) NOT NULL COMMENT '原始文件名',
    `file_path` VARCHAR(500) NOT NULL COMMENT '文件路径',
    `file_url` VARCHAR(500) NOT NULL COMMENT '文件访问URL',
    `file_type` VARCHAR(50) NOT NULL COMMENT '文件类型：image-图片，video-视频，audio-音频，document-文档',
    `mime_type` VARCHAR(100) NOT NULL COMMENT 'MIME类型',
    `file_extension` VARCHAR(20) NOT NULL COMMENT '文件扩展名',
    `file_size` BIGINT NOT NULL COMMENT '文件大小（字节）',
    `file_md5` VARCHAR(32) NOT NULL COMMENT '文件MD5值',
    `file_sha1` VARCHAR(40) DEFAULT NULL COMMENT '文件SHA1值',
    `upload_user_id` BIGINT NOT NULL COMMENT '上传用户ID',
    `storage_type` VARCHAR(50) DEFAULT 'local' COMMENT '存储类型：local-本地，oss-对象存储，cdn-CDN',
    `bucket_name` VARCHAR(100) DEFAULT NULL COMMENT '存储桶名称',
    `width` INT DEFAULT NULL COMMENT '图片宽度（像素）',
    `height` INT DEFAULT NULL COMMENT '图片高度（像素）',
    `duration` INT DEFAULT NULL COMMENT '音视频时长（秒）',
    `thumbnail_url` VARCHAR(500) DEFAULT NULL COMMENT '缩略图URL',
    `download_count` INT DEFAULT 0 COMMENT '下载次数',
    `is_public` TINYINT DEFAULT 1 COMMENT '是否公开：0-私有，1-公开',
    `status` TINYINT DEFAULT 1 COMMENT '状态：0-已删除，1-正常，2-审核中，3-审核失败',
    `created_at` DATETIME DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
    `updated_at` DATETIME DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
    `is_deleted` TINYINT DEFAULT 0 COMMENT '是否删除：0-未删除，1-已删除',
    PRIMARY KEY (`id`),
    UNIQUE KEY `uk_file_md5` (`file_md5`),
    KEY `idx_upload_user_id` (`upload_user_id`),
    KEY `idx_file_type` (`file_type`),
    KEY `idx_mime_type` (`mime_type`),
    KEY `idx_storage_type` (`storage_type`),
    KEY `idx_status` (`status`),
    KEY `idx_created_at` (`created_at`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci COMMENT='文件信息表';

-- 上传日志表
DROP TABLE IF EXISTS `fil_upload_log`;
CREATE TABLE `fil_upload_log` (
    `id` BIGINT NOT NULL COMMENT '上传日志ID',
    `file_id` BIGINT DEFAULT NULL COMMENT '文件ID',
    `user_id` BIGINT NOT NULL COMMENT '上传用户ID',
    `original_name` VARCHAR(255) NOT NULL COMMENT '原始文件名',
    `file_size` BIGINT NOT NULL COMMENT '文件大小',
    `upload_status` TINYINT DEFAULT 1 COMMENT '上传状态：1-上传中，2-上传成功，3-上传失败',
    `upload_progress` DECIMAL(5,2) DEFAULT 0.00 COMMENT '上传进度（百分比）',
    `error_message` TEXT DEFAULT NULL COMMENT '错误信息',
    `ip_address` VARCHAR(50) DEFAULT NULL COMMENT 'IP地址',
    `user_agent` TEXT DEFAULT NULL COMMENT '用户代理',
    `upload_time` DATETIME DEFAULT NULL COMMENT '上传完成时间',
    `created_at` DATETIME DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
    `updated_at` DATETIME DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
    PRIMARY KEY (`id`),
    KEY `idx_file_id` (`file_id`),
    KEY `idx_user_id` (`user_id`),
    KEY `idx_upload_status` (`upload_status`),
    KEY `idx_created_at` (`created_at`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci COMMENT='上传日志表';

-- =============================================
-- 系统管理模块 (sys_)
-- =============================================

-- 系统配置表
DROP TABLE IF EXISTS `sys_config`;
CREATE TABLE `sys_config` (
    `id` BIGINT NOT NULL COMMENT '配置ID',
    `config_key` VARCHAR(100) NOT NULL COMMENT '配置键',
    `config_value` TEXT DEFAULT NULL COMMENT '配置值',
    `config_type` VARCHAR(50) DEFAULT 'string' COMMENT '配置类型：string-字符串，number-数字，boolean-布尔，json-JSON',
    `config_group` VARCHAR(50) DEFAULT 'default' COMMENT '配置分组',
    `config_name` VARCHAR(100) NOT NULL COMMENT '配置名称',
    `description` TEXT DEFAULT NULL COMMENT '配置描述',
    `is_system` TINYINT DEFAULT 0 COMMENT '是否系统配置：0-否，1-是',
    `is_public` TINYINT DEFAULT 0 COMMENT '是否公开配置：0-否，1-是',
    `sort_order` INT DEFAULT 0 COMMENT '排序顺序',
    `status` TINYINT DEFAULT 1 COMMENT '状态：0-禁用，1-启用',
    `created_at` DATETIME DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
    `updated_at` DATETIME DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
    `is_deleted` TINYINT DEFAULT 0 COMMENT '是否删除：0-未删除，1-已删除',
    PRIMARY KEY (`id`),
    UNIQUE KEY `uk_config_key` (`config_key`),
    KEY `idx_config_group` (`config_group`),
    KEY `idx_is_system` (`is_system`),
    KEY `idx_is_public` (`is_public`),
    KEY `idx_status` (`status`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci COMMENT='系统配置表';

-- 数据字典表
DROP TABLE IF EXISTS `sys_dict`;
CREATE TABLE `sys_dict` (
    `id` BIGINT NOT NULL COMMENT '字典ID',
    `dict_type` VARCHAR(50) NOT NULL COMMENT '字典类型',
    `dict_key` VARCHAR(100) NOT NULL COMMENT '字典键',
    `dict_value` VARCHAR(200) NOT NULL COMMENT '字典值',
    `dict_label` VARCHAR(100) NOT NULL COMMENT '字典标签',
    `description` TEXT DEFAULT NULL COMMENT '描述',
    `sort_order` INT DEFAULT 0 COMMENT '排序顺序',
    `css_class` VARCHAR(100) DEFAULT NULL COMMENT 'CSS样式类',
    `list_class` VARCHAR(100) DEFAULT NULL COMMENT '列表样式类',
    `is_default` TINYINT DEFAULT 0 COMMENT '是否默认：0-否，1-是',
    `status` TINYINT DEFAULT 1 COMMENT '状态：0-禁用，1-启用',
    `created_at` DATETIME DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
    `updated_at` DATETIME DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
    `is_deleted` TINYINT DEFAULT 0 COMMENT '是否删除：0-未删除，1-已删除',
    PRIMARY KEY (`id`),
    UNIQUE KEY `uk_dict_type_key` (`dict_type`, `dict_key`),
    KEY `idx_dict_type` (`dict_type`),
    KEY `idx_sort_order` (`sort_order`),
    KEY `idx_status` (`status`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci COMMENT='数据字典表';

-- 操作日志表
DROP TABLE IF EXISTS `sys_operation_log`;
CREATE TABLE `sys_operation_log` (
    `id` BIGINT NOT NULL COMMENT '日志ID',
    `user_id` BIGINT DEFAULT NULL COMMENT '操作用户ID',
    `username` VARCHAR(50) DEFAULT NULL COMMENT '用户名',
    `operation_type` VARCHAR(50) NOT NULL COMMENT '操作类型：CREATE-创建，UPDATE-更新，DELETE-删除，LOGIN-登录等',
    `module_name` VARCHAR(50) NOT NULL COMMENT '模块名称',
    `business_type` VARCHAR(50) DEFAULT NULL COMMENT '业务类型',
    `method_name` VARCHAR(100) DEFAULT NULL COMMENT '方法名称',
    `request_method` VARCHAR(10) DEFAULT NULL COMMENT '请求方式：GET、POST、PUT、DELETE',
    `request_url` VARCHAR(500) DEFAULT NULL COMMENT '请求URL',
    `request_params` TEXT DEFAULT NULL COMMENT '请求参数',
    `response_data` TEXT DEFAULT NULL COMMENT '响应数据',
    `operation_status` TINYINT DEFAULT 1 COMMENT '操作状态：0-失败，1-成功',
    `error_message` TEXT DEFAULT NULL COMMENT '错误信息',
    `operation_time` DATETIME DEFAULT CURRENT_TIMESTAMP COMMENT '操作时间',
    `cost_time` BIGINT DEFAULT 0 COMMENT '消耗时间（毫秒）',
    `ip_address` VARCHAR(50) DEFAULT NULL COMMENT 'IP地址',
    `location` VARCHAR(100) DEFAULT NULL COMMENT '操作地点',
    `user_agent` TEXT DEFAULT NULL COMMENT '用户代理',
    `created_at` DATETIME DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
    PRIMARY KEY (`id`),
    KEY `idx_user_id` (`user_id`),
    KEY `idx_operation_type` (`operation_type`),
    KEY `idx_module_name` (`module_name`),
    KEY `idx_operation_status` (`operation_status`),
    KEY `idx_operation_time` (`operation_time`),
    KEY `idx_ip_address` (`ip_address`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci COMMENT='操作日志表';

-- 统计数据表
DROP TABLE IF EXISTS `sys_statistics`;
CREATE TABLE `sys_statistics` (
    `id` BIGINT NOT NULL COMMENT '统计ID',
    `stat_date` DATE NOT NULL COMMENT '统计日期',
    `stat_type` VARCHAR(50) NOT NULL COMMENT '统计类型：user-用户，content-内容，order-订单等',
    `stat_key` VARCHAR(100) NOT NULL COMMENT '统计键',
    `stat_value` BIGINT DEFAULT 0 COMMENT '统计值',
    `stat_extra` JSON DEFAULT NULL COMMENT '扩展统计数据（JSON格式）',
    `created_at` DATETIME DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
    `updated_at` DATETIME DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
    PRIMARY KEY (`id`),
    UNIQUE KEY `uk_date_type_key` (`stat_date`, `stat_type`, `stat_key`),
    KEY `idx_stat_date` (`stat_date`),
    KEY `idx_stat_type` (`stat_type`),
    KEY `idx_stat_key` (`stat_key`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci COMMENT='统计数据表';
