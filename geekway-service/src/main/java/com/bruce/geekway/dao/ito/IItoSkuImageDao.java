package com.bruce.geekway.dao.ito;

import java.util.List;

import com.bruce.geekway.dao.IBaseDao;
import com.bruce.geekway.model.ItoSkuImage;

public interface IItoSkuImageDao extends IBaseDao<ItoSkuImage, Integer> {
	
    public List<ItoSkuImage> queryAllBySkuId(int skuId);
    
    public int delete(int skuImageId, int productId, int skuId);
    
    @Deprecated
    public List<ItoSkuImage> queryAllByProductId(int productId);


//	public ItoSkuImage loadProductSku(int productId, int skuId); 

//    public List<ItoSkuImage> queryArticlesByModuleId(int moduleId, int limit);
//
//    public List<ItoSkuImage> queryArticlesOutModuleId(int moduleId);

    
}
