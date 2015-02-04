package com.bruce.geekway.model.upload;

import java.util.HashMap;
import java.util.Map;

/**
 * 
 * @author liqian
 * 
 */
public class UploadImageResult {

	private Map<String, UploadImageInfo> uploadImageMap = new HashMap<String, UploadImageInfo>();

	public void put(UploadImageInfo imageInfo) {
		if (imageInfo != null && imageInfo.getImageSpec() != null) {
			uploadImageMap.put(imageInfo.getImageSpec(), imageInfo);
		}
	}

	public Map<String, UploadImageInfo> getUploadImageMap() {
		return uploadImageMap;
	}

	public void setUploadImageMap(Map<String, UploadImageInfo> uploadImageMap) {
		this.uploadImageMap = uploadImageMap;
	}

}
