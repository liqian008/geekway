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
	
	/**
	 * scope策略
	 * @return
	 */
	public AuthorizeScope AuthorizeScope() default AuthorizeScope.WX_SNSAPI_BASE;
	
	
	public enum AuthorizeScope{
		//微信隐式scope
		WX_SNSAPI_BASE("snsapi_base"),
		//微信显式scope，可以获得用户资料
		WX_SNSAPI_USERINFO("snsapi_userinfo");
		
		private String scope;
		AuthorizeScope(String scope){
			this.scope = scope;
		}
		public String getScope() {
			return scope;
		}
	}
	
	public enum AuthorizeStrategy{
		//允许检查cookie中的认证信息
		COOKIE_ALLOW,
		//忽略cookie的内容（强制使用oauth登录，以获取最新的用户信息&accessToken）
		COOKIE_DENY
	}
}
