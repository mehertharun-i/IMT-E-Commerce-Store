package com.E_CommerceOrderManagementProject.aspect;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.AfterThrowing;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.stereotype.Component;

import lombok.extern.slf4j.Slf4j;

@Aspect
@Component
@Slf4j
public class E_CommerceOrderManagementAspect {

	
//Best AOP (Aspect Object Programming) For Logs
	
	
//	@Before("execution(* com.E_CommerceOrderManagementProject.*.*.*(..)) " + "&& !within(com.E_CommerceOrderManagementProject.exceptions.*) " + "&& args(request)")
//	public void logsBeforeAspect(JoinPoint joinpoint, Object request) {
//		log.debug("Entered into "+joinpoint.getSignature()+" method with a Request DataType of ("+request.getClass().getSimpleName()+") : {}", request);
//	}
//	
//	@AfterReturning(pointcut = "execution(* com.E_CommerceOrderManagementProject.*.*.*(..))", returning = "result")
//	public void logsAfterReturningAspect(JoinPoint joinpoint, Object result) {
//		log.debug("Exiting from "+joinpoint.getSignature()+" method and returning a response as : {}", result);	
//	}
	
	@AfterThrowing(pointcut ="execution(* com.E_CommerceOrderManagementProject.*.*.*(..))", throwing ="exceptionMessage")
	public void logsAfterThrowingAspect(JoinPoint joinpoint, RuntimeException exceptionMessage) {
		log.error("Exception occured for "+joinpoint.getSignature()+" , Exception "+exceptionMessage.getClass()+" : {} ", exceptionMessage.getMessage());
	}
	
	@Before("execution(* com.E_CommerceOrderManagementProject.exceptions.*.*(..)) && args(exceptionMessage)")
	public void logsBeforeAspect(JoinPoint joinpoint, RuntimeException exceptionMessage) {
		log.debug("Entered into "+joinpoint.getSignature()+" method with a Request DataType of ("+exceptionMessage.getClass().getSimpleName()+") : {}", exceptionMessage.getMessage());
	}
	
	@Around("execution(* com.E_CommerceOrderManagementProject.*.*.*(..)) " + "&& !within(com.E_CommerceOrderManagementProject.exceptions.*) " + "&& args(request, ..)")
	public Object logsAroundAspect(ProceedingJoinPoint joinpoint, Object request) throws Throwable {
		log.debug("1. Entered into "+joinpoint.getSignature()+" method with a Request DataType of ("+request.getClass().getSimpleName()+") : {}", request);
		
		Object proceed = joinpoint.proceed();
		
		log.debug("0. Exiting from "+joinpoint.getSignature()+" method and returning a response as : {}", proceed);
		
		return proceed;
	}

	
	
	
/*	The Below Code is for exclude particular class, package like (exceptions package should be exclude form the below pointcut),
	main syntax is ("&& !within(com.E_CommerceOrderManagementProject.exceptions.*) ") which is used to exclude from all packages pointcut

	@Before("execution(* com.E_CommerceOrderManagementProject..*.*(..)) " +
            "&& !within(com.E_CommerceOrderManagementProject.exceptions.*) " +
            "&& args(request)")
*/
}
