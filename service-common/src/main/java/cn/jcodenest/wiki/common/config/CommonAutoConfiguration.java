package cn.jcodenest.wiki.common.config;

import cn.jcodenest.wiki.common.handler.GlobalExceptionHandler;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.autoconfigure.AutoConfiguration;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.boot.autoconfigure.condition.ConditionalOnWebApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Import;

/**
 * 通用服务自动配置类
 *
 * @author JCodeNest
 * @version 1.0.0
 * @since 2025/7/24
 * <p>
 * Copyright (c) 2025 JCodeNest-Wiki
 * All rights reserved.
 */
@Slf4j
@AutoConfiguration
@ComponentScan(basePackages = "cn.jcodenest.wiki.common")
@Import({JacksonConfig.class, WebMvcConfig.class})
public class CommonAutoConfiguration {

    /**
     * 全局异常处理器
     *
     * @return GlobalExceptionHandler
     */
    @Bean
    @ConditionalOnMissingBean
    @ConditionalOnWebApplication
    public GlobalExceptionHandler globalExceptionHandler() {
        log.info("初始化全局异常处理器");
        return new GlobalExceptionHandler();
    }

    /**
     * 初始化完成日志
     */
    public CommonAutoConfiguration() {
        log.info("JCodeNest-Wiki 通用服务自动配置已加载");
    }
}
