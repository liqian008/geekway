package com.bruce.geekway.dao.ito;

import com.bruce.geekway.dao.IBaseDao;
import com.bruce.geekway.model.ItoUserProfile;

public interface IItoUserProfileDao extends IBaseDao<ItoUserProfile, Integer> {

	public boolean mobileExists(String mobile);

	public boolean usernameExists(String username);
	
	
}
