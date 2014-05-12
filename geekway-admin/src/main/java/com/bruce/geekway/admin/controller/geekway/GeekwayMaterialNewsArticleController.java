package com.bruce.geekway.admin.controller.geekway;

import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.bruce.geekway.model.WxMaterialArticle;
import com.bruce.geekway.model.WxMaterialNews;
import com.bruce.geekway.model.WxMaterialNewsArticle;
import com.bruce.geekway.service.IWxMaterialArticleService;
import com.bruce.geekway.service.IWxMaterialNewsArticleService;
import com.bruce.geekway.service.IWxMaterialNewsService;


@Controller
@RequestMapping("/geekway")
public class GeekwayMaterialNewsArticleController {

	@Autowired
	private IWxMaterialNewsService wxMaterialNewsService;
	@Autowired
	private IWxMaterialNewsArticleService wxMaterialNewsArticleService;
	@Autowired
	private IWxMaterialArticleService wxMaterialArticleService;
	
	
	/**
	 * 列出当前module对应的文章列表
	 * @param model
	 * @param newsId
	 * @param request
	 * @return
	 */
	@RequestMapping("/materialNewsSet")
	public String materialNewsSet(Model model,int newsId, HttpServletRequest request) {
		String servletPath = request.getRequestURI();
		model.addAttribute("servletPath", servletPath);

		WxMaterialNews materialNews = wxMaterialNewsService.loadById(newsId);
		model.addAttribute("materialNews", materialNews);
		
		List<WxMaterialArticle> mappedArticleList = wxMaterialArticleService.queryMaterialArticlesByNewsId(newsId);
		model.addAttribute("mappedArticleList", mappedArticleList);
		
		return "geekway/materialNewsArticleSet";
	}
	
	
	/**
	 * 
	 * @param model
	 * @param newsId
	 * @param request
	 * @return
	 */
	@RequestMapping("/materialNewsSetAdd")
	public String materialNewsAdd(Model model,int newsId, HttpServletRequest request) {
		String servletPath = request.getRequestURI();
		model.addAttribute("servletPath", servletPath);

		WxMaterialNews materialNews = wxMaterialNewsService.loadById(newsId);
		model.addAttribute("materialNews", materialNews);
		
		List<WxMaterialArticle> unmappedArticleList = wxMaterialArticleService.queryMaterialArticlesOutNewsId(newsId);
		model.addAttribute("unmappedArticleList", unmappedArticleList);
		
		return "geekway/materialNewsArticleSetAdd";
	}
	
	/**
	 * 
	 * @param model
	 * @param newsId
	 * @param request
	 * @return
	 */
	@RequestMapping("/addMaterialNewsArticle")
	public String addMaterialNewsArticle(Model model,int newsId, int articleId, HttpServletRequest request) {
		String servletPath = request.getRequestURI();
		model.addAttribute("servletPath", servletPath);
		
		Date currentTime = new Date();
		WxMaterialNewsArticle obj = new WxMaterialNewsArticle();
		obj.setNewsId(newsId);
		obj.setArticleId(articleId);
		obj.setCreateTime(currentTime);
		obj.setTopTime(currentTime);
		
		wxMaterialNewsArticleService.save(obj);
		
		model.addAttribute("redirectUrl", "./materialNewsSet?newsId="+newsId);
		return "forward:/home/operationRedirect";
	}
	
	/**
	 * 将某图文素材置顶
	 * @param model
	 * @param newsId
	 * @param request
	 * @return
	 */
	@RequestMapping("/topMaterialNewsArticle")
	public String topMaterialNewsArticle(Model model,int newsId, int articleId, HttpServletRequest request) {
		String servletPath = request.getRequestURI();
		model.addAttribute("servletPath", servletPath);
		
		wxMaterialNewsArticleService.topNewsArticle(newsId, articleId);
		
		model.addAttribute("redirectUrl", "./materialNewsSet?newsId="+newsId);
		return "forward:/home/operationRedirect";
	}
	
	
	/**
	 * 
	 * @param model
	 * @param newsId
	 * @param request
	 * @return
	 */
	@RequestMapping("/removeMaterialNewsArticle")
	public String removeMaterialNewsArticle(Model model, int newsId, int articleId,  HttpServletRequest request) {
		String servletPath = request.getRequestURI();
		model.addAttribute("servletPath", servletPath);
		
		wxMaterialNewsArticleService.delete(newsId, articleId);
		
		model.addAttribute("redirectUrl", "./materialNewsSet?newsId="+newsId);
		return "forward:/home/operationRedirect";
	}
}
