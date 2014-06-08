package com.bruce.geekway.handler.processor;


import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import com.bruce.geekway.model.WxCommand;
import com.bruce.geekway.model.WxMaterialArticle;
import com.bruce.geekway.model.wx.request.BaseRequest;
import com.bruce.geekway.model.wx.request.EventRequest;
import com.bruce.geekway.model.wx.request.TextRequest;
import com.bruce.geekway.model.wx.response.BaseResponse;
import com.bruce.geekway.service.IWxCommandService;
import com.bruce.geekway.service.IWxMaterialArticleService;
//import com.bruce.geekway.model.WxMaterialNews;

/**
 * 根据Key值，返回DB中对应的数据
 * 目前支持1文本、2菜单点击、3订阅消息
 * @author liqian
 *
 */
//@Service
public class CommandCmsProcessor extends AbstractProcessor{
    
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
//	@Autowired
//    private IWxMaterialNewsService materialNewsService;
//	@Autowired
//    private IWxCustomizeMenuService customizeMenuService;
	
	@Autowired
    private IWxCommandService commandService;
    
	
	/**
	 * 处理文本指令
	 */
	@Override
	protected BaseResponse processTextRequest(TextRequest request) {
		String key = ((TextRequest) request).getContent();

		WxCommand command = commandService.loadByCommandType((short) 0, key);
		if (command != null) {
			List<WxMaterialArticle> materialArticleList = materialArticleService.queryMaterialArticlesByCommandId(command.getId());
			return processResponse(request, materialArticleList);
		}
		return null;
	}

	
	/**
	 * 处理菜单指令
	 */
	@Override
	protected BaseResponse processClickEventRequest(EventRequest request) {
		String key = ((EventRequest) request).getEventKey();
		WxCommand command = commandService.loadByCommandType((short) 1, key);
		if (command != null) {
			List<WxMaterialArticle> materialArticleList = materialArticleService.queryMaterialArticlesByCommandId(command.getId());
			return processResponse(request, materialArticleList);
		}
		return null;
	}
	
	/**
	 * 处理新关注指令
	 */
	@Override
	protected BaseResponse processFirstSubscribeEventRequest(EventRequest request) {
		List<WxMaterialArticle> materialArticleList = materialArticleService.querySubscribedMaterials((short) 1);
		return processResponse(request, materialArticleList);
	}
	
	/**
	 * 处理重复关注指令
	 */
	@Override
	protected BaseResponse processRepeatSubscribeEventRequest(EventRequest request) {
		List<WxMaterialArticle> materialArticleList = materialArticleService.querySubscribedMaterials((short) 2);
		return processResponse(request, materialArticleList);
	}
	
	/**
	 * 素材列表的处理（1、文本、图文素材同时存在的情况下，优先使用图文）
	 * @param materialArticleList
	 * @return
	 */
	private BaseResponse processResponse(BaseRequest request, List<WxMaterialArticle> materialArticleList) {
		if(materialArticleList!=null&&materialArticleList.size()>0){
			boolean onlyTextMaterial = true;
			//检查是纯文本素材
			for(WxMaterialArticle material: materialArticleList){
				if(material.getMaterialType()!=null&&material.getMaterialType()==1){
					onlyTextMaterial = false;
					break;
				}
			}
			if(onlyTextMaterial){//纯文本素材
				return textReply(request, materialArticleList.get(0).getTextReply());
			}else{//图文&文本素材共存
				return newsReply(request, materialArticleList);
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

}
