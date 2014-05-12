package com.bruce.geekway.handler.processor;


import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bruce.geekway.model.WxArticle;
import com.bruce.geekway.model.WxCommand;
import com.bruce.geekway.model.WxCustomizeMenu;
import com.bruce.geekway.model.WxEventCode;
import com.bruce.geekway.model.WxMaterial;
import com.bruce.geekway.model.WxMaterialArticle;
import com.bruce.geekway.model.WxMaterialNews;
import com.bruce.geekway.model.wx.request.*;
import com.bruce.geekway.model.wx.response.*;
import com.bruce.geekway.service.IWxArticleService;
import com.bruce.geekway.service.IWxCommandService;
import com.bruce.geekway.service.IWxCustomizeMenuService;
import com.bruce.geekway.service.IWxEventCodeService;
import com.bruce.geekway.service.IWxEventCodeService;
import com.bruce.geekway.service.IWxMaterialArticleService;
import com.bruce.geekway.service.IWxMaterialNewsService;
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

//	@Autowired
//    private IWxEventCodeService eventCodeService;
//	@Autowired
//    private IWxArticleService articleService;
//	@Autowired
//	private IWxMaterialService materialService;
	@Autowired
    private IWxMaterialArticleService materialArticleService;
	@Autowired
    private IWxMaterialNewsService materialNewsService;
//	@Autowired
//    private IWxCustomizeMenuService customizeMenuService;
	
	@Autowired
    private IWxCommandService commandService;
    
	
	/**
	 * 处理文本指令
	 */
	@Override
	protected BaseResponse processTextRequest(TextRequest request) {
		String key = ((TextRequest)request).getContent();
        
        WxCommand command = commandService.loadByCommandType((short) 1, key);
        return commandResponse(request, command);
	}

	
	/**
	 * 处理菜单指令
	 */
	@Override
	protected BaseResponse processClickEventRequest(EventRequest request) {
		String key = ((EventRequest)request).getEventKey();
		WxCommand command = commandService.loadByCommandType((short) 2, key);
		return commandResponse(request, command);
	}
	
	/**
	 * 处理新关注指令
	 */
	@Override
	protected BaseResponse processSubscribeEventRequest(EventRequest request) {
		String key = "subscribe";
		WxCommand command = commandService.loadByCommandType((short) 3, key);
		return commandResponse(request, command);
	}
	
	
	/**
	 * 处理消息
	 * @param request
	 * @param command
	 * @return
	 */
	private BaseResponse commandResponse(BaseRequest request, WxCommand command) {
		if(command!=null){
        	if(command.getMaterialType()==1){//文本回复
        		return textReply(request, command.getReplyContent());
        	}else if(command.getMaterialType()==2){//单图文回复
        		if(command.getMaterialId()!=null&&command.getMaterialId()>0){
        			//取单图文的数据
	        		WxMaterialArticle materialArticle = materialArticleService.loadById(command.getMaterialId());
	        		if(materialArticle!=null){
	        			List<WxMaterialArticle> articleList = new ArrayList<WxMaterialArticle>();
	        			articleList.add(materialArticle);
	        			return newsReply(request, articleList);
	        		}
        		}
        	}else if(command.getMaterialType()==3){//多图文回复
        		if(command.getMaterialId()!=null&&command.getMaterialId()>0){
        			//取多图文的组合数据
        			WxMaterialNews materialNews = materialNewsService.loadById(command.getMaterialId());
	        		if(materialNews!=null){
	        			List<WxMaterialArticle> materialArticleList = materialArticleService.queryMaterialArticlesByNewsId(command.getMaterialId(), materialNews.getRowLimit());
		        		return newsReply(request, materialArticleList);
		        	}
        		}
        	}else if(command.getMaterialType()==4){//单图片
        		//do nothing
        	}else if(command.getMaterialType()==5){//语音
        		//do nothing
        	}
        }
		return null;
	}
	

	
	public IWxCommandService getCommandService() {
		return commandService;
	}

	public void setCommandService(IWxCommandService commandService) {
		this.commandService = commandService;
	}

	
	


	public IWxMaterialArticleService getMaterialArticleService() {
		return materialArticleService;
	}


	public void setMaterialArticleService(
			IWxMaterialArticleService materialArticleService) {
		this.materialArticleService = materialArticleService;
	}


	public IWxMaterialNewsService getMaterialNewsService() {
		return materialNewsService;
	}


	public void setMaterialNewsService(IWxMaterialNewsService materialNewsService) {
		this.materialNewsService = materialNewsService;
	}

	
//	public IWxEventCodeService getEventCodeService() {
//		return eventCodeService;
//	}
//
//	public void setEventCodeService(IWxEventCodeService eventCodeService) {
//		this.eventCodeService = eventCodeService;
//	}
//
//	public IWxArticleService getArticleService() {
//		return articleService;
//	}
//
//	public void setArticleService(IWxArticleService articleService) {
//		this.articleService = articleService;
//	}
//
//	
//	public IWxMaterialService getMaterialService() {
//		return materialService;
//	}
//
//	public void setMaterialService(IWxMaterialService materialService) {
//		this.materialService = materialService;
//	}
}
