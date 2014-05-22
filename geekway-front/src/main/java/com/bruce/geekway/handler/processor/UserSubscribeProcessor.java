package com.bruce.geekway.handler.processor;

import com.bruce.geekway.model.wx.request.EventRequest;
import com.bruce.geekway.model.wx.request.ImageRequest;
import com.bruce.geekway.model.wx.request.LocationRequest;
import com.bruce.geekway.model.wx.request.TextRequest;
import com.bruce.geekway.model.wx.request.VideoRequest;
import com.bruce.geekway.model.wx.request.VoiceRequest;
import com.bruce.geekway.model.wx.response.BaseResponse;
import com.bruce.geekway.service.IWxMpUserService;


/**
 * 用户订阅的processor，主要用于向db中写入数据
 * @author liqian
 *
 */
//@Service
public class UserSubscribeProcessor extends AbstractProcessor{ 
    
//	private WxUserService wxUserService;
	
	private IWxMpUserService mpUserService;
	
	
	
    @Override
	protected synchronized void preProcess() {
    	//do nothing
	}

	@Override
	protected BaseResponse processTextRequest(TextRequest request) {
		return null;
	}

	@Override
	protected BaseResponse processVideoRequest(VideoRequest request) {
		return null;
	}

	@Override
	protected BaseResponse processClickEventRequest(EventRequest request) {
		return null;
	}
	
	@Override
	protected BaseResponse processSubscribeEventRequest(EventRequest request) {
		//交给UserService处理，新增用户数据
		try{
			int result = mpUserService.newSubscribeUser(request.getFromUserName());
		}catch(Exception e){
		}
		return null;
	}

	@Override
	protected BaseResponse processLocationRequest(LocationRequest request) {
		return null;
	}

	@Override
	protected BaseResponse processVoiceRequest(VoiceRequest request) {
		return null;
	}

	@Override
	protected BaseResponse processImageRequest(ImageRequest request) {
		return null;
	}

	public IWxMpUserService getMpUserService() {
		return mpUserService;
	}

	public void setMpUserService(IWxMpUserService mpUserService) {
		this.mpUserService = mpUserService;
	}
	
	
}
