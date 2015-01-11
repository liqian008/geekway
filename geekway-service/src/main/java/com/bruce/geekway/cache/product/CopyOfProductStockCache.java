package com.bruce.geekway.cache.product;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;
import redis.clients.jedis.exceptions.JedisException;

import com.bruce.geekway.constants.ConstRedis;
import com.bruce.geekway.model.exception.RedisKeyNotExistException;


//备份 sortedset结构
/**
 * 库存cache
 * 数据结构
 * @createTime 2013-9-11 下午06:46:02
 */
public class CopyOfProductStockCache{
	
    private static final Logger logger = LoggerFactory.getLogger(CopyOfProductStockCache.class);
    
	@Autowired
    private JedisPool jedisPool;
	
	/* 产品sku库存数的key */
	public static final String COUNTER_KEY_PRODUCT_SKU_STOCK = "productSkuStock";
//	/* 产品销量的key */
//	public static final String COUNTER_KEY_PRODUCT_SALES = "productSales";
	
	
	public int getProductSkuStock(int productSkuId) throws RedisKeyNotExistException {
	    return getCountByKey(getProductSkuStockKey(), productSkuId); 
    }
	
	public int incrProductSkuStock(int productSkuId, int stockNum) throws RedisKeyNotExistException {
	    return incrByKey(getProductSkuStockKey(), stockNum, productSkuId);
    }
	
	public int reduceProductSkuStock(int productSkuId, int stockNum) throws RedisKeyNotExistException {
	    return incrByKey(getProductSkuStockKey(), (0-stockNum), productSkuId);
    }
	
	public void clearProductSkuStock(int productSkuId) throws RedisKeyNotExistException {
		clearByKey(getProductSkuStockKey(), productSkuId);
	}
	
	/**
	 * 重新加载库存数据
	 * @return
	 */
	public boolean rebuildProductSkuStock(int productSkuId, int currentStock) {
		String key = getProductSkuStockKey();
		return addByKey(key, productSkuId, currentStock);
    }
	
	
	private int getCountByKey(String key, int member) throws RedisKeyNotExistException{
		int result = 0;
	    Jedis jedis = null;
        try {
            jedis = jedisPool.getResource();
            boolean exists = jedis.exists(key);
            if (exists == false) {
                jedisPool.returnResource(jedis);
                throw new RedisKeyNotExistException();
            } else {
                Double countNum = jedis.zscore(key, String.valueOf(member));
                jedisPool.returnResource(jedis);
                if(countNum!=null){
	            	result = countNum.intValue();
	            	return result;
	            }
                throw new RedisKeyNotExistException();//没有member的情况下，共用RedisKeyNotExistException
            }
        } catch (JedisException t) {
            logger.error("incrByKey", t);
            if (jedis != null) {
                jedisPool.returnBrokenResource(jedis);
            }
        }
        return result;
	}
	
	
	private int incrByKey(String key, int score, int member) throws RedisKeyNotExistException {
        int result = 0;
	    Jedis jedis = null;
        try {
            jedis = jedisPool.getResource();
            boolean exists = jedis.exists(key);
            if (exists == false) {
                jedisPool.returnResource(jedis);
                throw new RedisKeyNotExistException();
            } else {
            	Double countNum = jedis.zscore(key, String.valueOf(member));
            	if(countNum!=null){//有值，可以执行incr操作
            		result = new Double(jedis.zincrby(key, score, String.valueOf(member))).intValue();
            		jedisPool.returnResource(jedis);
	            }else{
	            	jedisPool.returnResource(jedis);
	            	throw new RedisKeyNotExistException();//没有member的情况下，也返回RedisKeyNotExistException
	            }
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

	private void clearByKey(String key, int member) throws RedisKeyNotExistException {
	    Jedis jedis = null;
        try {
            jedis = jedisPool.getResource();
            boolean exists = jedis.exists(key);
            if (exists == false) {
                jedisPool.returnResource(jedis);
                throw new RedisKeyNotExistException();
            } else {
                jedis.zrem(key, String.valueOf(member));
                jedisPool.returnResource(jedis);
            }
        } catch (JedisException t) {
            logger.error("claerByKey", t);
            if (jedis != null) {
                jedisPool.returnBrokenResource(jedis);
            }
        }
    }
	
	/**
	 * 重新加载数据
	 * @return
	 */
	public boolean addByKey(String key, int member, int initScore) {
        boolean result = false;
        Jedis jedis = null;
        try {
            jedis = jedisPool.getResource();
            jedis.del(key);
            jedis.zadd(key, initScore, String.valueOf(member));
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


	private String getProductSkuStockKey() {
        return ConstRedis.REDIS_NAMESPACE + "_" + ConstRedis.REDIS_KEY_TYPE_COUNT + "_" + COUNTER_KEY_PRODUCT_SKU_STOCK;
    }

	public JedisPool getJedisPool() {
		return jedisPool;
	}

	public void setJedisPool(JedisPool jedisPool) {
		this.jedisPool = jedisPool;
	}
	
	
}
