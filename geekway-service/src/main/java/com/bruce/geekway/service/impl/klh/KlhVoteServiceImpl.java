package com.bruce.geekway.service.impl.klh;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bruce.geekway.dao.klh.IKlhVoteDao;
import com.bruce.geekway.model.KlhVote;
import com.bruce.geekway.model.KlhVoteCriteria;
import com.bruce.geekway.service.klh.IKlhVoteService;

@Service
public class KlhVoteServiceImpl implements IKlhVoteService{
	
	@Autowired
	private IKlhVoteDao klhVoteDao;
	
	@Override
	public int save(KlhVote t) {
		return klhVoteDao.save(t);
	}

	@Override
	public int updateById(KlhVote t) {
		return klhVoteDao.updateById(t);
	}

	@Override
	public int deleteById(Integer id) {
		return klhVoteDao.deleteById(id);
	}

	@Override
	public KlhVote loadById(Integer id) {
		return klhVoteDao.loadById(id);
	}

	@Override
	public List<KlhVote> queryAll() {
		return klhVoteDao.queryAll();
	}
	

	public IKlhVoteDao getKlhVoteDao() {
		return klhVoteDao;
	}

	public void setKlhVoteDao(IKlhVoteDao klhVoteDao) {
		this.klhVoteDao = klhVoteDao;
	}

	@Override
	public int updateByCriteria(KlhVote t, KlhVoteCriteria criteria) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int deleteByCriteria(KlhVoteCriteria criteria) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public List<KlhVote> queryAll(String orderByClause) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<KlhVote> queryByCriteria(KlhVoteCriteria criteria) {
		// TODO Auto-generated method stub
		return null;
	}
	
}