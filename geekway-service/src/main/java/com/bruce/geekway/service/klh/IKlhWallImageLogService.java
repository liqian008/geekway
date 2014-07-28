package com.bruce.geekway.service.klh;

import java.util.List;

import com.bruce.geekway.model.KlhWallImageLog;
import com.bruce.geekway.model.KlhWallImageStatBean;
import com.bruce.geekway.service.IBaseService;

public interface IKlhWallImageLogService extends IBaseService<KlhWallImageLog, Integer> {


	public int increaseBrowse(int wallImageId);
	
	public int increaseLike(int wallImageId);
	
	public List<KlhWallImageStatBean> wallImageStat(int periodType);
}