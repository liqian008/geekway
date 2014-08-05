package com.bruce.geekway.utils;

import org.apache.commons.lang3.StringUtils;
import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.DocumentHelper;
import org.dom4j.Element;

import com.bruce.geekway.model.wx.WxAuth;
import com.bruce.geekway.model.wx.WxEventTypeEnum;
import com.bruce.geekway.model.wx.WxMsgTypeEnum;
import com.bruce.geekway.model.wx.request.BaseRequest;
import com.bruce.geekway.model.wx.request.BroadcastFinishEventRequest;
import com.bruce.geekway.model.wx.request.EventRequest;
import com.bruce.geekway.model.wx.request.TextRequest;
import com.bruce.geekway.model.wx.response.BaseResponse;
import com.bruce.geekway.model.wx.response.NewsResponse;
import com.bruce.geekway.model.wx.response.TextResponse;
public class WxXmlUtil {
	
	private WxXmlUtil() {
	}

	public static WxAuth getAuthReq(String signature, String timestamp,
			String nonce, String echostr) {
		WxAuth result = new WxAuth(); 
		result.setSignature(signature);
		result.setTimestamp(timestamp);
		result.setNonce(nonce);
		result.setEchostr(echostr);
		return result;
	}

	/**
	 * <code>
	 * &lt;xml&gt;<br />
	 * &nbsp;&nbsp;&lt;ToUserName&gt;&lt;![CDATA[toUser]]&gt;&lt;/ToUserName&gt;<br />
	 * &nbsp;&nbsp;&lt;FromUserName&gt;&lt;![CDATA[fromUser]]&gt;&lt;/
	 * FromUserName&gt; <br />
	 * &nbsp;&nbsp;&lt;CreateTime&gt;1348831860&lt;/CreateTime&gt;<br />
	 * &nbsp;&nbsp;&lt;MsgType&gt;&lt;![CDATA[text]]&gt;&lt;/MsgType&gt;<br />
	 * &nbsp;&nbsp;&lt;Content&gt;&lt;![CDATA[this is a
	 * test]]&gt;&lt;/Content&gt;<br />
	 * &nbsp;&nbsp;&lt;MsgId&gt;1234567890123456&lt;/MsgId&gt;<br />
	 * &lt;/xml&gt;<br />
	 * </code>
	 * 
	 * @param xmlstr
	 * @return
	 * @throws DocumentException
	 */
	public static TextRequest getMsgText(Element ele) throws DocumentException {
//		WxMsgTextEntity result = msgEntityFactory(WxMsgTextEntity.class, ele);
		TextRequest textRequest = populateRequest(TextRequest.class, ele);
//		textRequest.setMsgId(strVal(ele, "MsgId"));
		textRequest.setContent(strVal(ele, "Content"));
		return textRequest;
	}
	
	/**
	 * <code>
	 * &lt;xml&gt;
 	 * &nbsp;&nbsp;&lt;ToUserName&gt;&lt;![CDATA[toUser]]&gt;&lt;/ToUserName&gt;
     * &nbsp;&nbsp;&lt;FromUserName&gt;&lt;![CDATA[fromUser]]&gt;&lt;/FromUserName&gt;
 	 * &nbsp;&nbsp;&lt;CreateTime&gt;1348831860&lt;/CreateTime&gt;
     * &nbsp;&nbsp;&lt;MsgType&gt;&lt;![CDATA[image]]&gt;&lt;/MsgType&gt;
     * &nbsp;&nbsp;&lt;PicUrl&gt;&lt;![CDATA[this is a url]]&gt;&lt;/PicUrl&gt;
     * &nbsp;&nbsp;&lt;MediaId&gt;&lt;![CDATA[media_id]]&gt;&lt;/MediaId&gt;
     * &nbsp;&nbsp;&lt;MsgId&gt;1234567890123456&lt;/MsgId&gt;
     * &lt;/xml&gt;
	 * </code>
	 * 
	 * @param xmlstr
	 * @return
	 * @throws DocumentException
	 */
//	public static WxMsgImageEntity getMsgImage(Element ele) throws DocumentException {
//		WxMsgImageEntity result = msgEntityFactory(WxMsgImageEntity.class, ele);
//		WxItemImageEntity image = new WxItemImageEntity();
//		image.setMediaId(strVal(ele, "MediaId"));
//		image.setPicUrl(strVal(ele, "PicUrl"));
//		result.setImage(image);
//		return result;
//	}
	
	/**
	 * <code>
	 * &lt;xml&gt;<br />
	 * &nbsp;&nbsp;&lt;ToUserName&gt;&lt;![CDATA[toUser]]&gt;&lt;/ToUserName&gt;<br />
	 * &nbsp;&nbsp;&lt;FromUserName&gt;&lt;![CDATA[fromUser]]&gt;&lt;/FromUserName&gt;<br />
	 * &nbsp;&nbsp;&lt;CreateTime&gt;1357290913&lt;/CreateTime&gt;<br />
	 * &nbsp;&nbsp;&lt;MsgType&gt;&lt;![CDATA[voice]]&gt;&lt;/MsgType&gt;<br />
	 * &nbsp;&nbsp;&lt;MediaId&gt;&lt;![CDATA[media_id]]&gt;&lt;/MediaId&gt;<br />
	 * &nbsp;&nbsp;&lt;Format&gt;&lt;![CDATA[Format]]&gt;&lt;/Format&gt;<br />
	 * &nbsp;&nbsp;&lt;MsgId&gt;1234567890123456&lt;/MsgId&gt;<br />
	 * &lt;/xml&gt;
	 * </code>
	 * 
	 * @param ele
	 * @return
	 * @throws DocumentException
	 */
//	public static WxMsgVoiceEntity getMsgVoice(Element ele) throws DocumentException {
//		WxMsgVoiceEntity result = msgEntityFactory(WxMsgVideoEntity.class, ele);
//		WxItemVoiceEntity voice = new WxItemVoiceEntity();
//		voice.setMediaId(strVal(ele, "MediaId"));
//		voice.setFormat(strVal(ele, "Format"));
//		if (!StringUtils.isEmpty(ele.elementText("Recognition"))) {
//			voice.setRecognition(strVal(ele, "Recognition"));
//		}
//		result.setVoice(voice);
//		return result;
//	}
	
	
	/**
	 * <code>
	 * &lt;xml&gt;<br />
	 * &nbsp;&nbsp;&lt;ToUserName&gt;&lt;![CDATA[toUser]]&gt;&lt;/ToUserName&gt;<br />
	 * &nbsp;&nbsp;&lt;FromUserName&gt;&lt;![CDATA[fromUser]]&gt;&lt;/FromUserName&gt;<br />
	 * &nbsp;&nbsp;&lt;CreateTime&gt;1357290913&lt;/CreateTime&gt;<br />
	 * &nbsp;&nbsp;&lt;MsgType&gt;&lt;![CDATA[video]]&gt;&lt;/MsgType&gt;<br />
	 * &nbsp;&nbsp;&lt;MediaId&gt;&lt;![CDATA[media_id]]&gt;&lt;/MediaId&gt;<br />
	 * &nbsp;&nbsp;&lt;ThumbMediaId&gt;&lt;![CDATA[thumb_media_id]]&gt;&lt;/ThumbMediaId&gt;<br />
	 * &nbsp;&nbsp;&lt;MsgId&gt;1234567890123456&lt;/MsgId&gt;<br />
	 * &lt;/xml&gt;
	 * </code>
	 * @param ele
	 * @return
	 * @throws DocumentException
	 */
//	public static WxMsgVideoEntity getMsgVideo(Element ele) throws DocumentException {
//		WxMsgVideoEntity result = msgEntityFactory(WxMsgVideoEntity.class, ele);
//		WxItemVideoEntity video = new WxItemVideoEntity();
//		video.setMediaId(strVal(ele, "MediaId"));
//		WxItemThumbEntity thumb = new WxItemThumbEntity();
//		thumb.setMediaId(strVal(ele, "ThumbMediaId"));
//		video.setThumb(thumb);
//		result.setVideo(video);
//		return result;
//	}
	
	/**
	 * <code>
	 * &lt;xml&gt;<br />
	 * &nbsp;&nbsp;&lt;ToUserName&gt;&lt;![CDATA[toUser]]&gt;&lt;/ToUserName&gt;<br />
	 * &nbsp;&nbsp;&lt;FromUserName&gt;&lt;![CDATA[fromUser]]&gt;&lt;/FromUserName&gt;<br />
	 * &nbsp;&nbsp;&lt;CreateTime&gt;1351776360&lt;/CreateTime&gt;<br />
	 * &nbsp;&nbsp;&lt;MsgType&gt;&lt;![CDATA[location]]&gt;&lt;/MsgType&gt;<br />
	 * &nbsp;&nbsp;&lt;Location_X&gt;23.134521&lt;/Location_X&gt;<br />
	 * &nbsp;&nbsp;&lt;Location_Y&gt;113.358803&lt;/Location_Y&gt;<br />
	 * &nbsp;&nbsp;&lt;Scale&gt;20&lt;/Scale&gt;<br />
	 * &nbsp;&nbsp;&lt;Label&gt;&lt;![CDATA[Location Information]]&gt;&lt;/Label&gt;<br />
	 * &nbsp;&nbsp;&lt;MsgId&gt;1234567890123456&lt;/MsgId&gt;<br />
	 * &lt;/xml&gt;
	 * </code>
	 * 
	 * 
	 * @param xmlstr
	 * @return
	 * @throws DocumentException
	 */
//	public static WxMsgLocEntity getMsgLoc(Element ele) throws DocumentException {
//		WxMsgLocEntity result = msgEntityFactory(WxMsgLocEntity.class, ele);
//		result.setLabel(strVal(ele, "Label"));
//		result.setLocationX(doubleVal(ele, "Location_X"));
//		result.setLocationY(doubleVal(ele, "Location_Y"));
//		result.setScale(doubleVal(ele, "Scale"));
//		return result;
//	}

	/**
	 * <code>
	 * &lt;xml&gt;<br />
	 * &nbsp;&nbsp;&lt;ToUserName&gt;&lt;![CDATA[toUser]]&gt;&lt;/ToUserName&gt;<br />
	 * &nbsp;&nbsp;&lt;FromUserName&gt;&lt;![CDATA[fromUser]]&gt;&lt;/FromUserName&gt;<br />
	 * &nbsp;&nbsp;&lt;CreateTime&gt;1351776360&lt;/CreateTime&gt;<br />
	 * &nbsp;&nbsp;&lt;MsgType&gt;&lt;![CDATA[link]]&gt;&lt;/MsgType&gt;<br />
	 * &nbsp;&nbsp;&lt;Title&gt;&lt;![CDATA[公众平台官网链接]]&gt;&lt;/Title&gt;<br />
	 * &nbsp;&nbsp;&lt;Description&gt;&lt;![CDATA[公众平台官网链接]]&gt;&lt;/Description&gt;<br />
	 * &nbsp;&nbsp;&lt;Url&gt;&lt;![CDATA[url]]&gt;&lt;/Url&gt;<br />
	 * &nbsp;&nbsp;&lt;MsgId&gt;1234567890123456&lt;/MsgId&gt;<br />
	 * &lt;/xml&gt; 
	 * </code>
	 * 
	 * @param xmlstr
	 * @return
	 * @throws DocumentException
	 */
//	public static WxMsgLinkEntity getMsgLink(Element ele) throws DocumentException {
//		WxMsgLinkEntity result = msgEntityFactory(WxMsgLinkEntity.class, ele);
//		result.setTitle(strVal(ele, "Title"));
//		result.setDescription(strVal(ele, "Description"));
//		result.setUrl(strVal(ele, "Url"));
//		return result;
//	}
	
	/**
	 * <code>
	 * &lt;xml&gt;<br />
	 * &nbsp;&nbsp;&lt;ToUserName&gt;&lt;![CDATA[toUser]]&gt;&lt;/ToUserName&gt;<br />
	 * &nbsp;&nbsp;&lt;FromUserName&gt;&lt;![CDATA[FromUser]]&gt;&lt;/FromUserName&gt;<br />
	 * &nbsp;&nbsp;&lt;CreateTime&gt;123456789&lt;/CreateTime&gt;<br />
	 * &nbsp;&nbsp;&lt;MsgType&gt;&lt;![CDATA[event]]&gt;&lt;/MsgType&gt;<br />
	 * &nbsp;&nbsp;&lt;Event&gt;&lt;![CDATA[EVENT]]&gt;&lt;/Event&gt;<br />
	 * &nbsp;&nbsp;&lt;EventKey&gt;&lt;![CDATA[EVENTKEY]]&gt;&lt;/EventKey&gt;<br />
	 * &lt;/xml&gt;
	 * </code>
	 * 
	 * @param xmlstr
	 * @return
	 * @throws DocumentException
	 */
	public static EventRequest getMsgEvent(Element ele) throws DocumentException {
		EventRequest eventRequest = populateRequest(EventRequest.class, ele);
		eventRequest.setEvent(WxEventTypeEnum.instance(strVal(ele, "Event")));
		if (ele.elementText("EventKey") != null) {
			eventRequest.setEventKey(strVal(ele, "EventKey"));
		}
		if (ele.elementText("Ticket") != null) {
			eventRequest.setEventKey(strVal(ele, "Ticket"));
		}
		return eventRequest;
	}
	
	
	/**
	 * 解析出群发结果的request对象
	 * @param ele
	 * @return
	 * @throws DocumentException
	 */
	public static BroadcastFinishEventRequest getBroadcastMsgEvent(Element ele) throws DocumentException {
		BroadcastFinishEventRequest eventRequest = populateRequest(BroadcastFinishEventRequest.class, ele);
		eventRequest.setEvent(WxEventTypeEnum.instance(strVal(ele, "Event")));
		if (ele.elementText("TotalCount") != null) {
			eventRequest.setMsgID(strVal(ele, "MsgID"));
		}
		if (ele.elementText("TotalCount") != null) {
			eventRequest.setTotalCount(intVal(ele, "TotalCount"));
		}
		if (ele.elementText("FilterCount") != null) {
			eventRequest.setFilterCount(intVal(ele, "FilterCount"));
		}
		if (ele.elementText("SentCount") != null) {
			eventRequest.setSentCount(intVal(ele, "SentCount"));
		}
		if (ele.elementText("ErrorCount") != null) {
			eventRequest.setErrorCount(intVal(ele, "ErrorCount"));
		}
		return eventRequest;
	}
	
	
	
	/**
	 * <code>
	 * &lt;xml&gt;<br />
	 * &nbsp;&nbsp;&lt;ToUserName&gt;&lt;![CDATA[toUser]]&gt;&lt;/ToUserName&gt;<br />
	 * &nbsp;&nbsp;&lt;FromUserName&gt;&lt;![CDATA[fromUser]]&gt;&lt;/FromUserName&gt;<br />
	 * &nbsp;&nbsp;&lt;CreateTime&gt;12345678&lt;/CreateTime&gt;<br />
	 * &nbsp;&nbsp;&lt;MsgType&gt;&lt;![CDATA[text]]&gt;&lt;/MsgType&gt;<br />
	 * &nbsp;&nbsp;&lt;Content&gt;&lt;![CDATA[content]]&gt;&lt;/Content&gt;<br />
	 * &nbsp;&nbsp;&lt;FuncFlag&gt;0&lt;/FuncFlag&gt;<br />
	 * &lt;/xml&gt;
	 * </code>
	 * 
	 * @param respText
	 * @return
	 * @throws DocumentException
	 */
	public static Element buildTextResponse(TextResponse textResponse) throws DocumentException {
		Element ele = buildBaseResponse(textResponse);
		ele.addElement("Content").addCDATA(textResponse.getContent());
		return ele;
	}
	
	/**
	 * <code>
	 * &lt;xml&gt;<br />
	 * &nbsp;&nbsp;&lt;ToUserName&gt;&lt;![CDATA[toUser]]&gt;&lt;/ToUserName&gt;<br />
	 * &nbsp;&nbsp;&lt;FromUserName&gt;&lt;![CDATA[fromUser]]&gt;&lt;/FromUserName&gt;<br />
	 * &nbsp;&nbsp;&lt;CreateTime&gt;12345678&lt;/CreateTime&gt;<br />
	 * &nbsp;&nbsp;&lt;MsgType&gt;&lt;![CDATA[image]]&gt;&lt;/MsgType&gt;<br />
	 * &nbsp;&nbsp;&lt;Image&gt;<br />
	 * &nbsp;&nbsp;&nbsp;&nbsp;&lt;MediaId&gt;&lt;![CDATA[media_id]]&gt;&lt;/MediaId&gt;<br />
	 * &nbsp;&nbsp;&lt;/Image&gt;<br />
	 * &lt;/xml&gt;<br />
	 * </code>
	 * 
	 * @param respImage
	 * @return
	 * @throws DocumentException
	 */
//	public static Element getRespImage(WxRespImageEntity respImage) throws DocumentException {
//		Element ele = respEntityFactory(respImage);
//		Element imageEle = ele.addElement("Image");
//		imageEle.addElement("MediaId").addCDATA(respImage.getImage().getMediaId());
//		return ele;
//	}
	
	/**
	 * <code>
	 * &lt;xml&gt;<br />
	 * &nbsp;&nbsp;&lt;ToUserName&gt;&lt;![CDATA[toUser]]&gt;&lt;/ToUserName&gt;<br />
	 * &nbsp;&nbsp;&lt;FromUserName&gt;&lt;![CDATA[fromUser]]&gt;&lt;/FromUserName&gt;<br />
	 * &nbsp;&nbsp;&lt;CreateTime&gt;12345678&lt;/CreateTime&gt;<br />
	 * &nbsp;&nbsp;&lt;MsgType&gt;&lt;![CDATA[voice]]&gt;&lt;/MsgType&gt;<br />
	 * &nbsp;&nbsp;&lt;Voice&gt;<br />
	 * &nbsp;&nbsp;&nbsp;&nbsp;&lt;MediaId&gt;&lt;![CDATA[media_id]]&gt;&lt;/MediaId&gt;<br />
	 * &nbsp;&nbsp;&lt;/Voice&gt;<br />
	 * &lt;/xml&gt;<br />
	 * </code>
	 * 
	 * @param respVoice
	 * @return
	 * @throws DocumentException
	 */
//	public static Element getRespVoice(WxRespVoiceEntity respVoice) throws DocumentException {
//		Element ele = respEntityFactory(respVoice);
//		Element voiceEle = ele.addElement("Voice");
//		voiceEle.addElement("MediaId").addCDATA(respVoice.getVoice().getMediaId());
//		return ele;
//	}
	
	/**
	 * <code>
	 * &lt;xml&gt;<br />
	 * &nbsp;&nbsp;&lt;ToUserName&gt;&lt;![CDATA[toUser]]&gt;&lt;/ToUserName&gt;<br />
	 * &nbsp;&nbsp;&lt;FromUserName&gt;&lt;![CDATA[fromUser]]&gt;&lt;/FromUserName&gt;<br />
	 * &nbsp;&nbsp;&lt;CreateTime&gt;12345678&lt;/CreateTime&gt;<br />
	 * &nbsp;&nbsp;&lt;MsgType&gt;&lt;![CDATA[video]]&gt;&lt;/MsgType&gt;<br />
	 * &nbsp;&nbsp;&lt;Video&gt;<br />
	 * &nbsp;&nbsp;&nbsp;&nbsp;&lt;MediaId&gt;&lt;![CDATA[media_id]]&gt;&lt;/MediaId&gt;<br />
	 * &nbsp;&nbsp;&nbsp;&nbsp;&lt;Title&gt;&lt;![CDATA[title]]&gt;&lt;/Title&gt;<br />
	 * &nbsp;&nbsp;&nbsp;&nbsp;&lt;Description&gt;&lt;![CDATA[description]]&gt;&lt;/Description&gt;<br />
	 * &nbsp;&nbsp;&lt;/Video&gt;<br />
	 * &lt;/xml&gt;<br />
	 * </code>
	 * 
	 * @param respVideo
	 * @return
	 * @throws DocumentException
	 */
//	public static Element getRespVideo(WxRespVideoEntity respVideo) throws DocumentException {
//		Element ele = respEntityFactory(respVideo);
//		Element videoEle = ele.addElement("Video");
//		videoEle.addElement("MediaId").addCDATA(respVideo.getVideo().getMediaId());
//		videoEle.addElement("Title").addCDATA(StringUtils.defaultString(respVideo.getVideo().getTitle()));
//		videoEle.addElement("Description").addCDATA(StringUtils.defaultString(respVideo.getVideo().getDescription()));
//		return ele;
//	}
	
	/**
	 * <code>
	 * &lt;xml&gt;<br />
	 * &nbsp;&nbsp;&lt;ToUserName&gt;&lt;![CDATA[toUser]]&gt;&lt;/ToUserName&gt;<br />
	 * &nbsp;&nbsp;&lt;FromUserName&gt;&lt;![CDATA[fromUser]]&gt;&lt;/FromUserName&gt;<br />
	 * &nbsp;&nbsp;&lt;CreateTime&gt;12345678&lt;/CreateTime&gt;<br />
	 * &nbsp;&nbsp;&lt;MsgType&gt;&lt;![CDATA[music]]&gt;&lt;/MsgType&gt;<br />
	 * &nbsp;&nbsp;&lt;Music&gt;<br />
	 * &nbsp;&nbsp;&nbsp;&nbsp;&lt;Title&gt;&lt;![CDATA[TITLE]]&gt;&lt;/Title&gt;<br />
	 * &nbsp;&nbsp;&nbsp;&nbsp;&lt;Description&gt;&lt;![CDATA[DESCRIPTION]]&gt;&lt;/Description&gt;<br />
	 * &nbsp;&nbsp;&nbsp;&nbsp;&lt;MusicUrl&gt;&lt;![CDATA[MUSIC_Url]]&gt;&lt;/MusicUrl&gt;<br />
	 * &nbsp;&nbsp;&nbsp;&nbsp;&lt;HQMusicUrl&gt;&lt;![CDATA[HQ_MUSIC_Url]]&gt;&lt;/HQMusicUrl&gt;<br />
	 * &nbsp;&nbsp;&nbsp;&nbsp;&lt;ThumbMediaId&gt;&lt;![CDATA[media_id]]&gt;&lt;/ThumbMediaId&gt;<br />
	 * &nbsp;&nbsp;&lt;/Music&gt;<br />
	 * &nbsp;&nbsp;&lt;FuncFlag&gt;0&lt;/FuncFlag&gt;<br />
	 * &lt;/xml&gt;
	 * </code>
	 * 
	 * @param respMusic
	 * @return
	 * @throws DocumentException
	 */
//	public static Element getRespMusic(WxRespMusicEntity respMusic, WxItemThumbEntity thumb) throws DocumentException {
//		Element ele = respEntityFactory(respMusic);
//		Element musicEle = ele.addElement("Music");
//		musicEle.addElement("Title").addCDATA(StringUtils.defaultString(respMusic.getMusic().getTitle()));
//		musicEle.addElement("Description").addCDATA(StringUtils.defaultString(respMusic.getMusic().getDescription()));
//		musicEle.addElement("MusicUrl").addCDATA(StringUtils.defaultString(respMusic.getMusic().getMusicUrl()));
//		musicEle.addElement("HQMusicUrl").addCDATA(StringUtils.defaultString(respMusic.getMusic().getHqMusicUrl()));
//		musicEle.addElement("ThumbMediaId").addCDATA(thumb.getMediaId());
//		return ele;
//	}
	
	/**
	 * <code>
	 * &lt;xml&gt;<br />
 	 * &nbsp;&nbsp;&lt;ToUserName&gt;&lt;![CDATA[toUser]]&gt;&lt;/ToUserName&gt;<br />
 	 * &nbsp;&nbsp;&lt;FromUserName&gt;&lt;![CDATA[fromUser]]&gt;&lt;/FromUserName&gt;<br />
 	 * &nbsp;&nbsp;&lt;CreateTime&gt;12345678&lt;/CreateTime&gt;<br />
 	 * &nbsp;&nbsp;&lt;MsgType&gt;&lt;![CDATA[news]]&gt;&lt;/MsgType&gt;<br />
 	 * &nbsp;&nbsp;&lt;ArticleCount&gt;2&lt;/ArticleCount&gt;<br />
 	 * &nbsp;&nbsp;&lt;Articles&gt;<br />
 	 * &nbsp;&nbsp;&nbsp;&nbsp;&lt;item&gt;<br />
 	 * &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&lt;Title&gt;&lt;![CDATA[title1]]&gt;&lt;/Title&gt;<br />
 	 * &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&lt;Description&gt;&lt;![CDATA[description1]]&gt;&lt;/Description&gt;<br />
 	 * &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&lt;PicUrl&gt;&lt;![CDATA[picurl]]&gt;&lt;/PicUrl&gt;<br />
 	 * &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&lt;Url&gt;&lt;![CDATA[url]]&gt;&lt;/Url&gt;<br />
 	 * &nbsp;&nbsp;&nbsp;&nbsp;&lt;/item&gt;<br />
 	 * &nbsp;&nbsp;&nbsp;&nbsp;&lt;item&gt;<br />
 	 * &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&lt;Title&gt;&lt;![CDATA[title]]&gt;&lt;/Title&gt;<br />
 	 * &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&lt;Description&gt;&lt;![CDATA[description]]&gt;&lt;/Description&gt;<br />
 	 * &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&lt;PicUrl&gt;&lt;![CDATA[picurl]]&gt;&lt;/PicUrl&gt;<br />
 	 * &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&lt;Url&gt;&lt;![CDATA[url]]&gt;&lt;/Url&gt;<br />
 	 * &nbsp;&nbsp;&nbsp;&nbsp;&lt;/item&gt;<br />
 	 * &nbsp;&nbsp;&lt;/Articles&gt;<br />
 	 * &nbsp;&nbsp;&lt;FuncFlag&gt;1&lt;/FuncFlag&gt;<br />
	 * &lt;/xml&gt; 
	 * </code>
	 * 
	 * @param respPicDesc
	 * @return
	 * @throws DocumentException
	 */
	public static Element buildNewsResponse(NewsResponse newsResponse) throws DocumentException {
		Element ele = buildBaseResponse(newsResponse);
		ele.addElement("ArticleCount").addText(String.valueOf(newsResponse.getArticles().size()));
		Element articlesEle = ele.addElement("Articles");
		for (NewsResponse.Item item : newsResponse.getArticles()) {
			Element itemEle = articlesEle.addElement("item");
			itemEle.addElement("Title").addCDATA(item.getTitle());
			itemEle.addElement("Description").addCDATA(item.getDescription());
			itemEle.addElement("PicUrl").addCDATA(item.getPicUrl());
			itemEle.addElement("Url").addCDATA(item.getUrl());
		}
		return ele;
	}
	
	public static Element toXML(String xmlstr) throws DocumentException {
		Document doc = DocumentHelper.parseText(xmlstr);
		return doc.getRootElement();
	}
	
	public static WxMsgTypeEnum getReqType(Element ele) {
		String type = ele.element("MsgType").getTextTrim();
		return WxMsgTypeEnum.instance(type);
	}
	
	
	//////////////////////////////////////////////////
	//                Private Methods               //
    //////////////////////////////////////////////////

	@SuppressWarnings("unchecked")
	private static <T> T populateRequest(Class<? extends BaseRequest> clazz, Element ele) {
		BaseRequest baseRequest;
		try {
			baseRequest = clazz.newInstance();
			baseRequest.setToUserName(strVal(ele, "ToUserName"));
			baseRequest.setFromUserName(strVal(ele, "FromUserName"));
			baseRequest.setCreateTime(strVal(ele, "CreateTime"));
//			baseRequest.setCreatedDate(new Date());
			baseRequest.setMsgType(strVal(ele, "MsgType"));
			if (ele.element("MsgId") != null) {
				baseRequest.setMsgId(strVal(ele, "MsgId"));
			}
			return (T) baseRequest;
		} catch (Exception e) {
			// never occurs
			return null;
		}
	}
	
	/**
	 * 构造响应
	 * @param response
	 * @return
	 */
	private static Element buildBaseResponse(BaseResponse response) {
		Element ele = DocumentHelper.createElement("xml");
		ele.addElement("ToUserName").addCDATA(response.getToUserName());
		ele.addElement("FromUserName").addCDATA(response.getFromUserName());
		String createTime = String.valueOf(response.getCreateTime());
		if (StringUtils.isBlank(createTime)) {
//			//TODO 取当前时间
		}
		ele.addElement("CreateTime").setText(createTime);
		ele.addElement("MsgType").addCDATA(response.getMsgType());
//		ele.addElement("FuncFlag").setText(String.valueOf(response.getFuncFlag()));
		return ele;
	}
	
	private static Integer intVal(Element ele, String name) {
		return Integer.valueOf(ele.element(name).getStringValue());
	}
	
	private static String strVal(Element ele, String name) {
		return ele.element(name).getStringValue();
	}

	private static Long longVal(Element ele, String name) {
		return Long.valueOf(ele.element(name).getStringValue());
	}

	private static Double doubleVal(Element ele, String name) {
		return Double.valueOf(ele.element(name).getStringValue());
	}

}
