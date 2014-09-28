package com.bruce.geekway.service.upload.impl;

import java.io.File;
import java.io.IOException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import com.bruce.geekway.model.upload.UploadImageInfo;
import com.bruce.geekway.model.upload.UploadImageResult;
import com.bruce.geekway.service.upload.IUploadService;
import com.bruce.geekway.utils.ImageUtil;
import com.bruce.geekway.utils.UploadUtil;


@Service
public class UploadServiceImpl implements IUploadService {
	
	private static final Logger logger = LoggerFactory.getLogger(UploadImageInfo.class);
	
	
	/* 普通文件类型 */
	public static final short UPLOAD_FILE_TYPE_NORMAL = 0;
	/* 图片文件类型 */
	public static final short UPLOAD_FILE_TYPE_IMAGE = 1;
	
	// 文件存储的绝对路径
	public static final String BASE_PATH = UploadUtil.getBasePath();
	// 文件的baseUrl
	public static final String BASE_URL = UploadUtil.getBaseUrl();

	/**
	 * 保存文件
	 */
	@Override
	public String uploadFile(byte[] data, String userId, String fileName) throws IOException {
		String newFileName = UploadUtil.getFileName(userId, fileName);
		String fileUrl = UploadUtil.saveFile(data, BASE_PATH, UploadUtil.getImagePath(), newFileName);
		return fileUrl;
	}

	/**
	 * 上传图片，需按尺寸切割
	 * 
	 * @throws IOException
	 */
	@Override
	public UploadImageResult uploadImage(byte[] data, String userId, String filename, UploadImageInfo... imageSpecs) throws IOException {
		long time = System.currentTimeMillis();
		// 获取图片存储的绝对、相对路径及文件名
		String imageDirPath = UploadUtil.getImagePath(time);
		String absoultImagePath = BASE_PATH + imageDirPath;
		
		logger.debug("upload absoultImagePath: "+absoultImagePath);

		String imageName = UploadUtil.getFileNameWithPlaceHolder(userId, filename, null, time);
		
		logger.debug("upload image rename to: "+imageName);

		String originalImageSpec = "original";
		
		String originalUrl = UploadUtil.saveFile(data, BASE_PATH, imageDirPath+ UploadUtil.FILE_SEPARTOR + originalImageSpec,  imageName);

		// 构造uploadResult
		UploadImageResult uploadResult = new UploadImageResult();
		UploadImageInfo originalImage = new UploadImageInfo(imageName, UPLOAD_FILE_TYPE_IMAGE, originalImageSpec, originalUrl, -1);
		//增加origin图片result
		uploadResult.getUploadImageList().add(originalImage);
		
		if(imageSpecs!=null&&imageSpecs.length>0){
			for (UploadImageInfo imageSpec : imageSpecs) {
				int width = imageSpec.getWidth();
				String scaleImagePath = absoultImagePath + UploadUtil.FILE_SEPARTOR +imageSpec;
				File scaleImageFile = new File(scaleImagePath);
				scaleImageFile.mkdirs();
				
				//图片缩放
				ImageUtil.scaleByWidth(absoultImagePath +"/"+originalImageSpec+"/"+ imageName, scaleImagePath+"/"+imageName, width);
				//TODO水印处理
				String imageUrl = BASE_URL + imageDirPath +"/"+imageSpec+"/"+ imageName;
				UploadImageInfo imageInfo = new UploadImageInfo(imageName, UPLOAD_FILE_TYPE_IMAGE, imageSpec.getImageSpec(), imageUrl, -1);

				//增加不同规格图片result
				uploadResult.getUploadImageList().add(imageInfo);
			}
		}
		return uploadResult;
	}
}
