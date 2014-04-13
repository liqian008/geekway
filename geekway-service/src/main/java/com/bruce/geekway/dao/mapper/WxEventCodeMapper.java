package com.bruce.geekway.dao.mapper;

import com.bruce.geekway.model.WxEventCode;
import com.bruce.geekway.model.WxEventCodeCriteria;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface WxEventCodeMapper {
    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table wx_event_code
     *
     * @mbggenerated Sun Apr 13 09:50:59 CST 2014
     */
    int countByExample(WxEventCodeCriteria example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table wx_event_code
     *
     * @mbggenerated Sun Apr 13 09:50:59 CST 2014
     */
    int deleteByExample(WxEventCodeCriteria example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table wx_event_code
     *
     * @mbggenerated Sun Apr 13 09:50:59 CST 2014
     */
    int deleteByPrimaryKey(Integer id);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table wx_event_code
     *
     * @mbggenerated Sun Apr 13 09:50:59 CST 2014
     */
    int insert(WxEventCode record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table wx_event_code
     *
     * @mbggenerated Sun Apr 13 09:50:59 CST 2014
     */
    int insertSelective(WxEventCode record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table wx_event_code
     *
     * @mbggenerated Sun Apr 13 09:50:59 CST 2014
     */
    List<WxEventCode> selectByExample(WxEventCodeCriteria example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table wx_event_code
     *
     * @mbggenerated Sun Apr 13 09:50:59 CST 2014
     */
    WxEventCode selectByPrimaryKey(Integer id);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table wx_event_code
     *
     * @mbggenerated Sun Apr 13 09:50:59 CST 2014
     */
    int updateByExampleSelective(@Param("record") WxEventCode record, @Param("example") WxEventCodeCriteria example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table wx_event_code
     *
     * @mbggenerated Sun Apr 13 09:50:59 CST 2014
     */
    int updateByExample(@Param("record") WxEventCode record, @Param("example") WxEventCodeCriteria example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table wx_event_code
     *
     * @mbggenerated Sun Apr 13 09:50:59 CST 2014
     */
    int updateByPrimaryKeySelective(WxEventCode record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table wx_event_code
     *
     * @mbggenerated Sun Apr 13 09:50:59 CST 2014
     */
    int updateByPrimaryKey(WxEventCode record);
}