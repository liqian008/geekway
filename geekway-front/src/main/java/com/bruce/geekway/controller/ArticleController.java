package com.bruce.geekway.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import com.bruce.geekway.model.WxArticle;
import com.bruce.geekway.service.IWxArticleService;

/**
 * Handles requests for the application home page.
 */
@Controller
@RequestMapping(value={"m"})
public class ArticleController {
	
	@Autowired
	private IWxArticleService wxArticleService;
	
	@RequestMapping(value = "/index")
	public String index(Model model) {
		System.out.println(wxArticleService);
		return "mobile/index";
	}
	
	@RequestMapping(value = "/articles/{moduleId}")
	public String articleList(Model model, @PathVariable int moduleId) {
		List<WxArticle> articleList = wxArticleService.queryAll();
		if(articleList!=null){
			model.addAttribute("articleList", articleList);
		}
		return "mobile/articleList";
	}
	
	@RequestMapping(value = "/article/{articleId}")
	public String article(Model model, @PathVariable int articleId) {
		WxArticle article = wxArticleService.loadById(articleId);
		if(article!=null){
			model.addAttribute("article", article);
		}
		return "mobile/article";
	}
}
