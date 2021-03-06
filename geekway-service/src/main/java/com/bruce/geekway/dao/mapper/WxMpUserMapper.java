package com.bruce.geekway.dao.mapper;

import com.bruce.geekway.model.WxMpUser;
import com.bruce.geekway.model.WxMpUserCriteria;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface WxMpUserMapper {

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table wx_mp_user
	 * @mbggenerated  Sat May 17 22:42:08 CST 2014
	 */
	int countByExample(WxMpUserCriteria example);

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table wx_mp_user
	 * @mbggenerated  Sat May 17 22:42:08 CST 2014
	 */
	int deleteByExample(WxMpUserCriteria example);

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table wx_mp_user
	 * @mbggenerated  Sat May 17 22:42:08 CST 2014
	 */
	int deleteByPrimaryKey(Integer id);

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table wx_mp_user
	 * @mbggenerated  Sat May 17 22:42:08 CST 2014
	 */
	int insert(WxMpUser record);

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table wx_mp_user
	 * @mbggenerated  Sat May 17 22:42:08 CST 2014
	 */
	int insertSelective(WxMpUser record);

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table wx_mp_user
	 * @mbggenerated  Sat May 17 22:42:08 CST 2014
	 */
	List<WxMpUser> selectByExample(WxMpUserCriteria example);

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table wx_mp_user
	 * @mbggenerated  Sat May 17 22:42:08 CST 2014
	 */
	WxMpUser selectByPrimaryKey(Integer id);

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table wx_mp_user
	 * @mbggenerated  Sat May 17 22:42:08 CST 2014
	 */
	int updateByExampleSelective(@Param("record") WxMpUser record, @Param("example") WxMpUserCriteria example);

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table wx_mp_user
	 * @mbggenerated  Sat May 17 22:42:08 CST 2014
	 */
	int updateByExample(@Param("record") WxMpUser record, @Param("example") WxMpUserCriteria example);

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table wx_mp_user
	 * @mbggenerated  Sat May 17 22:42:08 CST 2014
	 */
	int updateByPrimaryKeySelective(WxMpUser record);

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table wx_mp_user
	 * @mbggenerated  Sat May 17 22:42:08 CST 2014
	 */
	int updateByPrimaryKey(WxMpUser record);
}