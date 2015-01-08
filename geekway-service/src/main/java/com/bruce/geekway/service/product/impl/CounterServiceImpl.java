package com.bruce.geekway.service.product.impl;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;

import com.bruce.geekway.cache.product.ProductStockCache;
import com.bruce.geekway.model.exception.RedisKeyNotExistException;
import com.bruce.geekway.service.product.ICounterService;
import com.bruce.geekway.service.product.IWxProductService;

/**
 * Comments for CounterServiceImpl.java
 * 
 * @createTime 2013-8-11 下午12:08:09
 */
public class CounterServiceImpl implements ICounterService, InitializingBean {
	
	@Autowired 
	private ProductStockCache productStockCache;
	@Autowired 
	private IWxProductService wxProductService;
	
    /**
     * Logger for this class
     */
    private static final Logger logger = LoggerFactory.getLogger(CounterServiceImpl.class);
    
    @Override
    public int getStock(int productSkuId){
    	try {
    		return productStockCache.getStock(productSkuId);
    	} catch (RedisKeyNotExistException e) {
    		if(logger.isErrorEnabled()){
    			logger.error("get sku stock: "+productSkuId, e);
    		}
			int stock = 0;//TODO load from db
            //TODO 重建缓存
		}
    	return 0;
    }
    
    @Override
    public int incrStock(int productSkuId, int stockNum){
    	//TODO db update
    	int result = 0;//
    	if(result>0){
	    	try {
	    		return productStockCache.incrStock(productSkuId, stockNum);
	    	} catch (RedisKeyNotExistException e) {
	    		if(logger.isErrorEnabled()){
	    			logger.error("incr sku stock: "+productSkuId, e);
	    		}
				int stock = 0;//TODO load from db
	            //TODO 重建缓存
			}
    	}
    	return 0;
    }

	@Override
	public int reduceStock(int productSkuId, int stockNum) { 
		//扣减库存
		return incrStock(productSkuId, (0-stockNum));
	}
	
	@Override
	public void clearStock(int productSkuId) {
		try {
    		productStockCache.clearStock(productSkuId);
    	} catch (RedisKeyNotExistException e) {
    		if(logger.isErrorEnabled()){
    			logger.error("incr sku stock: "+productSkuId, e);
    		}
		}
	}
	
	@Override
    public void afterPropertiesSet() throws Exception {
    }
	
}
