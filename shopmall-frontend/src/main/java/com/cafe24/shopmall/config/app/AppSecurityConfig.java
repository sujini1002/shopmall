package com.cafe24.shopmall.config.app;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

import com.cafe24.shopmall.security.CustomUrlAuthenticationSuccessHandler;

@Configuration
@EnableWebSecurity
public class AppSecurityConfig extends WebSecurityConfigurerAdapter {
	
	@Autowired
	private UserDetailsService userDetailsService;
	
	@Override
	public void configure(WebSecurity web) throws Exception {
		web
			.ignoring()
			.antMatchers("/assets/**")
			.antMatchers("/favicon.ico");
	}

	@Override
    public void configure(HttpSecurity http) throws Exception {

		// AuthorizedURL(Basic ACL)
        http
        	.authorizeRequests()
        		// 인증이 되었을 경우
        		.antMatchers("/cart**").authenticated()
        		.antMatchers("/member/logout").authenticated()
        		// ADMIN 권한
        		.antMatchers("/admin", "/admin/**").hasRole("ADMIN")
        	
        		// 모두 허용
        		.anyRequest().permitAll()
        
        // FormLoginConfigurer
        .and()
        	.formLogin()
        		.loginPage("/member/login")
        		.loginProcessingUrl("/member/auth")
        		.failureUrl("/member/login")
        		.successHandler(authenticationSuccessHandler())
        		.usernameParameter("username")
        		.passwordParameter("password")
        
        // LogoutConfigurer
        .and()
        	.logout()
        			.logoutRequestMatcher(new AntPathRequestMatcher("/member/logout"))
        			.logoutSuccessUrl("/")
        			.deleteCookies("JSESSIONID")
        			.invalidateHttpSession(true)
        
        // ExceptionHandlingConfigurer
        .and()
        	.exceptionHandling()
        		.accessDeniedPage("/views/error/accessdenied.jsp")
        
        // RememberMeConfigurer
        .and()
        	.rememberMe()
        		.key("shopmall")
        		.rememberMeParameter("remember-me")

        // CSRFConfigurer(Temporary for Test)
        .and()
        	.csrf()
        		.disable();
        
	}

	// 사용자 세부 서비스를 설정
	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
		//super.configure(auth);
		auth
			.userDetailsService(userDetailsService)
			.and()
			.authenticationProvider(authProvider());
	}
	
	@Bean
	public AuthenticationSuccessHandler authenticationSuccessHandler() {
	    return new CustomUrlAuthenticationSuccessHandler();
	}
	
	// Encode the Password on Authentication
	// BCrypt Password Encoder(with Random Salt)
	@Bean
	public PasswordEncoder passwordEncoder() {
	    return new BCryptPasswordEncoder();
	}
	
	@Bean
	public DaoAuthenticationProvider authProvider() {
	    DaoAuthenticationProvider authProvider = new DaoAuthenticationProvider();
	    authProvider.setUserDetailsService(userDetailsService);
	    //비밀번호 암호화
	    //authProvider.setPasswordEncoder(passwordEncoder());
	    
	    return authProvider;
	}	
}