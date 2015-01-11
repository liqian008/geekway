package com.bruce.geekway.cache.product;

import org.apache.commons.lang3.math.NumberUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;
import redis.clients.jedis.exceptions.JedisException;

import com.bruce.geekway.constants.ConstRedis;
import com.bruce.geekway.model.exception.RedisKeyNotExistException;

/**
 * 库存cache
 * 数据结构string
 * @createTime 2013-9-11 下午06:46:02
 */
public class ProductStockCache{
	
    private static final Logger logger = LoggerFactory.getLogger(ProductStockCache.class);
    
	@Autowired
    private JedisPool jedisPool;
	
	/* 产品sku库存数的key */
	public static final String COUNTER_KEY_PRODUCT_SKU_STOCK = "productSkuStock";
	
	
	public int getProductSkuStock(int productSkuId) throws RedisKeyNotExistException {
	    return getCountByKey(getProductSkuStockKey(productSkuId)); 
    }
	
	public int incrProductSkuStock(int productSkuId, int incrNum) {
		delByKey(getProductSkuStockKey(productSkuId));//删除redis缓存
		return 1;
	}
	
	public int reduceProductSkuStock(int productSkuId, int reduceStock) {
		delByKey(getProductSkuStockKey(productSkuId));//删除redis缓存
		return 1;
	}

	
	
	/**
	 * 重建库存数据
	 * @return
	 */
	public boolean rebuildProductSkuStock(int productSkuId, int currentStock) {
		String key = getProductSkuStockKey(productSkuId);
		return setByKey(key, currentStock);
    }
	
	
	private int getCountByKey(String key) throws RedisKeyNotExistException{
		int result = 0;
	    Jedis jedis = null;
        try {
            jedis = jedisPool.getResource();
            boolean exists = jedis.exists(key);
            if (exists == false) {
                jedisPool.returnResource(jedis);
                throw new RedisKeyNotExistException();
            } else {
                String value = jedis.get(key);
                if(NumberUtils.isNumber(value)){
                	jedisPool.returnResource(jedis);
                	return NumberUtils.toInt(value);
                }else{
                	jedis.del(key);
                	jedisPool.returnResource(jedis);
                	throw new RedisKeyNotExistException();
                }
            }
        } catch (JedisException t) {
            logger.error("get count by key", t);
            if (jedis != null) {
                jedisPool.returnBrokenResource(jedis);
            }
        }
        return result;
	}
	
	/**
	 * 重新加载数据
	 * @return
	 */
	public boolean setByKey(String key, int stock) {
        boolean result = false;
        Jedis jedis = null;
        try {
            jedis = jedisPool.getResource();
            jedis.del(key);
            jedis.set(key, String.valueOf(stock));
            result =  true;
            jedisPool.returnResource(jedis);
            return result;
        } catch (JedisException t) {
            logger.error("reInitData", t);
            if (jedis != null) {
                jedisPool.returnBrokenResource(jedis);
            }
        }
        return result;
    }
	
	private void delByKey(String key) {
	    Jedis jedis = null;
        try {
            jedis = jedisPool.getResource();
            jedis.del(key);
            jedisPool.returnResource(jedis);
        } catch (JedisException t) {
            logger.error("delete by key", t);
            if (jedis != null) {
                jedisPool.returnBrokenResource(jedis);
            }
        }
	}
	
	

	private String getProductSkuStockKey(int productSkuId) {
        return ConstRedis.REDIS_NAMESPACE + "_" + ConstRedis.REDIS_KEY_TYPE_COUNT + "_" + COUNTER_KEY_PRODUCT_SKU_STOCK +"_"+ productSkuId;
    }

	public JedisPool getJedisPool() {
		return jedisPool;
	}

	public void setJedisPool(JedisPool jedisPool) {
		this.jedisPool = jedisPool;
	}

	
}
