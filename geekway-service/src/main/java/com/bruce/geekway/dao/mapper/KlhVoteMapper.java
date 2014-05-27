package com.bruce.geekway.dao.mapper;

import com.bruce.geekway.model.KlhVote;
import com.bruce.geekway.model.KlhVoteCriteria;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface KlhVoteMapper {

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table klh_vote
	 * @mbggenerated  Tue May 27 16:25:00 CST 2014
	 */
	int countByExample(KlhVoteCriteria example);

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table klh_vote
	 * @mbggenerated  Tue May 27 16:25:00 CST 2014
	 */
	int deleteByExample(KlhVoteCriteria example);

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table klh_vote
	 * @mbggenerated  Tue May 27 16:25:00 CST 2014
	 */
	int deleteByPrimaryKey(Integer id);

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table klh_vote
	 * @mbggenerated  Tue May 27 16:25:00 CST 2014
	 */
	int insert(KlhVote record);

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table klh_vote
	 * @mbggenerated  Tue May 27 16:25:00 CST 2014
	 */
	int insertSelective(KlhVote record);

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table klh_vote
	 * @mbggenerated  Tue May 27 16:25:00 CST 2014
	 */
	List<KlhVote> selectByExample(KlhVoteCriteria example);

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table klh_vote
	 * @mbggenerated  Tue May 27 16:25:00 CST 2014
	 */
	KlhVote selectByPrimaryKey(Integer id);

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table klh_vote
	 * @mbggenerated  Tue May 27 16:25:00 CST 2014
	 */
	int updateByExampleSelective(@Param("record") KlhVote record,
			@Param("example") KlhVoteCriteria example);

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table klh_vote
	 * @mbggenerated  Tue May 27 16:25:00 CST 2014
	 */
	int updateByExample(@Param("record") KlhVote record,
			@Param("example") KlhVoteCriteria example);

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table klh_vote
	 * @mbggenerated  Tue May 27 16:25:00 CST 2014
	 */
	int updateByPrimaryKeySelective(KlhVote record);

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table klh_vote
	 * @mbggenerated  Tue May 27 16:25:00 CST 2014
	 */
	int updateByPrimaryKey(KlhVote record);
}