package com.bruce.geekway.service.product;

import com.bruce.foundation.service.IFoundationPagingService;
import com.bruce.geekway.model.WxProductCategory;
import com.bruce.geekway.model.WxProductCategoryCriteria;

/**
 * 
 * @author liqian
 *
 */
public interface IWxProductCategoryService extends IFoundationPagingService<WxProductCategory, Integer, WxProductCategoryCriteria>{
	
	/*加载缓存中的category*/
	public WxProductCategory loadCachedById(Integer id); 

}