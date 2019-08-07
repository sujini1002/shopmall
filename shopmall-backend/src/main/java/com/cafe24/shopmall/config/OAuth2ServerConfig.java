package com.cafe24.shopmall.config;

import java.sql.SQLException;

import javax.sql.DataSource;

import org.apache.commons.dbcp.BasicDataSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.oauth2.config.annotation.configurers.ClientDetailsServiceConfigurer;
import org.springframework.security.oauth2.config.annotation.web.configuration.AuthorizationServerConfigurerAdapter;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableAuthorizationServer;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableResourceServer;
import org.springframework.security.oauth2.config.annotation.web.configuration.ResourceServerConfigurerAdapter;
import org.springframework.security.oauth2.config.annotation.web.configurers.AuthorizationServerEndpointsConfigurer;
import org.springframework.security.oauth2.config.annotation.web.configurers.ResourceServerSecurityConfigurer;
import org.springframework.security.oauth2.provider.token.store.JdbcTokenStore;

@Configuration
public class OAuth2ServerConfig {

	@Configuration
	@EnableResourceServer
	public class ResourceServerConfig extends ResourceServerConfigurerAdapter {
		
		@Override
		public void configure(HttpSecurity http) throws Exception {
			http.headers().frameOptions().disable();

			// 자원서버 접근 권한 설정
			http
				.authorizeRequests()
				.anyRequest().access("#oauth2.hasScope('read,write')");
		}

		@Override
		public void configure(ResourceServerSecurityConfigurer securityConfigure) throws Exception {
			securityConfigure.resourceId("v1");
		}
	}
	
	
	@Configuration
	@EnableAuthorizationServer
	public static class AuthorizationServerConfig extends AuthorizationServerConfigurerAdapter {
		
	    @Autowired
		@Qualifier("authenticationManagerBean")		    
	    private AuthenticationManager authenticationManager;
	    
		@Override
		public void configure(ClientDetailsServiceConfigurer clients) throws Exception {
			clients
				.jdbc(dataSource());
		}
		
	    @Override
	    public void configure(AuthorizationServerEndpointsConfigurer endpoints) throws Exception {
	        endpoints
	        	.tokenStore( new JdbcTokenStore(dataSource()) )
	        		.authenticationManager(authenticationManager);
	    }
	    
	    @Bean
	    @ConfigurationProperties("spring.datasource")
	    public DataSource dataSource() throws SQLException {
	        return new BasicDataSource();
	    }
	}
}
