package com.bruce.geekway.dao.ito;

import java.util.List;

import com.bruce.geekway.dao.IBaseDao;
import com.bruce.geekway.model.ItoProductSkuValue;
import com.bruce.geekway.model.ItoSkuPropValue;

public interface IItoProductSkuValueDao extends IBaseDao<ItoProductSkuValue, Integer> {

	public List<Integer> querySkuValueIdListByProductId(int productId);

	public List<ItoSkuPropValue> querySkuValueListByProductId(int productId);

	public int deleteSkuValuesByProductId(int productId);

	public int saveProductSkuValues(int productId, List<Integer> productSkuValueIdList);

}
