package com.bruce.geekway.dao.mapper;

import com.bruce.geekway.model.Product;
import com.bruce.geekway.model.ProductCriteria;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface ProductMapper {

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table tb_product
	 * @mbggenerated  Tue Jan 13 16:05:27 CST 2015
	 */
	int countByExample(ProductCriteria example);

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table tb_product
	 * @mbggenerated  Tue Jan 13 16:05:27 CST 2015
	 */
	int deleteByExample(ProductCriteria example);

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table tb_product
	 * @mbggenerated  Tue Jan 13 16:05:27 CST 2015
	 */
	int deleteByPrimaryKey(Integer id);

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table tb_product
	 * @mbggenerated  Tue Jan 13 16:05:27 CST 2015
	 */
	int insert(Product record);

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table tb_product
	 * @mbggenerated  Tue Jan 13 16:05:27 CST 2015
	 */
	int insertSelective(Product record);

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table tb_product
	 * @mbggenerated  Tue Jan 13 16:05:27 CST 2015
	 */
	List<Product> selectByExample(ProductCriteria example);

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table tb_product
	 * @mbggenerated  Tue Jan 13 16:05:27 CST 2015
	 */
	Product selectByPrimaryKey(Integer id);

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table tb_product
	 * @mbggenerated  Tue Jan 13 16:05:27 CST 2015
	 */
	int updateByExampleSelective(@Param("record") Product record,
			@Param("example") ProductCriteria example);

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table tb_product
	 * @mbggenerated  Tue Jan 13 16:05:27 CST 2015
	 */
	int updateByExample(@Param("record") Product record,
			@Param("example") ProductCriteria example);

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table tb_product
	 * @mbggenerated  Tue Jan 13 16:05:27 CST 2015
	 */
	int updateByPrimaryKeySelective(Product record);

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table tb_product
	 * @mbggenerated  Tue Jan 13 16:05:27 CST 2015
	 */
	int updateByPrimaryKey(Product record);

	List<Product> queryProductsByCategoryId(@Param("criteria")ProductCriteria criteria, @Param("categoryId")int categoryId);
	
	//count product by tagId
	int countProductsByTagId(@Param("criteria")ProductCriteria criteria, @Param("tagId")int tagId);
	
	List<Product> queryProductsByTagId(@Param("criteria")ProductCriteria criteria, @Param("tagId")int tagId);
	
	int countProductsOutByTagId(@Param("criteria")ProductCriteria criteria, @Param("tagId")int tagId);
	
	List<Product> queryProductsOutByTagId(@Param("criteria")ProductCriteria criteria, @Param("tagId")int tagId);
	
	//瀑布流方式加载更多tag的产品列表
//	@Deprecated
//	List<WxProduct> fallLoadProductsByTag(@Param("tagId")int tagId, @Param("tailId")int tailId, @Param("limit")int limit);

	
}