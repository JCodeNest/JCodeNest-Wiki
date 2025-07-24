-- =============================================
-- 支付交易模块表结构 (pay_)
-- 版本: 1.0.0
-- 创建时间: 2025-07-23
-- 描述: 支付交易相关的所有表结构
-- =============================================

USE `jcode_wiki`;

-- 订单主表
DROP TABLE IF EXISTS `pay_order`;
CREATE TABLE `pay_order` (
    `id` BIGINT NOT NULL COMMENT '订单ID，雪花算法生成',
    `order_no` VARCHAR(32) NOT NULL COMMENT '订单号，唯一标识',
    `user_id` BIGINT NOT NULL COMMENT '用户ID',
    `order_type` TINYINT DEFAULT 1 COMMENT '订单类型：1-内容购买，2-VIP订阅，3-积分充值',
    `order_status` TINYINT DEFAULT 1 COMMENT '订单状态：1-待支付，2-已支付，3-已取消，4-已退款，5-部分退款',
    `payment_status` TINYINT DEFAULT 0 COMMENT '支付状态：0-未支付，1-支付中，2-支付成功，3-支付失败',
    `total_amount` DECIMAL(10,2) NOT NULL COMMENT '订单总金额',
    `discount_amount` DECIMAL(10,2) DEFAULT 0.00 COMMENT '优惠金额',
    `actual_amount` DECIMAL(10,2) NOT NULL COMMENT '实际支付金额',
    `currency` VARCHAR(10) DEFAULT 'CNY' COMMENT '货币类型',
    `payment_method` VARCHAR(50) DEFAULT NULL COMMENT '支付方式：alipay、wechat、bank_card等',
    `payment_channel` VARCHAR(50) DEFAULT NULL COMMENT '支付渠道',
    `coupon_id` BIGINT DEFAULT NULL COMMENT '使用的优惠券ID',
    `remark` TEXT DEFAULT NULL COMMENT '订单备注',
    `expire_time` DATETIME DEFAULT NULL COMMENT '订单过期时间',
    `paid_time` DATETIME DEFAULT NULL COMMENT '支付时间',
    `cancel_time` DATETIME DEFAULT NULL COMMENT '取消时间',
    `cancel_reason` VARCHAR(200) DEFAULT NULL COMMENT '取消原因',
    `created_at` DATETIME DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
    `updated_at` DATETIME DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
    `is_deleted` TINYINT DEFAULT 0 COMMENT '是否删除：0-未删除，1-已删除',
    PRIMARY KEY (`id`),
    UNIQUE KEY `uk_order_no` (`order_no`),
    KEY `idx_user_id` (`user_id`),
    KEY `idx_order_status` (`order_status`),
    KEY `idx_payment_status` (`payment_status`),
    KEY `idx_created_at` (`created_at`),
    KEY `idx_paid_time` (`paid_time`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci COMMENT='订单主表';

-- 订单明细表
DROP TABLE IF EXISTS `pay_order_item`;
CREATE TABLE `pay_order_item` (
    `id` BIGINT NOT NULL COMMENT '明细ID',
    `order_id` BIGINT NOT NULL COMMENT '订单ID',
    `content_id` BIGINT NOT NULL COMMENT '内容ID',
    `content_title` VARCHAR(200) NOT NULL COMMENT '内容标题',
    `content_type` VARCHAR(50) NOT NULL COMMENT '内容类型',
    `unit_price` DECIMAL(10,2) NOT NULL COMMENT '单价',
    `quantity` INT DEFAULT 1 COMMENT '数量',
    `total_price` DECIMAL(10,2) NOT NULL COMMENT '小计金额',
    `created_at` DATETIME DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
    `updated_at` DATETIME DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
    `is_deleted` TINYINT DEFAULT 0 COMMENT '是否删除：0-未删除，1-已删除',
    PRIMARY KEY (`id`),
    KEY `idx_order_id` (`order_id`),
    KEY `idx_content_id` (`content_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci COMMENT='订单明细表';

-- 支付记录表
DROP TABLE IF EXISTS `pay_payment`;
CREATE TABLE `pay_payment` (
    `id` BIGINT NOT NULL COMMENT '支付记录ID',
    `payment_no` VARCHAR(32) NOT NULL COMMENT '支付流水号',
    `order_id` BIGINT NOT NULL COMMENT '订单ID',
    `order_no` VARCHAR(32) NOT NULL COMMENT '订单号',
    `user_id` BIGINT NOT NULL COMMENT '用户ID',
    `payment_method` VARCHAR(50) NOT NULL COMMENT '支付方式',
    `payment_channel` VARCHAR(50) NOT NULL COMMENT '支付渠道',
    `payment_amount` DECIMAL(10,2) NOT NULL COMMENT '支付金额',
    `currency` VARCHAR(10) DEFAULT 'CNY' COMMENT '货币类型',
    `payment_status` TINYINT DEFAULT 1 COMMENT '支付状态：1-支付中，2-支付成功，3-支付失败',
    `third_party_no` VARCHAR(100) DEFAULT NULL COMMENT '第三方支付流水号',
    `callback_data` TEXT DEFAULT NULL COMMENT '支付回调数据',
    `failure_reason` VARCHAR(200) DEFAULT NULL COMMENT '失败原因',
    `paid_time` DATETIME DEFAULT NULL COMMENT '支付完成时间',
    `created_at` DATETIME DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
    `updated_at` DATETIME DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
    `is_deleted` TINYINT DEFAULT 0 COMMENT '是否删除：0-未删除，1-已删除',
    PRIMARY KEY (`id`),
    UNIQUE KEY `uk_payment_no` (`payment_no`),
    KEY `idx_order_id` (`order_id`),
    KEY `idx_order_no` (`order_no`),
    KEY `idx_user_id` (`user_id`),
    KEY `idx_payment_status` (`payment_status`),
    KEY `idx_third_party_no` (`third_party_no`),
    KEY `idx_created_at` (`created_at`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci COMMENT='支付记录表';

-- 退款记录表
DROP TABLE IF EXISTS `pay_refund`;
CREATE TABLE `pay_refund` (
    `id` BIGINT NOT NULL COMMENT '退款记录ID',
    `refund_no` VARCHAR(32) NOT NULL COMMENT '退款流水号',
    `order_id` BIGINT NOT NULL COMMENT '订单ID',
    `payment_id` BIGINT NOT NULL COMMENT '支付记录ID',
    `user_id` BIGINT NOT NULL COMMENT '用户ID',
    `refund_type` TINYINT DEFAULT 1 COMMENT '退款类型：1-用户申请，2-系统自动，3-客服处理',
    `refund_reason` VARCHAR(200) DEFAULT NULL COMMENT '退款原因',
    `refund_amount` DECIMAL(10,2) NOT NULL COMMENT '退款金额',
    `refund_status` TINYINT DEFAULT 1 COMMENT '退款状态：1-申请中，2-退款成功，3-退款失败，4-已拒绝',
    `third_party_refund_no` VARCHAR(100) DEFAULT NULL COMMENT '第三方退款流水号',
    `processor_id` BIGINT DEFAULT NULL COMMENT '处理人ID',
    `process_time` DATETIME DEFAULT NULL COMMENT '处理时间',
    `process_remark` TEXT DEFAULT NULL COMMENT '处理备注',
    `created_at` DATETIME DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
    `updated_at` DATETIME DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
    `is_deleted` TINYINT DEFAULT 0 COMMENT '是否删除：0-未删除，1-已删除',
    PRIMARY KEY (`id`),
    UNIQUE KEY `uk_refund_no` (`refund_no`),
    KEY `idx_order_id` (`order_id`),
    KEY `idx_payment_id` (`payment_id`),
    KEY `idx_user_id` (`user_id`),
    KEY `idx_refund_status` (`refund_status`),
    KEY `idx_created_at` (`created_at`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci COMMENT='退款记录表';

-- 优惠券表
DROP TABLE IF EXISTS `pay_coupon`;
CREATE TABLE `pay_coupon` (
    `id` BIGINT NOT NULL COMMENT '优惠券ID',
    `coupon_name` VARCHAR(100) NOT NULL COMMENT '优惠券名称',
    `coupon_code` VARCHAR(50) NOT NULL COMMENT '优惠券编码',
    `coupon_type` TINYINT NOT NULL COMMENT '优惠券类型：1-满减券，2-折扣券，3-免费券',
    `discount_type` TINYINT NOT NULL COMMENT '优惠类型：1-固定金额，2-百分比折扣',
    `discount_value` DECIMAL(10,2) NOT NULL COMMENT '优惠值（金额或折扣比例）',
    `min_amount` DECIMAL(10,2) DEFAULT 0.00 COMMENT '最低消费金额',
    `max_discount` DECIMAL(10,2) DEFAULT NULL COMMENT '最大优惠金额',
    `total_quantity` INT NOT NULL COMMENT '发行总量',
    `used_quantity` INT DEFAULT 0 COMMENT '已使用数量',
    `per_user_limit` INT DEFAULT 1 COMMENT '每用户限领数量',
    `applicable_content` JSON DEFAULT NULL COMMENT '适用内容（JSON格式）',
    `start_time` DATETIME NOT NULL COMMENT '有效开始时间',
    `end_time` DATETIME NOT NULL COMMENT '有效结束时间',
    `status` TINYINT DEFAULT 1 COMMENT '状态：0-禁用，1-启用，2-已结束',
    `description` TEXT DEFAULT NULL COMMENT '使用说明',
    `created_at` DATETIME DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
    `updated_at` DATETIME DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
    `is_deleted` TINYINT DEFAULT 0 COMMENT '是否删除：0-未删除，1-已删除',
    PRIMARY KEY (`id`),
    UNIQUE KEY `uk_coupon_code` (`coupon_code`),
    KEY `idx_coupon_type` (`coupon_type`),
    KEY `idx_status` (`status`),
    KEY `idx_start_end_time` (`start_time`, `end_time`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci COMMENT='优惠券表';

-- 用户优惠券表
DROP TABLE IF EXISTS `pay_coupon_user`;
CREATE TABLE `pay_coupon_user` (
    `id` BIGINT NOT NULL COMMENT '用户优惠券ID',
    `user_id` BIGINT NOT NULL COMMENT '用户ID',
    `coupon_id` BIGINT NOT NULL COMMENT '优惠券ID',
    `coupon_code` VARCHAR(50) NOT NULL COMMENT '优惠券编码',
    `status` TINYINT DEFAULT 1 COMMENT '状态：1-未使用，2-已使用，3-已过期',
    `order_id` BIGINT DEFAULT NULL COMMENT '使用的订单ID',
    `used_time` DATETIME DEFAULT NULL COMMENT '使用时间',
    `expire_time` DATETIME NOT NULL COMMENT '过期时间',
    `created_at` DATETIME DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
    `updated_at` DATETIME DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
    `is_deleted` TINYINT DEFAULT 0 COMMENT '是否删除：0-未删除，1-已删除',
    PRIMARY KEY (`id`),
    KEY `idx_user_id` (`user_id`),
    KEY `idx_coupon_id` (`coupon_id`),
    KEY `idx_status` (`status`),
    KEY `idx_expire_time` (`expire_time`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci COMMENT='用户优惠券表';

-- 收益分成表
DROP TABLE IF EXISTS `pay_revenue`;
CREATE TABLE `pay_revenue` (
    `id` BIGINT NOT NULL COMMENT '收益记录ID',
    `order_id` BIGINT NOT NULL COMMENT '订单ID',
    `content_id` BIGINT NOT NULL COMMENT '内容ID',
    `author_id` BIGINT NOT NULL COMMENT '作者ID',
    `total_amount` DECIMAL(10,2) NOT NULL COMMENT '订单总金额',
    `author_rate` DECIMAL(5,4) DEFAULT 0.7000 COMMENT '作者分成比例',
    `platform_rate` DECIMAL(5,4) DEFAULT 0.2500 COMMENT '平台分成比例',
    `channel_rate` DECIMAL(5,4) DEFAULT 0.0500 COMMENT '渠道分成比例',
    `author_amount` DECIMAL(10,2) NOT NULL COMMENT '作者收益金额',
    `platform_amount` DECIMAL(10,2) NOT NULL COMMENT '平台收益金额',
    `channel_amount` DECIMAL(10,2) DEFAULT 0.00 COMMENT '渠道收益金额',
    `status` TINYINT DEFAULT 1 COMMENT '状态：1-待结算，2-已结算，3-已提现',
    `settle_time` DATETIME DEFAULT NULL COMMENT '结算时间',
    `created_at` DATETIME DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
    `updated_at` DATETIME DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
    `is_deleted` TINYINT DEFAULT 0 COMMENT '是否删除：0-未删除，1-已删除',
    PRIMARY KEY (`id`),
    KEY `idx_order_id` (`order_id`),
    KEY `idx_content_id` (`content_id`),
    KEY `idx_author_id` (`author_id`),
    KEY `idx_status` (`status`),
    KEY `idx_settle_time` (`settle_time`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci COMMENT='收益分成表';

-- 提现记录表
DROP TABLE IF EXISTS `pay_withdraw`;
CREATE TABLE `pay_withdraw` (
    `id` BIGINT NOT NULL COMMENT '提现记录ID',
    `withdraw_no` VARCHAR(32) NOT NULL COMMENT '提现流水号',
    `user_id` BIGINT NOT NULL COMMENT '用户ID',
    `withdraw_amount` DECIMAL(10,2) NOT NULL COMMENT '提现金额',
    `service_fee` DECIMAL(10,2) DEFAULT 0.00 COMMENT '手续费',
    `actual_amount` DECIMAL(10,2) NOT NULL COMMENT '实际到账金额',
    `withdraw_type` TINYINT DEFAULT 1 COMMENT '提现方式：1-银行卡，2-支付宝，3-微信',
    `account_info` JSON NOT NULL COMMENT '账户信息（JSON格式）',
    `withdraw_status` TINYINT DEFAULT 1 COMMENT '提现状态：1-申请中，2-处理中，3-已完成，4-已拒绝',
    `processor_id` BIGINT DEFAULT NULL COMMENT '处理人ID',
    `process_time` DATETIME DEFAULT NULL COMMENT '处理时间',
    `process_remark` TEXT DEFAULT NULL COMMENT '处理备注',
    `third_party_no` VARCHAR(100) DEFAULT NULL COMMENT '第三方流水号',
    `created_at` DATETIME DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
    `updated_at` DATETIME DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
    `is_deleted` TINYINT DEFAULT 0 COMMENT '是否删除：0-未删除，1-已删除',
    PRIMARY KEY (`id`),
    UNIQUE KEY `uk_withdraw_no` (`withdraw_no`),
    KEY `idx_user_id` (`user_id`),
    KEY `idx_withdraw_status` (`withdraw_status`),
    KEY `idx_created_at` (`created_at`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci COMMENT='提现记录表';
