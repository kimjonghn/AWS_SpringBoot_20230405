package com.web.study.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.web.study.IocAndDi.TestC;

@Configuration
public class BeanConfig {
	@Bean //conponent와 하는역할이 비슷 
	public TestC testC() {
		return new TestC();
	}
}
