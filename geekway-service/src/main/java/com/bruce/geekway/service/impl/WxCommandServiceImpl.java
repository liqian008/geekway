package com.bruce.geekway.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bruce.geekway.dao.IWxCommandDao;
import com.bruce.geekway.model.WxCommand;
import com.bruce.geekway.service.IWxCommandService;

@Service
public class WxCommandServiceImpl implements IWxCommandService{
	
	@Autowired
	private IWxCommandDao wxCommandDao;
	
	@Override
	public int save(WxCommand t) {
		return wxCommandDao.save(t);
	}

	@Override
	public int updateById(WxCommand t) {
		return wxCommandDao.updateById(t);
	}

	@Override
	public int deleteById(Integer id) {
		return wxCommandDao.deleteById(id);
	}

	@Override
	public WxCommand loadById(Integer id) {
		return wxCommandDao.loadById(id);
	}

	@Override
	public List<WxCommand> queryAll() {
		return wxCommandDao.queryAll();
	}
	
//	@Deprecated
//	public WxCommand loadByCode(String textCode){
//		return wxCommandDao.loadByCode(textCode);
//	}
	
	public WxCommand loadByCommandType(short commandType, String command){
		return wxCommandDao.loadByCommandType(commandType, command);
	}
	

	public IWxCommandDao getWxCommandDao() {
		return wxCommandDao;
	}

	public void setWxCommandDao(IWxCommandDao wxCommandDao) {
		this.wxCommandDao = wxCommandDao;
	}

}