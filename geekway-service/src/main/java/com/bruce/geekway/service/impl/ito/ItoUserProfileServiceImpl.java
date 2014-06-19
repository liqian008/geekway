package com.bruce.geekway.service.impl.ito;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bruce.geekway.dao.ito.IItoUserProfileDao;
import com.bruce.geekway.model.ItoUserProfile;
import com.bruce.geekway.service.ito.IItoUserProfileService;
import com.bruce.geekway.utils.DateUtil;

@Service
public class ItoUserProfileServiceImpl implements IItoUserProfileService{
	
	@Autowired
	private IItoUserProfileDao itoUserProfileDao;
	
	@Override
	public int save(ItoUserProfile t) {
		return itoUserProfileDao.save(t);
	}

	@Override
	public int updateById(ItoUserProfile t) {
		return itoUserProfileDao.updateById(t);
	}

	@Override
	public int deleteById(Integer id) {
		return itoUserProfileDao.deleteById(id);
	}

	@Override
	public ItoUserProfile loadById(Integer id) {
		return itoUserProfileDao.loadById(id);
	}

	@Override
	public List<ItoUserProfile> queryAll() {
		return itoUserProfileDao.queryAll();
	}
	
	@Override
	public boolean usernameExists(String username) {
		return itoUserProfileDao.usernameExists(username);
	}

	@Override
	public boolean mobileExists(String mobile) {
		return itoUserProfileDao.mobileExists(mobile); 
	}

	public IItoUserProfileDao getItoUserProfileDao() {
		return itoUserProfileDao;
	}

	public void setItoUserProfileDao(IItoUserProfileDao itoUserProfileDao) {
		this.itoUserProfileDao = itoUserProfileDao;
	}

	
}
