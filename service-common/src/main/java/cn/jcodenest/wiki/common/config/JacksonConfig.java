package cn.jcodenest.wiki.common.config;

import cn.jcodenest.wiki.common.constant.CommonConstants;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import com.fasterxml.jackson.datatype.jsr310.deser.LocalDateDeserializer;
import com.fasterxml.jackson.datatype.jsr310.deser.LocalDateTimeDeserializer;
import com.fasterxml.jackson.datatype.jsr310.deser.LocalTimeDeserializer;
import com.fasterxml.jackson.datatype.jsr310.ser.LocalDateSerializer;
import com.fasterxml.jackson.datatype.jsr310.ser.LocalDateTimeSerializer;
import com.fasterxml.jackson.datatype.jsr310.ser.LocalTimeSerializer;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.http.converter.json.Jackson2ObjectMapperBuilder;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.TimeZone;

/**
 * Jackson配置类
 *
 * @author JCodeNest
 * @version 1.0.0
 * @since 2025/7/24
 * <p>
 * Copyright (c) 2025 JCodeNest-Wiki
 * All rights reserved.
 */
@Slf4j
@Configuration
public class JacksonConfig {

    /**
     * 自定义ObjectMapper
     *
     * @param builder Jackson2ObjectMapperBuilder
     * @return ObjectMapper
     */
    @Bean
    @Primary
    @ConditionalOnMissingBean(ObjectMapper.class)
    public ObjectMapper objectMapper(Jackson2ObjectMapperBuilder builder) {
        log.info("初始化自定义ObjectMapper");
        
        // 创建JavaTimeModule并配置时间序列化器
        JavaTimeModule javaTimeModule = new JavaTimeModule();
        
        // 配置LocalDateTime序列化和反序列化
        javaTimeModule.addSerializer(LocalDateTime.class, new LocalDateTimeSerializer(
                DateTimeFormatter.ofPattern(CommonConstants.Time.DEFAULT_DATETIME_FORMAT)));
        javaTimeModule.addDeserializer(LocalDateTime.class, new LocalDateTimeDeserializer(
                DateTimeFormatter.ofPattern(CommonConstants.Time.DEFAULT_DATETIME_FORMAT)));
        
        // 配置LocalDate序列化和反序列化
        javaTimeModule.addSerializer(LocalDate.class, new LocalDateSerializer(
                DateTimeFormatter.ofPattern(CommonConstants.Time.DEFAULT_DATE_FORMAT)));
        javaTimeModule.addDeserializer(LocalDate.class, new LocalDateDeserializer(
                DateTimeFormatter.ofPattern(CommonConstants.Time.DEFAULT_DATE_FORMAT)));
        
        // 配置LocalTime序列化和反序列化
        javaTimeModule.addSerializer(LocalTime.class, new LocalTimeSerializer(
                DateTimeFormatter.ofPattern(CommonConstants.Time.DEFAULT_TIME_FORMAT)));
        javaTimeModule.addDeserializer(LocalTime.class, new LocalTimeDeserializer(
                DateTimeFormatter.ofPattern(CommonConstants.Time.DEFAULT_TIME_FORMAT)));
        
        return builder
            // 设置时区
            .timeZone(TimeZone.getTimeZone(CommonConstants.System.DEFAULT_TIMEZONE))
            // 设置日期格式
            .simpleDateFormat(CommonConstants.Time.DEFAULT_DATETIME_FORMAT)
            // 注册JavaTimeModule
            .modules(javaTimeModule)
            // 创建ObjectMapper
            .createXmlMapper(false)
            .build()
            // 配置序列化特性
            .configure(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS, false)
            .configure(SerializationFeature.WRITE_DATE_TIMESTAMPS_AS_NANOSECONDS, false)
            .configure(SerializationFeature.FAIL_ON_EMPTY_BEANS, false)
            .configure(SerializationFeature.WRITE_ENUMS_USING_TO_STRING, true)
            // 配置反序列化特性
            .configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false)
            .configure(DeserializationFeature.ACCEPT_EMPTY_STRING_AS_NULL_OBJECT, true)
            .configure(DeserializationFeature.ACCEPT_SINGLE_VALUE_AS_ARRAY, true)
            .configure(DeserializationFeature.READ_ENUMS_USING_TO_STRING, true)
            // 设置序列化包含策略
            .setSerializationInclusion(JsonInclude.Include.NON_NULL);
    }

    /**
     * Jackson2ObjectMapperBuilder配置
     *
     * @return Jackson2ObjectMapperBuilder
     */
    @Bean
    @ConditionalOnMissingBean
    public Jackson2ObjectMapperBuilder jackson2ObjectMapperBuilder() {
        log.info("初始化Jackson2ObjectMapperBuilder");
        
        return new Jackson2ObjectMapperBuilder()
            .timeZone(TimeZone.getTimeZone(CommonConstants.System.DEFAULT_TIMEZONE))
            .simpleDateFormat(CommonConstants.Time.DEFAULT_DATETIME_FORMAT)
            .serializers(
                new LocalDateTimeSerializer(DateTimeFormatter.ofPattern(CommonConstants.Time.DEFAULT_DATETIME_FORMAT)),
                new LocalDateSerializer(DateTimeFormatter.ofPattern(CommonConstants.Time.DEFAULT_DATE_FORMAT)),
                new LocalTimeSerializer(DateTimeFormatter.ofPattern(CommonConstants.Time.DEFAULT_TIME_FORMAT))
            )
            .deserializers(
                new LocalDateTimeDeserializer(DateTimeFormatter.ofPattern(CommonConstants.Time.DEFAULT_DATETIME_FORMAT)),
                new LocalDateDeserializer(DateTimeFormatter.ofPattern(CommonConstants.Time.DEFAULT_DATE_FORMAT)),
                new LocalTimeDeserializer(DateTimeFormatter.ofPattern(CommonConstants.Time.DEFAULT_TIME_FORMAT))
            )
            .featuresToDisable(
                SerializationFeature.WRITE_DATES_AS_TIMESTAMPS,
                SerializationFeature.WRITE_DATE_TIMESTAMPS_AS_NANOSECONDS,
                SerializationFeature.FAIL_ON_EMPTY_BEANS,
                DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES
            )
            .featuresToEnable(
                SerializationFeature.WRITE_ENUMS_USING_TO_STRING,
                DeserializationFeature.ACCEPT_EMPTY_STRING_AS_NULL_OBJECT,
                DeserializationFeature.ACCEPT_SINGLE_VALUE_AS_ARRAY,
                DeserializationFeature.READ_ENUMS_USING_TO_STRING
            );
    }
}
