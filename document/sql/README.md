# JCodeNest-Wiki 数据库设计文档

## 概述

本文档描述了 JCodeNest-Wiki 知识管理平台的完整数据库设计，包含45张表，覆盖用户管理、内容管理、支付交易、学习管理、社交互动、文件管理和系统管理等核心业务模块。

## 设计原则

### 1. 命名规范
- **表名**：采用业务前缀 + 模块 + 实体的三段式命名，如 `usr_user`、`cnt_content`
- **字段名**：使用下划线分隔的小写英文，如 `user_id`、`created_at`
- **索引名**：使用 `idx_` 前缀，如 `idx_user_id`
- **唯一索引**：使用 `uk_` 前缀，如 `uk_username`

### 2. 数据类型规范
- **主键**：统一使用 `BIGINT` 类型，雪花算法生成
- **时间字段**：使用 `DATETIME` 类型，默认值 `CURRENT_TIMESTAMP`
- **状态字段**：使用 `TINYINT` 类型表示枚举值
- **金额字段**：使用 `DECIMAL(10,2)` 类型
- **文本内容**：根据长度使用 `VARCHAR`、`TEXT`、`LONGTEXT`

### 3. 标准字段
每张表都包含以下标准字段：
- `id`：主键，BIGINT类型
- `created_at`：创建时间，DATETIME类型
- `updated_at`：更新时间，DATETIME类型
- `is_deleted`：软删除标记，TINYINT类型

### 4. 外键设计
- 使用逻辑外键，不设置物理外键约束
- 外键字段命名为 `关联表_id`，如 `user_id`、`content_id`

## 模块设计

### 用户管理模块 (usr_)

| 表名 | 说明 | 主要字段 |
|------|------|----------|
| usr_user | 用户基础信息表 | username, email, password, nickname |
| usr_role | 角色定义表 | role_code, role_name, description |
| usr_user_role | 用户角色关联表 | user_id, role_id |
| usr_permission | 权限定义表 | permission_code, permission_name, resource_path |
| usr_role_permission | 角色权限关联表 | role_id, permission_id |
| usr_profile | 用户画像表 | total_study_time, activity_score, level |
| usr_login_log | 登录日志表 | login_ip, login_status, user_agent |
| usr_third_auth | 第三方登录表 | provider, open_id, access_token |

### 内容管理模块 (cnt_)

| 表名 | 说明 | 主要字段 |
|------|------|----------|
| cnt_content | 内容主表 | title, content, author_id, status, price |
| cnt_category | 内容分类表 | category_name, parent_id, level, path |
| cnt_tag | 标签表 | tag_name, color, usage_count |
| cnt_content_tag | 内容标签关联表 | content_id, tag_id |
| cnt_content_version | 内容版本表 | content_id, version_number, change_log |
| cnt_content_audit | 内容审核表 | content_id, audit_status, auditor_id |
| cnt_chapter | 章节表 | content_id, chapter_title, video_url |
| cnt_section | 小节表 | chapter_id, section_title, duration |

### 支付交易模块 (pay_)

| 表名 | 说明 | 主要字段 |
|------|------|----------|
| pay_order | 订单主表 | order_no, user_id, total_amount, order_status |
| pay_order_item | 订单明细表 | order_id, content_id, unit_price, quantity |
| pay_payment | 支付记录表 | payment_no, order_id, payment_method, payment_status |
| pay_refund | 退款记录表 | refund_no, order_id, refund_amount, refund_status |
| pay_coupon | 优惠券表 | coupon_code, coupon_type, discount_value |
| pay_coupon_user | 用户优惠券表 | user_id, coupon_id, status, expire_time |
| pay_revenue | 收益分成表 | order_id, author_id, author_amount, platform_amount |
| pay_withdraw | 提现记录表 | withdraw_no, user_id, withdraw_amount, withdraw_status |

### 学习管理模块 (lrn_)

| 表名 | 说明 | 主要字段 |
|------|------|----------|
| lrn_course | 课程表 | course_title, instructor_id, difficulty_level, price |
| lrn_progress | 学习进度表 | user_id, content_id, progress_percent, study_duration |
| lrn_exam | 考试表 | exam_title, total_questions, pass_score, time_limit |
| lrn_question | 题目表 | exam_id, question_type, question_title, score |
| lrn_answer | 答案表 | question_id, answer_content, is_correct |
| lrn_exam_record | 考试记录表 | exam_id, user_id, user_score, pass_status |
| lrn_study_log | 学习日志表 | user_id, content_id, action_type, study_duration |

### 社交互动模块 (soc_)

| 表名 | 说明 | 主要字段 |
|------|------|----------|
| soc_comment | 评论表 | content_id, user_id, parent_id, comment_content |
| soc_like | 点赞表 | user_id, target_id, target_type, like_type |
| soc_favorite | 收藏表 | user_id, content_id, folder_name, tags |
| soc_follow | 关注表 | follower_id, following_id, follow_type, status |
| soc_message | 消息表 | sender_id, receiver_id, message_type, content |
| soc_notification | 通知表 | user_id, notification_type, title, content |
| soc_share | 分享记录表 | user_id, content_id, share_platform, share_url |
| soc_user_score | 用户积分表 | user_id, score_type, score_change, current_score |

### 文件管理模块 (fil_)

| 表名 | 说明 | 主要字段 |
|------|------|----------|
| fil_file | 文件信息表 | file_name, file_path, file_type, file_size, file_md5 |
| fil_upload_log | 上传日志表 | file_id, user_id, upload_status, upload_progress |

### 系统管理模块 (sys_)

| 表名 | 说明 | 主要字段 |
|------|------|----------|
| sys_config | 系统配置表 | config_key, config_value, config_type, config_group |
| sys_dict | 数据字典表 | dict_type, dict_key, dict_value, dict_label |
| sys_operation_log | 操作日志表 | user_id, operation_type, module_name, request_url |
| sys_statistics | 统计数据表 | stat_date, stat_type, stat_key, stat_value |

## 索引设计

### 主要索引类型
1. **主键索引**：每张表的 `id` 字段
2. **唯一索引**：用户名、邮箱、订单号等唯一字段
3. **普通索引**：外键字段、状态字段、时间字段
4. **复合索引**：常用查询条件的组合
5. **全文索引**：内容表的标题和正文字段

### 索引优化策略
- 根据查询频率和数据量大小设计索引
- 避免过多索引影响写入性能
- 定期分析慢查询日志优化索引

## 数据库配置

### 字符集设置
```sql
CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci
```

### 存储引擎
- 统一使用 InnoDB 存储引擎
- 支持事务、外键约束、行级锁定

### 性能优化参数
```sql
innodb_buffer_pool_size = 1G        # 缓冲池大小
innodb_log_file_size = 256M         # 日志文件大小
innodb_flush_log_at_trx_commit = 2  # 日志刷新策略
query_cache_size = 256M             # 查询缓存大小
```

## 使用说明

### 1. 环境要求
- MySQL 8.0 或以上版本
- 支持 utf8mb4 字符集
- 建议内存 4GB 以上

### 2. 安装步骤
```bash
# 1. 登录MySQL
mysql -u root -p

# 2. 执行完整脚本
source /path/to/document/sql/00_execute_all.sql

# 3. 验证安装
USE jcodenest_wiki;
SHOW TABLES;
```

### 3. 初始数据
系统会自动创建以下初始数据：
- 5个用户角色（游客、注册用户、VIP用户、内容创作者、管理员）
- 完整的权限体系
- 内容分类和标签
- 系统配置参数
- 数据字典

## 维护建议

### 1. 备份策略
- 每日全量备份
- 每小时增量备份
- 重要操作前手动备份

### 2. 监控指标
- 数据库连接数
- 查询响应时间
- 磁盘空间使用率
- 慢查询日志

### 3. 优化建议
- 定期分析表结构和索引使用情况
- 监控慢查询并优化
- 根据业务增长调整配置参数
- 考虑读写分离和分库分表

## 版本历史

| 版本 | 日期 | 说明 |
|------|------|------|
| 1.0.0 | 2024-01-01 | 初始版本，包含45张表的完整设计 |

## 联系方式

如有问题或建议，请联系开发团队。
