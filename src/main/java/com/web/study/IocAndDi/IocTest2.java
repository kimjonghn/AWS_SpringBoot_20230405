package com.web.study.IocAndDi;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

//결합도가 높다 => 다른클래스와 연관된 정도가 높아서 하나변경되면 수정할게 많다

@Component
public class IocTest2 {
	//@Autowired를 적용했을때 두개이상을 할때는 @Qualifier뒤에 메서드 명을 붙여서 실행되게한다
	@Qualifier("testC") 
	@Autowired
	private Test test;
	
	//Autowired = 
//	public IocTest(Test test) {
//		this.test = test;
//	}
	
	public void run() {
		test.printTest();
		System.out.println("IoCTest2출력!");
	}
	
}
