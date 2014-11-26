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