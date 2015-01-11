package com.bruce.geekway.service.product;

import java.util.HashMap;

import com.bruce.foundation.service.IFoundationService;
import com.bruce.geekway.model.SkuProp;
import com.bruce.geekway.model.SkuPropCriteria;

public interface ISkuPropService extends IFoundationService<SkuProp, Integer, SkuPropCriteria>{

	public HashMap<Integer, SkuProp> queryMap();
}