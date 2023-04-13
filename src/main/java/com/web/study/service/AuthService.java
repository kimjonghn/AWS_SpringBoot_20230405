package com.web.study.service;

import com.web.study.controller.dto.request.auth.LoginReqDto;
import com.web.study.controller.dto.request.auth.RegisteUserReqDto;
import com.web.study.controller.dto.response.auth.JwtTokenRespDto;

public interface AuthService {
	
	public void registeUser(RegisteUserReqDto registeUserReqDto);
	public void duplicatedUsername(RegisteUserReqDto registeUserReqDto);
	
	public JwtTokenRespDto login(LoginReqDto loginReqDto);
}
