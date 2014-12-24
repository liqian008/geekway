package com.bruce.geekway.service.impl.ito;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bruce.geekway.dao.ito.IItoSystemStatusDao;
import com.bruce.geekway.model.ItoSystemStatus;
import com.bruce.geekway.model.ItoSystemStatusCriteria;
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

	@Override
	public int updateByCriteria(ItoSystemStatus t,
			ItoSystemStatusCriteria criteria) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int deleteByCriteria(ItoSystemStatusCriteria criteria) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public List<ItoSystemStatus> queryAll(String orderByClause) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<ItoSystemStatus> queryByCriteria(
			ItoSystemStatusCriteria criteria) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public int countByCriteria(ItoSystemStatusCriteria criteria) {
		// TODO Auto-generated method stub
		return 0;
	}
	
}