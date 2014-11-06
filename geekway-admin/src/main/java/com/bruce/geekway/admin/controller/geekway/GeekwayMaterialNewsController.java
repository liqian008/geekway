package com.bruce.geekway.admin.controller.geekway;

import java.util.Date;
import java.util.HashMap;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.bruce.foundation.model.paging.PagingResult;
import com.bruce.geekway.constants.ConstConfig;
import com.bruce.geekway.model.WxMaterialArticle;
import com.bruce.geekway.model.WxMaterialNews;
import com.bruce.geekway.model.WxMaterialNewsArticle;
import com.bruce.geekway.model.WxMaterialNewsCriteria;
import com.bruce.geekway.service.IWxMaterialArticleService;
import com.bruce.geekway.service.IWxMaterialNewsArticleService;
import com.bruce.geekway.service.IWxMaterialNewsService;


/**
 * 多图文管理controller
 * @author liqian
 *
 */
@Controller
@RequestMapping("/geekway")
public class GeekwayMaterialNewsController {

	private static final int pageSize = ConstConfig.PAGE_SIZE_DEFAULT;

	@Autowired
	private IWxMaterialNewsService wxMaterialNewsService;
	@Autowired
	private IWxMaterialArticleService wxMaterialArticleService;
	@Autowired
	private IWxMaterialNewsArticleService wxMaterialNewsArticleService;
	
	/**
	 * 分页方式查询
	 * @param model
	 * @param pageNo
	 * @param pageSize
	 * @param request
	 * @return
	 */
	@SuppressWarnings("unchecked")
	@RequestMapping("/materialNewsPaging")
	public String materialNewsPaging(Model model, @RequestParam(defaultValue="1")int pageNo, HttpServletRequest request) {
		String servletPath = request.getRequestURI();
		model.addAttribute("servletPath", servletPath);
		
		model.addAttribute("pageNo", pageNo);
		
		WxMaterialNewsCriteria criteria = new WxMaterialNewsCriteria();
		criteria.setOrderByClause(" id desc");
		
		PagingResult<WxMaterialNews> materialNewsPagingData = wxMaterialNewsService.pagingByCriteria(pageNo, pageSize  , criteria);
		if(materialNewsPagingData!=null){
			materialNewsPagingData.setRequestUri(request.getRequestURI());
			
			HashMap<String, Object> queryMap = new HashMap<String, Object>();
			queryMap.putAll(request.getParameterMap());
			materialNewsPagingData.setQueryMap(queryMap);
			model.addAttribute("materialNewsPagingData", materialNewsPagingData);
		}
		return "material/materialNewsListPaging";
	}
	
	
	@RequestMapping("/materialNewsList")
	public String materialNewsList(Model model, HttpServletRequest request) {
		String servletPath = request.getRequestURI();
		model.addAttribute("servletPath", servletPath);
		
		List<WxMaterialNews> materialNewsList = wxMaterialNewsService.queryAll();
		model.addAttribute("materialNewsList", materialNewsList);
		return "material/materialNewsList";
	}
	
	@RequestMapping("/materialNewsAdd")
	public String materialNewsAdd(Model model, WxMaterialNews MaterialNews, HttpServletRequest request) {
		String servletPath = request.getRequestURI();
		model.addAttribute("servletPath", servletPath);
		
		model.addAttribute("materialNews", MaterialNews);
		return "material/materialNewsEdit";
	}
	
	@RequestMapping("/materialNewsEdit")
	public String materialNewsEdit(Model model, HttpServletRequest request, int newsId) {
		String servletPath = request.getRequestURI();
		model.addAttribute("servletPath", servletPath);
		
		WxMaterialNews MaterialNews = wxMaterialNewsService.loadById(newsId);
		model.addAttribute("materialNews", MaterialNews);
		return "material/materialNewsEdit";
	}
	
	@RequestMapping(value = "/saveMaterialNews", method = RequestMethod.POST)
	public String saveMaterialNews(Model model, WxMaterialNews MaterialNews, HttpServletRequest request) {
		String servletPath = request.getRequestURI();
		model.addAttribute("servletPath", servletPath);
		
		int result = 0;
		
		Date currentTime = new Date();
		MaterialNews.setUpdateTime(currentTime);
		if(MaterialNews!=null&&MaterialNews.getId()!=null&&MaterialNews.getId()>0){
			result = wxMaterialNewsService.updateById(MaterialNews);
		}else{
			MaterialNews.setCreateTime(currentTime);
			result = wxMaterialNewsService.save(MaterialNews);
		}
		
		model.addAttribute("redirectUrl", "./materialNewsPaging");
		return "forward:/home/operationRedirect";
	}
	
	@RequestMapping("/delMaterialNews")
	public String delMaterialNews(Model model, int newsId) {
		
		//删除关联表
		wxMaterialNewsArticleService.deleteByNewsId(newsId);
		
		//删除实体
		wxMaterialNewsService.deleteById(newsId);

		model.addAttribute("redirectUrl", "./materialNewsPaging");
		return "forward:/home/operationRedirect";
	}
	
	@RequestMapping("/topMaterialArticle")
	public String topMaterialArticle(Model model, int newsId, int articleId) {
		//置顶操作
		wxMaterialNewsArticleService.topArticle(newsId, articleId);
		
		//重新设置news的缩略图和摘要
		reloadNewsDigest(newsId);

		model.addAttribute("redirectUrl", "./materialNewsArticleSet?newsId="+newsId);
		return "forward:/home/operationRedirect";
	}
	
	/**
	 * 列出当前news对应的文章列表
	 * @param model
	 * @param newsId
	 * @param request
	 * @return
	 */
	@RequestMapping("/materialNewsArticleSet")
	public String materialNewsArticleSet(Model model,int newsId, HttpServletRequest request) {
		String servletPath = request.getRequestURI();
		model.addAttribute("servletPath", servletPath);

		WxMaterialNews materialNews = wxMaterialNewsService.loadById(newsId);
		model.addAttribute("materialNews", materialNews);
		
		List<WxMaterialArticle> mappedArticleList = wxMaterialArticleService.queryMaterialArticlesByNewsId(newsId);
		model.addAttribute("mappedArticleList", mappedArticleList);
		
		return "material/materialNewsArticleSet";
	}
	
	/**
	 * 
	 * @param model
	 * @param newsId
	 * @param request
	 * @return
	 */
	@RequestMapping("/materialNewsArticleSetAdd")
	public String materialNewsArticleAdd(Model model,int newsId, HttpServletRequest request) {
		String servletPath = request.getRequestURI();
		model.addAttribute("servletPath", servletPath);

		WxMaterialNews materialNews = wxMaterialNewsService.loadById(newsId);
		model.addAttribute("materialNews", materialNews);
		
		List<WxMaterialArticle> unmappedArticleList = wxMaterialArticleService.queryMaterialArticlesOutNewsId(newsId);
		model.addAttribute("unmappedArticleList", unmappedArticleList);
		
		return "material/materialNewsArticleSetAdd";
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
		
		WxMaterialNewsArticle obj = new WxMaterialNewsArticle();
		obj.setNewsId(newsId);
		obj.setArticleId(articleId);
		obj.setTopTime(new Date());
		wxMaterialNewsArticleService.save(obj);
		
		//设置news的缩略图和摘要
		reloadNewsDigest(newsId);
		
		model.addAttribute("redirectUrl", "./materialNewsArticleSet?newsId="+newsId);
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
		
		int result = wxMaterialNewsArticleService.delete(newsId, articleId); 
		
		//重新设置news的缩略图和摘要
		reloadNewsDigest(newsId);
		
		
		model.addAttribute("redirectUrl", "./materialNewsArticleSet?newsId="+newsId);
		return "forward:/home/operationRedirect";
	}
	
	/**
	 * 重新加载news的显示（通常用于关联、取消关联、置顶图文时）
	 * @param newsId
	 */
	private void reloadNewsDigest(int newsId){
		if(newsId>0){
			//设置news的缩略图和摘要
			List<WxMaterialArticle> articleList = wxMaterialArticleService.queryMaterialArticlesByNewsId(newsId);
			WxMaterialNews materialNews = new WxMaterialNews();
			if(articleList!=null&&articleList.size()>0){
				WxMaterialArticle article = articleList.get(0);
				
				materialNews.setCoverImageUrl(article.getCoverImageUrl());
				materialNews.setDigest(article.getTitle());
			}else{
				materialNews.setCoverImageUrl("");
				materialNews.setDigest("");
			}
			WxMaterialNewsCriteria criteria = new WxMaterialNewsCriteria();
			criteria.createCriteria().andIdEqualTo(newsId);
			wxMaterialNewsService.updateByCriteria(materialNews, criteria);
		}
	}
}
