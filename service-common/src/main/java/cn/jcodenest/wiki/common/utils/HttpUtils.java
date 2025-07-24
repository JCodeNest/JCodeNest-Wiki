package cn.jcodenest.wiki.common.utils;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

import jakarta.servlet.http.HttpServletRequest;
import java.net.InetAddress;
import java.net.UnknownHostException;
import java.util.Map;

/**
 * HTTP请求工具类
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
public final class HttpUtils {

    /**
     * RestTemplate实例
     */
    private static final RestTemplate REST_TEMPLATE = new RestTemplate();

    /**
     * 未知IP地址
     */
    private static final String UNKNOWN = "unknown";

    /**
     * 本地IP地址
     */
    private static final String LOCALHOST_IPV4 = "127.0.0.1";
    private static final String LOCALHOST_IPV6 = "0:0:0:0:0:0:0:1";

    /**
     * 发送GET请求
     *
     * @param url 请求URL
     * @return 响应字符串，请求失败返回null
     */
    public static String get(String url) {
        return get(url, null, null);
    }

    /**
     * 发送GET请求
     *
     * @param url     请求URL
     * @param headers 请求头
     * @return 响应字符串，请求失败返回null
     */
    public static String get(String url, Map<String, String> headers) {
        return get(url, headers, null);
    }

    /**
     * 发送GET请求
     *
     * @param url    请求URL
     * @param headers 请求头
     * @param params 请求参数
     * @return 响应字符串，请求失败返回null
     */
    public static String get(String url, Map<String, String> headers, Map<String, Object> params) {
        try {
            HttpHeaders httpHeaders = createHttpHeaders(headers);
            HttpEntity<String> entity = new HttpEntity<>(httpHeaders);
            
            String requestUrl = buildUrlWithParams(url, params);
            ResponseEntity<String> response = REST_TEMPLATE.exchange(requestUrl, HttpMethod.GET, entity, String.class);
            
            log.debug("GET请求成功: {} -> {}", requestUrl, response.getStatusCode());
            return response.getBody();
        } catch (Exception e) {
            log.error("GET请求失败: {}", url, e);
            return null;
        }
    }

    /**
     * 发送POST请求
     *
     * @param url  请求URL
     * @param body 请求体
     * @return 响应字符串，请求失败返回null
     */
    public static String post(String url, Object body) {
        return post(url, body, null);
    }

    /**
     * 发送POST请求
     *
     * @param url     请求URL
     * @param body    请求体
     * @param headers 请求头
     * @return 响应字符串，请求失败返回null
     */
    public static String post(String url, Object body, Map<String, String> headers) {
        try {
            HttpHeaders httpHeaders = createHttpHeaders(headers);
            httpHeaders.setContentType(MediaType.APPLICATION_JSON);
            
            String requestBody = body instanceof String ? (String) body : JsonUtils.toJsonString(body);
            HttpEntity<String> entity = new HttpEntity<>(requestBody, httpHeaders);
            
            ResponseEntity<String> response = REST_TEMPLATE.exchange(url, HttpMethod.POST, entity, String.class);
            
            log.debug("POST请求成功: {} -> {}", url, response.getStatusCode());
            return response.getBody();
        } catch (Exception e) {
            log.error("POST请求失败: {}", url, e);
            return null;
        }
    }

    /**
     * 发送PUT请求
     *
     * @param url  请求URL
     * @param body 请求体
     * @return 响应字符串，请求失败返回null
     */
    public static String put(String url, Object body) {
        return put(url, body, null);
    }

    /**
     * 发送PUT请求
     *
     * @param url     请求URL
     * @param body    请求体
     * @param headers 请求头
     * @return 响应字符串，请求失败返回null
     */
    public static String put(String url, Object body, Map<String, String> headers) {
        try {
            HttpHeaders httpHeaders = createHttpHeaders(headers);
            httpHeaders.setContentType(MediaType.APPLICATION_JSON);
            
            String requestBody = body instanceof String ? (String) body : JsonUtils.toJsonString(body);
            HttpEntity<String> entity = new HttpEntity<>(requestBody, httpHeaders);
            
            ResponseEntity<String> response = REST_TEMPLATE.exchange(url, HttpMethod.PUT, entity, String.class);
            
            log.debug("PUT请求成功: {} -> {}", url, response.getStatusCode());
            return response.getBody();
        } catch (Exception e) {
            log.error("PUT请求失败: {}", url, e);
            return null;
        }
    }

    /**
     * 发送DELETE请求
     *
     * @param url 请求URL
     * @return 响应字符串，请求失败返回null
     */
    public static String delete(String url) {
        return delete(url, null);
    }

    /**
     * 发送DELETE请求
     *
     * @param url     请求URL
     * @param headers 请求头
     * @return 响应字符串，请求失败返回null
     */
    public static String delete(String url, Map<String, String> headers) {
        try {
            HttpHeaders httpHeaders = createHttpHeaders(headers);
            HttpEntity<String> entity = new HttpEntity<>(httpHeaders);
            
            ResponseEntity<String> response = REST_TEMPLATE.exchange(url, HttpMethod.DELETE, entity, String.class);
            
            log.debug("DELETE请求成功: {} -> {}", url, response.getStatusCode());
            return response.getBody();
        } catch (Exception e) {
            log.error("DELETE请求失败: {}", url, e);
            return null;
        }
    }

    /**
     * 获取客户端真实IP地址
     *
     * @param request HttpServletRequest
     * @return 客户端IP地址
     */
    public static String getClientIpAddress(HttpServletRequest request) {
        if (request == null) {
            return UNKNOWN;
        }
        
        String ip = request.getHeader("X-Forwarded-For");
        if (isValidIp(ip)) {
            // 多次反向代理后会有多个IP值，第一个为真实IP
            int index = ip.indexOf(',');
            if (index != -1) {
                ip = ip.substring(0, index);
            }
            return ip.trim();
        }
        
        ip = request.getHeader("X-Real-IP");
        if (isValidIp(ip)) {
            return ip;
        }
        
        ip = request.getHeader("Proxy-Client-IP");
        if (isValidIp(ip)) {
            return ip;
        }
        
        ip = request.getHeader("WL-Proxy-Client-IP");
        if (isValidIp(ip)) {
            return ip;
        }
        
        ip = request.getHeader("HTTP_CLIENT_IP");
        if (isValidIp(ip)) {
            return ip;
        }
        
        ip = request.getHeader("HTTP_X_FORWARDED_FOR");
        if (isValidIp(ip)) {
            return ip;
        }
        
        ip = request.getRemoteAddr();
        if (LOCALHOST_IPV4.equals(ip) || LOCALHOST_IPV6.equals(ip)) {
            // 根据网卡取本机配置的IP
            try {
                InetAddress inet = InetAddress.getLocalHost();
                ip = inet.getHostAddress();
            } catch (UnknownHostException e) {
                log.warn("获取本机IP失败", e);
            }
        }
        
        return ip;
    }

    /**
     * 获取用户代理字符串
     *
     * @param request HttpServletRequest
     * @return 用户代理字符串
     */
    public static String getUserAgent(HttpServletRequest request) {
        if (request == null) {
            return UNKNOWN;
        }
        
        String userAgent = request.getHeader("User-Agent");
        return StringUtils.isNotBlank(userAgent) ? userAgent : UNKNOWN;
    }

    /**
     * 判断是否为Ajax请求
     *
     * @param request HttpServletRequest
     * @return true-Ajax请求，false-非Ajax请求
     */
    public static boolean isAjaxRequest(HttpServletRequest request) {
        if (request == null) {
            return false;
        }
        
        String requestedWith = request.getHeader("X-Requested-With");
        return "XMLHttpRequest".equals(requestedWith);
    }

    /**
     * 获取请求的完整URL
     *
     * @param request HttpServletRequest
     * @return 完整URL
     */
    public static String getFullRequestUrl(HttpServletRequest request) {
        if (request == null) {
            return "";
        }
        
        StringBuilder url = new StringBuilder();
        url.append(request.getScheme())
           .append("://")
           .append(request.getServerName());
        
        int port = request.getServerPort();
        if (port != 80 && port != 443) {
            url.append(":").append(port);
        }
        
        url.append(request.getRequestURI());
        
        String queryString = request.getQueryString();
        if (StringUtils.isNotBlank(queryString)) {
            url.append("?").append(queryString);
        }
        
        return url.toString();
    }

    /**
     * 创建HTTP请求头
     *
     * @param headers 请求头Map
     * @return HttpHeaders
     */
    private static HttpHeaders createHttpHeaders(Map<String, String> headers) {
        HttpHeaders httpHeaders = new HttpHeaders();
        
        if (headers != null && !headers.isEmpty()) {
            headers.forEach(httpHeaders::set);
        }
        
        return httpHeaders;
    }

    /**
     * 构建带参数的URL
     *
     * @param url    基础URL
     * @param params 参数Map
     * @return 带参数的URL
     */
    private static String buildUrlWithParams(String url, Map<String, Object> params) {
        if (params == null || params.isEmpty()) {
            return url;
        }
        
        StringBuilder urlBuilder = new StringBuilder(url);
        boolean hasQuery = url.contains("?");
        
        for (Map.Entry<String, Object> entry : params.entrySet()) {
            if (hasQuery) {
                urlBuilder.append("&");
            } else {
                urlBuilder.append("?");
                hasQuery = true;
            }
            
            urlBuilder.append(entry.getKey())
                     .append("=")
                     .append(entry.getValue());
        }
        
        return urlBuilder.toString();
    }

    /**
     * 判断IP地址是否有效
     *
     * @param ip IP地址
     * @return true-有效，false-无效
     */
    private static boolean isValidIp(String ip) {
        return StringUtils.isNotBlank(ip) && !UNKNOWN.equalsIgnoreCase(ip);
    }
}
