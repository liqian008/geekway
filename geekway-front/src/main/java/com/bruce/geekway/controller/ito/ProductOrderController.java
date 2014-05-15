package com.bruce.geekway.controller.ito;


import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.bruce.geekway.model.ItoProductOrder;
import com.bruce.geekway.model.ItoSku;
import com.bruce.geekway.model.exception.ErrorCode;
import com.bruce.geekway.model.exception.GeekwayException;
import com.bruce.geekway.service.ito.IItoProductOrderService;
import com.bruce.geekway.service.ito.IItoProductService;
import com.bruce.geekway.service.ito.IItoSkuService;
import com.bruce.geekway.utils.JsonResultBuilderUtil;
import com.bruce.geekway.utils.JsonUtil;
import com.bruce.geekway.utils.JsonViewBuilderUtil;

/**
 * Handles requests for the application home page.
 */
@Controller
@RequestMapping(value={"api"})
public class ProductOrderController {
	
	@Autowired
	private IItoProductService itoProductService;
	@Autowired
	private IItoProductOrderService itoProductOrderService;
	@Autowired
	private IItoSkuService itoSkuService;
	
//	/**
//	 * 产品详情
//	 * @param model
//	 * @return
//	 */
//	@RequestMapping(value = "/testPostOrder.json")
//	public ModelAndView testPostOrder() {
//		
//		ItoProductOrder itoProductOrder  = new ItoProductOrder();
//		itoProductOrder.setProductId(11);
//		itoProductOrder.setTitle("商品名称");
//		itoProductOrder.setDescription("商品描述");
//		itoProductOrder.setSkuId(1);
//		itoProductOrder.setSkuPropertiesName("");
//		
//		itoProductOrder.setPrice(1000d);
//		itoProductOrder.setPostFee(20d);
//		itoProductOrder.setNum(2);
//		itoProductOrder.setTotalPrice(2000d);
//		itoProductOrder.setPayType((short) 1);
//		
//		Map<String, Object> dataMap = new HashMap<String, Object>();
//		dataMap.put("productOrder", itoProductOrder);
//		return JsonViewBuilderUtil.buildJsonView(JsonResultBuilderUtil.buildSuccessJson(dataMap));
//	}
	
	/**
	 * 产品详情
	 * @param model
	 * @return
	 */
	@RequestMapping(value = "/testSubmitOrder.json")
	public ModelAndView testSubmitOrder() {
		Map<String, Object> dataMap = new HashMap<String, Object>();
		dataMap.put("payQrcode", "http://www.jinwanr.com.cn");
		dataMap.put("payType", 1);
		dataMap.put("payTypeDesc", "货到付款");
		return JsonViewBuilderUtil.buildJsonView(JsonResultBuilderUtil.buildSuccessJson(dataMap));
	}
	
	/**
	 * 客户端提交新订单
	 * @param model
	 * @return
	 */
	@RequestMapping(value = "/postNewOrder.json")
	public ModelAndView postNewOrder(String orderJson){
		//检查请求合法性
		boolean validRequest = true;
		if(orderJson==null||!validRequest){
			throw new GeekwayException(ErrorCode.SYSTEM_ERROR);
		}else{
			ItoProductOrder order = new ItoProductOrder();//checkOrder(orderJson);//检查交易参数的合法性
			if(order!=null){
				//将新订单的支付状态初始化为未支付
				order.setPayStatus((short)0);
				order.setPayType((short)0);
				
				//获取支付类型（到付or支付宝）
				if(order.getPayType()==1){
					//支付宝流程，调用支付宝生成二维码
					
				}else{
					//货到付款，生成付款链接的二维码
					
				}
				//TODO 创建订单记录，供付款流程使用
				int result = itoProductOrderService.save(order);
				String comfirmUrl = "http://wx.jinwanr.com.cn/m/order/"+order.getOrderSn()+"?hash=1234";
				System.out.println(comfirmUrl);
				
				Map<String, Object> dataMap = new HashMap<String, Object>();
				dataMap.put("payQrcode", comfirmUrl);
				dataMap.put("payType", order.getPayType());
				
				return JsonViewBuilderUtil.buildJsonView(JsonResultBuilderUtil.buildSuccessJson(dataMap));
			}
		}
		throw new GeekwayException(ErrorCode.SYSTEM_ERROR);
	}
	
	
	/**
	 * 确认后&提交订单
	 * @param model
	 * @return
	 */
	@RequestMapping(value = "/submitOrder.json")
	public ModelAndView submitOrder(String orderJson){
		//检查请求合法性
		boolean validRequest = true;
		if(orderJson==null||!validRequest){
			throw new GeekwayException(ErrorCode.SYSTEM_ERROR);
		}else{
			//更新订单状态（邮寄信息，订单状态）
			ItoProductOrder order = checkOrder(orderJson);
			order.setPayType((short) 1);//货到付款
			order.setPayStatus((short)1);//已下单
			order.setPostStatus((short)0);//未邮寄
			int submitResult = itoProductOrderService.changeOrderStatus(order);
		}
		throw new GeekwayException(ErrorCode.SYSTEM_ERROR);
	}
	
	

	/**
	 * 检查提交的订单信息是否与服务器配置的信息匹配，避免恶意购买
	 * @param orderJson
	 * @return
	 */
	private ItoProductOrder checkOrder(String orderJson) {
		ItoProductOrder postOrder;
		try{
			postOrder = JsonUtil.gson.fromJson(orderJson, ItoProductOrder.class);
		}catch(Exception e){
			throw new GeekwayException(ErrorCode.SYSTEM_ERROR);//TODO errorcode
		}
		//productId，skuId, skuPropertiesName等必要参数不能为空
		if(postOrder==null||postOrder.getProductId()==null||postOrder.getSkuId()==null||postOrder.getSkuPropertiesName()==null){
			throw new GeekwayException(ErrorCode.SYSTEM_ERROR);//TODO errorcode
		}
		if(postOrder.getPrice()==null||postOrder.getNum()==null){//单价、数量不能为空
			throw new GeekwayException(ErrorCode.SYSTEM_ERROR);//TODO errorcode
		}
		if(postOrder.getNum()<=0){//数量必须有效(>0)
			throw new GeekwayException(ErrorCode.SYSTEM_ERROR);//TODO errorcode
		}
		if(postOrder.getPayType()!=null&&postOrder.getPayType()>0){//支付类型必须有效(>0)
			throw new GeekwayException(ErrorCode.SYSTEM_ERROR);//TODO errorcode
		}
		
		//加载sku，核对价格
		ItoSku itoSku = itoSkuService.loadById(postOrder.getSkuId());
		if(itoSku==null){//sk数据为空，无法购买
			throw new GeekwayException(ErrorCode.SYSTEM_ERROR);//TODO errorcode
		}
		if(postOrder.getNum()>itoSku.getNum()){//剩货不足，无法购买
			throw new GeekwayException(ErrorCode.SYSTEM_ERROR);//TODO errorcode
		}
		if(postOrder.getSkuPropertiesName().equals(itoSku.getPropertiesName())){//sku的properties信息不正确
			throw new GeekwayException(ErrorCode.SYSTEM_ERROR);//TODO errorcode
		}
		if(postOrder.getPrice()!=itoSku.getPrice()){//提交价格不正确
			throw new GeekwayException(ErrorCode.SYSTEM_ERROR);//TODO errorcode
		}
		if(postOrder.getTotalPrice()!= multiPrice(itoSku.getPrice(), postOrder.getNum())){//总价计算不正确（未包含运费）
			throw new GeekwayException(ErrorCode.SYSTEM_ERROR);//TODO errorcode
		}
		return postOrder;
	}
	
	
	/**
	 * 计算多选价格（未含运费）
	 * @param itemPrice
	 * @param quality
	 * @return
	 */
	private double multiPrice(double itemPrice, int quality) {
		return itemPrice*quality;
	}
	
	
	
	public static void main(String[] args) {
//		UUID.randomUUID().toString().replaceAll("-", "");
		
	}
}
