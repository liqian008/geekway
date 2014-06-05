package com.bruce.geekway.dao.mapper;

import com.bruce.geekway.model.WxMaterialArticle;
import com.bruce.geekway.model.WxMaterialArticleCriteria;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface WxMaterialArticleMapper {

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table wx_material_article
	 * @mbggenerated  Thu Jun 05 20:42:00 CST 2014
	 */
	int countByExample(WxMaterialArticleCriteria example);

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table wx_material_article
	 * @mbggenerated  Thu Jun 05 20:42:00 CST 2014
	 */
	int deleteByExample(WxMaterialArticleCriteria example);

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table wx_material_article
	 * @mbggenerated  Thu Jun 05 20:42:00 CST 2014
	 */
	int deleteByPrimaryKey(Integer id);

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table wx_material_article
	 * @mbggenerated  Thu Jun 05 20:42:00 CST 2014
	 */
	int insert(WxMaterialArticle record);

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table wx_material_article
	 * @mbggenerated  Thu Jun 05 20:42:00 CST 2014
	 */
	int insertSelective(WxMaterialArticle record);

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table wx_material_article
	 * @mbggenerated  Thu Jun 05 20:42:00 CST 2014
	 */
	List<WxMaterialArticle> selectByExample(WxMaterialArticleCriteria example);

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table wx_material_article
	 * @mbggenerated  Thu Jun 05 20:42:00 CST 2014
	 */
	WxMaterialArticle selectByPrimaryKey(Integer id);

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table wx_material_article
	 * @mbggenerated  Thu Jun 05 20:42:00 CST 2014
	 */
	int updateByExampleSelective(@Param("record") WxMaterialArticle record, @Param("example") WxMaterialArticleCriteria example);

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table wx_material_article
	 * @mbggenerated  Thu Jun 05 20:42:00 CST 2014
	 */
	int updateByExample(@Param("record") WxMaterialArticle record, @Param("example") WxMaterialArticleCriteria example);

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table wx_material_article
	 * @mbggenerated  Thu Jun 05 20:42:00 CST 2014
	 */
	int updateByPrimaryKeySelective(WxMaterialArticle record);

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table wx_material_article
	 * @mbggenerated  Thu Jun 05 20:42:00 CST 2014
	 */
	int updateByPrimaryKey(WxMaterialArticle record);

	/**
     * ??��????��??newsId??��?��?????�?
     * @param newsId
     * @return
     */
    List<WxMaterialArticle> queryMaterialArticlesByNewsId(@Param("newsId")int newsId);
    
    /**
     * ??��????��??newsId??��?��?????�?
     * @param newsId
     * @return
     */
    List<WxMaterialArticle> queryMaterialArticlesByNewsId(@Param("newsId")int newsId, @Param("limit")int limit);
    
    /**
     * ??��????��??newsId�???��?��?????�?
     * @param newsId
     * @return
     */
    List<WxMaterialArticle> queryMaterialArticlesOutNewsId(int newsId);
    
    /**
     * ??��??commandId??��?��?��?��?????�???????�?
     * @param commandId
     * @return
     */
    List<WxMaterialArticle> queryMaterialArticlesByCommandId(int commandId);
    
    
//    /**
//     * ??��?��?��?????�???????�?
//     * @return
//     */
//    List<WxMaterialArticle> querySubscribedMaterials();
//    
//    /**
//     * ??��?��?????�???????�?
//     * @param commandId
//     * @return
//     */
//    List<WxMaterialArticle> queryGeneralMaterials();
    
}