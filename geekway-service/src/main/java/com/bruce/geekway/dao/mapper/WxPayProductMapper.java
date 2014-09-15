package com.bruce.geekway.dao.mapper;

import com.bruce.geekway.model.WxPayProduct;
import com.bruce.geekway.model.WxPayProductCriteria;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface WxPayProductMapper {

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table wx_pay_product
	 * @mbggenerated  Sun Sep 14 14:52:45 CST 2014
	 */
	int countByExample(WxPayProductCriteria example);

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table wx_pay_product
	 * @mbggenerated  Sun Sep 14 14:52:45 CST 2014
	 */
	int deleteByExample(WxPayProductCriteria example);

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table wx_pay_product
	 * @mbggenerated  Sun Sep 14 14:52:45 CST 2014
	 */
	int deleteByPrimaryKey(Integer id);

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table wx_pay_product
	 * @mbggenerated  Sun Sep 14 14:52:45 CST 2014
	 */
	int insert(WxPayProduct record);

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table wx_pay_product
	 * @mbggenerated  Sun Sep 14 14:52:45 CST 2014
	 */
	int insertSelective(WxPayProduct record);

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table wx_pay_product
	 * @mbggenerated  Sun Sep 14 14:52:45 CST 2014
	 */
	List<WxPayProduct> selectByExample(WxPayProductCriteria example);

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table wx_pay_product
	 * @mbggenerated  Sun Sep 14 14:52:45 CST 2014
	 */
	WxPayProduct selectByPrimaryKey(Integer id);

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table wx_pay_product
	 * @mbggenerated  Sun Sep 14 14:52:45 CST 2014
	 */
	int updateByExampleSelective(@Param("record") WxPayProduct record, @Param("example") WxPayProductCriteria example);

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table wx_pay_product
	 * @mbggenerated  Sun Sep 14 14:52:45 CST 2014
	 */
	int updateByExample(@Param("record") WxPayProduct record, @Param("example") WxPayProductCriteria example);

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table wx_pay_product
	 * @mbggenerated  Sun Sep 14 14:52:45 CST 2014
	 */
	int updateByPrimaryKeySelective(WxPayProduct record);

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table wx_pay_product
	 * @mbggenerated  Sun Sep 14 14:52:45 CST 2014
	 */
	int updateByPrimaryKey(WxPayProduct record);
}