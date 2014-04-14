package com.bruce.geekway.admin.controller;

import java.io.IOException;

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
import com.bruce.geekway.model.data.JsonResultBean;
import com.bruce.geekway.model.exception.ErrorCode;
import com.bruce.geekway.model.upload.UploadImageResult;
import com.bruce.geekway.service.IUploadService;
import com.bruce.geekway.utils.ResponseBuilderUtil;

@Controller
@RequestMapping("/geekway")
public class GeekwayUploadController extends BaseController{

	@Autowired
	private IUploadService uploadService;
	
	/**
	 * 处理ckEditor文件上传
	 * @param model
	 * @param file
	 * @param callback
	 * @return
	 */
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
	
	/**
	 * 处理图片上传
	 * @param model
	 * @param file
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value = "/imageUpload", method = RequestMethod.POST)
	public JsonResultBean imageUpload(Model model, @RequestParam("imageFile") CommonsMultipartFile file) {
		try {
			WebUserDetails userDetail = getUserInfo();
			int userId = userDetail.getUserId();
			UploadImageResult imageUploadResult = uploadService.uploadImage(file.getBytes(), userId, file.getOriginalFilename());
			if(imageUploadResult!=null){
				return ResponseBuilderUtil.buildSuccessJson(imageUploadResult);
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
		return ResponseBuilderUtil.buildErrorJson(ErrorCode.UPLOAD_IMAGE_ERROR);
	}

}
