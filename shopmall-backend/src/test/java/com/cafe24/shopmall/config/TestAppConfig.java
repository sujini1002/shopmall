package com.cafe24.shopmall.config;


import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;

import com.cafe24.config.app.TestDBConfig;
import com.cafe24.config.app.TestMyBatisConfig;

@Configuration
@ComponentScan({"com.cafe24.shopmall.service","com.cafe24.shopmall.repository"})
@Import({TestDBConfig.class, TestMyBatisConfig.class})
public class TestAppConfig {
	
}
