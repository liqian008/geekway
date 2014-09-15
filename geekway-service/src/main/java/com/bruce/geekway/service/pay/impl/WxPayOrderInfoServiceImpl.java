package com.bruce.geekway.service.pay.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import com.bruce.geekway.dao.mapper.WxPayOrderInfoMapper;
import com.bruce.geekway.model.WxPayOrderInfo;
import com.bruce.geekway.model.WxPayOrderInfoCriteria;
import com.bruce.geekway.service.pay.IWxPayOrderInfoService;

public class WxPayOrderInfoServiceImpl implements IWxPayOrderInfoService {

	@Autowired
	private WxPayOrderInfoMapper wxPayOrderInfoMapper;

	@Override
	public int save(WxPayOrderInfo t) {
		return wxPayOrderInfoMapper.insertSelective(t);
	}

	@Override
	public int updateById(WxPayOrderInfo t) {
		return wxPayOrderInfoMapper.updateByPrimaryKeySelective(t);
	}

	@Override
	public int updateByCriteria(WxPayOrderInfo t, WxPayOrderInfoCriteria criteria) {
		return wxPayOrderInfoMapper.updateByExampleSelective(t, criteria);
	}

	@Override
	public int deleteById(Integer id) {
		return wxPayOrderInfoMapper.deleteByPrimaryKey(id);
	}

	@Override
	public int deleteByCriteria(WxPayOrderInfoCriteria criteria) {
		return wxPayOrderInfoMapper.deleteByExample(criteria);
	}

	@Override
	public WxPayOrderInfo loadById(Integer id) {
		return wxPayOrderInfoMapper.selectByPrimaryKey(id);
	}

	@Override
	public List<WxPayOrderInfo> queryAll() {
		return queryAll(null);
	}

	@Override
	public List<WxPayOrderInfo> queryAll(String orderByClause) {
		WxPayOrderInfoCriteria criteria = new WxPayOrderInfoCriteria();
		criteria.createCriteria();
		criteria.setOrderByClause(orderByClause);
		return queryByCriteria(criteria);
	}

	@Override
	public List<WxPayOrderInfo> queryByCriteria(WxPayOrderInfoCriteria criteria) {
		return wxPayOrderInfoMapper.selectByExample(criteria);
	}

	public WxPayOrderInfoMapper getWxPayOrderInfoMapper() {
		return wxPayOrderInfoMapper;
	}

	public void setWxPayOrderInfoMapper(WxPayOrderInfoMapper wxPayOrderInfoMapper) {
		this.wxPayOrderInfoMapper = wxPayOrderInfoMapper;
	}
	
	
	
	
}