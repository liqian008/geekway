package com.bruce.geekway.handler.processor;

import java.util.List;

import com.bruce.geekway.model.WxMaterialArticle;
import com.bruce.geekway.model.wx.WxEventTypeEnum;
import com.bruce.geekway.model.wx.request.BaseRequest;
import com.bruce.geekway.model.wx.request.EventRequest;
import com.bruce.geekway.model.wx.request.ImageRequest;
import com.bruce.geekway.model.wx.request.LocationRequest;
import com.bruce.geekway.model.wx.request.TextRequest;
import com.bruce.geekway.model.wx.request.VideoRequest;
import com.bruce.geekway.model.wx.request.VoiceRequest;
import com.bruce.geekway.model.wx.response.BaseResponse;
import com.bruce.geekway.model.wx.response.NewsResponse;
import com.bruce.geekway.model.wx.response.TextResponse;
import com.bruce.geekway.utils.MaterialLinkUtil;
//import com.bruce.geekway.model.WxMaterial;


public abstract class AbstractProcessor implements Processor{
	
//	private int sort;
//	
//	protected AbstractProcessor(int sort){
//		this.sort = sort;
//	}
	
	public BaseResponse process(BaseRequest request){
		//预处理
		preProcess();
		
		if(request instanceof TextRequest){ 
            //文本默认回复
            return processTextRequest((TextRequest)request);
        }else if(request instanceof ImageRequest){
            //图片默认回复
        	return processImageRequest((ImageRequest)request);
        }else if(request instanceof VoiceRequest){
            //语音默认回复
        	return processVoiceRequest((VoiceRequest)request);
        }else if(request instanceof EventRequest){//事件请求
        	EventRequest eventRequest = (EventRequest) request;
        	//获取具体事件类型
        	WxEventTypeEnum eventType = ((EventRequest)request).getEvent();
        	if(WxEventTypeEnum.SUBSCRIBE.equals(eventType)){//订阅事件
        		//订阅事件
            	return processSubscribeEventRequest(eventRequest);
        	}if(WxEventTypeEnum.UNSUBSCRIBE.equals(eventType)){//订阅事件
        		//退订事件
            	return processUnsubscribeEventRequest(eventRequest);
        	}else if(WxEventTypeEnum.CLICK.equals(eventType)){//点击事件
            	return processClickEventRequest(eventRequest); 
        	}else if(WxEventTypeEnum.VIEW.equals(eventType)){//View事件
            	return processClickEventRequest(eventRequest); 
        	}
        }else if(request instanceof LocationRequest){
            //位置默认回复
        	return processLocationRequest((LocationRequest)request);
        }else if(request instanceof VideoRequest){
            //视频默认回复
        	return processVideoRequest((VideoRequest)request);
        }
		return null;
    }
	
	protected void preProcess() {
		//do nothing
	}

	protected BaseResponse processVideoRequest(VideoRequest request) {
		return null;
	}

	protected BaseResponse processTextRequest(TextRequest request) {
		return null;
	}

	protected BaseResponse processClickEventRequest(EventRequest request) {
		return null;
	}
	
	protected BaseResponse processSubscribeEventRequest(EventRequest request) {
		return null;
	}
	
	protected BaseResponse processUnsubscribeEventRequest(EventRequest request) {
		return null;
	}

	protected BaseResponse processLocationRequest(LocationRequest request) {
		return null;
	}

	protected BaseResponse processVoiceRequest(VoiceRequest request) {
		return null;
	}

	protected BaseResponse processImageRequest(ImageRequest request) {
		return null;
	}

	/**
     * 文本回复
     * @param request
     * @param content
     * @return
     */
	public static TextResponse textReply(BaseRequest request, String content){
        String fromUserName = request.getFromUserName();
        String toUserName = request.getToUserName();
        //交换fromUserName和toUserName
        return textReply(fromUserName, toUserName, content);
    }
    
    
    /**
     * 文本回复
     * @param toUserName
     * @param fromUserName
     * @param content
     * @return
     */
    protected static TextResponse textReply(String toUserName, String fromUserName, String content){
        TextResponse textResponse = new TextResponse(toUserName, fromUserName, content);
        return textResponse;
    }
    
    /**
     * 图文回复
     * @param request
     * @param Content
     * @return
     */
	public static NewsResponse newsReply(BaseRequest request, List<WxMaterialArticle> materialArticleList){
        String fromUserName = request.getFromUserName();
        String toUserName = request.getToUserName();
        //交换fromUserName和toUserName
        return newsReply(fromUserName, toUserName, materialArticleList);
    }

	private static NewsResponse newsReply(String toUserName, String fromUserName, List<WxMaterialArticle> materialArticleList){
		if(materialArticleList!=null&&materialArticleList.size()>0){
			NewsResponse newsResponse = new NewsResponse(toUserName, fromUserName);
			for(WxMaterialArticle article: materialArticleList){
				newsResponse.addArticle(article.getShortTitle(), article.getShortContent(), article.getCoverImageUrl(), MaterialLinkUtil.getMaterialLink(article.getId()));
			}
			return newsResponse;
		}
		return null;
	}
}
