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
	    	InputStream is = WxDeliveryTemplateServiceImpl.class.getResourceAsStream("/deliverTemplate.xml");
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
								//转int类型
								int normalStartStandards = NumberUtils.toInt(normalStartStandardsText, 1);
								int normalStartFees = NumberUtils.toInt(normalStartFeesText, 0);
								int normalAddStandards = NumberUtils.toInt(normalAddStandardsText, 1);
								int normalAddFees = NumberUtils.toInt(normalAddFeesText, 0);
								//构造NormalFee对象
								NormalFee normalFee = new NormalFee(normalStartStandards, normalStartFees, normalAddStandards, normalAddFees);
								
								List<Element> elementCustomList = topFeeElement.elements("Custom");
								List<CustomFee> customFeeList = new ArrayList<CustomFee>();
								if(elementCustomList!=null&&elementCustomList.size()>0){
									for(Element customElement: elementCustomList){
										String startStandardsText = customElement.attributeValue("StartStandards");
										String startFeesText = customElement.attributeValue("StartFees");
										String addStandardsText = customElement.attributeValue("AddStandards");
										String addFeesText = customElement.attributeValue("AddFees");
										String destCountry = customElement.attributeValue("DestCountry");
										String destProvince = customElement.attributeValue("DestProvince");
										String destCity = customElement.attributeValue("DestCity");
										
										//转int类型
										int startStandards = NumberUtils.toInt(startStandardsText, 1);
										int startFees = NumberUtils.toInt(startFeesText, 0);
										int addStandards = NumberUtils.toInt(addStandardsText, 1);
										int addFees = NumberUtils.toInt(addFeesText, 0);
										//构造CustomFee对象
										CustomFee customFee = new CustomFee(startStandards, startFees, addStandards, addFees, destCountry, destProvince, destCity);
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
	 * 计算邮费
	 */
	@Override
	public int calcDeliveryFee(int templateId, int deliveryType, String country, String privince, String city) {
		WxDeliveryTemplate deliverTemplate = loadDeliveryTemplate(templateId);
		if(deliverTemplate!=null){//有对应的邮费模板
			List<TopFee> topFeeList = deliverTemplate.getTopFeeList();
			if(topFeeList!=null&&topFeeList.size()>0){
				for(TopFee topFee: topFeeList){
					if(topFee.getDeliveryType()==deliveryType){//确定快递类型
						//计算费用金额
						int deliverFee =0;
						List<CustomFee> customFeeList = topFee.getCustomFeeList();
						if(customFeeList!=null&&customFeeList.size()>0){
							for(CustomFee customFee: customFeeList){
								if(customFee.getDestCountry().equals(country)&&customFee.getDestProvince().equals(privince)&&customFee.getDestCity().equals(city)){
									deliverFee = customFee.getStartFees();
								}
							}
						}
						//没有找到匹配的自定义邮费设置，则使用default的邮费
						if(deliverFee<=0){
							NormalFee normalFee = topFee.getNormalFee();
							if(normalFee!=null){
								deliverFee = normalFee.getStartFees();
							}
						}
					}
				}
			}
		}
		return 0;
	}
	
	
	public static void main(String[] args) {
		WxDeliveryTemplateServiceImpl instance = new WxDeliveryTemplateServiceImpl();
		System.out.println(instance.queryAllDeliveryTemplates());
		
		WxDeliveryTemplate template = instance.loadDeliveryTemplate(1);
		System.out.println(template);
	}

}