package com.bruce.geekway.dao.mapper;

import com.bruce.geekway.model.ProductOrderItem;
import com.bruce.geekway.model.ProductOrderItemCriteria;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface ProductOrderItemMapper {

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table tb_product_order_item
	 * @mbggenerated  Wed Feb 04 17:31:12 CST 2015
	 */
	int countByExample(ProductOrderItemCriteria example);

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table tb_product_order_item
	 * @mbggenerated  Wed Feb 04 17:31:12 CST 2015
	 */
	int deleteByExample(ProductOrderItemCriteria example);

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table tb_product_order_item
	 * @mbggenerated  Wed Feb 04 17:31:12 CST 2015
	 */
	int deleteByPrimaryKey(Long id);

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table tb_product_order_item
	 * @mbggenerated  Wed Feb 04 17:31:12 CST 2015
	 */
	int insert(ProductOrderItem record);

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table tb_product_order_item
	 * @mbggenerated  Wed Feb 04 17:31:12 CST 2015
	 */
	int insertSelective(ProductOrderItem record);

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table tb_product_order_item
	 * @mbggenerated  Wed Feb 04 17:31:12 CST 2015
	 */
	List<ProductOrderItem> selectByExample(ProductOrderItemCriteria example);

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table tb_product_order_item
	 * @mbggenerated  Wed Feb 04 17:31:12 CST 2015
	 */
	ProductOrderItem selectByPrimaryKey(Long id);

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table tb_product_order_item
	 * @mbggenerated  Wed Feb 04 17:31:12 CST 2015
	 */
	int updateByExampleSelective(@Param("record") ProductOrderItem record,
			@Param("example") ProductOrderItemCriteria example);

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table tb_product_order_item
	 * @mbggenerated  Wed Feb 04 17:31:12 CST 2015
	 */
	int updateByExample(@Param("record") ProductOrderItem record,
			@Param("example") ProductOrderItemCriteria example);

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table tb_product_order_item
	 * @mbggenerated  Wed Feb 04 17:31:12 CST 2015
	 */
	int updateByPrimaryKeySelective(ProductOrderItem record);

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table tb_product_order_item
	 * @mbggenerated  Wed Feb 04 17:31:12 CST 2015
	 */
	int updateByPrimaryKey(ProductOrderItem record);
}