package com.bruce.geekway.service.ito;

import java.util.List;

import com.bruce.foundation.service.IFoundationService;
import com.bruce.geekway.model.ItoProductOrder;
import com.bruce.geekway.model.ItoProductOrderCriteria;

public interface IItoProductOrderService extends IFoundationService<ItoProductOrder, Integer,ItoProductOrderCriteria>{

	/**
	 * 查询某productId的订单列表
	 * @return
	 */
	public int countByProductId(int productId);
	
	/**
	 * 查询来自支付宝的订单列表
	 * @return
	 */
	public List<ItoProductOrder> queryAlipayOrderList();
	
	/**
	 * 查询来自线下支付的订单列表
	 * @return
	 */
	public List<ItoProductOrder> querySelfOrderList();
	
	
	/**
	 * 修改订单状态
	 * @param order
	 * @return
	 */
	public int changeOrderStatus(ItoProductOrder order);
	
	public ItoProductOrder loadByOrderSn(String orderSn);

	public ItoProductOrder loadByOrderSn(String orderSn, short payType);

	public String generateOrderSn();
	
	
	
	
	
	
	
//	public String signature(String orderSn);
	
}