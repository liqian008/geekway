package com.bruce.geekway.service.impl.ito;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bruce.geekway.dao.ito.IItoWwjRecordDao;
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
	
}