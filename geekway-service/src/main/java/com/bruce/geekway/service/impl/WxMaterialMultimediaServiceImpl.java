package com.bruce.geekway.service.impl;

import java.util.List;

import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bruce.foundation.model.paging.PagingResult;
import com.bruce.geekway.constants.ConstConfig;
import com.bruce.geekway.dao.mapper.WxMaterialMultimediaMapper;
import com.bruce.geekway.model.WxMaterialMultimedia;
import com.bruce.geekway.model.WxMaterialMultimediaCriteria;
import com.bruce.geekway.service.IWxMaterialMultimediaService;

/**
 * 
 * @author liqian
 *
 */
@Service
public class WxMaterialMultimediaServiceImpl implements IWxMaterialMultimediaService, InitializingBean {
	
	@Autowired
	private WxMaterialMultimediaMapper wxMaterialMultimediaMapper;

	@Override
	public int save(WxMaterialMultimedia t) {
		return wxMaterialMultimediaMapper.insertSelective(t);
	}

	@Override
	public int updateById(WxMaterialMultimedia t) {
		return wxMaterialMultimediaMapper.updateByPrimaryKeySelective(t);
	}

	@Override
	public int updateByCriteria(WxMaterialMultimedia t, WxMaterialMultimediaCriteria criteria) {
		return wxMaterialMultimediaMapper.updateByExampleSelective(t, criteria);
	}

	@Override
	public int deleteById(Integer id) {
		return wxMaterialMultimediaMapper.deleteByPrimaryKey(id);
	}

	@Override
	public int deleteByCriteria(WxMaterialMultimediaCriteria criteria) {
		return wxMaterialMultimediaMapper.deleteByExample(criteria);
	}

	@Override
	public WxMaterialMultimedia loadById(Integer id) {
		return wxMaterialMultimediaMapper.selectByPrimaryKey(id);
	}

	@Override
	public List<WxMaterialMultimedia> queryAll() {
		return queryAll(null);
	}

	@Override
	public List<WxMaterialMultimedia> queryAll(String orderByClause) {
		WxMaterialMultimediaCriteria criteria = new WxMaterialMultimediaCriteria();
		criteria.createCriteria();
		criteria.setOrderByClause(orderByClause);
		return queryByCriteria(criteria);
	}

	@Override
	public List<WxMaterialMultimedia> queryByCriteria(WxMaterialMultimediaCriteria criteria) {
		return wxMaterialMultimediaMapper.selectByExample(criteria);
	}
	
	@Override
	public WxMaterialMultimedia loadImageById(Integer id) {
		WxMaterialMultimediaCriteria criteria = new WxMaterialMultimediaCriteria();
		criteria.createCriteria().andIdEqualTo(id).andMaterialTypeEqualTo(MATERIAL_TYPE_IMAGE);
		List<WxMaterialMultimedia> multimediaList =  queryByCriteria(criteria);
		if(multimediaList!=null&&multimediaList.size()>0){
			return multimediaList.get(0);
		}
		return null;
	}

	@Override
	public WxMaterialMultimedia loadVoiceById(Integer id) {
		WxMaterialMultimediaCriteria criteria = new WxMaterialMultimediaCriteria();
		criteria.createCriteria().andIdEqualTo(id).andMaterialTypeEqualTo(MATERIAL_TYPE_VOICE);
		List<WxMaterialMultimedia> multimediaList =  queryByCriteria(criteria);
		if(multimediaList!=null&&multimediaList.size()>0){
			return multimediaList.get(0);
		}
		return null;
	}
	
	@Override
	public List<WxMaterialMultimedia> queryImageMaterials() {
		WxMaterialMultimediaCriteria criteria = new WxMaterialMultimediaCriteria();
		criteria.createCriteria().andMaterialTypeEqualTo(MATERIAL_TYPE_IMAGE);
		return queryByCriteria(criteria);
	}

	@Override
	public List<WxMaterialMultimedia> queryVoiceMaterials() {
		WxMaterialMultimediaCriteria criteria = new WxMaterialMultimediaCriteria();
		criteria.createCriteria().andMaterialTypeEqualTo(MATERIAL_TYPE_VOICE);
		return queryByCriteria(criteria);
	}


	@Override
	public List<WxMaterialMultimedia> fallloadByCriteria(int pageSize, WxMaterialMultimediaCriteria criteria) {
		return null;
	}

	@Override
	public PagingResult<WxMaterialMultimedia> pagingByCriteria(int pageNo, int pageSize, WxMaterialMultimediaCriteria criteria) {
		pageNo = pageNo<=0?1:pageNo;//确保pageNo合法
		pageSize = pageNo<=0?ConstConfig.PAGE_SIZE_DEFAULT:pageSize;//确保pageSize合法
		int offset = (pageNo-1)*pageSize;
		
		//构造查询条件
		if(criteria==null){
			criteria = new WxMaterialMultimediaCriteria();
		}
		
		criteria.setLimitOffset(offset);
		criteria.setLimitRows(pageSize);
		
		int count = wxMaterialMultimediaMapper.countByExample(criteria);
		List<WxMaterialMultimedia> dataList = wxMaterialMultimediaMapper.selectByExample(criteria);
		//返回分页数据
		return new PagingResult<WxMaterialMultimedia>(pageNo, pageSize, count, dataList);
	}
	

	@Override
	public void afterPropertiesSet() throws Exception {

	}

	public WxMaterialMultimediaMapper getWxMaterialMultimediaMapper() {
		return wxMaterialMultimediaMapper;
	}

	public void setWxMaterialMultimediaMapper(WxMaterialMultimediaMapper wxMaterialMultimediaMapper) {
		this.wxMaterialMultimediaMapper = wxMaterialMultimediaMapper;
	}

	


}