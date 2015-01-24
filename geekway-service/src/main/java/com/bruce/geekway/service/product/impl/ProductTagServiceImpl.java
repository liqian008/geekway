package com.bruce.geekway.service.product.impl;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;

import com.bruce.foundation.model.paging.PagingResult;
import com.bruce.geekway.constants.ConstConfig;
import com.bruce.geekway.constants.ConstMemc;
import com.bruce.geekway.dao.mapper.ProductTagMapper;
import com.bruce.geekway.model.ProductTag;
import com.bruce.geekway.model.ProductTagCriteria;
import com.bruce.geekway.service.product.IProductTagService;

public class ProductTagServiceImpl implements IProductTagService {
	
	private static final Logger logger = LoggerFactory.getLogger(ProductSkuServiceImpl.class);
	
	@Autowired
	private ProductTagMapper productTagMapper;

	@Override
	public int save(ProductTag t) {
		return productTagMapper.insertSelective(t);
	}

	@Override
	public int updateById(ProductTag t) {
		return productTagMapper.updateByPrimaryKeySelective(t);
	}

	@Override
	public int updateByCriteria(ProductTag t, ProductTagCriteria criteria) {
		return productTagMapper.updateByExampleSelective(t, criteria);
	}

	@Override
	public int deleteById(Integer id) {
		return productTagMapper.deleteByPrimaryKey(id);
	}

	@Override
	public int deleteByCriteria(ProductTagCriteria criteria) {
		return productTagMapper.deleteByExample(criteria);
	}

	@Override
	public ProductTag loadById(Integer id) {
		return productTagMapper.selectByPrimaryKey(id);
	}

	@Override
	public List<ProductTag> queryAll() {
		return queryAll(null);
	}

	@Override
	public List<ProductTag> queryAll(String orderByClause) {
		ProductTagCriteria criteria = new ProductTagCriteria();
		criteria.createCriteria();
		criteria.setOrderByClause(orderByClause);
		return queryByCriteria(criteria);
	}

	@Override
	public List<ProductTag> queryByCriteria(ProductTagCriteria criteria) {
		return productTagMapper.selectByExample(criteria);
	}
	
	@Override
	public int countByCriteria(ProductTagCriteria criteria) {
		return productTagMapper.countByExample(criteria);
	}

	@Override
	public List<ProductTag> fallloadByCriteria(int pageSize,
			ProductTagCriteria criteria) {
		return null;
	}

	@Override
	public PagingResult<ProductTag> pagingByCriteria(int pageNo,
			int pageSize, ProductTagCriteria criteria) {
		pageNo = pageNo <= 0 ? 1 : pageNo;// 确保pageNo合法
		pageSize = pageNo <= 0 ? ConstConfig.PAGE_SIZE_DEFAULT : pageSize;// 确保pageSize合法
		int offset = (pageNo - 1) * pageSize;

		// 构造查询条件
		if (criteria == null) {
			criteria = new ProductTagCriteria();
		}

		criteria.setLimitOffset(offset);
		criteria.setLimitRows(pageSize);

		int count = productTagMapper.countByExample(criteria);
		List<ProductTag> dataList = productTagMapper.selectByExample(criteria);
		// 返回分页数据
		return new PagingResult<ProductTag>(pageNo, pageSize, count, dataList);
	}
	
	@Override
	@Cacheable(value=ConstMemc.MEMCACHE_CACHE_VALUE, key="'tag-'+#id")
	public ProductTag loadCachedById(Integer id) {
		logger.debug("load tag from db. [tagId:"+id+"]");
		return loadById(id);
	}

	public ProductTagMapper getProductTagMapper() {
		return productTagMapper;
	}

	public void setProductTagMapper(ProductTagMapper productTagMapper) {
		this.productTagMapper = productTagMapper;
	}

	
	

}