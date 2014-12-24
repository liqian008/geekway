package com.bruce.geekway.service.impl.klh;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bruce.geekway.dao.klh.IKlhProductOrderDao;
import com.bruce.geekway.model.KlhProductOrder;
import com.bruce.geekway.model.KlhProductOrderCriteria;
import com.bruce.geekway.service.klh.IKlhProductOrderService;

@Service
public class KlhProductOrderServiceImpl implements IKlhProductOrderService{
	
	@Autowired
	private IKlhProductOrderDao klhProductOrderDao;
	
	@Override
	public int save(KlhProductOrder t) {
		return klhProductOrderDao.save(t);
	}

	@Override
	public int updateById(KlhProductOrder t) {
		return klhProductOrderDao.updateById(t);
	}

	@Override
	public int deleteById(Integer id) {
		return klhProductOrderDao.deleteById(id);
	}

	@Override
	public KlhProductOrder loadById(Integer id) {
		return klhProductOrderDao.loadById(id);
	}

	@Override
	public List<KlhProductOrder> queryAll() {
		return klhProductOrderDao.queryAll();
	}
	

	public IKlhProductOrderDao getKlhProductOrderDao() {
		return klhProductOrderDao;
	}

	public void setKlhProductOrderDao(IKlhProductOrderDao klhProductOrderDao) {
		this.klhProductOrderDao = klhProductOrderDao;
	}

	@Override
	public int updateByCriteria(KlhProductOrder t, KlhProductOrderCriteria criteria) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int deleteByCriteria(KlhProductOrderCriteria criteria) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public List<KlhProductOrder> queryAll(String orderByClause) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<KlhProductOrder> queryByCriteria(KlhProductOrderCriteria criteria) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public int countByCriteria(KlhProductOrderCriteria criteria) {
		// TODO Auto-generated method stub
		return 0;
	}

	

}