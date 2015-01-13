package com.bruce.geekway.dao.ito;

import java.util.List;

import com.bruce.geekway.dao.IBaseDao;
import com.bruce.geekway.model.ItoProductSkuValue;
import com.bruce.geekway.model.ItoSkuPropValue;

public interface IItoProductSkuValueDao extends IBaseDao<ItoProductSkuValue, Integer> {
	
	public List<Integer> querySkuValueIdListByProductId(int productId);

	public List<ItoSkuPropValue> querySkuValueListByProductId(int productId);

	/**
	 * 删除该productId对应的关联（在新建前进行必要的清除操作）
	 * @param productId
	 * @return
	 */
	public int deleteSkuValuesByProductId(int productId);
	
	
	public int saveProductSkuValues(int productId, List<Integer> productSkuValueIdList);

	public List<ItoProductSkuValue> queryByProductId(int productId);
	
	/**
	 * 删除前检查skuPropValueId是否有关联的产品
	 * @param skuPropValueId
	 * @return
	 */
	public int queryCountBySkuPropValueId(int skuPropValueId);

	public int deleteBySkuPropValueIds(int productId, List<Integer> skuPropValueIdList); 
	
}
