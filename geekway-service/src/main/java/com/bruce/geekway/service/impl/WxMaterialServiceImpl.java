package com.bruce.geekway.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bruce.geekway.dao.IWxMaterialDao;
import com.bruce.geekway.model.WxMaterial;
import com.bruce.geekway.service.IWxMaterialService;

@Service
public class WxMaterialServiceImpl implements IWxMaterialService{
	
	@Autowired
	private IWxMaterialDao wxMaterialDao;
	
	@Override
	public int save(WxMaterial t) {
		return wxMaterialDao.save(t);
	}

	@Override
	public int updateById(WxMaterial t) {
		return wxMaterialDao.updateById(t);
	}

	@Override
	public int deleteById(Integer id) {
		return wxMaterialDao.deleteById(id);
	}

	@Override
	public WxMaterial loadById(Integer id) {
		return wxMaterialDao.loadById(id);
	}

	@Override
	public List<WxMaterial> queryAll() {
		return wxMaterialDao.queryAll();
	}
	
	
//	@Override
//    public List<WxMaterial> queryMaterialsByModuleId(int moduleId) { 
//        return wxMaterialDao.queryMaterialsByModuleId(moduleId);
//    }
//	
//	@Override
//    public List<WxMaterial> queryMaterialsByModuleId(int moduleId, int limit) {
//        return wxMaterialDao.queryMaterialsByModuleId(moduleId, limit);
//    }
//	
//	@Override
//    public List<WxMaterial> queryMaterialsOutModuleId(int moduleId) { 
//        return wxMaterialDao.queryMaterialsOutModuleId(moduleId);
//    }

	public IWxMaterialDao getWxMaterialDao() {
		return wxMaterialDao;
	}

	public void setWxMaterialDao(IWxMaterialDao wxMaterialDao) {
		this.wxMaterialDao = wxMaterialDao;
	}
	
}