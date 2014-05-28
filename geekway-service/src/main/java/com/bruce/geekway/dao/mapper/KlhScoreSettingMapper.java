package com.bruce.geekway.dao.mapper;

import com.bruce.geekway.model.KlhScoreSetting;
import com.bruce.geekway.model.KlhScoreSettingCriteria;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface KlhScoreSettingMapper {
    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table klh_score_setting
     *
     * @mbggenerated Wed May 28 16:43:29 CST 2014
     */
    int countByExample(KlhScoreSettingCriteria example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table klh_score_setting
     *
     * @mbggenerated Wed May 28 16:43:29 CST 2014
     */
    int deleteByExample(KlhScoreSettingCriteria example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table klh_score_setting
     *
     * @mbggenerated Wed May 28 16:43:29 CST 2014
     */
    int deleteByPrimaryKey(Integer id);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table klh_score_setting
     *
     * @mbggenerated Wed May 28 16:43:29 CST 2014
     */
    int insert(KlhScoreSetting record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table klh_score_setting
     *
     * @mbggenerated Wed May 28 16:43:29 CST 2014
     */
    int insertSelective(KlhScoreSetting record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table klh_score_setting
     *
     * @mbggenerated Wed May 28 16:43:29 CST 2014
     */
    List<KlhScoreSetting> selectByExample(KlhScoreSettingCriteria example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table klh_score_setting
     *
     * @mbggenerated Wed May 28 16:43:29 CST 2014
     */
    KlhScoreSetting selectByPrimaryKey(Integer id);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table klh_score_setting
     *
     * @mbggenerated Wed May 28 16:43:29 CST 2014
     */
    int updateByExampleSelective(@Param("record") KlhScoreSetting record, @Param("example") KlhScoreSettingCriteria example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table klh_score_setting
     *
     * @mbggenerated Wed May 28 16:43:29 CST 2014
     */
    int updateByExample(@Param("record") KlhScoreSetting record, @Param("example") KlhScoreSettingCriteria example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table klh_score_setting
     *
     * @mbggenerated Wed May 28 16:43:29 CST 2014
     */
    int updateByPrimaryKeySelective(KlhScoreSetting record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table klh_score_setting
     *
     * @mbggenerated Wed May 28 16:43:29 CST 2014
     */
    int updateByPrimaryKey(KlhScoreSetting record);
}