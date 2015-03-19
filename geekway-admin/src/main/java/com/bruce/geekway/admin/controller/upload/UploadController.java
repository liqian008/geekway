package com.bruce.geekway.admin.controller.upload;

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
import com.bruce.foundation.model.result.ApiJsonResult;
import com.bruce.geekway.model.exception.ErrorCode;
import com.bruce.geekway.model.upload.UploadImageResult;
import com.bruce.geekway.service.upload.IUploadService;
import com.bruce.geekway.utils.JsonResultBuilderUtil;

/**
 * 图片的上传
 * @author liqian
 *
 */
@Controller
@RequestMapping("/upload")
public class UploadController extends BaseController{

	@Autowired
	private IUploadService uploadQiniuService;
	@Autowired
	private IUploadService uploadLocalService;
	
	/**
	 * 上传到七牛cdn
	 * @param model
	 * @param file
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value = "/uploadQiniu", method = RequestMethod.POST)
	public ApiJsonResult uploadQiniu(Model model, @RequestParam("image") CommonsMultipartFile file) {
		try {
			WebUserDetails userDetail = getUserInfo();
			int userId = userDetail.getUserId();
			UploadImageResult imageUploadResult = uploadQiniuService.uploadImage(file.getBytes(), String.valueOf(userId), file.getOriginalFilename());
			if(imageUploadResult!=null){
				return JsonResultBuilderUtil.buildSuccessJson(imageUploadResult);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return JsonResultBuilderUtil.buildErrorJson(ErrorCode.UPLOAD_IMAGE_ERROR);
	}
	
	
	/**
	 * 上传到七牛cdn
	 * @param model
	 * @param file
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value = "/uploadLocal", method = RequestMethod.POST)
	public ApiJsonResult uploadLocal(Model model, @RequestParam("image") CommonsMultipartFile file) {
		try {
			WebUserDetails userDetail = getUserInfo();
			int userId = userDetail.getUserId();
			UploadImageResult imageUploadResult = uploadLocalService.uploadImage(file.getBytes(), String.valueOf(userId), file.getOriginalFilename());
			if(imageUploadResult!=null){
				return JsonResultBuilderUtil.buildSuccessJson(imageUploadResult);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return JsonResultBuilderUtil.buildErrorJson(ErrorCode.UPLOAD_IMAGE_ERROR);
	}
	
	
	/**
	 * 上传到本地
	 * @param model
	 * @param file
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value = "/upload", method = RequestMethod.POST)
	public ApiJsonResult upload(Model model, @RequestParam("image") CommonsMultipartFile file) {
		try {
			WebUserDetails userDetail = getUserInfo();
			int userId = userDetail.getUserId();
			UploadImageResult imageUploadResult = uploadLocalService.uploadImage(file.getBytes(), String.valueOf(userId), file.getOriginalFilename());
			if(imageUploadResult!=null){
				return JsonResultBuilderUtil.buildSuccessJson(imageUploadResult);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return JsonResultBuilderUtil.buildErrorJson(ErrorCode.UPLOAD_IMAGE_ERROR);
	}
}
