package com.bruce.geekway.dao.impl.klh;

import java.util.List;

import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.bruce.geekway.dao.klh.IKlhSettingDao;
import com.bruce.geekway.dao.mapper.KlhSettingMapper;
import com.bruce.geekway.model.KlhSetting;


@Repository
public class KlhSettingDaoImpl implements IKlhSettingDao, InitializingBean {

     @Autowired
    private KlhSettingMapper klhSettingMapper;

    @Override
    public int save(KlhSetting t) {
        return klhSettingMapper.insert(t);
    }

    @Override
    public int updateById(KlhSetting t) {
        return klhSettingMapper.updateByPrimaryKeySelective(t);
    }

    @Override
    public int deleteById(Integer id) {
        return klhSettingMapper.deleteByPrimaryKey(id);
    }

    @Override
    public KlhSetting loadById(Integer id) {
        return klhSettingMapper.selectByPrimaryKey(id);
    }

    @Override
    public List<KlhSetting> queryAll() {
        return klhSettingMapper.selectByExample(null);
    }

    @Override
    public List<KlhSetting> fallLoadList(Integer tailId, int limit) {
        return null;
    }
    
    
    @Override
    public void afterPropertiesSet() throws Exception {

    }

    public KlhSettingMapper getKlhSettingMapper() {
		return klhSettingMapper;
	}

	public void setKlhSettingMapper(KlhSettingMapper klhSettingMapper) {
		this.klhSettingMapper = klhSettingMapper;
	}

	

}
