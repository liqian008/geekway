package com.bruce.geekway.model.wx.request;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;

/**
 * 上报地理位置事件的请求<br>
 * MsgId 字段无效
 * 
 * @author jianqing.cai@qq.com, https://github.com/caijianqing/weixinmp4java/
 */
@XmlRootElement(name = "xml")
@XmlAccessorType(XmlAccessType.FIELD)
public class LocationEventRequest extends EventRequest {

	private static final long serialVersionUID = 1L;

	/** 地理位置纬度 */
	public String latitude;

	/** 地理位置经度 */
	public String longitude;

	/** 地理位置精度 */
	public String precision;

	public String getLatitude() {
		return latitude;
	}

	public void setLatitude(String latitude) {
		this.latitude = latitude;
	}

	public String getLongitude() {
		return longitude;
	}

	public void setLongitude(String longitude) {
		this.longitude = longitude;
	}

	public String getPrecision() {
		return precision;
	}

	public void setPrecision(String precision) {
		this.precision = precision;
	}

	@Override
	public String toString() {
		return "LocationEventRequest [Event=" + event + ", ToUserName=" + toUserName + ", FromUserName=" + fromUserName + ", CreateTime=" + createTime + ", MsgType=" + msgType
				+ ", MsgId=" + msgId + ", Latitude=" + latitude + ", Longitude=" + longitude + ", Precision=" + precision + "]";
	}

}
