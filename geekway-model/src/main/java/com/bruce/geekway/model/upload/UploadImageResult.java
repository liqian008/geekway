package com.bruce.geekway.model.upload;

import com.bruce.geekway.model.wx.json.response.WxMediaUploadResult;


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
	
	/*上传到微信服务器返回的对象*/
	private WxMediaUploadResult wxMediaResult;
	
	
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

	public WxMediaUploadResult getWxMediaResult() {
		return wxMediaResult;
	}

	public void setWxMediaResult(WxMediaUploadResult wxMediaResult) {
		this.wxMediaResult = wxMediaResult;
	}
	
}
