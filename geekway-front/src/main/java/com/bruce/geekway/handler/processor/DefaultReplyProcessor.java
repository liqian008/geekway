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
	protected BaseResponse processClickEventRequest(ClickEventRequest request) {
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
