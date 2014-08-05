package com.bruce.geekway.model.wx;

/**
 * @author grossopaforever@gmail.com
 * @version Jul 28, 2013
 * 
 */
public enum WxMsgTypeEnum {

	TEXT("text"), IMAGE("image"), LOCATION("location"), LINK("link"), EVENT("event"), VOICE("voice"), VIDEO("video");
	
	/**
	 * @param messageType
	 */
	private WxMsgTypeEnum(final String messageType) {
		this.messageType = messageType;
	}

	private final String messageType;

	public static WxMsgTypeEnum instance(String strVal) {
		for (WxMsgTypeEnum type : WxMsgTypeEnum.values()) {
			if (type.toString().equalsIgnoreCase(strVal)) {
				return type;
			}
		}
		return null;
	}

	@Override
	public String toString() {
		return messageType;
	}

}
