package com.bruce.geekway.service.impl;

import java.util.Date;
import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bruce.geekway.dao.mapper.WxAccessTokenMapper;
import com.bruce.geekway.model.WxAccessToken;
import com.bruce.geekway.model.WxAccessTokenCriteria;
import com.bruce.geekway.model.wx.json.response.WxAuthResult;
import com.bruce.geekway.service.IWxAccessTokenService;
import com.bruce.geekway.service.mp.WxMpTokenService;

@Service
public class WxAccessTokenServiceImpl implements IWxAccessTokenService {

	@Autowired
	private WxAccessTokenMapper wxAccessTokenMapper;
	@Autowired
	private WxMpTokenService wxMpTokenService;
	
	
	@Override
	public int save(WxAccessToken t) {
		return wxAccessTokenMapper.insertSelective(t);
	}

	@Override
	public int updateById(WxAccessToken t) {
		return wxAccessTokenMapper.updateByPrimaryKeySelective(t);
	}

	@Override
	public int updateByCriteria(WxAccessToken t, WxAccessTokenCriteria criteria) {
		return wxAccessTokenMapper.updateByExampleSelective(t, criteria);
	}

	@Override
	public int deleteById(Integer id) {
		return wxAccessTokenMapper.deleteByPrimaryKey(id);
	}

	@Override
	public int deleteByCriteria(WxAccessTokenCriteria criteria) {
		return wxAccessTokenMapper.deleteByExample(criteria);
	}

	@Override
	public WxAccessToken loadById(Integer id) {
		return wxAccessTokenMapper.selectByPrimaryKey(id);
	}

	@Override
	public List<WxAccessToken> queryAll() {
		return queryAll(null);
	}

	@Override
	public List<WxAccessToken> queryAll(String orderByClause) {
		WxAccessTokenCriteria criteria = new WxAccessTokenCriteria();
		criteria.createCriteria();
		criteria.setOrderByClause(orderByClause);
		return queryByCriteria(criteria);
	}

	@Override
	public List<WxAccessToken> queryByCriteria(WxAccessTokenCriteria criteria) {
		return wxAccessTokenMapper.selectByExample(criteria);
	}
	
	@Override
	public int countByCriteria(WxAccessTokenCriteria criteria) {
		return wxAccessTokenMapper.countByExample(criteria);
	}
	
	/**
	 * 获取缓存中的accessToken
	 */
	@Override
	public synchronized String getCachedAccessToken() {
		//从db中获取缓存的accessToken
		WxAccessToken dbAccessToken = loadById(1);
		if(dbAccessToken!=null&&!StringUtils.isBlank(dbAccessToken.getAccessToken())&&dbAccessToken.getExpireTime().getTime()>System.currentTimeMillis()){
			//accessToken可用
			return dbAccessToken.getAccessToken();
		}else{
			WxAuthResult wxResult = wxMpTokenService.getMpAccessToken();
			if(wxResult!=null){//成功的响应
				String accessToken = wxResult.getAccess_token();
				
				WxAccessToken newAccessToken = new WxAccessToken();
				newAccessToken.setAccessToken(accessToken);
				newAccessToken.setExpireTime(new Date(wxResult.getExpiresTime()));
				
				int result = cachedAccessToken(newAccessToken);
				
				return accessToken;
			}
		}
		return null;
	}

	
	@Override
	public int cachedAccessToken(WxAccessToken newAccessToken) {
		//构造更新条件
		WxAccessTokenCriteria criteria = new WxAccessTokenCriteria();
		criteria.createCriteria().andIdEqualTo(1);
		//更新db中的accessToken
		int result = updateByCriteria(newAccessToken, criteria); 
		return result;
	}
	
	
	
	public WxAccessTokenMapper getWxAccessTokenMapper() {
		return wxAccessTokenMapper;
	}

	public void setWxAccessTokenMapper(WxAccessTokenMapper wxAccessTokenMapper) {
		this.wxAccessTokenMapper = wxAccessTokenMapper;
	}

	

	
}