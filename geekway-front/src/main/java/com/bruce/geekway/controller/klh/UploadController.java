package com.bruce.geekway.controller.klh;

import java.io.IOException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.commons.CommonsMultipartFile;

import com.bruce.geekway.model.data.JsonResultBean;
import com.bruce.geekway.model.exception.ErrorCode;
import com.bruce.geekway.model.upload.UploadImageResult;
import com.bruce.geekway.service.IUploadService;
import com.bruce.geekway.utils.JsonResultBuilderUtil;

@Controller
@RequestMapping("/klh")
public class UploadController{

	@Autowired
	private IUploadService uploadService;
	
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
			int userId = 0;
			UploadImageResult imageUploadResult = uploadService.uploadImage(file.getBytes(), userId, file.getOriginalFilename());
			if(imageUploadResult!=null){
				return JsonResultBuilderUtil.buildSuccessJson(imageUploadResult);
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
		return JsonResultBuilderUtil.buildErrorJson(ErrorCode.UPLOAD_IMAGE_ERROR);
	}

}
