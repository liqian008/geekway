package com.bruce.geekway.admin.aspectj;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;


@Aspect
@Component
public class LoggerAspectj {
	
	private static Logger logger = LoggerFactory.getLogger(LoggerAspectj.class);

	@Pointcut("execution(* com.bruce..impl..*.*(..))||execution(* com.bruce..controller..*.*(..))")//com.bruce包及所有子包下impl的类的所有方法
	public void logPoint() {
	}
	
//	@Before("logPoint()")
//	public void before(ProceedingJoinPoint joinPoint) {
//		String clazz = joinPoint.getTarget().getClass().getName();
//		String method = joinPoint.getSignature().getName();
//		String beforeLog = clazz + " start to invoke method: "+ method;
//		System.err.println(beforeLog);
//    }

	@Around("logPoint()")
	public Object aroundLogging(ProceedingJoinPoint joinPoint) throws Throwable {
		String clazz = joinPoint.getTarget().getClass().getName();
		String method = joinPoint.getSignature().getName();
		
		StringBuilder paramSb = new StringBuilder("");
		Object[] args = joinPoint.getArgs();
		if (null != args && args.length > 0) {
			for (Object arg : args) {
				paramSb.append(arg);
				paramSb.append(",");
			}
		}
		
		
		String beforeLog = "["+clazz + " start to invoke method: "+ method+"("+paramSb+")]";
//		System.err.println(beforeLog);
		if(logger.isDebugEnabled()){
			logger.debug(beforeLog);
		}
		long startTime = System.currentTimeMillis();
		Object result = joinPoint.proceed();
		long endTime = System.currentTimeMillis();
		long timeCost = endTime - startTime;
		String aroundLog = "["+clazz + " invoke " + method + " method in " + timeCost + " ms]";
		
		if(timeCost>1000){//超时，记录warning日志
			if(logger.isWarnEnabled()){
				logger.warn(aroundLog);
			}
		}else{
			if(logger.isDebugEnabled()){
				logger.debug(aroundLog);
			}
		}
//		System.err.println(aroundLog);
		return result;
	}

}
