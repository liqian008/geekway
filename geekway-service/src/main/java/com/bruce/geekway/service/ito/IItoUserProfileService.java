package com.bruce.geekway.service.ito;

import com.bruce.geekway.model.ItoUserProfile;
import com.bruce.geekway.service.IBaseService;

public interface IItoUserProfileService extends IBaseService<ItoUserProfile, Integer> {
	
	
	public boolean usernameExists(String username);
	
	public boolean mobileExists(String mobile);
	
//	public boolean emailExists(String email);
	
}