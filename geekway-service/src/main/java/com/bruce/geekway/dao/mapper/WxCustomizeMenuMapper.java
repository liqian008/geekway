package com.bruce.geekway.dao.mapper;

import com.bruce.geekway.model.WxCustomizeMenu;
import com.bruce.geekway.model.WxCustomizeMenuCriteria;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface WxCustomizeMenuMapper {
    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table wx_customize_menu
     *
     * @mbggenerated Thu Apr 10 13:40:06 CST 2014
     */
    int countByExample(WxCustomizeMenuCriteria example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table wx_customize_menu
     *
     * @mbggenerated Thu Apr 10 13:40:06 CST 2014
     */
    int deleteByExample(WxCustomizeMenuCriteria example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table wx_customize_menu
     *
     * @mbggenerated Thu Apr 10 13:40:06 CST 2014
     */
    int deleteByPrimaryKey(Integer id);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table wx_customize_menu
     *
     * @mbggenerated Thu Apr 10 13:40:06 CST 2014
     */
    int insert(WxCustomizeMenu record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table wx_customize_menu
     *
     * @mbggenerated Thu Apr 10 13:40:06 CST 2014
     */
    int insertSelective(WxCustomizeMenu record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table wx_customize_menu
     *
     * @mbggenerated Thu Apr 10 13:40:06 CST 2014
     */
    List<WxCustomizeMenu> selectByExample(WxCustomizeMenuCriteria example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table wx_customize_menu
     *
     * @mbggenerated Thu Apr 10 13:40:06 CST 2014
     */
    WxCustomizeMenu selectByPrimaryKey(Integer id);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table wx_customize_menu
     *
     * @mbggenerated Thu Apr 10 13:40:06 CST 2014
     */
    int updateByExampleSelective(@Param("record") WxCustomizeMenu record, @Param("example") WxCustomizeMenuCriteria example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table wx_customize_menu
     *
     * @mbggenerated Thu Apr 10 13:40:06 CST 2014
     */
    int updateByExample(@Param("record") WxCustomizeMenu record, @Param("example") WxCustomizeMenuCriteria example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table wx_customize_menu
     *
     * @mbggenerated Thu Apr 10 13:40:06 CST 2014
     */
    int updateByPrimaryKeySelective(WxCustomizeMenu record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table wx_customize_menu
     *
     * @mbggenerated Thu Apr 10 13:40:06 CST 2014
     */
    int updateByPrimaryKey(WxCustomizeMenu record);
}