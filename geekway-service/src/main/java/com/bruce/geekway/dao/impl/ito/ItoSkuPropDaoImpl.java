package com.bruce.geekway.dao.impl.ito;

import java.util.List;

import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.bruce.geekway.dao.ito.IItoSkuPropDao;
import com.bruce.geekway.dao.mapper.ItoSkuPropMapper;
import com.bruce.geekway.model.ItoSkuProp;


@Repository
public class ItoSkuPropDaoImpl implements IItoSkuPropDao, InitializingBean {

     @Autowired
    private ItoSkuPropMapper itoSkuPropMapper;

    @Override
    public int save(ItoSkuProp t) {
        return itoSkuPropMapper.insert(t);
    }

    @Override
    public int updateById(ItoSkuProp t) {
        return itoSkuPropMapper.updateByPrimaryKeySelective(t);
    }

    @Override
    public int deleteById(Integer id) {
        return itoSkuPropMapper.deleteByPrimaryKey(id);
    }

    @Override
    public ItoSkuProp loadById(Integer id) {
        return itoSkuPropMapper.selectByPrimaryKey(id);
    }

    @Override
    public List<ItoSkuProp> queryAll() {
        return itoSkuPropMapper.selectByExample(null);
    }

    @Override
    public List<ItoSkuProp> fallLoadList(Integer tailId, int limit) {
        return null;
    }

    @Override
    public void afterPropertiesSet() throws Exception {

    }

//    @Override
//    public List<ItoSkuProp> queryArticlesByModuleId(int moduleId) {
//        return itoSkuPropMapper.queryArticlesByModuleId(moduleId);
//    }
//    
//    @Override
//	public List<ItoSkuProp> queryArticlesByModuleId(int moduleId, int limit){
//    	return itoSkuPropMapper.queryArticlesByModuleIdLimit(moduleId, limit);
//	}
//    
//    @Override
//    public List<ItoSkuProp> queryArticlesOutModuleId(int moduleId) {
//        return itoSkuPropMapper.queryArticlesOutModuleId(moduleId);
//    }

    public ItoSkuPropMapper getItoSkuMapper() {
		return itoSkuPropMapper;
	}

	public void setItoSkuPropMapper(ItoSkuPropMapper itoSkuPropMapper) {
		this.itoSkuPropMapper = itoSkuPropMapper;
	}

	

}
