package com.web.study.domain.entity;

import java.time.LocalDate;

import com.web.study.controller.dto.response.StudentRespDto;

import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class Student {
	private int id;
	private String name;
	private LocalDate birth_date;
	
	public StudentRespDto toDto() {
		return StudentRespDto.builder()
				.id(id)
				.name(name)
				.birthDate(birth_date)
				.build();
	}
}
