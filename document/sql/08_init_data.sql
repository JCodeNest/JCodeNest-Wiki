-- =============================================
-- 初始化数据脚本
-- 版本: 1.0.0
-- 创建时间: 2025-07-23
-- 描述: 插入系统初始化数据
-- =============================================

USE `jcode_wiki`;

-- =============================================
-- 角色初始化数据
-- =============================================

INSERT INTO `usr_role` (`id`, `role_code`, `role_name`, `description`, `sort_order`, `status`) VALUES
(1, 'GUEST', '游客', '未注册用户，只能浏览公开内容', 1, 1),
(2, 'USER', '注册用户', '已注册的普通用户，可以创建内容和评论互动', 2, 1),
(3, 'VIP', 'VIP用户', '付费会员用户，可以访问VIP专享内容', 3, 1),
(4, 'CREATOR', '内容创作者', '内容生产者，可以发布付费内容和查看收益', 4, 1),
(5, 'ADMIN', '管理员', '系统管理员，拥有所有权限', 5, 1);

-- =============================================
-- 权限初始化数据
-- =============================================

INSERT INTO `usr_permission` (`id`, `permission_code`, `permission_name`, `resource_type`, `resource_path`, `parent_id`, `sort_order`, `status`) VALUES
-- 用户管理权限
(1, 'user:view', '查看用户', 'api', '/api/user/**', 0, 1, 1),
(2, 'user:create', '创建用户', 'api', '/api/user/create', 1, 2, 1),
(3, 'user:update', '更新用户', 'api', '/api/user/update', 1, 3, 1),
(4, 'user:delete', '删除用户', 'api', '/api/user/delete', 1, 4, 1),

-- 内容管理权限
(10, 'content:view', '查看内容', 'api', '/api/content/**', 0, 10, 1),
(11, 'content:create', '创建内容', 'api', '/api/content/create', 10, 11, 1),
(12, 'content:update', '更新内容', 'api', '/api/content/update', 10, 12, 1),
(13, 'content:delete', '删除内容', 'api', '/api/content/delete', 10, 13, 1),
(14, 'content:publish', '发布内容', 'api', '/api/content/publish', 10, 14, 1),
(15, 'content:audit', '审核内容', 'api', '/api/content/audit', 10, 15, 1),

-- 订单管理权限
(20, 'order:view', '查看订单', 'api', '/api/order/**', 0, 20, 1),
(21, 'order:create', '创建订单', 'api', '/api/order/create', 20, 21, 1),
(22, 'order:update', '更新订单', 'api', '/api/order/update', 20, 22, 1),
(23, 'order:refund', '订单退款', 'api', '/api/order/refund', 20, 23, 1),

-- 学习管理权限
(30, 'learning:view', '查看学习记录', 'api', '/api/learning/**', 0, 30, 1),
(31, 'learning:progress', '学习进度管理', 'api', '/api/learning/progress', 30, 31, 1),
(32, 'learning:exam', '考试管理', 'api', '/api/learning/exam', 30, 32, 1),

-- 社交功能权限
(40, 'social:comment', '评论功能', 'api', '/api/social/comment', 0, 40, 1),
(41, 'social:like', '点赞功能', 'api', '/api/social/like', 0, 41, 1),
(42, 'social:favorite', '收藏功能', 'api', '/api/social/favorite', 0, 42, 1),
(43, 'social:follow', '关注功能', 'api', '/api/social/follow', 0, 43, 1),

-- 系统管理权限
(50, 'system:config', '系统配置', 'api', '/api/system/config', 0, 50, 1),
(51, 'system:log', '系统日志', 'api', '/api/system/log', 0, 51, 1),
(52, 'system:dict', '数据字典', 'api', '/api/system/dict', 0, 52, 1),
(53, 'system:statistics', '统计数据', 'api', '/api/system/statistics', 0, 53, 1);

-- =============================================
-- 角色权限关联数据
-- =============================================

-- 游客权限（只能查看公开内容）
INSERT INTO `usr_role_permission` (`id`, `role_id`, `permission_id`) VALUES
(1, 1, 10); -- 查看内容

-- 注册用户权限
INSERT INTO `usr_role_permission` (`id`, `role_id`, `permission_id`) VALUES
(10, 2, 10), -- 查看内容
(11, 2, 11), -- 创建内容
(12, 2, 20), -- 查看订单
(13, 2, 21), -- 创建订单
(14, 2, 30), -- 查看学习记录
(15, 2, 31), -- 学习进度管理
(16, 2, 40), -- 评论功能
(17, 2, 41), -- 点赞功能
(18, 2, 42), -- 收藏功能
(19, 2, 43); -- 关注功能

-- VIP用户权限（继承注册用户权限）
INSERT INTO `usr_role_permission` (`id`, `role_id`, `permission_id`) VALUES
(20, 3, 10), (21, 3, 11), (22, 3, 20), (23, 3, 21), (24, 3, 30), (25, 3, 31),
(26, 3, 40), (27, 3, 41), (28, 3, 42), (29, 3, 43), (30, 3, 32); -- 考试管理

-- 内容创作者权限（继承VIP用户权限）
INSERT INTO `usr_role_permission` (`id`, `role_id`, `permission_id`) VALUES
(40, 4, 10), (41, 4, 11), (42, 4, 12), (43, 4, 14), -- 内容管理权限
(44, 4, 20), (45, 4, 21), (46, 4, 30), (47, 4, 31), (48, 4, 32),
(49, 4, 40), (50, 4, 41), (51, 4, 42), (52, 4, 43);

-- 管理员权限（所有权限）
INSERT INTO `usr_role_permission` (`id`, `role_id`, `permission_id`) VALUES
(60, 5, 1), (61, 5, 2), (62, 5, 3), (63, 5, 4), -- 用户管理
(64, 5, 10), (65, 5, 11), (66, 5, 12), (67, 5, 13), (68, 5, 14), (69, 5, 15), -- 内容管理
(70, 5, 20), (71, 5, 21), (72, 5, 22), (73, 5, 23), -- 订单管理
(74, 5, 30), (75, 5, 31), (76, 5, 32), -- 学习管理
(77, 5, 40), (78, 5, 41), (79, 5, 42), (80, 5, 43), -- 社交功能
(81, 5, 50), (82, 5, 51), (83, 5, 52), (84, 5, 53); -- 系统管理

-- =============================================
-- 内容分类初始化数据
-- =============================================

INSERT INTO `cnt_category` (`id`, `category_name`, `category_code`, `description`, `parent_id`, `level`, `path`, `sort_order`, `status`) VALUES
-- 一级分类
(1, '前端开发', 'frontend', '前端开发相关技术', 0, 1, '/1/', 1, 1),
(2, '后端开发', 'backend', '后端开发相关技术', 0, 1, '/2/', 2, 1),
(3, '移动开发', 'mobile', '移动端开发技术', 0, 1, '/3/', 3, 1),
(4, '数据库', 'database', '数据库相关技术', 0, 1, '/4/', 4, 1),
(5, '运维部署', 'devops', '运维和部署相关', 0, 1, '/5/', 5, 1),
(6, '人工智能', 'ai', '人工智能和机器学习', 0, 1, '/6/', 6, 1),

-- 二级分类
(11, 'Vue.js', 'vue', 'Vue.js框架', 1, 2, '/1/11/', 1, 1),
(12, 'React', 'react', 'React框架', 1, 2, '/1/12/', 2, 1),
(13, 'Angular', 'angular', 'Angular框架', 1, 2, '/1/13/', 3, 1),
(14, 'JavaScript', 'javascript', 'JavaScript语言', 1, 2, '/1/14/', 4, 1),

(21, 'Java', 'java', 'Java开发', 2, 2, '/2/21/', 1, 1),
(22, 'Python', 'python', 'Python开发', 2, 2, '/2/22/', 2, 1),
(23, 'Node.js', 'nodejs', 'Node.js开发', 2, 2, '/2/23/', 3, 1),
(24, 'Go', 'golang', 'Go语言开发', 2, 2, '/2/24/', 4, 1),

(31, 'Android', 'android', 'Android开发', 3, 2, '/3/31/', 1, 1),
(32, 'iOS', 'ios', 'iOS开发', 3, 2, '/3/32/', 2, 1),
(33, '小程序', 'miniprogram', '小程序开发', 3, 2, '/3/33/', 3, 1),

(41, 'MySQL', 'mysql', 'MySQL数据库', 4, 2, '/4/41/', 1, 1),
(42, 'Redis', 'redis', 'Redis缓存', 4, 2, '/4/42/', 2, 1),
(43, 'MongoDB', 'mongodb', 'MongoDB数据库', 4, 2, '/4/43/', 3, 1);

-- =============================================
-- 标签初始化数据
-- =============================================

INSERT INTO `cnt_tag` (`id`, `tag_name`, `tag_code`, `description`, `color`, `sort_order`, `status`) VALUES
(1, '入门教程', 'tutorial', '适合初学者的教程', '#52c41a', 1, 1),
(2, '进阶实战', 'advanced', '进阶实战项目', '#1890ff', 2, 1),
(3, '源码解析', 'source-code', '源码分析和解读', '#722ed1', 3, 1),
(4, '面试题', 'interview', '面试相关题目', '#fa541c', 4, 1),
(5, '最佳实践', 'best-practice', '最佳实践和经验分享', '#13c2c2', 5, 1),
(6, '工具推荐', 'tools', '开发工具推荐', '#eb2f96', 6, 1),
(7, '性能优化', 'performance', '性能优化相关', '#f5222d', 7, 1),
(8, '架构设计', 'architecture', '系统架构设计', '#fa8c16', 8, 1),
(9, '开源项目', 'opensource', '开源项目介绍', '#a0d911', 9, 1),
(10, '技术趋势', 'trend', '技术发展趋势', '#2f54eb', 10, 1);

-- =============================================
-- 系统配置初始化数据
-- =============================================

INSERT INTO `sys_config` (`id`, `config_key`, `config_value`, `config_type`, `config_group`, `config_name`, `description`, `is_system`, `is_public`, `sort_order`, `status`) VALUES
-- 系统基础配置
(1, 'system.name', 'JCodeNest-Wiki', 'string', 'system', '系统名称', '系统名称配置', 1, 1, 1, 1),
(2, 'system.version', '1.0.0', 'string', 'system', '系统版本', '系统版本号', 1, 1, 2, 1),
(3, 'system.description', 'JCodeNest-Wiki知识管理平台', 'string', 'system', '系统描述', '系统描述信息', 1, 1, 3, 1),
(4, 'system.logo', '/static/images/logo.png', 'string', 'system', '系统Logo', '系统Logo图片路径', 1, 1, 4, 1),
(5, 'system.favicon', '/static/images/favicon.ico', 'string', 'system', '网站图标', '网站Favicon路径', 1, 1, 5, 1),

-- 用户相关配置
(10, 'user.register.enabled', 'true', 'boolean', 'user', '允许用户注册', '是否允许用户自主注册', 0, 1, 10, 1),
(11, 'user.email.verify', 'true', 'boolean', 'user', '邮箱验证', '注册时是否需要邮箱验证', 0, 1, 11, 1),
(12, 'user.default.avatar', '/static/images/default-avatar.png', 'string', 'user', '默认头像', '用户默认头像路径', 0, 1, 12, 1),
(13, 'user.password.min.length', '6', 'number', 'user', '密码最小长度', '用户密码最小长度要求', 0, 1, 13, 1),

-- 内容相关配置
(20, 'content.audit.enabled', 'true', 'boolean', 'content', '内容审核', '是否开启内容审核功能', 0, 0, 20, 1),
(21, 'content.auto.publish', 'false', 'boolean', 'content', '自动发布', '内容是否自动发布', 0, 0, 21, 1),
(22, 'content.max.size', '10485760', 'number', 'content', '内容最大大小', '单个内容最大大小（字节）', 0, 0, 22, 1),

-- 文件上传配置
(30, 'upload.max.size', '52428800', 'number', 'upload', '文件最大大小', '单个文件最大上传大小（字节）', 0, 0, 30, 1),
(31, 'upload.allowed.types', 'jpg,jpeg,png,gif,pdf,doc,docx,xls,xlsx,ppt,pptx,mp4,mp3', 'string', 'upload', '允许的文件类型', '允许上传的文件扩展名', 0, 0, 31, 1),
(32, 'upload.storage.type', 'local', 'string', 'upload', '存储类型', '文件存储类型：local-本地，oss-对象存储', 0, 0, 32, 1),

-- 支付相关配置
(40, 'payment.enabled', 'true', 'boolean', 'payment', '支付功能', '是否开启支付功能', 0, 0, 40, 1),
(41, 'payment.alipay.enabled', 'true', 'boolean', 'payment', '支付宝支付', '是否开启支付宝支付', 0, 0, 41, 1),
(42, 'payment.wechat.enabled', 'true', 'boolean', 'payment', '微信支付', '是否开启微信支付', 0, 0, 42, 1),

-- 邮件配置
(50, 'email.smtp.host', 'smtp.qq.com', 'string', 'email', 'SMTP服务器', '邮件SMTP服务器地址', 0, 0, 50, 1),
(51, 'email.smtp.port', '587', 'number', 'email', 'SMTP端口', '邮件SMTP服务器端口', 0, 0, 51, 1),
(52, 'email.smtp.username', '', 'string', 'email', 'SMTP用户名', '邮件SMTP用户名', 0, 0, 52, 1),
(53, 'email.smtp.password', '', 'string', 'email', 'SMTP密码', '邮件SMTP密码', 0, 0, 53, 1);

-- =============================================
-- 数据字典初始化数据
-- =============================================

INSERT INTO `sys_dict` (`id`, `dict_type`, `dict_key`, `dict_value`, `dict_label`, `description`, `sort_order`, `is_default`, `status`) VALUES
-- 用户状态
(1, 'user_status', '0', '0', '禁用', '用户状态：禁用', 1, 0, 1),
(2, 'user_status', '1', '1', '正常', '用户状态：正常', 2, 1, 1),
(3, 'user_status', '2', '2', '待激活', '用户状态：待激活', 3, 0, 1),

-- 性别
(10, 'gender', '0', '0', '未知', '性别：未知', 1, 1, 1),
(11, 'gender', '1', '1', '男', '性别：男', 2, 0, 1),
(12, 'gender', '2', '2', '女', '性别：女', 3, 0, 1),

-- 内容状态
(20, 'content_status', '0', '0', '草稿', '内容状态：草稿', 1, 1, 1),
(21, 'content_status', '1', '1', '已发布', '内容状态：已发布', 2, 0, 1),
(22, 'content_status', '2', '2', '审核中', '内容状态：审核中', 3, 0, 1),
(23, 'content_status', '3', '3', '审核失败', '内容状态：审核失败', 4, 0, 1),
(24, 'content_status', '4', '4', '已下架', '内容状态：已下架', 5, 0, 1),

-- 内容类型
(30, 'content_type', 'article', 'article', '文章', '内容类型：文章', 1, 1, 1),
(31, 'content_type', 'video', 'video', '视频', '内容类型：视频', 2, 0, 1),
(32, 'content_type', 'audio', 'audio', '音频', '内容类型：音频', 3, 0, 1),
(33, 'content_type', 'course', 'course', '课程', '内容类型：课程', 4, 0, 1),

-- 订单状态
(40, 'order_status', '1', '1', '待支付', '订单状态：待支付', 1, 1, 1),
(41, 'order_status', '2', '2', '已支付', '订单状态：已支付', 2, 0, 1),
(42, 'order_status', '3', '3', '已取消', '订单状态：已取消', 3, 0, 1),
(43, 'order_status', '4', '4', '已退款', '订单状态：已退款', 4, 0, 1),

-- 支付方式
(50, 'payment_method', 'alipay', 'alipay', '支付宝', '支付方式：支付宝', 1, 1, 1),
(51, 'payment_method', 'wechat', 'wechat', '微信支付', '支付方式：微信支付', 2, 0, 1),
(52, 'payment_method', 'bank_card', 'bank_card', '银行卡', '支付方式：银行卡', 3, 0, 1);
