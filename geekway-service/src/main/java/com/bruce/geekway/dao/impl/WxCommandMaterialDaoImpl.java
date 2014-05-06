package com.bruce.geekway.dao.impl;

import java.util.List;

import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.bruce.geekway.dao.IWxCommandMaterialDao;
import com.bruce.geekway.dao.mapper.WxCommandMaterialMapper;
import com.bruce.geekway.model.WxCommandMaterial;
import com.bruce.geekway.model.WxCommandMaterialCriteria;


@Repository
public class WxCommandMaterialDaoImpl implements IWxCommandMaterialDao, InitializingBean {

     @Autowired
    private WxCommandMaterialMapper wxCommandMaterialMapper;

    @Override
    public int save(WxCommandMaterial t) {
        return wxCommandMaterialMapper.insert(t);
    }

    @Override
    public int updateById(WxCommandMaterial t) {
        return wxCommandMaterialMapper.updateByPrimaryKeySelective(t);
    }

    @Override
    public int deleteById(Integer id) {
        return wxCommandMaterialMapper.deleteByPrimaryKey(id);
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
    public List<WxCommandMaterial> fallLoadList(Integer tailId, int limit) {
        return null;
    }

    @Override
    public void afterPropertiesSet() throws Exception {

    }

	public WxCommandMaterialMapper getWxCommandMaterialMapper() {
		return wxCommandMaterialMapper;
	}

	public void setWxCommandMaterialMapper(WxCommandMaterialMapper wxCommandMaterialMapper) {
		this.wxCommandMaterialMapper = wxCommandMaterialMapper;
	}

	@Override
	public int delete(int commandId, int materialId) {
		WxCommandMaterialCriteria criteria = new WxCommandMaterialCriteria();
        criteria.createCriteria().andCommandIdEqualTo(commandId).andMaterialIdEqualTo(materialId);
		return wxCommandMaterialMapper.deleteByExample(criteria);
	}

	
}
