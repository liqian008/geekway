package com.bruce.geekway.service.product.impl;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bruce.foundation.model.paging.PagingResult;
import com.bruce.geekway.dao.mapper.WxProductTagRelationMapper;
import com.bruce.geekway.model.WxProductTagRelation;
import com.bruce.geekway.model.WxProductTagRelationCriteria;
import com.bruce.geekway.service.product.IWxProductTagRelationService;

@Service
public class WxProductTagRelationServiceImpl implements IWxProductTagRelationService, InitializingBean {

	@Autowired
	private WxProductTagRelationMapper wxProductTagRelationMapper;

	@Override
	public int save(WxProductTagRelation t) {
		return wxProductTagRelationMapper.insertSelective(t);
	}

	@Override
	public int updateById(WxProductTagRelation t) {
		return wxProductTagRelationMapper.updateByPrimaryKeySelective(t);
	}

	@Override
	public int updateByCriteria(WxProductTagRelation t,
			WxProductTagRelationCriteria criteria) {
		return wxProductTagRelationMapper.updateByExampleSelective(t, criteria);
	}

	@Override
	public int deleteById(Long id) {
		return wxProductTagRelationMapper.deleteByPrimaryKey(id);
	}

	@Override
	public int deleteByCriteria(WxProductTagRelationCriteria criteria) {
		return wxProductTagRelationMapper.deleteByExample(criteria);
	}

	@Override
	public WxProductTagRelation loadById(Long id) {
		return wxProductTagRelationMapper.selectByPrimaryKey(id);
	}

	@Override
	public List<WxProductTagRelation> queryAll() {
		return queryAll(null);
	}

	@Override
	public List<WxProductTagRelation> queryAll(String orderByClause) {
		WxProductTagRelationCriteria criteria = new WxProductTagRelationCriteria();
		criteria.createCriteria();
		criteria.setOrderByClause(orderByClause);
		return queryByCriteria(criteria);
	}

	@Override
	public List<WxProductTagRelation> queryByCriteria(WxProductTagRelationCriteria criteria) {
		return wxProductTagRelationMapper.selectByExample(criteria);
	}
	
	@Override
	public int countByCriteria(WxProductTagRelationCriteria criteria) {
		return wxProductTagRelationMapper.countByExample(criteria);
	}
	
	@Override
	public int topProduct(int tagId, int productId) {
		WxProductTagRelationCriteria criteria = new WxProductTagRelationCriteria();
		criteria.createCriteria().andProductTagIdEqualTo(tagId).andProductIdEqualTo(productId);
		
		WxProductTagRelation tagProduct = new WxProductTagRelation();
		tagProduct.setTopTime(new Date());
		
		return wxProductTagRelationMapper.updateByExampleSelective(tagProduct, criteria);
	}

	
	@Override
	public int delete(int tagId, int productId) {
		WxProductTagRelationCriteria criteria = new WxProductTagRelationCriteria();
		criteria.createCriteria().andProductTagIdEqualTo(tagId).andProductIdEqualTo(productId);
		return deleteByCriteria(criteria);
	}
	

	@Override
	public int deleteByTagId(int tagId) {
		WxProductTagRelationCriteria criteria = new WxProductTagRelationCriteria();
		criteria.createCriteria().andProductTagIdEqualTo(tagId);
		return deleteByCriteria(criteria);
	}
	
	@Override
	public int deleteByProductId(int productId) {
		WxProductTagRelationCriteria criteria = new WxProductTagRelationCriteria();
		criteria.createCriteria().andProductIdEqualTo(productId);
		return deleteByCriteria(criteria);
	}
	
	@Override
	public List<WxProductTagRelation> fallloadByCriteria(int pageSize,
			WxProductTagRelationCriteria criteria) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public PagingResult<WxProductTagRelation> pagingByCriteria(int pageNo,
			int pageSize, WxProductTagRelationCriteria criteria) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void afterPropertiesSet() throws Exception {

	}

	public WxProductTagRelationMapper getWxProductTagRelationMapper() {
		return wxProductTagRelationMapper;
	}

	public void setWxProductTagRelationMapper(
			WxProductTagRelationMapper wxProductTagRelationMapper) {
		this.wxProductTagRelationMapper = wxProductTagRelationMapper;
	}

	
	

}