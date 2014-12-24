package com.bruce.geekway.service.impl;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bruce.geekway.dao.mapper.WxMpUserMapper;
import com.bruce.geekway.model.WxMpUser;
import com.bruce.geekway.model.WxMpUserCriteria;
import com.bruce.geekway.model.wx.json.response.WxUserInfoResult;
import com.bruce.geekway.service.IWxMpUserService;
import com.bruce.geekway.service.mp.WxUserService;
import com.bruce.geekway.utils.ConfigUtil;

@Service
public class WxMpUserServiceImpl implements IWxMpUserService {
	
	private static final String DEFAULT_USER_AVATAR_URL = ConfigUtil.getString("weixinmp_anonymous_user_avatar");
	
	@Autowired
	private WxMpUserMapper wxMpUserMapper;
	@Autowired
	private WxUserService wxUserService;
	
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
	 * 新关注用户
	 */
	@Override
	public int newSubscribeUser(String userOpenId) {
		WxUserInfoResult userInfoResult =  wxUserService.getUser(userOpenId);
		//有效数据
		if(userInfoResult!=null&&userInfoResult.getErrcode()==null){
			WxMpUser mpUser = new WxMpUser();
			mpUser.setOpenId(userOpenId);
			mpUser.setNickname(userInfoResult.getNickname());
			mpUser.setCity(userInfoResult.getCity());
			mpUser.setCountry(userInfoResult.getCountry());
			mpUser.setProvince(userInfoResult.getProvince());
			mpUser.setHeadImgUrl(userInfoResult.getHeadimgurl());
			mpUser.setLanguage(userInfoResult.getLanguage());
			mpUser.setSubscribeStatus(userInfoResult.getSubscribe());
			mpUser.setSex(userInfoResult.getSex());
			mpUser.setCreateTime(new Date());
			mpUser.setSyncStatus((short) 1);
			int result = save(mpUser);
			
			if(result<=0){//获取用户资料失败后，则只存入openid，待后台重新获取
				WxMpUser wxMpUser = loadByOpenId(userOpenId);
				if (wxMpUser == null) {// 不为空，新关注
					wxMpUser = new WxMpUser();
					wxMpUser.setHeadImgUrl(DEFAULT_USER_AVATAR_URL);
					wxMpUser.setNickname("新用户");
					wxMpUser.setOpenId(userOpenId);
					wxMpUser.setSubscribeStatus((short) 1);
					wxMpUser.setSyncStatus((short) 0);
					wxMpUser.setCreateTime(new Date());
					return save(wxMpUser);
				}
			}
		}
		return 0;
	}

	
	/**
	 * 用户关注重复
	 */
	@Override
	public int reSubscribeUser(String userOpenId) {
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
	 * 根据用户所发消息中，找回用户openId（避免更服的时候遗漏用户）
	 * @param userOpenId
	 * @return
	 */
	@Override
	public int logUserFromMessage(String userOpenId) {
		WxMpUser wxMpUser = new WxMpUser();
		wxMpUser.setHeadImgUrl(DEFAULT_USER_AVATAR_URL);
		wxMpUser.setNickname("新用户");
		wxMpUser.setOpenId(userOpenId);
		wxMpUser.setSubscribeStatus((short) 1);
		wxMpUser.setSyncStatus((short) 0);
		wxMpUser.setCreateTime(new Date());
		return save(wxMpUser);
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

	public WxUserService getWxUserService() {
		return wxUserService;
	}

	public void setWxUserService(WxUserService wxUserService) {
		this.wxUserService = wxUserService;
	}

	@Override
	public int countByCriteria(WxMpUserCriteria criteria) {
		// TODO Auto-generated method stub
		return 0;
	}

	
}
