package com.bruce.geekway.admin.controller.mp;


import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.bruce.geekway.model.WxMaterialArticle;
import com.bruce.geekway.model.WxMaterialMultimedia;
import com.bruce.geekway.model.data.JsonResultBean;
import com.bruce.geekway.model.wx.json.response.WxJsonResult;
import com.bruce.geekway.model.wx.message.CustomMessage;
import com.bruce.geekway.model.wx.message.ImageMessage;
import com.bruce.geekway.model.wx.message.NewsMessage;
import com.bruce.geekway.model.wx.message.NewsMessage.News;
import com.bruce.geekway.model.wx.message.TextMessage;
import com.bruce.geekway.model.wx.message.VoiceMessage;
import com.bruce.geekway.service.IWxHistoryMessageService;
import com.bruce.geekway.service.IWxMaterialArticleService;
import com.bruce.geekway.service.IWxMaterialMultimediaService;
import com.bruce.geekway.service.mp.WxCustomReplyService;
import com.bruce.geekway.utils.ArticleLinkUtil;
import com.bruce.geekway.utils.JsonResultBuilderUtil;

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
	@Autowired
	private IWxHistoryMessageService wxHistoryMessageService;
	
	@ResponseBody
	@RequestMapping("/mpReplyText.json")
	public JsonResultBean mpReplyText(Model model, HttpServletRequest request, String openId, String text) {
		if(!StringUtils.isBlank(openId)&&!StringUtils.isBlank(text)){
			TextMessage textMessage = new TextMessage();
			textMessage.touser = openId;
			textMessage.addContent(text);
			//发送客服消息
			WxJsonResult replyResult = wxCustomReplyService.replyTextMessage(textMessage);
			replyResult.setErrcode(0);
			if(replyResult!=null&&replyResult.getErrcode()!=null&&replyResult.getErrcode()==0){
				return JsonResultBuilderUtil.buildSuccessJson();
			}
		}
		return JsonResultBuilderUtil.buildErrorJson();
	}
	
	@ResponseBody
	@RequestMapping("/mpReplyArticle.json") 
	public JsonResultBean mpReplyArticle(Model model, HttpServletRequest request, String openId, int materialId) {
		return mpReplyMaterial(model, request, openId, (short) 1,  materialId);
	}
	
	@ResponseBody
	@RequestMapping("/mpReplyNews.json") 
	public JsonResultBean mpReplyNews(Model model, HttpServletRequest request, String openId, int materialId) {
		return mpReplyMaterial(model, request, openId, (short) 2,  materialId);
	}
	
	@ResponseBody
	@RequestMapping("/mpReplyImage.json") 
	public JsonResultBean mpReplyImage(Model model, HttpServletRequest request, String openId, int materialId) {
		return mpReplyMaterial(model, request, openId, (short) 3, materialId);
	}
	
	@ResponseBody
	@RequestMapping("/mpReplyVoice.json") 
	public JsonResultBean mpReplyVoice(Model model, HttpServletRequest request, String openId, int materialId) {
		return mpReplyMaterial(model, request, openId, (short) 4, materialId);
	}
	
	/**
	 * 使用素材库中的素材进行回复
	 * @param model
	 * @param request
	 * @param openId
	 * @param materialType
	 * @param materialId
	 * @return
	 */
	private JsonResultBean mpReplyMaterial(Model model, HttpServletRequest request, String openId, short materialType, int materialId) {
		if(!StringUtils.isBlank(openId)){
			CustomMessage replyMessage = null;
			if(materialType==1){//单图文
				//构造单图文消息对象
				WxMaterialArticle materialArticle = wxMaterialArticleService.loadById(materialId);
				if(materialArticle!=null){//TODO 检查数据有效性
					replyMessage = new NewsMessage();
					((NewsMessage)replyMessage).setMsgtype("news");
					News news = new News();
					news.addArticle(materialArticle.getTitle(), materialArticle.getShortContent(),  ArticleLinkUtil.getArticleLink(materialArticle.getId()), materialArticle.getCoverImageUrl());
					((NewsMessage)replyMessage).setNews(news);
				}
			}else if(materialType==2){//多图文
				//构造单图文消息对象
				List<WxMaterialArticle> articleList = wxMaterialArticleService.queryMaterialArticlesByNewsId(materialId, 10);
				if(articleList!=null && articleList.size()>0){//TODO 检查数据有效性
					replyMessage = new NewsMessage();
					((NewsMessage)replyMessage).setMsgtype("news");
					News news = new News();
					for(WxMaterialArticle article: articleList){
						String url = ArticleLinkUtil.getArticleLink(article.getId());
						news.addArticle(article.getTitle(), article.getShortContent(), url, article.getCoverImageUrl());
					}
					((NewsMessage)replyMessage).setNews(news);
				}
			}else if(materialType==3){//图片素材
				WxMaterialMultimedia materialImage = wxMaterialMultimediaService.loadById(materialId);
				if(materialImage!=null){//TODO 检查数据有效性
					//构造图片消息对象
					replyMessage = new ImageMessage();
					((ImageMessage)replyMessage).setMsgtype("image");
					((ImageMessage)replyMessage).addImage(materialImage.getMediaId());
				}
				
			}else if(materialType==4){//语音素材
				WxMaterialMultimedia materialImage = wxMaterialMultimediaService.loadById(materialId);
				if(materialImage!=null){//TODO 检查数据有效性
					//构造语音消息对象
					replyMessage = new VoiceMessage();
					((VoiceMessage)replyMessage).setMsgtype("voice");
					((VoiceMessage)replyMessage).addVoice(materialImage.getMediaId());
				}
			}
			
			if(replyMessage!=null){
				replyMessage.touser = openId;
				//发送客服消息
				WxJsonResult replyResult = wxCustomReplyService.replyMessage(replyMessage);
				replyResult.setErrcode(0);
				if(replyResult!=null&&replyResult.getErrcode()!=null&&replyResult.getErrcode()==0){
					return JsonResultBuilderUtil.buildSuccessJson();
				}
			}
		}
		return JsonResultBuilderUtil.buildErrorJson();
	}
	
}
