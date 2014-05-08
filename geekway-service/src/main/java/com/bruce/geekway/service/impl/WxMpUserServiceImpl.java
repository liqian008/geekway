package com.bruce.geekway.service.impl;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bruce.geekway.dao.IWxMpUserDao;
import com.bruce.geekway.model.WxMpUser;
import com.bruce.geekway.model.wx.json.response.WxUserInfoResult;
import com.bruce.geekway.service.IWxMpUserService;
import com.bruce.geekway.service.mp.WxUserService;

@Service
public class WxMpUserServiceImpl implements IWxMpUserService{
	
	@Autowired
	private WxUserService wxUserService;
	
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
	
	
	@Override
	public int insertOrUpdate(WxMpUser wxMpUser) {
		return wxMpUserDao.insertOrUpdate(wxMpUser);
	}
	
	/**
	 * 新关注用户
	 */
	@Override
	public int newMpUser(String openId) {
		
		WxUserInfoResult userInfoResult =  wxUserService.getUser(openId);
		if(userInfoResult!=null){
			WxMpUser wxMpUser = new WxMpUser();
			wxMpUser.setOpenId(openId);
			wxMpUser.setNickname(userInfoResult.getNickname());
			wxMpUser.setCity(userInfoResult.getCity());
			wxMpUser.setCountry(userInfoResult.getCountry());
			wxMpUser.setProvince(userInfoResult.getProvince());
			wxMpUser.setHeadImgUrl(userInfoResult.getHeadimgurl());
			wxMpUser.setLanguage(userInfoResult.getLanguage());
			wxMpUser.setSubscribeStatus(userInfoResult.getSubscribe());
			wxMpUser.setSex(userInfoResult.getSex());
			wxMpUser.setCreateTime(new Date());
			return save(wxMpUser);
		}	
		return 0;
	}
	
	public IWxMpUserDao getWxMpUserDao() {
		return wxMpUserDao;
	}

	public void setWxMpUserDao(IWxMpUserDao wxMpUserDao) {
		this.wxMpUserDao = wxMpUserDao;
	}

	
	

}