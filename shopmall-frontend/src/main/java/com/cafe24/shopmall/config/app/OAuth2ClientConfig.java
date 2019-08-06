package com.cafe24.shopmall.config.app;

import java.util.Arrays;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.security.oauth2.client.DefaultOAuth2ClientContext;
import org.springframework.security.oauth2.client.OAuth2RestTemplate;
import org.springframework.security.oauth2.client.resource.OAuth2ProtectedResourceDetails;
import org.springframework.security.oauth2.client.token.grant.client.ClientCredentialsResourceDetails;
import org.springframework.security.oauth2.common.AuthenticationScheme;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableOAuth2Client;

//MabatisConfig와 같은 역할

@Configuration
@EnableOAuth2Client
public class OAuth2ClientConfig {
	
	//@Autowired 
	//private ClientTokenServices clientTokenService;
	
	//@Autowired
	//private OAuth2ClientContext oauth2ClientContext;
	
	@Bean
	public OAuth2ProtectedResourceDetails resourceDetails() {
        ClientCredentialsResourceDetails resourceDetails = new ClientCredentialsResourceDetails();
        
        //토근을 어떻게 받을 것인가 설정
        resourceDetails.setAccessTokenUri("http://localhost:8888/shopmall/oauth/token");
	    resourceDetails.setClientId("shopmall");
	    resourceDetails.setClientSecret("1234");
        resourceDetails.setGrantType("client_credentials");
        resourceDetails.setScope(Arrays.asList("write", "read"));
	    resourceDetails.setAuthenticationScheme(AuthenticationScheme.header);
	    
	    return resourceDetails;
	}
	
	//oauth의 토큰을 생성 해주는 곳
	@Bean
	public OAuth2RestTemplate oauth2RestTemplate() {
		
		
	    OAuth2RestTemplate restTemplate = new OAuth2RestTemplate( resourceDetails(), new DefaultOAuth2ClientContext() );
	    
        restTemplate.setMessageConverters(Arrays.asList(new MappingJackson2HttpMessageConverter()));
        System.out.println("access token: " + restTemplate.getAccessToken());
        
	    
	    return restTemplate;		
	}
}
