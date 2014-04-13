package com.bruce.geekway.model.wx;

public enum WxMsgRespTypeEnum {
	TEXT("text"), IMAGE("image"), MUSIC("music"), 
	NEWS("news"), VIDEO("video"), VOICE("voice");
	
	
	/**
	 * @param messageType
	 */
	private WxMsgRespTypeEnum(final String messageType) {
		this.messageType = messageType;
	}

	private final String messageType;

	public static WxMsgRespTypeEnum instance(String strVal) {
		for (WxMsgRespTypeEnum type : WxMsgRespTypeEnum.values()) {
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
