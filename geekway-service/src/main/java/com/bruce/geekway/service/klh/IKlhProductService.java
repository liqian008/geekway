package com.bruce.geekway.service.klh;

import java.util.List;

import com.bruce.geekway.model.KlhProduct;
import com.bruce.geekway.service.IBaseService;

public interface IKlhProductService extends IBaseService<KlhProduct, Integer> {

	
	public List<KlhProduct> queryAvailableProducts();
	
}