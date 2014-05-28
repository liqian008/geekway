package com.bruce.geekway.service.impl.klh;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bruce.geekway.dao.klh.IKlhScoreSettingDao;
import com.bruce.geekway.model.KlhScoreSetting;
import com.bruce.geekway.service.klh.IKlhScoreSettingService;

@Service
public class KlhScoreSettingServiceImpl implements IKlhScoreSettingService{
	
	@Autowired
	private IKlhScoreSettingDao klhScoreSettingDao;
	
	@Override
	public int save(KlhScoreSetting t) {
		return klhScoreSettingDao.save(t);
	}

	@Override
	public int updateById(KlhScoreSetting t) {
		return klhScoreSettingDao.updateById(t);
	}

	@Override
	public int deleteById(Integer id) {
		return klhScoreSettingDao.deleteById(id);
	}

	@Override
	public KlhScoreSetting loadById(Integer id) {
		return klhScoreSettingDao.loadById(id);
	}

	@Override
	public List<KlhScoreSetting> queryAll() {
		return klhScoreSettingDao.queryAll();
	}
	

	public IKlhScoreSettingDao getKlhScoreSettingDao() {
		return klhScoreSettingDao;
	}

	public void setKlhScoreSettingDao(IKlhScoreSettingDao klhScoreSettingDao) {
		this.klhScoreSettingDao = klhScoreSettingDao;
	}
	
}