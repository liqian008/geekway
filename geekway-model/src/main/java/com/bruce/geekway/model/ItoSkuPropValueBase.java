package com.bruce.geekway.model;

import java.util.List;


public class ItoSkuPropValueBase {
	
	/* 用于级连的下级菜单，如光面材质的对应的下级颜色列表－>红色对应的下级尺码列表*/
	private List<ItoSkuPropValue> colorList;
	/* 用于级连的下级菜单，如光面材质的对应的下级颜色列表－>红色对应的下级尺码列表*/
	private List<ItoSkuPropValue> sizeList;
	public List<ItoSkuPropValue> getColorList() {
		return colorList;
	}
	public void setColorList(List<ItoSkuPropValue> colorList) {
		this.colorList = colorList;
	}
	public List<ItoSkuPropValue> getSizeList() {
		return sizeList;
	}
	public void setSizeList(List<ItoSkuPropValue> sizeList) {
		this.sizeList = sizeList;
	}
	
	
//	private List<ItoSkuPropValue> skuPropValueList;
//
//	public List<ItoSkuPropValue> getSkuPropValueList() {
//		return skuPropValueList;
//	}
//
//	public void setSkuPropValueList(List<ItoSkuPropValue> skuPropValueList) {
//		this.skuPropValueList = skuPropValueList;
//	}
	
	
}