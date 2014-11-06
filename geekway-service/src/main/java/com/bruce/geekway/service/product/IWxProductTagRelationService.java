package com.bruce.geekway.service.product;

import com.bruce.foundation.service.IFoundationPagingService;
import com.bruce.geekway.model.WxProductTagRelation;
import com.bruce.geekway.model.WxProductTagRelationCriteria;

/**
 * 多图文管理
 * @author liqian
 *
 */
public interface IWxProductTagRelationService extends IFoundationPagingService<WxProductTagRelation, Long, WxProductTagRelationCriteria>{

	/**
	 * 排序置顶
	 * @param tagId
	 * @param productId
	 * @return
	 */
	public int topProduct(int tagId, int productId);
	
	public int deleteByTagId(int tagId);
	
	public int deleteByProductId(int productId);
	
	public int delete(int tagId, int productId);
	
	
}