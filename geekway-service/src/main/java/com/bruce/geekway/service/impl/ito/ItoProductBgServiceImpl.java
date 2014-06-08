package com.bruce.geekway.service.impl.ito;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bruce.geekway.dao.ito.IItoProductBgDao;
import com.bruce.geekway.model.ItoProductBg;
import com.bruce.geekway.service.ito.IItoProductBgService;

@Service
public class ItoProductBgServiceImpl implements IItoProductBgService{
	
	@Autowired
	private IItoProductBgDao itoProductBgDao;
	
	@Override
	public int save(ItoProductBg t) {
		return itoProductBgDao.save(t);
	}

	@Override
	public int updateById(ItoProductBg t) {
		return itoProductBgDao.updateById(t);
	}

	@Override
	public int deleteById(Integer id) {
		return itoProductBgDao.deleteById(id);
	}

	@Override
	public ItoProductBg loadById(Integer id) {
		return itoProductBgDao.loadById(id);
	}

	@Override
	public List<ItoProductBg> queryAll() {
		return itoProductBgDao.queryAll();
	}
	
	public IItoProductBgDao getItoProductBgDao() {
		return itoProductBgDao;
	}

	public void setItoProductBgDao(IItoProductBgDao itoProductBgDao) {
		this.itoProductBgDao = itoProductBgDao;
	}
	
}