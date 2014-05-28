package com.bruce.geekway.dao.impl.klh;

import java.util.List;

import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.bruce.geekway.dao.klh.IKlhBindSettingDao;
import com.bruce.geekway.dao.mapper.KlhBindSettingMapper;
import com.bruce.geekway.model.KlhBindSetting;


@Repository
public class KlhBindSettingDaoImpl implements IKlhBindSettingDao, InitializingBean {

     @Autowired
    private KlhBindSettingMapper klhBindSettingMapper;

    @Override
    public int save(KlhBindSetting t) {
        return klhBindSettingMapper.insert(t);
    }

    @Override
    public int updateById(KlhBindSetting t) {
        return klhBindSettingMapper.updateByPrimaryKeySelective(t);
    }

    @Override
    public int deleteById(Integer id) {
        return klhBindSettingMapper.deleteByPrimaryKey(id);
    }

    @Override
    public KlhBindSetting loadById(Integer id) {
        return klhBindSettingMapper.selectByPrimaryKey(id);
    }

    @Override
    public List<KlhBindSetting> queryAll() {
        return klhBindSettingMapper.selectByExample(null);
    }

    @Override
    public List<KlhBindSetting> fallLoadList(Integer tailId, int limit) {
        return null;
    }
    
    
    @Override
    public void afterPropertiesSet() throws Exception {

    }

    public KlhBindSettingMapper getKlhBindSettingMapper() {
		return klhBindSettingMapper;
	}

	public void setKlhBindSettingMapper(KlhBindSettingMapper klhBindSettingMapper) {
		this.klhBindSettingMapper = klhBindSettingMapper;
	}

	

}
