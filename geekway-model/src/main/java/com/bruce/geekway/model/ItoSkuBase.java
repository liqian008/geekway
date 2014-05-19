package com.bruce.geekway.model;

import java.util.List;

/**
 * 基类，主要用于构造sku某属性下对应的skuImageList属性值
 * @author liqian
 *
 */
public class ItoSkuBase {
	
	
	private List<ItoSkuImage> skuImageList;

	public List<ItoSkuImage> getSkuImageList() {
		return skuImageList;
	}

	public void setSkuImageList(List<ItoSkuImage> skuImageList) {
		this.skuImageList = skuImageList;
	}
	
	

}
