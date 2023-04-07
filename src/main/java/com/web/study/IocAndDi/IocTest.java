package com.web.study.IocAndDi;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

//결합도가 높다 => 다른클래스와 연관된 정도가 높아서 하나변경되면 수정할게 많다

@Component
public class IocTest {
	//DI
	@Qualifier("t1")
	@Autowired
	private Test test;
	
//	public IocTest(Test test) {
//		this.test = test;
//	}
	
	public void run() {
		test.printTest();
		System.out.println("IoCTest 출력!");
	}
	
}
