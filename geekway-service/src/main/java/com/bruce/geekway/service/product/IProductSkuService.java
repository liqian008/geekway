package com.bruce.geekway.service.product;

import java.util.List;

import com.bruce.foundation.service.IFoundationService;
import com.bruce.geekway.model.ProductSku;
import com.bruce.geekway.model.ProductSkuCriteria;

public interface IProductSkuService extends IFoundationService<ProductSku, Integer, ProductSkuCriteria>{
	
	public List<ProductSku> querySkuListByProductId(int productId);
	
	/*cacheable query*/
	public List<ProductSku> queryCachedSkuListByProductId(int productId);
	
	public ProductSku loadProductSku(int productId, int skuId);
	/*cacheable query*/
	public ProductSku loadCachedProductSku(int productId, int skuId);

	/**
	 * 组查询productSku，供搜索使用
	 * @param example
	 * @return
	 */
//	public List<WxProductSku> fallLoadCategoryProductSkuList(int categoryId, int productTailId, int limit);
	
	/**
	 * 使用缓存的组查询productSku
	 * @return
	 */
//	public List<WxProductSku> fallLoadCachedCategoryProductSkuList(int categoryId, int productTailId, int limit);

	
	
//	public int queryCountBySkuPropValueId(int skuPropValueId);
	
}