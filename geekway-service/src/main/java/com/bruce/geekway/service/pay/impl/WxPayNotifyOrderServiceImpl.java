package com.bruce.geekway.service.pay.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import com.bruce.geekway.dao.mapper.WxPayNotifyOrderMapper;
import com.bruce.geekway.model.WxPayNotifyOrder;
import com.bruce.geekway.model.WxPayNotifyOrderCriteria;
import com.bruce.geekway.service.pay.IWxPayNotifyOrderService;

public class WxPayNotifyOrderServiceImpl implements IWxPayNotifyOrderService {

	@Autowired
	private WxPayNotifyOrderMapper wxPayNotifyOrderMapper;

	@Override
	public int save(WxPayNotifyOrder t) {
		return wxPayNotifyOrderMapper.insertSelective(t);
	}

	@Override
	public int updateById(WxPayNotifyOrder t) {
		return wxPayNotifyOrderMapper.updateByPrimaryKeySelective(t);
	}

	@Override
	public int updateByCriteria(WxPayNotifyOrder t, WxPayNotifyOrderCriteria criteria) {
		return wxPayNotifyOrderMapper.updateByExampleSelective(t, criteria);
	}

	@Override
	public int deleteById(Integer id) {
		return wxPayNotifyOrderMapper.deleteByPrimaryKey(id);
	}

	@Override
	public int deleteByCriteria(WxPayNotifyOrderCriteria criteria) {
		return wxPayNotifyOrderMapper.deleteByExample(criteria);
	}

	@Override
	public WxPayNotifyOrder loadById(Integer id) {
		return wxPayNotifyOrderMapper.selectByPrimaryKey(id);
	}

	@Override
	public List<WxPayNotifyOrder> queryAll() {
		return queryAll(null);
	}

	@Override
	public List<WxPayNotifyOrder> queryAll(String orderByClause) {
		WxPayNotifyOrderCriteria criteria = new WxPayNotifyOrderCriteria();
		criteria.createCriteria();
		criteria.setOrderByClause(orderByClause);
		return queryByCriteria(criteria);
	}

	@Override
	public List<WxPayNotifyOrder> queryByCriteria(WxPayNotifyOrderCriteria criteria) {
		return wxPayNotifyOrderMapper.selectByExample(criteria);
	}

	public WxPayNotifyOrderMapper getWxPayNotifyOrderMapper() {
		return wxPayNotifyOrderMapper;
	}

	public void setWxPayNotifyOrderMapper(WxPayNotifyOrderMapper wxPayNotifyOrderMapper) {
		this.wxPayNotifyOrderMapper = wxPayNotifyOrderMapper;
	}
	
	
	
	
}