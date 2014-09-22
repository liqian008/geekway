package com.bruce.geekway.dao.mapper;

import com.bruce.geekway.model.WxProductSku;
import com.bruce.geekway.model.WxProductSkuCriteria;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface WxProductSkuMapper {

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table wx_product_sku
	 * @mbggenerated  Mon Sep 22 17:06:28 CST 2014
	 */
	int countByExample(WxProductSkuCriteria example);

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table wx_product_sku
	 * @mbggenerated  Mon Sep 22 17:06:28 CST 2014
	 */
	int deleteByExample(WxProductSkuCriteria example);

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table wx_product_sku
	 * @mbggenerated  Mon Sep 22 17:06:28 CST 2014
	 */
	int deleteByPrimaryKey(Integer id);

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table wx_product_sku
	 * @mbggenerated  Mon Sep 22 17:06:28 CST 2014
	 */
	int insert(WxProductSku record);

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table wx_product_sku
	 * @mbggenerated  Mon Sep 22 17:06:28 CST 2014
	 */
	int insertSelective(WxProductSku record);

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table wx_product_sku
	 * @mbggenerated  Mon Sep 22 17:06:28 CST 2014
	 */
	List<WxProductSku> selectByExample(WxProductSkuCriteria example);

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table wx_product_sku
	 * @mbggenerated  Mon Sep 22 17:06:28 CST 2014
	 */
	WxProductSku selectByPrimaryKey(Integer id);

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table wx_product_sku
	 * @mbggenerated  Mon Sep 22 17:06:28 CST 2014
	 */
	int updateByExampleSelective(@Param("record") WxProductSku record,
			@Param("example") WxProductSkuCriteria example);

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table wx_product_sku
	 * @mbggenerated  Mon Sep 22 17:06:28 CST 2014
	 */
	int updateByExample(@Param("record") WxProductSku record,
			@Param("example") WxProductSkuCriteria example);

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table wx_product_sku
	 * @mbggenerated  Mon Sep 22 17:06:28 CST 2014
	 */
	int updateByPrimaryKeySelective(WxProductSku record);

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table wx_product_sku
	 * @mbggenerated  Mon Sep 22 17:06:28 CST 2014
	 */
	int updateByPrimaryKey(WxProductSku record);

	/**
	 * 组查询productSku，供搜索使用
	 * @param example
	 * @return
	 */
	List<WxProductSku> fallLoadCategoryProductSkuList(@Param("categoryId") int categoryId, @Param("productTailId") int productTailId, @Param("limit") int limit);
	
}