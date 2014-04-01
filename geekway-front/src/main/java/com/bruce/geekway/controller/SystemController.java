package com.bruce.geekway.controller;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.bruce.geekway.model.WxArticle;
import com.bruce.geekway.service.IWxArticleService;

/**
 * Handles requests for the application home page.
 */
@Controller
@RequestMapping(value="/mobile")
public class SystemController {
	
	@Autowired
	private IWxArticleService wxArticleService;
	
	@RequestMapping(value = "/index")
	public String index(Model model) {
		System.out.println(wxArticleService);
		return "mobile/index";
	}
	
	@RequestMapping(value = "/articles")
	public String articleList(Model model) {
		List<WxArticle> articleList = wxArticleService.queryAll();
		if(articleList!=null){
			model.addAttribute("articleList", articleList);
		}
		return "mobile/articleList";
	}
	
	@RequestMapping(value = "/article")
	public String article(Model model, int articleId) {
		WxArticle article = wxArticleService.loadById(articleId);
		if(article!=null){
			model.addAttribute("article", article);
		}
		return "mobile/article";
	}
}
