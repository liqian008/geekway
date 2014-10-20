package com.bruce.geekway.service.product.impl;

import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;

import org.apache.commons.lang3.math.NumberUtils;
import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;
import org.springframework.stereotype.Service;

import com.bruce.geekway.model.wx.pay.WxDeliveryTemplate;
import com.bruce.geekway.model.wx.pay.WxDeliveryTemplate.CustomFee;
import com.bruce.geekway.model.wx.pay.WxDeliveryTemplate.NormalFee;
import com.bruce.geekway.model.wx.pay.WxDeliveryTemplate.TopFee;
import com.bruce.geekway.service.product.IWxDeliveryTemplateService;

@Service
public class WxDeliveryTemplateServiceImpl implements IWxDeliveryTemplateService{
	
	private List<WxDeliveryTemplate> cachedDeliveryTemplates;
	
	@Override
	@PostConstruct
	public void init(){
		cachedDeliveryTemplates = initFromTemplateXml();
	}
	
	@Override
	public List<WxDeliveryTemplate> queryAllDeliveryTemplates() {
		return cachedDeliveryTemplates;
	}
	
	/**
	 * 从xml中加载邮费模板
	 * @return
	 * @throws DocumentException 
	 */
	@SuppressWarnings("unchecked")
	private List<WxDeliveryTemplate> initFromTemplateXml() {
		SAXReader reader = new SAXReader();           
	    try {
	    	InputStream is = WxDeliveryTemplateServiceImpl.class.getResourceAsStream("/deliveryTemplate.xml");
	    	Document document = reader.read(is);
	    	is.close();  
			Element root = document.getRootElement();
			if(root!=null){
				List<WxDeliveryTemplate> deliveryTemplateList = new ArrayList<WxDeliveryTemplate>();
				List<Element> templateList = root.elements("delivery_template");
				if(templateList!=null&&templateList.size()>0){
					for(Element templateElement: templateList){
						Element eleId = templateElement.element("Id");
						Element eleName = templateElement.element("Name");
						Element eleAssumer = templateElement.element("Assumer");
						Element eleValuation = templateElement.element("Valuation");
						//转int类型
						int templateId = NumberUtils.toInt(eleId.getText(), 0);
						String templateName = eleName.getText();
						int templateAssumer = NumberUtils.toInt(eleAssumer.getText(), 1);
						int templateValuation = NumberUtils.toInt(eleValuation.getText(), 0);
						
						List<Element> elementTopFeeList = templateElement.elements("TopFee");
						List<TopFee> topFeeList = new ArrayList<TopFee>();
						if(elementTopFeeList!=null&&elementTopFeeList.size()>0){
							for(Element topFeeElement: elementTopFeeList){
								Element eleType = topFeeElement.element("Type");
								int deliverType = NumberUtils.toInt(eleType.getText(), 0);
								Element eleDesc = topFeeElement.element("Name");
								String deliverDesc = eleDesc.getTextTrim();
								
								Element eleNormal = topFeeElement.element("Normal");
								String normalStartStandardsText = eleNormal.attributeValue("StartStandards");
								String normalStartFeesText = eleNormal.attributeValue("StartFees");
								String normalAddStandardsText = eleNormal.attributeValue("AddStandards");
								String normalAddFeesText = eleNormal.attributeValue("AddFees");
								String normalFreeStartFeesText = eleNormal.attributeValue("FreeStartFees");
								
								//转double类型
								int normalStartStandards = NumberUtils.toInt(normalStartStandardsText, 1);
								double normalStartFees = NumberUtils.toDouble(normalStartFeesText, 0);
								int normalAddStandards = NumberUtils.toInt(normalAddStandardsText, 1);
								double normalAddFees = NumberUtils.toDouble(normalAddFeesText, 0);
								double normalFreeStartFees = NumberUtils.toDouble(normalFreeStartFeesText, Double.MAX_VALUE);
								
								//构造NormalFee对象
								NormalFee normalFee = new NormalFee(normalStartStandards, normalStartFees, normalAddStandards, normalAddFees, normalFreeStartFees);
								
								List<Element> elementCustomList = topFeeElement.elements("Custom");
								List<CustomFee> customFeeList = new ArrayList<CustomFee>();
								if(elementCustomList!=null&&elementCustomList.size()>0){
									for(Element customElement: elementCustomList){
										String startStandardsText = customElement.attributeValue("StartStandards");
										String startFeesText = customElement.attributeValue("StartFees");
										String addStandardsText = customElement.attributeValue("AddStandards");
										String addFeesText = customElement.attributeValue("AddFees");
										String freeStartFeesText = customElement.attributeValue("FreeStartFees");
										String destCountry = customElement.attributeValue("DestCountry");
										String destProvince = customElement.attributeValue("DestProvince");
										String destCity = customElement.attributeValue("DestCity");
										
										//转int类型
										int startStandards = NumberUtils.toInt(startStandardsText, 1);
										double startFees = NumberUtils.toDouble(startFeesText, 0);
										int addStandards = NumberUtils.toInt(addStandardsText, 1);
										double addFees = NumberUtils.toDouble(addFeesText, 0);
										double freeStartFees = NumberUtils.toDouble(freeStartFeesText, Double.MAX_VALUE);
										//构造CustomFee对象
										CustomFee customFee = new CustomFee(startStandards, startFees, addStandards, addFees, freeStartFees, destCountry, destProvince, destCity);
										customFeeList.add(customFee);
									}
								}
								TopFee topfee = new TopFee(deliverType, deliverDesc, normalFee, customFeeList);
								topFeeList.add(topfee);
							}
						}
						WxDeliveryTemplate deliverTemplate = new WxDeliveryTemplate(templateId, templateName, templateAssumer, templateValuation, topFeeList);
						deliveryTemplateList.add(deliverTemplate);
					}
					return deliveryTemplateList;
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}
	
	
	@Override
	public WxDeliveryTemplate loadDeliveryTemplate(int templateId) {
//		queryAllDeliveryTemplates();//加载数据到缓存中
		if(cachedDeliveryTemplates!=null){
			for(WxDeliveryTemplate template: cachedDeliveryTemplates){
				if(template.getId()==templateId){
					return template;
				}
			}
		}
		return null;
	}
	
	/**
	 * 计算相应的邮费
	 */
	@Override
	public double calcDeliveryFee(int templateId, int deliveryType, int amount, String country, String province, String city) {
		WxDeliveryTemplate deliverTemplate = loadDeliveryTemplate(templateId);
		if(deliverTemplate!=null){//有对应的邮费模板
			List<TopFee> topFeeList = deliverTemplate.getTopFeeList();
			if(topFeeList!=null&&topFeeList.size()>0){
				double deliveryFee = 0;
				for(TopFee topFee: topFeeList){
					if(deliveryType!=0 && topFee.getDeliveryType()==deliveryType){//确定快递类型
						//计算费用金额
						deliveryFee = calcTopFeeDelivery(amount, topFee, province, city);
						return deliveryFee;
					}
				}
				
				//没有找到匹配的匹配的TopFee邮费设置，则使用默认的TopFee模板（type=0）
				if(deliveryFee<=0){
					for(TopFee topFee: topFeeList){
						if(topFee.getDeliveryType()==0){//定位默认的TopFee模板
							//计算费用金额
							deliveryFee = calcTopFeeDelivery(amount, topFee, province, city);
							return deliveryFee;
						}
					}
				}
			}
		}
		return 0;
	}

	private double calcTopFeeDelivery(int amount, TopFee topFee, String province, String city) {
		//计算费用金额
		double deliveryFee =0;
		List<CustomFee> customFeeList = topFee.getCustomFeeList();
		if(customFeeList!=null&&customFeeList.size()>0){
			for(CustomFee customFee: customFeeList){
				if(customFee.getDestProvince().equals(province)&&customFee.getDestCity().equals(city)){
					//暂不检查国家 if(customFee.getDestCountry().equals(country))
					deliveryFee = customFee.getStartFees();
					if(amount>customFee.getStartStandards()){//大于起始数量
						int times = (int)Math.ceil((double)(amount-customFee.getStartStandards())/customFee.getAddStandards());
						deliveryFee = deliveryFee + (customFee.getAddFees()*times);
					}
					return deliveryFee;
				}
			}
		}
		//没有找到匹配的自定义邮费设置，则使用default的邮费
		if(deliveryFee<=0){
			NormalFee normalFee = topFee.getNormalFee();
			if(normalFee!=null){
				deliveryFee = normalFee.getStartFees();
				if(amount>normalFee.getStartStandards()){//大于起始数量
					int times = (int)Math.ceil((double)(amount-normalFee.getStartStandards())/normalFee.getAddStandards());
					deliveryFee = deliveryFee + (normalFee.getAddFees()*times);
				}
				return deliveryFee;
			}
		}
		return 0;
	}
	
	
	public static void main(String[] args) {
//		WxDeliveryTemplateServiceImpl instance = new WxDeliveryTemplateServiceImpl();
//		instance.cachedDeliveryTemplates = instance.initFromTemplateXml();
//		System.out.println(instance.calcDeliveryFee(1, 100003, 6,"","",""));
//		
//		WxDeliveryTemplate template = instance.loadDeliveryTemplate(1);
//		System.out.println(template);
//		float a = 10.7f;
//		float b = 0.17f;
//		System.out.println(a+b);
//		
//		float x = 10.3f;
//		float y = 4.2f;
//		System.out.println(x-y);
	}

}
