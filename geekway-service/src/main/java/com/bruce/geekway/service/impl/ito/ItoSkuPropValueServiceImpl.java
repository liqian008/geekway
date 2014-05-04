package com.bruce.geekway.service.impl.ito;

import java.util.HashMap;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bruce.geekway.dao.ito.IItoProductSkuValueDao;
import com.bruce.geekway.dao.ito.IItoSkuPropValueDao;
import com.bruce.geekway.model.ItoSkuProp;
import com.bruce.geekway.model.ItoSkuPropValue;
import com.bruce.geekway.service.ito.IItoSkuPropValueService;

@Service
public class ItoSkuPropValueServiceImpl implements IItoSkuPropValueService{
	
	@Autowired
	private IItoSkuPropValueDao itoSkuPropValueDao;
	@Autowired
	private IItoProductSkuValueDao toProductSkuValueDao;
	
	@Override
	public int save(ItoSkuPropValue t) {
		return itoSkuPropValueDao.save(t);
	}

	@Override
	public int updateById(ItoSkuPropValue t) {
		return itoSkuPropValueDao.updateById(t);
	}

	@Override
	public int deleteById(Integer id) {
		return itoSkuPropValueDao.deleteById(id);
	}

	@Override
	public ItoSkuPropValue loadById(Integer id) {
		return itoSkuPropValueDao.loadById(id);
	}
	

	@Override
	public List<ItoSkuPropValue> queryAll() {
		return itoSkuPropValueDao.queryAll();
	}
	
	
	@Override
	public HashMap<Integer, ItoSkuPropValue> queryMap() {
		HashMap<Integer, ItoSkuPropValue> skuPropValueHm = new HashMap<Integer, ItoSkuPropValue>();
		List<ItoSkuPropValue> skuPropValueList = queryAll();
		if(skuPropValueList!=null){
			for(ItoSkuPropValue skuPropValue: skuPropValueList){
				skuPropValueHm.put(skuPropValue.getId(), skuPropValue);
			}
			return skuPropValueHm;
		}
		return skuPropValueHm;
	}
	
	@Override
	public List<Integer> querySkuValueIdListByProductId(int productId) {
		return toProductSkuValueDao.querySkuValueIdListByProductId(productId);
	}
	
	@Override
	public List<ItoSkuPropValue> querySkuValueListByProductId(int productId) {
		return toProductSkuValueDao.querySkuValueListByProductId(productId);
	}
	
	@Override
	public int deleteSkuValuesByProductId(int productId) {
		return toProductSkuValueDao.deleteSkuValuesByProductId(productId);
	}

	@Override
	public int saveProductSkuValues(int productId, List<Integer> productSkuValueIdList) {
		return toProductSkuValueDao.saveProductSkuValues(productId, productSkuValueIdList);
	}
	
	public IItoSkuPropValueDao getItoSkuPropValueDao() {
		return itoSkuPropValueDao;
	}

	public void setItoSkuPropValueDao(IItoSkuPropValueDao itoSkuPropValueDao) {
		this.itoSkuPropValueDao = itoSkuPropValueDao;
	}

	

}