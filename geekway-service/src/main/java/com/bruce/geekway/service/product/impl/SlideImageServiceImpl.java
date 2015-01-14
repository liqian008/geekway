package com.bruce.geekway.service.product.impl;

import java.util.List;

import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;

import com.bruce.foundation.model.paging.PagingResult;
import com.bruce.geekway.constants.ConstConfig;
import com.bruce.geekway.constants.ConstMemc;
import com.bruce.geekway.dao.mapper.SlideImageMapper;
import com.bruce.geekway.model.SlideImage;
import com.bruce.geekway.model.SlideImageCriteria;
import com.bruce.geekway.model.enumeration.GeekwayEnum;
import com.bruce.geekway.service.product.ISlideImageService;

public class SlideImageServiceImpl implements ISlideImageService, InitializingBean {

	@Autowired
	private SlideImageMapper slideImageMapper;

	@Override
	public int save(SlideImage t) {
		return slideImageMapper.insertSelective(t);
	}

	@Override
	public int updateById(SlideImage t) {
		return slideImageMapper.updateByPrimaryKeySelective(t);
	}

	@Override
	public int updateByCriteria(SlideImage t, SlideImageCriteria criteria) {
		return slideImageMapper.updateByExampleSelective(t, criteria);
	}

	@Override
	public int deleteById(Integer id) {
		return slideImageMapper.deleteByPrimaryKey(id);
	}

	@Override
	public int deleteByCriteria(SlideImageCriteria criteria) {
		return slideImageMapper.deleteByExample(criteria);
	}

	@Override
	public SlideImage loadById(Integer id) {
		return slideImageMapper.selectByPrimaryKey(id);
	}

	@Override
	public List<SlideImage> queryAll() {
		return queryAll(null);
	}

	@Override
	public List<SlideImage> queryAll(String orderByClause) {
		SlideImageCriteria criteria = new SlideImageCriteria();
		criteria.createCriteria();
		criteria.setOrderByClause(orderByClause);
		return queryByCriteria(criteria);
	}

	@Override
	public List<SlideImage> queryByCriteria(SlideImageCriteria criteria) {
		return slideImageMapper.selectByExample(criteria);
	}
	

	@Override
	public int countByCriteria(SlideImageCriteria criteria) {
		return slideImageMapper.countByExample(criteria);
	}
	

	@Override
	public List<SlideImage> fallloadByCriteria(int pageSize, SlideImageCriteria criteria) {
		return null;
	}

	@Override
	public PagingResult<SlideImage> pagingByCriteria(int pageNo, int pageSize, SlideImageCriteria criteria) {
		pageNo = pageNo<=0?1:pageNo;//确保pageNo合法
		pageSize = pageNo<=0?ConstConfig.PAGE_SIZE_DEFAULT:pageSize;//确保pageSize合法
		int offset = (pageNo-1)*pageSize;
		
		//构造查询条件
		if(criteria==null){
			criteria = new SlideImageCriteria();
		}
		
		criteria.setLimitOffset(offset);
		criteria.setLimitRows(pageSize);
		
		int count = slideImageMapper.countByExample(criteria);
		List<SlideImage> dataList = slideImageMapper.selectByExample(criteria);
		//返回分页数据
		return new PagingResult<SlideImage>(pageNo, pageSize, count, dataList);
	}
	
	

	@Override
	public void afterPropertiesSet() throws Exception {
	}


	@Override
	public List<SlideImage> queryByIndex() {
		SlideImageCriteria criteria = new SlideImageCriteria();
		criteria.createCriteria().andImageTypeEqualTo(GeekwayEnum.SlideImageTypeEnum.INDEX.getValue()).andStatusEqualTo(GeekwayEnum.CommonStatusEnum.OPENED.getStatus());
		return slideImageMapper.selectByExample(criteria);
	}

	@Override
	@Cacheable(value=ConstMemc.MEMCACHE_CACHE_VALUE, key="'slideImages:index")
	public List<SlideImage> queryCachedByIndex() {
		return queryByIndex();
	}

	@Override
	public List<SlideImage> queryByProductSkuId(int productSkuId) {
		SlideImageCriteria criteria = new SlideImageCriteria();
		criteria.createCriteria().andRootIdEqualTo(productSkuId).andImageTypeEqualTo(GeekwayEnum.SlideImageTypeEnum.PRODUCT.getValue()).andStatusEqualTo(GeekwayEnum.CommonStatusEnum.OPENED.getStatus());
		return slideImageMapper.selectByExample(criteria);
	}

	@Override
	@Cacheable(value=ConstMemc.MEMCACHE_CACHE_VALUE, key="'slideImages:productSkuId-'+#productSkuId")
	public List<SlideImage> queryCachedByProductSkuId(int productSkuId) {
		//TODO log
		return queryByProductSkuId(productSkuId);
	}

	@Override
	public List<SlideImage> queryByProductId(int productId) {
		SlideImageCriteria criteria = new SlideImageCriteria();
		criteria.createCriteria().andProductIdEqualTo(productId).andImageTypeEqualTo(GeekwayEnum.SlideImageTypeEnum.PRODUCT.getValue()).andStatusEqualTo(GeekwayEnum.CommonStatusEnum.OPENED.getStatus());
		return slideImageMapper.selectByExample(criteria);
	}

	@Override
	@Cacheable(value=ConstMemc.MEMCACHE_CACHE_VALUE, key="'slideImages:productId-'+#productId")
	public List<SlideImage> queryCachedByProductId(int productId) {
		//TODO log
		return queryByProductId(productId);
	}

	@Override
	public List<SlideImage> queryByCategoryId(int categoryId) {
		SlideImageCriteria criteria = new SlideImageCriteria();
		criteria.createCriteria().andRootIdEqualTo(categoryId).andImageTypeEqualTo(GeekwayEnum.SlideImageTypeEnum.CATEGORY.getValue()).andStatusEqualTo(GeekwayEnum.CommonStatusEnum.OPENED.getStatus());
		return slideImageMapper.selectByExample(criteria);
	}

	@Override
	@Cacheable(value=ConstMemc.MEMCACHE_CACHE_VALUE, key="'slideImages:categoryId-'+#categoryId")
	public List<SlideImage> queryCachedByCategoryId(int categoryId) {
		//TODO log
		return queryByCategoryId(categoryId);
	}

	@Override
	public List<SlideImage> queryByTagId(int tagId) {
		SlideImageCriteria criteria = new SlideImageCriteria();
		criteria.createCriteria().andRootIdEqualTo(tagId).andImageTypeEqualTo(GeekwayEnum.SlideImageTypeEnum.TAG.getValue()).andStatusEqualTo(GeekwayEnum.CommonStatusEnum.OPENED.getStatus());
		return slideImageMapper.selectByExample(criteria);
	}

	@Override
	@Cacheable(value=ConstMemc.MEMCACHE_CACHE_VALUE, key="'slideImages:tagId-'+#tagId")
	public List<SlideImage> queryCachedByTagId(int tagId) {
		//TODO log
		return queryByTagId(tagId);
	}
	
	public SlideImageMapper getSlideImageMapper() {
		return slideImageMapper;
	}

	public void setSlideImageMapper(SlideImageMapper slideImageMapper) {
		this.slideImageMapper = slideImageMapper;
	}

}