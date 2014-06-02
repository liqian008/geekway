package com.bruce.geekway.dao.impl.klh;

import java.util.List;

import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.bruce.geekway.dao.klh.IKlhVoteOptionDao;
import com.bruce.geekway.dao.mapper.KlhVoteOptionMapper;
import com.bruce.geekway.model.KlhVoteOption;
import com.bruce.geekway.model.KlhVoteOptionCriteria;


@Repository
public class KlhVoteOptionDaoImpl implements IKlhVoteOptionDao, InitializingBean {

     @Autowired
    private KlhVoteOptionMapper klhVoteOptionMapper;

    @Override
    public int save(KlhVoteOption t) {
        return klhVoteOptionMapper.insert(t);
    }

    @Override
    public int updateById(KlhVoteOption t) {
        return klhVoteOptionMapper.updateByPrimaryKeySelective(t);
    }

    @Override
    public int deleteById(Integer id) {
        return klhVoteOptionMapper.deleteByPrimaryKey(id);
    }

    @Override
    public KlhVoteOption loadById(Integer id) {
        return klhVoteOptionMapper.selectByPrimaryKey(id);
    }

    @Override
    public List<KlhVoteOption> queryAll() {
        return klhVoteOptionMapper.selectByExample(null);
    }

    @Override
    public List<KlhVoteOption> fallLoadList(Integer tailId, int limit) {
        return null;
    }
    
    @Override
    public List<KlhVoteOption> queryByVoteId(int voteId) {
    	KlhVoteOptionCriteria criteria = new KlhVoteOptionCriteria();
    	criteria.createCriteria().andVoteIdEqualTo(voteId);
    	return klhVoteOptionMapper.selectByExample(criteria);
    }
    
    
    @Override
    public void afterPropertiesSet() throws Exception {

    }

    public KlhVoteOptionMapper getKlhVoteOptionMapper() {
		return klhVoteOptionMapper;
	}

	public void setKlhVoteOptionMapper(KlhVoteOptionMapper klhVoteOptionMapper) {
		this.klhVoteOptionMapper = klhVoteOptionMapper;
	}

	

}
