package com.bruce.geekway.dao.ito;

import java.util.List;

import com.bruce.geekway.dao.IBaseDao;
import com.bruce.geekway.model.ItoSku;

public interface IItoSkuDao extends IBaseDao<ItoSku, Integer> {
	
    public List<ItoSku> queryAllByProductId(int productId);

//	public ItoSku loadProductSku(int productId, int skuId); 

//    public List<ItoSku> queryArticlesByModuleId(int moduleId, int limit);
//
//    public List<ItoSku> queryArticlesOutModuleId(int moduleId);

    
}
