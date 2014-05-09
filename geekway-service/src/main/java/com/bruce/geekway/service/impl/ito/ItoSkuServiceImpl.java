package com.bruce.geekway.service.impl.ito;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bruce.geekway.dao.ito.IItoSkuDao;
import com.bruce.geekway.model.ItoSku;
import com.bruce.geekway.service.ito.IItoSkuService;

@Service
public class ItoSkuServiceImpl implements IItoSkuService{
	
	@Autowired
	private IItoSkuDao itoSkuDao;
	
	@Override
	public int save(ItoSku t) {
		return itoSkuDao.save(t);
	}

	@Override
	public int updateById(ItoSku t) {
		return itoSkuDao.updateById(t);
	}

	@Override
	public int deleteById(Integer id) {
		return itoSkuDao.deleteById(id);
	}

	@Override
	public ItoSku loadById(Integer id) {
		return itoSkuDao.loadById(id);
	}

	@Override
	public List<ItoSku> queryAll() {
		return itoSkuDao.queryAll();
	}
	

	@Override
	public List<ItoSku> queryAllByProductId(int productId) {
		return itoSkuDao.queryAllByProductId(productId); 
	}
	
	@Override
	public ItoSku loadProductSku(int productId, int skuId) {
		ItoSku sku = loadById(skuId);
		if(sku!=null&&sku.getProductId()!=null&&sku.getProductId()==productId){
			return sku;
		}
		return null;
	}

	public IItoSkuDao getItoSkuDao() {
		return itoSkuDao;
	}

	public void setItoSkuDao(IItoSkuDao itoSkuDao) {
		this.itoSkuDao = itoSkuDao;
	}
	
}