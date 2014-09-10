package com.bruce.geekway.admin.controller.geekway;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.bruce.geekway.model.WxMaterialArticle;
import com.bruce.geekway.model.WxMaterialMultimedia;
import com.bruce.geekway.model.WxMaterialNews;
import com.bruce.geekway.service.IWxMaterialArticleService;
import com.bruce.geekway.service.IWxMaterialMultimediaService;
import com.bruce.geekway.service.IWxMaterialNewsService;

/**
 * 选择资源的controller
 * @author liqian
 *
 */
@Controller
@RequestMapping("/geekway") 
public class GeekwayPickMaterialController {

	@Autowired
	private IWxMaterialArticleService wxMaterialArticleService;
	@Autowired
	private IWxMaterialNewsService wxMaterialNewsService;
	@Autowired
	private IWxMaterialMultimediaService wxMaterialMultimediaService;
	
	/**
	 * 选择输入文字素材
	 * @param model
	 * @param request
	 * @return
	 */
	@RequestMapping("/iframePickTextMaterial")
	public String pickTextMaterial(Model model, String openId, int operation, HttpServletRequest request){
		model.addAttribute("operation", operation);
		if(!StringUtils.isBlank(openId)){
			model.addAttribute("openId", openId);
		}
		return "iframe/pickTextMaterial";
	}
	
	/**
	 * 选择单图文素材
	 * @param model
	 * @param request
	 * @return
	 */
	@RequestMapping("/iframePickArticleMaterial")
	public String pickArticleMaterial(Model model, String openId, int operation, HttpServletRequest request){
		
		List<WxMaterialArticle> materialArticleList = wxMaterialArticleService.queryAll("id desc");
		
		model.addAttribute("materialArticleList", materialArticleList);
		model.addAttribute("operation", operation);
		if(!StringUtils.isBlank(openId)){
			model.addAttribute("openId", openId);
		}
		return "iframe/pickArticleMaterial";
	}
	
	/**
	 * 选择多图文素材
	 * @param model
	 * @param request
	 * @return
	 */
	@RequestMapping("/iframePickNewsMaterial")
	public String pickNewsMaterial(Model model, String openId, int operation, HttpServletRequest request){
		
		List<WxMaterialNews> materialNewsList = wxMaterialNewsService.queryAll("id desc");
		
		model.addAttribute("materialNewsList", materialNewsList);
		model.addAttribute("operation", operation);
		if(!StringUtils.isBlank(openId)){
			model.addAttribute("openId", openId);
		}
		return "iframe/pickNewsMaterial";
	}
	
	/**
	 * 选择图片素材
	 * @param model
	 * @param request
	 * @return
	 */
	@RequestMapping("/iframePickImageMaterial")
	public String pickImageMaterial(Model model, String openId, int operation, HttpServletRequest request){
		
		List<WxMaterialMultimedia> materialImageList = wxMaterialMultimediaService.queryImageMaterials();
		
		model.addAttribute("materialImageList", materialImageList);
		model.addAttribute("operation", operation);
		if(!StringUtils.isBlank(openId)){
			model.addAttribute("openId", openId);
		}
		return "iframe/pickImageMaterial";
	}
	
	/**
	 * 选择语音素材
	 * @param model
	 * @param request
	 * @return
	 */
	@RequestMapping("/iframePickVoiceMaterial")
	public String pickVoiceMaterial(Model model, String openId, int operation, HttpServletRequest request){
		
		List<WxMaterialMultimedia> materialVoiceList = wxMaterialMultimediaService.queryVoiceMaterials();
		
		model.addAttribute("materialVoiceList", materialVoiceList);
		model.addAttribute("operation", operation);
		if(!StringUtils.isBlank(openId)){
			model.addAttribute("openId", openId);
		}
		return "iframe/pickVoiceMaterial";
	}
	
}
