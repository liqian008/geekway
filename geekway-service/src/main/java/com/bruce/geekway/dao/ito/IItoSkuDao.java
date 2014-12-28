package com.bruce.geekway.dao.ito;

import java.util.List;

import com.bruce.geekway.dao.IBaseDao;
import com.bruce.geekway.model.ItoSku;
import com.bruce.geekway.model.ItoSkuCriteria;

public interface IItoSkuDao extends IBaseDao<ItoSku, Integer> {
	
    public List<ItoSku> queryAllByProductId(int productId);

	public int updateByCriteria(ItoSku t, ItoSkuCriteria criteria);

    
//	public ItoSku loadProductSku(int productId, int skuId); 

//    public List<ItoSku> queryArticlesByModuleId(int moduleId, int limit);
//
//    public List<ItoSku> queryArticlesOutModuleId(int moduleId);

    
}
