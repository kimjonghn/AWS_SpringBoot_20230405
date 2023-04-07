package com.web.study.controller;

import java.util.HashMap;
import java.util.Map;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HelloController {
	
	@GetMapping("/hello") //hello주소에 get요청
	public Map<String, String> hello(String name) {
		
		
		Map<String, String> testMap = new HashMap<>();
		testMap.put("name", name);
		testMap.put("age", "25");
		testMap.put("adress", "김해 능동로118");
		
		return testMap;
	}
}
