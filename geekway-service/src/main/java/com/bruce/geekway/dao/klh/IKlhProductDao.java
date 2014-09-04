package com.bruce.geekway.dao.klh;

import java.util.List;

import com.bruce.geekway.dao.IBaseDao;
import com.bruce.geekway.model.KlhProduct;

public interface IKlhProductDao extends IBaseDao<KlhProduct, Integer> {

	public List<KlhProduct> queryAvailableProducts();


}
