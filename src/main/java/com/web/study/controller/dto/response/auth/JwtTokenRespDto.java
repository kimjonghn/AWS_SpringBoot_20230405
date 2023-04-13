package com.web.study.controller.dto.response.auth;

import lombok.Builder;
import lombok.Data;

@Builder
@Data
public class JwtTokenRespDto {
	private String grantType;
	private String accessToken;
}
