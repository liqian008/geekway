package com.bruce.geekway.service.product;

import java.util.HashMap;

import com.bruce.foundation.service.IFoundationService;
import com.bruce.geekway.model.WxSkuProp;
import com.bruce.geekway.model.WxSkuPropCriteria;

public interface IWxSkuPropService extends IFoundationService<WxSkuProp, Integer, WxSkuPropCriteria>{

	public HashMap<Integer, WxSkuProp> queryMap();
}