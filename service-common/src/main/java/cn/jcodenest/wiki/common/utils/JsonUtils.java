package cn.jcodenest.wiki.common.utils;

import com.alibaba.fastjson2.JSON;
import com.alibaba.fastjson2.JSONException;
import com.alibaba.fastjson2.JSONObject;
import com.alibaba.fastjson2.JSONWriter;
import com.alibaba.fastjson2.TypeReference;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;

import java.util.List;
import java.util.Map;

/**
 * JSON 工具类 (基于 FastJSON2)
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
public final class JsonUtils {

    /**
     * 对象转 JSON 字符串
     *
     * @param object 对象
     * @return JSON字符串，转换失败返回null
     */
    public static String toJsonString(Object object) {
        if (object == null) {
            return null;
        }
        
        try {
            return JSON.toJSONString(object);
        } catch (JSONException e) {
            log.error("对象转JSON字符串失败: {}", object, e);
            return null;
        }
    }

    /**
     * 对象转格式化的 JSON 字符串
     *
     * @param object 对象
     * @return 格式化的JSON字符串，转换失败返回null
     */
    public static String toJsonStringPretty(Object object) {
        if (object == null) {
            return null;
        }

        try {
            return JSON.toJSONString(object, JSONWriter.Feature.PrettyFormat);
        } catch (JSONException e) {
            log.error("对象转格式化JSON字符串失败: {}", object, e);
            return null;
        }
    }

    /**
     * JSON 字符串转对象
     *
     * @param jsonString JSON字符串
     * @param clazz      目标类型
     * @param <T>        泛型类型
     * @return 对象，转换失败返回null
     */
    public static <T> T parseObject(String jsonString, Class<T> clazz) {
        if (StringUtils.isBlank(jsonString) || clazz == null) {
            return null;
        }
        
        try {
            return JSON.parseObject(jsonString, clazz);
        } catch (JSONException e) {
            log.error("JSON字符串转对象失败: jsonString={}, clazz={}", jsonString, clazz.getName(), e);
            return null;
        }
    }

    /**
     * JSON 字符串转对象（使用 TypeReference）
     *
     * @param jsonString    JSON字符串
     * @param typeReference 类型引用
     * @param <T>           泛型类型
     * @return 对象，转换失败返回null
     */
    public static <T> T parseObject(String jsonString, TypeReference<T> typeReference) {
        if (StringUtils.isBlank(jsonString) || typeReference == null) {
            return null;
        }
        
        try {
            return JSON.parseObject(jsonString, typeReference);
        } catch (JSONException e) {
            log.error("JSON字符串转对象失败: jsonString={}, typeReference={}", jsonString, typeReference.getType(), e);
            return null;
        }
    }

    /**
     * JSON 字符串转 List
     *
     * @param jsonString JSON字符串
     * @param clazz      元素类型
     * @param <T>        泛型类型
     * @return List对象，转换失败返回null
     */
    public static <T> List<T> parseArray(String jsonString, Class<T> clazz) {
        if (StringUtils.isBlank(jsonString) || clazz == null) {
            return null;
        }
        
        try {
            return JSON.parseArray(jsonString, clazz);
        } catch (JSONException e) {
            log.error("JSON字符串转List失败: jsonString={}, clazz={}", jsonString, clazz.getName(), e);
            return null;
        }
    }

    /**
     * JSON 字符串转 Map
     *
     * @param jsonString JSON字符串
     * @return Map对象，转换失败返回null
     */
    public static Map<String, Object> parseMap(String jsonString) {
        if (StringUtils.isBlank(jsonString)) {
            return null;
        }
        
        try {
            return JSON.parseObject(jsonString, new TypeReference<Map<String, Object>>() {});
        } catch (JSONException e) {
            log.error("JSON字符串转Map失败: jsonString={}", jsonString, e);
            return null;
        }
    }

    /**
     * JSON 字符串转 JSONObject
     *
     * @param jsonString JSON字符串
     * @return JSONObject对象，转换失败返回null
     */
    public static JSONObject parseJSONObject(String jsonString) {
        if (StringUtils.isBlank(jsonString)) {
            return null;
        }
        
        try {
            return JSON.parseObject(jsonString);
        } catch (JSONException e) {
            log.error("JSON字符串转JSONObject失败: jsonString={}", jsonString, e);
            return null;
        }
    }

    /**
     * 判断字符串是否为有效的 JSON 格式
     *
     * @param jsonString JSON字符串
     * @return true-有效，false-无效
     */
    public static boolean isValidJson(String jsonString) {
        if (StringUtils.isBlank(jsonString)) {
            return false;
        }
        
        try {
            JSON.parse(jsonString);
            return true;
        } catch (JSONException e) {
            return false;
        }
    }

    /**
     * 判断字符串是否为有效的 JSON 对象格式
     *
     * @param jsonString JSON字符串
     * @return true-有效的JSON对象，false-无效
     */
    public static boolean isValidJsonObject(String jsonString) {
        if (StringUtils.isBlank(jsonString)) {
            return false;
        }
        
        try {
            Object obj = JSON.parse(jsonString);
            return obj instanceof JSONObject;
        } catch (JSONException e) {
            return false;
        }
    }

    /**
     * 判断字符串是否为有效的 JSON 数组格式
     *
     * @param jsonString JSON字符串
     * @return true-有效的JSON数组，false-无效
     */
    public static boolean isValidJsonArray(String jsonString) {
        if (StringUtils.isBlank(jsonString)) {
            return false;
        }
        
        try {
            Object obj = JSON.parse(jsonString);
            return obj instanceof List;
        } catch (JSONException e) {
            return false;
        }
    }

    /**
     * 对象深拷贝（通过 JSON 序列化和反序列化实现）
     *
     * @param object 源对象
     * @param clazz  目标类型
     * @param <T>    泛型类型
     * @return 拷贝后的对象，失败返回null
     */
    public static <T> T deepCopy(Object object, Class<T> clazz) {
        if (object == null || clazz == null) {
            return null;
        }
        
        try {
            String jsonString = JSON.toJSONString(object);
            return JSON.parseObject(jsonString, clazz);
        } catch (JSONException e) {
            log.error("对象深拷贝失败: object={}, clazz={}", object, clazz.getName(), e);
            return null;
        }
    }
}
