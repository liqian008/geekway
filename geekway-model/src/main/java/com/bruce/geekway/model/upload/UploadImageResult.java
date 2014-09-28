package com.bruce.geekway.model.upload;

import java.util.ArrayList;
import java.util.List;

import com.bruce.geekway.model.wx.json.response.WxMediaUploadResult;

/**
 * 
 * @author liqian
 * 
 */
public class UploadImageResult{
	
	/*上传到微信服务器后返回的对象*/
	private WxMediaUploadResult wxMediaResult;
	
	private List<UploadImageInfo> uploadImageList = new ArrayList<UploadImageInfo>();

	public void add(UploadImageInfo imageInfo){
		uploadImageList.add(imageInfo);
	}
	
	
	public List<UploadImageInfo> getUploadImageList() {
		return uploadImageList;
	}

	public void setUploadImageList(List<UploadImageInfo> uploadImageList) {
		this.uploadImageList = uploadImageList;
	}

	public WxMediaUploadResult getWxMediaResult() {
		return wxMediaResult;
	}

	public void setWxMediaResult(WxMediaUploadResult wxMediaResult) {
		this.wxMediaResult = wxMediaResult;
	}
	
	
	
//	private UploadImageInfo originalImage;;
//	
//	private UploadImageInfo largeImage;
//	
//	private UploadImageInfo mediumImage;
//	
//	private UploadImageInfo smallImage;
//	

//	
//	
//	public UploadImageInfo getLargeImage() {
//		return largeImage;
//	}
//
//	public void setLargeImage(UploadImageInfo largeImage) {
//		this.largeImage = largeImage;
//	}
//
//	public UploadImageInfo getMediumImage() {
//		return mediumImage;
//	}
//
//	public void setMediumImage(UploadImageInfo mediumImage) {
//		this.mediumImage = mediumImage;
//	}
//
//	public UploadImageInfo getSmallImage() {
//		return smallImage;
//	}
//
//	public void setSmallImage(UploadImageInfo smallImage) {
//		this.smallImage = smallImage;
//	}
//
//	public UploadImageInfo getOriginalImage() {
//		return originalImage;
//	}
//
//	public void setOriginalImage(UploadImageInfo originalImage) {
//		this.originalImage = originalImage;
//	}
//
//	public WxMediaUploadResult getWxMediaResult() {
//		return wxMediaResult;
//	}
//
//	public void setWxMediaResult(WxMediaUploadResult wxMediaResult) {
//		this.wxMediaResult = wxMediaResult;
//	}
	
}
