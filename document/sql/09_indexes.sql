-- =============================================
-- 索引优化脚本
-- 版本: 1.0.0
-- 创建时间: 2025-07-23
-- 描述: 为提高查询性能而创建的额外索引
-- =============================================

USE `jcode_wiki`;

-- =============================================
-- 用户相关表索引优化
-- =============================================

-- 用户表复合索引
ALTER TABLE `usr_user` ADD INDEX `idx_status_created` (`status`, `created_at`);
ALTER TABLE `usr_user` ADD INDEX `idx_email_verified` (`email_verified`, `status`);
ALTER TABLE `usr_user` ADD INDEX `idx_login_time` (`last_login_time` DESC);

-- 用户画像表索引
ALTER TABLE `usr_profile` ADD INDEX `idx_level_score` (`level`, `activity_score` DESC);
ALTER TABLE `usr_profile` ADD INDEX `idx_study_time` (`total_study_time` DESC);

-- 登录日志表索引
ALTER TABLE `usr_login_log` ADD INDEX `idx_user_time` (`user_id`, `created_at` DESC);
ALTER TABLE `usr_login_log` ADD INDEX `idx_ip_time` (`login_ip`, `created_at` DESC);

-- =============================================
-- 内容相关表索引优化
-- =============================================

-- 内容表复合索引
ALTER TABLE `cnt_content` ADD INDEX `idx_author_status_time` (`author_id`, `status`, `created_at` DESC);
ALTER TABLE `cnt_content` ADD INDEX `idx_category_status_time` (`category_id`, `status`, `published_at` DESC);
ALTER TABLE `cnt_content` ADD INDEX `idx_type_visibility_time` (`content_type`, `visibility`, `published_at` DESC);
ALTER TABLE `cnt_content` ADD INDEX `idx_hot_recommend_time` (`is_hot`, `is_recommend`, `published_at` DESC);
ALTER TABLE `cnt_content` ADD INDEX `idx_view_like_count` (`view_count` DESC, `like_count` DESC);
ALTER TABLE `cnt_content` ADD INDEX `idx_price_status` (`price`, `status`, `visibility`);

-- 内容分类表索引
ALTER TABLE `cnt_category` ADD INDEX `idx_parent_sort` (`parent_id`, `sort_order`);
ALTER TABLE `cnt_category` ADD INDEX `idx_level_status` (`level`, `status`);

-- 标签表索引
ALTER TABLE `cnt_tag` ADD INDEX `idx_usage_sort` (`usage_count` DESC, `sort_order`);

-- 内容版本表索引
ALTER TABLE `cnt_content_version` ADD INDEX `idx_content_time` (`content_id`, `created_at` DESC);

-- =============================================
-- 支付相关表索引优化
-- =============================================

-- 订单表复合索引
ALTER TABLE `pay_order` ADD INDEX `idx_user_status_time` (`user_id`, `order_status`, `created_at` DESC);
ALTER TABLE `pay_order` ADD INDEX `idx_status_time` (`order_status`, `created_at` DESC);
ALTER TABLE `pay_order` ADD INDEX `idx_payment_time` (`payment_status`, `paid_time` DESC);
ALTER TABLE `pay_order` ADD INDEX `idx_amount_time` (`actual_amount` DESC, `created_at` DESC);

-- 支付记录表索引
ALTER TABLE `pay_payment` ADD INDEX `idx_user_status_time` (`user_id`, `payment_status`, `created_at` DESC);
ALTER TABLE `pay_payment` ADD INDEX `idx_method_time` (`payment_method`, `created_at` DESC);

-- 优惠券表索引
ALTER TABLE `pay_coupon` ADD INDEX `idx_type_status_time` (`coupon_type`, `status`, `start_time`, `end_time`);
ALTER TABLE `pay_coupon` ADD INDEX `idx_time_range` (`start_time`, `end_time`);

-- 用户优惠券表索引
ALTER TABLE `pay_coupon_user` ADD INDEX `idx_user_status_expire` (`user_id`, `status`, `expire_time`);

-- 收益分成表索引
ALTER TABLE `pay_revenue` ADD INDEX `idx_author_status_time` (`author_id`, `status`, `created_at` DESC);
ALTER TABLE `pay_revenue` ADD INDEX `idx_content_time` (`content_id`, `created_at` DESC);

-- =============================================
-- 学习相关表索引优化
-- =============================================

-- 课程表索引
ALTER TABLE `lrn_course` ADD INDEX `idx_instructor_status_time` (`instructor_id`, `status`, `created_at` DESC);
ALTER TABLE `lrn_course` ADD INDEX `idx_category_type_time` (`category_id`, `course_type`, `created_at` DESC);
ALTER TABLE `lrn_course` ADD INDEX `idx_difficulty_rating` (`difficulty_level`, `rating` DESC);
ALTER TABLE `lrn_course` ADD INDEX `idx_recommend_time` (`is_recommend`, `created_at` DESC);

-- 学习进度表索引
ALTER TABLE `lrn_progress` ADD INDEX `idx_user_type_time` (`user_id`, `content_type`, `last_study_time` DESC);
ALTER TABLE `lrn_progress` ADD INDEX `idx_content_progress` (`content_id`, `progress_percent` DESC);
ALTER TABLE `lrn_progress` ADD INDEX `idx_completed_time` (`is_completed`, `completion_time` DESC);

-- 考试记录表索引
ALTER TABLE `lrn_exam_record` ADD INDEX `idx_user_exam_time` (`user_id`, `exam_id`, `start_time` DESC);
ALTER TABLE `lrn_exam_record` ADD INDEX `idx_exam_status_time` (`exam_id`, `exam_status`, `start_time` DESC);
ALTER TABLE `lrn_exam_record` ADD INDEX `idx_pass_score` (`pass_status`, `user_score` DESC);

-- 学习日志表索引
ALTER TABLE `lrn_study_log` ADD INDEX `idx_user_content_time` (`user_id`, `content_id`, `created_at` DESC);
ALTER TABLE `lrn_study_log` ADD INDEX `idx_action_time` (`action_type`, `created_at` DESC);

-- =============================================
-- 社交相关表索引优化
-- =============================================

-- 评论表索引
ALTER TABLE `soc_comment` ADD INDEX `idx_content_status_time` (`content_id`, `status`, `created_at` DESC);
ALTER TABLE `soc_comment` ADD INDEX `idx_user_time` (`user_id`, `created_at` DESC);
ALTER TABLE `soc_comment` ADD INDEX `idx_parent_time` (`parent_id`, `created_at` DESC);
ALTER TABLE `soc_comment` ADD INDEX `idx_hot_like` (`is_hot`, `like_count` DESC);

-- 点赞表索引
ALTER TABLE `soc_like` ADD INDEX `idx_target_type_time` (`target_id`, `target_type`, `created_at` DESC);
ALTER TABLE `soc_like` ADD INDEX `idx_user_time` (`user_id`, `created_at` DESC);

-- 收藏表索引
ALTER TABLE `soc_favorite` ADD INDEX `idx_user_folder_time` (`user_id`, `folder_name`, `created_at` DESC);
ALTER TABLE `soc_favorite` ADD INDEX `idx_content_time` (`content_id`, `created_at` DESC);

-- 关注表索引
ALTER TABLE `soc_follow` ADD INDEX `idx_follower_status_time` (`follower_id`, `status`, `created_at` DESC);
ALTER TABLE `soc_follow` ADD INDEX `idx_following_status_time` (`following_id`, `status`, `created_at` DESC);

-- 消息表索引
ALTER TABLE `soc_message` ADD INDEX `idx_receiver_read_time` (`receiver_id`, `is_read`, `created_at` DESC);
ALTER TABLE `soc_message` ADD INDEX `idx_sender_time` (`sender_id`, `created_at` DESC);
ALTER TABLE `soc_message` ADD INDEX `idx_type_time` (`message_type`, `created_at` DESC);

-- 通知表索引
ALTER TABLE `soc_notification` ADD INDEX `idx_user_read_time` (`user_id`, `is_read`, `created_at` DESC);
ALTER TABLE `soc_notification` ADD INDEX `idx_type_priority_time` (`notification_type`, `priority`, `created_at` DESC);

-- 分享记录表索引
ALTER TABLE `soc_share` ADD INDEX `idx_user_platform_time` (`user_id`, `share_platform`, `created_at` DESC);
ALTER TABLE `soc_share` ADD INDEX `idx_content_time` (`content_id`, `created_at` DESC);

-- 用户积分表索引
ALTER TABLE `soc_user_score` ADD INDEX `idx_user_type_time` (`user_id`, `score_type`, `created_at` DESC);
ALTER TABLE `soc_user_score` ADD INDEX `idx_source_time` (`score_source`, `created_at` DESC);

-- =============================================
-- 文件和系统相关表索引优化
-- =============================================

-- 文件表索引
ALTER TABLE `fil_file` ADD INDEX `idx_user_type_time` (`upload_user_id`, `file_type`, `created_at` DESC);
ALTER TABLE `fil_file` ADD INDEX `idx_type_size` (`file_type`, `file_size` DESC);
ALTER TABLE `fil_file` ADD INDEX `idx_storage_status` (`storage_type`, `status`);

-- 上传日志表索引
ALTER TABLE `fil_upload_log` ADD INDEX `idx_user_status_time` (`user_id`, `upload_status`, `created_at` DESC);

-- 操作日志表索引
ALTER TABLE `sys_operation_log` ADD INDEX `idx_user_module_time` (`user_id`, `module_name`, `operation_time` DESC);
ALTER TABLE `sys_operation_log` ADD INDEX `idx_operation_status_time` (`operation_type`, `operation_status`, `operation_time` DESC);
ALTER TABLE `sys_operation_log` ADD INDEX `idx_ip_time` (`ip_address`, `operation_time` DESC);

-- 统计数据表索引
ALTER TABLE `sys_statistics` ADD INDEX `idx_date_type_key` (`stat_date` DESC, `stat_type`, `stat_key`);
ALTER TABLE `sys_statistics` ADD INDEX `idx_type_date` (`stat_type`, `stat_date` DESC);

-- =============================================
-- 性能优化建议
-- =============================================

-- 设置查询缓存（如果MySQL版本支持）
-- SET GLOBAL query_cache_size = 268435456; -- 256MB
-- SET GLOBAL query_cache_type = ON;

-- 优化InnoDB缓冲池大小（建议设置为物理内存的70-80%）
-- SET GLOBAL innodb_buffer_pool_size = 1073741824; -- 1GB

-- 显示索引创建完成信息
SELECT 
    '索引优化完成' as message,
    COUNT(*) as total_indexes
FROM information_schema.statistics 
WHERE table_schema = 'jcode_wiki';
