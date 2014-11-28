package com.bruce.geekway.service.pay;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.bruce.geekway.model.exception.ErrorCode;
import com.bruce.geekway.model.exception.GeekwayException;
import com.bruce.geekway.service.product.IWxProductOrderService;

/**
 * 支付宝支付service
 * 
 * @author liqian
 * 
 */
public class AlipayService {

	private static final Logger alipayNotifyLogger = LoggerFactory.getLogger("alipayNotifyLogger");

	@Autowired
	private IWxProductOrderService wxProductOrderService;
	@Autowired
	private IGenericPayService genericPayService;

	/**
	 * 支付宝支付成功，且更新系统订单状态+库存（事务操作）
	 * 
	 * @param wxNotifyOrder
	 * @return
	 */
	@Transactional(propagation = Propagation.REQUIRES_NEW)
	public int receiveAlipayOrder(String outTradeNo, String transId) {
		if (outTradeNo == null) {
			throw new GeekwayException(ErrorCode.SYSTEM_ERROR);
		}
		//TODO 入支付宝流水库表
		int result = genericPayService.processPayNotify((short)1, outTradeNo, transId);
		
		return result;
	}

	public IWxProductOrderService getWxProductOrderService() {
		return wxProductOrderService;
	}

	public void setWxProductOrderService(
			IWxProductOrderService wxProductOrderService) {
		this.wxProductOrderService = wxProductOrderService;
	}

	public IGenericPayService getGenericPayService() {
		return genericPayService;
	}

	public void setGenericPayService(IGenericPayService genericPayService) {
		this.genericPayService = genericPayService;
	}


	
}
