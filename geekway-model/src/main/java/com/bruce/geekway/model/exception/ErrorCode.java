package com.bruce.geekway.model.exception;

import java.lang.reflect.Field;
import java.lang.reflect.Modifier;
import java.util.HashMap;
import java.util.Map;
import java.util.MissingResourceException;
import java.util.ResourceBundle;

public class ErrorCode {

	/* 正确请求 */
	// public static final int SUCCESS = 0;

	/* 系统错误分配100内的错误码 */

	/* 系统未知错误 */
	public final static int SYSTEM_ERROR = 1;

	/* 参数不正确 */
	public final static int SYSTEM_MISSING_PARAM = 2;

	/* 验证码不正确 */
	public final static int SYSTEM_VERIFYCODE_ERROR = 3;
	
	/* 没有更多数据 */
	public final static int SYSTEM_NO_MORE_DATA = 4;

	/* 权限相关分配100-200的错误码 */
	
	/* 权限不够，需登陆 */
	public static final int AUTHORIZE_NEED_LOGIN = 100; 
	/* 用户权限不够，需设计师权限 */
	public static final int AUTHORIZE_NEED_DESIGNER = 101;
	
	/* 账户相关分配200-300的错误码 */
	
	/* 获取用户失败 */
	public static final int USER_ERROR = 200;
	/* 用户已经被封禁 */
	public static final int USER_FORBIDDEN = 201;
	/* 用户名（email形式）格式错误 */
	public static final int USER_USERNAME_FORMAT_ERROR = 202;
	/* 用户名（email形式）已存在 */
	public static final int USER_USERNAME_EXISTS = 203;
	/* 昵称已存在 */
	public static final int USER_NICKNAME_EXISTS = 204;
	/* 账户密码不匹配，通常用于登录验证 */
	public static final int USER_PASSWORD_NOT_MATCH = 205;
	/* 旧密码输入错误，通常用与改密码情况 */
	public static final int USER_OLD_PASSWORD_NOT_MATCH = 206;
	/* 修改密码失败 */
	public static final int USER_CHANGE_PASSWORD_FAILED = 207;
	/* 用户不存在 */
	public static final int USER_NOT_EXIST = 208;
	/* 用户注册失败 */
	public static final int USER_REGISTER_ERROR = 209;
	/* 用户登录失败 */
	public static final int USER_LOGIN_ERROR = 210;
	/* 手机号已存在 */
	public static final int USER_MOBILE_EXISTS = 211;

	/* 上传分配x00-x00的错误码 */
	/* 未知错误 */
	public static final int UPLOAD_ERROR = 600;
	/* 文件格式不支持 */
	public static final int UPLOAD_FORMAT_NOT_SUPPORT = 601;
	/* 图片上传未知错误 */
	public static final int UPLOAD_IMAGE_ERROR = 602;
	
	
	
	/* WEB AJAX请求分配10000-19999的错误码 */

	/* MCP请求分配20000-29999的错误码 */

	private final static Map<Integer, String> msgMap = new HashMap<Integer, String>();

	/**
	 * static initialize
	 */
	static {
		staticInit();
	}

	public final static String getMessage(int resultCode) {
		return msgMap.get(resultCode);
	}

	/**
	 * static init
	 */
	private static void staticInit() {
		Field[] fields = ErrorCode.class.getFields();
		for (Field field : fields) { 
			int modifiers = field.getModifiers();
			if (Modifier.isPublic(modifiers) && Modifier.isStatic(modifiers)//
					&& Modifier.isFinal(modifiers) && (field.getType() == int.class)) {
				try {
					msgMap.put(field.getInt(null), Messages.getString(field.getName()));
				} catch (IllegalArgumentException e) {
					// logger.error("staticInit()", e);
				} catch (IllegalAccessException e) {
					// logger.error("staticInit()", e);
				}
			}
		}
	}

	static class Messages {

		private static final String BUNDLE_NAME = "conf/error_code_messages"; //$NON-NLS-1$

		static final ResourceBundle RESOURCE_BUNDLE = ResourceBundle.getBundle(BUNDLE_NAME);

		public static String getString(String key) {
			try {
				return RESOURCE_BUNDLE.getString(key);
			} catch (MissingResourceException e) {
				return  key + " not found!";
			}
		}

		private Messages() {
		}
	}
}
