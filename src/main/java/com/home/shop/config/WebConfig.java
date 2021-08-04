package com.home.shop.config;

import javax.servlet.Filter;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.amazonaws.xray.javax.servlet.AWSXRayServletFilter;

@Configuration
public class WebConfig {
    private static final Logger logger = LoggerFactory.getLogger(WebConfig.class);

    @Bean
    public Filter TracingFilter() {
        return new AWSXRayServletFilter("Shop");
    }
}