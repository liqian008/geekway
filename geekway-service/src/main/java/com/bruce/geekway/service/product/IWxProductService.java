package com.bruce.geekway.service.product;

import java.util.List;

import com.bruce.foundation.service.IFoundationPagingService;
import com.bruce.geekway.model.WxProduct;
import com.bruce.geekway.model.WxProductCriteria;

public interface IWxProductService extends IFoundationPagingService<WxProduct, Integer, WxProductCriteria>{

	/**
	 * 获取正在销售的产品系列
	 * @return
	 */
	public List<WxProduct> queryAvailableList();
	
	
	/**
	 * 根据tagId查询匹配的商品列表
	 * @return
	 */
	public List<WxProduct> queryProductsByTagId(int tagId);
	
	public List<WxProduct> queryProductsOutTagId(int tagId);

}