package com.web.study.controller.dto.response;

import lombok.Builder;
import lombok.Getter;
import lombok.ToString;

@Builder
@Getter
@ToString
public class LectureRespDto {
	private int lectureId;
	private String lectureName;
	private int lecturePrice;
	private String instructorName;
}
