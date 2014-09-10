package com.bruce.geekway.service.impl.ito;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bruce.geekway.dao.ito.IItoProductDao;
import com.bruce.geekway.model.ItoProduct;
import com.bruce.geekway.model.ItoProductCriteria;
import com.bruce.geekway.service.ito.IItoProductService;

@Service
public class ItoProductServiceImpl implements IItoProductService{
	
	@Autowired
	private IItoProductDao itoProductDao;
	
	@Override
	public int save(ItoProduct t) {
		return itoProductDao.save(t);
	}

	@Override
	public int updateById(ItoProduct t) {
		return itoProductDao.updateById(t);
	}

	@Override
	public int deleteById(Integer id) {
		return itoProductDao.deleteById(id);
	}

	@Override
	public ItoProduct loadById(Integer id) {
		return itoProductDao.loadById(id);
	}

	@Override
	public List<ItoProduct> queryAll() {
		return itoProductDao.queryAll();
	}
	
	@Override
	public List<ItoProduct> queryAvailableList() {
		return itoProductDao.queryAvailableList();
	}
	

	public IItoProductDao getItoProductDao() {
		return itoProductDao;
	}

	public void setItoProductDao(IItoProductDao itoProductDao) {
		this.itoProductDao = itoProductDao;
	}

	@Override
	public int updateByCriteria(ItoProduct t, ItoProductCriteria criteria) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int deleteByCriteria(ItoProductCriteria criteria) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public List<ItoProduct> queryAll(String orderByClause) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<ItoProduct> queryByCriteria(ItoProductCriteria criteria) {
		// TODO Auto-generated method stub
		return null;
	}

	
	
}