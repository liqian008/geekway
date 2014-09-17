package com.bruce.geekway.service.product;

import java.util.List;

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
	
	/**
	 * 根据商品id查询其与skuPropValue的关联 
	 * @param productId
	 * @return
	 */
	public List<WxProductSkuRelation> queryByProductId(int productId);
	
	/**
	 * 根据productId删除关联
	 * @param productId
	 * @return
	 */
	public int deleteByProductId(int productId);
	
	/**
	 * 保存关联
	 * @param productId
	 * @param skuPropValueIds
	 * @return
	 */
	public int saveProductSkuRelations(int productId, List<Integer> skuPropValueIds);
}