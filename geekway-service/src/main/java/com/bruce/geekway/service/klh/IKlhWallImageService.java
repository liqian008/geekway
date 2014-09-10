package com.bruce.geekway.service.klh;

import com.bruce.foundation.service.IFoundationService;
import com.bruce.geekway.data.PagingData;
import com.bruce.geekway.model.KlhWallImage;
import com.bruce.geekway.model.KlhWallImageCriteria;

public interface IKlhWallImageService extends IFoundationService<KlhWallImage, Integer, KlhWallImageCriteria> {

//	public List<KlhWallImage> queryLatestImages(int pageSize);
//
//	public List<KlhWallImage> queryHotestImages(int pageSize);
	
	public PagingData<KlhWallImage> pagingLatestImages(int pageNo, int pageSize);

	public PagingData<KlhWallImage> pagingHotestImages(int pageNo, int pageSize);

	public int increaseLike(int wallImageId, String userOpenId);
	
	public int increaseBrowse(int wallImageId, String userOpenId);
	
	/**
	 * 发布照片
	 * @param wallImage
	 * @param userOpenId
	 * @return
	 */ 
	int postWallImage(KlhWallImage wallImage, String userOpenId);
	
}