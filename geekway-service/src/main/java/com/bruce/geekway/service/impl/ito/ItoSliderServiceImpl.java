package com.bruce.geekway.service.impl.ito;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bruce.geekway.dao.ito.IItoSliderDao;
import com.bruce.geekway.model.ItoSlider;
import com.bruce.geekway.model.ItoSliderCriteria;
import com.bruce.geekway.service.ito.IItoSliderService;

@Service
public class ItoSliderServiceImpl implements IItoSliderService{
	
	@Autowired
	private IItoSliderDao itoSliderDao;
	
	@Override
	public int save(ItoSlider t) {
		return itoSliderDao.save(t);
	}

	@Override
	public int updateById(ItoSlider t) {
		return itoSliderDao.updateById(t);
	}

	@Override
	public int deleteById(Integer id) {
		return itoSliderDao.deleteById(id);
	}

	@Override
	public ItoSlider loadById(Integer id) {
		return itoSliderDao.loadById(id);
	}

	@Override
	public List<ItoSlider> queryAll() {
		return itoSliderDao.queryAll();
	}
	
	@Override
	public List<ItoSlider> querySortedList() {
		return itoSliderDao.querySortedList();
	}

	public IItoSliderDao getItoSliderDao() {
		return itoSliderDao;
	}

	public void setItoSliderDao(IItoSliderDao itoSliderDao) {
		this.itoSliderDao = itoSliderDao;
	}

	@Override
	public int updateByCriteria(ItoSlider t, ItoSliderCriteria criteria) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int deleteByCriteria(ItoSliderCriteria criteria) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public List<ItoSlider> queryAll(String orderByClause) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<ItoSlider> queryByCriteria(ItoSliderCriteria criteria) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public int countByCriteria(ItoSliderCriteria criteria) {
		// TODO Auto-generated method stub
		return 0;
	}
	
}