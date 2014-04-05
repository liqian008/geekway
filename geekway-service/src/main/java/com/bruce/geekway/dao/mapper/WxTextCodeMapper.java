package com.bruce.geekway.dao.mapper;

import com.bruce.geekway.model.WxTextCode;
import com.bruce.geekway.model.WxTextCodeCriteria;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface WxTextCodeMapper {

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table wx_text_code
	 * @mbggenerated  Sat Apr 05 21:09:10 CST 2014
	 */
	int countByExample(WxTextCodeCriteria example);

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table wx_text_code
	 * @mbggenerated  Sat Apr 05 21:09:10 CST 2014
	 */
	int deleteByExample(WxTextCodeCriteria example);

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table wx_text_code
	 * @mbggenerated  Sat Apr 05 21:09:10 CST 2014
	 */
	int deleteByPrimaryKey(Integer id);

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table wx_text_code
	 * @mbggenerated  Sat Apr 05 21:09:10 CST 2014
	 */
	int insert(WxTextCode record);

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table wx_text_code
	 * @mbggenerated  Sat Apr 05 21:09:10 CST 2014
	 */
	int insertSelective(WxTextCode record);

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table wx_text_code
	 * @mbggenerated  Sat Apr 05 21:09:10 CST 2014
	 */
	List<WxTextCode> selectByExample(WxTextCodeCriteria example);

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table wx_text_code
	 * @mbggenerated  Sat Apr 05 21:09:10 CST 2014
	 */
	WxTextCode selectByPrimaryKey(Integer id);

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table wx_text_code
	 * @mbggenerated  Sat Apr 05 21:09:10 CST 2014
	 */
	int updateByExampleSelective(@Param("record") WxTextCode record, @Param("example") WxTextCodeCriteria example);

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table wx_text_code
	 * @mbggenerated  Sat Apr 05 21:09:10 CST 2014
	 */
	int updateByExample(@Param("record") WxTextCode record, @Param("example") WxTextCodeCriteria example);

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table wx_text_code
	 * @mbggenerated  Sat Apr 05 21:09:10 CST 2014
	 */
	int updateByPrimaryKeySelective(WxTextCode record);

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table wx_text_code
	 * @mbggenerated  Sat Apr 05 21:09:10 CST 2014
	 */
	int updateByPrimaryKey(WxTextCode record);
}