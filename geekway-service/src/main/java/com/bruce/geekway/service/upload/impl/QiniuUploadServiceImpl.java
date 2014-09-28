package com.bruce.geekway.service.upload.impl;

import java.io.File;
import java.io.IOException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import com.bruce.geekway.constants.ConstUpload;
import com.bruce.geekway.model.upload.UploadImageInfo;
import com.bruce.geekway.model.upload.UploadImageResult;
import com.bruce.geekway.utils.UploadUtil;
import com.qiniu.api.auth.digest.Mac;
import com.qiniu.api.io.IoApi;
import com.qiniu.api.io.PutExtra;
import com.qiniu.api.io.PutRet;
import com.qiniu.api.rs.PutPolicy;

/**
 * 七牛上传的处理实现
 * 
 * @author liqian
 * 
 */
@Service
public class QiniuUploadServiceImpl {
	
	// 文件存储的绝对路径
	public static final String BASE_PATH = UploadUtil.getBasePath();
	// 文件的baseUrl
	public static final String BASE_URL =  UploadUtil.getBaseUrl();
	
	private Mac mac = new Mac(ConstUpload.UPLOAD_QINIU_APP_KEY, ConstUpload.UPLOAD_QINIU_SECRET_KEY);
		
	private static final Logger logger = LoggerFactory.getLogger(QiniuUploadServiceImpl.class);

	
	/**
	 * 上传七牛服务器，
	 * @param fileKey 通常使用路径名(但避免以/开头)
	 * @param uploadFile
	 * @return
	 * @throws Exception
	 */
	private String basicUpload(String fileKey, File uploadFile) throws Exception {
		logger.info("七牛上传文件, fileKey: "+fileKey);
		PutPolicy putPolicy = new PutPolicy(ConstUpload.UPLOAD_QINIU_BUCKET);
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
	
	
	/**
	 * 上传文件，返回完整的url
	 * @param userId
	 * @param file
	 * @return
	 * @throws Exception
	 */
	public String uploadFile(String userId, File file) throws Exception {
		String newFileName = UploadUtil.getFileName(userId, file.getName());
		String directory = UploadUtil.getFilePath();
		String fileKey = directory + UploadUtil.FILE_SEPARTOR + newFileName;
		if(fileKey.startsWith("/")){
			fileKey = fileKey.substring(1);
		}
		String resultKey = basicUpload(fileKey, file);
		if(resultKey!=null){
			return BASE_URL + UploadUtil.FILE_SEPARTOR + resultKey; 
		}
		return null;
	}
	
	/**
	 * 上传图片
	 * @param data
	 * @param userId
	 * @param filename
	 * @param imageSpecs
	 * @return
	 * @throws IOException
	 */
	public UploadImageResult uploadImage(File image, String userId, UploadImageInfo... imageSpecs) throws Exception {
		String newFileName = UploadUtil.getFileName(userId, image.getName());
		String directory = UploadUtil.getImagePath();
		String fileKey = directory + UploadUtil.FILE_SEPARTOR + newFileName;
		if(fileKey.startsWith("/")){
			fileKey = fileKey.substring(1);
		}
		String resultKey = basicUpload(fileKey, image);
		if(resultKey!=null){
			UploadImageResult uploadImageResult = new UploadImageResult();
			String originUrl = BASE_URL + UploadUtil.FILE_SEPARTOR + resultKey;
			uploadImageResult.add(buildImageResult(originUrl, "origin", 0));//原始图片
			
			if(imageSpecs!=null&&imageSpecs.length>0){
				for (UploadImageInfo imageSpec : imageSpecs) {
					int width = imageSpec.getWidth();
					String itemUrl = originUrl+"?imageView/2/w/"+width;
					uploadImageResult.add(buildImageResult(itemUrl, imageSpec.getImageSpec(), width));
				}
			}
			return uploadImageResult;
		}
		return null;
	}
		


	
	private static UploadImageInfo buildImageResult(String url, String imageSpec, int width){
		UploadImageInfo imageResult = new UploadImageInfo(imageSpec, width);
		imageResult.setUrl(url);
		return imageResult;
	}
	
	public static void main(String[] args) {
		QiniuUploadServiceImpl qiniuProcessor = new QiniuUploadServiceImpl();
		try {
			qiniuProcessor.basicUpload("file/20140901/123456.jpg", new File("/home/liqian/Desktop/pic/m2w595hq85lt_original_wGOu_3fe90000231f118c.jpg"));
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	

}
