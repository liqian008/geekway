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
	 * 查询我的优惠券
	 * @param userOpenId
	 * @param voucherTailId
	 * @param limit
	 * @return
	 */
	public List<WxProductVoucher> fallLoadUserVoucherList(String userOpenId, long voucherTailId, int limit);
	
	
}