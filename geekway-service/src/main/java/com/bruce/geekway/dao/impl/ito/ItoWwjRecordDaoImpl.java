package com.bruce.geekway.dao.impl.ito;

import java.util.List;

import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.bruce.geekway.dao.ito.IItoWwjRecordDao;
import com.bruce.geekway.dao.mapper.ItoWwjRecordMapper;
import com.bruce.geekway.model.ItoWwjRecord;


@Repository
public class ItoWwjRecordDaoImpl implements IItoWwjRecordDao, InitializingBean {

     @Autowired
    private ItoWwjRecordMapper itoWwjRecordMapper;

    @Override
    public int save(ItoWwjRecord t) {
        return itoWwjRecordMapper.insert(t);
    }

    @Override
    public int updateById(ItoWwjRecord t) {
//        return itoWwjRecordMapper.updateByPrimaryKey(t);
    	return itoWwjRecordMapper.updateByPrimaryKeySelective(t);
    }

    @Override
    public int deleteById(Integer id) {
        return itoWwjRecordMapper.deleteByPrimaryKey(id);
    }

    @Override
    public ItoWwjRecord loadById(Integer id) {
        return itoWwjRecordMapper.selectByPrimaryKey(id);
    }

    @Override
    public List<ItoWwjRecord> queryAll() {
        return itoWwjRecordMapper.selectByExample(null);
    }

    @Override
    public List<ItoWwjRecord> fallLoadList(Integer tailId, int limit) {
        return null;
    }

    @Override
    public void afterPropertiesSet() throws Exception {

    }

//    @Override
//    public List<ItoWwjRecord> queryArticlesByModuleId(int moduleId) {
//        return itoWwjRecordMapper.queryArticlesByModuleId(moduleId);
//    }
//    
//    @Override
//	public List<ItoWwjRecord> queryArticlesByModuleId(int moduleId, int limit){
//    	return itoWwjRecordMapper.queryArticlesByModuleIdLimit(moduleId, limit);
//	}
//    
//    @Override
//    public List<ItoWwjRecord> queryArticlesOutModuleId(int moduleId) {
//        return itoWwjRecordMapper.queryArticlesOutModuleId(moduleId);
//    }

    public ItoWwjRecordMapper getItoWwjRecordMapper() {
		return itoWwjRecordMapper;
	}

	public void setItoWwjRecordMapper(ItoWwjRecordMapper itoWwjRecordMapper) {
		this.itoWwjRecordMapper = itoWwjRecordMapper;
	}

	

}
