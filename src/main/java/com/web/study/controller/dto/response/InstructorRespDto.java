package com.web.study.controller.dto.response;

import java.time.LocalDate;

import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class InstructorRespDto {
	private int itm_id;
	private String itm_name;
	private LocalDate itm_birth;
}
