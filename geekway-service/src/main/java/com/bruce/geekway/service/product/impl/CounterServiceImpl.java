package com.bruce.geekway.service.product.impl;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;

import com.bruce.geekway.cache.product.ProductStockCache;
import com.bruce.geekway.dao.mapper.WxProductSkuMapper;
import com.bruce.geekway.model.WxProductSku;
import com.bruce.geekway.model.exception.RedisKeyNotExistException;
import com.bruce.geekway.model.exception.RedisScoreNotExistException;
import com.bruce.geekway.service.product.ICounterService;
import com.bruce.geekway.service.product.IWxProductSkuService;

/**
 * Comments for CounterServiceImpl.java
 * 
 * @createTime 2013-8-11 下午12:08:09
 */
public class CounterServiceImpl implements ICounterService, InitializingBean {
	
	@Autowired 
	private ProductStockCache productStockCache;
	@Autowired 
	private IWxProductSkuService wxProductSkuService;
	@Autowired
	private WxProductSkuMapper wxProductSkuMapper;
	
    /**
     * Logger for this class
     */
    private static final Logger logger = LoggerFactory.getLogger(CounterServiceImpl.class);
    
    @Override
    public int queryProductSkuStock(int productSkuId){
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
    }
    
    @Override
    public int incrProductSkuStock(int productSkuId, int incrNum){
    	int result = wxProductSkuMapper.incrStock(productSkuId, incrNum);
    	if(result>0){
	    	try {
	    		return productStockCache.incrProductSkuStock(productSkuId, incrNum);
	    	} catch (RedisKeyNotExistException e) {
	    		if(logger.isErrorEnabled()){
	    			logger.error("incr sku stock: "+productSkuId, e);
	    		}
	    		 //重建库存的缓存
	    		int currentStock = queryProductSkuStockFromDB(productSkuId);
				productStockCache.rebuildProductSkuStock(productSkuId, currentStock);
			}
    	}
    	return 0;
    }

    /**
     * 扣减库存
     */
	@Override
	public int reduceProductSkuStock(int productSkuId, int reduceStock) { 
		int result = wxProductSkuMapper.reduceStock(productSkuId, reduceStock);
    	if(result>0){
	    	try {
	    		return productStockCache.reduceProductSkuStock(productSkuId, reduceStock);
	    	} catch (RedisKeyNotExistException e) {
	    		if(logger.isErrorEnabled()){
	    			logger.error("reduce product sku stock: "+productSkuId, e);
	    		}
	    		 //重建库存的缓存
	    		int currentStock = queryProductSkuStockFromDB(productSkuId);
				productStockCache.rebuildProductSkuStock(productSkuId, currentStock);
			}
    	}
    	return 0;
	}
	
	
	/**
	 * 查询db中的库存数
	 * @param productSkuId
	 * @return
	 */
	private int queryProductSkuStockFromDB(int productSkuId) {
		WxProductSku productSku = wxProductSkuMapper.selectByPrimaryKey(productSkuId);
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

	public IWxProductSkuService getWxProductSkuService() {
		return wxProductSkuService;
	}

	public void setWxProductSkuService(IWxProductSkuService wxProductSkuService) {
		this.wxProductSkuService = wxProductSkuService;
	}

	public WxProductSkuMapper getWxProductSkuMapper() {
		return wxProductSkuMapper;
	}

	public void setWxProductSkuMapper(WxProductSkuMapper wxProductSkuMapper) {
		this.wxProductSkuMapper = wxProductSkuMapper;
	}
	
}
