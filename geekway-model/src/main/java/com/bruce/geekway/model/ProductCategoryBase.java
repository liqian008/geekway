package com.bruce.geekway.model;

import java.util.List;

public class ProductCategoryBase {
	
	
	private List<Product> categoryProductList;

	public List<Product> getCategoryProductList() {
		return categoryProductList;
	}

	public void setCategoryProductList(List<Product> categoryProductList) {
		this.categoryProductList = categoryProductList;
	}

//	private List<SlideImage> slideImageList;
//
//	public List<SlideImage> getSlideImageList() {
//		return slideImageList;
//	}
//
//	public void setSlideImageList(List<SlideImage> slideImageList) {
//		this.slideImageList = slideImageList;
//	}
	
}
