package cn.jcodenest.wiki.common.exception;

import lombok.Getter;

/**
 * 错误码枚举
 *
 * @author JCodeNest
 * @version 1.0.0
 * @since 2025/7/24
 * <p>
 * Copyright (c) 2025 JCodeNest-Wiki
 * All rights reserved.
 */
@Getter
public enum ErrorCode {

    /**
     * 系统级错误码 (0-999)
     */
    SUCCESS(200, "操作成功"),
    SYSTEM_ERROR(500, "系统内部错误"),
    SYSTEM_BUSY(501, "系统繁忙，请稍后重试"),
    SYSTEM_MAINTENANCE(502, "系统维护中"),
    DATABASE_ERROR(503, "数据库操作失败"),
    CACHE_ERROR(504, "缓存操作失败"),
    MESSAGE_QUEUE_ERROR(505, "消息队列操作失败"),
    THIRD_PARTY_SERVICE_ERROR(506, "第三方服务调用失败"),

    /**
     * 请求相关错误码 (1000-1099)
     */
    BAD_REQUEST(1000, "请求参数错误"),
    UNAUTHORIZED(1001, "未授权访问"),
    FORBIDDEN(1003, "禁止访问"),
    NOT_FOUND(1004, "资源不存在"),
    METHOD_NOT_ALLOWED(1005, "请求方法不允许"),
    REQUEST_TIMEOUT(1008, "请求超时"),
    CONFLICT(1009, "资源冲突"),
    GONE(1010, "资源已不存在"),
    PAYLOAD_TOO_LARGE(1013, "请求体过大"),
    UNSUPPORTED_MEDIA_TYPE(1015, "不支持的媒体类型"),
    TOO_MANY_REQUESTS(1029, "请求过于频繁"),

    /**
     * 参数校验错误码 (1100-1199)
     */
    PARAMETER_INVALID(1100, "参数校验失败"),
    PARAMETER_MISSING(1101, "缺少必需参数"),
    PARAMETER_TYPE_ERROR(1102, "参数类型错误"),
    PARAMETER_FORMAT_ERROR(1103, "参数格式错误"),
    PARAMETER_LENGTH_ERROR(1104, "参数长度错误"),
    PARAMETER_RANGE_ERROR(1105, "参数值超出范围"),
    JSON_PARSE_ERROR(1106, "JSON解析失败"),
    XML_PARSE_ERROR(1107, "XML解析失败"),

    /**
     * 用户相关错误码 (2000-2099)
     */
    USER_NOT_FOUND(2000, "用户不存在"),
    USER_ALREADY_EXISTS(2001, "用户已存在"),
    USER_DISABLED(2002, "用户已被禁用"),
    USER_NOT_ACTIVATED(2003, "用户未激活"),
    USER_LOCKED(2004, "用户已被锁定"),
    PASSWORD_ERROR(2005, "密码错误"),
    PASSWORD_EXPIRED(2006, "密码已过期"),
    PASSWORD_TOO_WEAK(2007, "密码强度不足"),
    LOGIN_FAILED(2008, "登录失败"),
    LOGIN_EXPIRED(2009, "登录已过期"),
    TOKEN_INVALID(2010, "令牌无效"),
    TOKEN_EXPIRED(2011, "令牌已过期"),
    PERMISSION_DENIED(2012, "权限不足"),
    ROLE_NOT_FOUND(2013, "角色不存在"),
    CAPTCHA_ERROR(2014, "验证码错误"),
    CAPTCHA_EXPIRED(2015, "验证码已过期"),

    /**
     * 内容相关错误码 (3000-3099)
     */
    CONTENT_NOT_FOUND(3000, "内容不存在"),
    CONTENT_ALREADY_EXISTS(3001, "内容已存在"),
    CONTENT_AUDIT_FAILED(3002, "内容审核失败"),
    CONTENT_PUBLISHED(3003, "内容已发布"),
    CONTENT_DRAFT(3004, "内容为草稿状态"),
    CONTENT_OFFLINE(3005, "内容已下架"),
    CATEGORY_NOT_FOUND(3006, "分类不存在"),
    CATEGORY_HAS_CHILDREN(3007, "分类下存在子分类"),
    TAG_NOT_FOUND(3008, "标签不存在"),
    TAG_ALREADY_EXISTS(3009, "标签已存在"),
    CONTENT_ACCESS_DENIED(3010, "内容访问权限不足"),

    /**
     * 订单相关错误码 (4000-4099)
     */
    ORDER_NOT_FOUND(4000, "订单不存在"),
    ORDER_STATUS_ERROR(4001, "订单状态错误"),
    ORDER_PAID(4002, "订单已支付"),
    ORDER_CANCELLED(4003, "订单已取消"),
    ORDER_EXPIRED(4004, "订单已过期"),
    PAYMENT_FAILED(4005, "支付失败"),
    PAYMENT_TIMEOUT(4006, "支付超时"),
    REFUND_FAILED(4007, "退款失败"),
    REFUND_AMOUNT_ERROR(4008, "退款金额错误"),
    COUPON_INVALID(4009, "优惠券无效"),
    COUPON_EXPIRED(4010, "优惠券已过期"),
    COUPON_USED(4011, "优惠券已使用"),
    COUPON_NOT_APPLICABLE(4012, "优惠券不适用"),
    INSUFFICIENT_BALANCE(4013, "余额不足"),
    PRODUCT_OUT_OF_STOCK(4014, "商品库存不足"),

    /**
     * 学习相关错误码 (5000-5099)
     */
    COURSE_NOT_FOUND(5000, "课程不存在"),
    COURSE_NOT_PURCHASED(5001, "课程未购买"),
    COURSE_EXPIRED(5002, "课程已过期"),
    CHAPTER_NOT_FOUND(5003, "章节不存在"),
    SECTION_NOT_FOUND(5004, "小节不存在"),
    EXAM_NOT_FOUND(5005, "考试不存在"),
    EXAM_EXPIRED(5006, "考试已过期"),
    EXAM_SUBMITTED(5007, "考试已提交"),
    EXAM_NOT_STARTED(5008, "考试未开始"),
    QUESTION_NOT_FOUND(5009, "题目不存在"),
    ANSWER_REQUIRED(5010, "答案不能为空"),
    LEARNING_PROGRESS_ERROR(5011, "学习进度更新失败"),

    /**
     * 文件相关错误码 (6000-6099)
     */
    FILE_NOT_FOUND(6000, "文件不存在"),
    FILE_UPLOAD_FAILED(6001, "文件上传失败"),
    FILE_DOWNLOAD_FAILED(6002, "文件下载失败"),
    FILE_DELETE_FAILED(6003, "文件删除失败"),
    FILE_TYPE_NOT_SUPPORTED(6004, "文件类型不支持"),
    FILE_SIZE_EXCEEDED(6005, "文件大小超限"),
    FILE_NAME_INVALID(6006, "文件名无效"),
    FILE_ALREADY_EXISTS(6007, "文件已存在"),
    STORAGE_SPACE_INSUFFICIENT(6008, "存储空间不足"),
    FILE_CORRUPTED(6009, "文件已损坏"),

    /**
     * 社交相关错误码 (7000-7099)
     */
    COMMENT_NOT_FOUND(7000, "评论不存在"),
    COMMENT_DELETED(7001, "评论已删除"),
    ALREADY_LIKED(7002, "已经点赞"),
    NOT_LIKED(7003, "未点赞"),
    ALREADY_FOLLOWED(7004, "已经关注"),
    NOT_FOLLOWED(7005, "未关注"),
    CANNOT_FOLLOW_SELF(7006, "不能关注自己"),
    FAVORITE_NOT_FOUND(7007, "收藏不存在"),
    ALREADY_FAVORITE(7008, "已经收藏"),
    MESSAGE_NOT_FOUND(7009, "消息不存在"),
    NOTIFICATION_NOT_FOUND(7010, "通知不存在"),

    /**
     * 业务规则错误码 (8000-8099)
     */
    OPERATION_NOT_ALLOWED(8000, "操作不被允许"),
    RESOURCE_LOCKED(8001, "资源被锁定"),
    QUOTA_EXCEEDED(8002, "配额已超限"),
    FREQUENCY_LIMIT_EXCEEDED(8003, "操作频率超限"),
    TIME_WINDOW_INVALID(8004, "时间窗口无效"),
    DUPLICATE_OPERATION(8005, "重复操作"),
    PRECONDITION_FAILED(8006, "前置条件不满足"),
    BUSINESS_RULE_VIOLATION(8007, "违反业务规则"),
    DATA_INTEGRITY_ERROR(8008, "数据完整性错误"),
    CONCURRENT_MODIFICATION(8009, "并发修改冲突");

    /**
     * 错误码
     */
    private final Integer code;

    /**
     * 错误消息
     */
    private final String message;

    /**
     * 构造函数
     *
     * @param code    错误码
     * @param message 错误消息
     */
    ErrorCode(Integer code, String message) {
        this.code = code;
        this.message = message;
    }

    /**
     * 根据错误码获取枚举
     *
     * @param code 错误码
     * @return ErrorCode枚举，如果不存在则返回null
     */
    public static ErrorCode getByCode(Integer code) {
        if (code == null) {
            return null;
        }
        
        for (ErrorCode errorCode : values()) {
            if (errorCode.getCode().equals(code)) {
                return errorCode;
            }
        }
        return null;
    }

    /**
     * 判断是否为成功码
     *
     * @param code 错误码
     * @return true-成功，false-失败
     */
    public static boolean isSuccess(Integer code) {
        return SUCCESS.getCode().equals(code);
    }

    /**
     * 判断是否为系统错误
     *
     * @param code 错误码
     * @return true-系统错误，false-非系统错误
     */
    public static boolean isSystemError(Integer code) {
        return code != null && code >= 500 && code <= 999;
    }

    /**
     * 判断是否为业务错误
     *
     * @param code 错误码
     * @return true-业务错误，false-非业务错误
     */
    public static boolean isBusinessError(Integer code) {
        return code != null && code >= 1000;
    }

    /**
     * 转换为字符串
     *
     * @return 字符串表示
     */
    @Override
    public String toString() {
        return String.format("ErrorCode{code=%d, message='%s'}", code, message);
    }
}
