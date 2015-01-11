package com.bruce.geekway.service.product;

import com.bruce.foundation.service.IFoundationPagingService;
import com.bruce.geekway.model.ProductCategory;
import com.bruce.geekway.model.ProductCategoryCriteria;

/**
 * 
 * @author liqian
 *
 */
public interface IProductCategoryService extends IFoundationPagingService<ProductCategory, Integer, ProductCategoryCriteria>{
	
	/*加载缓存中的category*/
	public ProductCategory loadCachedById(Integer id); 

}