package com.bruce.geekway.service.impl.klh;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bruce.geekway.dao.klh.IKlhSettingDao;
import com.bruce.geekway.model.KlhSetting;
import com.bruce.geekway.model.KlhSettingCriteria;
import com.bruce.geekway.service.klh.IKlhSettingService;

@Service
public class KlhSettingServiceImpl implements IKlhSettingService{
	
	@Autowired
	private IKlhSettingDao klhSettingDao;
	
	/*系统的setting对象，默认x分钟更新一次*/
	private KlhSetting klhSetting;
	
	private long lastLoadTime = 0;
	
	
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
	
	@Override
	public synchronized KlhSetting loadKlhSetting() {
    	long currentTime = System.currentTimeMillis();
    	if(klhSetting==null||currentTime-lastLoadTime>1000*60*60){//每小时刷新一次默认值
    		klhSetting = klhSettingDao.loadById(1);
    		lastLoadTime = currentTime;
    	}
    	return klhSetting;
	}
	

	public IKlhSettingDao getKlhSettingDao() {
		return klhSettingDao;
	}

	public void setKlhSettingDao(IKlhSettingDao klhSettingDao) {
		this.klhSettingDao = klhSettingDao;
	}

	@Override
	public int updateByCriteria(KlhSetting t, KlhSettingCriteria criteria) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int deleteByCriteria(KlhSettingCriteria criteria) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public List<KlhSetting> queryAll(String orderByClause) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<KlhSetting> queryByCriteria(KlhSettingCriteria criteria) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public int countByCriteria(KlhSettingCriteria criteria) {
		// TODO Auto-generated method stub
		return 0;
	}
	
}