package com.bruce.geekway.admin.controller.geekway;

import java.io.IOException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.commons.CommonsMultipartFile;

import com.bruce.foundation.admin.controller.BaseController;
import com.bruce.foundation.admin.security.WebUserDetails;
import com.bruce.geekway.model.data.JsonResultBean;
import com.bruce.geekway.model.exception.ErrorCode;
import com.bruce.geekway.model.upload.UploadImageResult;
import com.bruce.geekway.model.wx.json.response.WxMediaUploadResult;
import com.bruce.geekway.service.IUploadService;
import com.bruce.geekway.service.mp.WxMediaUploadService;
import com.bruce.geekway.utils.JsonResultBuilderUtil;

@Controller
@RequestMapping("/geekway")
public class GeekwayUploadController extends BaseController{

	@Autowired
	private IUploadService uploadService;
	
	@Autowired
	private WxMediaUploadService wxMediaUploadService;
	
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
		return "geekway/ckUploadResult";
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
				return JsonResultBuilderUtil.buildSuccessJson(imageUploadResult);
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
		return JsonResultBuilderUtil.buildErrorJson(ErrorCode.UPLOAD_IMAGE_ERROR);
	}
	
	
	
	@ResponseBody
	@RequestMapping(value = "/wxMediaUpload", method = RequestMethod.POST)
	public JsonResultBean wxMediaUpload(Model model, @RequestParam("mediaFile") CommonsMultipartFile mediaFile) {
		WebUserDetails userDetail = getUserInfo();
		int userId = userDetail.getUserId();
//			UploadImageResult imageUploadResult = uploadService.uploadImage(mediaFile.getBytes(), userId, mediaFile.getOriginalFilename());
		
		WxMediaUploadResult uploadResult = wxMediaUploadService.uploadImage(mediaFile.getBytes());
			
		return JsonResultBuilderUtil.buildErrorJson(ErrorCode.UPLOAD_IMAGE_ERROR);
	}

}
