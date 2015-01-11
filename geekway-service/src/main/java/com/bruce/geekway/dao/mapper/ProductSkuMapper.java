package com.bruce.geekway.dao.mapper;

import com.bruce.geekway.model.ProductSku;
import com.bruce.geekway.model.ProductSkuCriteria;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface ProductSkuMapper {

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table wx_product_sku
	 * @mbggenerated  Sun Jan 11 19:43:57 CST 2015
	 */
	int countByExample(ProductSkuCriteria example);


	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table wx_product_sku
	 * @mbggenerated  Sun Jan 11 19:43:57 CST 2015
	 */
	int deleteByExample(ProductSkuCriteria example);


	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table wx_product_sku
	 * @mbggenerated  Sun Jan 11 19:43:57 CST 2015
	 */
	int deleteByPrimaryKey(Integer id);


	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table wx_product_sku
	 * @mbggenerated  Sun Jan 11 19:43:57 CST 2015
	 */
	int insert(ProductSku record);


	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table wx_product_sku
	 * @mbggenerated  Sun Jan 11 19:43:57 CST 2015
	 */
	int insertSelective(ProductSku record);


	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table wx_product_sku
	 * @mbggenerated  Sun Jan 11 19:43:57 CST 2015
	 */
	List<ProductSku> selectByExample(ProductSkuCriteria example);


	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table wx_product_sku
	 * @mbggenerated  Sun Jan 11 19:43:57 CST 2015
	 */
	ProductSku selectByPrimaryKey(Integer id);


	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table wx_product_sku
	 * @mbggenerated  Sun Jan 11 19:43:57 CST 2015
	 */
	int updateByExampleSelective(@Param("record") ProductSku record,
			@Param("example") ProductSkuCriteria example);


	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table wx_product_sku
	 * @mbggenerated  Sun Jan 11 19:43:57 CST 2015
	 */
	int updateByExample(@Param("record") ProductSku record,
			@Param("example") ProductSkuCriteria example);


	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table wx_product_sku
	 * @mbggenerated  Sun Jan 11 19:43:57 CST 2015
	 */
	int updateByPrimaryKeySelective(ProductSku record);


	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table wx_product_sku
	 * @mbggenerated  Sun Jan 11 19:43:57 CST 2015
	 */
	int updateByPrimaryKey(ProductSku record);


//	public List<WxProductSku> fallLoadCategoryProductSkuList(@Param("categoryId") int categoryId, @Param("productTailId") int productTailId, @Param("limit") int limit);
	
	
	public int incrStock(@Param("id") long id, @Param("stock") int stock);
	
	public int reduceStock(@Param("id") long id, @Param("stock") int stock);
}