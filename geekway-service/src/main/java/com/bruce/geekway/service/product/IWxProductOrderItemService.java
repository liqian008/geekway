package com.bruce.geekway.service.product;

import java.util.List;

import com.bruce.foundation.service.IFoundationService;
import com.bruce.geekway.model.WxProductOrderItem;
import com.bruce.geekway.model.WxProductOrderItemCriteria;

/**
 * 订单item service
 * @author liqian
 *
 */
public interface IWxProductOrderItemService extends IFoundationService<WxProductOrderItem, Long, WxProductOrderItemCriteria>{
	
	/**
	 * 根据订单号加载订单条目数据
	 * @param outTradeNo
	 * @return
	 */
	public List<WxProductOrderItem> queryByTradeNo(String outTradeNo);
	
	
}
