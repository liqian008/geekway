package com.bruce.geekway.utils;

public class MessageMocker {

	public static final String MSG_TEXT_XML = "<xml>"
			+ "<ToUserName><![CDATA[toUser]]></ToUserName>"
			+ "<FromUserName><![CDATA[fromUser]]></FromUserName> "
			+ "<CreateTime>1348831860</CreateTime>"
			+ "<MsgType><![CDATA[text]]></MsgType>"
			+ "<Content><![CDATA[liqian]]></Content>"
			+ "<MsgId>1234567890123456</MsgId>" + "</xml>";
	
	
	public static final String MSG_IMG_XML = "<xml>"
			+ "<ToUserName><![CDATA[toUser]]></ToUserName>"
			+ "<FromUserName><![CDATA[fromUser]]></FromUserName>"
			+ "<CreateTime>1348831860</CreateTime>"
			+ "<MsgType><![CDATA[image]]></MsgType>"
			+ "<PicUrl><![CDATA[this is a url]]></PicUrl>"
			+ "<MsgId>1234567890123456</MsgId>" + "</xml>";
	
	public static final String MSG_LOC_XML = "<xml>"
			+ "<ToUserName><![CDATA[toUser]]></ToUserName>"
			+ "<FromUserName><![CDATA[fromUser]]></FromUserName>"
			+ "<CreateTime>1351776360</CreateTime>"
			+ "<MsgType><![CDATA[location]]></MsgType>"
			+ "<Location_X>23.134521</Location_X>"
			+ "<Location_Y>113.358803</Location_Y>" 
			+ "<Scale>20</Scale>"
			+ "<Label><![CDATA[位置信息]]></Label>"
			+ "<MsgId>1234567890123456</MsgId>" + "</xml> ";
	
	public static final String MSG_LINK_XML = "<xml>"
			+ "<ToUserName><![CDATA[toUser]]></ToUserName>"
			+ "<FromUserName><![CDATA[fromUser]]></FromUserName>"
			+ "<CreateTime>1351776360</CreateTime>"
			+ "<MsgType><![CDATA[link]]></MsgType>"
			+ "<Title><![CDATA[公众平台官网链接]]></Title>"
			+ "<Description><![CDATA[公众平台官网链接123]]></Description>"
			+ "<Url><![CDATA[url]]></Url>" + "<MsgId>1234567890123456</MsgId>"
			+ "</xml> ";
	
	
	public static final String MSG_EVENT_CLICK_XML = "<xml>"
			+ "<ToUserName><![CDATA[toUser]]></ToUserName>"
			+ "<FromUserName><![CDATA[FromUser]]></FromUserName>"
			+ "<CreateTime>123456789</CreateTime>"
			+ "<MsgType><![CDATA[event]]></MsgType>"
			+ "<Event><![CDATA[click]]></Event>"
			+ "<EventKey><![CDATA[liqian]]></EventKey>" + "</xml>";
	
	
	public static final String MSG_EVENT_SUBSCRIBE_XML = "<xml>"
			+ "<ToUserName><![CDATA[toUser]]></ToUserName>"
			+ "<FromUserName><![CDATA[oxGeHjg87dS82dsp4iP4SE1iVujA]]></FromUserName>"
			+ "<CreateTime>123456789</CreateTime>"
			+ "<MsgType><![CDATA[event]]></MsgType>"
			+ "<Event><![CDATA[subscribe]]></Event>"
			+ "<EventKey><![CDATA[EVENTKEY]]></EventKey>" + "</xml>";
	
	public static final String MSG_EVENT_UNSUBSCRIBE_XML = "<xml>"
			+ "<ToUserName><![CDATA[toUser]]></ToUserName>"
			+ "<FromUserName><![CDATA[oxGeHjg87dS82dsp4iP4SE1iVujA]]></FromUserName>"
			+ "<CreateTime>123456789</CreateTime>"
			+ "<MsgType><![CDATA[event]]></MsgType>"
			+ "<Event><![CDATA[unsubscribe]]></Event>"
			+ "<EventKey><![CDATA[EVENTKEY]]></EventKey>" + "</xml>";
	
	public static final String MSG_EVENT_BROADCAST_XML = "<xml>"
			+ "<ToUserName><![CDATA[toUser]]></ToUserName>"
			+ "<FromUserName><![CDATA[oxGeHjg87dS82dsp4iP4SE1iVujA]]></FromUserName>"
			+ "<CreateTime>123456789</CreateTime>"
			+ "<MsgType><![CDATA[event]]></MsgType>"
			+ "<Event><![CDATA[MASSSENDJOBFINISH]]></Event>"
			+ "<MsgID>1988</MsgID>"
			+ "<Status><![CDATA[sendsuccess]]></Status>"
			+ "<TotalCount>100</TotalCount>"
			+ "<FilterCount>80</FilterCount>"
			+ "<SentCount>75</SentCount>"
			+ "<ErrorCount>5</ErrorCount>"
			+ "</xml>";
	
}
