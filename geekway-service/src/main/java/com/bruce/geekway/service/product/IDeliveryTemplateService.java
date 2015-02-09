package com.bruce.geekway.service.product;

import java.util.List;

import com.bruce.geekway.model.wx.pay.WxDeliveryTemplate;


/**
 * 运费模板service
 * @author liqian
 *
 */
public interface IDeliveryTemplateService{
	
	/**
	 * init-method
	 */
	public void init();
	
	/**
	 * 列出所有运费模板
	 * @return
	 */
	public List<WxDeliveryTemplate> queryAllDeliveryTemplates();
	
	/**
	 * 加载指定的运费模板
	 * @param templateId
	 * @return
	 */
	public WxDeliveryTemplate loadDeliveryTemplate(int templateId);
	
	/**
	 * 计算运费
	 * @param templateId
	 * @param deliveryType
	 * @param country
	 * @param privince
	 * @param city
	 * @return
	 */
	public int calcDeliveryFee(int deliveryType, double totalProductFee, int totalAmount, String country, String province, String city);

	
	
	
}
