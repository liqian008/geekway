package com.bruce.geekway.admin.controller.product;

import java.util.Date;
import java.util.HashMap;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.bruce.foundation.model.paging.PagingResult;
import com.bruce.geekway.constants.ConstConfig;
import com.bruce.geekway.model.WxProductNews;
import com.bruce.geekway.model.WxProductNewsCriteria;
import com.bruce.geekway.service.product.IWxProductNewsService;



@Controller
@RequestMapping("/product")
public class WxProductNewsController {
	

	private static final int pageSize = ConstConfig.PAGE_SIZE_DEFAULT;
	

	@Autowired
	private IWxProductNewsService wxProductNewsService;
	
	/**
	 * 分页方式查询
	 * @param model
	 * @param pageNo
	 * @param pageSize
	 * @param request
	 * @return
	 */
	@SuppressWarnings("unchecked")
	@RequestMapping("/newsPaging")
	public String productNewsPaging(Model model, @RequestParam(defaultValue="1")int pageNo, HttpServletRequest request) {
		String servletPath = request.getRequestURI();
		model.addAttribute("servletPath", servletPath);
		
		model.addAttribute("pageNo", pageNo);
		
		WxProductNewsCriteria criteria = new WxProductNewsCriteria();
		criteria.setOrderByClause(" id desc");
		WxProductNewsCriteria.Criteria subCriteria = criteria.createCriteria();
		
		PagingResult<WxProductNews> productNewsPagingData = wxProductNewsService.pagingByCriteria(pageNo, pageSize , criteria);
		if(productNewsPagingData!=null){
			productNewsPagingData.setRequestUri(request.getRequestURI());
			
			HashMap<String, Object> queryMap = new HashMap<String, Object>();
			queryMap.putAll(request.getParameterMap());
			productNewsPagingData.setQueryMap(queryMap);
			model.addAttribute("productNewsPagingData", productNewsPagingData);
		}
		return "product/newsListPaging";
	}
	
	
	@RequestMapping("/newsAdd")
	public String newsAdd(Model model, WxProductNews news, HttpServletRequest request) {
		String servletPath = request.getRequestURI();
		model.addAttribute("servletPath", servletPath);
		
		model.addAttribute("news", news);
		return "product/newsEdit";
	}
	
	@RequestMapping("/newsEdit")
	public String newsEdit(Model model, HttpServletRequest request, int newsId) {
		String servletPath = request.getRequestURI();
		model.addAttribute("servletPath", servletPath);
		
		WxProductNews ProductNews = wxProductNewsService.loadById(newsId);
		model.addAttribute("news", ProductNews);
		return "product/newsEdit";
	}
	
	@RequestMapping(value = "/saveProductNews", method = RequestMethod.POST)
	public String saveProductNews(Model model, WxProductNews news, HttpServletRequest request) {
		String servletPath = request.getRequestURI();
		model.addAttribute("servletPath", servletPath);
		
		int result = 0;
		Date currentTime = new Date();
		news.setUpdateTime(currentTime);
		if(news!=null&&news.getId()!=null&&news.getId()>0){
			result = wxProductNewsService.updateById(news);
		}else{
			news.setCreateTime(currentTime);
			result = wxProductNewsService.save(news);
		}
		
		model.addAttribute("redirectUrl", "./newsPaging");
		return "forward:/home/operationRedirect";
	}
	
	
	/**
	 * 删除news
	 * @param model
	 * @param request
	 * @return
	 */
	@RequestMapping(value = "/delProductNews")
	public String delProductNews(Model model, int newsId, HttpServletRequest request) {
		String servletPath = request.getRequestURI();
		model.addAttribute("servletPath", servletPath);
	
		int result = wxProductNewsService.deleteById(newsId);
		
		model.addAttribute("redirectUrl", "./newsPaging");
		return "forward:/home/operationRedirect"; 
	}
}