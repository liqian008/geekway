package com.bruce.geekway.dao.impl.klh;

import java.util.List;

import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.bruce.geekway.dao.klh.IKlhScoreSettingDao;
import com.bruce.geekway.dao.mapper.KlhScoreSettingMapper;
import com.bruce.geekway.model.KlhScoreSetting;


@Repository
public class KlhScoreSettingDaoImpl implements IKlhScoreSettingDao, InitializingBean {

     @Autowired
    private KlhScoreSettingMapper klhScoreSettingMapper;

    @Override
    public int save(KlhScoreSetting t) {
        return klhScoreSettingMapper.insert(t);
    }

    @Override
    public int updateById(KlhScoreSetting t) {
        return klhScoreSettingMapper.updateByPrimaryKeySelective(t);
    }

    @Override
    public int deleteById(Integer id) {
        return klhScoreSettingMapper.deleteByPrimaryKey(id);
    }

    @Override
    public KlhScoreSetting loadById(Integer id) {
        return klhScoreSettingMapper.selectByPrimaryKey(id);
    }

    @Override
    public List<KlhScoreSetting> queryAll() {
        return klhScoreSettingMapper.selectByExample(null);
    }

    @Override
    public List<KlhScoreSetting> fallLoadList(Integer tailId, int limit) {
        return null;
    }
    
    
    @Override
    public void afterPropertiesSet() throws Exception {

    }

    public KlhScoreSettingMapper getKlhScoreSettingMapper() {
		return klhScoreSettingMapper;
	}

	public void setKlhScoreSettingMapper(KlhScoreSettingMapper klhScoreSettingMapper) {
		this.klhScoreSettingMapper = klhScoreSettingMapper;
	}

	

}
