package com.bruce.geekway.service.pay.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import com.bruce.geekway.dao.mapper.WxPayComplaintMapper;
import com.bruce.geekway.model.WxPayComplaint;
import com.bruce.geekway.model.WxPayComplaintCriteria;
import com.bruce.geekway.service.pay.IWxPayComplaintService;

public class WxPayComplaintServiceImpl implements IWxPayComplaintService {

	@Autowired
	private WxPayComplaintMapper wxPayComplaintMapper;

	@Override
	public int save(WxPayComplaint t) {
		return wxPayComplaintMapper.insertSelective(t);
	}

	@Override
	public int updateById(WxPayComplaint t) {
		return wxPayComplaintMapper.updateByPrimaryKeySelective(t);
	}

	@Override
	public int updateByCriteria(WxPayComplaint t, WxPayComplaintCriteria criteria) {
		return wxPayComplaintMapper.updateByExampleSelective(t, criteria);
	}

	@Override
	public int deleteById(Integer id) {
		return wxPayComplaintMapper.deleteByPrimaryKey(id);
	}

	@Override
	public int deleteByCriteria(WxPayComplaintCriteria criteria) {
		return wxPayComplaintMapper.deleteByExample(criteria);
	}

	@Override
	public WxPayComplaint loadById(Integer id) {
		return wxPayComplaintMapper.selectByPrimaryKey(id);
	}

	@Override
	public List<WxPayComplaint> queryAll() {
		return queryAll(null);
	}

	@Override
	public List<WxPayComplaint> queryAll(String orderByClause) {
		WxPayComplaintCriteria criteria = new WxPayComplaintCriteria();
		criteria.createCriteria();
		criteria.setOrderByClause(orderByClause);
		return queryByCriteria(criteria);
	}

	@Override
	public List<WxPayComplaint> queryByCriteria(WxPayComplaintCriteria criteria) {
		return wxPayComplaintMapper.selectByExample(criteria);
	}

}