package com.bruce.geekway.service.klh;

import java.util.List;

import com.bruce.geekway.model.KlhWallImage;
import com.bruce.geekway.service.IBaseService;

public interface IKlhWallImageService extends IBaseService<KlhWallImage, Integer> {

	public List<KlhWallImage> queryLatestImages(int pageSize);

	public List<KlhWallImage> queryHotestImages(int pageSize);
	
	public int increaseLike(int wallImageId);
}