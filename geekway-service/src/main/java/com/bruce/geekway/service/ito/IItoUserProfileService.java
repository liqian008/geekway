package com.bruce.geekway.service.ito;

import com.bruce.foundation.service.IFoundationService;
import com.bruce.geekway.model.ItoUserProfile;
import com.bruce.geekway.model.ItoUserProfileCriteria;

public interface IItoUserProfileService extends IFoundationService<ItoUserProfile, Integer, ItoUserProfileCriteria> {
	
	
	public boolean usernameExists(String username);
	
	public boolean mobileExists(String mobile);
	
//	public boolean emailExists(String email);
	
}