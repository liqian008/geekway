package com.bruce.geekway.service.product.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bruce.geekway.dao.mapper.WxProductOrderMapper;
import com.bruce.geekway.model.WxProductOrder;
import com.bruce.geekway.model.WxProductOrderCriteria;
import com.bruce.geekway.service.product.IWxProductOrderService;

@Service
public class WxProductOrderServiceImpl implements IWxProductOrderService {

	@Autowired
	private WxProductOrderMapper wxProductOrderMapper;

	@Override
	public int save(WxProductOrder t) {
		return wxProductOrderMapper.insertSelective(t);
	}

	@Override
	public int updateById(WxProductOrder t) {
		return wxProductOrderMapper.updateByPrimaryKeySelective(t);
	}

	@Override
	public int updateByCriteria(WxProductOrder t, WxProductOrderCriteria criteria) {
		return wxProductOrderMapper.updateByExampleSelective(t, criteria);
	}

	@Override
	public int deleteById(Long id) {
		return wxProductOrderMapper.deleteByPrimaryKey(id);
	}

	@Override
	public int deleteByCriteria(WxProductOrderCriteria criteria) {
		return wxProductOrderMapper.deleteByExample(criteria);
	}

	@Override
	public WxProductOrder loadById(Long id) {
		return wxProductOrderMapper.selectByPrimaryKey(id);
	}

	@Override
	public List<WxProductOrder> queryAll() {
		return queryAll(null);
	}

	@Override
	public List<WxProductOrder> queryAll(String orderByClause) {
		WxProductOrderCriteria criteria = new WxProductOrderCriteria();
		criteria.createCriteria();
		criteria.setOrderByClause(orderByClause);
		return queryByCriteria(criteria);
	}

	@Override
	public List<WxProductOrder> queryByCriteria(WxProductOrderCriteria criteria) {
		return wxProductOrderMapper.selectByExample(criteria);
	}
	
	@Override
	public List<WxProductOrder> fallLoadUserOrderList(String userOpenId, long orderTailId, int limit) {
		WxProductOrderCriteria criteria = new WxProductOrderCriteria();
		WxProductOrderCriteria.Criteria subCriteria =  criteria.createCriteria();
		subCriteria.andUserOpenIdEqualTo(userOpenId); 
		if(orderTailId>0){
			subCriteria.andIdLessThan(orderTailId);
		}
		criteria.setLimitRows(limit);
		criteria.setOrderByClause(" id desc");
		return queryByCriteria(criteria);
	}
	
	
	@Override
	public int changeOrderStatus(String outTradeNo, short status) {
		
	}
	
	
	public WxProductOrderMapper getWxProductOrderMapper() {
		return wxProductOrderMapper;
	}

	public void setWxProductOrderMapper(WxProductOrderMapper wxPayProductOrderMapper) {
		this.wxProductOrderMapper = wxPayProductOrderMapper;
	}

	

	
}