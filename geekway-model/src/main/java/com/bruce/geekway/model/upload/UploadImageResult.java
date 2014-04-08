package com.bruce.geekway.model.upload;


/**
 * 
 * @author liqian
 * 
 */
public class UploadImageResult{
	
	private UploadImageInfo originalImage;;
	
	private UploadImageInfo largeImage;
	
	private UploadImageInfo mediumImage;
	
	private UploadImageInfo smallImage;
	
	public UploadImageInfo getLargeImage() {
		return largeImage;
	}

	public void setLargeImage(UploadImageInfo largeImage) {
		this.largeImage = largeImage;
	}

	public UploadImageInfo getMediumImage() {
		return mediumImage;
	}

	public void setMediumImage(UploadImageInfo mediumImage) {
		this.mediumImage = mediumImage;
	}

	public UploadImageInfo getSmallImage() {
		return smallImage;
	}

	public void setSmallImage(UploadImageInfo smallImage) {
		this.smallImage = smallImage;
	}

	public UploadImageInfo getOriginalImage() {
		return originalImage;
	}

	public void setOriginalImage(UploadImageInfo originalImage) {
		this.originalImage = originalImage;
	}
	
	
}
