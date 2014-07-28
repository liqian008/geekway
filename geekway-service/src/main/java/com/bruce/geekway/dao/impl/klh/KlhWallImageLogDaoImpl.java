package com.bruce.geekway.dao.impl.klh;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.bruce.geekway.dao.klh.IKlhWallImageLogDao;
import com.bruce.geekway.dao.mapper.KlhWallImageLogMapper;
import com.bruce.geekway.model.KlhWallImageLog;
import com.bruce.geekway.model.KlhWallImageStatBean;


@Repository
public class KlhWallImageLogDaoImpl implements IKlhWallImageLogDao, InitializingBean {

     @Autowired
    private KlhWallImageLogMapper klhWallImageLogMapper;

    @Override
    public int save(KlhWallImageLog t) {
        return klhWallImageLogMapper.insert(t);
    }

    @Override
    public int updateById(KlhWallImageLog t) {
        return klhWallImageLogMapper.updateByPrimaryKeySelective(t);
    }

    @Override
    public int deleteById(Integer id) {
        return klhWallImageLogMapper.deleteByPrimaryKey(id);
    }

    @Override
    public KlhWallImageLog loadById(Integer id) {
        return klhWallImageLogMapper.selectByPrimaryKey(id);
    }

    @Override
    public List<KlhWallImageLog> queryAll() {
        return klhWallImageLogMapper.selectByExample(null);
    }

    @Override
    public List<KlhWallImageLog> fallLoadList(Integer tailId, int limit) {
        return null;
    }
    

    @Override
    public void afterPropertiesSet() throws Exception {

    }
    
    public KlhWallImageLogMapper getKlhWallImageLogMapper() {
		return klhWallImageLogMapper;
	}

	public void setKlhWallImageLogMapper(KlhWallImageLogMapper klhWallImageLogMapper) {
		this.klhWallImageLogMapper = klhWallImageLogMapper;
	}

	@Override
	public int increaseLike(int wallImageId) {
		KlhWallImageLog wallImageLog = new KlhWallImageLog();
		wallImageLog.setImageId(wallImageId);
		wallImageLog.setLikeOpt((short) 1);
		wallImageLog.setBrowseOpt((short) 0);
		wallImageLog.setCreateTime(new Date());
		return save(wallImageLog);
	}
	
	@Override
	public int increaseBrowse(int wallImageId) {
		KlhWallImageLog wallImageLog = new KlhWallImageLog();
		wallImageLog.setImageId(wallImageId);
		wallImageLog.setLikeOpt((short) 0);
		wallImageLog.setBrowseOpt((short) 1);
		wallImageLog.setCreateTime(new Date());
		return save(wallImageLog);
	}
	
	@Override
	public List<KlhWallImageStatBean> wallImageStat(int periodType){
		switch (periodType) {
		case 0:
			return klhWallImageLogMapper.weeklyStat();
		case 1:
			return klhWallImageLogMapper.monthlyStat();
		default:
			return klhWallImageLogMapper.totalStat();
		}
	}
	
}
