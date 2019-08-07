package com.cafe24.shopmall.config;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.EnableAspectJAutoProxy;
import org.springframework.context.annotation.Import;

import com.cafe24.shopmall.config.app.AppSecurityConfig;
import com.cafe24.shopmall.config.app.OAuth2ClientConfig;

@Configuration
@EnableAspectJAutoProxy
@ComponentScan({"com.cafe24.shopmall.security", "com.cafe24.shopmall.service",  "com.cafe24.shopmall.provider", "com.cafe24.shopmall.aspect"})
@Import({ AppSecurityConfig.class, OAuth2ClientConfig.class})
public class AppConfig {
}