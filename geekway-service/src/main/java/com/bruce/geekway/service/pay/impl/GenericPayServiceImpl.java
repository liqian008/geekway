package com.bruce.geekway.service.pay.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import com.bruce.geekway.model.ProductOrder;
import com.bruce.geekway.model.ProductOrderItem;
import com.bruce.geekway.model.enumeration.GeekwayEnum;
import com.bruce.geekway.service.pay.IGenericPayService;
import com.bruce.geekway.service.product.ICounterService;
import com.bruce.geekway.service.product.IProductOrderItemService;
import com.bruce.geekway.service.product.IProductOrderService;
import com.bruce.geekway.service.product.IProductSkuService;

/**
 * 支付通用处理service（负责维护、更新订单状态&扣减商品库存）
 * 
 * @author liqian
 */
public class GenericPayServiceImpl implements IGenericPayService{

	@Autowired
	private IProductOrderService productOrderService;
	@Autowired
	private IProductOrderItemService productOrderItemService;
	@Autowired
	private IProductSkuService productSkuService;
	@Autowired
	private ICounterService counterService;

	/**
	 * 支付成功，记录订单流水表，且更新系统订单状态+库存（事务操作）
	 * @param payType
	 * @param outTradeNo
	 * @param transactionId
	 * @return
	 */
	public synchronized int processPayNotify(short payType, String outTradeNo, String transactionId) {
		int result = 0;
		ProductOrder productOrder = productOrderService.loadByTradeNo(outTradeNo);
		if (productOrder != null && productOrder.getId() != null) {// 有效的订单信息
			//检查订单是否已经是成功状态（已发货或已完成）,防止重复的情况补单
			boolean alreadySuccess = GeekwayEnum.ProductOrderStatusEnum.COMPLETED.getStatus()==productOrder.getStatus()||GeekwayEnum.ProductOrderStatusEnum.DELIVERED.getStatus()==productOrder.getStatus();
			if(alreadySuccess){//之前已经是成功状态，则不做任何处理（可能情况：首次交易成功了，但因超时或其他未及时拿到到reponse，然后进行的重试）
				return 1;
			}else{//非重单的情况
				//更新本系统内的订单状态为已支付
				result = productOrderService.markNotifyReceived(payType, outTradeNo, transactionId);
				if(result>0){
					List<ProductOrderItem> orderItemList = productOrderItemService.queryByTradeNo(outTradeNo);
					// 遍历单条订单，扣减sku商品的库存数
					if (orderItemList != null && orderItemList.size() > 0) {
						for (ProductOrderItem orderItem : orderItemList) {
							int productSkuId = orderItem.getProductSkuId();
							int amount = orderItem.getAmount();
							// 执行扣减
							result = counterService.reduceProductSkuStock(productSkuId, amount); 
							//如果虚拟商品，可能无需扣减库存数
						}
					}
					//更新订单状态为待发货
					result = productOrderService.markWaitingDelivery(outTradeNo);
					return result;
				}
			}
		}
		return 0;
	}

}
