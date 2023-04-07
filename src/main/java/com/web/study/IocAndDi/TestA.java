package com.web.study.IocAndDi;

import org.springframework.stereotype.Component;

@Component("t1") //패키지 스캔 안에 이 어노테이션은 "이 클래스를 정의했으니 빈으로 등록해줘." 라는 뜻이 된다.
public class TestA implements Test{
	
	@Override
	public void printTest() {
			System.out.println("TestA 클래스!!");
	}
}
