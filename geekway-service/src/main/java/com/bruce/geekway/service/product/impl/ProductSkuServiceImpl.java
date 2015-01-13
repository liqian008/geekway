package com.bruce.geekway.service.product.impl;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.cache.annotation.Caching;

import com.bruce.geekway.constants.ConstMemc;
import com.bruce.geekway.dao.mapper.ProductSkuMapper;
import com.bruce.geekway.model.ProductSku;
import com.bruce.geekway.model.ProductSkuCriteria;
import com.bruce.geekway.service.product.IProductSkuService;

public class ProductSkuServiceImpl implements IProductSkuService{
	
	@Autowired
	private ProductSkuMapper productSkuMapper;
	
	private static final Logger logger = LoggerFactory.getLogger(ProductSkuServiceImpl.class);
	
	
	@Override
	public int save(ProductSku t) {
		return productSkuMapper.insertSelective(t);
	}

	@Override
	@Caching(evict = {
			@CacheEvict(value = ConstMemc.MEMCACHE_CACHE_VALUE, key = "'product-sku-'+#t.id"),
			@CacheEvict(value = ConstMemc.MEMCACHE_CACHE_VALUE, key = "'product-'+#t.productId+'-skus'"), })
	public int updateById(ProductSku t) {
		return productSkuMapper.updateByPrimaryKeySelective(t);
	}

	@Override
	public int updateByCriteria(ProductSku t, ProductSkuCriteria criteria) {
		return productSkuMapper.updateByExampleSelective(t, criteria);
	}

	@Override
	@Caching(evict = {
			@CacheEvict(value = ConstMemc.MEMCACHE_CACHE_VALUE, key = "'product-sku-'+#t.id"),
			@CacheEvict(value = ConstMemc.MEMCACHE_CACHE_VALUE, key = "'product-'+#t.productId+'-skus'"), })
	public int deleteById(Integer id) {
		return productSkuMapper.deleteByPrimaryKey(id);
	}

	@Override
	public int deleteByCriteria(ProductSkuCriteria criteria) {
		//return productSkuMapper.deleteByExample(criteria);
		return 0;
	}

	@Override
	public ProductSku loadById(Integer id) {
		return productSkuMapper.selectByPrimaryKey(id);
	}

	@Override
	public List<ProductSku> queryAll() {
		return queryAll(null);
	}

	@Override
	public List<ProductSku> queryAll(String orderByClause) {
		ProductSkuCriteria criteria = new ProductSkuCriteria();
		criteria.createCriteria();
		criteria.setOrderByClause(orderByClause);
		return queryByCriteria(criteria);
	}

	@Override
	public List<ProductSku> queryByCriteria(ProductSkuCriteria criteria) {
		return productSkuMapper.selectByExample(criteria);
	}

	@Override
	public int countByCriteria(ProductSkuCriteria criteria) {
		return productSkuMapper.countByExample(criteria);
	}
	
	@Override
	public List<ProductSku> querySkuListByProductId(int productId) {
		ProductSkuCriteria criteria = new ProductSkuCriteria();
		criteria.createCriteria().andProductIdEqualTo(productId);
		return productSkuMapper.selectByExample(criteria);
	}
	
	@Override
	@Cacheable(value=ConstMemc.MEMCACHE_CACHE_VALUE, key="'product-'+#productId+'-skus'")
	public List<ProductSku> queryCachedSkuListByProductId(int productId) {
		logger.debug("load product "+productId+" skus from db");
		return querySkuListByProductId(productId);
	}
	
	
	@Override
	public ProductSku loadProductSku(int productId, int skuId) {
		ProductSku sku = loadById(skuId);
		if(sku!=null&&sku.getProductId()!=null&&sku.getProductId()==productId){
			return sku;
		}
		return null;
	}
	
	@Override
	@Cacheable(value=ConstMemc.MEMCACHE_CACHE_VALUE, key="'product-sku-'+#skuId")
	public ProductSku loadCachedProductSku(int productId, int skuId) {
		logger.debug("load productSku from db. [skuId:"+skuId+"]");
		return loadProductSku(productId, skuId);
	}

	public ProductSkuMapper getProductSkuMapper() {
		return productSkuMapper;
	}

	public void setProductSkuMapper(ProductSkuMapper productSkuMapper) {
		this.productSkuMapper = productSkuMapper;
	}

	
	
	
	
//	@Override
//	public List<WxProductSku> fallLoadCategoryProductSkuList(int categoryId, int productTailId, int limit) {
//		return wxProductSkuMapper.fallLoadCategoryProductSkuList(categoryId, productTailId, limit);
//	}
	
//	/**
//	 * 使用缓存的组查询productSku
//	 * @return
//	 */
//	@Override
//	@Cacheable(value=ConstMemc.MEMCACHE_CACHE_STORAGE, key="'category-'+#categoryId+'-tailId-'+#productTailId+'-limit-'+#limit+")
//	public List<WxProductSku> fallLoadCachedCategoryProductSkuList(int categoryId, int productTailId, int limit) {
//		logger.debug("fallload CategoryProductSkuList from db. [categoryId:"+categoryId+", productTailId:"+productTailId+", limit:"+limit+"]");
//		return fallLoadCategoryProductSkuList(categoryId, productTailId, limit);
//	}
	
	
	

}