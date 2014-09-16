package com.bruce.geekway.service.product.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bruce.geekway.dao.mapper.WxProductSkuRelationMapper;
import com.bruce.geekway.model.WxProductSkuRelation;
import com.bruce.geekway.model.WxProductSkuRelationCriteria;
import com.bruce.geekway.service.product.IWxProductSkuRelationService;

/**
 * 处理product与skuPropValue关联关系
 * @author liqian
 *
 */
@Service
public class WxProductSkuRelationServiceImpl implements IWxProductSkuRelationService{
	
	
	@Autowired
	private WxProductSkuRelationMapper wxProductSkuRelationMapper;

	@Override
	public int save(WxProductSkuRelation t) {
		return wxProductSkuRelationMapper.insertSelective(t);
	}

	@Override
	public int updateById(WxProductSkuRelation t) {
		return wxProductSkuRelationMapper.updateByPrimaryKeySelective(t);
	}

	@Override
	public int updateByCriteria(WxProductSkuRelation t, WxProductSkuRelationCriteria criteria) {
		return wxProductSkuRelationMapper.updateByExampleSelective(t, criteria);
	}

	@Override
	public int deleteById(Long id) {
		return wxProductSkuRelationMapper.deleteByPrimaryKey(id);
	}

	@Override
	public int deleteByCriteria(WxProductSkuRelationCriteria criteria) {
		return wxProductSkuRelationMapper.deleteByExample(criteria);
	}

	@Override
	public WxProductSkuRelation loadById(Long id) {
		return wxProductSkuRelationMapper.selectByPrimaryKey(id);
	}

	@Override
	public List<WxProductSkuRelation> queryAll() {
		return queryAll(null);
	}

	@Override
	public List<WxProductSkuRelation> queryAll(String orderByClause) {
		WxProductSkuRelationCriteria criteria = new WxProductSkuRelationCriteria();
		criteria.createCriteria();
		criteria.setOrderByClause(orderByClause);
		return queryByCriteria(criteria);
	}

	@Override
	public List<WxProductSkuRelation> queryByCriteria(WxProductSkuRelationCriteria criteria) {
		return wxProductSkuRelationMapper.selectByExample(criteria);
	}

	@Override
	public int queryCountBySkuPropValueId(int skuPropValueId) {
		WxProductSkuRelationCriteria criteria = new WxProductSkuRelationCriteria();
		criteria.createCriteria().andSkuPropValueIdEqualTo(skuPropValueId);
		return wxProductSkuRelationMapper.countByExample(criteria);
	}
	
}