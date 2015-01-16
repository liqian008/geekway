package com.bruce.geekway.service.pay.impl;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
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

	private static final Logger payLogger = LoggerFactory.getLogger("payLogger");
	
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
				payLogger.info("回调通知的订单已经处理过了，outTradeNo：" + outTradeNo);
				return 1;
			}else{//非重单的情况
				payLogger.info("回调通知的订单为新订单，outTradeNo：" + outTradeNo);
				//更新本系统内的订单状态为已支付
				result = productOrderService.markNotifyReceived(payType, outTradeNo, transactionId);
				if(result>0){
					payLogger.info("");
					List<ProductOrderItem> orderItemList = productOrderItemService.queryByTradeNo(outTradeNo);
					// 遍历单条订单，扣减sku商品的库存数
					if (orderItemList != null && orderItemList.size() > 0) {
						for (ProductOrderItem orderItem : orderItemList) {
							int productSkuId = orderItem.getProductSkuId();
							int amount = orderItem.getAmount();
							payLogger.info("对支付对商品库存进行扣减：productSkuId：" + productSkuId+", amount：" + amount);
							result = counterService.reduceProductSkuStock(productSkuId, amount); 
							//如果虚拟商品，则可能无需扣减库存数
						}
					}
					payLogger.info("更新订单状态为待发货：outTradeNo：" + outTradeNo);
					result = productOrderService.markWaitingDelivery(outTradeNo);
					return result;
				}
			}
		}
		return 0;
	}

}
