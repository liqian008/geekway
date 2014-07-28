package com.bruce.geekway.service.klh;

import com.bruce.geekway.model.KlhWallImageLog;
import com.bruce.geekway.service.IBaseService;

public interface IKlhWallImageLogService extends IBaseService<KlhWallImageLog, Integer> {


	public int increaseBrowse(int wallImageId);
	
	public int increaseLike(int wallImageId);
}