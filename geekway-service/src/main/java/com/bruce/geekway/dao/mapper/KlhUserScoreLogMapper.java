package com.bruce.geekway.dao.mapper;

import com.bruce.geekway.model.KlhUserScoreLog;
import com.bruce.geekway.model.KlhUserScoreLogCriteria;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface KlhUserScoreLogMapper {

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table klh_user_score_log
	 * @mbggenerated  Mon Jun 02 15:23:48 CST 2014
	 */
	int countByExample(KlhUserScoreLogCriteria example);

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table klh_user_score_log
	 * @mbggenerated  Mon Jun 02 15:23:48 CST 2014
	 */
	int deleteByExample(KlhUserScoreLogCriteria example);

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table klh_user_score_log
	 * @mbggenerated  Mon Jun 02 15:23:48 CST 2014
	 */
	int deleteByPrimaryKey(Integer id);

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table klh_user_score_log
	 * @mbggenerated  Mon Jun 02 15:23:48 CST 2014
	 */
	int insert(KlhUserScoreLog record);

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table klh_user_score_log
	 * @mbggenerated  Mon Jun 02 15:23:48 CST 2014
	 */
	int insertSelective(KlhUserScoreLog record);

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table klh_user_score_log
	 * @mbggenerated  Mon Jun 02 15:23:48 CST 2014
	 */
	List<KlhUserScoreLog> selectByExample(KlhUserScoreLogCriteria example);

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table klh_user_score_log
	 * @mbggenerated  Mon Jun 02 15:23:48 CST 2014
	 */
	KlhUserScoreLog selectByPrimaryKey(Integer id);

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table klh_user_score_log
	 * @mbggenerated  Mon Jun 02 15:23:48 CST 2014
	 */
	int updateByExampleSelective(@Param("record") KlhUserScoreLog record, @Param("example") KlhUserScoreLogCriteria example);

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table klh_user_score_log
	 * @mbggenerated  Mon Jun 02 15:23:48 CST 2014
	 */
	int updateByExample(@Param("record") KlhUserScoreLog record, @Param("example") KlhUserScoreLogCriteria example);

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table klh_user_score_log
	 * @mbggenerated  Mon Jun 02 15:23:48 CST 2014
	 */
	int updateByPrimaryKeySelective(KlhUserScoreLog record);

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table klh_user_score_log
	 * @mbggenerated  Mon Jun 02 15:23:48 CST 2014
	 */
	int updateByPrimaryKey(KlhUserScoreLog record);
}