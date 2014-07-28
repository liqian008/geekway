package com.bruce.geekway.service.impl.klh;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bruce.geekway.dao.klh.IKlhWallImageLogDao;
import com.bruce.geekway.model.KlhWallImageLog;
import com.bruce.geekway.model.KlhWallImageStatBean;
import com.bruce.geekway.service.klh.IKlhWallImageLogService;

@Service
public class KlhWallImageLogServiceImpl implements IKlhWallImageLogService{
	
	@Autowired
	private IKlhWallImageLogDao klhWallImageLogDao;
	
	@Override
	public int save(KlhWallImageLog t) {
		return klhWallImageLogDao.save(t);
	}

	@Override
	public int updateById(KlhWallImageLog t) {
		return klhWallImageLogDao.updateById(t);
	}

	@Override
	public int deleteById(Integer id) {
		return klhWallImageLogDao.deleteById(id);
	}

	@Override
	public KlhWallImageLog loadById(Integer id) {
		return klhWallImageLogDao.loadById(id);
	}

	@Override
	public List<KlhWallImageLog> queryAll() {
		return klhWallImageLogDao.queryAll();
	}
	
	@Override
	public int increaseLike(int wallImageId) {
		return klhWallImageLogDao.increaseLike(wallImageId);
	}
	
	@Override
	public int increaseBrowse(int wallImageId) {
		return klhWallImageLogDao.increaseBrowse(wallImageId);
	}
	
	@Override
	public List<KlhWallImageStatBean> wallImageStat(int periodType){
		return klhWallImageLogDao.wallImageStat(periodType);
	}

	public IKlhWallImageLogDao getKlhWallImageLogDao() {
		return klhWallImageLogDao;
	}

	public void setKlhWallImageLogDao(IKlhWallImageLogDao klhWallImageLogDao) {
		this.klhWallImageLogDao = klhWallImageLogDao;
	}

}