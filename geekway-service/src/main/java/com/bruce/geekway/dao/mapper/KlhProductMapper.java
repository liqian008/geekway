package com.bruce.geekway.dao.mapper;

import com.bruce.geekway.model.KlhProduct;
import com.bruce.geekway.model.KlhProductCriteria;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface KlhProductMapper {
    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table klh_product
     *
     * @mbggenerated Wed May 28 16:43:29 CST 2014
     */
    int countByExample(KlhProductCriteria example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table klh_product
     *
     * @mbggenerated Wed May 28 16:43:29 CST 2014
     */
    int deleteByExample(KlhProductCriteria example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table klh_product
     *
     * @mbggenerated Wed May 28 16:43:29 CST 2014
     */
    int deleteByPrimaryKey(Integer id);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table klh_product
     *
     * @mbggenerated Wed May 28 16:43:29 CST 2014
     */
    int insert(KlhProduct record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table klh_product
     *
     * @mbggenerated Wed May 28 16:43:29 CST 2014
     */
    int insertSelective(KlhProduct record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table klh_product
     *
     * @mbggenerated Wed May 28 16:43:29 CST 2014
     */
    List<KlhProduct> selectByExample(KlhProductCriteria example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table klh_product
     *
     * @mbggenerated Wed May 28 16:43:29 CST 2014
     */
    KlhProduct selectByPrimaryKey(Integer id);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table klh_product
     *
     * @mbggenerated Wed May 28 16:43:29 CST 2014
     */
    int updateByExampleSelective(@Param("record") KlhProduct record, @Param("example") KlhProductCriteria example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table klh_product
     *
     * @mbggenerated Wed May 28 16:43:29 CST 2014
     */
    int updateByExample(@Param("record") KlhProduct record, @Param("example") KlhProductCriteria example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table klh_product
     *
     * @mbggenerated Wed May 28 16:43:29 CST 2014
     */
    int updateByPrimaryKeySelective(KlhProduct record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table klh_product
     *
     * @mbggenerated Wed May 28 16:43:29 CST 2014
     */
    int updateByPrimaryKey(KlhProduct record);
}