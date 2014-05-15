package com.bruce.geekway.dao.ito;

import com.bruce.geekway.dao.IBaseDao;
import com.bruce.geekway.model.ItoProductOrder;

public interface IItoProductOrderDao extends IBaseDao<ItoProductOrder, Integer> {

	/**
	 * 根据订单号查询订单
	 * @param orderSn
	 * @return
	 */
	public ItoProductOrder loadByOrderSn(String orderSn);

//	public int changeOrderStatus(ItoProductOrder order);
    
}
