package com.bruce.geekway.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bruce.geekway.dao.IWxTextCodeDao;
import com.bruce.geekway.model.WxTextCode;
import com.bruce.geekway.service.IWxTextCodeService;

@Service
public class WxTextCodeServiceImpl implements IWxTextCodeService{
	
	@Autowired
	private IWxTextCodeDao wxTextCodeDao;
	
	@Override
	public int save(WxTextCode t) {
		return wxTextCodeDao.save(t);
	}

	@Override
	public int updateById(WxTextCode t) {
		return wxTextCodeDao.updateById(t);
	}

	@Override
	public int deleteById(Integer id) {
		return wxTextCodeDao.deleteById(id);
	}

	@Override
	public WxTextCode loadById(Integer id) {
		return wxTextCodeDao.loadById(id);
	}

	@Override
	public List<WxTextCode> queryAll() {
		return wxTextCodeDao.queryAll();
	}
	
	public WxTextCode loadByCode(String textCode){
		return wxTextCodeDao.loadByCode(textCode);
	}

	public IWxTextCodeDao getWxTextCodeDao() {
		return wxTextCodeDao;
	}

	public void setWxTextCodeDao(IWxTextCodeDao wxTextCodeDao) {
		this.wxTextCodeDao = wxTextCodeDao;
	}
	
	
}