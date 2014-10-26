package com.bruce.geekway.service.product;

import java.util.List;

import com.bruce.foundation.service.IFoundationService;
import com.bruce.geekway.model.WxProductOrder;
import com.bruce.geekway.model.WxProductOrderCriteria;
import com.bruce.geekway.model.WxUserAddress;

/**
 * 订单service
 * @author liqian
 *
 */
public interface IWxProductOrderService extends IFoundationService<WxProductOrder, Long, WxProductOrderCriteria>{
	
	public static enum StatusEnum{
		//可能还需要其他流程，如维权，退款等
		SUBMITED((short)1, "待支付"), PAYED((short)2, "待发货"), DELIVERED((short)3, "待收货"), COMPLETED((short)100, "完成"), UNKNOWN((short)0, "关闭");
		
		private short status;
		private String name;
		StatusEnum(short status, String name){
			this.status = status;
			this.name = name;
		}

		public StatusEnum valueOf(short status){
			StatusEnum[] statusArray  = StatusEnum.values();
			for(StatusEnum orderStatus : statusArray){
				if(status == orderStatus.status){
					return orderStatus;
				}
			}
			return null;
		}
		
		public short getStatus() {
			return status;
		}
		
		public String getName() {
			return name;
		}
		
	}
	
	
	/**
	 * 查询我的订单详情
	 * @param userOpenId
	 * @param orderId
	 * @param tradeNo
	 * @return
	 */
	public WxProductOrder loadUserOrderInfo(String userOpenId, long orderId, String tradeNo);
	
	/**
	 * 查询我的订单列表
	 * @param userOpenId
	 * @param voucherTailId
	 * @param limit
	 * @return
	 */
	public List<WxProductOrder> fallLoadUserOrderList(String userOpenId, long orderTailId, int limit);
	
	
	/**
	 * 创建订单，等待用户支付
	 * @param outTradeNo
	 * @param status
	 * @return
	 */
	public int createOrder(WxProductOrder productOrder, WxUserAddress userAddressInfo);
	
	
	/**
	 * 更新订单状态
	 * @param status
	 * @return
	 */
	public int changeOrderStatus(String outTradeNo, short status);
	
	/**
	 * 根据订单号加载订单数据
	 * @param outTradeNo
	 * @return
	 */
	public WxProductOrder loadByTradeNo(String outTradeNo);
	
	
}
