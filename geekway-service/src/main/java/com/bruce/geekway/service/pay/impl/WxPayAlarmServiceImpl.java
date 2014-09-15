package com.bruce.geekway.service.pay.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import com.bruce.geekway.dao.mapper.WxPayAlarmMapper;
import com.bruce.geekway.model.WxPayAlarm;
import com.bruce.geekway.model.WxPayAlarmCriteria;
import com.bruce.geekway.service.pay.IWxPayAlarmService;

public class WxPayAlarmServiceImpl implements IWxPayAlarmService {

	@Autowired
	private WxPayAlarmMapper wxPayAlarmMapper;

	@Override
	public int save(WxPayAlarm t) {
		return wxPayAlarmMapper.insertSelective(t);
	}

	@Override
	public int updateById(WxPayAlarm t) {
		return wxPayAlarmMapper.updateByPrimaryKeySelective(t);
	}

	@Override
	public int updateByCriteria(WxPayAlarm t, WxPayAlarmCriteria criteria) {
		return wxPayAlarmMapper.updateByExampleSelective(t, criteria);
	}

	@Override
	public int deleteById(Integer id) {
		return wxPayAlarmMapper.deleteByPrimaryKey(id);
	}

	@Override
	public int deleteByCriteria(WxPayAlarmCriteria criteria) {
		return wxPayAlarmMapper.deleteByExample(criteria);
	}

	@Override
	public WxPayAlarm loadById(Integer id) {
		return wxPayAlarmMapper.selectByPrimaryKey(id);
	}

	@Override
	public List<WxPayAlarm> queryAll() {
		return queryAll(null);
	}

	@Override
	public List<WxPayAlarm> queryAll(String orderByClause) {
		WxPayAlarmCriteria criteria = new WxPayAlarmCriteria();
		criteria.createCriteria();
		criteria.setOrderByClause(orderByClause);
		return queryByCriteria(criteria);
	}

	@Override
	public List<WxPayAlarm> queryByCriteria(WxPayAlarmCriteria criteria) {
		return wxPayAlarmMapper.selectByExample(criteria);
	}

	public WxPayAlarmMapper getWxPayAlarmMapper() {
		return wxPayAlarmMapper;
	}

	public void setWxPayAlarmMapper(WxPayAlarmMapper wxPayAlarmMapper) {
		this.wxPayAlarmMapper = wxPayAlarmMapper;
	}
	
	
	
	
}