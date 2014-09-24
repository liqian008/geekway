package com.bruce.geekway.service.product.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bruce.geekway.dao.mapper.WxProductOrderItemMapper;
import com.bruce.geekway.model.WxProductOrderItem;
import com.bruce.geekway.model.WxProductOrderItemCriteria;
import com.bruce.geekway.service.product.IWxProductOrderItemService;

@Service
public class WxProductOrderItemServiceImpl implements IWxProductOrderItemService {

	@Autowired
	private WxProductOrderItemMapper wxProductOrderItemMapper;

	@Override
	public int save(WxProductOrderItem t) {
		return wxProductOrderItemMapper.insertSelective(t);
	}

	@Override
	public int updateById(WxProductOrderItem t) {
		return wxProductOrderItemMapper.updateByPrimaryKeySelective(t);
	}

	@Override
	public int updateByCriteria(WxProductOrderItem t, WxProductOrderItemCriteria criteria) {
		return wxProductOrderItemMapper.updateByExampleSelective(t, criteria);
	}

	@Override
	public int deleteById(Long id) {
		return wxProductOrderItemMapper.deleteByPrimaryKey(id);
	}

	@Override
	public int deleteByCriteria(WxProductOrderItemCriteria criteria) {
		return wxProductOrderItemMapper.deleteByExample(criteria);
	}

	@Override
	public WxProductOrderItem loadById(Long id) {
		return wxProductOrderItemMapper.selectByPrimaryKey(id);
	}

	@Override
	public List<WxProductOrderItem> queryAll() {
		return queryAll(null);
	}

	@Override
	public List<WxProductOrderItem> queryAll(String orderByClause) {
		WxProductOrderItemCriteria criteria = new WxProductOrderItemCriteria();
		criteria.createCriteria();
		criteria.setOrderByClause(orderByClause);
		return queryByCriteria(criteria);
	}

	@Override
	public List<WxProductOrderItem> queryByCriteria(WxProductOrderItemCriteria criteria) {
		return wxProductOrderItemMapper.selectByExample(criteria);
	}
	
	@Override
	public List<WxProductOrderItem> queryByTradeNo(String outTradeNo) {
		WxProductOrderItemCriteria criteria = new WxProductOrderItemCriteria();
		criteria.createCriteria().andOutTradeNoEqualTo(outTradeNo);
		return queryByCriteria(criteria);
	}
	
	
	
	public WxProductOrderItemMapper getWxProductOrderItemMapper() {
		return wxProductOrderItemMapper;
	}

	public void setWxProductOrderItemMapper(WxProductOrderItemMapper wxPayProductOrderItemMapper) {
		this.wxProductOrderItemMapper = wxPayProductOrderItemMapper;
	}

	
	
}
