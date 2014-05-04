package com.bruce.geekway.service.ito;

import java.util.HashMap;

import com.bruce.geekway.model.ItoSkuProp;
import com.bruce.geekway.service.IBaseService;

public interface IItoSkuPropService extends IBaseService<ItoSkuProp, Integer>{

	public HashMap<Integer, ItoSkuProp> queryMap();
}