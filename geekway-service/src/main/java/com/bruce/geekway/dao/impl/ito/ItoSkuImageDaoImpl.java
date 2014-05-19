package com.bruce.geekway.dao.impl.ito;

import java.util.List;

import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.bruce.geekway.dao.ito.IItoSkuImageDao;
import com.bruce.geekway.dao.mapper.ItoSkuImageMapper;
import com.bruce.geekway.model.ItoSkuImage;
import com.bruce.geekway.model.ItoSkuImageCriteria;


@Repository
public class ItoSkuImageDaoImpl implements IItoSkuImageDao, InitializingBean {

     @Autowired
    private ItoSkuImageMapper itoSkuImageMapper;

    @Override
    public int save(ItoSkuImage t) {
        return itoSkuImageMapper.insert(t);
    }

    @Override
    public int updateById(ItoSkuImage t) {
        return itoSkuImageMapper.updateByPrimaryKeySelective(t);
    }

    @Override
    public int deleteById(Integer id) {
        return itoSkuImageMapper.deleteByPrimaryKey(id);
    }

    @Override
    public ItoSkuImage loadById(Integer id) {
        return itoSkuImageMapper.selectByPrimaryKey(id);
    }

    @Override
    public List<ItoSkuImage> queryAll() {
        return itoSkuImageMapper.selectByExample(null);
    }

    @Override
    public List<ItoSkuImage> fallLoadList(Integer tailId, int limit) {
        return null;
    }
    @Override
	public List<ItoSkuImage> queryAllByProductId(int productId) {
    	ItoSkuImageCriteria criteria = new ItoSkuImageCriteria();
		criteria.createCriteria().andProductIdEqualTo(productId);
		return itoSkuImageMapper.selectByExample(criteria);
	}
    
    @Override
	public List<ItoSkuImage> queryAllBySkuId(int skuId) {
    	ItoSkuImageCriteria criteria = new ItoSkuImageCriteria();
		criteria.createCriteria().andSkuIdEqualTo(skuId);
		criteria.setOrderByClause(" sort");
		return itoSkuImageMapper.selectByExample(criteria);
	}
    

	@Override
	public int delete(int skuImageId, int productId, int skuId) {
		ItoSkuImageCriteria criteria = new ItoSkuImageCriteria();
		criteria.createCriteria().andIdEqualTo(skuImageId).andProductIdEqualTo(productId).andSkuIdEqualTo(skuId);
		return itoSkuImageMapper.deleteByExample(criteria);
	}
	
//    @Override
//	public ItoSkuImage loadProductSku(int productId, int skuId) { 
//    	ItoSkuImageCriteria criteria = new ItoSkuImageCriteria();
//		criteria.createCriteria().andIdEqualTo(skuId).andProductIdEqualTo(productId);
//		List<ItoSkuImage> skuList = itoSkuImageMapper.selectByExample(criteria);
//		if(skuList!=null&&skuList)
//	}
    

    @Override
    public void afterPropertiesSet() throws Exception {

    }

    public ItoSkuImageMapper getItoSkuImageMapper() {
		return itoSkuImageMapper;
	}

	public void setItoSkuImageMapper(ItoSkuImageMapper itoSkuImageMapper) {
		this.itoSkuImageMapper = itoSkuImageMapper;
	}

}
