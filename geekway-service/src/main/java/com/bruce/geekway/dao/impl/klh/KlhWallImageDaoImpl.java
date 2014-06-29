package com.bruce.geekway.dao.impl.klh;

import java.util.List;

import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.bruce.geekway.dao.klh.IKlhWallImageDao;
import com.bruce.geekway.dao.mapper.KlhWallImageMapper;
import com.bruce.geekway.model.KlhWallImage;
import com.bruce.geekway.model.KlhWallImageCriteria;


@Repository
public class KlhWallImageDaoImpl implements IKlhWallImageDao, InitializingBean {

     @Autowired
    private KlhWallImageMapper klhWallImageMapper;

    @Override
    public int save(KlhWallImage t) {
        return klhWallImageMapper.insert(t);
    }

    @Override
    public int updateById(KlhWallImage t) {
        return klhWallImageMapper.updateByPrimaryKeySelective(t);
    }

    @Override
    public int deleteById(Integer id) {
        return klhWallImageMapper.deleteByPrimaryKey(id);
    }

    @Override
    public KlhWallImage loadById(Integer id) {
        return klhWallImageMapper.selectByPrimaryKey(id);
    }

    @Override
    public List<KlhWallImage> queryAll() {
        return klhWallImageMapper.selectByExample(null);
    }

    @Override
    public List<KlhWallImage> fallLoadList(Integer tailId, int limit) {
        return null;
    }
    
    @Override
	public List<KlhWallImage> queryLatestImages(int pageSize) {
    	KlhWallImageCriteria criteria = new KlhWallImageCriteria();
    	criteria.createCriteria().andStatusEqualTo((short)1);
    	criteria.setOrderByClause(" id desc");
    	return klhWallImageMapper.selectByExample(criteria);
	}

	@Override
	public List<KlhWallImage> queryHotestImages(int pageSize) {
		KlhWallImageCriteria criteria = new KlhWallImageCriteria();
    	criteria.createCriteria().andStatusEqualTo((short)1);
    	criteria.setOrderByClause(" like_count desc");
    	return klhWallImageMapper.selectByExample(criteria);
	}
    

    @Override
    public void afterPropertiesSet() throws Exception {

    }
    
    public KlhWallImageMapper getKlhWallImageMapper() {
		return klhWallImageMapper;
	}

	public void setKlhWallImageMapper(KlhWallImageMapper klhWallImageMapper) {
		this.klhWallImageMapper = klhWallImageMapper;
	}


	

}
