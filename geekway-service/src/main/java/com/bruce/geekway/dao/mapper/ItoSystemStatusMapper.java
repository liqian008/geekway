package com.bruce.geekway.dao.mapper;

import com.bruce.geekway.model.ItoSystemStatus;
import com.bruce.geekway.model.ItoSystemStatusCriteria;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface ItoSystemStatusMapper {
    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table ito_system_status
     *
     * @mbggenerated Sun May 25 22:14:30 CST 2014
     */
    int countByExample(ItoSystemStatusCriteria example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table ito_system_status
     *
     * @mbggenerated Sun May 25 22:14:30 CST 2014
     */
    int deleteByExample(ItoSystemStatusCriteria example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table ito_system_status
     *
     * @mbggenerated Sun May 25 22:14:30 CST 2014
     */
    int deleteByPrimaryKey(Integer id);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table ito_system_status
     *
     * @mbggenerated Sun May 25 22:14:30 CST 2014
     */
    int insert(ItoSystemStatus record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table ito_system_status
     *
     * @mbggenerated Sun May 25 22:14:30 CST 2014
     */
    int insertSelective(ItoSystemStatus record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table ito_system_status
     *
     * @mbggenerated Sun May 25 22:14:30 CST 2014
     */
    List<ItoSystemStatus> selectByExample(ItoSystemStatusCriteria example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table ito_system_status
     *
     * @mbggenerated Sun May 25 22:14:30 CST 2014
     */
    ItoSystemStatus selectByPrimaryKey(Integer id);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table ito_system_status
     *
     * @mbggenerated Sun May 25 22:14:30 CST 2014
     */
    int updateByExampleSelective(@Param("record") ItoSystemStatus record, @Param("example") ItoSystemStatusCriteria example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table ito_system_status
     *
     * @mbggenerated Sun May 25 22:14:30 CST 2014
     */
    int updateByExample(@Param("record") ItoSystemStatus record, @Param("example") ItoSystemStatusCriteria example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table ito_system_status
     *
     * @mbggenerated Sun May 25 22:14:30 CST 2014
     */
    int updateByPrimaryKeySelective(ItoSystemStatus record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table ito_system_status
     *
     * @mbggenerated Sun May 25 22:14:30 CST 2014
     */
    int updateByPrimaryKey(ItoSystemStatus record);
}