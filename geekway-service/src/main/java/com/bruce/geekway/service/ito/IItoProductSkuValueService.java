package com.bruce.geekway.service.ito;

import com.bruce.geekway.model.ItoProductSkuValue;
import com.bruce.geekway.service.IBaseService;

public interface IItoProductSkuValueService extends IBaseService<ItoProductSkuValue, Integer>{

	/**
	 * 删除前检查skuPropValueId是否有关联的产品
	 * @param skuPropValueId
	 * @return
	 */
	public int queryCountBySkuPropValueId(int skuPropValueId);
}