package com.bruce.geekway.cache.product;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;
import redis.clients.jedis.exceptions.JedisException;

import com.bruce.geekway.constants.ConstRedis;
import com.bruce.geekway.model.exception.RedisKeyNotExistException;

/**
 * 库存cache
 * 数据结构
 * @createTime 2013-9-11 下午06:46:02
 */
public class ProductStockCache{
	
    private static final Logger logger = LoggerFactory.getLogger(ProductStockCache.class);
    
	@Autowired
    private JedisPool jedisPool;
	
	/* 产品库存数的key */
	public static final String COUNTER_KEY_PRODUCT_STOCK = "productStock";
	
	
	public int getStock(int productSkuId) throws RedisKeyNotExistException {
	    return getCountByKey(getProductStockKey(), productSkuId);
    }
	
	public int incrStock(int productSkuId, int stockNum) throws RedisKeyNotExistException {
	    return incrByKey(getProductStockKey(), stockNum, productSkuId);
    }
	
	public void clearStock(int productSkuId) throws RedisKeyNotExistException {
		clearByKey(getProductStockKey(), productSkuId);
	}

	
	
	private int getCountByKey(String key, int id) throws RedisKeyNotExistException {
		int result = 0;
	    Jedis jedis = null;
        try {
            jedis = jedisPool.getResource();
            boolean exists = jedis.exists(key);
            if (exists == false) {
                jedisPool.returnResource(jedis);
                throw new RedisKeyNotExistException();
            } else {
                Double countNum = jedis.zscore(key, String.valueOf(id));
                jedisPool.returnResource(jedis);
                if(countNum!=null){
	            	result = countNum.intValue();
	            }
            }
            return result;
        } catch (JedisException t) {
            logger.error("incrByKey", t);
            if (jedis != null) {
                jedisPool.returnBrokenResource(jedis);
            }
        }
        return result;
	}
	
	
	private int incrByKey(String key, int score, int id) throws RedisKeyNotExistException {
        int result = 0;
	    Jedis jedis = null;
        try {
            jedis = jedisPool.getResource();
            boolean exists = jedis.exists(key);
            if (exists == false) {
                jedisPool.returnResource(jedis);
                throw new RedisKeyNotExistException();
            } else {
                result = new Double(jedis.zincrby(key, score, String.valueOf(id))).intValue();
                jedisPool.returnResource(jedis);
            }
            return result;
        } catch (JedisException t) {
            logger.error("incrByKey", t);
            if (jedis != null) {
                jedisPool.returnBrokenResource(jedis);
            }
        }
        return 0;
    }

	private void clearByKey(String key, int id) throws RedisKeyNotExistException {
	    Jedis jedis = null;
        try {
            jedis = jedisPool.getResource();
            boolean exists = jedis.exists(key);
            if (exists == false) {
                jedisPool.returnResource(jedis);
                throw new RedisKeyNotExistException();
            } else {
                jedis.zrem(key, String.valueOf(id));
                jedisPool.returnResource(jedis);
            }
        } catch (JedisException t) {
            logger.error("incrByKey", t);
            if (jedis != null) {
                jedisPool.returnBrokenResource(jedis);
            }
        }
    }

	private String getProductStockKey() {
        return ConstRedis.REDIS_NAMESPACE + "_" + ConstRedis.REDIS_KEY_TYPE_COUNT + "_" + COUNTER_KEY_PRODUCT_STOCK;
    }

	
}
