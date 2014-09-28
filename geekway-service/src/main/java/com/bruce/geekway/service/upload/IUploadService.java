package com.bruce.geekway.service.upload;

import java.io.IOException;

import com.bruce.geekway.model.upload.UploadImageInfo;
import com.bruce.geekway.model.upload.UploadImageResult;

public interface IUploadService{
	
	/*常用的图片规格*/
	public final static UploadImageInfo IMAGE_SPEC_LARGE = new UploadImageInfo("large", 1000);
	public final static UploadImageInfo IMAGE_SPEC_MEDIUM = new UploadImageInfo("medium", 400);
	public final static UploadImageInfo IMAGE_SPEC_SMALL = new UploadImageInfo("small", 200);
	/*常用的头像规格*/
	public final static UploadImageInfo AVATAR_SPEC_LARGE = new UploadImageInfo("large", 200);
	public final static UploadImageInfo AVATAR_SPEC_MEDIUM = new UploadImageInfo("medium", 100);
	public final static UploadImageInfo AVATAR_SPEC_SMALL = new UploadImageInfo("small", 50);
    
	/**
	 * 保存普通文件
	 * @param bytes
	 * @return
	 * @throws IOException 
	 */
	public String uploadFile(byte[] bytes, String userId, String filename) throws IOException;
	
	/**
	 * 保存图片文件，需切割图片
	 * @param bytes
	 * @return 
	 */
	public UploadImageResult uploadImage(byte[] bytes, String userId, String filename, UploadImageInfo... imageSpecs) throws IOException;
	
}
