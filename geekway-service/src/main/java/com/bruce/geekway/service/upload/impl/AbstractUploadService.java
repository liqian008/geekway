package com.bruce.geekway.service.upload.impl;

import java.io.File;
import java.io.IOException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.bruce.geekway.constants.ConstConfig;
import com.bruce.geekway.model.upload.UploadImageInfo;
import com.bruce.geekway.model.upload.UploadImageResult;
import com.bruce.geekway.service.upload.IUploadService;
import com.bruce.geekway.utils.UploadUtil;


public abstract class AbstractUploadService implements IUploadService {
	
	private static final Logger logger = LoggerFactory.getLogger(UploadImageInfo.class);
	
//	@Autowired
//	private IUploadProcessor uploadProcessor;
	
//	/* 普通文件类型 */
//	public static final short UPLOAD_FILE_TYPE_NORMAL = 0;
//	/* 图片文件类型 */
//	public static final short UPLOAD_FILE_TYPE_IMAGE = 1;
	
//	// 文件存储的绝对路径
//	public static final String BASE_PATH = UploadUtil.getBasePath();
//	// 文件的baseUrl
//	public static final String BASE_URL = UploadUtil.getBaseUrl();
	

	/**
	 * 保存文件
	 */
	@Override
	public String uploadFile(byte[] data, String userId, String fileName) throws Exception {
		//先保存本地文件
		String newFileName = UploadUtil.getFileName(userId, fileName);
		String filePath = UploadUtil.getFilePath();//相对路径
		String absoultDirPath = ConstConfig.UPLOAD_PATH_BASE + filePath;//完整路径
		File destFile =  UploadUtil.saveFile(data, absoultDirPath, newFileName);
		
		return saveFile(destFile, filePath);
	}

	
	/**
	 * 上传图片，需按尺寸切割
	 * 
	 * @throws IOException
	 */
	@Override
	public UploadImageResult uploadImage(byte[] data, String userId, String filename, UploadImageInfo... imageSpecs) throws Exception {
		long time = System.currentTimeMillis();
		// 获取图片存储的绝对、相对路径及文件名
		String newImageName = UploadUtil.getFileNameWithPlaceHolder(userId, filename, null, time);
		String imageDirPath = UploadUtil.getImagePath(time);//相对路径
		String absoultImageDirPath = ConstConfig.UPLOAD_PATH_BASE + imageDirPath;//完整路径
		logger.debug("upload absoultImageDirPath: "+absoultImageDirPath);
		logger.debug("upload image rename to: "+newImageName);

		String originalImageSpec = "original";
		File originalImageFile = UploadUtil.saveFile(data, absoultImageDirPath + UploadUtil.FILE_SEPARTOR + originalImageSpec,  newImageName);
		
		return saveImage(originalImageFile, imageDirPath, imageSpecs);
	}

	
	protected abstract String saveFile(File destFile, String filePath) throws Exception;

	protected abstract UploadImageResult saveImage(File originalImageFile, String imageDirPath, UploadImageInfo[] imageSpecs) throws Exception;

//	public IUploadProcessor getUploadProcessor() {
//		return uploadProcessor;
//	}
//
//	public void setUploadProcessor(IUploadProcessor uploadProcessor) {
//		this.uploadProcessor = uploadProcessor;
//	}
	
}