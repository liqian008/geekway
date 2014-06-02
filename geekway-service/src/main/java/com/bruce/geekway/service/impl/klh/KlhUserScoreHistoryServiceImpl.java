package com.bruce.geekway.service.impl.klh;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bruce.geekway.dao.klh.IKlhUserScoreHistoryDao;
import com.bruce.geekway.model.KlhUserScoreHistory;
import com.bruce.geekway.service.klh.IKlhUserScoreHistoryService;

@Service
public class KlhUserScoreHistoryServiceImpl implements IKlhUserScoreHistoryService{
	
	@Autowired
	private IKlhUserScoreHistoryDao klhUserScoreHistoryDao;
	
	@Override
	public int save(KlhUserScoreHistory t) {
		return klhUserScoreHistoryDao.save(t);
	}

	@Override
	public int updateById(KlhUserScoreHistory t) {
		return klhUserScoreHistoryDao.updateById(t);
	}

	@Override
	public int deleteById(Integer id) {
		return klhUserScoreHistoryDao.deleteById(id);
	}

	@Override
	public KlhUserScoreHistory loadById(Integer id) {
		return klhUserScoreHistoryDao.loadById(id);
	}

	@Override
	public List<KlhUserScoreHistory> queryAll() {
		return klhUserScoreHistoryDao.queryAll();
	}
	
	@Override
	public List<KlhUserScoreHistory> queryByUserId(int userId) { 
		return klhUserScoreHistoryDao.queryByUserId(userId);
	}

	public IKlhUserScoreHistoryDao getKlhUserScoreHistoryDao() {
		return klhUserScoreHistoryDao;
	}

	public void setKlhUserScoreHistoryDao(IKlhUserScoreHistoryDao klhUserScoreHistoryDao) {
		this.klhUserScoreHistoryDao = klhUserScoreHistoryDao;
	}
	
}