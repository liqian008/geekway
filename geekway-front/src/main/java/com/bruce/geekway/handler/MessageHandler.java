package com.bruce.geekway.handler;


import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bruce.geekway.handler.processor.DefaultReplyProcessor;
import com.bruce.geekway.handler.processor.Processor;
import com.bruce.geekway.model.wx.WxEventTypeEnum;
import com.bruce.geekway.model.wx.WxMsgTypeEnum;
import com.bruce.geekway.model.wx.request.*;
import com.bruce.geekway.model.wx.response.BaseResponse;


//@Service
public class MessageHandler{
//    @Resource
//    @Qualifier("textProcessorList")
//	@Autowired
    private List<Processor> textProcessorList;
//	@Autowired
    private List<Processor> imageProcessorList;
//	@Autowired
    private List<Processor> voiceProcessorList;
//	@Autowired
    private List<Processor> eventClickProcessorList;
    @Autowired
    private List<Processor> eventSubscribeProcessorList;
    @Autowired
    private List<Processor> eventLocationProcessorList;

    protected BaseResponse processTextRequest(TextRequest request) {
        return process(request, textProcessorList);
    }

    protected BaseResponse processImageRequest(ImageRequest request) {
        return process(request, imageProcessorList);
    }

    protected BaseResponse processVoiceRequest(VoiceRequest request) {
    	
    	return process(request, voiceProcessorList);
    }
    
    protected BaseResponse processEventSubscribeRequest(EventRequest request) { 
        return process(request, eventSubscribeProcessorList);
    }
    
    protected BaseResponse processEventClickRequest(EventRequest request) {
        return process(request, eventClickProcessorList);
    }
    
    protected BaseResponse processEventLocationRequest(EventRequest request) {
        return process(request, eventLocationProcessorList);
    }
    
    public BaseResponse processMessage(String xml) throws Exception{
//        Element ele = DocumentHelper.parseText(xml).getRootElement();
//        String msgType = null;
//        if ((msgType = ele.elementText("MsgType")) == null) {
////            throw new WxException("cannot find MsgType Node!\n" + xml);
//        }
        WxMsgTypeEnum msgTypeEnum = WxMsgTypeEnum.EVENT;//WxMsgTypeEnum.instance(msgType); 
        switch (msgTypeEnum) {
        case TEXT:
//            TextRequest textRequest = WxXmlUtil.getMsgText(ele);
            TextRequest textRequest = new TextRequest();
            textRequest.setContent("welcome");
            return processTextRequest(textRequest);
        case EVENT:{
        	WxEventTypeEnum eventTypeEnum = WxEventTypeEnum.CLICK;//WxEventTypeEnum.instance(""); 
        	switch(eventTypeEnum){
        		case SUBSCRIBE: {//订阅关注
        			SubscribeEventRequest subscribeEventRequest = new SubscribeEventRequest();
                    return processEventSubscribeRequest(subscribeEventRequest);
        		}
        		case CLICK: {//点击菜单
        			ClickEventRequest clickEventRequest = new ClickEventRequest(WxEventTypeEnum.CLICK.toString());
        			clickEventRequest.setEventKey("welcome");
                    return processEventClickRequest(clickEventRequest);
        		}
        		case VIEW: {//点击菜单
        			ClickEventRequest clickEventRequest = new ClickEventRequest(WxEventTypeEnum.VIEW.toString());
                    return processEventClickRequest(clickEventRequest);
        		}
        		case LOCATION: {//上报location位置
        			LocationEventRequest locationEventRequest = new LocationEventRequest();
                    return processEventLocationRequest(locationEventRequest);
        		}
        		case SCAN: {
        			//TODO
        		}
        		case UNSUBSCRIBE: {//退订
        			//do nothing
        		}
        		default: {
        			//do nothing
        		}
        	}
        }default:
            return null;
        }
    }
    
    /**
     * 根据配置的processorList操作request
     * @param request
     * @param processList
     * @return
     */
    private BaseResponse process(BaseRequest request, List<Processor> processList) {
        if(processList!=null&&processList.size()>0){
            for(Processor processor: processList){
                BaseResponse response = processor.process(request);
                //有数据则返回，无数据则继续使用下一个processor处理
                if(response!=null){
                    return response;
                }
            }
        }
        return null;
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

	public List<Processor> getEventSubscribeProcessorList() {
		return eventSubscribeProcessorList;
	}

	public void setEventSubscribeProcessorList(List<Processor> eventSubscribeProcessorList) {
		this.eventSubscribeProcessorList = eventSubscribeProcessorList;
	}

	public List<Processor> getEventLocationProcessorList() {
		return eventLocationProcessorList;
	}

	public void setEventLocationProcessorList(List<Processor> eventLocationProcessorList) {
		this.eventLocationProcessorList = eventLocationProcessorList;
	}
    

}
