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
	private IItoUserProfileDao klhUserProfileDao;
	
	@Override
	public int save(ItoUserProfile t) {
		return klhUserProfileDao.save(t);
	}

	@Override
	public int updateById(ItoUserProfile t) {
		return klhUserProfileDao.updateById(t);
	}

	@Override
	public int deleteById(Integer id) {
		return klhUserProfileDao.deleteById(id);
	}

	@Override
	public ItoUserProfile loadById(Integer id) {
		return klhUserProfileDao.loadById(id);
	}

	@Override
	public List<ItoUserProfile> queryAll() {
		return klhUserProfileDao.queryAll();
	}
	

	public IItoUserProfileDao getItoUserProfileDao() {
		return klhUserProfileDao;
	}

	public void setItoUserProfileDao(IItoUserProfileDao klhUserProfileDao) {
		this.klhUserProfileDao = klhUserProfileDao;
	}

	
}
