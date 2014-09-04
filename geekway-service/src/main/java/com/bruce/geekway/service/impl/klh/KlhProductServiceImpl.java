package com.bruce.geekway.service.impl.klh;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bruce.geekway.dao.klh.IKlhProductDao;
import com.bruce.geekway.model.KlhProduct;
import com.bruce.geekway.service.klh.IKlhProductService;

@Service
public class KlhProductServiceImpl implements IKlhProductService{
	
	@Autowired
	private IKlhProductDao klhProductDao;
	
	@Override
	public int save(KlhProduct t) {
		return klhProductDao.save(t);
	}

	@Override
	public int updateById(KlhProduct t) {
		return klhProductDao.updateById(t);
	}

	@Override
	public int deleteById(Integer id) {
		return klhProductDao.deleteById(id);
	}

	@Override
	public KlhProduct loadById(Integer id) {
		return klhProductDao.loadById(id);
	}

	@Override
	public List<KlhProduct> queryAll() {
		return klhProductDao.queryAll();
	}
	

	public IKlhProductDao getKlhProductDao() {
		return klhProductDao;
	}

	public void setKlhProductDao(IKlhProductDao klhProductDao) {
		this.klhProductDao = klhProductDao;
	}

}