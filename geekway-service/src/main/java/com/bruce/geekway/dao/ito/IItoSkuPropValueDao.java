package com.bruce.geekway.dao.ito;

import java.util.List;

import com.bruce.geekway.dao.IBaseDao;
import com.bruce.geekway.model.ItoSkuPropValue;

public interface IItoSkuPropValueDao extends IBaseDao<ItoSkuPropValue, Integer> {

	// public List<ItoSku> queryArticlesByModuleId(int moduleId);
	//
	// public List<ItoSku> queryArticlesByModuleId(int moduleId, int limit);
	//
	// public List<ItoSku> queryArticlesOutModuleId(int moduleId);

	// public List<ItoSkuPropValue> queryCombiledSkuPropValueListByProductId(int
	// productId);

	public List<ItoSkuPropValue> querySortedSkuPropValues();

}
