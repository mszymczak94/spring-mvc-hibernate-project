package com.szymczak.springdemo.aspect;

import java.util.logging.Logger;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;


@Aspect
@Component
public class CRMLoggingAspect {
	private Logger logger = Logger.getLogger(getClass().getName());
	
	@Pointcut("execution(* com.szymczak.springdemo.controller.*.*(..))")
	private void forControllerPackage() {
	}

	@Pointcut("execution(* com.szymczak.springdemo.dao.*.*(..))")
	private void forDaoPackage() {
	}
	@Pointcut("execution(* com.szymczak.springdemo.service.*.*(..))")
	private void forServicePackage() {
	}
	
	@Pointcut("forControllerPackage() || forDaoPackage() || forServicePackage()")
	private void forAppFlow() {
	}
	
	@Before("forAppFlow()")
	public void beforeMethod(JoinPoint theJoinPoint) {
		String shortString = theJoinPoint.getSignature().toShortString();
		logger.info("=======> in @before calling method: " + shortString);
		
		Object[] args = theJoinPoint.getArgs();
		
		for(Object arg : args) {
			logger.info("=======> argument: " + arg);
		}
		
	}
	
	@AfterReturning(
			pointcut="forAppFlow()",
			returning="theResult"
			)
	public void afterReturningMethod(JoinPoint theJoinPoint, Object theResult) {
		
		logger.info("======> @AfterReturning from method: " + theJoinPoint.getSignature().toShortString());
		
		logger.info("======> returning value is: " + theResult);
	}
	
}
