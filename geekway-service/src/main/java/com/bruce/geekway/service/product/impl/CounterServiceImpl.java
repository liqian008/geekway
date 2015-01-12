package com.bruce.geekway.service.product.impl;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;

import com.bruce.geekway.cache.product.ProductStockCache;
import com.bruce.geekway.constants.ConstConfig;
import com.bruce.geekway.dao.mapper.ProductSkuMapper;
import com.bruce.geekway.model.ProductSku;
import com.bruce.geekway.model.exception.RedisKeyNotExistException;
import com.bruce.geekway.service.product.ICounterService;
import com.bruce.geekway.service.product.IProductSkuService;

/**
 * Comments for CounterServiceImpl.java
 * 
 * @createTime 2013-8-11 下午12:08:09
 */
public class CounterServiceImpl implements ICounterService, InitializingBean {
	
	@Autowired 
	private ProductStockCache productStockCache;
	@Autowired 
	private IProductSkuService productSkuService;
	@Autowired
	private ProductSkuMapper productSkuMapper;
	
    /**
     * Logger for this class
     */
    private static final Logger logger = LoggerFactory.getLogger(CounterServiceImpl.class);
    
    @Override
    public int queryProductSkuStock(int productSkuId){
    	if(ConstConfig.REDIS_ENABLE){//当启用redis时，先读redis
	    	try {
	    		return productStockCache.getProductSkuStock(productSkuId);
	    	} catch (RedisKeyNotExistException e) {//key不存在
	    		if(logger.isErrorEnabled()){
	    			logger.error("get product sku stock: "+productSkuId, e);
	    		}
	            //重建库存的缓存
	    		int currentStock = queryProductSkuStockFromDB(productSkuId);
				productStockCache.rebuildProductSkuStock(productSkuId, currentStock);
				return currentStock;
			}
    	}else{//不启用redis，则直接读db
    		return queryProductSkuStockFromDB(productSkuId);
    	}
    }
    
    
    /**
     * 增加库存
     */
    @Override
    public int incrProductSkuStock(int productSkuId, int incrNum){
    	//TODO synchronized
    	int result = productSkuMapper.incrStock(productSkuId, incrNum);
    	if(ConstConfig.REDIS_ENABLE){//启用redis时，同步redis数据
	    	if(result>0){
		    	return productStockCache.incrProductSkuStock(productSkuId, incrNum);
	    	}
	    	return 0;
    	}else{//不启用redis，直接返回
    		return result;
    	}
    }

    /**
     * 扣减库存
     */
	@Override
	public int reduceProductSkuStock(int productSkuId, int reduceStock) { 
		//TODO synchronized
    	int result = productSkuMapper.reduceStock(productSkuId, reduceStock);
		if(ConstConfig.REDIS_ENABLE){//启用redis时，同步redis数据
			if(result>0){
		    	return productStockCache.reduceProductSkuStock(productSkuId, reduceStock);
	    	}
	    	return 0;
		}else{//不启用redis，直接返回
			return result;
		}
	}
	
	
	/**
	 * 查询db中的库存数
	 * @param productSkuId
	 * @return
	 */
	private int queryProductSkuStockFromDB(int productSkuId) {
		ProductSku productSku = productSkuMapper.selectByPrimaryKey(productSkuId);
		if(productSku!=null&&productSku.getStock()!=null&&productSku.getStock()>0){
			return productSku.getStock().intValue();
		}
		return 0;
	}
	
	@Override
    public void afterPropertiesSet() throws Exception {
    }

	public ProductStockCache getProductStockCache() {
		return productStockCache;
	}

	public void setProductStockCache(ProductStockCache productStockCache) {
		this.productStockCache = productStockCache;
	}

	public IProductSkuService getProductSkuService() {
		return productSkuService;
	}

	public void setProductSkuService(IProductSkuService productSkuService) {
		this.productSkuService = productSkuService;
	}

	public ProductSkuMapper getProductSkuMapper() {
		return productSkuMapper;
	}

	public void setProductSkuMapper(ProductSkuMapper productSkuMapper) {
		this.productSkuMapper = productSkuMapper;
	}
	
}
