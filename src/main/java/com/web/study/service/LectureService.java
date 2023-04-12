package com.web.study.service;

import java.util.List;

import com.web.study.controller.dto.request.lecture.LectureReqDto;
import com.web.study.controller.dto.response.LectureRespDto;

public interface LectureService {
	
	public void registeLecture(LectureReqDto lecrtureReqDto);
	public List<LectureRespDto> searchLecture(int type, String searchValue);
}
