package com.bruce.geekway.dao.klh;

import java.util.List;

import com.bruce.geekway.dao.IBaseDao;
import com.bruce.geekway.model.KlhWallImageLog;
import com.bruce.geekway.model.KlhWallImageStatBean;

public interface IKlhWallImageLogDao extends IBaseDao<KlhWallImageLog, Integer> {

	public int increaseBrowse(int wallImageId);

	public int increaseLike(int wallImageId);
	
	public List<KlhWallImageStatBean> wallImageStat(int periodType);

}
