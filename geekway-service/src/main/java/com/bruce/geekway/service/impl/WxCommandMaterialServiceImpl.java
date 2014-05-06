package com.bruce.geekway.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bruce.geekway.dao.IWxCommandMaterialDao;
import com.bruce.geekway.model.WxCommandMaterial;
import com.bruce.geekway.service.IWxCommandMaterialService;

@Service
public class WxCommandMaterialServiceImpl implements IWxCommandMaterialService{
	
	@Autowired
	private IWxCommandMaterialDao wxCommandMaterialDao;
	
	@Override
	public int save(WxCommandMaterial t) {
		return wxCommandMaterialDao.save(t);
	}

	@Override
	public int updateById(WxCommandMaterial t) {
		return wxCommandMaterialDao.updateById(t);
	}

	@Override
	public int deleteById(Integer id) {
		return wxCommandMaterialDao.deleteById(id);
	}

	@Override
	public WxCommandMaterial loadById(Integer id) {
		return wxCommandMaterialDao.loadById(id);
	}

	@Override
	public List<WxCommandMaterial> queryAll() {
		return wxCommandMaterialDao.queryAll();
	}
	
	@Override
	public int delete(int commandId, int materialId) {
		return wxCommandMaterialDao.delete(commandId, materialId);
	}
	@Override
	public int deleteByCommandId(int commandId){
		return wxCommandMaterialDao.deleteByCommandId(commandId);
	}

	@Override
	public int deleteByMaterialId(int materialId){
		return wxCommandMaterialDao.deleteByMaterialId(materialId);
	}
	
}