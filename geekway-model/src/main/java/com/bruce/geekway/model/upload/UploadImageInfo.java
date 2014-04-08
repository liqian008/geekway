package com.bruce.geekway.model.upload;

/**
 * 
 * @author liqian
 * 
 */
public class UploadImageInfo extends UploadFileInfo{
	
	
	/*图片宽度*/
	private int width;
	/*图片高度*/
	private int height;
	/* 图片规格，original,large,medium,tiny */
	private String imageSpec;
	
	public UploadImageInfo(){
		super();
	}
	
	public UploadImageInfo(String fileName, short fileType, String imageSpec, String url, long length){
		super(fileName, fileType, url, length);
		this.imageSpec = imageSpec;
	}
	
	public UploadImageInfo(String fileName, short fileType, String imageSpec, String url, long length, int width, int height){
		super(fileName, fileType, url, length);
		this.width=width;
		this.imageSpec=imageSpec;
		this.height=height;
	}

	public int getWidth() {
		return width;
	}

	public void setWidth(int width) {
		this.width = width;
	}

	public int getHeight() {
		return height;
	}

	public void setHeight(int height) {
		this.height = height;
	}

	public String getImageSpec() {
		return imageSpec;
	}

	public void setImageSpec(String imageSpec) {
		this.imageSpec = imageSpec;
	}
	

}
