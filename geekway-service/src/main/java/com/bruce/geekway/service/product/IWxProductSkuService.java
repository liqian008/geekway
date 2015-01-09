package com.bruce.geekway.service.product;

import java.util.List;

import com.bruce.foundation.service.IFoundationService;
import com.bruce.geekway.model.WxProductSku;
import com.bruce.geekway.model.WxProductSkuCriteria;

public interface IWxProductSkuService extends IFoundationService<WxProductSku, Integer, WxProductSkuCriteria>{
	
	public List<WxProductSku> querySkuListByProductId(int productId);
	
	/*cacheable query*/
	public List<WxProductSku> queryCachedSkuListByProductId(int productId);
	
	public WxProductSku loadProductSku(int productId, int skuId);
	/*cacheable query*/
	public WxProductSku loadCachedProductSku(int productId, int skuId);

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