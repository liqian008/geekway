package com.bruce.geekway.dao.impl.klh;

import java.util.List;

import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.bruce.geekway.dao.klh.IKlhProductOrderDao;
import com.bruce.geekway.dao.mapper.KlhProductOrderMapper;
import com.bruce.geekway.model.KlhProductOrder;
import com.bruce.geekway.model.KlhProductOrderCriteria;

@Repository
public class KlhProductOrderDaoImpl implements IKlhProductOrderDao, InitializingBean {

	@Autowired
	private KlhProductOrderMapper klhProductOrderMapper;

	@Override
	public int save(KlhProductOrder t) {
		return klhProductOrderMapper.insert(t);
	}

	@Override
	public int updateById(KlhProductOrder t) {
		return klhProductOrderMapper.updateByPrimaryKeySelective(t);
	}

	@Override
	public int deleteById(Integer id) {
		return klhProductOrderMapper.deleteByPrimaryKey(id);
	}

	@Override
	public KlhProductOrder loadById(Integer id) {
		return klhProductOrderMapper.selectByPrimaryKey(id);
	}

	@Override
	public List<KlhProductOrder> queryAll() {
		return klhProductOrderMapper.selectByExample(null);
	}

	@Override
	public List<KlhProductOrder> fallLoadList(Integer tailId, int limit) {
		return null;
	}


	@Override
	public void afterPropertiesSet() throws Exception {

	}

	public KlhProductOrderMapper getKlhProductOrderMapper() {
		return klhProductOrderMapper;
	}

	public void setKlhProductOrderMapper(KlhProductOrderMapper klhProductOrderMapper) {
		this.klhProductOrderMapper = klhProductOrderMapper;
	}

}
