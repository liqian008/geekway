package com.bruce.geekway.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang3.math.NumberUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Required;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.bruce.geekway.model.WxArticle;
import com.bruce.geekway.service.IWxArticleService;


@Controller
@RequestMapping("/geekway")
public class GeekwayAdminController {

	@Autowired
	private IWxArticleService wxArticleService;
	
	@RequestMapping("/articles")
	public String articleList(Model model, HttpServletRequest request) {
		String servletPath = request.getRequestURI();
		model.addAttribute("servletPath", servletPath);
		
		List<WxArticle> articleList = wxArticleService.queryAll();
		model.addAttribute("articleList", articleList);
		return "article/articleList";
	}
	
	@RequestMapping("/articleInfo")
	public String articleInfo(Model model, HttpServletRequest request, int articleId) {
		String servletPath = request.getRequestURI();
		model.addAttribute("servletPath", servletPath);
		
		WxArticle article = wxArticleService.loadById(articleId);
		model.addAttribute("article", article);
		return "article/articleInfo";
	}
	
	
}
