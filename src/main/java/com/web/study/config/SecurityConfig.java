package com.web.study.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import com.web.study.controller.security.jwt.JwtAuthenticationEntryPoint;
import com.web.study.controller.security.jwt.JwtAuthenticationFilter;
import com.web.study.controller.security.jwt.JwtTokenProvider;

import lombok.RequiredArgsConstructor;

@EnableWebSecurity
@Configuration
@RequiredArgsConstructor
public class SecurityConfig extends WebSecurityConfigurerAdapter{ //Security정보를 설정할수있다
	
	private final JwtTokenProvider jwtTokenProvider;
	private final JwtAuthenticationEntryPoint jwtAuthenticationEntryPoint;
	
	@Bean
	public BCryptPasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}
	
	//security filter
	@Override
	protected void configure(HttpSecurity http) throws Exception {
		http.csrf().disable(); //csrf => 보안 때문에 씀() 하지만 쓰지 않기 때문에 disable()을 붙여줌
		http.httpBasic().disable(); //웹 기본 인증 방식
		http.formLogin().disable();// 폼태그를 통한 로그인
		http.sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS); // 세션 비활성
		http.authorizeRequests()
			.antMatchers("/auth/register/**", "/auth/login/**") // auth/뒤에 머가오든 상관없다.
			.permitAll()
			.anyRequest()
			.authenticated()
			.and()
			// 여기서 jwtTokenProvider가 UsernamePasswordAuthenticationFilter로 전달했는데 실행이 안되면 밑에꺼가 실행됨
			.addFilterBefore(new JwtAuthenticationFilter(jwtTokenProvider), UsernamePasswordAuthenticationFilter.class) 
			.exceptionHandling()
			.authenticationEntryPoint(jwtAuthenticationEntryPoint);
	}
}
