package com.bruce.geekway.dao.mapper;

import com.bruce.geekway.model.WxBroadcast;
import com.bruce.geekway.model.WxBroadcastCriteria;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface WxBroadcastMapper {

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table wx_broadcast
	 * @mbggenerated  Tue Aug 05 18:43:57 CST 2014
	 */
	int countByExample(WxBroadcastCriteria example);

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table wx_broadcast
	 * @mbggenerated  Tue Aug 05 18:43:57 CST 2014
	 */
	int deleteByExample(WxBroadcastCriteria example);

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table wx_broadcast
	 * @mbggenerated  Tue Aug 05 18:43:57 CST 2014
	 */
	int deleteByPrimaryKey(Integer id);

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table wx_broadcast
	 * @mbggenerated  Tue Aug 05 18:43:57 CST 2014
	 */
	int insert(WxBroadcast record);

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table wx_broadcast
	 * @mbggenerated  Tue Aug 05 18:43:57 CST 2014
	 */
	int insertSelective(WxBroadcast record);

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table wx_broadcast
	 * @mbggenerated  Tue Aug 05 18:43:57 CST 2014
	 */
	List<WxBroadcast> selectByExample(WxBroadcastCriteria example);

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table wx_broadcast
	 * @mbggenerated  Tue Aug 05 18:43:57 CST 2014
	 */
	WxBroadcast selectByPrimaryKey(Integer id);

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table wx_broadcast
	 * @mbggenerated  Tue Aug 05 18:43:57 CST 2014
	 */
	int updateByExampleSelective(@Param("record") WxBroadcast record,
			@Param("example") WxBroadcastCriteria example);

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table wx_broadcast
	 * @mbggenerated  Tue Aug 05 18:43:57 CST 2014
	 */
	int updateByExample(@Param("record") WxBroadcast record,
			@Param("example") WxBroadcastCriteria example);

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table wx_broadcast
	 * @mbggenerated  Tue Aug 05 18:43:57 CST 2014
	 */
	int updateByPrimaryKeySelective(WxBroadcast record);

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table wx_broadcast
	 * @mbggenerated  Tue Aug 05 18:43:57 CST 2014
	 */
	int updateByPrimaryKey(WxBroadcast record);
}