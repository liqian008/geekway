package com.bruce.geekway.service.impl.klh;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bruce.geekway.dao.klh.IKlhBindSettingDao;
import com.bruce.geekway.model.KlhBindSetting;
import com.bruce.geekway.service.klh.IKlhBindSettingService;

@Service
public class KlhBindSettingServiceImpl implements IKlhBindSettingService{
	
	@Autowired
	private IKlhBindSettingDao klhBindSettingDao;
	
	@Override
	public int save(KlhBindSetting t) {
		return klhBindSettingDao.save(t);
	}

	@Override
	public int updateById(KlhBindSetting t) {
		return klhBindSettingDao.updateById(t);
	}

	@Override
	public int deleteById(Integer id) {
		return klhBindSettingDao.deleteById(id);
	}

	@Override
	public KlhBindSetting loadById(Integer id) {
		return klhBindSettingDao.loadById(id);
	}

	@Override
	public List<KlhBindSetting> queryAll() {
		return klhBindSettingDao.queryAll();
	}
	

	public IKlhBindSettingDao getKlhBindSettingDao() {
		return klhBindSettingDao;
	}

	public void setKlhBindSettingDao(IKlhBindSettingDao klhBindSettingDao) {
		this.klhBindSettingDao = klhBindSettingDao;
	}
	
}