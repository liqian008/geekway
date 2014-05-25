package com.bruce.geekway.service.impl.ito;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bruce.geekway.dao.ito.IItoSystemStatusDao;
import com.bruce.geekway.model.ItoSystemStatus;
import com.bruce.geekway.service.ito.IItoSystemStatusService;

@Service
public class ItoSystemStatusServiceImpl implements IItoSystemStatusService{
	
	@Autowired
	private IItoSystemStatusDao itoSystemStatusDao;
	
	@Override
	public int save(ItoSystemStatus t) {
		return itoSystemStatusDao.save(t);
	}

	@Override
	public int updateById(ItoSystemStatus t) {
		return itoSystemStatusDao.updateById(t);
	}

	@Override
	public int deleteById(Integer id) {
		return itoSystemStatusDao.deleteById(id);
	}

	@Override
	public ItoSystemStatus loadById(Integer id) {
		return itoSystemStatusDao.loadById(id);
	}

	@Override
	public List<ItoSystemStatus> queryAll() {
		return itoSystemStatusDao.queryAll();
	}
	
	public IItoSystemStatusDao getItoSystemStatusDao() {
		return itoSystemStatusDao;
	}

	public void setItoSystemStatusDao(IItoSystemStatusDao itoSystemStatusDao) {
		this.itoSystemStatusDao = itoSystemStatusDao;
	}
	
}