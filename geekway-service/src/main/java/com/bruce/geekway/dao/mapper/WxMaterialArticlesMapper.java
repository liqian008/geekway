package com.bruce.geekway.dao.mapper;

import com.bruce.geekway.model.WxMaterialArticles;
import com.bruce.geekway.model.WxMaterialArticlesCriteria;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface WxMaterialArticlesMapper {

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table wx_material_articles
	 * @mbggenerated  Mon May 12 00:48:09 CST 2014
	 */
	int countByExample(WxMaterialArticlesCriteria example);

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table wx_material_articles
	 * @mbggenerated  Mon May 12 00:48:09 CST 2014
	 */
	int deleteByExample(WxMaterialArticlesCriteria example);

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table wx_material_articles
	 * @mbggenerated  Mon May 12 00:48:09 CST 2014
	 */
	int deleteByPrimaryKey(Integer id);

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table wx_material_articles
	 * @mbggenerated  Mon May 12 00:48:09 CST 2014
	 */
	int insert(WxMaterialArticles record);

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table wx_material_articles
	 * @mbggenerated  Mon May 12 00:48:09 CST 2014
	 */
	int insertSelective(WxMaterialArticles record);

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table wx_material_articles
	 * @mbggenerated  Mon May 12 00:48:09 CST 2014
	 */
	List<WxMaterialArticles> selectByExample(WxMaterialArticlesCriteria example);

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table wx_material_articles
	 * @mbggenerated  Mon May 12 00:48:09 CST 2014
	 */
	WxMaterialArticles selectByPrimaryKey(Integer id);

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table wx_material_articles
	 * @mbggenerated  Mon May 12 00:48:09 CST 2014
	 */
	int updateByExampleSelective(@Param("record") WxMaterialArticles record, @Param("example") WxMaterialArticlesCriteria example);

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table wx_material_articles
	 * @mbggenerated  Mon May 12 00:48:09 CST 2014
	 */
	int updateByExample(@Param("record") WxMaterialArticles record, @Param("example") WxMaterialArticlesCriteria example);

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table wx_material_articles
	 * @mbggenerated  Mon May 12 00:48:09 CST 2014
	 */
	int updateByPrimaryKeySelective(WxMaterialArticles record);

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table wx_material_articles
	 * @mbggenerated  Mon May 12 00:48:09 CST 2014
	 */
	int updateByPrimaryKey(WxMaterialArticles record);
}