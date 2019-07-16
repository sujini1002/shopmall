package com.cafe24.config.web;

import org.springframework.context.MessageSource;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.support.ResourceBundleMessageSource;

@Configuration
public class MessageConfig {
	
	@Bean
	public MessageSource messageSource() {
		ResourceBundleMessageSource messageSoure = new ResourceBundleMessageSource();
		messageSoure.setBasenames("com/cafe24/config/web/messages/messages_ko");
		messageSoure.setDefaultEncoding("UTF-8");
		
		return messageSoure;
	}
}
