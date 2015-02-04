package com.bruce.geekway.service.impl;

import java.util.List;

import org.apache.commons.collections.CollectionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import com.bruce.foundation.model.paging.PagingResult;
import com.bruce.geekway.constants.ConstMemc;
import com.bruce.geekway.dao.mapper.WxAppMapper;
import com.bruce.geekway.model.WxApp;
import com.bruce.geekway.model.WxAppCriteria;
import com.bruce.geekway.service.IWxAppService;

@Service
public class WxAppServiceImpl implements IWxAppService {

	@Autowired
	private WxAppMapper wxAppMapper;
	
	
	@Override
	public int save(WxApp t) {
		return wxAppMapper.insertSelective(t);
	}

	@Override
	public int updateById(WxApp t) {
		return wxAppMapper.updateByPrimaryKeySelective(t);
	}

	@Override
	public int updateByCriteria(WxApp t, WxAppCriteria criteria) {
		return wxAppMapper.updateByExampleSelective(t, criteria);
	}

	@Override
	public int deleteById(Integer id) {
		return wxAppMapper.deleteByPrimaryKey(id);
	}

	@Override
	public int deleteByCriteria(WxAppCriteria criteria) {
		return wxAppMapper.deleteByExample(criteria);
	}

	@Override
	public WxApp loadById(Integer id) {
		return wxAppMapper.selectByPrimaryKey(id);
	}

	@Override
	public List<WxApp> queryAll() {
		return queryAll(null);
	}

	@Override
	public List<WxApp> queryAll(String orderByClause) {
		WxAppCriteria criteria = new WxAppCriteria();
		criteria.createCriteria();
		criteria.setOrderByClause(orderByClause);
		return queryByCriteria(criteria);
	}

	@Override
	public List<WxApp> queryByCriteria(WxAppCriteria criteria) {
		return wxAppMapper.selectByExample(criteria);
	}
	
	@Override
	public int countByCriteria(WxAppCriteria criteria) {
		return wxAppMapper.countByExample(criteria);
	}
	
	@Override
	public List<WxApp> fallloadByCriteria(int pageSize, WxAppCriteria criteria) {
		return null;
	}

	@Override
	public PagingResult<WxApp> pagingByCriteria(int pageNo, int pageSize,
			WxAppCriteria criteria) {
		// TODO Auto-generated method stub
		return null;
	}
	
	@Override
	@Cacheable(value=ConstMemc.MEMCACHE_CACHE_VALUE+"#7000", key="'"+ConstMemc.MEMCACHE_KEY_WX_APP_ID+"-'+#wxAppId")
	public WxApp loadCachedByWxAppId(String wxAppId) {
		return loadByWxAppId(wxAppId);
	}

	@Override
	public WxApp loadByWxAppId(String wxAppId) {
		WxAppCriteria criteria = new WxAppCriteria();
		criteria.createCriteria().andWxAppIdEqualTo(wxAppId);
		
		List<WxApp> wxAppList = queryByCriteria(criteria);
		if(CollectionUtils.isNotEmpty(wxAppList)&&wxAppList.size()>0){
			return wxAppList.get(0);
		}
		return null;
	}
	
	@Override
	public WxApp loadMyApp(int id, int userId) {
		WxAppCriteria criteria = new WxAppCriteria();
		criteria.createCriteria().andIdEqualTo(id).andUserIdEqualTo(userId);
		
		List<WxApp> wxAppList = queryByCriteria(criteria);
		if(CollectionUtils.isNotEmpty(wxAppList)&&wxAppList.size()>0){
			return wxAppList.get(0);
		}
		return null;
	}

	@Override
	public WxApp loadCachedMyApp(int id, int userId) {
		// TODO Auto-generated method stub
		return null;
	}
	

	@Override
	public List<WxApp> queryMyApp(int userId) {
		WxAppCriteria criteria = new WxAppCriteria();
		criteria.createCriteria().andUserIdEqualTo(userId);
		
		List<WxApp> myWxAppList = queryByCriteria(criteria);
		return myWxAppList;
	}

	@Override
	@Cacheable(value=ConstMemc.MEMCACHE_CACHE_VALUE+"#7000", key="'"+ConstMemc.MEMCACHE_KEY_WY_WX_APPS+"-'+#userId")
	public List<WxApp> queryCachedMyApp(int userId) {
		return queryMyApp(userId);
	}

	
	public WxAppMapper getWxAppMapper() {
		return wxAppMapper;
	}

	public void setWxAppMapper(WxAppMapper wxAppMapper) {
		this.wxAppMapper = wxAppMapper;
	}


	
	
}