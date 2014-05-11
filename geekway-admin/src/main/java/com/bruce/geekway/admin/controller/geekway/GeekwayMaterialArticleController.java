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
import com.bruce.geekway.model.WxMaterialArticle;
import com.bruce.geekway.service.IWxMaterialArticleService;

@Controller
@RequestMapping("/geekway")
public class GeekwayMaterialArticleController extends BaseController {

	@Autowired
	private IWxMaterialArticleService wxMaterialArticleService;

	@RequestMapping("/materialArticleList")
	public String materialArticleList(Model model, HttpServletRequest request) {
		String servletPath = request.getRequestURI();
		model.addAttribute("servletPath", servletPath);

		List<WxMaterialArticle> materialArticleList = wxMaterialArticleService.queryAll();
		model.addAttribute("materialArticleList", materialArticleList);
		return "geekway/materialArticleList";
	}

	@RequestMapping("/materialArticleAdd")
	public String materialArticleAdd(Model model, WxMaterialArticle materialArticle, HttpServletRequest request) {
		String servletPath = request.getRequestURI();
		model.addAttribute("servletPath", servletPath);

		model.addAttribute("materialArticle", materialArticle);

		return "geekway/materialArticleEdit";
	}

	@RequestMapping("/materialArticleEdit")
	public String materialArticleEdit(Model model, HttpServletRequest request, int articleId) {
		String servletPath = request.getRequestURI();
		model.addAttribute("servletPath", servletPath);

		WxMaterialArticle materialArticle = wxMaterialArticleService.loadById(articleId);
		model.addAttribute("materialArticle", materialArticle);
		return "geekway/materialArticleEdit";
	}

	@RequestMapping(value = "/saveMaterialArticle", method = RequestMethod.POST)
	public String saveMaterialArticle(Model model, WxMaterialArticle materialArticle, HttpServletRequest request) {
		String servletPath = request.getRequestURI();
		model.addAttribute("servletPath", servletPath);

		int result = 0;

		Date currentTime = new Date();
		materialArticle.setUpdateTime(currentTime);
		if (materialArticle != null && materialArticle.getId() != null && materialArticle.getId() > 0) {
			result = wxMaterialArticleService.updateById(materialArticle);
		} else {
			materialArticle.setCreateTime(currentTime);
			result = wxMaterialArticleService.save(materialArticle);
		}

		model.addAttribute("redirectUrl", "./materialArticleList");
		return "forward:/home/operationRedirect";
	}

	@RequestMapping("/delMaterialArticle")
	public String delMaterialArticle(Model model, int articleId) {

		// 删除资源的关联
		// wxCommandMaterialService.deleteByCommandId(materialId);

		// 删除menu实体
		wxMaterialArticleService.deleteById(articleId);

		model.addAttribute("redirectUrl", "./materialArticleList");
		return "forward:/home/operationRedirect";
	}

}
