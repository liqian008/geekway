package com.bruce.geekway.dao.mapper;

import com.bruce.geekway.model.WxSkuProp;
import com.bruce.geekway.model.WxSkuPropCriteria;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface WxSkuPropMapper {

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table wx_sku_prop
	 * @mbggenerated  Wed Sep 17 09:41:59 CST 2014
	 */
	int countByExample(WxSkuPropCriteria example);

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table wx_sku_prop
	 * @mbggenerated  Wed Sep 17 09:41:59 CST 2014
	 */
	int deleteByExample(WxSkuPropCriteria example);

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table wx_sku_prop
	 * @mbggenerated  Wed Sep 17 09:41:59 CST 2014
	 */
	int deleteByPrimaryKey(Integer id);

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table wx_sku_prop
	 * @mbggenerated  Wed Sep 17 09:41:59 CST 2014
	 */
	int insert(WxSkuProp record);

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table wx_sku_prop
	 * @mbggenerated  Wed Sep 17 09:41:59 CST 2014
	 */
	int insertSelective(WxSkuProp record);

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table wx_sku_prop
	 * @mbggenerated  Wed Sep 17 09:41:59 CST 2014
	 */
	List<WxSkuProp> selectByExample(WxSkuPropCriteria example);

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table wx_sku_prop
	 * @mbggenerated  Wed Sep 17 09:41:59 CST 2014
	 */
	WxSkuProp selectByPrimaryKey(Integer id);

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table wx_sku_prop
	 * @mbggenerated  Wed Sep 17 09:41:59 CST 2014
	 */
	int updateByExampleSelective(@Param("record") WxSkuProp record,
			@Param("example") WxSkuPropCriteria example);

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table wx_sku_prop
	 * @mbggenerated  Wed Sep 17 09:41:59 CST 2014
	 */
	int updateByExample(@Param("record") WxSkuProp record,
			@Param("example") WxSkuPropCriteria example);

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table wx_sku_prop
	 * @mbggenerated  Wed Sep 17 09:41:59 CST 2014
	 */
	int updateByPrimaryKeySelective(WxSkuProp record);

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table wx_sku_prop
	 * @mbggenerated  Wed Sep 17 09:41:59 CST 2014
	 */
	int updateByPrimaryKey(WxSkuProp record);
}