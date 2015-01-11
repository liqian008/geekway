package com.bruce.geekway.service.product.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bruce.geekway.dao.mapper.UserAddressMapper;
import com.bruce.geekway.model.UserAddress;
import com.bruce.geekway.model.UserAddressCriteria;
import com.bruce.geekway.service.product.IUserAddressService;

public class UserAddressServiceImpl implements IUserAddressService {

	@Autowired
	private UserAddressMapper userAddressMapper;

	@Override
	public int save(UserAddress t) {
		return userAddressMapper.insertSelective(t);
	}

	@Override
	public int updateById(UserAddress t) {
		return userAddressMapper.updateByPrimaryKeySelective(t);
	}

	@Override
	public int updateByCriteria(UserAddress t, UserAddressCriteria criteria) {
		return userAddressMapper.updateByExampleSelective(t, criteria);
	}

	@Override
	public int deleteById(Long id) {
		return userAddressMapper.deleteByPrimaryKey(id);
	}

	@Override
	public int deleteByCriteria(UserAddressCriteria criteria) {
		return userAddressMapper.deleteByExample(criteria);
	}

	@Override
	public UserAddress loadById(Long id) {
		return userAddressMapper.selectByPrimaryKey(id);
	}

	@Override
	public List<UserAddress> queryAll() {
		return queryAll(null);
	}

	@Override
	public List<UserAddress> queryAll(String orderByClause) {
		UserAddressCriteria criteria = new UserAddressCriteria();
		criteria.createCriteria();
		criteria.setOrderByClause(orderByClause);
		return queryByCriteria(criteria);
	}

	@Override
	public List<UserAddress> queryByCriteria(UserAddressCriteria criteria) {
		return userAddressMapper.selectByExample(criteria);
	}
	
	@Override
	public int countByCriteria(UserAddressCriteria criteria) {
		return userAddressMapper.countByExample(criteria);
	}

	public UserAddressMapper getUserAddressMapper() {
		return userAddressMapper;
	}

	public void setUserAddressMapper(UserAddressMapper userAddressMapper) {
		this.userAddressMapper = userAddressMapper;
	}

	

}