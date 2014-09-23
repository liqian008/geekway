package com.bruce.geekway.dao.mapper;

import com.bruce.geekway.model.WxProductOrder;
import com.bruce.geekway.model.WxProductOrderCriteria;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface WxProductOrderMapper {

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table wx_product_order
	 * @mbggenerated  Tue Sep 23 19:28:10 CST 2014
	 */
	int countByExample(WxProductOrderCriteria example);

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table wx_product_order
	 * @mbggenerated  Tue Sep 23 19:28:10 CST 2014
	 */
	int deleteByExample(WxProductOrderCriteria example);

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table wx_product_order
	 * @mbggenerated  Tue Sep 23 19:28:10 CST 2014
	 */
	int deleteByPrimaryKey(Long id);

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table wx_product_order
	 * @mbggenerated  Tue Sep 23 19:28:10 CST 2014
	 */
	int insert(WxProductOrder record);

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table wx_product_order
	 * @mbggenerated  Tue Sep 23 19:28:10 CST 2014
	 */
	int insertSelective(WxProductOrder record);

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table wx_product_order
	 * @mbggenerated  Tue Sep 23 19:28:10 CST 2014
	 */
	List<WxProductOrder> selectByExample(WxProductOrderCriteria example);

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table wx_product_order
	 * @mbggenerated  Tue Sep 23 19:28:10 CST 2014
	 */
	WxProductOrder selectByPrimaryKey(Long id);

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table wx_product_order
	 * @mbggenerated  Tue Sep 23 19:28:10 CST 2014
	 */
	int updateByExampleSelective(@Param("record") WxProductOrder record,
			@Param("example") WxProductOrderCriteria example);

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table wx_product_order
	 * @mbggenerated  Tue Sep 23 19:28:10 CST 2014
	 */
	int updateByExample(@Param("record") WxProductOrder record,
			@Param("example") WxProductOrderCriteria example);

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table wx_product_order
	 * @mbggenerated  Tue Sep 23 19:28:10 CST 2014
	 */
	int updateByPrimaryKeySelective(WxProductOrder record);

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table wx_product_order
	 * @mbggenerated  Tue Sep 23 19:28:10 CST 2014
	 */
	int updateByPrimaryKey(WxProductOrder record);
}