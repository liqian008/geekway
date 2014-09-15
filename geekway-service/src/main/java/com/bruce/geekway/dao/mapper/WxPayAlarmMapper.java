package com.bruce.geekway.dao.mapper;

import com.bruce.geekway.model.WxPayAlarm;
import com.bruce.geekway.model.WxPayAlarmCriteria;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface WxPayAlarmMapper {

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table wx_pay_alarm
	 * @mbggenerated  Sun Sep 14 14:52:45 CST 2014
	 */
	int countByExample(WxPayAlarmCriteria example);

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table wx_pay_alarm
	 * @mbggenerated  Sun Sep 14 14:52:45 CST 2014
	 */
	int deleteByExample(WxPayAlarmCriteria example);

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table wx_pay_alarm
	 * @mbggenerated  Sun Sep 14 14:52:45 CST 2014
	 */
	int deleteByPrimaryKey(Integer id);

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table wx_pay_alarm
	 * @mbggenerated  Sun Sep 14 14:52:45 CST 2014
	 */
	int insert(WxPayAlarm record);

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table wx_pay_alarm
	 * @mbggenerated  Sun Sep 14 14:52:45 CST 2014
	 */
	int insertSelective(WxPayAlarm record);

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table wx_pay_alarm
	 * @mbggenerated  Sun Sep 14 14:52:45 CST 2014
	 */
	List<WxPayAlarm> selectByExample(WxPayAlarmCriteria example);

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table wx_pay_alarm
	 * @mbggenerated  Sun Sep 14 14:52:45 CST 2014
	 */
	WxPayAlarm selectByPrimaryKey(Integer id);

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table wx_pay_alarm
	 * @mbggenerated  Sun Sep 14 14:52:45 CST 2014
	 */
	int updateByExampleSelective(@Param("record") WxPayAlarm record, @Param("example") WxPayAlarmCriteria example);

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table wx_pay_alarm
	 * @mbggenerated  Sun Sep 14 14:52:45 CST 2014
	 */
	int updateByExample(@Param("record") WxPayAlarm record, @Param("example") WxPayAlarmCriteria example);

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table wx_pay_alarm
	 * @mbggenerated  Sun Sep 14 14:52:45 CST 2014
	 */
	int updateByPrimaryKeySelective(WxPayAlarm record);

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table wx_pay_alarm
	 * @mbggenerated  Sun Sep 14 14:52:45 CST 2014
	 */
	int updateByPrimaryKey(WxPayAlarm record);
}