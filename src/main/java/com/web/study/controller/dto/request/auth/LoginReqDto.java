package com.web.study.controller.dto.request.auth;

import lombok.Data;

@Data
public class LoginReqDto {
	private String username;
	private String password;
}
