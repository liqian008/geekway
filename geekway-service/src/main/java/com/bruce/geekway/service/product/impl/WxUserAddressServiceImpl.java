package com.bruce.geekway.service.product.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bruce.geekway.dao.mapper.WxUserAddressMapper;
import com.bruce.geekway.model.WxUserAddress;
import com.bruce.geekway.model.WxUserAddressCriteria;
import com.bruce.geekway.service.product.IWxUserAddressService;

@Service
public class WxUserAddressServiceImpl implements IWxUserAddressService {

	@Autowired
	private WxUserAddressMapper wxUserAddressMapper;

	@Override
	public int save(WxUserAddress t) {
		return wxUserAddressMapper.insertSelective(t);
	}

	@Override
	public int updateById(WxUserAddress t) {
		return wxUserAddressMapper.updateByPrimaryKeySelective(t);
	}

	@Override
	public int updateByCriteria(WxUserAddress t, WxUserAddressCriteria criteria) {
		return wxUserAddressMapper.updateByExampleSelective(t, criteria);
	}

	@Override
	public int deleteById(Long id) {
		return wxUserAddressMapper.deleteByPrimaryKey(id);
	}

	@Override
	public int deleteByCriteria(WxUserAddressCriteria criteria) {
		return wxUserAddressMapper.deleteByExample(criteria);
	}

	@Override
	public WxUserAddress loadById(Long id) {
		return wxUserAddressMapper.selectByPrimaryKey(id);
	}

	@Override
	public List<WxUserAddress> queryAll() {
		return queryAll(null);
	}

	@Override
	public List<WxUserAddress> queryAll(String orderByClause) {
		WxUserAddressCriteria criteria = new WxUserAddressCriteria();
		criteria.createCriteria();
		criteria.setOrderByClause(orderByClause);
		return queryByCriteria(criteria);
	}

	@Override
	public List<WxUserAddress> queryByCriteria(WxUserAddressCriteria criteria) {
		return wxUserAddressMapper.selectByExample(criteria);
	}
	
	@Override
	public int countByCriteria(WxUserAddressCriteria criteria) {
		return wxUserAddressMapper.countByExample(criteria);
	}

	public WxUserAddressMapper getWxUserAddressMapper() {
		return wxUserAddressMapper;
	}

	public void setWxUserAddressMapper(WxUserAddressMapper wxPayUserAddressMapper) {
		this.wxUserAddressMapper = wxPayUserAddressMapper;
	}

}