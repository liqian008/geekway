package com.bruce.geekway.dao.impl.klh;

import java.util.List;

import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.bruce.geekway.dao.klh.IKlhVoteDao;
import com.bruce.geekway.dao.mapper.KlhVoteMapper;
import com.bruce.geekway.model.KlhVote;


@Repository
public class KlhVoteDaoImpl implements IKlhVoteDao, InitializingBean {

     @Autowired
    private KlhVoteMapper klhVoteMapper;

    @Override
    public int save(KlhVote t) {
        return klhVoteMapper.insert(t);
    }

    @Override
    public int updateById(KlhVote t) {
        return klhVoteMapper.updateByPrimaryKeySelective(t);
    }

    @Override
    public int deleteById(Integer id) {
        return klhVoteMapper.deleteByPrimaryKey(id);
    }

    @Override
    public KlhVote loadById(Integer id) {
        return klhVoteMapper.selectByPrimaryKey(id);
    }

    @Override
    public List<KlhVote> queryAll() {
        return klhVoteMapper.selectByExample(null);
    }

    @Override
    public List<KlhVote> fallLoadList(Integer tailId, int limit) {
        return null;
    }
    
    
    @Override
    public void afterPropertiesSet() throws Exception {

    }

    public KlhVoteMapper getKlhVoteMapper() {
		return klhVoteMapper;
	}

	public void setKlhVoteMapper(KlhVoteMapper klhVoteMapper) {
		this.klhVoteMapper = klhVoteMapper;
	}

	

}
