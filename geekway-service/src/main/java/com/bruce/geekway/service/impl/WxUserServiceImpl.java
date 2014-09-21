package com.bruce.geekway.service.impl;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bruce.geekway.constants.ConstWeixin;
import com.bruce.geekway.dao.mapper.WxUserMapper;
import com.bruce.geekway.model.WxUser;
import com.bruce.geekway.model.WxUserCriteria;
import com.bruce.geekway.model.wx.json.response.WxUserInfoResult;
import com.bruce.geekway.service.IWxUserService;
import com.bruce.geekway.service.mp.WxMpUserService;

@Service
public class WxUserServiceImpl implements IWxUserService {
	
	@Autowired
	private WxUserMapper wxUserMapper;
	@Autowired
	private WxMpUserService wxMpUserService;
	
	@Override
	public int save(WxUser t) {
		return wxUserMapper.insertSelective(t);
	}

	@Override
	public int updateById(WxUser t) {
		return wxUserMapper.updateByPrimaryKeySelective(t);
	}

	@Override
	public int updateByCriteria(WxUser t, WxUserCriteria criteria) {
		return wxUserMapper.updateByExampleSelective(t, criteria);
	}

	@Override
	public int deleteById(Integer id) {
		return wxUserMapper.deleteByPrimaryKey(id);
	}

	@Override
	public int deleteByCriteria(WxUserCriteria criteria) {
		return wxUserMapper.deleteByExample(criteria);
	}

	@Override
	public WxUser loadById(Integer id) {
		return wxUserMapper.selectByPrimaryKey(id);
	}

	@Override
	public List<WxUser> queryAll() {
		return queryAll(null);
	}

	@Override
	public List<WxUser> queryAll(String orderByClause) {
		WxUserCriteria criteria = new WxUserCriteria();
		criteria.createCriteria();
		criteria.setOrderByClause(orderByClause);
		return queryByCriteria(criteria);
	}

	@Override
	public List<WxUser> queryByCriteria(WxUserCriteria criteria) {
		return wxUserMapper.selectByExample(criteria);
	}

	/**
	 * 
	 */
	public WxUser loadByOpenId(String userOpenId) {
		WxUserCriteria criteria = new WxUserCriteria();
		criteria.createCriteria().andOpenIdEqualTo(userOpenId);
		List<WxUser> mpUserList = wxUserMapper.selectByExample(criteria);
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
		WxUserInfoResult userInfoResult =  wxMpUserService.getUser(userOpenId);
		//有效数据
		if(userInfoResult!=null&&userInfoResult.getErrcode()==null){
			WxUser mpUser = new WxUser();
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
				WxUser wxUser = loadByOpenId(userOpenId);
				if (wxUser == null) {// 不为空，新关注
					wxUser = new WxUser();
					wxUser.setHeadImgUrl(ConstWeixin.DEFAULT_USER_AVATAR_URL);
					wxUser.setNickname("新用户");
					wxUser.setOpenId(userOpenId);
					wxUser.setSubscribeStatus((short) 1);
					wxUser.setSyncStatus((short) 0);
					wxUser.setCreateTime(new Date());
					return save(wxUser);
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
		WxUser wxUser = new WxUser();
		wxUser.setHeadImgUrl(ConstWeixin.DEFAULT_USER_AVATAR_URL);
		wxUser.setNickname("新用户");
		wxUser.setOpenId(userOpenId);
		wxUser.setSubscribeStatus((short) 1);
		wxUser.setSyncStatus((short) 0);
		wxUser.setCreateTime(new Date());
		return save(wxUser);
	}
	
	
	
	/**
	 * 根据同步状态获取用户列表
	 */
	@Override
	public List<WxUser> getUserListBySyncStatus(short syncStatus) {
		WxUserCriteria criteria = new WxUserCriteria();
		criteria.createCriteria().andSyncStatusEqualTo(syncStatus);
		return wxUserMapper.selectByExample(criteria);
	}
	
	
	private int updateUserSubscribeStatus(String userOpenId, short subscribeStatus) {
		WxUser mpUser = new WxUser();
		mpUser.setSubscribeStatus(subscribeStatus);// 未订阅为0，订阅为0

		WxUserCriteria criteria = new WxUserCriteria();
		criteria.createCriteria().andOpenIdEqualTo(userOpenId);
		return wxUserMapper.updateByExampleSelective(mpUser, criteria);
	}

	public WxUserMapper getWxUserMapper() {
		return wxUserMapper;
	}

	public void setWxUserMapper(WxUserMapper wxUserMapper) {
		this.wxUserMapper = wxUserMapper;
	}

	public WxMpUserService getWxMpUserService() {
		return wxMpUserService;
	}

	public void setWxMpUserService(WxMpUserService wxMpUserService) {
		this.wxMpUserService = wxMpUserService;
	}

	
	
}