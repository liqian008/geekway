package com.bruce.geekway.dao.impl.ito;

import java.util.List;

import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.bruce.geekway.dao.ito.IItoSkuDao;
import com.bruce.geekway.dao.mapper.ItoSkuMapper;
import com.bruce.geekway.model.ItoSku;
import com.bruce.geekway.model.ItoSkuCriteria;


@Repository
public class ItoSkuDaoImpl implements IItoSkuDao, InitializingBean {

     @Autowired
    private ItoSkuMapper itoSkuMapper;

    @Override
    public int save(ItoSku t) {
        return itoSkuMapper.insert(t);
    }

    @Override
    public int updateById(ItoSku t) {
        return itoSkuMapper.updateByPrimaryKey(t);
    }

    @Override
    public int deleteById(Integer id) {
        return itoSkuMapper.deleteByPrimaryKey(id);
    }

    @Override
    public ItoSku loadById(Integer id) {
        return itoSkuMapper.selectByPrimaryKey(id);
    }

    @Override
    public List<ItoSku> queryAll() {
        return itoSkuMapper.selectByExample(null);
    }

    @Override
    public List<ItoSku> fallLoadList(Integer tailId, int limit) {
        return null;
    }
    
    @Override
	public List<ItoSku> queryAllByProductId(int productId) {
    	ItoSkuCriteria criteria = new ItoSkuCriteria();
		criteria.createCriteria().andProductIdEqualTo(productId);
		return itoSkuMapper.selectByExample(criteria);
	}
    

    @Override
    public void afterPropertiesSet() throws Exception {

    }

//    @Override
//    public List<ItoSku> queryArticlesByModuleId(int moduleId) {
//        return itoSkuMapper.queryArticlesByModuleId(moduleId);
//    }
//    
//    @Override
//	public List<ItoSku> queryArticlesByModuleId(int moduleId, int limit){
//    	return itoSkuMapper.queryArticlesByModuleIdLimit(moduleId, limit);
//	}
//    
//    @Override
//    public List<ItoSku> queryArticlesOutModuleId(int moduleId) {
//        return itoSkuMapper.queryArticlesOutModuleId(moduleId);
//    }

    public ItoSkuMapper getItoSkuMapper() {
		return itoSkuMapper;
	}

	public void setItoSkuMapper(ItoSkuMapper itoSkuMapper) {
		this.itoSkuMapper = itoSkuMapper;
	}

	

}
