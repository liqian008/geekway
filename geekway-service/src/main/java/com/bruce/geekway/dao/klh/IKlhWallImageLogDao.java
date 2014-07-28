package com.bruce.geekway.dao.klh;

import com.bruce.geekway.dao.IBaseDao;
import com.bruce.geekway.model.KlhWallImageLog;

public interface IKlhWallImageLogDao extends IBaseDao<KlhWallImageLog, Integer> {

	public int increaseBrowse(int wallImageId);

	public int increaseLike(int wallImageId);

}
