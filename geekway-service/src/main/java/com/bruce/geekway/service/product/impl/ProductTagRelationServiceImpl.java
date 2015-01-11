package com.bruce.geekway.service.product.impl;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;

import com.bruce.foundation.model.paging.PagingResult;
import com.bruce.geekway.dao.mapper.ProductTagRelationMapper;
import com.bruce.geekway.model.ProductTagRelation;
import com.bruce.geekway.model.ProductTagRelationCriteria;
import com.bruce.geekway.service.product.IProductTagRelationService;

public class ProductTagRelationServiceImpl implements IProductTagRelationService, InitializingBean {

	@Autowired
	private ProductTagRelationMapper productTagRelationMapper;

	@Override
	public int save(ProductTagRelation t) {
		return productTagRelationMapper.insertSelective(t);
	}

	@Override
	public int updateById(ProductTagRelation t) {
		return productTagRelationMapper.updateByPrimaryKeySelective(t);
	}

	@Override
	public int updateByCriteria(ProductTagRelation t,
			ProductTagRelationCriteria criteria) {
		return productTagRelationMapper.updateByExampleSelective(t, criteria);
	}

	@Override
	public int deleteById(Long id) {
		return productTagRelationMapper.deleteByPrimaryKey(id);
	}

	@Override
	public int deleteByCriteria(ProductTagRelationCriteria criteria) {
		return productTagRelationMapper.deleteByExample(criteria);
	}

	@Override
	public ProductTagRelation loadById(Long id) {
		return productTagRelationMapper.selectByPrimaryKey(id);
	}

	@Override
	public List<ProductTagRelation> queryAll() {
		return queryAll(null);
	}

	@Override
	public List<ProductTagRelation> queryAll(String orderByClause) {
		ProductTagRelationCriteria criteria = new ProductTagRelationCriteria();
		criteria.createCriteria();
		criteria.setOrderByClause(orderByClause);
		return queryByCriteria(criteria);
	}

	@Override
	public List<ProductTagRelation> queryByCriteria(ProductTagRelationCriteria criteria) {
		return productTagRelationMapper.selectByExample(criteria);
	}
	
	@Override
	public int countByCriteria(ProductTagRelationCriteria criteria) {
		return productTagRelationMapper.countByExample(criteria);
	}
	
	@Override
	public int topProduct(int tagId, int productId) {
		ProductTagRelationCriteria criteria = new ProductTagRelationCriteria();
		criteria.createCriteria().andProductTagIdEqualTo(tagId).andProductIdEqualTo(productId);
		
		ProductTagRelation tagProduct = new ProductTagRelation();
		tagProduct.setTopTime(new Date());
		
		return productTagRelationMapper.updateByExampleSelective(tagProduct, criteria);
	}

	
	@Override
	public int delete(int tagId, int productId) {
		ProductTagRelationCriteria criteria = new ProductTagRelationCriteria();
		criteria.createCriteria().andProductTagIdEqualTo(tagId).andProductIdEqualTo(productId);
		return deleteByCriteria(criteria);
	}
	

	@Override
	public int deleteByTagId(int tagId) {
		ProductTagRelationCriteria criteria = new ProductTagRelationCriteria();
		criteria.createCriteria().andProductTagIdEqualTo(tagId);
		return deleteByCriteria(criteria);
	}
	
	@Override
	public int deleteByProductId(int productId) {
		ProductTagRelationCriteria criteria = new ProductTagRelationCriteria();
		criteria.createCriteria().andProductIdEqualTo(productId);
		return deleteByCriteria(criteria);
	}
	
	@Override
	public List<ProductTagRelation> fallloadByCriteria(int pageSize,
			ProductTagRelationCriteria criteria) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public PagingResult<ProductTagRelation> pagingByCriteria(int pageNo,
			int pageSize, ProductTagRelationCriteria criteria) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void afterPropertiesSet() throws Exception {

	}

	public ProductTagRelationMapper getProductTagRelationMapper() {
		return productTagRelationMapper;
	}

	public void setProductTagRelationMapper(
			ProductTagRelationMapper productTagRelationMapper) {
		this.productTagRelationMapper = productTagRelationMapper;
	}



}