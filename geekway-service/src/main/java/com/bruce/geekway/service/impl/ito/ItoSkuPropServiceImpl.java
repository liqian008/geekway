package com.bruce.geekway.service.impl.ito;

import java.util.HashMap;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bruce.geekway.dao.ito.IItoSkuPropDao;
import com.bruce.geekway.model.ItoSkuProp;
import com.bruce.geekway.service.ito.IItoSkuPropService;

@Service
public class ItoSkuPropServiceImpl implements IItoSkuPropService{
	
	@Autowired
	private IItoSkuPropDao itoSkuPropDao;
	
	@Override
	public int save(ItoSkuProp t) {
		return itoSkuPropDao.save(t);
	}

	@Override
	public int updateById(ItoSkuProp t) {
		return itoSkuPropDao.updateById(t);
	}

	@Override
	public int deleteById(Integer id) {
		return itoSkuPropDao.deleteById(id);
	}

	@Override
	public ItoSkuProp loadById(Integer id) {
		return itoSkuPropDao.loadById(id);
	}

	@Override
	public List<ItoSkuProp> queryAll() {
		return itoSkuPropDao.queryAll();
	}
	
	@Override
	public HashMap<Integer, ItoSkuProp> queryMap() {
		HashMap<Integer, ItoSkuProp> skuPropHm = new HashMap<Integer, ItoSkuProp>();
		List<ItoSkuProp> skuPropList = queryAll();
		if(skuPropList!=null){
			for(ItoSkuProp skuProp: skuPropList){
				skuPropHm.put(skuProp.getId(), skuProp);
			}
			return skuPropHm;
		}
		return skuPropHm;
	}
	
	public IItoSkuPropDao getItoSkuPropDao() {
		return itoSkuPropDao;
	}

	public void setItoSkuPropDao(IItoSkuPropDao itoSkuPropDao) {
		this.itoSkuPropDao = itoSkuPropDao;
	}

	
}