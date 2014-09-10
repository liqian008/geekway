/**
 * 
 */
package com.bruce.geekway.model.wx;

/**
 * 
 * @author liqian
 *
 */
public enum WxMediaTypeEnum {
	IMAGE("image"), VOICE("voice"), VIDEO("video"), THUMB("thumb");

	private final String mediaType;

	/**
	 * @param text
	 */
	WxMediaTypeEnum(final String mediaType) {
		this.mediaType = mediaType;
	}

	@Override
	public String toString() {
		return mediaType;
	}

	public static WxMediaTypeEnum instanceOf(String strVal) {
		for (WxMediaTypeEnum type : WxMediaTypeEnum.values()) {
			if (type.toString().equalsIgnoreCase(strVal)) {
				return type;
			}
		}
		return null;
	}
}
