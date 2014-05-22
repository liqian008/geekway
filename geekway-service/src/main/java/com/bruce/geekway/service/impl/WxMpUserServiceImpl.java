package com.bruce.geekway.service.impl;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bruce.geekway.dao.IWxMpUserDao;
import com.bruce.geekway.model.WxMpUser;
import com.bruce.geekway.service.IWxMpUserService;

@Service
public class WxMpUserServiceImpl implements IWxMpUserService{
	
//	@Autowired
//	private WxUserService wxUserService;
	
	@Autowired
	private IWxMpUserDao wxMpUserDao;
	
	@Override
	public int save(WxMpUser t) {
		return wxMpUserDao.save(t);
	}

	@Override
	public int updateById(WxMpUser t) {
		return wxMpUserDao.updateById(t);
	}

	@Override
	public int deleteById(Integer id) {
		return wxMpUserDao.deleteById(id);
	}

	@Override
	public WxMpUser loadById(Integer id) {
		return wxMpUserDao.loadById(id);
	}
	

	@Override
	public List<WxMpUser> queryAll() {
		return wxMpUserDao.queryAll();
	}
	
	
//	@Override
//	public int insertOrUpdate(WxMpUser wxMpUser) {
//		return wxMpUserDao.insertOrUpdate(wxMpUser);
//	}
	
	/**
	 * 新关注用户
	 */
	@Override
	public int newSubscribeUser(String userOpenId) {
//		//TODO 放在线程中执行
		WxMpUser wxMpUser = new WxMpUser();
		wxMpUser.setOpenId(userOpenId);
		wxMpUser.setSyncStatus((short) 0);
		wxMpUser.setCreateTime(new Date());
		return save(wxMpUser);
	}
	
	/**
	 * 用户退订
	 */
	@Override
	public int unsubscribeUser(String userOpenId) {
//		//TODO 放在线程中执行
		return wxMpUserDao.unsubscribeUser(userOpenId);
	}
	
	public IWxMpUserDao getWxMpUserDao() {
		return wxMpUserDao;
	}

	public void setWxMpUserDao(IWxMpUserDao wxMpUserDao) {
		this.wxMpUserDao = wxMpUserDao;
	}

//	public WxUserService getWxUserService() {
//		return wxUserService;
//	}
//
//	public void setWxUserService(WxUserService wxUserService) {
//		this.wxUserService = wxUserService;
//	}

}