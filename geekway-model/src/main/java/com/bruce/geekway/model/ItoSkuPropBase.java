package com.bruce.geekway.model;

import java.util.List;

/**
 * 基类，主要用于构造skuProp某属性下对应的属性值
 * @author liqian
 *
 */
public class ItoSkuPropBase {
	
	
	private List<ItoSkuPropValue> skuPropValueList;

	public List<ItoSkuPropValue> getSkuPropValueList() {
		return skuPropValueList;
	}

	public void setSkuPropValueList(List<ItoSkuPropValue> skuPropValueList) {
		this.skuPropValueList = skuPropValueList;
	}
	
	

}
