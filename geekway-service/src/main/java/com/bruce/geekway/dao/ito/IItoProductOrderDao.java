package com.bruce.geekway.dao.ito;

import java.util.List;

import com.bruce.geekway.dao.IBaseDao;
import com.bruce.geekway.model.ItoProductOrder;

public interface IItoProductOrderDao extends IBaseDao<ItoProductOrder, Integer> {

	public int countByProductId(int productId);

	public List<ItoProductOrder> queryOrderListByPayType(short payType);

	/**
	 * 根据订单号查询订单
	 * @param orderSn
	 * @return
	 */
	
	ItoProductOrder loadByOrderSn(String orderSn);
	
	/**
	 * 根据订单号和类型查询订单
	 * @param orderSn
	 * @param payType
	 * @return
	 */
	public ItoProductOrder loadByOrderSn(String orderSn, short payType);

	


//	public int changeOrderStatus(ItoProductOrder order);
    
}
