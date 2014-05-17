package com.bruce.geekway.admin.controller.mp;


import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.bruce.geekway.model.wx.json.response.WxJsonResult;
import com.bruce.geekway.model.wx.message.NewsMessage;
import com.bruce.geekway.model.wx.message.TextMessage;
import com.bruce.geekway.service.mp.WxReplyService;

/**
 * 客服消息管理，目前仅支持文本、单图文、多图文三种方式
 * @author liqian
 *
 */
@Controller
@RequestMapping("/geekway")
public class GeekwayMpReplyController {
	
	@Autowired
	private WxReplyService wxReplyService;

	@RequestMapping("/mpReplyText") 
	public String mpReplyText(Model model, HttpServletRequest request, String toOpenId, String text) {
		
		if(!StringUtils.isBlank(toOpenId)&&!StringUtils.isBlank(text)){
			TextMessage textMessage = new TextMessage();
			textMessage.touser = toOpenId;
			textMessage.addContent(text);
			//发送客服消息
			WxJsonResult sendResult = wxReplyService.replyTextMessage(textMessage);
			if(sendResult!=null){
				
			}
		}
		model.addAttribute("redirectUrl", "./mpUserListRemote");
		return "forward:/home/operationRedirect";
	}
	
	@RequestMapping("/mpReplyArticle") 
	public String mpReplyArticle(Model model, HttpServletRequest request, String toOpenId, int articleId) {
		return mpReplyMaterial(model, request, toOpenId, (short) 2,  articleId);
	}
	
	@RequestMapping("/mpReplyNews") 
	public String mpReplyNews(Model model, HttpServletRequest request, String toOpenId, int newsId) {
		return mpReplyMaterial(model, request, toOpenId, (short) 3, newsId);
	}
	
	/**
	 * 使用素材库中的素材进行回复
	 * @param model
	 * @param request
	 * @param toOpenId
	 * @param materialType
	 * @param materialId
	 * @return
	 */
	private String mpReplyMaterial(Model model, HttpServletRequest request, String toOpenId, short materialType, int materialId) {
		if(!StringUtils.isBlank(toOpenId)){
			NewsMessage newsMessage = new NewsMessage();
			newsMessage.touser = toOpenId;
			
			//发送客服消息
			WxJsonResult sendResult = wxReplyService.replyNewsMessage(newsMessage);
			if(sendResult!=null){
			}
		}
		model.addAttribute("redirectUrl", "./mpUserListRemote");
		return "forward:/home/operationRedirect";
	}
	
}
