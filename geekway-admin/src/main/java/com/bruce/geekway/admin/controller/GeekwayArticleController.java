package com.bruce.geekway.admin.controller;

import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.bruce.baseAdmin.controller.BaseController;
import com.bruce.geekway.model.WxArticle;
import com.bruce.geekway.service.IUploadService;
import com.bruce.geekway.service.IWxArticleService;

@Controller
@RequestMapping("/geekway")
public class GeekwayArticleController extends BaseController{

	@Autowired
	private IWxArticleService wxArticleService;
	@Autowired
	private IUploadService uploadService;
	
	@RequestMapping("/articleList")
	public String articleList(Model model, HttpServletRequest request) {
		String servletPath = request.getRequestURI();
		model.addAttribute("servletPath", servletPath);

		List<WxArticle> articleList = wxArticleService.queryAll();
		model.addAttribute("articleList", articleList);
		return "article/articleList";
	}

	@RequestMapping("/articleAdd")
	public String articleAdd(Model model, WxArticle article,
			HttpServletRequest request) {
		String servletPath = request.getRequestURI();
		model.addAttribute("servletPath", servletPath);

		model.addAttribute("article", article);
		return "article/articleEdit";
	}

	@RequestMapping("/articleEdit")
	public String articleEdit(Model model, HttpServletRequest request,
			int articleId) {
		String servletPath = request.getRequestURI();
		model.addAttribute("servletPath", servletPath);

		WxArticle article = wxArticleService.loadById(articleId);
		model.addAttribute("article", article);
		return "article/articleEdit";
	}

	@RequestMapping(value = "/saveArticle", method = RequestMethod.POST)
	public String saveArticle(Model model, WxArticle article,
			HttpServletRequest request) {
		String servletPath = request.getRequestURI();
		model.addAttribute("servletPath", servletPath);

		int result = 0;

		Date currentTime = new Date();
		article.setUpdateTime(currentTime);
		if (article != null && article.getId() != null && article.getId() > 0) {
			result = wxArticleService.updateById(article);
		} else {
			article.setCreateTime(currentTime);
			result = wxArticleService.save(article);
//			if(result>0){
//				
//			}
		}

		model.addAttribute("redirectUrl", "./articleList");
		return "forward:/home/operationRedirect";
	}
}
