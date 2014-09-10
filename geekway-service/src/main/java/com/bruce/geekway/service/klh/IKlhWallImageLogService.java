package com.bruce.geekway.service.klh;

import java.util.List;

import com.bruce.foundation.service.IFoundationService;
import com.bruce.geekway.model.KlhWallImageLog;
import com.bruce.geekway.model.KlhWallImageLogCriteria;
import com.bruce.geekway.model.KlhWallImageStatBean;

public interface IKlhWallImageLogService extends IFoundationService<KlhWallImageLog, Integer, KlhWallImageLogCriteria> {


	public int increaseBrowse(int wallImageId);
	
	public int increaseLike(int wallImageId);
	
	public List<KlhWallImageStatBean> wallImageStat(int periodType);
}