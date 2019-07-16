package com.cafe24.shopmall.config;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.EnableAspectJAutoProxy;
import org.springframework.context.annotation.Import;

import com.cafe24.config.web.MessageConfig;
import com.cafe24.config.web.SwaggerConfig;


@Configuration
@EnableAspectJAutoProxy
@ComponentScan({"com.cafe24.shopmall.controller"})
@Import({TestMVCConfig.class,SwaggerConfig.class,MessageConfig.class})
public class TestWebConfig {
	
}