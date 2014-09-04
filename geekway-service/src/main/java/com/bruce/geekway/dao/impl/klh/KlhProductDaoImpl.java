package com.bruce.geekway.dao.impl.klh;

import java.util.List;

import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.bruce.geekway.dao.klh.IKlhProductDao;
import com.bruce.geekway.dao.mapper.KlhProductMapper;
import com.bruce.geekway.model.KlhProduct;
import com.bruce.geekway.model.KlhProductCriteria;

@Repository
public class KlhProductDaoImpl implements IKlhProductDao, InitializingBean {

	@Autowired
	private KlhProductMapper klhProductMapper;

	@Override
	public int save(KlhProduct t) {
		return klhProductMapper.insert(t);
	}

	@Override
	public int updateById(KlhProduct t) {
		return klhProductMapper.updateByPrimaryKeySelective(t);
	}

	@Override
	public int deleteById(Integer id) {
		return klhProductMapper.deleteByPrimaryKey(id);
	}

	@Override
	public KlhProduct loadById(Integer id) {
		return klhProductMapper.selectByPrimaryKey(id);
	}

	@Override
	public List<KlhProduct> queryAll() {
		return klhProductMapper.selectByExample(null);
	}

	@Override
	public List<KlhProduct> fallLoadList(Integer tailId, int limit) {
		return null;
	}

	@Override
	public List<KlhProduct> queryAvailableProducts() {
		KlhProductCriteria criteria = new KlhProductCriteria();
		criteria.createCriteria().andStatusEqualTo((short) 1);
		return klhProductMapper.selectByExample(criteria);
	}

	@Override
	public void afterPropertiesSet() throws Exception {

	}

	public KlhProductMapper getKlhProductMapper() {
		return klhProductMapper;
	}

	public void setKlhProductMapper(KlhProductMapper klhProductMapper) {
		this.klhProductMapper = klhProductMapper;
	}

}
