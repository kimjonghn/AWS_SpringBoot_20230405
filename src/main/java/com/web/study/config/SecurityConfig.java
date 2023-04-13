package com.web.study.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

@EnableWebSecurity
@Configuration
public class SecurityConfig extends WebSecurityConfigurerAdapter{ //Security정보를 설정할수있다
	
	@Bean
	public BCryptPasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}
	
	//security filter
	@Override
	protected void configure(HttpSecurity http) throws Exception {
		http.csrf().disable(); //csrf => 보안 때문에 씀() 하지만 쓰지 않기 때문에 disable()을 붙여줌
		http.authorizeRequests()
			.antMatchers("/auth/register/**", "/auth/login/**") // auth/뒤에 머가오든 상관없다.
			.permitAll()
			.anyRequest()
			.authenticated();
		
	}
}
