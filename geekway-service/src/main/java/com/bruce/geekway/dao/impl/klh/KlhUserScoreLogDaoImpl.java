package com.bruce.geekway.dao.impl.klh;

import java.util.List;

import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.bruce.geekway.dao.klh.IKlhUserScoreLogDao;
import com.bruce.geekway.dao.mapper.KlhUserScoreLogMapper;
import com.bruce.geekway.model.KlhUserScoreLog;
import com.bruce.geekway.model.KlhUserScoreLogCriteria;


@Repository
public class KlhUserScoreLogDaoImpl implements IKlhUserScoreLogDao, InitializingBean {

     @Autowired
    private KlhUserScoreLogMapper klhUserScoreLogMapper;

    @Override
    public int save(KlhUserScoreLog t) {
        return klhUserScoreLogMapper.insert(t);
    }

    @Override
    public int updateById(KlhUserScoreLog t) {
        return klhUserScoreLogMapper.updateByPrimaryKeySelective(t);
    }

    @Override
    public int deleteById(Integer id) {
        return klhUserScoreLogMapper.deleteByPrimaryKey(id);
    }

    @Override
    public KlhUserScoreLog loadById(Integer id) {
        return klhUserScoreLogMapper.selectByPrimaryKey(id);
    }

    @Override
    public List<KlhUserScoreLog> queryAll() {
        return klhUserScoreLogMapper.selectByExample(null);
    }

    @Override
    public List<KlhUserScoreLog> fallLoadList(Integer tailId, int limit) {
        return null;
    }
    
    @Override
	public List<KlhUserScoreLog> queryByUserOpenId(String userOpenId) { 
    	KlhUserScoreLogCriteria criteria = new KlhUserScoreLogCriteria();
    	criteria.createCriteria().andUserOpenIdEqualTo(userOpenId);
    	criteria.setOrderByClause(" sign_date desc");
    	return klhUserScoreLogMapper.selectByExample(criteria);
	}

	@Override
	public int queryUserScore(String userOpenId) {
		//TODO realize
		return 0;
	}

    
    
    @Override
    public void afterPropertiesSet() throws Exception {

    }

    public KlhUserScoreLogMapper getKlhUserScoreLogMapper() {
		return klhUserScoreLogMapper;
	}

	public void setKlhUserScoreLogMapper(KlhUserScoreLogMapper klhUserScoreLogMapper) {
		this.klhUserScoreLogMapper = klhUserScoreLogMapper;
	}

	
	

}
