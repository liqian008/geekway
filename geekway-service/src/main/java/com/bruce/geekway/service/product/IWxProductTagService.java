package com.bruce.geekway.service.product;

import com.bruce.foundation.service.IFoundationPagingService;
import com.bruce.geekway.model.WxProductTag;
import com.bruce.geekway.model.WxProductTagCriteria;

/**
 * 
 * @author liqian
 *
 */
public interface IWxProductTagService extends IFoundationPagingService<WxProductTag, Integer, WxProductTagCriteria>{
	
	/*加载缓存中的tag*/
	public WxProductTag loadCachedById(Integer id);
	
}