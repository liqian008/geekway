package com.bruce.geekway.handler.processor;


import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bruce.geekway.model.WxArticle;
import com.bruce.geekway.model.WxCommand;
import com.bruce.geekway.model.WxCustomizeMenu;
import com.bruce.geekway.model.WxEventCode;
import com.bruce.geekway.model.WxMaterial;
import com.bruce.geekway.model.wx.request.*;
import com.bruce.geekway.model.wx.response.*;
import com.bruce.geekway.service.IWxArticleService;
import com.bruce.geekway.service.IWxCommandService;
import com.bruce.geekway.service.IWxCustomizeMenuService;
import com.bruce.geekway.service.IWxEventCodeService;
import com.bruce.geekway.service.IWxEventCodeService;
import com.bruce.geekway.service.IWxMaterialService;

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
	
	
	@Autowired
    private IWxCommandService commandService;
	@Autowired
    private IWxMaterialService materialService;
    
	
	@Override
	protected BaseResponse processTextRequest(TextRequest request) {
		String key = ((TextRequest)request).getContent();
        
        WxCommand command = commandService.loadByCommandType((short) 1, key);
        if(command!=null){
        	if(command.getMaterialType()==1){//文本回复
        		return textReply(request, command.getReplyContent());
        	}else if(command.getMaterialType()==2){//图文回复
        		List<WxMaterial> materialList = materialService.queryMaterialsByCommandId(command.getId(), command.getRowLimit());
        		return newsReply(request, materialList);
        	}
        }
		return null;
	}
	
	@Override
	protected BaseResponse processClickEventRequest(EventRequest request) {
		String key = ((EventRequest)request).getEventKey();
		WxCommand command = commandService.loadByCommandType((short) 2, key);
        if(command!=null){
        	if(command.getMaterialType()==1){//文本回复
        		return textReply(request, command.getReplyContent());
        	}else if(command.getMaterialType()==2){//图文回复
        		List<WxMaterial> materialList = materialService.queryMaterialsByCommandId(command.getId(), command.getRowLimit());
        		return newsReply(request, materialList);
        	}
        }
		return null;
	}
	
	@Override
	protected BaseResponse processSubscribeEventRequest(EventRequest request) {
		String key = "subscribe";
		WxCommand command = commandService.loadByCommandType((short) 3, key);
        if(command!=null){
        	if(command.getMaterialType()==1){//文本回复
        		return textReply(request, command.getReplyContent());
        	}else if(command.getMaterialType()==2){//图文回复
        		List<WxMaterial> materialList = materialService.queryMaterialsByCommandId(command.getId(), command.getRowLimit());
        		return newsReply(request, materialList);
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

	public IWxCommandService getCommandService() {
		return commandService;
	}

	public void setCommandService(IWxCommandService commandService) {
		this.commandService = commandService;
	}

	public IWxMaterialService getMaterialService() {
		return materialService;
	}

	public void setMaterialService(IWxMaterialService materialService) {
		this.materialService = materialService;
	}

	
}
