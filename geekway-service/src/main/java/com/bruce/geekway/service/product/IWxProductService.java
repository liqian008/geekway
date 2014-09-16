package com.bruce.geekway.service.product;

import java.util.List;

import com.bruce.foundation.service.IFoundationService;
import com.bruce.geekway.model.WxProduct;
import com.bruce.geekway.model.WxProductCriteria;

public interface IWxProductService extends IFoundationService<WxProduct, Integer, WxProductCriteria>{

	/**
	 * 获取正在销售的产品系列
	 * @return
	 */
	public List<WxProduct> queryAvailableList();
}