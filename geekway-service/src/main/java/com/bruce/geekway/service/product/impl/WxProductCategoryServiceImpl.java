package com.bruce.geekway.service.product.impl;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import com.bruce.foundation.model.paging.PagingResult;
import com.bruce.geekway.constants.ConstConfig;
import com.bruce.geekway.dao.mapper.WxProductCategoryMapper;
import com.bruce.geekway.model.WxProductCategory;
import com.bruce.geekway.model.WxProductCategoryCriteria;
import com.bruce.geekway.service.product.IWxProductCategoryService;

@Service
public class WxProductCategoryServiceImpl implements IWxProductCategoryService {
	
	private static final Logger logger = LoggerFactory.getLogger(WxProductSkuServiceImpl.class);

	@Autowired
	private WxProductCategoryMapper wxProductCategoryMapper;

	
	
	@Override
	public int save(WxProductCategory t) {
		return wxProductCategoryMapper.insertSelective(t);
	}

	@Override
	public int updateById(WxProductCategory t) {
		return wxProductCategoryMapper.updateByPrimaryKeySelective(t);
	}

	@Override
	public int updateByCriteria(WxProductCategory t, WxProductCategoryCriteria criteria) {
		return wxProductCategoryMapper.updateByExampleSelective(t, criteria);
	}

	@Override
	public int deleteById(Integer id) {
		return wxProductCategoryMapper.deleteByPrimaryKey(id);
	}

	@Override
	public int deleteByCriteria(WxProductCategoryCriteria criteria) {
		return wxProductCategoryMapper.deleteByExample(criteria);
	}

	@Override
	public WxProductCategory loadById(Integer id) {
		return wxProductCategoryMapper.selectByPrimaryKey(id);
	}

	@Override
	public List<WxProductCategory> queryAll() {
		return queryAll(null);
	}

	@Override
	public List<WxProductCategory> queryAll(String orderByClause) {
		WxProductCategoryCriteria criteria = new WxProductCategoryCriteria();
		criteria.createCriteria();
		criteria.setOrderByClause(orderByClause);
		return queryByCriteria(criteria);
	}

	@Override
	public List<WxProductCategory> queryByCriteria(WxProductCategoryCriteria criteria) {
		return wxProductCategoryMapper.selectByExample(criteria);
	}
	
	@Override
	public int countByCriteria(WxProductCategoryCriteria criteria) {
		return wxProductCategoryMapper.countByExample(criteria);
	}
	
	@Override
	public List<WxProductCategory> fallloadByCriteria(int pageSize,
			WxProductCategoryCriteria criteria) {
		return null;
	}

	@Override
	public PagingResult<WxProductCategory> pagingByCriteria(int pageNo, int pageSize, WxProductCategoryCriteria criteria) {
		pageNo = pageNo <= 0 ? 1 : pageNo;// 确保pageNo合法
		pageSize = pageNo <= 0 ? ConstConfig.PAGE_SIZE_DEFAULT : pageSize;// 确保pageSize合法
		int offset = (pageNo - 1) * pageSize;

		// 构造查询条件
		if (criteria == null) {
			criteria = new WxProductCategoryCriteria();
		}

		criteria.setLimitOffset(offset);
		criteria.setLimitRows(pageSize);

		int count = wxProductCategoryMapper.countByExample(criteria);
		List<WxProductCategory> dataList = wxProductCategoryMapper.selectByExample(criteria);
		// 返回分页数据
		return new PagingResult<WxProductCategory>(pageNo, pageSize, count, dataList);
	}
	
	@Override
	@Cacheable(value="storageCache", key="'category-'+#id")
	public WxProductCategory loadCachedById(Integer id) {
		logger.debug("load category from db. [categoryId:"+id+"]");
		return loadById(id);
	}
	

	public WxProductCategoryMapper getWxProductCategoryMapper() {
		return wxProductCategoryMapper;
	}

	public void setWxProductCategoryMapper(WxProductCategoryMapper wxPayProductCategoryMapper) {
		this.wxProductCategoryMapper = wxPayProductCategoryMapper;
	}

}