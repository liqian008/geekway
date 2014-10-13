package com.bruce.geekway.service.product;

import java.util.List;


/**
 * 运费模板service
 * @author liqian
 *
 */
public interface IWxDeliveryTemplateService{

	/**
	 * 列出所有运费模板
	 * @return
	 */
	public List queryAllDeliveryTemplates();
	
	/**
	 * 加载指定的运费模板
	 * @param templateId
	 * @return
	 */
	public String loadDeliveryTemplate(int templateId);
	
	/**
	 * 计算运费
	 * @param templateId
	 * @param country
	 * @param privince
	 * @param city
	 * @return
	 */
	public double calcDeliveryFee(int templateId, String country, String privince, String city);
	
	
}