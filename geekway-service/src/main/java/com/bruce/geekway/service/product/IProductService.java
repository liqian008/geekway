package com.bruce.geekway.service.product;

import java.util.List;

import com.bruce.foundation.model.paging.PagingResult;
import com.bruce.foundation.service.IFoundationPagingService;
import com.bruce.geekway.model.Product;
import com.bruce.geekway.model.ProductCriteria;

public interface IProductService extends IFoundationPagingService<Product, Integer, ProductCriteria>{

	/**
	 * 从缓存种读取（无缓存则读db）
	 * @param id
	 * @return
	 */
	public Product loadCachedById(Integer id);
	
	/**
	 * 获取正在销售的产品系列
	 * @return
	 */
	public List<Product> queryAvailableList();
	
	
	/**
	 * 根据tagId查询匹配的商品列表
	 * @return
	 */
//	public List<WxProduct> queryProductsByTagId(int tagId);
//	
//	public List<WxProduct> queryProductsOutTagId(int tagId);
	
	/*后台使用*/
	public PagingResult<Product> pagingTagProductsByCriteria(int tagId, int pageNo, int pageSize, ProductCriteria criteria);
	/*后台使用*/
	public PagingResult<Product> pagingTagOutProductsByCriteria(int tagId, int pageNo, int pageSize, ProductCriteria criteria);

	//前台分页查询tag商品
	public List<Product> queryProductsByTagId(int tagId, int pageNo, int pageSize, boolean isFallload);
	
	//前台分页查询缓存tag商品
	public List<Product> queryCachedProductsByTagId(int tagId, int pageNo, int pageSize, boolean isFallload);
	
	//前台分页查询category商品
	public List<Product> queryProductsByCategoryId(int categoryId, int pageNo, int pageSize, boolean isFallload);
	//前台分页查询缓存category商品
	public List<Product> queryCachedProductsByCategoryId(int categoryId, int pageNo, int pageSize, boolean isFallload);


	
	
	//瀑布流方式加载tag产品
//	public List<WxProduct> fallLoadProductsByTag(int tagId, int tailId, int limit);
	
	//瀑布流方式加载缓存中的tag产品
//	public List<WxProduct> fallLoadCachedProductsByTag(int tagId, int tailId, int limit); 
}