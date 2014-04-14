package com.bruce.geekway.admin.controller;

import java.io.IOException;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.commons.CommonsMultipartFile;

import com.bruce.baseAdmin.controller.BaseController;
import com.bruce.baseAdmin.security.WebUserDetails;
import com.bruce.geekway.model.WxArticle;
import com.bruce.geekway.model.upload.UploadImageResult;
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
		}

		model.addAttribute("redirectUrl", "./articleList");
		return "forward:/home/operationRedirect";
	}

	//处理ckEditor文件上传
	@RequestMapping(value = "/ckEditorImageUpload", method = RequestMethod.POST)
	public String ckEditorImageUpload(Model model, @RequestParam("upload") CommonsMultipartFile file, @RequestParam("CKEditorFuncNum")String callback) {
		try {
			WebUserDetails userDetail = getUserInfo();
			int userId = userDetail.getUserId();
			UploadImageResult imageUploadResult = uploadService.uploadImage(file.getBytes(), userId, file.getOriginalFilename());
			model.addAttribute("imageUploadResult", imageUploadResult);
			model.addAttribute("callback", callback);
		} catch (IOException e) {
			e.printStackTrace();
		}
		return "article/uploadResult";
	}
	
	// 处理图片上传
	@ResponseBody
	@RequestMapping(value = "/imageUpload", method = RequestMethod.POST)
	public Object imageUpload(Model model, @RequestParam("imageFile") CommonsMultipartFile file) {
		try {
			WebUserDetails userDetail = getUserInfo();
			int userId = userDetail.getUserId();
			UploadImageResult imageUploadResult = uploadService.uploadImage(file.getBytes(), userId, file.getOriginalFilename());
			return imageUploadResult;
		} catch (IOException e) {
			e.printStackTrace();
		}
		return "";
	}

}
