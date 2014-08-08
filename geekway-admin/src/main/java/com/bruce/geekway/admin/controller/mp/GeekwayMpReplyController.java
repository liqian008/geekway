package com.bruce.geekway.admin.controller.mp;


import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.bruce.geekway.model.WxMaterialArticle;
import com.bruce.geekway.model.WxMaterialMultimedia;
import com.bruce.geekway.model.wx.json.response.WxJsonResult;
import com.bruce.geekway.model.wx.message.CustomMessage;
import com.bruce.geekway.model.wx.message.ImageMessage;
import com.bruce.geekway.model.wx.message.NewsMessage;
import com.bruce.geekway.model.wx.message.NewsMessage.News;
import com.bruce.geekway.model.wx.message.TextMessage;
import com.bruce.geekway.model.wx.message.VoiceMessage;
import com.bruce.geekway.service.IWxMaterialArticleService;
import com.bruce.geekway.service.IWxMaterialMultimediaService;
import com.bruce.geekway.service.mp.WxCustomReplyService;

/**
 * 客服消息管理，目前仅支持文本、单图文、多图文三种方式
 * @author liqian
 *
 */
@Controller
@RequestMapping("/geekway")
public class GeekwayMpReplyController {
	
	@Autowired
	private WxCustomReplyService wxCustomReplyService;
	@Autowired
	private IWxMaterialMultimediaService wxMaterialMultimediaService;
	@Autowired
	private IWxMaterialArticleService wxMaterialArticleService;
	
	@RequestMapping("/mpReplyText") 
	public String mpReplyText(Model model, HttpServletRequest request, String openId, String text) {
		
		if(!StringUtils.isBlank(openId)&&!StringUtils.isBlank(text)){
			TextMessage textMessage = new TextMessage();
			textMessage.touser = openId;
			textMessage.addContent(text);
			//发送客服消息
			WxJsonResult sendResult = wxCustomReplyService.replyTextMessage(textMessage);
			if(sendResult!=null){
				
			}
		}
		model.addAttribute("redirectUrl", "./historyMessageDialog?openId="+openId);
		return "forward:/home/operationRedirect";
	}
	
	@RequestMapping("/mpReplyArticle") 
	public String mpReplyArticle(Model model, HttpServletRequest request, String toOpenId, int materialArticleId) {
		return mpReplyMaterial(model, request, toOpenId, (short) 1,  materialArticleId);
	}
	
	@RequestMapping("/mpReplyNews") 
	public String mpReplyNews(Model model, HttpServletRequest request, String toOpenId, int materialNewsId) {
		return mpReplyMaterial(model, request, toOpenId, (short) 2,  materialNewsId);
	}
	
	@RequestMapping("/mpReplyImage") 
	public String mpReplyImage(Model model, HttpServletRequest request, String toOpenId, int materialImageId) {
		return mpReplyMaterial(model, request, toOpenId, (short) 3, materialImageId);
	}
	
	@RequestMapping("/mpReplyVoice") 
	public String mpReplyVoice(Model model, HttpServletRequest request, String toOpenId, int materialVoiceId) {
		return mpReplyMaterial(model, request, toOpenId, (short) 4, materialVoiceId);
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
		String redirectUrl = "";
		if(!StringUtils.isBlank(toOpenId)){
			CustomMessage replyMessage = null;
			if(materialType==1){//单图文
				//构造单图文消息对象
				WxMaterialArticle materialArticle = wxMaterialArticleService.loadById(materialId);
				if(materialArticle!=null){//TODO 数据有效
					replyMessage = new NewsMessage();
					((NewsMessage)replyMessage).setMsgtype("news");
					News news = new News();
					news.addArticle(materialArticle.getTitle(), materialArticle.getShortContent(), "", materialArticle.getCoverThumbImageUrl());
					((NewsMessage)replyMessage).setNews(news);
				}
			}else if(materialType==2){//多图文
				//构造单图文消息对象
				List<WxMaterialArticle> articleList = wxMaterialArticleService.queryMaterialArticlesByNewsId(materialId, 10);
				if(articleList!=null && articleList.size()>0){//TODO 数据有效
					replyMessage = new NewsMessage();
					((NewsMessage)replyMessage).setMsgtype("news");
					News news = new News();
					for(WxMaterialArticle article: articleList){
						news.addArticle(article.getTitle(), article.getShortContent(), "", article.getCoverThumbImageUrl());
					}
					((NewsMessage)replyMessage).setNews(news);
				}
			}else if(materialType==3){//图片素材
				WxMaterialMultimedia materialImage = wxMaterialMultimediaService.loadById(materialId);
				if(materialImage!=null){//TODO 数据有效
					//构造图片消息对象
					replyMessage = new ImageMessage();
					((ImageMessage)replyMessage).setMsgtype("image");
					((ImageMessage)replyMessage).addImage(materialImage.getMediaId());
				}
				
			}else if(materialType==4){//语音素材
				WxMaterialMultimedia materialImage = wxMaterialMultimediaService.loadById(materialId);
				if(materialImage!=null){//TODO 数据有效
					//构造语音消息对象
					replyMessage = new VoiceMessage();
					((VoiceMessage)replyMessage).setMsgtype("voice");
					((VoiceMessage)replyMessage).addVoice(materialImage.getMediaId());
				}
			}
			
			if(replyMessage!=null){
				replyMessage.touser = toOpenId;
				//发送客服消息
				WxJsonResult sendResult = wxCustomReplyService.replyMessage(replyMessage);
				if(sendResult!=null){
					redirectUrl = "";
				}
				
			}
		}
		model.addAttribute("redirectUrl", redirectUrl);
		return "forward:/home/operationRedirect";
	}
	
}
