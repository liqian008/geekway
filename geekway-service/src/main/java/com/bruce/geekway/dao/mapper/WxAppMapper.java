package com.bruce.geekway.dao.mapper;

import com.bruce.geekway.model.WxApp;
import com.bruce.geekway.model.WxAppCriteria;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface WxAppMapper {

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table wx_app
	 * @mbggenerated  Mon Jan 26 16:54:13 CST 2015
	 */
	int countByExample(WxAppCriteria example);

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table wx_app
	 * @mbggenerated  Mon Jan 26 16:54:13 CST 2015
	 */
	int deleteByExample(WxAppCriteria example);

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table wx_app
	 * @mbggenerated  Mon Jan 26 16:54:13 CST 2015
	 */
	int deleteByPrimaryKey(Integer id);

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table wx_app
	 * @mbggenerated  Mon Jan 26 16:54:13 CST 2015
	 */
	int insert(WxApp record);

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table wx_app
	 * @mbggenerated  Mon Jan 26 16:54:13 CST 2015
	 */
	int insertSelective(WxApp record);

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table wx_app
	 * @mbggenerated  Mon Jan 26 16:54:13 CST 2015
	 */
	List<WxApp> selectByExample(WxAppCriteria example);

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table wx_app
	 * @mbggenerated  Mon Jan 26 16:54:13 CST 2015
	 */
	WxApp selectByPrimaryKey(Integer id);

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table wx_app
	 * @mbggenerated  Mon Jan 26 16:54:13 CST 2015
	 */
	int updateByExampleSelective(@Param("record") WxApp record,
			@Param("example") WxAppCriteria example);

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table wx_app
	 * @mbggenerated  Mon Jan 26 16:54:13 CST 2015
	 */
	int updateByExample(@Param("record") WxApp record,
			@Param("example") WxAppCriteria example);

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table wx_app
	 * @mbggenerated  Mon Jan 26 16:54:13 CST 2015
	 */
	int updateByPrimaryKeySelective(WxApp record);

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table wx_app
	 * @mbggenerated  Mon Jan 26 16:54:13 CST 2015
	 */
	int updateByPrimaryKey(WxApp record);
}