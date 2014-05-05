package com.bruce.geekway.model;

import java.util.List;

public class ItoProductBase {
	
	/**
	 * product对应的所有sku信息
	 */
	private List<ItoSku> productSkus;

	public List<ItoSku> getProductSkus() {
		return productSkus;
	}

	public void setProductSkus(List<ItoSku> productSkus) {
		this.productSkus = productSkus;
	}
	
	
}
