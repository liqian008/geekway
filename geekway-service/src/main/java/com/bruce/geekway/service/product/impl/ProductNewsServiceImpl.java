package com.bruce.geekway.service.product.impl;

import java.util.List;

import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;

import com.bruce.foundation.model.paging.PagingResult;
import com.bruce.geekway.constants.ConstConfig;
import com.bruce.geekway.dao.mapper.ProductNewsMapper;
import com.bruce.geekway.model.ProductNews;
import com.bruce.geekway.model.ProductNewsCriteria;
import com.bruce.geekway.service.product.IProductNewsService;

public class ProductNewsServiceImpl implements IProductNewsService, InitializingBean {

	@Autowired
	private ProductNewsMapper productNewsMapper;

	@Override
	public int save(ProductNews t) {
		return productNewsMapper.insertSelective(t);
	}

	@Override
	public int updateById(ProductNews t) {
		return productNewsMapper.updateByPrimaryKeySelective(t);
	}

	@Override
	public int updateByCriteria(ProductNews t, ProductNewsCriteria criteria) {
		return productNewsMapper.updateByExampleSelective(t, criteria);
	}

	@Override
	public int deleteById(Integer id) {
		return productNewsMapper.deleteByPrimaryKey(id);
	}

	@Override
	public int deleteByCriteria(ProductNewsCriteria criteria) {
		return productNewsMapper.deleteByExample(criteria);
	}

	@Override
	public ProductNews loadById(Integer id) {
		return productNewsMapper.selectByPrimaryKey(id);
	}

	@Override
	public List<ProductNews> queryAll() {
		return queryAll(null);
	}

	@Override
	public List<ProductNews> queryAll(String orderByClause) {
		ProductNewsCriteria criteria = new ProductNewsCriteria();
		criteria.createCriteria();
		criteria.setOrderByClause(orderByClause);
		return queryByCriteria(criteria);
	}

	@Override
	public List<ProductNews> queryByCriteria(ProductNewsCriteria criteria) {
		return productNewsMapper.selectByExample(criteria);
	}
	

	@Override
	public int countByCriteria(ProductNewsCriteria criteria) {
		return productNewsMapper.countByExample(criteria);
	}
	

	@Override
	public List<ProductNews> fallloadByCriteria(int pageSize, ProductNewsCriteria criteria) {
		return null;
	}

	@Override
	public PagingResult<ProductNews> pagingByCriteria(int pageNo, int pageSize, ProductNewsCriteria criteria) {
		pageNo = pageNo<=0?1:pageNo;//确保pageNo合法
		pageSize = pageNo<=0?ConstConfig.PAGE_SIZE_DEFAULT:pageSize;//确保pageSize合法
		int offset = (pageNo-1)*pageSize;
		
		//构造查询条件
		if(criteria==null){
			criteria = new ProductNewsCriteria();
		}
		
		criteria.setLimitOffset(offset);
		criteria.setLimitRows(pageSize);
		
		int count = productNewsMapper.countByExample(criteria);
		List<ProductNews> dataList = productNewsMapper.selectByExample(criteria);
		//返回分页数据
		return new PagingResult<ProductNews>(pageNo, pageSize, count, dataList);
	}
	
	

	@Override
	public void afterPropertiesSet() throws Exception {

	}

	public ProductNewsMapper getProductNewsMapper() {
		return productNewsMapper;
	}

	public void setProductNewsMapper(ProductNewsMapper productNewsMapper) {
		this.productNewsMapper = productNewsMapper;
	}
	

}