package com.web.study.controller.security.jwt;

import java.security.Key;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

import com.web.study.controller.dto.response.auth.JwtTokenRespDto;
import com.web.study.controller.security.PrincipalUserDetails;
import com.web.study.exception.CustomException;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.MalformedJwtException;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.UnsupportedJwtException;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import io.jsonwebtoken.security.SecurityException;
import lombok.extern.slf4j.Slf4j;


@Slf4j
@Component
public class JwtTokenProvider {
	
	private final Key key;
	
	public JwtTokenProvider(@Value("${jwt.secretKey}")String secretKey) {
		byte[] keyBytes = Decoders.BASE64.decode(secretKey);
		this.key = Keys.hmacShaKeyFor(keyBytes);
	}
	public JwtTokenRespDto createToken(Authentication authentication) {
		StringBuilder authoritiesBuilder = new StringBuilder();
		
		authentication.getAuthorities().forEach(grantedAuthority -> {
			authoritiesBuilder.append(grantedAuthority.getAuthority());
			authoritiesBuilder.append(",");
		});
		authoritiesBuilder.delete(authoritiesBuilder.length() -1, authoritiesBuilder.length());
		
		String authorities = authoritiesBuilder.toString();
		
		long now = (new Date()).getTime();
		//1000 = 1초
		Date tokenExpires = new Date(now + 1000 * 60 * 30); //토큰 만료 시간
		
		PrincipalUserDetails userDetails = (PrincipalUserDetails) authentication.getPrincipal(); //유저정보를 가져오기 위해
		
		String accessToken = Jwts.builder()
				.setSubject(authentication.getName())
				.claim("userId", userDetails.getUserId())
				.claim("auth", authorities)
				.setExpiration(tokenExpires)
				.signWith(key, SignatureAlgorithm.HS256)
				.compact();
		
		return JwtTokenRespDto.builder()
				.grantType("Bearer")
				.accessToken(accessToken)
				.build();
	}
	public boolean validateToken(String token) {
		try {
			Jwts.parserBuilder()
			.setSigningKey(key)
			.build()
			.parseClaimsJws(token);
			
			return true;
		}catch (SecurityException | MalformedJwtException e) {
			//Security라이브러리에 오류가 있거나, 잘못된 형식의 JWT가 들어왔을 때 예외
			//SignatureException이 포함되어있음
			log.info("Invalid JWT Token", e);
		}catch (ExpiredJwtException e) {
			//토큰의 유효기간이 만료된 경우 예외
			log.info("Exception JWT Token", e);
		}catch (UnsupportedJwtException e) {
			//jwt의 형식을 지키지 않은 경우 (Header.Payload.Signature)
			log.info("Unsupported JWT Token", e);
		}catch (IllegalArgumentException e) {
			//Jwt토큰이 없을때
			log.info("IllegalArgument JWT Token", e);
		}catch (Exception e) {
			log.info("JMT Token Error", e);
		}
		return false;
	}
	
	public Authentication getAuthentication(String accessToken){
		Claims claims = parseClaims(accessToken);
		Object roles = claims.get("auth");
		
		if(roles == null){
			throw new CustomException("권한 정보가 없는 토큰입니다.");
		}
		
		List<SimpleGrantedAuthority> authorities = new ArrayList<>();
		
		String[] rolesArray = roles.toString().split(",");
		
		Arrays.asList(rolesArray).forEach(role -> {
			authorities.add(new SimpleGrantedAuthority(role));
		});
		
		UserDetails userDetails = new User(claims.getSubject(), "", authorities);
		
		return new UsernamePasswordAuthenticationToken(userDetails, "", authorities);
	}
	private Claims parseClaims(String accessToken) {
		try {
			return Jwts.parserBuilder()
					.setSigningKey(key)
					.build()
					.parseClaimsJws(accessToken)
					.getBody();
		} catch (ExpiredJwtException e) {
			return e.getClaims();
		}
	}
}
