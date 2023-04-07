package com.web.study.controller;

import java.util.HashMap;
import java.util.Map;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.web.study.controller.dto.DataResponseDto;
import com.web.study.controller.dto.ResponseDto;
import com.web.study.controller.dto.request.BasicTestDto;
import com.web.study.controller.dto.request.TestPostDto;

@RestController //?
public class BasicRestController2 {
	//get 요청 Dto 만들어서 
	@GetMapping("/testGet")
	public ResponseEntity<? extends ResponseDto> test(BasicTestDto basicTestDto){
		
		String userInfo = basicTestDto.getName() + "[" + basicTestDto.getAge() + "]";
		
		return ResponseEntity.ok().body(DataResponseDto.of(userInfo));
	}
	//get요청 Dto 없이 json으로
	@GetMapping("/testGet2")
	public Map<String, Integer> testGet2(String name){
		
		Map<String, Integer> testGet2 = new HashMap<>();
		testGet2.put(name, 10);
		testGet2.put("bbb", 11);
		testGet2.put("ccc", 12);
		testGet2.put("ddd", 13);
		
		return testGet2;
	}
	//post
	@PostMapping("/testPost")
	public ResponseEntity<? extends ResponseDto> testPost(@RequestBody TestPostDto testpostDto){
		
		return ResponseEntity.created(null).body(DataResponseDto.of(testpostDto));
	}
	
}
