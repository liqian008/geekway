package com.bruce.geekway.dao.mapper;

import com.bruce.geekway.model.WxMaterialArticlesRelation;
import com.bruce.geekway.model.WxMaterialArticlesRelationCriteria;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface WxMaterialArticlesRelationMapper {

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table wx_material_articles_relation
	 * @mbggenerated  Sun May 11 23:54:50 CST 2014
	 */
	int countByExample(WxMaterialArticlesRelationCriteria example);

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table wx_material_articles_relation
	 * @mbggenerated  Sun May 11 23:54:50 CST 2014
	 */
	int deleteByExample(WxMaterialArticlesRelationCriteria example);

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table wx_material_articles_relation
	 * @mbggenerated  Sun May 11 23:54:50 CST 2014
	 */
	int deleteByPrimaryKey(Integer id);

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table wx_material_articles_relation
	 * @mbggenerated  Sun May 11 23:54:50 CST 2014
	 */
	int insert(WxMaterialArticlesRelation record);

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table wx_material_articles_relation
	 * @mbggenerated  Sun May 11 23:54:50 CST 2014
	 */
	int insertSelective(WxMaterialArticlesRelation record);

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table wx_material_articles_relation
	 * @mbggenerated  Sun May 11 23:54:50 CST 2014
	 */
	List<WxMaterialArticlesRelation> selectByExample(WxMaterialArticlesRelationCriteria example);

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table wx_material_articles_relation
	 * @mbggenerated  Sun May 11 23:54:50 CST 2014
	 */
	WxMaterialArticlesRelation selectByPrimaryKey(Integer id);

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table wx_material_articles_relation
	 * @mbggenerated  Sun May 11 23:54:50 CST 2014
	 */
	int updateByExampleSelective(@Param("record") WxMaterialArticlesRelation record, @Param("example") WxMaterialArticlesRelationCriteria example);

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table wx_material_articles_relation
	 * @mbggenerated  Sun May 11 23:54:50 CST 2014
	 */
	int updateByExample(@Param("record") WxMaterialArticlesRelation record, @Param("example") WxMaterialArticlesRelationCriteria example);

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table wx_material_articles_relation
	 * @mbggenerated  Sun May 11 23:54:50 CST 2014
	 */
	int updateByPrimaryKeySelective(WxMaterialArticlesRelation record);

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table wx_material_articles_relation
	 * @mbggenerated  Sun May 11 23:54:50 CST 2014
	 */
	int updateByPrimaryKey(WxMaterialArticlesRelation record);
}