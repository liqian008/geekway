package com.bruce.geekway.service.klh;

import java.util.List;

import com.bruce.foundation.service.IFoundationService;
import com.bruce.geekway.model.KlhProduct;
import com.bruce.geekway.model.KlhProductCriteria;

public interface IKlhProductService extends IFoundationService<KlhProduct, Integer, KlhProductCriteria> {

	
	public List<KlhProduct> queryAvailableProducts();
	
}