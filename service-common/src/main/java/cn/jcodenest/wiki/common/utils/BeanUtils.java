package cn.jcodenest.wiki.common.utils;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanWrapper;
import org.springframework.beans.BeanWrapperImpl;

import java.beans.PropertyDescriptor;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * Bean 操作工具类
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
public final class BeanUtils extends org.springframework.beans.BeanUtils {

    /**
     * 复制对象属性（忽略 null 值）
     *
     * @param source 源对象
     * @param target 目标对象
     */
    public static void copyPropertiesIgnoreNull(Object source, Object target) {
        if (source == null || target == null) {
            return;
        }
        
        try {
            copyProperties(source, target, getNullPropertyNames(source));
        } catch (Exception e) {
            log.error("复制对象属性失败: source={}, target={}", source.getClass().getSimpleName(), target.getClass().getSimpleName(), e);
        }
    }

    /**
     * 复制对象属性（忽略指定属性）
     *
     * @param source           源对象
     * @param target           目标对象
     * @param ignoreProperties 忽略的属性名数组
     */
    public static void copyPropertiesIgnore(Object source, Object target, String... ignoreProperties) {
        if (source == null || target == null) {
            return;
        }
        
        try {
            copyProperties(source, target, ignoreProperties);
        } catch (Exception e) {
            log.error("复制对象属性失败: source={}, target={}", source.getClass().getSimpleName(), target.getClass().getSimpleName(), e);
        }
    }

    /**
     * 复制对象属性并返回新对象
     *
     * @param source      源对象
     * @param targetClass 目标类型
     * @param <T>         目标类型泛型
     * @return 复制后的新对象，复制失败返回null
     */
    public static <T> T copyProperties(Object source, Class<T> targetClass) {
        if (source == null || targetClass == null) {
            return null;
        }
        
        try {
            T target = targetClass.getDeclaredConstructor().newInstance();
            copyProperties(source, target);
            return target;
        } catch (Exception e) {
            log.error("复制对象属性失败: source={}, targetClass={}", source.getClass().getSimpleName(), targetClass.getSimpleName(), e);
            return null;
        }
    }

    /**
     * 复制对象列表
     *
     * @param sourceList  源对象列表
     * @param targetClass 目标类型
     * @param <S>         源类型泛型
     * @param <T>         目标类型泛型
     * @return 复制后的新对象列表
     */
    public static <S, T> List<T> copyList(List<S> sourceList, Class<T> targetClass) {
        if (sourceList == null || sourceList.isEmpty() || targetClass == null) {
            return new ArrayList<>();
        }
        
        List<T> targetList = new ArrayList<>(sourceList.size());
        for (S source : sourceList) {
            T target = copyProperties(source, targetClass);
            if (target != null) {
                targetList.add(target);
            }
        }
        
        return targetList;
    }

    /**
     * 将对象转换为 Map
     *
     * @param obj 对象
     * @return Map对象，转换失败返回空Map
     */
    public static Map<String, Object> beanToMap(Object obj) {
        Map<String, Object> map = new HashMap<>();
        
        if (obj == null) {
            return map;
        }
        
        try {
            BeanWrapper beanWrapper = new BeanWrapperImpl(obj);
            PropertyDescriptor[] propertyDescriptors = beanWrapper.getPropertyDescriptors();
            
            for (PropertyDescriptor propertyDescriptor : propertyDescriptors) {
                String propertyName = propertyDescriptor.getName();
                
                // 忽略class属性
                if ("class".equals(propertyName)) {
                    continue;
                }
                
                Object propertyValue = beanWrapper.getPropertyValue(propertyName);
                map.put(propertyName, propertyValue);
            }
        } catch (Exception e) {
            log.error("对象转Map失败: {}", obj.getClass().getSimpleName(), e);
        }
        
        return map;
    }

    /**
     * 将 Map 转换为对象
     *
     * @param map         Map对象
     * @param targetClass 目标类型
     * @param <T>         目标类型泛型
     * @return 转换后的对象，转换失败返回null
     */
    public static <T> T mapToBean(Map<String, Object> map, Class<T> targetClass) {
        if (map == null || map.isEmpty() || targetClass == null) {
            return null;
        }
        
        try {
            T target = targetClass.getDeclaredConstructor().newInstance();
            BeanWrapper beanWrapper = new BeanWrapperImpl(target);
            
            for (Map.Entry<String, Object> entry : map.entrySet()) {
                String propertyName = entry.getKey();
                Object propertyValue = entry.getValue();
                
                if (beanWrapper.isWritableProperty(propertyName)) {
                    beanWrapper.setPropertyValue(propertyName, propertyValue);
                }
            }
            
            return target;
        } catch (Exception e) {
            log.error("Map转对象失败: targetClass={}", targetClass.getSimpleName(), e);
            return null;
        }
    }

    /**
     * 获取对象的所有字段名
     *
     * @param clazz 类型
     * @return 字段名列表
     */
    public static List<String> getFieldNames(Class<?> clazz) {
        List<String> fieldNames = new ArrayList<>();
        
        if (clazz == null) {
            return fieldNames;
        }
        
        Field[] fields = clazz.getDeclaredFields();
        for (Field field : fields) {
            fieldNames.add(field.getName());
        }
        
        return fieldNames;
    }

    /**
     * 获取对象的字段值
     *
     * @param obj       对象
     * @param fieldName 字段名
     * @return 字段值，获取失败返回null
     */
    public static Object getFieldValue(Object obj, String fieldName) {
        if (obj == null || StringUtils.isBlank(fieldName)) {
            return null;
        }
        
        try {
            Field field = obj.getClass().getDeclaredField(fieldName);
            field.setAccessible(true);
            return field.get(obj);
        } catch (Exception e) {
            log.warn("获取字段值失败: class={}, fieldName={}", obj.getClass().getSimpleName(), fieldName, e);
            return null;
        }
    }

    /**
     * 设置对象的字段值
     *
     * @param obj        对象
     * @param fieldName  字段名
     * @param fieldValue 字段值
     * @return true-设置成功，false-设置失败
     */
    public static boolean setFieldValue(Object obj, String fieldName, Object fieldValue) {
        if (obj == null || StringUtils.isBlank(fieldName)) {
            return false;
        }
        
        try {
            Field field = obj.getClass().getDeclaredField(fieldName);
            field.setAccessible(true);
            field.set(obj, fieldValue);
            return true;
        } catch (Exception e) {
            log.warn("设置字段值失败: class={}, fieldName={}, fieldValue={}", obj.getClass().getSimpleName(), fieldName, fieldValue, e);
            return false;
        }
    }

    /**
     * 调用对象的方法
     *
     * @param obj        对象
     * @param methodName 方法名
     * @param args       方法参数
     * @return 方法返回值，调用失败返回null
     */
    public static Object invokeMethod(Object obj, String methodName, Object... args) {
        if (obj == null || StringUtils.isBlank(methodName)) {
            return null;
        }
        
        try {
            Class<?>[] paramTypes = new Class[args.length];
            for (int i = 0; i < args.length; i++) {
                paramTypes[i] = args[i] != null ? args[i].getClass() : Object.class;
            }
            
            Method method = obj.getClass().getDeclaredMethod(methodName, paramTypes);
            method.setAccessible(true);
            return method.invoke(obj, args);
        } catch (Exception e) {
            log.warn("调用方法失败: class={}, methodName={}", obj.getClass().getSimpleName(), methodName, e);
            return null;
        }
    }

    /**
     * 判断对象是否有指定字段
     *
     * @param clazz     类型
     * @param fieldName 字段名
     * @return true-有字段，false-无字段
     */
    public static boolean hasField(Class<?> clazz, String fieldName) {
        if (clazz == null || StringUtils.isBlank(fieldName)) {
            return false;
        }
        
        try {
            clazz.getDeclaredField(fieldName);
            return true;
        } catch (NoSuchFieldException e) {
            return false;
        }
    }

    /**
     * 判断对象是否有指定方法
     *
     * @param clazz      类型
     * @param methodName 方法名
     * @param paramTypes 参数类型
     * @return true-有方法，false-无方法
     */
    public static boolean hasMethod(Class<?> clazz, String methodName, Class<?>... paramTypes) {
        if (clazz == null || StringUtils.isBlank(methodName)) {
            return false;
        }
        
        try {
            clazz.getDeclaredMethod(methodName, paramTypes);
            return true;
        } catch (NoSuchMethodException e) {
            return false;
        }
    }

    /**
     * 获取对象中值为null的属性名
     *
     * @param source 源对象
     * @return null属性名数组
     */
    private static String[] getNullPropertyNames(Object source) {
        BeanWrapper beanWrapper = new BeanWrapperImpl(source);
        PropertyDescriptor[] propertyDescriptors = beanWrapper.getPropertyDescriptors();
        
        Set<String> nullPropertyNames = new HashSet<>();
        for (PropertyDescriptor propertyDescriptor : propertyDescriptors) {
            String propertyName = propertyDescriptor.getName();
            Object propertyValue = beanWrapper.getPropertyValue(propertyName);
            
            if (propertyValue == null) {
                nullPropertyNames.add(propertyName);
            }
        }
        
        return nullPropertyNames.toArray(new String[0]);
    }

    /**
     * 深度复制对象（通过序列化）
     *
     * @param source      源对象
     * @param targetClass 目标类型
     * @param <T>         目标类型泛型
     * @return 深度复制后的对象，复制失败返回null
     */
    public static <T> T deepCopy(Object source, Class<T> targetClass) {
        if (source == null || targetClass == null) {
            return null;
        }
        
        return JsonUtils.deepCopy(source, targetClass);
    }

    /**
     * 比较两个对象的属性值是否相等
     *
     * @param obj1 对象1
     * @param obj2 对象2
     * @return true-相等，false-不相等
     */
    public static boolean equals(Object obj1, Object obj2) {
        if (obj1 == obj2) {
            return true;
        }
        
        if (obj1 == null || obj2 == null) {
            return false;
        }
        
        if (!obj1.getClass().equals(obj2.getClass())) {
            return false;
        }
        
        try {
            Field[] fields = obj1.getClass().getDeclaredFields();
            for (Field field : fields) {
                field.setAccessible(true);
                Object value1 = field.get(obj1);
                Object value2 = field.get(obj2);
                
                if (value1 == null && value2 == null) {
                    continue;
                }
                
                if (value1 == null || !value1.equals(value2)) {
                    return false;
                }
            }
            
            return true;
        } catch (Exception e) {
            log.warn("比较对象失败: class={}", obj1.getClass().getSimpleName(), e);
            return false;
        }
    }
}
