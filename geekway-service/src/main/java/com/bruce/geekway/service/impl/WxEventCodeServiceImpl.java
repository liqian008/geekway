package com.bruce.geekway.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bruce.geekway.dao.IWxEventCodeDao;
import com.bruce.geekway.model.WxEventCode;
import com.bruce.geekway.service.IWxEventCodeService;

@Service
public class WxEventCodeServiceImpl implements IWxEventCodeService{
	
	@Autowired
	private IWxEventCodeDao wxEventCodeDao;
	
	@Override
	public int save(WxEventCode t) {
		return wxEventCodeDao.save(t);
	}

	@Override
	public int updateById(WxEventCode t) {
		return wxEventCodeDao.updateById(t);
	}

	@Override
	public int deleteById(Integer id) {
		return wxEventCodeDao.deleteById(id);
	}

	@Override
	public WxEventCode loadById(Integer id) {
		return wxEventCodeDao.loadById(id);
	}

	@Override
	public List<WxEventCode> queryAll() {
		return wxEventCodeDao.queryAll();
	}
	
	@Deprecated
	public WxEventCode loadByCode(String textCode){
		return wxEventCodeDao.loadByCode(textCode);
	}
	
	public WxEventCode loadByTypeCode(short eventType, String eventCode){
		return wxEventCodeDao.loadByTypeCode(eventType, eventCode);
	}
	

	public IWxEventCodeDao getWxEventCodeDao() {
		return wxEventCodeDao;
	}

	public void setWxEventCodeDao(IWxEventCodeDao wxEventCodeDao) {
		this.wxEventCodeDao = wxEventCodeDao;
	}
	
	
}