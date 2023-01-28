package com.chill.chillbbs.util;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/**
 * @author Jarviz
 */
@Configuration
public class WebMvcConfig implements WebMvcConfigurer {
    @Value("${postFilepath}")
    String postFilePath;
    @Value("${commentFilepath}")
    String commentFilePath;
    @Value("${albumCoverPath}")
    String albumCoverPath;
    @Value("${userIconPath}")
    String userIconPath;

    @Override
    public void addCorsMappings(CorsRegistry registry) {
        registry.addMapping("/**").allowedOrigins("*").allowCredentials(false)
                .allowedMethods("*").maxAge(3600);
    }

    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        registry.addResourceHandler("/file/postFiles/**").addResourceLocations("file:" + postFilePath);
        registry.addResourceHandler("/file/commentFiles/**").addResourceLocations("file:" + commentFilePath);
        registry.addResourceHandler("/file/albumCovers/**").addResourceLocations("file:" + albumCoverPath);
        registry.addResourceHandler("/file/userIcons/**").addResourceLocations("file:" + userIconPath);
    }
}
