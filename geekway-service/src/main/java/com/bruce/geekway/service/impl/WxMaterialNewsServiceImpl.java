package com.bruce.geekway.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bruce.geekway.dao.IWxMaterialNewsDao;
import com.bruce.geekway.model.WxMaterialNews;
import com.bruce.geekway.service.IWxMaterialNewsService;

@Service
public class WxMaterialNewsServiceImpl implements IWxMaterialNewsService{
	
	@Autowired
	private IWxMaterialNewsDao wxMaterialNewsDao;
	
	@Override
	public int save(WxMaterialNews t) {
		return wxMaterialNewsDao.save(t);
	}

	@Override
	public int updateById(WxMaterialNews t) {
		return wxMaterialNewsDao.updateById(t);
	}

	@Override
	public int deleteById(Integer id) {
		return wxMaterialNewsDao.deleteById(id);
	}

	@Override
	public WxMaterialNews loadById(Integer id) {
		return wxMaterialNewsDao.loadById(id);
	}

	@Override
	public List<WxMaterialNews> queryAll() {
		return wxMaterialNewsDao.queryAll();
	}
	
	
//	@Override
//    public List<WxMaterialNews> queryMaterialNewssByCommandId(int commandId) { 
//        return wxMaterialNewsDao.queryMaterialNewssByCommandId(commandId);
//    }
//	
//	@Override
//    public List<WxMaterialNews> queryMaterialNewssByCommandId(int commandId, int limit) {
//        return wxMaterialNewsDao.queryMaterialNewssByCommandId(commandId, limit);
//    }
//	
//	@Override
//    public List<WxMaterialNews> queryMaterialNewssOutCommandId(int commandId) { 
//        return wxMaterialNewsDao.queryMaterialNewssOutCommandId(commandId);
//    }

	public IWxMaterialNewsDao getWxMaterialNewsDao() {
		return wxMaterialNewsDao;
	}

	public void setWxMaterialNewsDao(IWxMaterialNewsDao wxMaterialNewsDao) {
		this.wxMaterialNewsDao = wxMaterialNewsDao;
	}
	
}