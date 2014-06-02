package com.bruce.geekway.dao.impl.klh;

import java.util.List;

import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.bruce.geekway.dao.klh.IKlhUserScoreHistoryDao;
import com.bruce.geekway.dao.mapper.KlhUserScoreHistoryMapper;
import com.bruce.geekway.model.KlhUserScoreHistory;
import com.bruce.geekway.model.KlhUserScoreHistoryCriteria;


@Repository
public class KlhUserScoreHistoryDaoImpl implements IKlhUserScoreHistoryDao, InitializingBean {

     @Autowired
    private KlhUserScoreHistoryMapper klhUserScoreHistoryMapper;

    @Override
    public int save(KlhUserScoreHistory t) {
        return klhUserScoreHistoryMapper.insert(t);
    }

    @Override
    public int updateById(KlhUserScoreHistory t) {
        return klhUserScoreHistoryMapper.updateByPrimaryKeySelective(t);
    }

    @Override
    public int deleteById(Integer id) {
        return klhUserScoreHistoryMapper.deleteByPrimaryKey(id);
    }

    @Override
    public KlhUserScoreHistory loadById(Integer id) {
        return klhUserScoreHistoryMapper.selectByPrimaryKey(id);
    }

    @Override
    public List<KlhUserScoreHistory> queryAll() {
        return klhUserScoreHistoryMapper.selectByExample(null);
    }

    @Override
    public List<KlhUserScoreHistory> fallLoadList(Integer tailId, int limit) {
        return null;
    }
    
    @Override
	public List<KlhUserScoreHistory> queryByUserId(int userId) {
    	KlhUserScoreHistoryCriteria criteria = new KlhUserScoreHistoryCriteria();
    	criteria.createCriteria().andKlhUserIdEqualTo(userId);
    	return klhUserScoreHistoryMapper.selectByExample(criteria);
	}

	@Override
	public int queryUserScore(int userId) {
		//TODO realize
		return 0;
	}

    
    
    @Override
    public void afterPropertiesSet() throws Exception {

    }

    public KlhUserScoreHistoryMapper getKlhUserScoreHistoryMapper() {
		return klhUserScoreHistoryMapper;
	}

	public void setKlhUserScoreHistoryMapper(KlhUserScoreHistoryMapper klhUserScoreHistoryMapper) {
		this.klhUserScoreHistoryMapper = klhUserScoreHistoryMapper;
	}

	
	

}
