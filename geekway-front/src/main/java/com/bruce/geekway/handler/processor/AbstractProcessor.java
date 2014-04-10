package com.bruce.geekway.handler.processor;

import com.bruce.geekway.model.wx.request.BaseRequest;
import com.bruce.geekway.model.wx.request.EventRequest;
import com.bruce.geekway.model.wx.request.ImageRequest;
import com.bruce.geekway.model.wx.request.LocationRequest;
import com.bruce.geekway.model.wx.request.TextRequest;
import com.bruce.geekway.model.wx.request.VideoRequest;
import com.bruce.geekway.model.wx.request.VoiceRequest;
import com.bruce.geekway.model.wx.response.BaseResponse;
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
        }else if(request instanceof EventRequest){
            //菜单点击默认回复
        	return processEventRequest((EventRequest)request);
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
	
	protected abstract BaseResponse processTextRequest(TextRequest request);
	
	protected abstract BaseResponse processVideoRequest(VideoRequest request);

	protected abstract BaseResponse processEventRequest(EventRequest request);

	protected abstract BaseResponse processLocationRequest(LocationRequest request);

	protected abstract BaseResponse processVoiceRequest(VoiceRequest request);

	protected abstract BaseResponse processImageRequest(ImageRequest request);

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
    
//    public int getSort() {
//		return sort;
//	}
//
//	public void setSort(int sort) {
//		this.sort = sort;
//	}
}
