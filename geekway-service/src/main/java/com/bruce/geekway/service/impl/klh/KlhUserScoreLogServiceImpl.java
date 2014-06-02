package com.bruce.geekway.service.impl.klh;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bruce.geekway.dao.klh.IKlhUserScoreLogDao;
import com.bruce.geekway.model.KlhUserScoreLog;
import com.bruce.geekway.service.klh.IKlhUserScoreLogService;

@Service
public class KlhUserScoreLogServiceImpl implements IKlhUserScoreLogService{
	
	@Autowired
	private IKlhUserScoreLogDao klhUserScoreLogDao;
	
	@Override
	public int save(KlhUserScoreLog t) {
		return klhUserScoreLogDao.save(t);
	}

	@Override
	public int updateById(KlhUserScoreLog t) {
		return klhUserScoreLogDao.updateById(t);
	}

	@Override
	public int deleteById(Integer id) {
		return klhUserScoreLogDao.deleteById(id);
	}

	@Override
	public KlhUserScoreLog loadById(Integer id) {
		return klhUserScoreLogDao.loadById(id);
	}

	@Override
	public List<KlhUserScoreLog> queryAll() {
		return klhUserScoreLogDao.queryAll();
	}
	
	@Override
	public List<KlhUserScoreLog> queryByUserOpenId(String userOpenId) { 
		return klhUserScoreLogDao.queryByUserOpenId(userOpenId);
	}

	public IKlhUserScoreLogDao getKlhUserScoreLogDao() {
		return klhUserScoreLogDao;
	}

	public void setKlhUserScoreLogDao(IKlhUserScoreLogDao klhUserScoreLogDao) {
		this.klhUserScoreLogDao = klhUserScoreLogDao;
	}
	
}