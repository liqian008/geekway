package com.bruce.geekway.handler.processor;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bruce.geekway.model.WxCustomizeMenu;
import com.bruce.geekway.model.WxTextCode;
import com.bruce.geekway.model.wx.request.*;
import com.bruce.geekway.model.wx.response.*;
import com.bruce.geekway.service.IWxCustomizeMenuService;
import com.bruce.geekway.service.IWxTextCodeService;

/**
 * 根据Key值，返回DB中对应的数据
 * 目前仅支持文本与菜单类型消息
 * @author liqian
 *
 */
//@Service
public class KeycodeCmsProcessor extends AbstractProcessor{
    
//	public KeycodeCmsProcessor(int sort) {
//		super(sort);
//	}

	@Autowired
    private IWxTextCodeService textCodeService;
	@Autowired
    private IWxCustomizeMenuService customizeMenuService;
    
	
	@Override
	protected BaseResponse processTextRequest(TextRequest request) {
		String code = ((TextRequest)request).getContent();
        
        WxTextCode textCode = textCodeService.loadByCode(code);
        if(textCode!=null){
        	return textReply(request, textCode.getReplyContent());
        }
		return null;
	}
	
	@Override
	protected BaseResponse processVideoRequest(VideoRequest request) {
		return null;
	}

	@Override
	protected BaseResponse processEventRequest(EventRequest request) {
		String key = ((EventRequest)request).getEventKey();
//      return textCodeService.loadByMenuCode(key);
		
		WxCustomizeMenu customizeMenu = customizeMenuService.loadByCode(key);
        if(customizeMenu!=null){
        	return textReply(request, customizeMenu.getReplyContent());
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

	public IWxTextCodeService getTextCodeService() {
		return textCodeService;
	}

	public void setTextCodeService(IWxTextCodeService textCodeService) {
		this.textCodeService = textCodeService;
	}

	public IWxCustomizeMenuService getCustomizeMenuService() {
		return customizeMenuService;
	}

	public void setCustomizeMenuService(IWxCustomizeMenuService customizeMenuService) {
		this.customizeMenuService = customizeMenuService;
	}

}
