package com.bruce.geekway.service.product;

/**
 * 计数服务
 * 
 * @createTime 2013-8-11 下午12:07:34
 */
public interface ICounterService {
	
	//查询库存
	public int queryProductSkuStock(int productSkuId);
	
	//增加库存
	public int incrProductSkuStock(int productSkuId, int incrNum);
	
	//扣减库存
    public int reduceProductSkuStock(int productSkuId, int reduceNum);
    
	//清空库存
//  	public void clearProductSkuStock(int productSkuId);
}
