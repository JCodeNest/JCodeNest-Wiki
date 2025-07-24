package cn.jcodenest.wiki.common.utils;

import cn.jcodenest.wiki.common.exception.ErrorCode;
import cn.jcodenest.wiki.common.response.Result;
import jakarta.servlet.http.HttpServletResponse;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.MediaType;

import java.io.IOException;
import java.io.PrintWriter;
import java.nio.charset.StandardCharsets;

/**
 * 响应工具类
 *
 * @author JCodeNest
 * @version 1.0.0
 * @since 2025/7/24
 * <p>
 * Copyright (c) 2025 JCodeNest-Wiki
 * All rights reserved.
 */
@Slf4j
@NoArgsConstructor(access = AccessLevel.PRIVATE)
public final class ResponseUtils {

    /**
     * HTTP状态码常量（兼容性处理）
     */
    private static final int SC_UNPROCESSABLE_ENTITY = 422;
    private static final int SC_TOO_MANY_REQUESTS = 429;

    /**
     * 向响应中写入JSON数据
     *
     * @param response HttpServletResponse对象
     * @param result   响应结果
     */
    public static void writeJson(HttpServletResponse response, Result<?> result) {
        writeJson(response, result, HttpServletResponse.SC_OK);
    }

    /**
     * 向响应中写入JSON数据
     *
     * @param response   HttpServletResponse对象
     * @param result     响应结果
     * @param httpStatus HTTP状态码
     */
    public static void writeJson(HttpServletResponse response, Result<?> result, int httpStatus) {
        try {
            response.setStatus(httpStatus);
            response.setContentType(MediaType.APPLICATION_JSON_VALUE);
            response.setCharacterEncoding(StandardCharsets.UTF_8.name());
            
            String jsonString = JsonUtils.toJsonString(result);
            
            try (PrintWriter writer = response.getWriter()) {
                writer.write(jsonString);
                writer.flush();
            }
        } catch (IOException e) {
            log.error("写入响应数据失败", e);
        }
    }

    /**
     * 向响应中写入成功结果
     *
     * @param response HttpServletResponse对象
     */
    public static void writeSuccess(HttpServletResponse response) {
        writeJson(response, Result.success());
    }

    /**
     * 向响应中写入成功结果
     *
     * @param response HttpServletResponse对象
     * @param data     响应数据
     */
    public static void writeSuccess(HttpServletResponse response, Object data) {
        writeJson(response, Result.success(data));
    }

    /**
     * 向响应中写入成功结果
     *
     * @param response HttpServletResponse对象
     * @param message  响应消息
     * @param data     响应数据
     */
    public static void writeSuccess(HttpServletResponse response, String message, Object data) {
        writeJson(response, Result.success(message, data));
    }

    /**
     * 向响应中写入错误结果
     *
     * @param response  HttpServletResponse对象
     * @param errorCode 错误码枚举
     */
    public static void writeError(HttpServletResponse response, ErrorCode errorCode) {
        writeJson(response, Result.error(errorCode), getHttpStatusByErrorCode(errorCode));
    }

    /**
     * 向响应中写入错误结果
     *
     * @param response HttpServletResponse对象
     * @param code     状态码
     * @param message  错误消息
     */
    public static void writeError(HttpServletResponse response, Integer code, String message) {
        writeJson(response, Result.error(code, message), getHttpStatusByCode(code));
    }

    /**
     * 向响应中写入未授权错误
     *
     * @param response HttpServletResponse对象
     */
    public static void writeUnauthorized(HttpServletResponse response) {
        writeError(response, ErrorCode.UNAUTHORIZED);
    }

    /**
     * 向响应中写入禁止访问错误
     *
     * @param response HttpServletResponse对象
     */
    public static void writeForbidden(HttpServletResponse response) {
        writeError(response, ErrorCode.FORBIDDEN);
    }

    /**
     * 向响应中写入资源不存在错误
     *
     * @param response HttpServletResponse对象
     */
    public static void writeNotFound(HttpServletResponse response) {
        writeError(response, ErrorCode.NOT_FOUND);
    }

    /**
     * 向响应中写入服务器内部错误
     *
     * @param response HttpServletResponse对象
     */
    public static void writeInternalServerError(HttpServletResponse response) {
        writeError(response, ErrorCode.SYSTEM_ERROR);
    }

    /**
     * 根据ErrorCode获取对应的HTTP状态码
     *
     * @param errorCode 错误码枚举
     * @return HTTP状态码
     */
    private static int getHttpStatusByErrorCode(ErrorCode errorCode) {
        return switch (errorCode) {
            case SUCCESS -> HttpServletResponse.SC_OK;
            case BAD_REQUEST, PARAMETER_INVALID -> HttpServletResponse.SC_BAD_REQUEST;
            case UNAUTHORIZED, TOKEN_INVALID, TOKEN_EXPIRED -> HttpServletResponse.SC_UNAUTHORIZED;
            case FORBIDDEN, PERMISSION_DENIED -> HttpServletResponse.SC_FORBIDDEN;
            case NOT_FOUND, USER_NOT_FOUND, CONTENT_NOT_FOUND, ORDER_NOT_FOUND,
                 COURSE_NOT_FOUND, FILE_NOT_FOUND -> HttpServletResponse.SC_NOT_FOUND;
            case METHOD_NOT_ALLOWED -> HttpServletResponse.SC_METHOD_NOT_ALLOWED;
            case CONFLICT -> HttpServletResponse.SC_CONFLICT;
            case TOO_MANY_REQUESTS -> SC_TOO_MANY_REQUESTS;
            default -> HttpServletResponse.SC_INTERNAL_SERVER_ERROR;
        };
    }

    /**
     * 根据状态码获取对应的HTTP状态码
     *
     * @param code 状态码
     * @return HTTP状态码
     */
    private static int getHttpStatusByCode(Integer code) {
        if (code == null) {
            return HttpServletResponse.SC_INTERNAL_SERVER_ERROR;
        }
        
        return switch (code) {
            case 200 -> HttpServletResponse.SC_OK;
            case 400 -> HttpServletResponse.SC_BAD_REQUEST;
            case 401 -> HttpServletResponse.SC_UNAUTHORIZED;
            case 403 -> HttpServletResponse.SC_FORBIDDEN;
            case 404 -> HttpServletResponse.SC_NOT_FOUND;
            case 405 -> HttpServletResponse.SC_METHOD_NOT_ALLOWED;
            case 409 -> HttpServletResponse.SC_CONFLICT;
            case 422 -> SC_UNPROCESSABLE_ENTITY;
            case 429 -> SC_TOO_MANY_REQUESTS;
            case 502 -> HttpServletResponse.SC_BAD_GATEWAY;
            case 503 -> HttpServletResponse.SC_SERVICE_UNAVAILABLE;
            case 504 -> HttpServletResponse.SC_GATEWAY_TIMEOUT;
            default -> code >= 400 && code < 500 ? HttpServletResponse.SC_BAD_REQUEST :
                      HttpServletResponse.SC_INTERNAL_SERVER_ERROR;
        };
    }
}
