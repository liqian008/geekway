package com.bruce.geekway.dao.impl.ito;

import java.util.List;

import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.bruce.geekway.dao.ito.IItoSliderDao;
import com.bruce.geekway.dao.mapper.ItoSliderMapper;
import com.bruce.geekway.model.ItoSkuImageCriteria;
import com.bruce.geekway.model.ItoSlider;
import com.bruce.geekway.model.ItoSliderCriteria;


@Repository
public class ItoSliderDaoImpl implements IItoSliderDao, InitializingBean {

     @Autowired
    private ItoSliderMapper itoSliderMapper;

    @Override
    public int save(ItoSlider t) {
        return itoSliderMapper.insert(t);
    }

    @Override
    public int updateById(ItoSlider t) {
        return itoSliderMapper.updateByPrimaryKeySelective(t);
    }

    @Override
    public int deleteById(Integer id) {
        return itoSliderMapper.deleteByPrimaryKey(id);
    }

    @Override
    public ItoSlider loadById(Integer id) {
        return itoSliderMapper.selectByPrimaryKey(id);
    }

    @Override
    public List<ItoSlider> queryAll() {
        return itoSliderMapper.selectByExample(null);
    }

    @Override
    public List<ItoSlider> fallLoadList(Integer tailId, int limit) {
        return null;
    }
    
    @Override
   	public List<ItoSlider> querySortedList() {
       	ItoSliderCriteria criteria = new ItoSliderCriteria();
   		criteria.createCriteria();
   		criteria.setOrderByClause(" sort");
   		return itoSliderMapper.selectByExample(criteria);
   	}
    
    @Override
    public void afterPropertiesSet() throws Exception {

    }

    public ItoSliderMapper getItoSliderMapper() {
		return itoSliderMapper;
	}

	public void setItoSliderMapper(ItoSliderMapper itoSliderMapper) {
		this.itoSliderMapper = itoSliderMapper;
	}

	

}
