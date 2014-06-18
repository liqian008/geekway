package com.bruce.geekway.dao.impl.ito;

import java.util.List;

import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.bruce.geekway.dao.ito.IItoProductDao;
import com.bruce.geekway.dao.mapper.ItoProductMapper;
import com.bruce.geekway.model.ItoProduct;
import com.bruce.geekway.model.ItoProductCriteria;


@Repository
public class ItoProductDaoImpl implements IItoProductDao, InitializingBean {

     @Autowired
    private ItoProductMapper itoProductMapper;

    @Override
    public int save(ItoProduct t) {
        return itoProductMapper.insert(t);
    }

    @Override
    public int updateById(ItoProduct t) {
//        return itoProductMapper.updateByPrimaryKey(t);
    	return itoProductMapper.updateByPrimaryKeySelective(t);
    }

    @Override
    public int deleteById(Integer id) {
        return itoProductMapper.deleteByPrimaryKey(id);
    }

    @Override
    public ItoProduct loadById(Integer id) {
        return itoProductMapper.selectByPrimaryKey(id);
    }

    @Override
    public List<ItoProduct> queryAll() {
        return itoProductMapper.selectByExample(null);
    }

    @Override
    public List<ItoProduct> fallLoadList(Integer tailId, int limit) {
        return null;
    }

    @Override
	public List<ItoProduct> queryAvailableList() {
    	ItoProductCriteria criteria = new ItoProductCriteria();
    	criteria.createCriteria().andStatusEqualTo((short) 1);
		return itoProductMapper.selectByExample(criteria);
	}

    
    @Override
    public void afterPropertiesSet() throws Exception {

    }

//    @Override
//    public List<ItoProduct> queryArticlesByModuleId(int moduleId) {
//        return itoProductMapper.queryArticlesByModuleId(moduleId);
//    }
//    
//    @Override
//	public List<ItoProduct> queryArticlesByModuleId(int moduleId, int limit){
//    	return itoProductMapper.queryArticlesByModuleIdLimit(moduleId, limit);
//	}
//    
//    @Override
//    public List<ItoProduct> queryArticlesOutModuleId(int moduleId) {
//        return itoProductMapper.queryArticlesOutModuleId(moduleId);
//    }

    public ItoProductMapper getItoProductMapper() {
		return itoProductMapper;
	}

	public void setItoProductMapper(ItoProductMapper itoProductMapper) {
		this.itoProductMapper = itoProductMapper;
	}

	
	

}
