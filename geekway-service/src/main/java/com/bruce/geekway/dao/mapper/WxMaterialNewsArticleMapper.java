package com.bruce.geekway.dao.mapper;

import com.bruce.geekway.model.WxMaterialNewsArticle;
import com.bruce.geekway.model.WxMaterialNewsArticleCriteria;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface WxMaterialNewsArticleMapper {
    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table wx_material_news_article
     *
     * @mbggenerated Mon May 12 11:41:38 CST 2014
     */
    int countByExample(WxMaterialNewsArticleCriteria example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table wx_material_news_article
     *
     * @mbggenerated Mon May 12 11:41:38 CST 2014
     */
    int deleteByExample(WxMaterialNewsArticleCriteria example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table wx_material_news_article
     *
     * @mbggenerated Mon May 12 11:41:38 CST 2014
     */
    int deleteByPrimaryKey(Integer id);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table wx_material_news_article
     *
     * @mbggenerated Mon May 12 11:41:38 CST 2014
     */
    int insert(WxMaterialNewsArticle record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table wx_material_news_article
     *
     * @mbggenerated Mon May 12 11:41:38 CST 2014
     */
    int insertSelective(WxMaterialNewsArticle record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table wx_material_news_article
     *
     * @mbggenerated Mon May 12 11:41:38 CST 2014
     */
    List<WxMaterialNewsArticle> selectByExample(WxMaterialNewsArticleCriteria example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table wx_material_news_article
     *
     * @mbggenerated Mon May 12 11:41:38 CST 2014
     */
    WxMaterialNewsArticle selectByPrimaryKey(Integer id);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table wx_material_news_article
     *
     * @mbggenerated Mon May 12 11:41:38 CST 2014
     */
    int updateByExampleSelective(@Param("record") WxMaterialNewsArticle record, @Param("example") WxMaterialNewsArticleCriteria example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table wx_material_news_article
     *
     * @mbggenerated Mon May 12 11:41:38 CST 2014
     */
    int updateByExample(@Param("record") WxMaterialNewsArticle record, @Param("example") WxMaterialNewsArticleCriteria example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table wx_material_news_article
     *
     * @mbggenerated Mon May 12 11:41:38 CST 2014
     */
    int updateByPrimaryKeySelective(WxMaterialNewsArticle record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table wx_material_news_article
     *
     * @mbggenerated Mon May 12 11:41:38 CST 2014
     */
    int updateByPrimaryKey(WxMaterialNewsArticle record);
}