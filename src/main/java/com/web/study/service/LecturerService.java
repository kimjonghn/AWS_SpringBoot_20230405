package com.web.study.service;

import java.util.List;

import com.web.study.controller.dto.request.lecturer.LecturerReqDto;
import com.web.study.controller.dto.response.LecturerRespDto;

public interface LecturerService {
	
	public void registeLecturer(LecturerReqDto lecturerReqDto);
	public List<LecturerRespDto> getLecturerAll();
	public LecturerRespDto findLecturerById(int id);
}