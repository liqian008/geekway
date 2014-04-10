package com.bruce.geekway.handler.processor;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bruce.geekway.model.WxDefaultReply;
import com.bruce.geekway.model.wx.request.*;
import com.bruce.geekway.model.wx.response.*;
import com.bruce.geekway.service.IWxDefaultReplyService;


/**
 * 默认processor
 * 用于没有任何数据返回时的文字提示内容（配置文件中配置）
 * @author liqian
 *
 */
//@Service
public class DefaultReplyProcessor extends AbstractProcessor{ 
    
    @Autowired
    private IWxDefaultReplyService defaultReplyService;
    
    private WxDefaultReply defaultReply = null;
    
//    public DefaultReplyProcessor(int sort) {
//		super(sort);
//	}
    
//    public BaseResponse process(BaseRequest request){
//        
//        defaultReply = defaultReplyService.loadById(1);
//        if(defaultReply==null||defaultReply.getId()<=0){
//            return null;
//        }
//        
//        if(request instanceof TextRequest){ 
//            //文本默认回复
//            return textReply(request, defaultReply.getTextReply());
//        }else if(request instanceof ImageRequest){
//            //图片默认回复
//            return textReply(request, defaultReply.getImageReply());
//        }else if(request instanceof VoiceRequest){
//            //语音默认回复
//            return textReply(request, defaultReply.getVoiceReply());
//        }else if(request instanceof EventRequest){
//            //菜单点击默认回复
//            return textReply(request, defaultReply.getMenuClickReply());
//        }else if(request instanceof LocationRequest){
//            //位置默认回复
//            return textReply(request, defaultReply.getLocationReply());
//        }else if(request instanceof VideoRequest){
//            //视频默认回复
//            return textReply(request, defaultReply.getVideoReply());
//        }
//        return null;
//    }
    
    @Override
	protected void preProcess() {
    	 defaultReply = defaultReplyService.loadById(1);
	}


	@Override
	protected BaseResponse processTextRequest(TextRequest request) {
		if(defaultReply!=null){
			return textReply(request, defaultReply.getTextReply());
		}
		return null;
	}

	@Override
	protected BaseResponse processVideoRequest(VideoRequest request) {
		if(defaultReply!=null){
			return textReply(request, defaultReply.getVideoReply());
		}
		return null;
	}

	@Override
	protected BaseResponse processEventRequest(EventRequest request) {
		if(defaultReply!=null){
			return textReply(request, defaultReply.getMenuClickReply());
		}
		return null;
	}

	@Override
	protected BaseResponse processLocationRequest(LocationRequest request) {
		if(defaultReply!=null){
			return textReply(request, defaultReply.getLocationReply());
		}
		return null;
	}

	@Override
	protected BaseResponse processVoiceRequest(VoiceRequest request) {
		if(defaultReply!=null){
			return textReply(request, defaultReply.getVoiceReply());
		}
		return null;
	}

	@Override
	protected BaseResponse processImageRequest(ImageRequest request) {
		if(defaultReply!=null){
			return textReply(request, defaultReply.getImageReply());
		}
		return null;
	}

	public IWxDefaultReplyService getDefaultReplyService() {
		return defaultReplyService;
	}

	public void setDefaultReplyService(IWxDefaultReplyService defaultReplyService) {
		this.defaultReplyService = defaultReplyService;
	}
}
