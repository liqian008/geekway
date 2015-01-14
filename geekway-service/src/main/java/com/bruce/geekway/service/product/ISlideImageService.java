package com.bruce.geekway.service.product;

import java.util.List;

import com.bruce.foundation.service.IFoundationPagingService;
import com.bruce.geekway.model.SlideImage;
import com.bruce.geekway.model.SlideImageCriteria;

/**
 * 图片service
 * @author liqian
 *
 */
public interface ISlideImageService extends IFoundationPagingService<SlideImage, Integer, SlideImageCriteria>{
	
	/*查询首页指定的图片*/
	public List<SlideImage> queryByIndex();
	/*查询缓存中首页指定的图片*/
	public List<SlideImage> queryCachedByIndex();
	
	/*查询指定skuId对应的图片*/
	public List<SlideImage> queryByProductSkuId(int productSkuId);
	/*查询缓存中指定skuId对应的图片*/
	public List<SlideImage> queryCachedByProductSkuId(int productSkuId);
	
	/*查询指定productId对应的图片*/
	public List<SlideImage> queryByProductId(int productSkuId);
	/*查询缓存中指定productId对应的图片*/
	public List<SlideImage> queryCachedByProductId(int productId);
	
	/*查询指定categoryId对应的图片*/
	public List<SlideImage> queryByCategoryId(int categoryId);
	/*查询缓存中指定categoryId对应的图片*/
	public List<SlideImage> queryCachedByCategoryId(int categoryId);
	
	/*查询指定tagId对应的图片*/
	public List<SlideImage> queryByTagId(int tagId);
	/*查询缓存中指定tagId对应的图片*/
	public List<SlideImage> queryCachedByTagId(int tagId);
	
	
}