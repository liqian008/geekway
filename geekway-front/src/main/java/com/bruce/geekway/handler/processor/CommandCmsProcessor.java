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
    private IWxMaterialArticleService wxMaterialArticleService;
	@Autowired
    private IWxMaterialNewsService wxMaterialNewsService;
	@Autowired
    private IWxMaterialMultimediaService wxMaterialMultimediaService;
	@Autowired
    private IWxCommandService wxCommandService;
    
	/**
	 * 处理文本指令
	 */
	@Override
	protected BaseResponse processTextRequest(TextRequest request) {
		String key = ((TextRequest)request).getContent();
        
        WxCommand command = wxCommandService.loadByCommand((short) 0, key);
        return commandResponse(request, command);
	}

	
	/**
	 * 处理菜单指令
	 */
	@Override
	protected BaseResponse processClickEventRequest(EventRequest request) {
		String key = ((EventRequest)request).getEventKey();
		WxCommand command = wxCommandService.loadByCommand((short) 1,key);
		return commandResponse(request, command);
	}
	
	/**
	 * 处理新关注指令
	 */
	@Override
	protected BaseResponse processNewSubscribeEventRequest(EventRequest request) { 
//		String key = "subscribe";
		WxCommand command = wxCommandService.loadNewSubscribedCommand();
		return commandResponse(request, command);
	}
	/**
	 * 处理重复关注指令
	 */
	@Override
	protected BaseResponse processReSubscribeEventRequest(EventRequest request) {
		WxCommand command = wxCommandService.loadReSubscribedCommand();
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
	        		WxMaterialArticle materialArticle = wxMaterialArticleService.loadById(command.getMaterialId());
	        		if(materialArticle!=null){
	        			List<WxMaterialArticle> articleList = new ArrayList<WxMaterialArticle>();
	        			articleList.add(materialArticle);
	        			return newsReply(request, articleList);
	        		}
        		}
        	}else if(command.getMaterialType()==2){//多图文回复
        		if(command.getMaterialId()!=null&&command.getMaterialId()>0){
        			//取多图文的组合数据
        			WxMaterialNews materialNews = wxMaterialNewsService.loadById(command.getMaterialId());
	        		if(materialNews!=null){
	        			List<WxMaterialArticle> materialArticleList = wxMaterialArticleService.queryMaterialArticlesByNewsId(command.getMaterialId());
		        		return newsReply(request, materialArticleList);
		        	}
        		}
        	}else if(command.getMaterialType()==3){//回复图片
        		if(command.getMaterialId()!=null&&command.getMaterialId()>0){
        			//取多图文的组合数据
        			//取单图文的数据
	        		WxMaterialMultimedia materialImage = wxMaterialMultimediaService.loadById(command.getMaterialId());
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
	
	
	public IWxMaterialArticleService getWxMaterialArticleService() {
		return wxMaterialArticleService;
	}


	public void setWxMaterialArticleService(IWxMaterialArticleService wxMaterialArticleService) {
		this.wxMaterialArticleService = wxMaterialArticleService;
	}


	public IWxMaterialNewsService getWxMaterialNewsService() {
		return wxMaterialNewsService;
	}


	public void setWxMaterialNewsService(IWxMaterialNewsService wxMaterialNewsService) {
		this.wxMaterialNewsService = wxMaterialNewsService;
	}


	public IWxMaterialMultimediaService getWxMaterialMultimediaService() {
		return wxMaterialMultimediaService;
	}


	public void setWxMaterialMultimediaService(IWxMaterialMultimediaService wxMaterialMultimediaService) {
		this.wxMaterialMultimediaService = wxMaterialMultimediaService;
	}


	public IWxCommandService getWxCommandService() {
		return wxCommandService;
	}


	public void setWxCommandService(IWxCommandService wxCommandService) {
		this.wxCommandService = wxCommandService;
	}


	public void afterPropertiesSet() throws Exception {
		System.out.println("========commandService========"+wxCommandService);
		System.out.println("========materialArticleService========"+wxMaterialArticleService);
		System.out.println("========materialNewsService========"+wxMaterialNewsService);
		
	
	
	}
}
