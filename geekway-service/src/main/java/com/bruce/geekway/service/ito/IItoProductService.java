package com.bruce.geekway.service.ito;

import java.util.List;

import com.bruce.geekway.model.ItoProduct;
import com.bruce.geekway.service.IBaseService;

public interface IItoProductService extends IBaseService<ItoProduct, Integer>{

	/**
	 * 获取正在销售的产品系列
	 * @return
	 */
	public List<ItoProduct> queryAvailableList();
}