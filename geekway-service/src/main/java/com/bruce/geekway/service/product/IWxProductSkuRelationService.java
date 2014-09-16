package com.bruce.geekway.service.product;

import com.bruce.foundation.service.IFoundationService;
import com.bruce.geekway.model.WxProductSkuRelation;
import com.bruce.geekway.model.WxProductSkuRelationCriteria;

public interface IWxProductSkuRelationService extends IFoundationService<WxProductSkuRelation, Long, WxProductSkuRelationCriteria>{

	/**
	 * 删除前检查skuPropValueId是否有关联的产品
	 * @param skuPropValueId
	 * @return
	 */
	public int queryCountBySkuPropValueId(int skuPropValueId);
}