package com.web.study.controller;

import java.util.HashMap;
import java.util.Map;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.web.study.controller.dto.DataResponseDto;
import com.web.study.controller.dto.ResponseDto;
import com.web.study.controller.dto.request.BasicTestDto;

//post body에 넣는다
//get Params에 넣는다

@RestController
public class BasicRestController {
	
	//GET 요청의 param을 처리하는 방법
	@GetMapping("/read")
	public ResponseEntity<? extends ResponseDto> read(BasicTestDto basicTestDto){
		//내가응답하는 데이터의 상태를 설정해준다. ex) tatus : 200
		String userInfo = basicTestDto.getName() + "(" + basicTestDto.getAge() + ")";
		
		return ResponseEntity.ok().body(DataResponseDto.of(userInfo));
	}
	
	@GetMapping("/read2/{id}")
	public ResponseEntity<? extends ResponseDto> read(@PathVariable int id){
		Map<Integer, String> userMap = new HashMap<>();
		
		userMap.put(1, "aaa");
		userMap.put(2, "bbb");
		userMap.put(3, "ccc");
		userMap.put(4, "ddd");
		
		return ResponseEntity.ok().body(DataResponseDto.of(userMap.get(id)));
	}
	
	//@RequestBody Post body 에서 입력값이 안들어 갈때 
	//post요청의 데이터 처리
	@PostMapping("/create")
	public ResponseEntity<? extends ResponseDto> create(@RequestBody BasicTestDto basicTestDto){
		System.out.println("데이터 추가");
		
		return ResponseEntity.created(null).body(DataResponseDto.of(basicTestDto));	
	}
	
}










