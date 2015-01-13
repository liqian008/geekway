package com.bruce.geekway.service.ito;

import java.util.HashMap;
import java.util.List;

import com.bruce.foundation.service.IFoundationService;
import com.bruce.geekway.model.ItoSkuProp;
import com.bruce.geekway.model.ItoSkuPropCriteria;

public interface IItoSkuPropService extends IFoundationService<ItoSkuProp, Integer, ItoSkuPropCriteria>{

	public HashMap<Integer, ItoSkuProp> queryMap();

}