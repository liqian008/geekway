package com.bruce.geekway.service.impl.klh;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bruce.geekway.dao.klh.IKlhUserDao;
import com.bruce.geekway.model.KlhUser;
import com.bruce.geekway.service.klh.IKlhUserService;

@Service
public class KlhUserServiceImpl implements IKlhUserService{
	
	@Autowired
	private IKlhUserDao klhUserDao;
	
	@Override
	public int save(KlhUser t) {
		return klhUserDao.save(t);
	}

	@Override
	public int updateById(KlhUser t) {
		return klhUserDao.updateById(t);
	}

	@Override
	public int deleteById(Integer id) {
		return klhUserDao.deleteById(id);
	}

	@Override
	public KlhUser loadById(Integer id) {
		return klhUserDao.loadById(id);
	}

	@Override
	public List<KlhUser> queryAll() {
		return klhUserDao.queryAll();
	}
	

	public IKlhUserDao getKlhUserDao() {
		return klhUserDao;
	}

	public void setKlhUserDao(IKlhUserDao klhUserDao) {
		this.klhUserDao = klhUserDao;
	}
	
}