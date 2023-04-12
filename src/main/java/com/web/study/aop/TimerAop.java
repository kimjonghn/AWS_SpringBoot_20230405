package com.web.study.aop;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;
import org.springframework.util.StopWatch;

import lombok.extern.slf4j.Slf4j;

@Slf4j //private final Logger logger = LogManager.getLogger(TimerAop.class);
@Aspect
@Component
public class TimerAop {
	
	
	
	//접근지정자 public은 생략 가능!
	@Pointcut("execution(* com.web.study.controller.lecture.*.*(int))")
	private void pointCut() {
	}
	@Pointcut("@annotation(com.web.study.aop.annotation.TimerAspect)")
	private void annotationPointCut() {
	}
	
	@Around("annotationPointCut()&&pointCut()")
	public Object around(ProceedingJoinPoint joinPoint) throws Throwable {
		
		StopWatch stopWatch = new StopWatch();
		stopWatch.start();
		
		//전처리
		Object logic = joinPoint.proceed();
		//후처리
		
		stopWatch.stop();
		log.info("Time >>> {}.{}: {}초",
				joinPoint.getSignature().getDeclaringTypeName(),
				joinPoint.getSignature().getName(),
				stopWatch.getTotalTimeSeconds());
		
		return logic;
	}
}
