package com.bruce.geekway.service.impl.ito;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bruce.geekway.dao.ito.IItoSkuImageDao;
import com.bruce.geekway.model.ItoSkuImage;
import com.bruce.geekway.service.ito.IItoSkuImageService;

@Service
public class ItoSkuImageServiceImpl implements IItoSkuImageService{
	
	@Autowired
	private IItoSkuImageDao itoSkuImageDao;
	
	@Override
	public int save(ItoSkuImage t) {
		return itoSkuImageDao.save(t);
	}

	@Override
	public int updateById(ItoSkuImage t) {
		return itoSkuImageDao.updateById(t);
	}

	@Override
	public int deleteById(Integer id) {
		return itoSkuImageDao.deleteById(id);
	}

	@Override
	public ItoSkuImage loadById(Integer id) {
		return itoSkuImageDao.loadById(id);
	}

	@Override
	public List<ItoSkuImage> queryAll() {
		return itoSkuImageDao.queryAll();
	}
	
	@Override
	public List<ItoSkuImage> queryAllBySkuId(int skuId) {
		return itoSkuImageDao.queryAllBySkuId(skuId);
	}
	
	@Override
	public int delete(int skuImageId, int productId, int skuId) {
		return itoSkuImageDao.delete(skuImageId, productId, skuId); 
	}

	public IItoSkuImageDao getItoSkuImageDao() {
		return itoSkuImageDao;
	}

	public void setItoSkuImageDao(IItoSkuImageDao itoSkuImageDao) {
		this.itoSkuImageDao = itoSkuImageDao;
	}

	
}