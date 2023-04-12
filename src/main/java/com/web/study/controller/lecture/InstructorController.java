package com.web.study.controller.lecture;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.web.study.controller.dto.DataResponseDto;
import com.web.study.controller.dto.ResponseDto;
import com.web.study.controller.dto.request.instructor.InstructorReqDto;
import com.web.study.service.InstructorService;

import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
public class InstructorController {
	
	private final InstructorService instructorService;
	
	@PostMapping("/instructor")
	public ResponseEntity<? extends ResponseDto> registeInstructor(@RequestBody InstructorReqDto instructorReqDto){
		
		instructorService.registeInstructor(instructorReqDto);
		
		return ResponseEntity.ok().body(ResponseDto.ofdefault());
	}
	@GetMapping("/instructors")
	public ResponseEntity<? extends ResponseDto> getInstructor(){
		
		
		return ResponseEntity.ok().body(DataResponseDto.of(instructorService.getInstructorAll()));
	}
	@GetMapping("/instructor{id}")
		public ResponseEntity<? extends ResponseDto> getInstructorById(@PathVariable int id){
		
		
		return ResponseEntity.ok().body(DataResponseDto.of(instructorService.findInstructorById(id)));
	}
}
