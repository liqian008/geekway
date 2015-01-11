package com.bruce.geekway.service.product;

import com.bruce.foundation.service.IFoundationPagingService;
import com.bruce.geekway.model.ProductTag;
import com.bruce.geekway.model.ProductTagCriteria;

/**
 * 
 * @author liqian
 *
 */
public interface IProductTagService extends IFoundationPagingService<ProductTag, Integer, ProductTagCriteria>{
	
	/*加载缓存中的tag*/
	public ProductTag loadCachedById(Integer id);
	
}