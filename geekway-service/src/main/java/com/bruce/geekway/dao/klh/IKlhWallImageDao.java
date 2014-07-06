package com.bruce.geekway.dao.klh;

import java.util.List;

import com.bruce.geekway.dao.IBaseDao;
import com.bruce.geekway.data.PagingData;
import com.bruce.geekway.model.KlhWallImage;

public interface IKlhWallImageDao extends IBaseDao<KlhWallImage, Integer> {

//	public List<KlhWallImage> queryLatestImages(int pageSize);
//
//	public List<KlhWallImage> queryHotestImages(int pageSize);

	public PagingData<KlhWallImage> pagingLatestImages(int pageNo, int pageSize);

	public PagingData<KlhWallImage> pagingHotestImages(int pageNo, int pageSize);

//	public int increaseLike(int wallImageId);

}
