package com.bruce.geekway.dao.mapper;

import com.bruce.geekway.model.ItoProductOrder;
import com.bruce.geekway.model.ItoProductOrderCriteria;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface ItoProductOrderMapper {

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table ito_product_order
	 * @mbggenerated  Sun May 11 10:54:19 CST 2014
	 */
	int countByExample(ItoProductOrderCriteria example);

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table ito_product_order
	 * @mbggenerated  Sun May 11 10:54:19 CST 2014
	 */
	int deleteByExample(ItoProductOrderCriteria example);

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table ito_product_order
	 * @mbggenerated  Sun May 11 10:54:19 CST 2014
	 */
	int deleteByPrimaryKey(Integer id);

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table ito_product_order
	 * @mbggenerated  Sun May 11 10:54:19 CST 2014
	 */
	int insert(ItoProductOrder record);

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table ito_product_order
	 * @mbggenerated  Sun May 11 10:54:19 CST 2014
	 */
	int insertSelective(ItoProductOrder record);

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table ito_product_order
	 * @mbggenerated  Sun May 11 10:54:19 CST 2014
	 */
	List<ItoProductOrder> selectByExample(ItoProductOrderCriteria example);

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table ito_product_order
	 * @mbggenerated  Sun May 11 10:54:19 CST 2014
	 */
	ItoProductOrder selectByPrimaryKey(Integer id);

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table ito_product_order
	 * @mbggenerated  Sun May 11 10:54:19 CST 2014
	 */
	int updateByExampleSelective(@Param("record") ItoProductOrder record, @Param("example") ItoProductOrderCriteria example);

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table ito_product_order
	 * @mbggenerated  Sun May 11 10:54:19 CST 2014
	 */
	int updateByExample(@Param("record") ItoProductOrder record, @Param("example") ItoProductOrderCriteria example);

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table ito_product_order
	 * @mbggenerated  Sun May 11 10:54:19 CST 2014
	 */
	int updateByPrimaryKeySelective(ItoProductOrder record);

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table ito_product_order
	 * @mbggenerated  Sun May 11 10:54:19 CST 2014
	 */
	int updateByPrimaryKey(ItoProductOrder record);
}