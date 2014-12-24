package com.bruce.geekway.service.impl.ito;

import java.util.HashMap;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bruce.geekway.dao.ito.IItoProductSkuValueDao;
import com.bruce.geekway.dao.ito.IItoSkuPropValueDao;
import com.bruce.geekway.model.ItoSkuPropValue;
import com.bruce.geekway.model.ItoSkuPropValueCriteria;
import com.bruce.geekway.service.ito.IItoSkuPropValueService;

@Service
public class ItoSkuPropValueServiceImpl implements IItoSkuPropValueService{
	
	@Autowired
	private IItoSkuPropValueDao itoSkuPropValueDao;
	@Autowired
	private IItoProductSkuValueDao itoProductSkuValueDao;
	
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
	
	public List<ItoSkuPropValue> querySortedSkuPropValues(){
		return itoSkuPropValueDao.querySortedSkuPropValues();
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
		return itoProductSkuValueDao.querySkuValueIdListByProductId(productId);
	}
	
	@Override
	public List<ItoSkuPropValue> querySkuValueListByProductId(int productId) {
		return itoProductSkuValueDao.querySkuValueListByProductId(productId);
	}
	
	@Override
	public int deleteSkuValuesByProductId(int productId) {
		return itoProductSkuValueDao.deleteSkuValuesByProductId(productId);
	}

	@Override
	public int saveProductSkuValues(int productId, List<Integer> productSkuValueIdList) {
		return itoProductSkuValueDao.saveProductSkuValues(productId, productSkuValueIdList);
	}
	
//	@Override
//	public List<ItoSkuPropValue> queryCombiledSkuPropValueListByProductId(int productId){
//		return itoSkuPropValueDao.queryCombiledSkuPropValueListByProductId(productId);
//	}
	
	public IItoSkuPropValueDao getItoSkuPropValueDao() {
		return itoSkuPropValueDao;
	}

	public void setItoSkuPropValueDao(IItoSkuPropValueDao itoSkuPropValueDao) {
		this.itoSkuPropValueDao = itoSkuPropValueDao;
	}

	public IItoProductSkuValueDao getItoProductSkuValueDao() {
		return itoProductSkuValueDao;
	}

	public void setItoProductSkuValueDao(IItoProductSkuValueDao itoProductSkuValueDao) {
		this.itoProductSkuValueDao = itoProductSkuValueDao;
	}

	@Override
	public int updateByCriteria(ItoSkuPropValue t,
			ItoSkuPropValueCriteria criteria) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int deleteByCriteria(ItoSkuPropValueCriteria criteria) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public List<ItoSkuPropValue> queryAll(String orderByClause) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<ItoSkuPropValue> queryByCriteria(
			ItoSkuPropValueCriteria criteria) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public int countByCriteria(ItoSkuPropValueCriteria criteria) {
		// TODO Auto-generated method stub
		return 0;
	}

}