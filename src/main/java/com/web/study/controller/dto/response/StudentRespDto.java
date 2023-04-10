package com.web.study.controller.dto.response;

import java.time.LocalDate;

import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class StudentRespDto {
	private int id;
	private String name;
	private LocalDate birthDate;
}
