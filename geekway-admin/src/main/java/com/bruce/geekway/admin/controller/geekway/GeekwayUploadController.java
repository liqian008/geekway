package com.bruce.geekway.admin.controller.geekway;

import java.io.File;
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
import com.bruce.geekway.service.mp.WxMpMediaUploadService;
import com.bruce.geekway.service.upload.IUploadService;
import com.bruce.geekway.utils.JsonResultBuilderUtil;
import com.bruce.geekway.utils.UploadUtil;

@Controller
@RequestMapping("/geekway")
public class GeekwayUploadController extends BaseController{

	@Autowired
	private IUploadService uploadService;
	
	@Autowired
	private WxMpMediaUploadService wxMediaUploadService;
	
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
			UploadImageResult imageUploadResult = uploadService.uploadImage(file.getBytes(), String.valueOf(userId), file.getOriginalFilename());
			model.addAttribute("imageUploadResult", imageUploadResult);
			model.addAttribute("callback", callback);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "geekway/ckUploadResult";
	}
	
	/**
	 * 处理正常流程的图片上传
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
			UploadImageResult imageUploadResult = uploadService.uploadImage(file.getBytes(), String.valueOf(userId), file.getOriginalFilename());
			if(imageUploadResult!=null){
				return JsonResultBuilderUtil.buildSuccessJson(imageUploadResult);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return JsonResultBuilderUtil.buildErrorJson(ErrorCode.UPLOAD_IMAGE_ERROR);
	}
	
	/**
	 * 上传微信图片素材(需返回mediaId)
	 * @param model
	 * @param image
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value = "/wxImageUpload", method = RequestMethod.POST)
	public JsonResultBean wxImageUpload(Model model, @RequestParam("image") CommonsMultipartFile image) {
		WebUserDetails userDetail = getUserInfo();
		int userId = userDetail.getUserId();
		UploadImageResult imageUploadResult;
		try {
			imageUploadResult = uploadService.uploadImage(image.getBytes(), String.valueOf(userId), image.getOriginalFilename(), IUploadService.IMAGE_SPEC_MEDIUM);
			
			if(imageUploadResult!=null && imageUploadResult.getUploadImageList().size()>0){
				File imageFile = UploadUtil.loadFileByUrl(imageUploadResult.getUploadImageList().get(1).getUrl());//第0条为originalUrl的数据
				if(imageFile!=null){//文件存在，可以上传至微信服务器
					WxMediaUploadResult wxImageUploadResult = wxMediaUploadService.uploadImage(imageFile);
					imageUploadResult.setWxMediaResult(wxImageUploadResult);
					//返回正确响应
					return JsonResultBuilderUtil.buildSuccessJson(imageUploadResult);
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}

		return JsonResultBuilderUtil.buildErrorJson(ErrorCode.UPLOAD_IMAGE_ERROR);
	}
	
	/**
	 * 上传微信缩略图素材(需返回thumb_media_id)
	 * @param model
	 * @param thumbImage
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value = "/wxThumbUpload", method = RequestMethod.POST)
	public JsonResultBean wxThumbUpload(Model model, @RequestParam("thumbImage") CommonsMultipartFile thumbImage) {
		WebUserDetails userDetail = getUserInfo();
		int userId = userDetail.getUserId();
		UploadImageResult imageUploadResult;
		try {
			//缩略图上传小尺寸
			imageUploadResult = uploadService.uploadImage(thumbImage.getBytes(), String.valueOf(userId), thumbImage.getOriginalFilename(), IUploadService.IMAGE_SPEC_SMALL);
		
			if(imageUploadResult!=null && imageUploadResult.getUploadImageList().size()>0){
				File thumbFile = UploadUtil.loadFileByUrl(imageUploadResult.getUploadImageList().get(1).getUrl());//第0条为originalUrl的数据
				if(thumbFile!=null){//文件存在，可以上传至微信服务器
					WxMediaUploadResult wxThumbUploadResult = wxMediaUploadService.uploadThumb(thumbFile);
					imageUploadResult.setWxMediaResult(wxThumbUploadResult);
					//返回正确响应
					return JsonResultBuilderUtil.buildSuccessJson(imageUploadResult);
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return JsonResultBuilderUtil.buildErrorJson(ErrorCode.UPLOAD_IMAGE_ERROR);
	}

}
