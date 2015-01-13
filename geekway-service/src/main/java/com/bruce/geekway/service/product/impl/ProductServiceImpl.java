
package com.bruce.geekway.service.product.impl;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;

import com.bruce.foundation.model.paging.PagingResult;
import com.bruce.geekway.constants.ConstConfig;
import com.bruce.geekway.constants.ConstMemc;
import com.bruce.geekway.dao.mapper.ProductMapper;
import com.bruce.geekway.model.Product;
import com.bruce.geekway.model.ProductCriteria;
import com.bruce.geekway.service.product.IProductService;
import com.bruce.geekway.service.product.IProductSkuService;

public class ProductServiceImpl implements IProductService {

	private static final Logger logger = LoggerFactory.getLogger(ProductSkuServiceImpl.class);
	
	@Autowired
	private ProductMapper productMapper;
	@Autowired
	private IProductSkuService productSkuService;
	

	@Override
	public int save(Product t) {
		return productMapper.insertSelective(t);
	}

	/**
	 * 更新时同时逐出内存
	 */
	@Override
	@CacheEvict(value=ConstMemc.MEMCACHE_CACHE_VALUE, key="'product-'+#t.id")
	public int updateById(Product t) {
		return productMapper.updateByPrimaryKeySelective(t);
	}

	@Override
	public int updateByCriteria(Product t, ProductCriteria criteria) {
		return productMapper.updateByExampleSelective(t, criteria);
	}

	@Override
	@CacheEvict(value=ConstMemc.MEMCACHE_CACHE_VALUE, key="'product-'+#t.id")
	public int deleteById(Integer id) {
//		return productMapper.deleteByPrimaryKey(id);
		return 0;
	}

	@Override
	public int deleteByCriteria(ProductCriteria criteria) {
//		return productMapper.deleteByExample(criteria);
		return 0;
	}

	@Override
	public Product loadById(Integer id) {
		return productMapper.selectByPrimaryKey(id);
	}

	@Override
	public List<Product> queryAll() {
		return queryAll(null);
	}

	@Override
	public List<Product> queryAll(String orderByClause) {
		ProductCriteria criteria = new ProductCriteria();
		criteria.createCriteria();
		criteria.setOrderByClause(orderByClause);
		return queryByCriteria(criteria);
	}

	@Override
	public List<Product> queryByCriteria(ProductCriteria criteria) {
		return productMapper.selectByExample(criteria);
	}
	
	@Override
	public int countByCriteria(ProductCriteria criteria) {
		return productMapper.countByExample(criteria);
	}
	

	@Override
	public List<Product> fallloadByCriteria(int pageSize,
			ProductCriteria criteria) {
		return null;
	}

	@Override
	public PagingResult<Product> pagingByCriteria(int pageNo, int pageSize,
			ProductCriteria criteria) {
		pageNo = pageNo<=0?1:pageNo;//确保pageNo合法
		pageSize = pageNo<=0?ConstConfig.PAGE_SIZE_DEFAULT:pageSize;//确保pageSize合法
		int offset = (pageNo-1)*pageSize;
		
		//构造查询条件
		if(criteria==null){
			criteria = new ProductCriteria();
		}
		
		criteria.setLimitOffset(offset);
		criteria.setLimitRows(pageSize);
		
		int count = productMapper.countByExample(criteria);
		List<Product> dataList = productMapper.selectByExample(criteria);
		//返回分页数据
		return new PagingResult<Product>(pageNo, pageSize, count, dataList);
	}
	
	

	@Override
	public List<Product> queryAvailableList() {
		ProductCriteria criteria = new ProductCriteria();
		criteria.createCriteria().andStatusEqualTo((short) 1);
		return productMapper.selectByExample(criteria);
	}
	
	
	@Override
	public PagingResult<Product> pagingTagProductsByCriteria(int tagId, int pageNo, int pageSize,
			ProductCriteria criteria) {
		pageNo = pageNo<=0?1:pageNo;//确保pageNo合法
		pageSize = pageNo<=0?ConstConfig.PAGE_SIZE_DEFAULT:pageSize;//确保pageSize合法
		int offset = (pageNo-1)*pageSize;
		
		//构造查询条件
		if(criteria==null){
			criteria = new ProductCriteria();
		}
		criteria.setLimitOffset(offset);
		criteria.setLimitRows(pageSize);
		
		int count = productMapper.countProductsByTagId(criteria, tagId);
		List<Product> dataList =  productMapper.queryProductsByTagId(criteria, tagId);
		//返回分页数据
		return new PagingResult<Product>(pageNo, pageSize, count, dataList);
	}
	

	@Override
	public PagingResult<Product> pagingTagOutProductsByCriteria(int tagId, int pageNo, int pageSize,
			ProductCriteria criteria) {
		pageNo = pageNo<=0?1:pageNo;//确保pageNo合法
		pageSize = pageNo<=0?ConstConfig.PAGE_SIZE_DEFAULT:pageSize;//确保pageSize合法
		int offset = (pageNo-1)*pageSize;
		
		//构造查询条件
		if(criteria==null){
			criteria = new ProductCriteria();
		}
		criteria.setLimitOffset(offset);
		criteria.setLimitRows(pageSize);
		
		int count = productMapper.countProductsOutByTagId(criteria, tagId);
		List<Product> dataList =  productMapper.queryProductsOutByTagId(criteria, tagId);
		//返回分页数据
		return new PagingResult<Product>(pageNo, pageSize, count, dataList);
	}
	
	@Override
	public List<Product> queryProductsByCategoryId(int categoryId, int pageNo, int pageSize, boolean isFallload) {
		pageNo = pageNo<=0?1:pageNo;//确保pageNo合法
		pageSize = pageNo<=0?ConstConfig.PAGE_SIZE_DEFAULT:pageSize;//确保pageSize合法
		int offset = (pageNo-1)*pageSize;
		
		ProductCriteria criteria = new ProductCriteria();
		criteria.setLimitOffset(offset);
		if(isFallload){//如果是瀑布流方式加载，需要多加一行，以便于判断是否有下一页
			criteria.setLimitRows(pageSize+1);
		}else{
			criteria.setLimitRows(pageSize);
		}
		
		return productMapper.queryProductsByCategoryId(criteria, categoryId);
	}
	
	@Override
	@Cacheable(value=ConstMemc.MEMCACHE_CACHE_VALUE, key="'products:categoryId-'+#categoryId+'-pageNo-'+#pageNo+'-pageSize-'+#pageSize")
	public List<Product> queryCachedProductsByCategoryId(int categoryId, int pageNo, int pageSize, boolean isFallload) {
		logger.debug("fallload productsByTag from db. [tagId:"+categoryId+", pageNo:"+pageNo+", pageSize:"+pageSize+"]");
		return queryProductsByCategoryId(categoryId, pageNo, pageSize, isFallload);
	}
	
	@Override
	public List<Product> queryProductsByTagId(int tagId, int pageNo, int pageSize, boolean isFallload) {
		pageNo = pageNo<=0?1:pageNo;//确保pageNo合法
		pageSize = pageNo<=0?ConstConfig.PAGE_SIZE_DEFAULT:pageSize;//确保pageSize合法
		int offset = (pageNo-1)*pageSize;
		
		ProductCriteria criteria = new ProductCriteria();
		criteria.setLimitOffset(offset);
		if(isFallload){//如果是瀑布流方式加载，需要多加一行，以便于判断是否有下一页
			criteria.setLimitRows(pageSize+1);
		}else{
			criteria.setLimitRows(pageSize);
		}
		
		return productMapper.queryProductsByTagId(criteria, tagId);
	}
	
	@Override
	@Cacheable(value=ConstMemc.MEMCACHE_CACHE_VALUE, key="'products:tagId-'+#tagId+'-pageNo-'+#pageNo+'-pageSize-'+#pageSize")
	public List<Product> queryCachedProductsByTagId(int tagId, int pageNo, int pageSize, boolean isFallload) {
		logger.debug("fallload productsByTag from db. [tagId:"+tagId+", pageNo:"+pageNo+", pageSize:"+pageSize+"]");
		return queryProductsByTagId(tagId, pageNo, pageSize, isFallload);
	}
	
	
//	@Override
//	public List<WxProduct> fallLoadProductsByTag(int tagId, int tailId, int limit) {
//		return productMapper.fallLoadProductsByTag(tagId, tailId, limit);
//	}
//	
//	@Override
//	@Cacheable(value=ConstMemc.MEMCACHE_CACHE_STORAGE, key="'tagId-'+#tagId+'-tailId-'+#tailId+'-limit-'+#limit")
//	public List<WxProduct> fallLoadCachedProductsByTag(int tagId, int tailId, int limit) {
//		logger.debug("fallload productsByTag from db. [tagId:"+tagId+", tailId:"+tailId+", limit:"+limit+"]");
//		return fallLoadProductsByTag(tagId, tailId, limit);
//	}
	
	
	@Override
	@Cacheable(value=ConstMemc.MEMCACHE_CACHE_VALUE, key="'product-'+#id")
	public Product loadCachedById(Integer id) {
		logger.debug("load product from db. [productId:"+id+"]");
		return loadById(id);
	}

	public ProductMapper getProductMapper() {
		return productMapper;
	}

	public void setProductMapper(ProductMapper productMapper) {
		this.productMapper = productMapper;
	}

	public IProductSkuService getProductSkuService() {
		return productSkuService;
	}

	public void setProductSkuService(IProductSkuService productSkuService) {
		this.productSkuService = productSkuService;
	}

	
//	@Override
//	public List<WxProduct> queryProductsByTagId(int tagId, int limitOffset, int limitRows){
//		return productMapper.queryProductsByTagId(tagId, limitOffset, limitRows); 
//	}
//
//	@Override
//	public List<WxProduct> queryProductsOutTagId(int tagId, int limitOffset, int limitRows){ 
//		return productMapper.queryProductsOutTagId(tagId, limitOffset, limitRows); 
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
//		productSkuService.save(sku);
//		
//		return 0;
//	}

	

	
	
}