package com.bruce.geekway.service;

import java.io.IOException;

import com.bruce.geekway.model.upload.UploadImageResult;

public interface IUploadService{ 
    
	/**
	 * 保存普通文件
	 * @param bytes
	 * @return
	 * @throws IOException 
	 */
	public String uploadFile(byte[] bytes, int userId, String filename) throws IOException;
	
	/**
	 * 保存图片文件，需切割图片
	 * @param bytes
	 * @return 
	 */
	public UploadImageResult uploadImage(byte[] bytes, int userId, String filename) throws IOException;
	

}
