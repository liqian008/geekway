package com.bruce.geekway.service.impl;

import java.util.List;

import org.apache.commons.collections.CollectionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import com.bruce.foundation.model.paging.PagingResult;
import com.bruce.geekway.constants.ConstMemc;
import com.bruce.geekway.dao.mapper.RegUserMapper;
import com.bruce.geekway.model.RegUser;
import com.bruce.geekway.model.RegUserCriteria;
import com.bruce.geekway.service.IRegUserService;

@Service
public class RegUserServiceImpl implements IRegUserService {

	@Autowired
	private RegUserMapper regUserMapper;
	
	
	@Override
	public int save(RegUser t) {
		return regUserMapper.insertSelective(t);
	}

	@Override
	public int updateById(RegUser t) {
		return regUserMapper.updateByPrimaryKeySelective(t);
	}

	@Override
	public int updateByCriteria(RegUser t, RegUserCriteria criteria) {
		return regUserMapper.updateByExampleSelective(t, criteria);
	}

	@Override
	public int deleteById(Integer id) {
		return regUserMapper.deleteByPrimaryKey(id);
	}

	@Override
	public int deleteByCriteria(RegUserCriteria criteria) {
		return regUserMapper.deleteByExample(criteria);
	}

	@Override
	public RegUser loadById(Integer id) {
		return regUserMapper.selectByPrimaryKey(id);
	}

	@Override
	public List<RegUser> queryAll() {
		return queryAll(null);
	}

	@Override
	public List<RegUser> queryAll(String orderByClause) {
		RegUserCriteria criteria = new RegUserCriteria();
		criteria.createCriteria();
		criteria.setOrderByClause(orderByClause);
		return queryByCriteria(criteria);
	}

	@Override
	public List<RegUser> queryByCriteria(RegUserCriteria criteria) {
		return regUserMapper.selectByExample(criteria);
	}
	
	@Override
	public int countByCriteria(RegUserCriteria criteria) {
		return regUserMapper.countByExample(criteria);
	}
	
	@Override
	public List<RegUser> fallloadByCriteria(int pageSize, RegUserCriteria criteria) {
		return null;
	}

	@Override
	public PagingResult<RegUser> pagingByCriteria(int pageNo, int pageSize,
			RegUserCriteria criteria) {
		return null;
	}
	
	@Override
	@Cacheable(value=ConstMemc.MEMCACHE_CACHE_VALUE+"#7000", key="'"+ConstMemc.MEMCACHE_KEY_WX_APP_ID+"-'+#regUserId")
	public RegUser loadCachedByRegUserId(int regUserId) {
		return loadByRegUserId(regUserId);
	}

	

	@Override
	public RegUser authRegUser(String email, String password) {
		RegUserCriteria criteria = new RegUserCriteria();
		criteria.createCriteria().andEmailEqualTo(email).andPasswordEqualTo(password);
		List<RegUser> userList = queryByCriteria(criteria);
		if(CollectionUtils.isNotEmpty(userList)){
			return userList.get(0);
		}
		return null;
	}
	
	
	@Override
	public RegUser loadByRegUserId(int regUserId) {
		return null;
	}
	
	public RegUserMapper getRegUserMapper() {
		return regUserMapper;
	}

	public void setRegUserMapper(RegUserMapper regUserMapper) {
		this.regUserMapper = regUserMapper;
	}

	

}