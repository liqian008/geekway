package com.bruce.geekway.dao.mapper;

import com.bruce.geekway.model.WxArticle;
import com.bruce.geekway.model.WxArticleCriteria;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface WxArticleMapper {

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table wx_article
	 * @mbggenerated  Tue Apr 08 11:33:42 CST 2014
	 */
	int countByExample(WxArticleCriteria example);

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table wx_article
	 * @mbggenerated  Tue Apr 08 11:33:42 CST 2014
	 */
	int deleteByExample(WxArticleCriteria example);

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table wx_article
	 * @mbggenerated  Tue Apr 08 11:33:42 CST 2014
	 */
	int deleteByPrimaryKey(Integer id);

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table wx_article
	 * @mbggenerated  Tue Apr 08 11:33:42 CST 2014
	 */
	int insert(WxArticle record);

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table wx_article
	 * @mbggenerated  Tue Apr 08 11:33:42 CST 2014
	 */
	int insertSelective(WxArticle record);

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table wx_article
	 * @mbggenerated  Tue Apr 08 11:33:42 CST 2014
	 */
	List<WxArticle> selectByExample(WxArticleCriteria example);

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table wx_article
	 * @mbggenerated  Tue Apr 08 11:33:42 CST 2014
	 */
	WxArticle selectByPrimaryKey(Integer id);

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table wx_article
	 * @mbggenerated  Tue Apr 08 11:33:42 CST 2014
	 */
	int updateByExampleSelective(@Param("record") WxArticle record,
			@Param("example") WxArticleCriteria example);

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table wx_article
	 * @mbggenerated  Tue Apr 08 11:33:42 CST 2014
	 */
	int updateByExample(@Param("record") WxArticle record,
			@Param("example") WxArticleCriteria example);

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table wx_article
	 * @mbggenerated  Tue Apr 08 11:33:42 CST 2014
	 */
	int updateByPrimaryKeySelective(WxArticle record);

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table wx_article
	 * @mbggenerated  Tue Apr 08 11:33:42 CST 2014
	 */
	int updateByPrimaryKey(WxArticle record);

	/**
     * 根据查询moduleId相关的文章
     * @param moduleId
     * @return
     */
    List<WxArticle> queryArticlesByModuleId(int moduleId);
    
    /**
     * 根据查询moduleId不相关的文章
     * @param moduleId
     * @return
     */
    List<WxArticle> queryArticlesOutModuleId(int moduleId);
}