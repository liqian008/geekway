package com.bruce.geekway.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bruce.geekway.dao.IWxCustomizeMenuDao;
import com.bruce.geekway.model.WxCustomizeMenu;
import com.bruce.geekway.service.IWxCustomizeMenuService;

@Service
public class WxCustomizeMenuServiceImpl implements IWxCustomizeMenuService{
	
	@Autowired
	private IWxCustomizeMenuDao wxCustomizeMenuDao;
	
	@Override
	public int save(WxCustomizeMenu t) {
		return wxCustomizeMenuDao.save(t);
	}

	@Override
	public int updateById(WxCustomizeMenu t) {
		return wxCustomizeMenuDao.updateById(t);
	}

	@Override
	public int deleteById(Integer id) {
		return wxCustomizeMenuDao.deleteById(id);
	}

	@Override
	public WxCustomizeMenu loadById(Integer id) {
		return wxCustomizeMenuDao.loadById(id);
	}

	@Override
	public List<WxCustomizeMenu> queryAll() {
		return wxCustomizeMenuDao.queryAll();
	}
	
//	public WxCustomizeMenu loadByCode(String textCode){
//		return wxCustomizeMenuDao.loadByCode(textCode);
//	}

	public IWxCustomizeMenuDao getWxCustomizeMenuDao() {
		return wxCustomizeMenuDao;
	}

	public void setWxCustomizeMenuDao(IWxCustomizeMenuDao wxCustomizeMenuDao) {
		this.wxCustomizeMenuDao = wxCustomizeMenuDao;
	}
	
	
}