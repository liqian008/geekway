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
import com.bruce.geekway.model.WxMaterialArticles;
import com.bruce.geekway.service.IWxMaterialArticlesService;

@Controller
@RequestMapping("/geekway")
public class GeekwayMaterialArticlesController extends BaseController {

	@Autowired
	private IWxMaterialArticlesService wxMaterialArticlesService;

	@RequestMapping("/materialArticlesList")
	public String materialArticlesList(Model model, HttpServletRequest request) {
		String servletPath = request.getRequestURI();
		model.addAttribute("servletPath", servletPath);

		List<WxMaterialArticles> materialArticlesList = wxMaterialArticlesService.queryAll();
		model.addAttribute("materialArticlesList", materialArticlesList);
		return "geekway/materialArticlesList";
	}

	@RequestMapping("/materialArticlesAdd")
	public String materialArticlesAdd(Model model, WxMaterialArticles materialArticles, HttpServletRequest request) {
		String servletPath = request.getRequestURI();
		model.addAttribute("servletPath", servletPath);

		model.addAttribute("materialArticles", materialArticles);

		return "geekway/materialArticlesEdit";
	}

	@RequestMapping("/materialArticlesEdit")
	public String materialArticlesEdit(Model model, HttpServletRequest request, int articlesId) {
		String servletPath = request.getRequestURI();
		model.addAttribute("servletPath", servletPath);

		WxMaterialArticles materialArticles = wxMaterialArticlesService.loadById(articlesId);
		model.addAttribute("materialArticles", materialArticles);
		return "geekway/materialArticlesEdit";
	}

	@RequestMapping(value = "/saveMaterialArticless", method = RequestMethod.POST)
	public String saveMaterialArticless(Model model, WxMaterialArticles materialArticles, HttpServletRequest request) {
		String servletPath = request.getRequestURI();
		model.addAttribute("servletPath", servletPath);

		int result = 0;

		Date currentTime = new Date();
		materialArticles.setUpdateTime(currentTime);
		if (materialArticles != null && materialArticles.getId() != null && materialArticles.getId() > 0) {
			result = wxMaterialArticlesService.updateById(materialArticles);
		} else {
			materialArticles.setCreateTime(currentTime);
			result = wxMaterialArticlesService.save(materialArticles);
		}

		model.addAttribute("redirectUrl", "./materialArticlesList");
		return "forward:/home/operationRedirect";
	}

	@RequestMapping("/delMaterialArticles")
	public String delMaterialArticless(Model model, int articlesId) {

		// 删除资源的关联
		// wxCommandMaterialService.deleteByCommandId(materialId);

		// 删除menu实体
		wxMaterialArticlesService.deleteById(articlesId);

		model.addAttribute("redirectUrl", "./materialArticlesList");
		return "forward:/home/operationRedirect";
	}

}
