package com.bruce.geekway.service.product;

/**
 * 计数服务
 * 
 * @createTime 2013-8-11 下午12:07:34
 */
public interface ICounterService {
	
	public int getStock(int productSkuId);
	
	//增加库存
	public int incrStock(int productSkuId, int stockNum);
	
	//扣减库存
    public int reduceStock(int productSkuId, int stockNum);
    
	//清空库存
  	public void clearStock(int productSkuId);
}
