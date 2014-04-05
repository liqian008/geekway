package com.bruce.geekway.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bruce.geekway.dao.IWxCodeModuleDao;
import com.bruce.geekway.model.WxCodeModule;
import com.bruce.geekway.service.IWxCodeModuleService;

@Service
public class WxCodeModuleServiceImpl implements IWxCodeModuleService{
	
	@Autowired
	private IWxCodeModuleDao wxCodeModuleDao;
	
	@Override
	public int save(WxCodeModule t) {
		return wxCodeModuleDao.save(t);
	}

	@Override
	public int updateById(WxCodeModule t) {
		return wxCodeModuleDao.updateById(t);
	}

	@Override
	public int deleteById(Integer id) {
		return wxCodeModuleDao.deleteById(id);
	}

	@Override
	public WxCodeModule loadById(Integer id) {
		return wxCodeModuleDao.loadById(id);
	}

	@Override
	public List<WxCodeModule> queryAll() {
		return wxCodeModuleDao.queryAll();
	}
	
	
}