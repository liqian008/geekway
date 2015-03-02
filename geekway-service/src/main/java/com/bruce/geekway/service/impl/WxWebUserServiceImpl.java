package com.bruce.geekway.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import com.bruce.foundation.model.paging.PagingResult;
import com.bruce.geekway.constants.ConstConfig;
import com.bruce.geekway.constants.ConstMemc;
import com.bruce.geekway.dao.mapper.WxWebUserMapper;
import com.bruce.geekway.model.WxWebUser;
import com.bruce.geekway.model.WxWebUserCriteria;
import com.bruce.geekway.model.exception.CachedException;
import com.bruce.geekway.service.IWxWebUserService;
import com.bruce.geekway.service.mp.WxMpUserService;

@Service
public class WxWebUserServiceImpl implements IWxWebUserService {
	
	@Autowired
	private WxWebUserMapper wxWebUserMapper;
	@Autowired
	private WxMpUserService wxMpUserService;
	
	@Override
	public int save(WxWebUser t) {
		return wxWebUserMapper.insertSelective(t);
	}

	@Override
	public int updateById(WxWebUser t) {
		return wxWebUserMapper.updateByPrimaryKeySelective(t);
	}

	@Override
	public int updateByCriteria(WxWebUser t, WxWebUserCriteria criteria) {
		return wxWebUserMapper.updateByExampleSelective(t, criteria);
	}

	@Override
	public int deleteById(Integer id) {
		return wxWebUserMapper.deleteByPrimaryKey(id);
	}

	@Override
	public int deleteByCriteria(WxWebUserCriteria criteria) {
		return wxWebUserMapper.deleteByExample(criteria);
	}

	@Override
	public WxWebUser loadById(Integer id) {
		return wxWebUserMapper.selectByPrimaryKey(id);
	}

	@Override
	public List<WxWebUser> queryAll() {
		return queryAll(null);
	}

	@Override
	public List<WxWebUser> queryAll(String orderByClause) {
		WxWebUserCriteria criteria = new WxWebUserCriteria();
		criteria.createCriteria();
		criteria.setOrderByClause(orderByClause);
		return queryByCriteria(criteria);
	}

	@Override
	public List<WxWebUser> queryByCriteria(WxWebUserCriteria criteria) {
		return wxWebUserMapper.selectByExample(criteria);
	}
	
	@Override
	public int countByCriteria(WxWebUserCriteria criteria) {
		return wxWebUserMapper.countByExample(criteria);
	}
	
	@Override
	public List<WxWebUser> fallloadByCriteria(int pageSize, WxWebUserCriteria criteria) {
		return null;
	}

	@Override
	public PagingResult<WxWebUser> pagingByCriteria(int pageNo, int pageSize, WxWebUserCriteria criteria) {
		pageNo = pageNo<=0?1:pageNo;//确保pageNo合法
		pageSize = pageNo<=0?ConstConfig.PAGE_SIZE_DEFAULT:pageSize;//确保pageSize合法
		int offset = (pageNo-1)*pageSize;
		
		//构造查询条件
		if(criteria==null){
			criteria = new WxWebUserCriteria();
		}
		
		criteria.setLimitOffset(offset);
		criteria.setLimitRows(pageSize);
		
		int count = wxWebUserMapper.countByExample(criteria);
		List<WxWebUser> dataList = wxWebUserMapper.selectByExample(criteria);
		//返回分页数据
		return new PagingResult<WxWebUser>(pageNo, pageSize, count, dataList);
	}

	

	/**
	 * 
	 */
	public WxWebUser loadByUnionId(String userUnionId) {
		WxWebUserCriteria criteria = new WxWebUserCriteria();
		criteria.createCriteria().andUnionIdEqualTo(userUnionId);
		List<WxWebUser> mpUserList = wxWebUserMapper.selectByExample(criteria);
		if (mpUserList != null && mpUserList.size() > 0) {
			return mpUserList.get(0);
		}
		return null;
	}
	
	/**
	 * 
	 */
	public WxWebUser loadByOpenId(String userOpenId) {
		WxWebUserCriteria criteria = new WxWebUserCriteria();
		criteria.createCriteria().andOpenIdEqualTo(userOpenId);
		List<WxWebUser> mpUserList = wxWebUserMapper.selectByExample(criteria);
		if (mpUserList != null && mpUserList.size() > 0) {
			return mpUserList.get(0);
		}
		return null;
	}


	@Override
//	@Caching(
//			cacheable = { 
//					@Cacheable(value = ConstMemc.MEMCACHE_CACHE_VALUE + "#7000", key = "'webUserOpenId-'+#userOpenId") }, 
//			put = { 
//					@CachePut(value = ConstMemc.MEMCACHE_CACHE_VALUE + "#7000", key = "'webUserOpenId-'+#userOpenId", condition = "#result != null")}
//			)
	@Cacheable(value = ConstMemc.MEMCACHE_CACHE_VALUE + "#7000", key = "'webUserOpenId-'+#userOpenId")//, condition = "#result != null ") 
	public WxWebUser loadCachedByOpenId(String userOpenId) throws CachedException { 
		WxWebUser webUser = loadByOpenId(userOpenId);
		if(webUser==null||webUser.getId()==null){
			throw new CachedException("webUser result is null"); 
		}
		return webUser;
	}
	
	
	public WxWebUserMapper getWxWebUserMapper() {
		return wxWebUserMapper;
	}

	public void setWxWebUserMapper(WxWebUserMapper wxWebUserMapper) {
		this.wxWebUserMapper = wxWebUserMapper;
	}

	
}
