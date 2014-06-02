package com.bruce.geekway.service.klh;

import com.bruce.geekway.model.KlhUserProfile;
import com.bruce.geekway.service.IBaseService;

public interface IKlhUserProfileService extends IBaseService<KlhUserProfile, Integer> {

	public KlhUserProfile loadByOpenid(String openid);
	
	public int bindProfile(String userOpenId, KlhUserProfile userProfile);
}