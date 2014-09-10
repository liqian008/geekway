package com.bruce.geekway.service.klh;

import com.bruce.foundation.service.IFoundationService;
import com.bruce.geekway.model.KlhUserProfile;
import com.bruce.geekway.model.KlhUserProfileCriteria;

public interface IKlhUserProfileService extends IFoundationService<KlhUserProfile, Integer, KlhUserProfileCriteria> {

	public KlhUserProfile loadByOpenid(String openid);
	
	public int bindProfile(String userOpenId, KlhUserProfile userProfile);
}