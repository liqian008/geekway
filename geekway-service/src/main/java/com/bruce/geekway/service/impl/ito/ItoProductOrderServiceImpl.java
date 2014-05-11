package com.bruce.geekway.service.impl.ito;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bruce.geekway.dao.ito.IItoProductOrderDao;
import com.bruce.geekway.model.ItoProductOrder;
import com.bruce.geekway.service.ito.IItoProductOrderService;

@Service
public class ItoProductOrderServiceImpl implements IItoProductOrderService{
	
	@Autowired
	private IItoProductOrderDao itoProductOrderDao;
	
	@Override
	public int save(ItoProductOrder t) {
		return itoProductOrderDao.save(t);
	}

	@Override
	public int updateById(ItoProductOrder t) {
		return itoProductOrderDao.updateById(t);
	}

	@Override
	public int deleteById(Integer id) {
		return itoProductOrderDao.deleteById(id);
	}

	@Override
	public ItoProductOrder loadById(Integer id) {
		return itoProductOrderDao.loadById(id);
	}

	@Override
	public List<ItoProductOrder> queryAll() {
		return itoProductOrderDao.queryAll();
	}
	
	@Override
	public int changeOrderStatus(ItoProductOrder order) {
		return itoProductOrderDao.updateById(order);
	}
	
	

	public IItoProductOrderDao getItoProductOrderDao() {
		return itoProductOrderDao;
	}

	public void setItoProductOrderDao(IItoProductOrderDao itoProductOrderDao) {
		this.itoProductOrderDao = itoProductOrderDao;
	}

	
	
}