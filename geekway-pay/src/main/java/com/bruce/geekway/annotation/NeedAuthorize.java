package com.bruce.geekway.annotation;


import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;


@Target({ElementType.METHOD, ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
public @interface NeedAuthorize {
	
	/**
	 * 认证策略
	 * @return
	 */
	public AuthorizeStrategy authorizeStrategy() default AuthorizeStrategy.COOKIE_ALLOW;
	
	public enum AuthorizeStrategy{
		//允许检查cookie中的认证信息
		COOKIE_ALLOW,
		//忽略cookie的内容（强制使用oauth登录，以获取最新的用户信息）
		COOKIE_DENY
	}
}
