package com.bruce.geekway.admin.aspectj;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;

@Aspect
@Component
public class LoggerAspectj {

	@Pointcut("execution(* com.bruce.geekway.service.impl.*.*(..))")
	public void service() {
	}

	@Around("service()")
	public Object logging(ProceedingJoinPoint joinPoint) throws Throwable {
		String clazz = joinPoint.getTarget().getClass().getSimpleName();
		String method = joinPoint.getSignature().getName();
		long startTime = System.currentTimeMillis();
		Object result = joinPoint.proceed();
		long endTime = System.currentTimeMillis();
		String log = clazz + " Run " + method + "method complete in " + (endTime - startTime) + " ms";
		System.err.println(log);
		return result;
	}

}
