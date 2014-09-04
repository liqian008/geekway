package com.bruce.geekway.dao.mapper;

import com.bruce.geekway.model.WxHistorMessage;
import com.bruce.geekway.model.WxHistorMessageCriteria;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface WxHistorMessageMapper {
    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table wx_history_message
     *
     * @mbggenerated Sun Apr 20 09:59:30 CST 2014
     */
    int countByExample(WxHistorMessageCriteria example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table wx_history_message
     *
     * @mbggenerated Sun Apr 20 09:59:30 CST 2014
     */
    int deleteByExample(WxHistorMessageCriteria example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table wx_history_message
     *
     * @mbggenerated Sun Apr 20 09:59:30 CST 2014
     */
    int deleteByPrimaryKey(Integer id);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table wx_history_message
     *
     * @mbggenerated Sun Apr 20 09:59:30 CST 2014
     */
    int insert(WxHistorMessage record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table wx_history_message
     *
     * @mbggenerated Sun Apr 20 09:59:30 CST 2014
     */
    int insertSelective(WxHistorMessage record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table wx_history_message
     *
     * @mbggenerated Sun Apr 20 09:59:30 CST 2014
     */
    List<WxHistorMessage> selectByExample(WxHistorMessageCriteria example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table wx_history_message
     *
     * @mbggenerated Sun Apr 20 09:59:30 CST 2014
     */
    WxHistorMessage selectByPrimaryKey(Integer id);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table wx_history_message
     *
     * @mbggenerated Sun Apr 20 09:59:30 CST 2014
     */
    int updateByExampleSelective(@Param("record") WxHistorMessage record, @Param("example") WxHistorMessageCriteria example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table wx_history_message
     *
     * @mbggenerated Sun Apr 20 09:59:30 CST 2014
     */
    int updateByExample(@Param("record") WxHistorMessage record, @Param("example") WxHistorMessageCriteria example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table wx_history_message
     *
     * @mbggenerated Sun Apr 20 09:59:30 CST 2014
     */
    int updateByPrimaryKeySelective(WxHistorMessage record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table wx_history_message
     *
     * @mbggenerated Sun Apr 20 09:59:30 CST 2014
     */
    int updateByPrimaryKey(WxHistorMessage record);
}