package com.bruce.geekway.service.pay;


/**
 * 支付通用处理service（负责维护、更新订单状态&扣减商品库存）
 * 
 * @author liqian
 * 
 */
public interface IGenericPayService {

	public int processPayNotify(short payType, String outTradeNo, String transactionId);
}
