package com.bruce.geekway.service.impl;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bruce.foundation.model.paging.PagingResult;
import com.bruce.geekway.dao.mapper.WxCommandMaterialMapper;
import com.bruce.geekway.model.WxCommandCriteria;
import com.bruce.geekway.model.WxCommandMaterial;
import com.bruce.geekway.model.WxCommandMaterialCriteria;
import com.bruce.geekway.service.IWxCommandMaterialService;

@Service
public class WxCommandMaterialServiceImpl implements IWxCommandMaterialService {

	@Autowired
	private WxCommandMaterialMapper wxCommandMaterialMapper;

	@Override
	public int save(WxCommandMaterial t) {
		return wxCommandMaterialMapper.insertSelective(t);
	}

	@Override
	public int updateById(WxCommandMaterial t) {
		return wxCommandMaterialMapper.updateByPrimaryKeySelective(t);
	}

	@Override
	public int updateByCriteria(WxCommandMaterial t, WxCommandMaterialCriteria criteria) {
		return wxCommandMaterialMapper.updateByExampleSelective(t, criteria);
	}

	@Override
	public int deleteById(Integer id) {
		return wxCommandMaterialMapper.deleteByPrimaryKey(id);
	}

	@Override
	public int deleteByCriteria(WxCommandMaterialCriteria criteria) {
		return wxCommandMaterialMapper.deleteByExample(criteria);
	}

	@Override
	public WxCommandMaterial loadById(Integer id) {
		return wxCommandMaterialMapper.selectByPrimaryKey(id);
	}

	@Override
	public List<WxCommandMaterial> queryAll() {
		return wxCommandMaterialMapper.selectByExample(null);
	}

	@Override
	public List<WxCommandMaterial> queryAll(String orderByClause) {
		WxCommandCriteria criteria = new WxCommandCriteria();
		criteria.createCriteria();
		criteria.setOrderByClause(orderByClause);
		return wxCommandMaterialMapper.selectByExample(null);
	}

	@Override
	public List<WxCommandMaterial> queryByCriteria(WxCommandMaterialCriteria criteria) {
		return wxCommandMaterialMapper.selectByExample(criteria);
	}

	@Override
	public int delete(int commandId, int materialId) {
		WxCommandMaterialCriteria criteria = new WxCommandMaterialCriteria();
		criteria.createCriteria().andCommandIdEqualTo(commandId).andMaterialIdEqualTo(materialId);
		return wxCommandMaterialMapper.deleteByExample(criteria);
	}

	@Override
	public int deleteByCommandId(int commandId) {
		WxCommandMaterialCriteria criteria = new WxCommandMaterialCriteria();
		criteria.createCriteria().andCommandIdEqualTo(commandId);
		return wxCommandMaterialMapper.deleteByExample(criteria);
	}

	@Override
	public int deleteByMaterialId(int materialId) {
		WxCommandMaterialCriteria criteria = new WxCommandMaterialCriteria();
		criteria.createCriteria().andMaterialIdEqualTo(materialId);
		return wxCommandMaterialMapper.deleteByExample(criteria);
	}

	/**
	 * 置顶资源
	 */
	public int topCommandMaterial(int commandId, int materialId) {
		WxCommandMaterialCriteria criteria = new WxCommandMaterialCriteria();
		criteria.createCriteria().andCommandIdEqualTo(commandId).andMaterialIdEqualTo(materialId);

		// 修改top
		WxCommandMaterial commandMaterial = new WxCommandMaterial();
		commandMaterial.setTopTime(new Date());
		return wxCommandMaterialMapper.updateByExampleSelective(commandMaterial, criteria);
	}

	@Override
	public List<WxCommandMaterial> fallloadByCriteria(int pageSize, WxCommandMaterialCriteria criteria) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public PagingResult<WxCommandMaterial> pagingByCriteria(int pageNo, int pageSize, WxCommandMaterialCriteria criteria) {
		// TODO Auto-generated method stub
		return null;
	}

	public WxCommandMaterialMapper getWxCommandMaterialMapper() {
		return wxCommandMaterialMapper;
	}

	public void setWxCommandMaterialMapper(WxCommandMaterialMapper wxCommandMaterialMapper) {
		this.wxCommandMaterialMapper = wxCommandMaterialMapper;
	}

}