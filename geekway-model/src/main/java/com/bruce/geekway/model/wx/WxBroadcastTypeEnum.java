package com.bruce.geekway.model.wx;

public enum WxBroadcastTypeEnum {
	TEXT("text"), IMAGE("image"), MUSIC("music"),
	MPNEWS("mpnews"), VIDEO("video"), VOICE("voice");
	
	/**
	 * @param broadcastType
	 */
	private WxBroadcastTypeEnum(final String broadcastType) {
		this.broadcastType = broadcastType;
	}

	private final String broadcastType;

	public static WxBroadcastTypeEnum instance(String strVal) {
		for (WxBroadcastTypeEnum type : WxBroadcastTypeEnum.values()) {
			if (type.toString().equalsIgnoreCase(strVal)) {
				return type;
			}
		}
		return null;
	}

	@Override
	public String toString() {
		return broadcastType;
	}
}
