package com.bruce.geekway.service.ito;

import com.bruce.foundation.service.IFoundationService;
import com.bruce.geekway.model.ItoProductSkuValue;
import com.bruce.geekway.model.ItoProductSkuValueCriteria;

public interface IItoProductSkuValueService extends IFoundationService<ItoProductSkuValue, Integer, ItoProductSkuValueCriteria>{

	/**
	 * 删除前检查skuPropValueId是否有关联的产品
	 * @param skuPropValueId
	 * @return
	 */
	public int queryCountBySkuPropValueId(int skuPropValueId);
}