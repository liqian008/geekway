package com.bruce.geekway.service.impl;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;
import java.util.UUID;

import javax.imageio.ImageIO;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Service;
import org.springframework.util.FileCopyUtils;

import com.bruce.geekway.model.upload.UploadImageInfo;
import com.bruce.geekway.model.upload.UploadImageResult;
import com.bruce.geekway.service.IUploadService;
import com.bruce.geekway.utils.ImageUtil;
import com.bruce.geekway.utils.UploadUtil;


@Service
public class UploadServiceImpl implements IUploadService { 
	

	/* 普通文件类型 */
	public static final short UPLOAD_FILE_TYPE_NORMAL = 0;
	/* 图片文件类型 */
	public static final short UPLOAD_FILE_TYPE_IMAGE = 1;
	
	/* 原始图片类型 */
	public static final String UPLOAD_IMAGE_SPEC_ORIGINAL = "";
	/* 大图片规格 */
	public static final String UPLOAD_IMAGE_SPEC_LARGE = "large";
	/* 中图片规格 */
	public static final String UPLOAD_IMAGE_SPEC_MEDIUM = "medium";
	/* 小图片规格 */
	public static final String UPLOAD_IMAGE_SPEC_SMALL = "small";
	
	
    private static final Logger logger = Logger.getLogger(UploadServiceImpl.class);
    
	// 文件存储的绝对路径
	public static final String basePath = UploadUtil.getBasePath();
	// 文件的baseUrl
	public static final String baseUrl = UploadUtil.getBaseUrl();
	// 头像保存的相对Url

	private static Map<String, Integer> imageSizeMap = new HashMap<String, Integer>();

	static {
//		imageSizeMap.put(UPLOAD_IMAGE_SPEC_LARGE, 1024);
//		imageSizeMap.put(UPLOAD_IMAGE_SPEC_MEDIUM, 400);
//		imageSizeMap.put(UPLOAD_IMAGE_SPEC_SMALL, 200);
	}

	/**
	 * 保存文件
	 */
	@Override
	public String uploadFile(byte[] data, int userId, String fileName) throws IOException {
		String basePath = UploadUtil.getBasePath();
		String newFileName = UploadUtil.getFileName(userId, fileName);
		String fileUrl = UploadUtil.saveFile(data, basePath, UploadUtil.getImagePath(), newFileName);
		return fileUrl;
	}

	/**
	 * 上传图片，需按尺寸切割
	 * 
	 * @throws IOException
	 */
	@Override
	public UploadImageResult uploadImage(byte[] data, int userId, String filename) throws IOException {
		long time = System.currentTimeMillis();
		// 获取图片存储的绝对、相对路径及文件名
		String imageDirPath = UploadUtil.getImagePath(time);
		String absoultImagePath = basePath + imageDirPath;
		String imageName = UploadUtil.getFileNameWithPlaceHolder(userId, filename, null, time);

		String originalImageSpec = "original";
		String originalUrl = UploadUtil.saveFile(data, basePath, imageDirPath+ "/"+originalImageSpec,  imageName);

		// 构造uploadResult
		UploadImageResult uploadResult = new UploadImageResult();
		UploadImageInfo originalImage = new UploadImageInfo(imageName, UPLOAD_FILE_TYPE_IMAGE, originalImageSpec, originalUrl, -1);
		uploadResult.setOriginalImage(originalImage);
		
		Set<String> keys = imageSizeMap.keySet();
		// 根据需要的尺寸进行zoom
		for (String imageSpec : keys) {
			int width = imageSizeMap.get(imageSpec);// 获取指定的尺寸
			String scaleImagePath = absoultImagePath+"/"+imageSpec;
			File scaleImageFile = new File(scaleImagePath);
			scaleImageFile.mkdirs();
			
			//图片缩放
			ImageUtil.scaleByWidth(absoultImagePath +"/"+originalImageSpec+"/"+ imageName, scaleImagePath+"/"+imageName, width);
			//水印处理
			String imageUrl = baseUrl + imageDirPath +"/"+imageSpec+"/"+ imageName;
			UploadImageInfo imageInfo = new UploadImageInfo(imageName, UPLOAD_FILE_TYPE_IMAGE, imageSpec, imageUrl, -1);

			// 组装uploadResult
			if (UPLOAD_IMAGE_SPEC_LARGE.equals(imageSpec)) {
				uploadResult.setLargeImage(imageInfo);
			} else if (UPLOAD_IMAGE_SPEC_MEDIUM.equals(imageSpec)) {
				uploadResult.setMediumImage(imageInfo);
			} else if (UPLOAD_IMAGE_SPEC_SMALL.equals(imageSpec)) {
				uploadResult.setSmallImage(imageInfo);
			}
		}
		return uploadResult;
	}
	
}
