package com.bruce.geekway.service.product;

import java.util.List;

import com.bruce.foundation.service.IFoundationService;
import com.bruce.geekway.model.WxProductSku;
import com.bruce.geekway.model.WxProductSkuCriteria;

public interface IWxProductSkuService extends IFoundationService<WxProductSku, Integer, WxProductSkuCriteria>{
	
	public List<WxProductSku> queryAllByProductId(int productId);
	
	public WxProductSku loadProductSku(int productId, int skuId);

	/**
	 * 组查询productSku，供搜索使用
	 * @param example
	 * @return
	 */
	List<WxProductSku> fallLoadCategoryProductSkuList(int categoryId, int productTailId, int limit);
	
//	public int queryCountBySkuPropValueId(int skuPropValueId);
	
}