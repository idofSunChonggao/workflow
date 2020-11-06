package com.lingz.workflow.config;

import com.lingz.workflow.utils.MyLocaleResolver;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.LocaleResolver;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/**
 * @Author SunChonggao
 * @Date 2020-08-30 15:49
 * @Version 1.0
 * @Description：
 */
@Configuration
public class WebConfig implements WebMvcConfigurer {
    @Bean
    public LocaleResolver localeResolver() {

        return new MyLocaleResolver();
    }
}
