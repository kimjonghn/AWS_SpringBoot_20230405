package com.web.study;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.web.study.IocAndDi.IocTest;
import com.web.study.IocAndDi.TestB;

@SpringBootApplication
public class StudyApplication {
	
	public static void main(String[] args) {
		SpringApplication.run(StudyApplication.class, args); 
//		iocAndDiTest();
	}
	//DI
	//여기서 TestA를 넣는다
//	public static void iocAndDiTest() { 
//		// IOC => Inversion of Control(제어를 개발자가안하고 프로그램이 한다)
//		IocTest iocTest = new IocTest(new TestB());//ioctest가 생성될때 TestA를 준다
//		iocTest.run();
//	}

}
