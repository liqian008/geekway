package com.bruce.geekway.service.impl.ito;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bruce.geekway.dao.ito.IItoSliderDao;
import com.bruce.geekway.model.ItoSlider;
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
	
}