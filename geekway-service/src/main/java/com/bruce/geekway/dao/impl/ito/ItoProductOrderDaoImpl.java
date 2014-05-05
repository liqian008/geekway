package com.bruce.geekway.dao.impl.ito;

import java.util.List;

import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.bruce.geekway.dao.ito.IItoProductOrderDao;
import com.bruce.geekway.dao.mapper.ItoProductOrderMapper;
import com.bruce.geekway.model.ItoProductOrder;


@Repository
public class ItoProductOrderDaoImpl implements IItoProductOrderDao, InitializingBean {

     @Autowired
    private ItoProductOrderMapper itoProductOrderMapper;

    @Override
    public int save(ItoProductOrder t) {
        return itoProductOrderMapper.insert(t);
    }

    @Override
    public int updateById(ItoProductOrder t) {
//        return itoProductOrderMapper.updateByPrimaryKey(t);
    	return itoProductOrderMapper.updateByPrimaryKeySelective(t);
    }

    @Override
    public int deleteById(Integer id) {
        return itoProductOrderMapper.deleteByPrimaryKey(id);
    }

    @Override
    public ItoProductOrder loadById(Integer id) {
        return itoProductOrderMapper.selectByPrimaryKey(id);
    }

    @Override
    public List<ItoProductOrder> queryAll() {
        return itoProductOrderMapper.selectByExample(null);
    }

    @Override
    public List<ItoProductOrder> fallLoadList(Integer tailId, int limit) {
        return null;
    }

    @Override
    public void afterPropertiesSet() throws Exception {

    }

//    @Override
//    public List<ItoProductOrder> queryArticlesByModuleId(int moduleId) {
//        return itoProductOrderMapper.queryArticlesByModuleId(moduleId);
//    }
//    
//    @Override
//	public List<ItoProductOrder> queryArticlesByModuleId(int moduleId, int limit){
//    	return itoProductOrderMapper.queryArticlesByModuleIdLimit(moduleId, limit);
//	}
//    
//    @Override
//    public List<ItoProductOrder> queryArticlesOutModuleId(int moduleId) {
//        return itoProductOrderMapper.queryArticlesOutModuleId(moduleId);
//    }

    public ItoProductOrderMapper getItoProductOrderMapper() {
		return itoProductOrderMapper;
	}

	public void setItoProductOrderMapper(ItoProductOrderMapper itoProductOrderMapper) {
		this.itoProductOrderMapper = itoProductOrderMapper;
	}

	

}
