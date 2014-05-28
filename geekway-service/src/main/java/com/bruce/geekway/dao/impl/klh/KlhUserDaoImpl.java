package com.bruce.geekway.dao.impl.klh;

import java.util.List;

import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.bruce.geekway.dao.klh.IKlhUserDao;
import com.bruce.geekway.dao.mapper.KlhUserMapper;
import com.bruce.geekway.model.KlhUser;


@Repository
public class KlhUserDaoImpl implements IKlhUserDao, InitializingBean {

     @Autowired
    private KlhUserMapper klhUserMapper;

    @Override
    public int save(KlhUser t) {
        return klhUserMapper.insert(t);
    }

    @Override
    public int updateById(KlhUser t) {
        return klhUserMapper.updateByPrimaryKeySelective(t);
    }

    @Override
    public int deleteById(Integer id) {
        return klhUserMapper.deleteByPrimaryKey(id);
    }

    @Override
    public KlhUser loadById(Integer id) {
        return klhUserMapper.selectByPrimaryKey(id);
    }

    @Override
    public List<KlhUser> queryAll() {
        return klhUserMapper.selectByExample(null);
    }

    @Override
    public List<KlhUser> fallLoadList(Integer tailId, int limit) {
        return null;
    }
    
    
    @Override
    public void afterPropertiesSet() throws Exception {

    }

    public KlhUserMapper getKlhUserMapper() {
		return klhUserMapper;
	}

	public void setKlhUserMapper(KlhUserMapper klhUserMapper) {
		this.klhUserMapper = klhUserMapper;
	}

	

}
