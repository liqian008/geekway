package com.bruce.geekway.dao.klh;

import com.bruce.geekway.dao.IBaseDao;
import com.bruce.geekway.model.KlhUserProfile;

public interface IKlhUserProfileDao extends IBaseDao<KlhUserProfile, Integer> {

	
	public KlhUserProfile loadByOpenid(String openid);
	
	
}
