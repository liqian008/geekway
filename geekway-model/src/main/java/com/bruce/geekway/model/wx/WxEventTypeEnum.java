package com.bruce.geekway.model.wx;

/**
 * 事件类型
 * 
 * @author jianqing.cai@qq.com, https://github.com/caijianqing/weixinmp4java/
 */
public enum WxEventTypeEnum {
	/** 1:首次订阅; 2:用户首次关注时，进行关注后的事件推送 */
	SUBSCRIBE("subscribe"),
	/** 2:重复订阅; 2:用户重复关注时，进行关注后的事件推送 */
	RESUBSCRIBE("resubscribe"),
	/** 取消订阅 */
	UNSUBSCRIBE("unsubscribe"),
	/** 用户已关注时的事件推送 */
	SCAN("scan"),
	/** 上报地理位置事件 */
	LOCATION("location"),
	/** 自定义菜单链接事件 */
	VIEW("view"),
	/** 自定义菜单点击事件 */
	CLICK("click");

	private final String eventType;

	private WxEventTypeEnum(final String eventType) {
		this.eventType = eventType;
	}

	public static WxEventTypeEnum instance(String strVal) {
		for (WxEventTypeEnum type : WxEventTypeEnum.values()) {
			if (type.toString().equalsIgnoreCase(strVal)) {
				return type;
			}
		}
		return null;
	}

	@Override
	public String toString() {
		return eventType;
	}

}