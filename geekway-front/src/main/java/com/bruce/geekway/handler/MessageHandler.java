package com.bruce.geekway.handler;

import java.util.List;

import org.dom4j.DocumentHelper;
import org.dom4j.Element;
import org.springframework.beans.factory.annotation.Autowired;

import com.bruce.geekway.handler.processor.Processor;
import com.bruce.geekway.model.WxMpUser;
import com.bruce.geekway.model.wx.WxEventTypeEnum;
import com.bruce.geekway.model.wx.WxMsgRespTypeEnum;
import com.bruce.geekway.model.wx.WxMsgTypeEnum;
import com.bruce.geekway.model.wx.request.BaseRequest;
import com.bruce.geekway.model.wx.request.BroadcastFinishEventRequest;
import com.bruce.geekway.model.wx.request.EventRequest;
import com.bruce.geekway.model.wx.request.ImageRequest;
import com.bruce.geekway.model.wx.request.LocationEventRequest;
import com.bruce.geekway.model.wx.request.TextRequest;
import com.bruce.geekway.model.wx.request.VoiceRequest;
import com.bruce.geekway.model.wx.response.BaseResponse;
import com.bruce.geekway.model.wx.response.NewsResponse;
import com.bruce.geekway.model.wx.response.TextResponse;
import com.bruce.geekway.service.IWxBroadcastService;
import com.bruce.geekway.service.IWxMpUserService;
import com.bruce.geekway.utils.WxXmlUtil;

/**
 * 处理微信消息的handler
 * @author liqian
 *
 */
//@Service
public class MessageHandler {
	// @Resource
	// @Qualifier("textProcessorList")
	// @Autowired
	private List<Processor> textProcessorList;
	// @Autowired
	private List<Processor> imageProcessorList;
	// @Autowired
	private List<Processor> voiceProcessorList;
	// @Autowired
	private List<Processor> eventClickProcessorList;
	@Autowired
	private List<Processor> eventFirstSubscribeProcessorList;
	@Autowired
	private List<Processor> eventRepeatSubscribeProcessorList;
	@Autowired
	private List<Processor> eventUnsubscribeProcessorList;
	@Autowired
	private List<Processor> eventLocationProcessorList;
	@Autowired
	private IWxMpUserService mpUserService;
	@Autowired
	private IWxBroadcastService broadcastService;
	
	public BaseResponse processMessage(String xml) throws Exception {
		System.out.println("request xml: " + xml);
		Element ele = DocumentHelper.parseText(xml).getRootElement();
		String msgType = ele.elementText("MsgType");
		if (msgType == null) {
			throw new Exception("cannot find MsgType Node!" + xml);
		}
		WxMsgTypeEnum msgTypeEnum = WxMsgTypeEnum.instance(msgType);
		switch (msgTypeEnum) {
		case TEXT:
			TextRequest textRequest = WxXmlUtil.getMsgText(ele);
			return processTextRequest(textRequest);
		case EVENT: {
			String event = ele.elementText("Event");
			if (event == null) {
				throw new Exception("cannot find Event Node!" + xml);
			}
			WxEventTypeEnum eventTypeEnum = WxEventTypeEnum.instance(event);
			switch (eventTypeEnum) {
				case SUBSCRIBE: {// 订阅关注
					EventRequest eventRequest = WxXmlUtil.getMsgEvent(ele);
					String fromOpenId = eventRequest.getFromUserName();
					WxMpUser wxUser = mpUserService.loadByOpenId(fromOpenId);
					if(wxUser==null){//全新用户关注
						mpUserService.newSubscribeUser(fromOpenId);//作为系统级操作，向用户表中写入新数据（脱离processor处理系统的订阅事件）
						return processEventFirstSubscribeRequest(eventRequest);
					}else{//之前存在过，属于重复关注
						mpUserService.repeatSubscribeUser(fromOpenId);//作为系统级操作，更新用户表的关注状态（脱离processor处理系统的订阅事件）
						eventRequest.setEvent(WxEventTypeEnum.RESUBSCRIBE);//设置为重复关注类型
						return processEventRepeatSubscribeRequest(eventRequest);
					}
				}
				case UNSUBSCRIBE: {// 退订
					EventRequest eventRequest = WxXmlUtil.getMsgEvent(ele);
					//作为系统级操作，向用户表中更新数据（脱离processor处理系统的退订事件）
					mpUserService.unsubscribeUser(eventRequest.getFromUserName());
					return processEventUnsubscribeRequest(eventRequest);
				}
				case CLICK: {// 点击菜单
					EventRequest eventRequest = WxXmlUtil.getMsgEvent(ele);
					return processEventClickRequest(eventRequest);
				}
				case VIEW: {// 点击菜单链接跳转（wiki文档中写到会发送该消息，但实际未检测到该消息）
					break;
				}
				case LOCATION: {// 上报location位置
					LocationEventRequest locationEventRequest = new LocationEventRequest();
					return processEventLocationRequest(locationEventRequest);
				}
				case SCAN: {
					break;
				}
				case BROADCAST_FINISH: {
					BroadcastFinishEventRequest request = WxXmlUtil.getBroadcastMsgEvent(ele);
					//作为系统级操作，向表中回写群发结果数据（脱离processor处理系统的订阅事件）
					broadcastService.broadcastNofify(request.getMsgID(), request.getTotalCount(), request.getFilterCount(), request.getSentCount(), request.getErrorCount());
					break;
				}
				default: {
					break;
				}
			}
		}
		default:
			return null;
		}
	}

	/**
	 * 解析生成响应数据
	 * 
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public String parseXMLResp(BaseResponse response) throws Exception {
		WxMsgRespTypeEnum responseType = WxMsgRespTypeEnum.instance(response.getMsgType());
		switch (responseType) {
		case NEWS:
			return WxXmlUtil.buildNewsResponse((NewsResponse) response).asXML();
		case TEXT:
			return WxXmlUtil.buildTextResponse((TextResponse) response).asXML();
			// 以下功能暂不支持
			// case IMAGE:
			// return WxXmlUtil.getRespImage((WxRespImageEntity) resp);
			// case MUSIC:
			// return WxXmlUtil.getRespMusic((WxRespMusicEntity) resp,
			// ((WxRespMusicEntity) resp).getThumb());
			// case VIDEO:
			// return WxXmlUtil.getRespVideo((WxRespVideoEntity) resp);
			// case VOICE:
			// return WxXmlUtil.getRespVoice((WxRespVoiceEntity) resp);
		default:
			break;
		}
		return null;
	}

	/**
	 * 根据配置的processorList操作request
	 * 
	 * @param request
	 * @param processList
	 * @return
	 */
	private BaseResponse process(BaseRequest request, List<Processor> processList) {
		if (processList != null && processList.size() > 0) {
			for (Processor processor : processList) {
				BaseResponse response = processor.process(request);
				// 有数据则返回，无数据则继续使用下一个processor处理
				if (response != null) {
					return response;
				}
			}
		}
		return null;
	}

	protected BaseResponse processTextRequest(TextRequest request) {
		return process(request, textProcessorList);
	}

	protected BaseResponse processImageRequest(ImageRequest request) {
		return process(request, imageProcessorList);
	}

	protected BaseResponse processVoiceRequest(VoiceRequest request) {

		return process(request, voiceProcessorList);
	}

	protected BaseResponse processEventFirstSubscribeRequest(EventRequest request) {
		return process(request, eventFirstSubscribeProcessorList);
	}
	
	protected BaseResponse processEventRepeatSubscribeRequest(EventRequest request) {
		return process(request, eventRepeatSubscribeProcessorList);
	}
	
	protected BaseResponse processEventUnsubscribeRequest(EventRequest request) {
		return process(request, eventUnsubscribeProcessorList);
	}

	protected BaseResponse processEventClickRequest(EventRequest request) {
		return process(request, eventClickProcessorList);
	}

	protected BaseResponse processEventLocationRequest(EventRequest request) {
		return process(request, eventLocationProcessorList);
	}

	public List<Processor> getTextProcessorList() {
		return textProcessorList;
	}

	public void setTextProcessorList(List<Processor> textProcessorList) {
		this.textProcessorList = textProcessorList;
	}

	public List<Processor> getImageProcessorList() {
		return imageProcessorList;
	}

	public void setImageProcessorList(List<Processor> imageProcessorList) {
		this.imageProcessorList = imageProcessorList;
	}

	public List<Processor> getVoiceProcessorList() {
		return voiceProcessorList;
	}

	public void setVoiceProcessorList(List<Processor> voiceProcessorList) {
		this.voiceProcessorList = voiceProcessorList;
	}

	public List<Processor> getEventClickProcessorList() {
		return eventClickProcessorList;
	}

	public void setEventClickProcessorList(List<Processor> eventClickProcessorList) {
		this.eventClickProcessorList = eventClickProcessorList;
	}

	public List<Processor> getEventFirstSubscribeProcessorList() {
		return eventFirstSubscribeProcessorList;
	}

	public void setEventFirstSubscribeProcessorList(List<Processor> eventFirstSubscribeProcessorList) {
		this.eventFirstSubscribeProcessorList = eventFirstSubscribeProcessorList;
	}

	public List<Processor> getEventRepeatSubscribeProcessorList() {
		return eventRepeatSubscribeProcessorList;
	}

	public void setEventRepeatSubscribeProcessorList(List<Processor> eventRepeatSubscribeProcessorList) {
		this.eventRepeatSubscribeProcessorList = eventRepeatSubscribeProcessorList;
	}

	public List<Processor> getEventLocationProcessorList() {
		return eventLocationProcessorList;
	}

	public void setEventLocationProcessorList(List<Processor> eventLocationProcessorList) {
		this.eventLocationProcessorList = eventLocationProcessorList;
	}

	public List<Processor> getEventUnsubscribeProcessorList() {
		return eventUnsubscribeProcessorList;
	}

	public void setEventUnsubscribeProcessorList(
			List<Processor> eventUnsubscribeProcessorList) {
		this.eventUnsubscribeProcessorList = eventUnsubscribeProcessorList;
	}

	public IWxMpUserService getMpUserService() {
		return mpUserService;
	}

	public void setMpUserService(IWxMpUserService mpUserService) {
		this.mpUserService = mpUserService;
	}

	public IWxBroadcastService getBroadcastService() {
		return broadcastService;
	}

	public void setBroadcastService(IWxBroadcastService broadcastService) {
		this.broadcastService = broadcastService;
	}

}
