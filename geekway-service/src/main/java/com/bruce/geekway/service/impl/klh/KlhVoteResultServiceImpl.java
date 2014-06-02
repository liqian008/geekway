package com.bruce.geekway.service.impl.klh;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bruce.geekway.dao.klh.IKlhVoteResultDao;
import com.bruce.geekway.model.KlhVoteResult;
import com.bruce.geekway.service.klh.IKlhVoteResultService;

@Service
public class KlhVoteResultServiceImpl implements IKlhVoteResultService{
	
	@Autowired
	private IKlhVoteResultDao klhVoteResultDao;
	
	@Override
	public int save(KlhVoteResult t) {
		return klhVoteResultDao.save(t);
	}

	@Override
	public int updateById(KlhVoteResult t) {
		return klhVoteResultDao.updateById(t);
	}

	@Override
	public int deleteById(Integer id) {
		return klhVoteResultDao.deleteById(id);
	}

	@Override
	public KlhVoteResult loadById(Integer id) {
		return klhVoteResultDao.loadById(id);
	}

	@Override
	public List<KlhVoteResult> queryAll() {
		return klhVoteResultDao.queryAll();
	}
	
	@Override
	public List<KlhVoteResult> queryByVoteId(int voteId) {
		return klhVoteResultDao.queryByVoteId(voteId);
	}

	public IKlhVoteResultDao getKlhVoteResultDao() {
		return klhVoteResultDao;
	}

	public void setKlhVoteResultDao(IKlhVoteResultDao klhVoteResultDao) {
		this.klhVoteResultDao = klhVoteResultDao;
	}
	
}