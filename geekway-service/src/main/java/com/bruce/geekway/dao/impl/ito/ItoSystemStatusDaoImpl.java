package com.bruce.geekway.dao.impl.ito;

import java.util.List;

import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.bruce.geekway.dao.ito.IItoSystemStatusDao;
import com.bruce.geekway.dao.mapper.ItoSystemStatusMapper;
import com.bruce.geekway.model.ItoSystemStatus;


@Repository
public class ItoSystemStatusDaoImpl implements IItoSystemStatusDao, InitializingBean {

     @Autowired
    private ItoSystemStatusMapper itoSystemStatusMapper;

    @Override
    public int save(ItoSystemStatus t) {
        return itoSystemStatusMapper.insert(t);
    }

    @Override
    public int updateById(ItoSystemStatus t) {
        return itoSystemStatusMapper.updateByPrimaryKeySelective(t);
    }

    @Override
    public int deleteById(Integer id) {
        return itoSystemStatusMapper.deleteByPrimaryKey(id);
    }

    @Override
    public ItoSystemStatus loadById(Integer id) {
        return itoSystemStatusMapper.selectByPrimaryKey(id);
    }

    @Override
    public List<ItoSystemStatus> queryAll() {
        return itoSystemStatusMapper.selectByExample(null);
    }

    @Override
    public List<ItoSystemStatus> fallLoadList(Integer tailId, int limit) {
        return null;
    }
    
    
    @Override
    public void afterPropertiesSet() throws Exception {

    }

    public ItoSystemStatusMapper getItoSystemStatusMapper() {
		return itoSystemStatusMapper;
	}

	public void setItoSystemStatusMapper(ItoSystemStatusMapper itoSystemStatusMapper) {
		this.itoSystemStatusMapper = itoSystemStatusMapper;
	}

	

}
