package com.bruce.geekway.service.product.impl;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import com.bruce.geekway.dao.mapper.WxProductSkuMapper;
import com.bruce.geekway.model.WxProductSku;
import com.bruce.geekway.model.WxProductSkuCriteria;
import com.bruce.geekway.service.product.IWxProductSkuService;

@Service
public class WxProductSkuServiceImpl implements IWxProductSkuService{
	
	@Autowired
	private WxProductSkuMapper wxProductSkuMapper;
	
	private static final Logger logger = LoggerFactory.getLogger(WxProductSkuServiceImpl.class);
	
	
	@Override
	public int save(WxProductSku t) {
		return wxProductSkuMapper.insertSelective(t);
	}

	@Override
	public int updateById(WxProductSku t) {
		return wxProductSkuMapper.updateByPrimaryKeySelective(t);
	}

	@Override
	public int updateByCriteria(WxProductSku t, WxProductSkuCriteria criteria) {
		return wxProductSkuMapper.updateByExampleSelective(t, criteria);
	}

	@Override
	public int deleteById(Integer id) {
		return wxProductSkuMapper.deleteByPrimaryKey(id);
	}

	@Override
	public int deleteByCriteria(WxProductSkuCriteria criteria) {
		return wxProductSkuMapper.deleteByExample(criteria);
	}

	@Override
	public WxProductSku loadById(Integer id) {
		return wxProductSkuMapper.selectByPrimaryKey(id);
	}

	@Override
	public List<WxProductSku> queryAll() {
		return queryAll(null);
	}

	@Override
	public List<WxProductSku> queryAll(String orderByClause) {
		WxProductSkuCriteria criteria = new WxProductSkuCriteria();
		criteria.createCriteria();
		criteria.setOrderByClause(orderByClause);
		return queryByCriteria(criteria);
	}

	@Override
	public List<WxProductSku> queryByCriteria(WxProductSkuCriteria criteria) {
		return wxProductSkuMapper.selectByExample(criteria);
	}

	@Override
	public int countByCriteria(WxProductSkuCriteria criteria) {
		return wxProductSkuMapper.countByExample(criteria);
	}
	
	@Override
	public List<WxProductSku> querySkuListByProductId(int productId) {
		WxProductSkuCriteria criteria = new WxProductSkuCriteria();
		criteria.createCriteria().andProductIdEqualTo(productId);
		return wxProductSkuMapper.selectByExample(criteria);
	}
	
	@Override
	@Cacheable(value="storageCache", key="'product-'+#productId+'-skus'")
	public List<WxProductSku> queryCachedSkuListByProductId(int productId) {
		logger.debug("load product "+productId+" skus from db");
		return querySkuListByProductId(productId);
	}
	
	
	@Override
	public WxProductSku loadProductSku(int productId, int skuId) {
		WxProductSku sku = loadById(skuId);
		if(sku!=null&&sku.getProductId()!=null&&sku.getProductId()==productId){
			return sku;
		}
		return null;
	}
	
	@Override
	@Cacheable(value="storageCache", key="'product-sku-'+#skuId")
	public WxProductSku loadCachedProductSku(int productId, int skuId) {
		logger.debug("load productSku from db. [skuId:"+skuId+"]");
		return loadProductSku(productId, skuId);
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
//	@Cacheable(value="storageCache", key="'category-'+#categoryId+'-tailId-'+#productTailId+'-limit-'+#limit+")
//	public List<WxProductSku> fallLoadCachedCategoryProductSkuList(int categoryId, int productTailId, int limit) {
//		logger.debug("fallload CategoryProductSkuList from db. [categoryId:"+categoryId+", productTailId:"+productTailId+", limit:"+limit+"]");
//		return fallLoadCategoryProductSkuList(categoryId, productTailId, limit);
//	}
	

	public WxProductSkuMapper getWxProductSkuMapper() {
		return wxProductSkuMapper;
	}

	public void setWxProductSkuMapper(WxProductSkuMapper wxProductSkuMapper) {
		this.wxProductSkuMapper = wxProductSkuMapper;
	}

	
}
