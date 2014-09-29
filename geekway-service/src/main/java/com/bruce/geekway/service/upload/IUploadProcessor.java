package com.bruce.geekway.service.upload;

import java.io.File;

import com.bruce.geekway.model.upload.UploadImageInfo;
import com.bruce.geekway.model.upload.UploadImageResult;

public interface IUploadProcessor {
	
	/**
	 * process根据原始文件进行处理，返回url
	 * @param destFile
	 * @param fileDir 文件夹的相对路径
	 * @return
	 * @throws Exception 
	 */
	public String saveFile(File destFile, String fileDir) throws Exception;

	/**
	 * 
	 * @param originalImageFile
	 * @param imageDir 图片文件夹的相对路径
	 * @param imageSpecs
	 * @return
	 * @throws Exception
	 */
	public UploadImageResult saveImage(File originalImageFile, String imageDir, UploadImageInfo[] imageSpecs) throws Exception; 

}
