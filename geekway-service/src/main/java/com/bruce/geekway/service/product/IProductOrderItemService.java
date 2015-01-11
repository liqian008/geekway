package com.bruce.geekway.service.product;

import java.util.List;

import com.bruce.foundation.service.IFoundationService;
import com.bruce.geekway.model.ProductOrderItem;
import com.bruce.geekway.model.ProductOrderItemCriteria;

/**
 * 订单item service
 * @author liqian
 *
 */
public interface IProductOrderItemService extends IFoundationService<ProductOrderItem, Long, ProductOrderItemCriteria>{
	
	/**
	 * 根据订单号加载订单条目数据
	 * @param outTradeNo
	 * @return
	 */
	public List<ProductOrderItem> queryByTradeNo(String outTradeNo);
	
	
}
