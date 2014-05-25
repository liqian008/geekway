package com.bruce.geekway.service.impl.ito;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bruce.geekway.dao.ito.IItoProductSkuValueDao;
import com.bruce.geekway.model.ItoProductSkuValue;
import com.bruce.geekway.service.ito.IItoProductSkuValueService;

/**
 * 处理product与skuPropValue关联关系
 * @author liqian
 *
 */
@Service
public class ItoProductSkuValueServiceImpl implements IItoProductSkuValueService{
	
	@Autowired
	private IItoProductSkuValueDao itoProductSkuValueDao;
	
	@Override
	public int save(ItoProductSkuValue t) {
		return itoProductSkuValueDao.save(t);
	}

	@Override
	public int updateById(ItoProductSkuValue t) {
		return itoProductSkuValueDao.updateById(t);
	}

	@Override
	public int deleteById(Integer id) {
		return itoProductSkuValueDao.deleteById(id);
	}

	@Override
	public ItoProductSkuValue loadById(Integer id) {
		return itoProductSkuValueDao.loadById(id);
	}

	@Override
	public List<ItoProductSkuValue> queryAll() {
		return itoProductSkuValueDao.queryAll();
	}
	
	@Override
	public int queryCountBySkuPropValueId(int skuPropValueId) {
		return itoProductSkuValueDao.queryCountBySkuPropValueId(skuPropValueId);
	}

	public IItoProductSkuValueDao getItoProductSkuValueDao() {
		return itoProductSkuValueDao;
	}

	public void setItoProductSkuValueDao(IItoProductSkuValueDao itoProductSkuValueDao) {
		this.itoProductSkuValueDao = itoProductSkuValueDao;
	}

	
	
}