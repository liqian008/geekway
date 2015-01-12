package com.bruce.geekway.service.product;

import java.util.List;

import com.bruce.foundation.service.IFoundationPagingService;
import com.bruce.geekway.model.ProductOrder;
import com.bruce.geekway.model.ProductOrderCriteria;
import com.bruce.geekway.model.ProductOrderItem;
import com.bruce.geekway.model.UserAddress;

/**
 * 订单service
 * @author liqian
 *
 */
public interface IProductOrderService extends IFoundationPagingService<ProductOrder, Long, ProductOrderCriteria>{
	
	
	/**
	 * 查询我的订单详情
	 * @param userOpenId
	 * @param orderId
	 * @param tradeNo
	 * @return
	 */
	public ProductOrder loadUserOrderInfo(String userOpenId, long orderId, String tradeNo);
	
	/**
	 * 查询我的订单列表
	 * @param userOpenId
	 * @param voucherTailId
	 * @param limit
	 * @return
	 */
	public List<ProductOrder> queryUserOrders(String userOpenId, int pageNo, int pageSize, boolean isFallload);
	
	/**
	 * 使用缓存查询我的订单列表
	 * @return
	 */
	public List<ProductOrder> queryCachedUserOrders(String userOpenId, int pageNo, int pageSize, boolean isFallload);
	
	
	/**
	 * 创建订单，等待用户支付
	 * @param productOrder
	 * @param userAddressInfo
	 * @param orderItemList
	 * @return
	 */
	public int createOrder(ProductOrder productOrder, UserAddress userAddressInfo, List<ProductOrderItem> orderItemList);
	
	/**
	 * 根据系统内用户openId和订单号加载订单数据(用于前台查询)
	 * @param userOpenId
	 * @param tradeNo
	 * @return
	 */
	public ProductOrder loadByUserTradeNo(String userOpenId, String tradeNo);
	
	/**
	 * 根据系统内订单号加载订单数据(用于后台管理)
	 * @param outTradeNo
	 * @return
	 */
	public ProductOrder loadByTradeNo(String outTradeNo);
	
	/**
	 * 根据微支付的交易id加载订单信息
	 * @param transactionId
	 * @return
	 */
	public ProductOrder loadByTransactionId(String transactionId);
	
	
	/**
	 * 已收到支付通知（更新订单状态&其中的微信id）
	 * @param status
	 * @return
	 */
	public int markNotifyReceived(short payType, String outTradeNo, String wxTransId);
	
	/**
	 * 标记为等待发货
	 * @param outTradeNo
	 * @param status
	 * @return
	 */
	public int markWaitingDelivery(String outTradeNo);
	
	
	
	/**
	 * 第三方发货成功，更新订单状态为已发货
	 * @param status
	 * @return
	 */
	public int markDelivered(String outTradeNo);

	
}
