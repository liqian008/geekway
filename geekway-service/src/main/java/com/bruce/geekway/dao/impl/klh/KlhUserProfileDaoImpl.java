package com.bruce.geekway.dao.impl.klh;

import java.util.List;

import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.bruce.geekway.dao.klh.IKlhUserProfileDao;
import com.bruce.geekway.dao.mapper.KlhUserProfileMapper;
import com.bruce.geekway.model.KlhUserProfile;
import com.bruce.geekway.model.KlhUserProfileCriteria;


@Repository
public class KlhUserProfileDaoImpl implements IKlhUserProfileDao, InitializingBean {

     @Autowired
    private KlhUserProfileMapper klhUserProfileMapper;

    @Override
    public int save(KlhUserProfile t) {
        return klhUserProfileMapper.insert(t);
    }

    @Override
    public int updateById(KlhUserProfile t) {
        return klhUserProfileMapper.updateByPrimaryKeySelective(t);
    }

    @Override
    public int deleteById(Integer id) {
        return klhUserProfileMapper.deleteByPrimaryKey(id);
    }

    @Override
    public KlhUserProfile loadById(Integer id) {
        return klhUserProfileMapper.selectByPrimaryKey(id);
    }

    @Override
    public List<KlhUserProfile> queryAll() {
        return klhUserProfileMapper.selectByExample(null);
    }

    @Override
    public List<KlhUserProfile> fallLoadList(Integer tailId, int limit) {
        return null;
    }
    
    @Override
    public KlhUserProfile loadByOpenid(String openid){
    	KlhUserProfileCriteria criteria = new KlhUserProfileCriteria();
    	criteria.createCriteria().andUserOpenIdEqualTo(openid);
    	List<KlhUserProfile> userProfileList = klhUserProfileMapper.selectByExample(criteria);
    	if(userProfileList!=null&&userProfileList.size()>0){
    		return userProfileList.get(0);
    	}
    	return null;
    }
	
    
    
    @Override
    public void afterPropertiesSet() throws Exception {

    }

    public KlhUserProfileMapper getKlhUserProfileMapper() {
		return klhUserProfileMapper;
	}

	public void setKlhUserProfileMapper(KlhUserProfileMapper klhUserProfileMapper) {
		this.klhUserProfileMapper = klhUserProfileMapper;
	}

	

}
