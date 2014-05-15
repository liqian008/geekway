package com.bruce.geekway.admin.controller.geekway;

import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.bruce.baseAdmin.controller.BaseController;
import com.bruce.geekway.model.WxMaterialNews;
import com.bruce.geekway.service.IWxMaterialNewsArticleService;
import com.bruce.geekway.service.IWxMaterialNewsService;

@Controller
@RequestMapping("/geekway")
public class GeekwayMaterialNewsController extends BaseController {

	@Autowired
	private IWxMaterialNewsService wxMaterialNewsService;
//	@Autowired
//	private IWxMaterialNewsArticleService wxMaterialNewsArticleService;

	@RequestMapping("/materialNewsList")
	public String materialNewsList(Model model, HttpServletRequest request) {
		String servletPath = request.getRequestURI();
		model.addAttribute("servletPath", servletPath);

		List<WxMaterialNews> materialNewsList = wxMaterialNewsService.queryAll();
		model.addAttribute("materialNewsList", materialNewsList);
		return "geekway/materialNewsList";
	}

	@RequestMapping("/materialNewsAdd")
	public String materialNewsAdd(Model model, WxMaterialNews materialNews, HttpServletRequest request) {
		String servletPath = request.getRequestURI();
		model.addAttribute("servletPath", servletPath);

		model.addAttribute("materialNews", materialNews);

		return "geekway/materialNewsEdit";
	}

	@RequestMapping("/materialNewsEdit")
	public String materialNewsEdit(Model model, HttpServletRequest request, int newsId) {
		String servletPath = request.getRequestURI();
		model.addAttribute("servletPath", servletPath);

		WxMaterialNews materialNews = wxMaterialNewsService.loadById(newsId);
		model.addAttribute("materialNews", materialNews);
		return "geekway/materialNewsEdit";
	}

	@RequestMapping(value = "/saveMaterialNews", method = RequestMethod.POST)
	public String saveMaterialNews(Model model, WxMaterialNews materialNews, HttpServletRequest request) {
		String servletPath = request.getRequestURI();
		model.addAttribute("servletPath", servletPath);

		int result = 0;

		Date currentTime = new Date();
		materialNews.setUpdateTime(currentTime);
		if (materialNews != null && materialNews.getId() != null && materialNews.getId() > 0) {
			result = wxMaterialNewsService.updateById(materialNews);
		} else {
			materialNews.setCreateTime(currentTime);
			result = wxMaterialNewsService.save(materialNews);
		}

		model.addAttribute("redirectUrl", "./materialNewsList");
		return "forward:/home/operationRedirect";
	}

	@RequestMapping("/delMaterialNews")
	public String delMaterialNews(Model model, int newsId) {

//		// 删除资源的关联
//		wxMaterialNewsArticleService.deleteByNewsId(newsId);

		// 删除实体&关联
		int result = wxMaterialNewsService.deleteById(newsId);

		model.addAttribute("redirectUrl", "./materialNewsList");
		return "forward:/home/operationRedirect";
	}

}
