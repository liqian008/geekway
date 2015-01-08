
package com.bruce.geekway.service.product.impl;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import com.bruce.foundation.model.paging.PagingResult;
import com.bruce.geekway.constants.ConstConfig;
import com.bruce.geekway.dao.mapper.WxProductMapper;
import com.bruce.geekway.model.WxProduct;
import com.bruce.geekway.model.WxProductCriteria;
import com.bruce.geekway.service.product.IWxProductService;
import com.bruce.geekway.service.product.IWxProductSkuService;

@Service
public class WxProductServiceImpl implements IWxProductService {

	private static final Logger logger = LoggerFactory.getLogger(WxProductSkuServiceImpl.class);
	
	@Autowired
	private WxProductMapper wxProductMapper;
	@Autowired
	private IWxProductSkuService wxProductSkuService;
	

	@Override
	public int save(WxProduct t) {
		return wxProductMapper.insertSelective(t);
	}

	@Override
	public int updateById(WxProduct t) {
		return wxProductMapper.updateByPrimaryKeySelective(t);
	}

	@Override
	public int updateByCriteria(WxProduct t, WxProductCriteria criteria) {
		return wxProductMapper.updateByExampleSelective(t, criteria);
	}

	@Override
	public int deleteById(Integer id) {
		return wxProductMapper.deleteByPrimaryKey(id);
	}

	@Override
	public int deleteByCriteria(WxProductCriteria criteria) {
		return wxProductMapper.deleteByExample(criteria);
	}

	@Override
	public WxProduct loadById(Integer id) {
		return wxProductMapper.selectByPrimaryKey(id);
	}

	@Override
	public List<WxProduct> queryAll() {
		return queryAll(null);
	}

	@Override
	public List<WxProduct> queryAll(String orderByClause) {
		WxProductCriteria criteria = new WxProductCriteria();
		criteria.createCriteria();
		criteria.setOrderByClause(orderByClause);
		return queryByCriteria(criteria);
	}

	@Override
	public List<WxProduct> queryByCriteria(WxProductCriteria criteria) {
		return wxProductMapper.selectByExample(criteria);
	}
	
	@Override
	public int countByCriteria(WxProductCriteria criteria) {
		return wxProductMapper.countByExample(criteria);
	}
	

	@Override
	public List<WxProduct> fallloadByCriteria(int pageSize,
			WxProductCriteria criteria) {
		return null;
	}

	@Override
	public PagingResult<WxProduct> pagingByCriteria(int pageNo, int pageSize,
			WxProductCriteria criteria) {
		pageNo = pageNo<=0?1:pageNo;//确保pageNo合法
		pageSize = pageNo<=0?ConstConfig.PAGE_SIZE_DEFAULT:pageSize;//确保pageSize合法
		int offset = (pageNo-1)*pageSize;
		
		//构造查询条件
		if(criteria==null){
			criteria = new WxProductCriteria();
		}
		
		criteria.setLimitOffset(offset);
		criteria.setLimitRows(pageSize);
		
		int count = wxProductMapper.countByExample(criteria);
		List<WxProduct> dataList = wxProductMapper.selectByExample(criteria);
		//返回分页数据
		return new PagingResult<WxProduct>(pageNo, pageSize, count, dataList);
	}
	
	

	@Override
	public List<WxProduct> queryAvailableList() {
		WxProductCriteria criteria = new WxProductCriteria();
		criteria.createCriteria().andStatusEqualTo((short) 1);
		return wxProductMapper.selectByExample(criteria);
	}
	
	
	@Override
	public PagingResult<WxProduct> pagingTagProductsByCriteria(int tagId, int pageNo, int pageSize,
			WxProductCriteria criteria) {
		pageNo = pageNo<=0?1:pageNo;//确保pageNo合法
		pageSize = pageNo<=0?ConstConfig.PAGE_SIZE_DEFAULT:pageSize;//确保pageSize合法
		int offset = (pageNo-1)*pageSize;
		
		//构造查询条件
		if(criteria==null){
			criteria = new WxProductCriteria();
		}
		criteria.setLimitOffset(offset);
		criteria.setLimitRows(pageSize);
		
		int count = wxProductMapper.countProductsByTagId(criteria, tagId);
		List<WxProduct> dataList =  wxProductMapper.queryProductsByTagId(criteria, tagId);
		//返回分页数据
		return new PagingResult<WxProduct>(pageNo, pageSize, count, dataList);
	}
	

	@Override
	public PagingResult<WxProduct> pagingTagOutProductsByCriteria(int tagId, int pageNo, int pageSize,
			WxProductCriteria criteria) {
		pageNo = pageNo<=0?1:pageNo;//确保pageNo合法
		pageSize = pageNo<=0?ConstConfig.PAGE_SIZE_DEFAULT:pageSize;//确保pageSize合法
		int offset = (pageNo-1)*pageSize;
		
		//构造查询条件
		if(criteria==null){
			criteria = new WxProductCriteria();
		}
		criteria.setLimitOffset(offset);
		criteria.setLimitRows(pageSize);
		
		int count = wxProductMapper.countProductsOutByTagId(criteria, tagId);
		List<WxProduct> dataList =  wxProductMapper.queryProductsOutByTagId(criteria, tagId);
		//返回分页数据
		return new PagingResult<WxProduct>(pageNo, pageSize, count, dataList);
	}
	
	@Override
	public List<WxProduct> queryProductsByCategoryId(int categoryId, int pageNo, int pageSize) {
		pageNo = pageNo<=0?1:pageNo;//确保pageNo合法
		pageSize = pageNo<=0?ConstConfig.PAGE_SIZE_DEFAULT:pageSize;//确保pageSize合法
		int offset = (pageNo-1)*pageSize;
		
		WxProductCriteria criteria = new WxProductCriteria();
		criteria.setLimitOffset(offset);
		criteria.setLimitRows(pageSize);
		
		return wxProductMapper.queryProductsByCategoryId(criteria, categoryId);
	}
	
	@Override
	@Cacheable(value="storageCache", key="'categoryId-'+#categoryId+'-pageNo-'+#pageNo+'-pageSize-'+#pageSize")
	public List<WxProduct> queryCachedProductsByCategoryId(int categoryId, int pageNo, int pageSize) {
		logger.debug("fallload productsByTag from db. [tagId:"+categoryId+", pageNo:"+pageNo+", pageSize:"+pageSize+"]");
		return queryProductsByTagId(categoryId, pageNo, pageSize);
	}
	
	@Override
	public List<WxProduct> queryProductsByTagId(int tagId, int pageNo, int pageSize) {
		pageNo = pageNo<=0?1:pageNo;//确保pageNo合法
		pageSize = pageNo<=0?ConstConfig.PAGE_SIZE_DEFAULT:pageSize;//确保pageSize合法
		int offset = (pageNo-1)*pageSize;
		
		WxProductCriteria criteria = new WxProductCriteria();
		criteria.setLimitOffset(offset);
		criteria.setLimitRows(pageSize);
		
		return wxProductMapper.queryProductsByTagId(criteria, tagId);
	}
	
	@Override
	@Cacheable(value="storageCache", key="'tagId-'+#tagId+'-pageNo-'+#pageNo+'-pageSize-'+#pageSize")
	public List<WxProduct> queryCachedProductsByTagId(int tagId, int pageNo, int pageSize) {
		logger.debug("fallload productsByTag from db. [tagId:"+tagId+", pageNo:"+pageNo+", pageSize:"+pageSize+"]");
		//TODO converPageNo
		return queryProductsByTagId(tagId, pageNo, pageSize);
	}
	
	
//	@Override
//	public List<WxProduct> fallLoadProductsByTag(int tagId, int tailId, int limit) {
//		return wxProductMapper.fallLoadProductsByTag(tagId, tailId, limit);
//	}
//	
//	@Override
//	@Cacheable(value="storageCache", key="'tagId-'+#tagId+'-tailId-'+#tailId+'-limit-'+#limit")
//	public List<WxProduct> fallLoadCachedProductsByTag(int tagId, int tailId, int limit) {
//		logger.debug("fallload productsByTag from db. [tagId:"+tagId+", tailId:"+tailId+", limit:"+limit+"]");
//		return fallLoadProductsByTag(tagId, tailId, limit);
//	}
	
	
	@Override
	@Cacheable(value="storageCache", key="'product-'+#id")
	public WxProduct loadCachedById(Integer id) {
		logger.debug("load product from db. [productId:"+id+"]");
		return loadById(id);
	}

	
//	@Override
//	public List<WxProduct> queryProductsByTagId(int tagId, int limitOffset, int limitRows){
//		return wxProductMapper.queryProductsByTagId(tagId, limitOffset, limitRows); 
//	}
//
//	@Override
//	public List<WxProduct> queryProductsOutTagId(int tagId, int limitOffset, int limitRows){ 
//		return wxProductMapper.queryProductsOutTagId(tagId, limitOffset, limitRows); 
//	}

	

//	@Transactional(propagation=Propagation.REQUIRED)
//	@Override
//	public int txTest() {
//		Date currentTime = new Date();
//		WxProduct product = new WxProduct();
//		product.setId(1);
//		product.setCreateTime(currentTime);
//		save(product);
//		
//		WxProductSku sku = new WxProductSku();
//		sku.setId(1);
//		sku.setProductId(1);
//		sku.setCreateTime(currentTime);
//		wxProductSkuService.save(sku);
//		
//		return 0;
//	}

	
	public WxProductMapper getWxProductMapper() {
		return wxProductMapper;
	}

	public void setWxProductMapper(WxProductMapper wxPayProductMapper) {
		this.wxProductMapper = wxPayProductMapper;
	}

	public IWxProductSkuService getWxProductSkuService() {
		return wxProductSkuService;
	}

	public void setWxProductSkuService(IWxProductSkuService wxProductSkuService) {
		this.wxProductSkuService = wxProductSkuService;
	}

	
	
}