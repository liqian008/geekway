package com.bruce.geekway.dao.impl.klh;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.bruce.geekway.dao.klh.IKlhDailySignDao;
import com.bruce.geekway.dao.mapper.KlhDailySignMapper;
import com.bruce.geekway.model.KlhDailySign;
import com.bruce.geekway.model.KlhDailySignCriteria;


@Repository
public class KlhDailySignDaoImpl implements IKlhDailySignDao, InitializingBean {

     @Autowired
    private KlhDailySignMapper klhDailySignMapper;

    @Override
    public int save(KlhDailySign t) {
        return klhDailySignMapper.insert(t);
    }

    @Override
    public int updateById(KlhDailySign t) {
        return klhDailySignMapper.updateByPrimaryKeySelective(t);
    }

    @Override
    public int deleteById(Integer id) {
        return klhDailySignMapper.deleteByPrimaryKey(id);
    }

    @Override
    public KlhDailySign loadById(Integer id) {
        return klhDailySignMapper.selectByPrimaryKey(id);
    }

    @Override
    public List<KlhDailySign> queryAll() {
        return klhDailySignMapper.selectByExample(null);
    }

    @Override
    public List<KlhDailySign> fallLoadList(Integer tailId, int limit) {
        return null;
    }
    
    /**
	 * 判断今日是否签到过
	 * @param userOpenId
	 * @param date
	 * @return
	 */
	public boolean hasSigned(String userOpenId, Date date){
		KlhDailySignCriteria criteria = new KlhDailySignCriteria();
		criteria.createCriteria().andUserOpenIdEqualTo(userOpenId).andSignDateEqualTo(date);
		int count = klhDailySignMapper.countByExample(criteria);
		return count>0?true:false;
	}
	
	
    
    
    @Override
    public void afterPropertiesSet() throws Exception {

    }

    public KlhDailySignMapper getKlhDailySignMapper() {
		return klhDailySignMapper;
	}

	public void setKlhDailySignMapper(KlhDailySignMapper klhDailySignMapper) {
		this.klhDailySignMapper = klhDailySignMapper;
	}

	

}
