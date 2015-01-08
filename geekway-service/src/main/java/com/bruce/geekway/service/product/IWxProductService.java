package com.bruce.geekway.service.product;

import java.util.List;

import com.bruce.foundation.model.paging.PagingResult;
import com.bruce.foundation.service.IFoundationPagingService;
import com.bruce.geekway.model.WxProduct;
import com.bruce.geekway.model.WxProductCriteria;

public interface IWxProductService extends IFoundationPagingService<WxProduct, Integer, WxProductCriteria>{

	
	public WxProduct loadCachedById(Integer id);
	
	
	/**
	 * 获取正在销售的产品系列
	 * @return
	 */
	public List<WxProduct> queryAvailableList();
	
	
	/**
	 * 根据tagId查询匹配的商品列表
	 * @return
	 */
//	public List<WxProduct> queryProductsByTagId(int tagId);
//	
//	public List<WxProduct> queryProductsOutTagId(int tagId);
	
	/*后台使用*/
	public PagingResult<WxProduct> pagingTagProductsByCriteria(int tagId, int pageNo, int pageSize, WxProductCriteria criteria);
	/*后台使用*/
	public PagingResult<WxProduct> pagingTagOutProductsByCriteria(int tagId, int pageNo, int pageSize, WxProductCriteria criteria);

	//前台分页查询tag商品
	public List<WxProduct> queryProductsByTagId(int tagId, int tailId, int limit);
	
	//前台分页查询缓存tag商品
	public List<WxProduct> queryCachedProductsByTagId(int tagId, int tailId, int limit);
	
	public List<WxProduct> queryProductsByCategoryId(int categoryId, int pageNo, int pageSize);
	
	public List<WxProduct> queryCachedProductsByCategoryId(int categoryId, int pageNo, int pageSize);

	
	//瀑布流方式加载tag产品
//	public List<WxProduct> fallLoadProductsByTag(int tagId, int tailId, int limit);
	
	//瀑布流方式加载缓存中的tag产品
//	public List<WxProduct> fallLoadCachedProductsByTag(int tagId, int tailId, int limit); 
}