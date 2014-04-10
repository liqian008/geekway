package com.bruce.geekway.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bruce.geekway.dao.IWxDefaultReplyDao;
import com.bruce.geekway.model.WxDefaultReply;
import com.bruce.geekway.service.IWxDefaultReplyService;

@Service
public class WxDefaultReplyServiceImpl implements IWxDefaultReplyService{
	
	@Autowired
	private IWxDefaultReplyDao wxDefaultReplyDao;
	
	@Override
	public int save(WxDefaultReply t) {
		return wxDefaultReplyDao.save(t);
	}

	@Override
	public int updateById(WxDefaultReply t) {
		return wxDefaultReplyDao.updateById(t);
	}

	@Override
	public int deleteById(Integer id) {
		return wxDefaultReplyDao.deleteById(id);
	}

	@Override
	public WxDefaultReply loadById(Integer id) {
		return wxDefaultReplyDao.loadById(id);
	}

	@Override
	public List<WxDefaultReply> queryAll() {
		return wxDefaultReplyDao.queryAll();
	}

	public IWxDefaultReplyDao getWxDefaultReplyDao() {
		return wxDefaultReplyDao;
	}

	public void setWxDefaultReplyDao(IWxDefaultReplyDao wxDefaultReplyDao) {
		this.wxDefaultReplyDao = wxDefaultReplyDao;
	}
	
}