package com.bruce.geekway.dao.impl.klh;

import java.util.List;

import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.bruce.geekway.dao.klh.IKlhVoteResultDao;
import com.bruce.geekway.dao.mapper.KlhVoteResultMapper;
import com.bruce.geekway.model.KlhVoteResult;
import com.bruce.geekway.model.KlhVoteResultCriteria;


@Repository
public class KlhVoteResultDaoImpl implements IKlhVoteResultDao, InitializingBean {

     @Autowired
    private KlhVoteResultMapper klhVoteResultMapper;

    @Override
    public int save(KlhVoteResult t) {
        return klhVoteResultMapper.insert(t);
    }

    @Override
    public int updateById(KlhVoteResult t) {
        return klhVoteResultMapper.updateByPrimaryKeySelective(t);
    }

    @Override
    public int deleteById(Integer id) {
        return klhVoteResultMapper.deleteByPrimaryKey(id);
    }

    @Override
    public KlhVoteResult loadById(Integer id) {
        return klhVoteResultMapper.selectByPrimaryKey(id);
    }

    @Override
    public List<KlhVoteResult> queryAll() {
        return klhVoteResultMapper.selectByExample(null);
    }

    @Override
    public List<KlhVoteResult> fallLoadList(Integer tailId, int limit) {
        return null;
    }
    
    @Override
    public List<KlhVoteResult> queryByVoteId(int voteId) {
    	KlhVoteResultCriteria criteria = new KlhVoteResultCriteria();
    	criteria.createCriteria().andVoteIdEqualTo(voteId);
    	return klhVoteResultMapper.selectByExample(criteria);
    }
    
    
    @Override
    public void afterPropertiesSet() throws Exception {

    }

    public KlhVoteResultMapper getKlhVoteResultMapper() {
		return klhVoteResultMapper;
	}

	public void setKlhVoteResultMapper(KlhVoteResultMapper klhVoteResultMapper) {
		this.klhVoteResultMapper = klhVoteResultMapper;
	}

	

}
