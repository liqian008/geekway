package com.bruce.geekway.dao.mapper;

import com.bruce.geekway.model.WxCommandMaterial;
import com.bruce.geekway.model.WxCommandMaterialCriteria;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface WxCommandMaterialMapper {
    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table wx_command_material
     *
     * @mbggenerated Tue May 06 15:15:28 CST 2014
     */
    int countByExample(WxCommandMaterialCriteria example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table wx_command_material
     *
     * @mbggenerated Tue May 06 15:15:28 CST 2014
     */
    int deleteByExample(WxCommandMaterialCriteria example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table wx_command_material
     *
     * @mbggenerated Tue May 06 15:15:28 CST 2014
     */
    int deleteByPrimaryKey(Integer id);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table wx_command_material
     *
     * @mbggenerated Tue May 06 15:15:28 CST 2014
     */
    int insert(WxCommandMaterial record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table wx_command_material
     *
     * @mbggenerated Tue May 06 15:15:28 CST 2014
     */
    int insertSelective(WxCommandMaterial record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table wx_command_material
     *
     * @mbggenerated Tue May 06 15:15:28 CST 2014
     */
    List<WxCommandMaterial> selectByExample(WxCommandMaterialCriteria example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table wx_command_material
     *
     * @mbggenerated Tue May 06 15:15:28 CST 2014
     */
    WxCommandMaterial selectByPrimaryKey(Integer id);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table wx_command_material
     *
     * @mbggenerated Tue May 06 15:15:28 CST 2014
     */
    int updateByExampleSelective(@Param("record") WxCommandMaterial record, @Param("example") WxCommandMaterialCriteria example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table wx_command_material
     *
     * @mbggenerated Tue May 06 15:15:28 CST 2014
     */
    int updateByExample(@Param("record") WxCommandMaterial record, @Param("example") WxCommandMaterialCriteria example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table wx_command_material
     *
     * @mbggenerated Tue May 06 15:15:28 CST 2014
     */
    int updateByPrimaryKeySelective(WxCommandMaterial record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table wx_command_material
     *
     * @mbggenerated Tue May 06 15:15:28 CST 2014
     */
    int updateByPrimaryKey(WxCommandMaterial record);
}