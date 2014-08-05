package com.bruce.geekway.controller.web;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import com.bruce.geekway.model.WxMaterialArticle;
import com.bruce.geekway.service.IWxMaterialArticleService;

/**
 * Handles requests for the application home page.
 */
@Controller
@RequestMapping(value={"m"})
public class MaterialController {
	
	@Autowired
	private IWxMaterialArticleService wxMaterialArticleService;
	
	@RequestMapping(value = "/index")
	public String index(Model model) {
//		System.out.println(wxArticleService);
		return "mobile/index";
	}
	
//	@RequestMapping(value = "/news/{newsId}")
//	public String news(Model model, @PathVariable int newsId) {
//		List<WxMaterialArticle> articleList = wxMaterialArticleService.queryMaterialArticlesByNewsId(newsId, 4);
//		if(articleList!=null){
//			model.addAttribute("articleList", articleList);
//		}
//		return "mobile/articleList";
//	}
	
	@RequestMapping(value = "/article/{articleId}")
	public String article(Model model, @PathVariable int articleId) {
		WxMaterialArticle article = wxMaterialArticleService.loadById(articleId);
		if(article!=null){
			model.addAttribute("article", article);
		}
		return "mobile/article";
	}
}
