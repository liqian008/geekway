package com.bruce.geekway.service.impl.klh;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bruce.geekway.dao.klh.IKlhVoteOptionDao;
import com.bruce.geekway.model.KlhVoteOption;
import com.bruce.geekway.service.klh.IKlhVoteOptionService;

@Service
public class KlhVoteOptionServiceImpl implements IKlhVoteOptionService{
	
	@Autowired
	private IKlhVoteOptionDao klhVoteOptionDao;
	
	@Override
	public int save(KlhVoteOption t) {
		return klhVoteOptionDao.save(t);
	}

	@Override
	public int updateById(KlhVoteOption t) {
		return klhVoteOptionDao.updateById(t);
	}

	@Override
	public int deleteById(Integer id) {
		return klhVoteOptionDao.deleteById(id);
	}

	@Override
	public KlhVoteOption loadById(Integer id) {
		return klhVoteOptionDao.loadById(id);
	}

	@Override
	public List<KlhVoteOption> queryAll() {
		return klhVoteOptionDao.queryAll();
	}
	

	public IKlhVoteOptionDao getKlhVoteOptionDao() {
		return klhVoteOptionDao;
	}

	public void setKlhVoteOptionDao(IKlhVoteOptionDao klhVoteOptionDao) {
		this.klhVoteOptionDao = klhVoteOptionDao;
	}
	
}