package com.bruce.geekway.handler.processor;


import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bruce.geekway.model.WxArticle;
import com.bruce.geekway.model.WxCustomizeMenu;
import com.bruce.geekway.model.WxEventCode;
import com.bruce.geekway.model.wx.request.*;
import com.bruce.geekway.model.wx.response.*;
import com.bruce.geekway.service.IWxArticleService;
import com.bruce.geekway.service.IWxCustomizeMenuService;
import com.bruce.geekway.service.IWxEventCodeService;
import com.bruce.geekway.service.IWxEventCodeService;

/**
 * 根据Key值，返回DB中对应的数据
 * 目前支持1文本、2菜单点击、3订阅消息
 * @author liqian
 *
 */
//@Service
public class KeycodeCmsProcessor extends AbstractProcessor{
    
//	public KeycodeCmsProcessor(int sort) {
//		super(sort);
//	}

	@Autowired
    private IWxEventCodeService eventCodeService;
	@Autowired
    private IWxArticleService articleService;
//	@Autowired
//    private IWxCustomizeMenuService customizeMenuService;
    
	
	@Override
	protected BaseResponse processTextRequest(TextRequest request) {
		String code = ((TextRequest)request).getContent();
        
        WxEventCode eventCode = eventCodeService.loadByTypeCode((short) 1, code);
        if(eventCode!=null){
        	if(eventCode.getDisplayType()==1){//文本回复
        		return textReply(request, eventCode.getReplyContent());
        	}else if(eventCode.getDisplayType()==2){//图文回复
        		List<WxArticle> articleList = articleService.queryArticlesByModuleId(eventCode.getModuleId());
        		return newsReply(request, articleList);
        	}
        }
		return null;
	}
	
	@Override
	protected BaseResponse processClickEventRequest(EventRequest request) {
		String key = ((EventRequest)request).getEventKey();
		WxEventCode eventCode = eventCodeService.loadByTypeCode((short) 2, key);
        if(eventCode!=null){
        	if(eventCode.getDisplayType()==1){//文本回复
        		return textReply(request, eventCode.getReplyContent());
        	}else if(eventCode.getDisplayType()==2){//图文回复
        		List<WxArticle> articleList = articleService.queryArticlesByModuleId(eventCode.getModuleId());
        		return newsReply(request, articleList);
        	}
        }
		return null;
	}
	
	@Override
	protected BaseResponse processSubscribeEventRequest(EventRequest request) {
		String key = "";
		WxEventCode eventCode = eventCodeService.loadByTypeCode((short) 3, key);
        if(eventCode!=null){
        	if(eventCode.getDisplayType()==1){//文本回复
        		return textReply(request, eventCode.getReplyContent());
        	}else if(eventCode.getDisplayType()==2){//图文回复
        		List<WxArticle> articleList = articleService.queryArticlesByModuleId(eventCode.getModuleId());
        		return newsReply(request, articleList);
        	}
        }
		return null;
	}

	public IWxEventCodeService getEventCodeService() {
		return eventCodeService;
	}

	public void setEventCodeService(IWxEventCodeService eventCodeService) {
		this.eventCodeService = eventCodeService;
	}

	public IWxArticleService getArticleService() {
		return articleService;
	}

	public void setArticleService(IWxArticleService articleService) {
		this.articleService = articleService;
	}

}
