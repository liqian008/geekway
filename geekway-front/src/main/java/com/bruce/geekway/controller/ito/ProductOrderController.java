package com.bruce.geekway.controller.ito;

import java.util.HashMap;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.alipay.config.AlipayConfig;
import com.alipay.util.AlipaySubmit;
import com.alipay.util.AlipayUtil;
import com.alipay.util.UtilDate;
import com.bruce.geekway.constants.ConstIto;
import com.bruce.geekway.model.ItoProductOrder;
import com.bruce.geekway.model.ItoSku;
import com.bruce.geekway.model.exception.ErrorCode;
import com.bruce.geekway.model.exception.GeekwayException;
import com.bruce.geekway.service.ito.IItoProductOrderService;
import com.bruce.geekway.service.ito.IItoProductService;
import com.bruce.geekway.service.ito.IItoSkuService;
import com.bruce.geekway.utils.ItoOrderUtil;
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
	 * 支持2种方式： 1、pad提交地址直接购买；2、提交至支付宝支付
	 * （微支付的方式，则直接使用口袋通的二维码）
	 * @param model
	 * @return
	 */
	@RequestMapping(value = "/postJsonOrder.json")
	public ModelAndView postJsonOrder(@RequestBody String orderJson){
		//检查请求合法性
		boolean validRequest = true;
		if(orderJson!=null&&validRequest){
			ItoProductOrder order = checkOrder(orderJson);//检查交易参数的合法性
			if(order!=null){
				//将新订单的支付状态初始化为未支付
				order.setPayStatus((short)0);
				
				//判断支付类型
				if(order.getPayType()==ConstIto.PAYTYPE_ALIPAY){//支付宝流程，调用支付宝生成二维码
					//生成预备订单
					int result = itoProductOrderService.save(order);
					if(result>0){
						//TODO 根据订单信息，动态生成alipay的二维码订单
						String alipayQrcodeUrl = "http://www.alipay.com";
						//TODO FIX
						AlipayUtil.buildAlipayQrCode(order, null);
						
						Map<String, Object> dataMap = new HashMap<String, Object>();
						dataMap.put("payQrcodeUrl", alipayQrcodeUrl);
						dataMap.put("payType", ConstIto.PAYTYPE_ALIPAY);
						return JsonViewBuilderUtil.buildJsonView(JsonResultBuilderUtil.buildSuccessJson(dataMap));
					}
				}else if(order.getPayType()==ConstIto.PAYTYPE_SELF){//pad上进行到付
					//检查邮寄信息
					checkOrderPostInfo(order);
					//直接生成订单
					int result = itoProductOrderService.save(order);
					if(result>0){
						Map<String, Object> dataMap = new HashMap<String, Object>();
						dataMap.put("payType", ConstIto.PAYTYPE_SELF);
						return JsonViewBuilderUtil.buildJsonView(JsonResultBuilderUtil.buildSuccessJson(dataMap));
					}
				}else{
					throw new GeekwayException(ErrorCode.ITO_PRODUCT_ORDER_PAYTYPE_ERROR);
				}
			}
		}
		throw new GeekwayException(ErrorCode.ITO_PRODUCT_ORDER_ERROR);
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
			throw new GeekwayException(ErrorCode.ITO_PRODUCT_ORDER_ERROR);
		}
		//productId，skuId, skuPropertiesName等必要参数不能为空
		if(postOrder==null||postOrder.getProductId()==null||postOrder.getSkuId()==null||postOrder.getSkuPropertiesName()==null){
			throw new GeekwayException(ErrorCode.ITO_PRODUCT_ORDER_BASIC_PARAM_ERROR);
		}
		if(postOrder.getPrice()==null||postOrder.getNum()==null){//单价、数量不能为空
			throw new GeekwayException(ErrorCode.ITO_PRODUCT_ORDER_BUY_DATA_ERROR);
		}
		if(postOrder.getNum()<=0){//数量必须有效(>0)
			throw new GeekwayException(ErrorCode.ITO_PRODUCT_ORDER_BUY_NUM_ERROR);
		}
		if(postOrder.getPayType()!=null&&postOrder.getPayType()<0){//支付类型必须有效(>0)
			throw new GeekwayException(ErrorCode.ITO_PRODUCT_ORDER_PAYTYPE_ERROR);
		}
		
		//加载sku，核对价格
		ItoSku itoSku = itoSkuService.loadById(postOrder.getSkuId());
		if(itoSku==null){//sk数据为空，无法购买
			throw new GeekwayException(ErrorCode.ITO_PRODUCT_ORDER_SKU_ERROR);
		}
		if(postOrder.getNum()>itoSku.getNum()){//剩货不足，无法购买
			throw new GeekwayException(ErrorCode.ITO_PRODUCT_ORDER_EMPTY_ITEM_ERROR);
		}
		if(!postOrder.getSkuPropertiesName().equals(itoSku.getPropertiesName())){//sku的properties信息不正确
			throw new GeekwayException(ErrorCode.ITO_PRODUCT_ORDER_SKU_NO_TMATCH_ERROR);
		}
		if(!postOrder.getPrice().equals(itoSku.getPrice())){//提交价格不正确
			throw new GeekwayException(ErrorCode.ITO_PRODUCT_ORDER_PRICE_ERROR);
		}
		if(!postOrder.getTotalPrice().equals(multiPrice(itoSku.getPrice(), postOrder.getNum()))){//总价计算不正确（未包含运费）
			throw new GeekwayException(ErrorCode.ITO_PRODUCT_ORDER_TOTAL_PRICE_ERROR);
		}
		return postOrder;
	}
	
	
	
	/**
	 * 检查提交的订单的邮寄信息
	 * @param orderJson
	 * @return
	 */
	private boolean checkOrderPostInfo(ItoProductOrder postOrder) {
		if(postOrder!=null){
			if(StringUtils.isBlank(postOrder.getPostName())){//收件人姓名未填写
				throw new GeekwayException(ErrorCode.ITO_PRODUCT_ORDER_POST_NAME_ERROR);
			}
			if(StringUtils.isBlank(postOrder.getPostAddress())){//邮寄地址未填写
				throw new GeekwayException(ErrorCode.ITO_PRODUCT_ORDER_POST_ADDRESS_ERROR);
			}
			if(StringUtils.isBlank(postOrder.getPostMobile())){//收货电话未填写
				throw new GeekwayException(ErrorCode.ITO_PRODUCT_ORDER_POST_MOBILE_ERROR);
			}
		}
		throw new GeekwayException(ErrorCode.ITO_PRODUCT_ORDER_ERROR);
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
	
//	private Map<String, String> buildAlipayParam(String bizData){
//		//接口调用时间
//		String timestamp = UtilDate.getDateFormatter();
//		//格式为：yyyy-MM-dd HH:mm:ss
//
//		//动作
//		String method = "add";
//		//创建商品二维码
//		//业务类型
//		String biz_type = "1";
//		//目前只支持1
//		//业务数据
//		String biz_data = bizData;
//		//格式：JSON 大字符串，详见技术文档4.2.1章节
//		
//		//////////////////////////////////////////////////////////////////////////////////
//		
//		//把请求参数打包成数组
//		Map<String, String> sParaTemp = new HashMap<String, String>();
//		sParaTemp.put("service", "alipay.mobile.qrcode.manage");
//        sParaTemp.put("partner", AlipayConfig.partner);
//        sParaTemp.put("_input_charset", AlipayConfig.input_charset);
//		sParaTemp.put("timestamp", timestamp);
//		sParaTemp.put("method", method);
//		sParaTemp.put("biz_type", biz_type);
//		sParaTemp.put("biz_data", biz_data);
//		return sParaTemp;
//	}
	
	
	public static void main(String[] args) {
//		UUID.randomUUID().toString().replaceAll("-", "");
		
	}
}
