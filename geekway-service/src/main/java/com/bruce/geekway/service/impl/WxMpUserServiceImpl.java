package com.bruce.geekway.service.impl;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bruce.geekway.dao.mapper.WxMpUserMapper;
import com.bruce.geekway.model.WxMpUser;
import com.bruce.geekway.model.WxMpUserCriteria;
import com.bruce.geekway.service.IWxMpUserService;

@Service
public class WxMpUserServiceImpl implements IWxMpUserService {
	@Autowired
	private WxMpUserMapper wxMpUserMapper;

	@Override
	public int save(WxMpUser t) {
		return wxMpUserMapper.insertSelective(t);
	}

	@Override
	public int updateById(WxMpUser t) {
		return wxMpUserMapper.updateByPrimaryKeySelective(t);
	}

	@Override
	public int updateByCriteria(WxMpUser t, WxMpUserCriteria criteria) {
		return wxMpUserMapper.updateByExampleSelective(t, criteria);
	}

	@Override
	public int deleteById(Integer id) {
		return wxMpUserMapper.deleteByPrimaryKey(id);
	}

	@Override
	public int deleteByCriteria(WxMpUserCriteria criteria) {
		return wxMpUserMapper.deleteByExample(criteria);
	}

	@Override
	public WxMpUser loadById(Integer id) {
		return wxMpUserMapper.selectByPrimaryKey(id);
	}

	@Override
	public List<WxMpUser> queryAll() {
		return queryAll(null);
	}

	@Override
	public List<WxMpUser> queryAll(String orderByClause) {
		WxMpUserCriteria criteria = new WxMpUserCriteria();
		criteria.createCriteria();
		criteria.setOrderByClause(orderByClause);
		return queryByCriteria(criteria);
	}

	@Override
	public List<WxMpUser> queryByCriteria(WxMpUserCriteria criteria) {
		return wxMpUserMapper.selectByExample(criteria);
	}

	/**
	 * 新关注用户
	 */
	@Override
	public int newSubscribeUser(String userOpenId) {
		WxMpUser wxMpUser = loadByOpenId(userOpenId);
		if (wxMpUser == null) {// 不为空，新关注
			wxMpUser = new WxMpUser();
			wxMpUser.setOpenId(userOpenId);
			wxMpUser.setSubscribeStatus((short) 1);
			wxMpUser.setSyncStatus((short) 0);
			wxMpUser.setCreateTime(new Date());
			return save(wxMpUser);
		}
		return 0;
	}

	/**
	 * 
	 */
	public WxMpUser loadByOpenId(String userOpenId) {
		WxMpUserCriteria criteria = new WxMpUserCriteria();
		criteria.createCriteria().andOpenIdEqualTo(userOpenId);
		List<WxMpUser> mpUserList = wxMpUserMapper.selectByExample(criteria);
		if (mpUserList != null && mpUserList.size() > 0) {
			return mpUserList.get(0);
		}
		return null;
	}

	/**
	 * 用户关注重复
	 */
	@Override
	public int repeatSubscribeUser(String userOpenId) {
		// 将订阅状态改为1
		return updateUserSubscribeStatus(userOpenId, (short) 1);
	}

	/**
	 * 用户退订
	 */
	@Override
	public int unsubscribeUser(String userOpenId) {
		// TODO 放在线程中执行
		// 将订阅状态改为0
		return updateUserSubscribeStatus(userOpenId, (short) 0);
	}

	/**
	 * 根据同步状态获取用户列表
	 */
	@Override
	public List<WxMpUser> getMpUserListBySyncStatus(short syncStatus) {
		WxMpUserCriteria criteria = new WxMpUserCriteria();
		criteria.createCriteria().andSyncStatusEqualTo(syncStatus);
		return wxMpUserMapper.selectByExample(criteria);
	}
	
	private int updateUserSubscribeStatus(String userOpenId, short subscribeStatus) {
		WxMpUser mpUser = new WxMpUser();
		mpUser.setSubscribeStatus(subscribeStatus);// 未订阅为0，订阅为0

		WxMpUserCriteria criteria = new WxMpUserCriteria();
		criteria.createCriteria().andOpenIdEqualTo(userOpenId);
		return wxMpUserMapper.updateByExampleSelective(mpUser, criteria);
	}

	public WxMpUserMapper getWxMpUserMapper() {
		return wxMpUserMapper;
	}

	public void setWxMpUserMapper(WxMpUserMapper wxMpUserMapper) {
		this.wxMpUserMapper = wxMpUserMapper;
	}

	

}
