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
    private ItoUserProfileMapper itoUserProfileMapper;

    @Override
    public int save(ItoUserProfile t) {
        return itoUserProfileMapper.insert(t);
    }

    @Override
    public int updateById(ItoUserProfile t) {
        return itoUserProfileMapper.updateByPrimaryKeySelective(t);
    }

    @Override
    public int deleteById(Integer id) {
        return itoUserProfileMapper.deleteByPrimaryKey(id);
    }

    @Override
    public ItoUserProfile loadById(Integer id) {
        return itoUserProfileMapper.selectByPrimaryKey(id);
    }

    @Override
    public List<ItoUserProfile> queryAll() {
        return itoUserProfileMapper.selectByExample(null);
    }

    @Override
    public List<ItoUserProfile> fallLoadList(Integer tailId, int limit) {
        return null;
    }
    
    @Override
	public boolean usernameExists(String username) {
		ItoUserProfileCriteria criteria = new ItoUserProfileCriteria();
		criteria.createCriteria().andUsernameEqualTo(username);
		return itoUserProfileMapper.countByExample(criteria)>0;
	}

	@Override
	public boolean mobileExists(String mobile) {
		ItoUserProfileCriteria criteria = new ItoUserProfileCriteria();
		criteria.createCriteria().andMobileEqualTo(mobile);
		return itoUserProfileMapper.countByExample(criteria)>0;
	}
    
    @Override
    public void afterPropertiesSet() throws Exception {

    }

    public ItoUserProfileMapper getItoUserProfileMapper() {
		return itoUserProfileMapper;
	}

	public void setItoUserProfileMapper(ItoUserProfileMapper itoUserProfileMapper) {
		this.itoUserProfileMapper = itoUserProfileMapper;
	}

	

}
