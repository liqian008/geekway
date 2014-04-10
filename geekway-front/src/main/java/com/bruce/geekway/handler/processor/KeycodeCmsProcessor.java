package com.bruce.geekway.handler.processor;


import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bruce.geekway.model.WxArticle;
import com.bruce.geekway.model.WxCustomizeMenu;
import com.bruce.geekway.model.WxTextCode;
import com.bruce.geekway.model.wx.request.*;
import com.bruce.geekway.model.wx.response.*;
import com.bruce.geekway.service.IWxArticleService;
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
    private IWxArticleService articleService;
	@Autowired
    private IWxCustomizeMenuService customizeMenuService;
    
	
	@Override
	protected BaseResponse processTextRequest(TextRequest request) {
		String code = ((TextRequest)request).getContent();
        
        WxTextCode textCode = textCodeService.loadByCode(code);
        if(textCode!=null){
        	if(textCode.getDisplayType()==1){//文本回复
        		return textReply(request, textCode.getReplyContent());
        	}else if(textCode.getDisplayType()==2){//图文回复
        		List<WxArticle> articleList = articleService.queryArticlesByModuleId(textCode.getModuleId());
        		return newsReply(request, articleList);
        	}
        }
		return null;
	}
	
	@Override
	protected BaseResponse processClickEventRequest(ClickEventRequest request) {
		String key = ((EventRequest)request).getEventKey();
		
		WxCustomizeMenu customizeMenu = customizeMenuService.loadByCode(key);
        if(customizeMenu!=null){
        	if(customizeMenu.getDisplayType()==1){//文本回复
        		return textReply(request, customizeMenu.getReplyContent());
        	}else if(customizeMenu.getDisplayType()==2){//图文回复
        		List<WxArticle> articleList = articleService.queryArticlesByModuleId(customizeMenu.getModuleId());
        		return newsReply(request, articleList);
        	}
        }
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

	public IWxArticleService getArticleService() {
		return articleService;
	}

	public void setArticleService(IWxArticleService articleService) {
		this.articleService = articleService;
	}

}
