package com.bruce.geekway.model;

import java.util.List;

public class ItoProductBase {
	
	/**
	 *	增加一个是否是封面属性（主要为客户端提供的，用于区分ProductCover表中的数据），0为否，1为是
	 */
	private short isCover;
	
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

	public short getIsCover() {
		return isCover;
	}

	public void setIsCover(short isCover) {
		this.isCover = isCover;
	}

	
}
