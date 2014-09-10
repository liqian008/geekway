package com.bruce.geekway.service.impl.ito;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bruce.geekway.dao.ito.IItoWwjRecordDao;
import com.bruce.geekway.model.ItoUserProfileCriteria;
import com.bruce.geekway.model.ItoWwjRecord;
import com.bruce.geekway.service.ito.IItoWwjRecordService;

@Service
public class ItoWwjRecordServiceImpl implements IItoWwjRecordService{
	
	@Autowired
	private IItoWwjRecordDao itoWwjRecordDao;
	
	@Override
	public int save(ItoWwjRecord t) {
		return itoWwjRecordDao.save(t);
	}

	@Override
	public int updateById(ItoWwjRecord t) {
		return itoWwjRecordDao.updateById(t);
	}

	@Override
	public int deleteById(Integer id) {
		return itoWwjRecordDao.deleteById(id);
	}

	@Override
	public ItoWwjRecord loadById(Integer id) {
		return itoWwjRecordDao.loadById(id);
	}

	@Override
	public List<ItoWwjRecord> queryAll() {
		return itoWwjRecordDao.queryAll();
	}
	
	

	public IItoWwjRecordDao getItoWwjRecordDao() {
		return itoWwjRecordDao;
	}

	public void setItoWwjRecordDao(IItoWwjRecordDao itoWwjRecordDao) {
		this.itoWwjRecordDao = itoWwjRecordDao;
	}

	@Override
	public int updateByCriteria(ItoWwjRecord t, ItoUserProfileCriteria criteria) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int deleteByCriteria(ItoUserProfileCriteria criteria) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public List<ItoWwjRecord> queryAll(String orderByClause) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<ItoWwjRecord> queryByCriteria(ItoUserProfileCriteria criteria) {
		// TODO Auto-generated method stub
		return null;
	}
	
}