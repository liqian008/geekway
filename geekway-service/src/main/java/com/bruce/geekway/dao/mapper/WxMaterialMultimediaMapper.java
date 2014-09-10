package com.bruce.geekway.dao.mapper;

import com.bruce.geekway.model.WxMaterialMultimedia;
import com.bruce.geekway.model.WxMaterialMultimediaCriteria;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface WxMaterialMultimediaMapper {
    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table wx_material_multimedia
     *
     * @mbggenerated Fri Aug 08 10:25:38 CST 2014
     */
    int countByExample(WxMaterialMultimediaCriteria example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table wx_material_multimedia
     *
     * @mbggenerated Fri Aug 08 10:25:38 CST 2014
     */
    int deleteByExample(WxMaterialMultimediaCriteria example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table wx_material_multimedia
     *
     * @mbggenerated Fri Aug 08 10:25:38 CST 2014
     */
    int deleteByPrimaryKey(Integer id);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table wx_material_multimedia
     *
     * @mbggenerated Fri Aug 08 10:25:38 CST 2014
     */
    int insert(WxMaterialMultimedia record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table wx_material_multimedia
     *
     * @mbggenerated Fri Aug 08 10:25:38 CST 2014
     */
    int insertSelective(WxMaterialMultimedia record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table wx_material_multimedia
     *
     * @mbggenerated Fri Aug 08 10:25:38 CST 2014
     */
    List<WxMaterialMultimedia> selectByExample(WxMaterialMultimediaCriteria example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table wx_material_multimedia
     *
     * @mbggenerated Fri Aug 08 10:25:38 CST 2014
     */
    WxMaterialMultimedia selectByPrimaryKey(Integer id);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table wx_material_multimedia
     *
     * @mbggenerated Fri Aug 08 10:25:38 CST 2014
     */
    int updateByExampleSelective(@Param("record") WxMaterialMultimedia record, @Param("example") WxMaterialMultimediaCriteria example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table wx_material_multimedia
     *
     * @mbggenerated Fri Aug 08 10:25:38 CST 2014
     */
    int updateByExample(@Param("record") WxMaterialMultimedia record, @Param("example") WxMaterialMultimediaCriteria example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table wx_material_multimedia
     *
     * @mbggenerated Fri Aug 08 10:25:38 CST 2014
     */
    int updateByPrimaryKeySelective(WxMaterialMultimedia record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table wx_material_multimedia
     *
     * @mbggenerated Fri Aug 08 10:25:38 CST 2014
     */
    int updateByPrimaryKey(WxMaterialMultimedia record);
}