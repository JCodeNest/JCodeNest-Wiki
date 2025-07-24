-- =============================================
-- JCodeNest-Wiki 数据库初始化脚本
-- 版本: 1.0.0
-- 创建时间: 2025-07-23
-- 描述: 创建数据库并设置字符集和排序规则
-- =============================================

-- 创建数据库
DROP DATABASE IF EXISTS `jcode_wiki`;
CREATE DATABASE `jcode_wiki`
    DEFAULT CHARACTER SET utf8mb4 
    DEFAULT COLLATE utf8mb4_unicode_ci;

-- 使用数据库
USE `jcode_wiki`;

-- 设置SQL模式
SET sql_mode = 'STRICT_TRANS_TABLES,NO_ZERO_DATE,NO_ZERO_IN_DATE,ERROR_FOR_DIVISION_BY_ZERO';

-- 设置时区
SET time_zone = '+8:00';

-- 显示数据库信息
SELECT 
    SCHEMA_NAME as '数据库名',
    DEFAULT_CHARACTER_SET_NAME as '字符集',
    DEFAULT_COLLATION_NAME as '排序规则'
FROM information_schema.SCHEMATA 
WHERE SCHEMA_NAME = 'jcode_wiki';
