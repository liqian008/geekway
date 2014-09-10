package com.bruce.geekway.service.ito;

import java.util.List;

import com.bruce.foundation.service.IFoundationService;
import com.bruce.geekway.model.ItoProduct;
import com.bruce.geekway.model.ItoProductCriteria;

public interface IItoProductService extends IFoundationService<ItoProduct, Integer, ItoProductCriteria>{

	/**
	 * 获取正在销售的产品系列
	 * @return
	 */
	public List<ItoProduct> queryAvailableList();
}