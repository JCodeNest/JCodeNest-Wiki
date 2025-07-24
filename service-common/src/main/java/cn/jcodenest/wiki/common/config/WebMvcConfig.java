package cn.jcodenest.wiki.common.config;

import cn.jcodenest.wiki.common.constant.SecurityConstants;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.autoconfigure.condition.ConditionalOnWebApplication;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import java.util.List;

/**
 * Web MVC配置类
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
@ConditionalOnWebApplication
public class WebMvcConfig implements WebMvcConfigurer {

    /**
     * 跨域配置
     *
     * @param registry CorsRegistry
     */
    @Override
    public void addCorsMappings(CorsRegistry registry) {
        log.info("配置跨域访问");
        
        registry.addMapping("/**")
            // 允许的源
            .allowedOriginPatterns(SecurityConstants.Cors.ALLOWED_ORIGINS)
            // 允许的方法
            .allowedMethods("GET", "POST", "PUT", "DELETE", "OPTIONS", "HEAD", "PATCH")
            // 允许的头部
            .allowedHeaders("*")
            // 是否允许凭证
            .allowCredentials(SecurityConstants.Cors.ALLOW_CREDENTIALS)
            // 预检请求缓存时间
            .maxAge(SecurityConstants.Cors.MAX_AGE);
    }

    /**
     * 静态资源处理
     *
     * @param registry ResourceHandlerRegistry
     */
    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        log.info("配置静态资源处理");
        
        // 静态资源映射
        registry.addResourceHandler("/static/**")
            .addResourceLocations("classpath:/static/");
        
        // 图片资源映射
        registry.addResourceHandler("/images/**")
            .addResourceLocations("classpath:/static/images/");
        
        // CSS资源映射
        registry.addResourceHandler("/css/**")
            .addResourceLocations("classpath:/static/css/");
        
        // JS资源映射
        registry.addResourceHandler("/js/**")
            .addResourceLocations("classpath:/static/js/");
        
        // 字体资源映射
        registry.addResourceHandler("/fonts/**")
            .addResourceLocations("classpath:/static/fonts/");
        
        // Swagger UI资源映射
        registry.addResourceHandler("/swagger-ui/**")
            .addResourceLocations("classpath:/META-INF/resources/webjars/swagger-ui/");
        
        // Webjars资源映射
        registry.addResourceHandler("/webjars/**")
            .addResourceLocations("classpath:/META-INF/resources/webjars/");
    }

    /**
     * 配置消息转换器
     *
     * @param converters 消息转换器列表
     */
    @Override
    public void configureMessageConverters(List<HttpMessageConverter<?>> converters) {
        log.info("配置消息转换器");
        
        // 确保Jackson转换器在列表前面，优先使用
        converters.removeIf(converter -> converter instanceof MappingJackson2HttpMessageConverter);
        
        // 添加自定义的Jackson转换器
        MappingJackson2HttpMessageConverter jackson2HttpMessageConverter = new MappingJackson2HttpMessageConverter();
        converters.addFirst(jackson2HttpMessageConverter);
    }
}
