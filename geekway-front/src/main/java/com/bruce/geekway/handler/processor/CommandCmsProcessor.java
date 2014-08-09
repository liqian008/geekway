package com.bruce.geekway.handler.processor;


import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;

import com.bruce.geekway.model.WxCommand;
import com.bruce.geekway.model.WxMaterialArticle;
import com.bruce.geekway.model.WxMaterialMultimedia;
import com.bruce.geekway.model.WxMaterialNews;
import com.bruce.geekway.model.wx.request.BaseRequest;
import com.bruce.geekway.model.wx.request.EventRequest;
import com.bruce.geekway.model.wx.request.TextRequest;
import com.bruce.geekway.model.wx.response.BaseResponse;
import com.bruce.geekway.service.IWxCommandService;
import com.bruce.geekway.service.IWxMaterialArticleService;
import com.bruce.geekway.service.IWxMaterialMultimediaService;
import com.bruce.geekway.service.IWxMaterialNewsService;
//import com.bruce.geekway.model.WxMaterialNews;

/**
 * 根据Key值，返回DB中对应的数据
 * 目前支持1文本、2菜单点击、3订阅消息
 * @author liqian
 *
 */
//@Service
public class CommandCmsProcessor extends AbstractProcessor implements InitializingBean{
    
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
	@Autowired
    private IWxMaterialMultimediaService materialMultimediaService;
	@Autowired
    private IWxCommandService commandService;
    
	/**
	 * 处理文本指令
	 */
	@Override
	protected BaseResponse processTextRequest(TextRequest request) {
		String key = ((TextRequest)request).getContent();
        
        WxCommand command = commandService.loadByCommand(key);
        return commandResponse(request, command);
	}

	
	/**
	 * 处理菜单指令
	 */
	@Override
	protected BaseResponse processClickEventRequest(EventRequest request) {
		String key = ((EventRequest)request).getEventKey();
		WxCommand command = commandService.loadByCommand(key);
		return commandResponse(request, command);
	}
	
	/**
	 * 处理新关注指令
	 */
	@Override
	protected BaseResponse processNewSubscribeEventRequest(EventRequest request) { 
//		String key = "subscribe";
		WxCommand command = commandService.loadNewSubscribedCommand();
		return commandResponse(request, command);
	}
	/**
	 * 处理重复关注指令
	 */
	@Override
	protected BaseResponse processReSubscribeEventRequest(EventRequest request) {
		WxCommand command = commandService.loadReSubscribedCommand();
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
        	if(command.getMaterialType()==0){//文本回复
        		return textReply(request, command.getReplyContent());
        	}else if(command.getMaterialType()==1){//单图文回复
        		if(command.getMaterialId()!=null&&command.getMaterialId()>0){
        			//取单图文的数据
	        		WxMaterialArticle materialArticle = materialArticleService.loadById(command.getMaterialId());
	        		if(materialArticle!=null){
	        			List<WxMaterialArticle> articleList = new ArrayList<WxMaterialArticle>();
	        			articleList.add(materialArticle);
	        			return newsReply(request, articleList);
	        		}
        		}
        	}else if(command.getMaterialType()==2){//多图文回复
        		if(command.getMaterialId()!=null&&command.getMaterialId()>0){
        			//取多图文的组合数据
        			WxMaterialNews materialNews = materialNewsService.loadById(command.getMaterialId());
	        		if(materialNews!=null){
	        			List<WxMaterialArticle> materialArticleList = materialArticleService.queryMaterialArticlesByNewsId(command.getMaterialId());
		        		return newsReply(request, materialArticleList);
		        	}
        		}
        	}else if(command.getMaterialType()==3){//回复图片
        		if(command.getMaterialId()!=null&&command.getMaterialId()>0){
        			//取多图文的组合数据
        			//取单图文的数据
	        		WxMaterialMultimedia materialImage = materialMultimediaService.loadById(command.getMaterialId());
	        		if(materialImage!=null){
	        			return imageReply(request, materialImage);
	        		}
        		}
        	}else if(command.getMaterialType()==4){//回复语音
        		//暂不支持，do nothing
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

	public void setMaterialArticleService(IWxMaterialArticleService materialArticleService) {
		this.materialArticleService = materialArticleService;
	}

	public IWxMaterialNewsService getMaterialNewsService() {
		return materialNewsService;
	}


	public void setMaterialNewsService(IWxMaterialNewsService materialNewsService) {
		this.materialNewsService = materialNewsService;
	}
	
	public IWxMaterialMultimediaService getMaterialMultimediaService() {
		return materialMultimediaService;
	}

	public void setMaterialMultimediaService(IWxMaterialMultimediaService materialMultimediaService) {
		this.materialMultimediaService = materialMultimediaService;
	}


	public void afterPropertiesSet() throws Exception {
		System.out.println("========commandService========"+commandService);
		System.out.println("========materialArticleService========"+materialArticleService);
		System.out.println("========materialNewsService========"+materialNewsService);
		
	
	
	}
}
