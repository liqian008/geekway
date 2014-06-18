package com.bruce.geekway.dao.impl.ito;

import java.util.List;

import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.bruce.geekway.dao.ito.IItoUserProfileDao;
import com.bruce.geekway.dao.mapper.ItoUserProfileMapper;
import com.bruce.geekway.model.ItoUserProfile;
import com.bruce.geekway.model.ItoUserProfileCriteria;


@Repository
public class ItoUserProfileDaoImpl implements IItoUserProfileDao, InitializingBean {

     @Autowired
    private ItoUserProfileMapper klhUserProfileMapper;

    @Override
    public int save(ItoUserProfile t) {
        return klhUserProfileMapper.insert(t);
    }

    @Override
    public int updateById(ItoUserProfile t) {
        return klhUserProfileMapper.updateByPrimaryKeySelective(t);
    }

    @Override
    public int deleteById(Integer id) {
        return klhUserProfileMapper.deleteByPrimaryKey(id);
    }

    @Override
    public ItoUserProfile loadById(Integer id) {
        return klhUserProfileMapper.selectByPrimaryKey(id);
    }

    @Override
    public List<ItoUserProfile> queryAll() {
        return klhUserProfileMapper.selectByExample(null);
    }

    @Override
    public List<ItoUserProfile> fallLoadList(Integer tailId, int limit) {
        return null;
    }
    
    
    @Override
    public void afterPropertiesSet() throws Exception {

    }

    public ItoUserProfileMapper getItoUserProfileMapper() {
		return klhUserProfileMapper;
	}

	public void setItoUserProfileMapper(ItoUserProfileMapper klhUserProfileMapper) {
		this.klhUserProfileMapper = klhUserProfileMapper;
	}

	

}
