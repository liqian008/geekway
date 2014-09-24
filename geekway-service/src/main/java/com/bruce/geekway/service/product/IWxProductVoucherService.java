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
	 * 验证优惠券是否可用
	 * @param voucherId
	 * @param userOpenId
	 * @return
	 */
	public boolean verifyVoucher(long voucherId, String userOpenId);
	
	
}