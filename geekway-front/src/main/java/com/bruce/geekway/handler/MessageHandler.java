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
import com.bruce.geekway.service.IWxHistoryMessageService;
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
	
	private List<Processor> eventViewProcessorList;
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
	@Autowired
	private IWxHistoryMessageService historyMessageService;
	
	public BaseResponse processMessage(String xml) throws Exception {
		System.out.println("request xml: " + xml);
		Element ele = DocumentHelper.parseText(xml).getRootElement();
		String msgType = ele.elementText("MsgType");
		if (msgType == null) {
			throw new Exception("cannot find MsgType Node!" + xml);
		}
		
		BaseRequest wxRequest = null;
		BaseResponse wxResponse = null;
		WxMsgTypeEnum msgTypeEnum = WxMsgTypeEnum.instance(msgType);
		switch (msgTypeEnum) {
		case TEXT:
			TextRequest textRequest = WxXmlUtil.getMsgText(ele);//解析文本请求
			wxRequest = textRequest;
			wxResponse =  processTextRequest(textRequest);
			break;
		case IMAGE:
			ImageRequest imageRequest = WxXmlUtil.getMsgImage(ele);//解析图片消息
			wxRequest = imageRequest;
			wxResponse = processImageRequest(imageRequest);
			break;
		case VOICE:
			VoiceRequest voiceRequest = WxXmlUtil.getMsgVoice(ele);//解析voice消息
			wxRequest = voiceRequest;
			wxResponse =  processVoiceRequest(voiceRequest);
			break;
		case EVENT: {
			//Event消息暂不需要保存到历史消息
			String event = ele.elementText("Event");
			if (event == null) {
				throw new Exception("cannot find Event Node!" + xml);
			}
			WxEventTypeEnum eventTypeEnum = WxEventTypeEnum.instance(event);
			switch (eventTypeEnum) {
				case SUBSCRIBE: {// 订阅关注
					EventRequest eventRequest = WxXmlUtil.getMsgEvent(ele);
					wxRequest = eventRequest;
					String fromOpenId = eventRequest.getFromUserName();
					WxMpUser wxUser = mpUserService.loadByOpenId(fromOpenId);
					if(wxUser==null){//全新用户关注
						mpUserService.newSubscribeUser(fromOpenId);//作为系统级操作，向用户表中写入新数据（脱离processor处理系统的订阅事件）
						wxResponse = processEventFirstSubscribeRequest(eventRequest);
						break;
					}else{//之前存在过，属于重复关注
						mpUserService.repeatSubscribeUser(fromOpenId);//作为系统级操作，更新用户表的关注状态（脱离processor处理系统的订阅事件）
						eventRequest.setEvent(WxEventTypeEnum.RESUBSCRIBE);//设置为重复关注类型
						wxResponse = processEventRepeatSubscribeRequest(eventRequest);
						break;
					}
				}
				case UNSUBSCRIBE: {// 退订
					EventRequest eventRequest = WxXmlUtil.getMsgEvent(ele);
					wxRequest = eventRequest;
					//作为系统级操作，向用户表中更新数据（脱离processor处理系统的退订事件）
					mpUserService.unsubscribeUser(eventRequest.getFromUserName());
					wxResponse = processEventUnsubscribeRequest(eventRequest);
					break;
				}
				case CLICK: {// 点击菜单
					EventRequest eventRequest = WxXmlUtil.getMsgEvent(ele);
					wxRequest = eventRequest;
					wxResponse = processEventClickRequest(eventRequest);
					break;
				}
				case VIEW: {// 点击菜单链接跳转（wiki文档中写到会发送该消息，但实际未检测到该消息）
					EventRequest eventRequest = WxXmlUtil.getMsgEvent(ele);
					wxRequest = eventRequest;
					wxResponse = null;//点击菜单链接跳转，无需返回数据（此处作为特殊处理）
					break;
				}
				case LOCATION: {// 上报location位置
					LocationEventRequest locationEventRequest = new LocationEventRequest();
					wxRequest = locationEventRequest;
					wxResponse = processEventLocationRequest(locationEventRequest);
					break;
				}
				case SCAN: {
					break;
				}
				case BROADCAST_FINISH: {//群发广播
					BroadcastFinishEventRequest broadcastFinishRrequest = WxXmlUtil.getBroadcastMsgEvent(ele);
					wxRequest = broadcastFinishRrequest;
					//作为系统级操作，向表中回写群发结果数据（脱离processor处理系统的订阅事件）
					broadcastService.broadcastNofify(broadcastFinishRrequest.getMsgID(), broadcastFinishRrequest.getTotalCount(), broadcastFinishRrequest.getFilterCount(), broadcastFinishRrequest.getSentCount(), broadcastFinishRrequest.getErrorCount());
					wxResponse = null;//系统群发广播，无需返回数据（此处作为特殊处理）
					break;
				}
				default: {
					break;
				}
			}
		}
		default:
			break;
		}
		if(wxRequest!=null){
			//更新用户的消息时间，便于进行客服消息回复的判断
			
			
			//将wx消息写入历史消息，便于查阅
			historyMessageService.logRequestMessage(wxRequest, xml);
		}
		return wxResponse;
	}

	/**
	 * 解析生成响应数据
	 * 
	 * @param wxResponse
	 * @return
	 * @throws Exception
	 */
	public String parseXMLResp(BaseResponse wxResponse) throws Exception {
		WxMsgRespTypeEnum responseType = WxMsgRespTypeEnum.instance(wxResponse.getMsgType());
		String responseStr = "";
		switch (responseType) {
		case NEWS:
			responseStr = WxXmlUtil.buildNewsResponse((NewsResponse) wxResponse).asXML();
			break;
		case TEXT:
			responseStr = WxXmlUtil.buildTextResponse((TextResponse) wxResponse).asXML();
			break;
			
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
		historyMessageService.logResponseMessage(wxResponse, responseStr);
		return responseStr;
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
	
	protected BaseResponse processEventViewRequest(EventRequest request) {
		return process(request, eventViewProcessorList);
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

	public List<Processor> getEventViewProcessorList() {
		return eventViewProcessorList;
	}

	public void setEventViewProcessorList(List<Processor> eventViewProcessorList) {
		this.eventViewProcessorList = eventViewProcessorList;
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

	public IWxHistoryMessageService getHistoryMessageService() {
		return historyMessageService;
	}

	public void setHistoryMessageService(
			IWxHistoryMessageService historyMessageService) {
		this.historyMessageService = historyMessageService;
	}
	
}
