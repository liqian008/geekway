package com.bruce.geekway.service.product.impl;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;

import com.bruce.foundation.model.paging.PagingResult;
import com.bruce.geekway.constants.ConstConfig;
import com.bruce.geekway.dao.mapper.ProductCategoryMapper;
import com.bruce.geekway.model.ProductCategory;
import com.bruce.geekway.model.ProductCategoryCriteria;
import com.bruce.geekway.service.product.IProductCategoryService;

public class ProductCategoryServiceImpl implements IProductCategoryService {
	
	private static final Logger logger = LoggerFactory.getLogger(ProductSkuServiceImpl.class);

	@Autowired
	private ProductCategoryMapper productCategoryMapper;

	
	
	@Override
	public int save(ProductCategory t) {
		return productCategoryMapper.insertSelective(t);
	}

	@Override
	public int updateById(ProductCategory t) {
		return productCategoryMapper.updateByPrimaryKeySelective(t);
	}

	@Override
	public int updateByCriteria(ProductCategory t, ProductCategoryCriteria criteria) {
		return productCategoryMapper.updateByExampleSelective(t, criteria);
	}

	@Override
	public int deleteById(Integer id) {
		return productCategoryMapper.deleteByPrimaryKey(id);
	}

	@Override
	public int deleteByCriteria(ProductCategoryCriteria criteria) {
		return productCategoryMapper.deleteByExample(criteria);
	}

	@Override
	public ProductCategory loadById(Integer id) {
		return productCategoryMapper.selectByPrimaryKey(id);
	}

	@Override
	public List<ProductCategory> queryAll() {
		return queryAll(null);
	}

	@Override
	public List<ProductCategory> queryAll(String orderByClause) {
		ProductCategoryCriteria criteria = new ProductCategoryCriteria();
		criteria.createCriteria();
		criteria.setOrderByClause(orderByClause);
		return queryByCriteria(criteria);
	}

	@Override
	public List<ProductCategory> queryByCriteria(ProductCategoryCriteria criteria) {
		return productCategoryMapper.selectByExample(criteria);
	}
	
	@Override
	public int countByCriteria(ProductCategoryCriteria criteria) {
		return productCategoryMapper.countByExample(criteria);
	}
	
	@Override
	public List<ProductCategory> fallloadByCriteria(int pageSize,
			ProductCategoryCriteria criteria) {
		return null;
	}

	@Override
	public PagingResult<ProductCategory> pagingByCriteria(int pageNo, int pageSize, ProductCategoryCriteria criteria) {
		pageNo = pageNo <= 0 ? 1 : pageNo;// 确保pageNo合法
		pageSize = pageNo <= 0 ? ConstConfig.PAGE_SIZE_DEFAULT : pageSize;// 确保pageSize合法
		int offset = (pageNo - 1) * pageSize;

		// 构造查询条件
		if (criteria == null) {
			criteria = new ProductCategoryCriteria();
		}

		criteria.setLimitOffset(offset);
		criteria.setLimitRows(pageSize);

		int count = productCategoryMapper.countByExample(criteria);
		List<ProductCategory> dataList = productCategoryMapper.selectByExample(criteria);
		// 返回分页数据
		return new PagingResult<ProductCategory>(pageNo, pageSize, count, dataList);
	}
	
	@Override
	@Cacheable(value="storageCache", key="'category-'+#id")
	public ProductCategory loadCachedById(Integer id) {
		logger.debug("load category from db. [categoryId:"+id+"]");
		return loadById(id);
	}

	public ProductCategoryMapper getProductCategoryMapper() {
		return productCategoryMapper;
	}

	public void setProductCategoryMapper(ProductCategoryMapper productCategoryMapper) {
		this.productCategoryMapper = productCategoryMapper;
	}
	
	
}