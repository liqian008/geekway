package com.bruce.geekway.service.product;

import java.util.List;

import com.bruce.foundation.service.IFoundationService;
import com.bruce.geekway.model.WxProductVoucher;
import com.bruce.geekway.model.WxProductVoucherCriteria;

/**
 * 优惠券service
 * @author liqian
 *
 */
public interface IWxProductVoucherService extends IFoundationService<WxProductVoucher, Long, WxProductVoucherCriteria>{
	
	public static enum StatusEnum{
		//可能还需要其他流程，如维权，退款等
		USED((short)2, "已使用"), AVAILABLE((short)1, "可用"), UNKNOWN((short)0, "冻结");
		
		private short status;
		private String name;
		StatusEnum(short status, String name){
			this.status = status;
			this.name = name;
		}

		public StatusEnum valueOf(short status){
			StatusEnum[] statusArray  = StatusEnum.values();
			for(StatusEnum statusEnum : statusArray){
				if(status == statusEnum.status){
					return statusEnum;
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
	 * 加载我的所有优惠券
	 * @param userOpenId
	 * @param voucherTailId
	 * @param limit
	 * @return
	 */
	public List<WxProductVoucher> fallLoadUserVoucherList(String userOpenId, long voucherTailId, int limit);
	
	/**
	 * 加载我的可用优惠券
	 * @param userOpenId
	 * @param limit
	 * @return
	 */
	public List<WxProductVoucher> queryUserAvailableVoucherList(String userOpenId, int limit);
	
	/**
	 * 加载单个优惠券信息
	 * @param userOpenId
	 * @param voucherId 
	 * @return
	 */
	public WxProductVoucher loadUserVoucherById(String userOpenId, long voucherId);
	
	/**
	 * 申请优惠券
	 * @param userOpenId
	 * @return
	 */
	public WxProductVoucher applyVoucher(String userOpenId);
	
	/**
	 * 验证优惠券是否可用
	 * @param userOpenId
	 * @param voucherId
	 * @return
	 */
	public boolean verifyVoucher(String userOpenId, long voucherId);
	
	/**
	 * 修改优惠券的状态
	 * @param userOpenId
	 * @param voucherId
	 * @param status
	 * @return
	 */
	public int changeStatus(String userOpenId, long voucherId, short status);
}