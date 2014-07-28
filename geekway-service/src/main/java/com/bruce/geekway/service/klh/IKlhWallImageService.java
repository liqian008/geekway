package com.bruce.geekway.service.klh;

import com.bruce.geekway.data.PagingData;
import com.bruce.geekway.model.KlhWallImage;
import com.bruce.geekway.service.IBaseService;

public interface IKlhWallImageService extends IBaseService<KlhWallImage, Integer> {

//	public List<KlhWallImage> queryLatestImages(int pageSize);
//
//	public List<KlhWallImage> queryHotestImages(int pageSize);
	
	public PagingData<KlhWallImage> pagingLatestImages(int pageNo, int pageSize);

	public PagingData<KlhWallImage> pagingHotestImages(int pageNo, int pageSize);

	public int increaseLike(int wallImageId);
	
	public int increaseBrowse(int wallImageId);
}