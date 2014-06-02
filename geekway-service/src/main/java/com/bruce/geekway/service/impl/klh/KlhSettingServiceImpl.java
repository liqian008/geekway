package com.bruce.geekway.service.impl.klh;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bruce.geekway.dao.klh.IKlhSettingDao;
import com.bruce.geekway.model.KlhSetting;
import com.bruce.geekway.service.klh.IKlhSettingService;

@Service
public class KlhSettingServiceImpl implements IKlhSettingService{
	
	@Autowired
	private IKlhSettingDao klhSettingDao;
	
	@Override
	public int save(KlhSetting t) {
		return klhSettingDao.save(t);
	}

	@Override
	public int updateById(KlhSetting t) {
		return klhSettingDao.updateById(t);
	}

	@Override
	public int deleteById(Integer id) {
		return klhSettingDao.deleteById(id);
	}

	@Override
	public KlhSetting loadById(Integer id) {
		return klhSettingDao.loadById(id);
	}

	@Override
	public List<KlhSetting> queryAll() {
		return klhSettingDao.queryAll();
	}
	

	public IKlhSettingDao getKlhSettingDao() {
		return klhSettingDao;
	}

	public void setKlhSettingDao(IKlhSettingDao klhSettingDao) {
		this.klhSettingDao = klhSettingDao;
	}
	
}