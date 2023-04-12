package com.web.study.controller.lecture;

import org.springframework.http.ResponseEntity;
import org.springframework.lang.Nullable;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.web.study.controller.dto.DataResponseDto;
import com.web.study.controller.dto.ResponseDto;
import com.web.study.controller.dto.request.lecture.LectureReqDto;
import com.web.study.service.LectureService;

import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
public class LectureController {
	//final => 상수: 초기화가 무조건 일어나야함.
	private final LectureService lectureService;
	
	// Create
	@PostMapping("/lecture")
	public ResponseEntity<? extends ResponseDto> register(@RequestBody LectureReqDto lectureReqDto) {
		lectureService.registeLecture(lectureReqDto);
		return ResponseEntity.ok().body(ResponseDto.ofdefault());
	}
	
	@GetMapping("/search/lectures")
	public ResponseEntity<? extends ResponseDto> searchLecture(int type, String searchValue) {
		System.out.println(type);
		System.out.println(searchValue);
		return ResponseEntity.ok().body(DataResponseDto.of(lectureService.searchLecture(type, searchValue)));
	}
	
	// Read
	public ResponseEntity<? extends ResponseDto> get() {
		return ResponseEntity.ok().body(ResponseDto.ofdefault());
	}
	// Update
	public ResponseEntity<? extends ResponseDto> modify() {
		return ResponseEntity.ok().body(ResponseDto.ofdefault());
	}
	// Delete
	public ResponseEntity<? extends ResponseDto> remove() {
		return ResponseEntity.ok().body(ResponseDto.ofdefault());
	}
}
