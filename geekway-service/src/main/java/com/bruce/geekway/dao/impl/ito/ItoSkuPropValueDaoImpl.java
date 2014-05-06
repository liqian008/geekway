package com.bruce.geekway.dao.impl.ito;

import java.util.List;

import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.bruce.geekway.dao.ito.IItoSkuPropValueDao;
import com.bruce.geekway.dao.mapper.ItoSkuPropValueMapper;
import com.bruce.geekway.model.ItoSkuPropValue;


@Repository
public class ItoSkuPropValueDaoImpl implements IItoSkuPropValueDao, InitializingBean {

     @Autowired
    private ItoSkuPropValueMapper itoSkuPropValueMapper;

    @Override
    public int save(ItoSkuPropValue t) {
        return itoSkuPropValueMapper.insert(t);
    }

    @Override
    public int updateById(ItoSkuPropValue t) {
        return itoSkuPropValueMapper.updateByPrimaryKeySelective(t);
    }

    @Override
    public int deleteById(Integer id) {
        return itoSkuPropValueMapper.deleteByPrimaryKey(id);
    }

    @Override
    public ItoSkuPropValue loadById(Integer id) {
        return itoSkuPropValueMapper.selectByPrimaryKey(id);
    }

    @Override
    public List<ItoSkuPropValue> queryAll() {
        return itoSkuPropValueMapper.selectByExample(null);
    }

    @Override
    public List<ItoSkuPropValue> fallLoadList(Integer tailId, int limit) {
        return null;
    }

    @Override
    public void afterPropertiesSet() throws Exception {

    }

//    @Override
//    public List<ItoSkuPropValue> queryArticlesByModuleId(int moduleId) {
//        return itoSkuPropValueMapper.queryArticlesByModuleId(moduleId);
//    }
//    
//    @Override
//	public List<ItoSkuPropValue> queryArticlesByModuleId(int moduleId, int limit){
//    	return itoSkuPropValueMapper.queryArticlesByModuleIdLimit(moduleId, limit);
//	}
//    
//    @Override
//    public List<ItoSkuPropValue> queryArticlesOutModuleId(int moduleId) {
//        return itoSkuPropValueMapper.queryArticlesOutModuleId(moduleId);
//    }

    public ItoSkuPropValueMapper getItoSkuPropValueMapper() {
		return itoSkuPropValueMapper;
	}

	public void setItoSkuPropValueMapper(ItoSkuPropValueMapper itoSkuPropValueMapper) {
		this.itoSkuPropValueMapper = itoSkuPropValueMapper;
	}

	

}
