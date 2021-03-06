package com.bruce.geekway.dao.mapper;

import com.bruce.geekway.model.ItoSkuProp;
import com.bruce.geekway.model.ItoSkuPropCriteria;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface ItoSkuPropMapper {

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table ito_sku_prop
	 * @mbggenerated  Sat May 03 14:51:30 CST 2014
	 */
	int countByExample(ItoSkuPropCriteria example);

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table ito_sku_prop
	 * @mbggenerated  Sat May 03 14:51:30 CST 2014
	 */
	int deleteByExample(ItoSkuPropCriteria example);

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table ito_sku_prop
	 * @mbggenerated  Sat May 03 14:51:30 CST 2014
	 */
	int deleteByPrimaryKey(Integer id);

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table ito_sku_prop
	 * @mbggenerated  Sat May 03 14:51:30 CST 2014
	 */
	int insert(ItoSkuProp record);

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table ito_sku_prop
	 * @mbggenerated  Sat May 03 14:51:30 CST 2014
	 */
	int insertSelective(ItoSkuProp record);

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table ito_sku_prop
	 * @mbggenerated  Sat May 03 14:51:30 CST 2014
	 */
	List<ItoSkuProp> selectByExample(ItoSkuPropCriteria example);

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table ito_sku_prop
	 * @mbggenerated  Sat May 03 14:51:30 CST 2014
	 */
	ItoSkuProp selectByPrimaryKey(Integer id);

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table ito_sku_prop
	 * @mbggenerated  Sat May 03 14:51:30 CST 2014
	 */
	int updateByExampleSelective(@Param("record") ItoSkuProp record, @Param("example") ItoSkuPropCriteria example);

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table ito_sku_prop
	 * @mbggenerated  Sat May 03 14:51:30 CST 2014
	 */
	int updateByExample(@Param("record") ItoSkuProp record, @Param("example") ItoSkuPropCriteria example);

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table ito_sku_prop
	 * @mbggenerated  Sat May 03 14:51:30 CST 2014
	 */
	int updateByPrimaryKeySelective(ItoSkuProp record);

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table ito_sku_prop
	 * @mbggenerated  Sat May 03 14:51:30 CST 2014
	 */
	int updateByPrimaryKey(ItoSkuProp record);
}