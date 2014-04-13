package com.bruce.geekway.handler.processor;

import java.util.List;

import com.bruce.geekway.model.WxArticle;
import com.bruce.geekway.model.wx.request.BaseRequest;
import com.bruce.geekway.model.wx.request.ClickEventRequest;
import com.bruce.geekway.model.wx.request.EventRequest;
import com.bruce.geekway.model.wx.request.ImageRequest;
import com.bruce.geekway.model.wx.request.LocationRequest;
import com.bruce.geekway.model.wx.request.SubscribeEventRequest;
import com.bruce.geekway.model.wx.request.TextRequest;
import com.bruce.geekway.model.wx.request.VideoRequest;
import com.bruce.geekway.model.wx.request.VoiceRequest;
import com.bruce.geekway.model.wx.response.BaseResponse;
import com.bruce.geekway.model.wx.response.NewsResponse;
import com.bruce.geekway.model.wx.response.TextResponse;


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
        	if(request instanceof ClickEventRequest){
                //菜单点击默认回复
            	return processClickEventRequest((ClickEventRequest)request);
            }else if(request instanceof SubscribeEventRequest){
                //关注事件
            	return processSubscribeEventRequest((SubscribeEventRequest)request); 
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

	protected BaseResponse processClickEventRequest(ClickEventRequest request) {
		return null;
	}
	
	protected BaseResponse processSubscribeEventRequest(SubscribeEventRequest request) {
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
        TextResponse textResponse = new TextResponse(fromUserName, toUserName, content);
        return textResponse;
    }
    
    /**
     * 图文回复
     * @param request
     * @param content
     * @return
     */
	public static NewsResponse newsReply(BaseRequest request, List<WxArticle> articleList){
        String fromUserName = request.getFromUserName();
        String toUserName = request.getToUserName();
        //交换fromUserName和toUserName
        return newsReply(fromUserName, toUserName, articleList);
    }

	private static NewsResponse newsReply(String fromUserName, String toUserName, List<WxArticle> articleList) {
		if(articleList!=null&&articleList.size()>0){
			NewsResponse newsResponse = new NewsResponse();
			for(WxArticle article: articleList){
				newsResponse.addArticle(article.getShortTitle(), article.getShortContent(), article.getLink());
			}
			return newsResponse;
		}
		return null;
	}
}
