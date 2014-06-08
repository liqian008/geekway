package com.bruce.geekway.dao.impl.ito;

import java.util.List;

import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.bruce.geekway.dao.ito.IItoProductBgDao;
import com.bruce.geekway.dao.mapper.ItoProductBgMapper;
import com.bruce.geekway.model.ItoProductBg;


@Repository
public class ItoProductBgDaoImpl implements IItoProductBgDao, InitializingBean {

     @Autowired
    private ItoProductBgMapper itoProductBgMapper;

    @Override
    public int save(ItoProductBg t) {
        return itoProductBgMapper.insert(t);
    }

    @Override
    public int updateById(ItoProductBg t) {
        return itoProductBgMapper.updateByPrimaryKeySelective(t);
    }

    @Override
    public int deleteById(Integer id) {
        return itoProductBgMapper.deleteByPrimaryKey(id);
    }

    @Override
    public ItoProductBg loadById(Integer id) {
        return itoProductBgMapper.selectByPrimaryKey(id);
    }

    @Override
    public List<ItoProductBg> queryAll() {
        return itoProductBgMapper.selectByExample(null);
    }

    @Override
    public List<ItoProductBg> fallLoadList(Integer tailId, int limit) {
        return null;
    }
    
    @Override
    public void afterPropertiesSet() throws Exception {

    }

    public ItoProductBgMapper getItoProductBgMapper() {
		return itoProductBgMapper;
	}

	public void setItoProductBgMapper(ItoProductBgMapper itoProductBgMapper) {
		this.itoProductBgMapper = itoProductBgMapper;
	}

	

}
