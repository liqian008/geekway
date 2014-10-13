package com.bruce.geekway.service.product.impl;

import java.util.List;

import org.springframework.stereotype.Service;

import com.bruce.geekway.service.product.IWxDeliveryTemplateService;

@Service
public class WxDeliveryTemplateServiceImpl implements IWxDeliveryTemplateService{
	
	
	@Override
	public List queryAllDeliveryTemplates() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String loadDeliveryTemplate(int templateId) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public double calcDeliveryFee(int templateId, String country,
			String privince, String city) {
		return 0;
	}

}