package com.bruce.geekway.service.impl.klh;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bruce.geekway.dao.klh.IKlhVoteOptionDao;
import com.bruce.geekway.model.KlhVoteOption;
import com.bruce.geekway.model.KlhVoteOptionCriteria;
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
	
	@Override
	public List<KlhVoteOption> queryByVoteId(int voteId) {
		return klhVoteOptionDao.queryByVoteId(voteId);
	}

	public IKlhVoteOptionDao getKlhVoteOptionDao() {
		return klhVoteOptionDao;
	}

	public void setKlhVoteOptionDao(IKlhVoteOptionDao klhVoteOptionDao) {
		this.klhVoteOptionDao = klhVoteOptionDao;
	}

	@Override
	public int updateByCriteria(KlhVoteOption t, KlhVoteOptionCriteria criteria) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int deleteByCriteria(KlhVoteOptionCriteria criteria) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public List<KlhVoteOption> queryAll(String orderByClause) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<KlhVoteOption> queryByCriteria(KlhVoteOptionCriteria criteria) {
		// TODO Auto-generated method stub
		return null;
	}
	
}