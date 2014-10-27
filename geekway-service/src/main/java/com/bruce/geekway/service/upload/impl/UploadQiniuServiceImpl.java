package com.bruce.geekway.service.upload.impl;

import java.io.File;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import com.bruce.geekway.constants.ConstConfig;
import com.bruce.geekway.model.upload.UploadImageInfo;
import com.bruce.geekway.model.upload.UploadImageResult;
import com.qiniu.api.auth.digest.Mac;
import com.qiniu.api.io.IoApi;
import com.qiniu.api.io.PutExtra;
import com.qiniu.api.io.PutRet;
import com.qiniu.api.rs.PutPolicy;

/**
 * 七牛上传的处理实现
 * @author liqian
 */
@Service
public class UploadQiniuServiceImpl extends AbstractUploadService{
	
	private Mac mac = new Mac(ConstConfig.UPLOAD_QINIU_APP_KEY, ConstConfig.UPLOAD_QINIU_SECRET_KEY);
		
	private static final Logger logger = LoggerFactory.getLogger(UploadQiniuServiceImpl.class);
	
	protected String saveFile(File destFile, String fileDir) throws Exception { 
		if(fileDir.startsWith("/")){
			fileDir = fileDir.substring(1);//使用fileDir作为key，确保首字母不能为/，否则不兼容七牛
		}
		String resultKey =  basicUpload(destFile, fileDir + "/" + destFile.getName());
		return ConstConfig.UPLOAD_QINIU_BIND_DOMAIN + "/" + resultKey;
	}
	
	/**
	 * 七牛处理图片（根据所需的尺寸，返回相应的数据）
	 */
	protected UploadImageResult saveImage(File originalImageFile, String imageDir, UploadImageInfo[] imageSpecs) throws Exception {
		if(imageDir.startsWith("/")){
			imageDir = imageDir.substring(1);
		}
		String resultKey =  basicUpload(originalImageFile,  imageDir + "/" + originalImageFile.getName());
		if(resultKey!=null){
			UploadImageResult uploadImageResult = new UploadImageResult();
			String originUrl = ConstConfig.UPLOAD_QINIU_BIND_DOMAIN + "/" + resultKey;
			
			uploadImageResult.put(buildImageResult(originUrl, "original", 0));//原始图片
			
			if(imageSpecs!=null&&imageSpecs.length>0){
				for (UploadImageInfo imageSpec : imageSpecs) {
					int width = imageSpec.getWidth();
					String itemUrl = originUrl+"?imageView/2/w/"+width;
					uploadImageResult.put(buildImageResult(itemUrl, imageSpec.getImageSpec(), width));
				}
			}
			return uploadImageResult;
		}
		return null;
	}
	
	
	/**
	 * 上传七牛服务器，
	 * @param fileKey 通常使用路径名(但避免以/开头)
	 * @param uploadFile
	 * @return
	 * @throws Exception
	 */
	private String basicUpload(File uploadFile, String fileKey) throws Exception {
		logger.info("七牛上传文件, fileKey: "+fileKey);
		PutPolicy putPolicy = new PutPolicy(ConstConfig.UPLOAD_QINIU_BUCKET);
		String uptoken = putPolicy.token(mac);

		// key是上传内容对应的标识，可设置为习惯的目录形式
		PutRet ret = null;
		String localRealPath = uploadFile.getAbsolutePath();
		try {
			ret = IoApi.putFile(uptoken, fileKey, localRealPath, new PutExtra());
			if (ret.ok()){
				String resultKey = ret.getKey();
				System.out.println(resultKey);
				return resultKey;
			}
		} catch (Throwable e) {
			logger.error("上传七牛失败！", e);
		}
		return null;
	}
	
	private static UploadImageInfo buildImageResult(String url, String imageSpec, int width){
		UploadImageInfo imageResult = new UploadImageInfo(imageSpec, width);
		imageResult.setUrl(url);
		return imageResult;
	}

}
