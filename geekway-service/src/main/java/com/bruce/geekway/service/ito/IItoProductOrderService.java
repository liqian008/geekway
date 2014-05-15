package com.bruce.geekway.service.ito;

import com.bruce.geekway.model.ItoProductOrder;
import com.bruce.geekway.service.IBaseService;

public interface IItoProductOrderService extends IBaseService<ItoProductOrder, Integer>{

	/**
	 * 修改订单状态
	 * @param order
	 * @return
	 */
	public int changeOrderStatus(ItoProductOrder order);
	
	
	public ItoProductOrder loadByOrderSn(String orderSn);

	public String generateOrderSn();
	
	public String signature(String orderSn);
	
	
}