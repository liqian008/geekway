package com.bruce.geekway.controller.ito;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.bruce.geekway.constants.ConstIto;
import com.bruce.geekway.model.ItoProductOrder;
import com.bruce.geekway.model.ItoSku;
import com.bruce.geekway.model.ItoSkuPropValue;
import com.bruce.geekway.model.exception.ErrorCode;
import com.bruce.geekway.model.exception.GeekwayException;
import com.bruce.geekway.service.ito.IItoProductOrderService;
import com.bruce.geekway.service.ito.IItoProductService;
import com.bruce.geekway.service.ito.IItoSkuPropValueService;
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
	
	private static final Logger logger = LoggerFactory.getLogger("ItoAppOrderLogger");
	
	
	@Autowired
	private IItoProductService itoProductService;
	@Autowired
	private IItoProductOrderService itoProductOrderService;
	@Autowired
	private IItoSkuPropValueService itoSkuPropValueService;
	
	@Autowired
	private IItoSkuService itoSkuService;
	
	/**
	 * 产品详情
	 * @param model
	 * @return
	 */
	@RequestMapping(value = "/testPostOrder.json")
	public ModelAndView testPostOrder() {
		
		ItoProductOrder itoProductOrder  = new ItoProductOrder();
		itoProductOrder.setProductId(11);
		itoProductOrder.setTitle("商品名称");
		itoProductOrder.setDescription("商品描述");
		itoProductOrder.setSkuId(1);
		itoProductOrder.setSkuPropertiesName("");
		
		itoProductOrder.setPrice(1000d);
		itoProductOrder.setPostFee(20d);
		itoProductOrder.setNum(2);
		itoProductOrder.setTotalPrice(2000d);
		itoProductOrder.setPayType((short) 1);
		
		
		itoProductOrder.setPostAddress("收件人地址");
		itoProductOrder.setPostMobile("收件人电话");
		itoProductOrder.setPostName("收件人姓名");
		itoProductOrder.setPostCode("邮编");
		itoProductOrder.setPostEmail("Email");
		
		
		Map<String, Object> dataMap = new HashMap<String, Object>();
		dataMap.put("productOrder", itoProductOrder);
		return JsonViewBuilderUtil.buildJsonView(JsonResultBuilderUtil.buildSuccessJson(dataMap));
	}
	
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
				ItoSku orderSku = itoSkuService.loadById(order.getSkuId());
				if(orderSku!=null){
					//判断支付类型
					if(order.getPayType()==ConstIto.PAYTYPE_ALIPAY){//支付宝流程，调用支付宝生成二维码
							//根据订单信息，返回alipay的二维码订单
							String alipayQrcodeUrl;
							try {
								alipayQrcodeUrl = orderSku.getAlipaySkuUrl() ;//TODO动态二维码 AlipayUtil.buildAlipayQrCode(order, orderSku);
							} catch (Exception e) {
								throw new GeekwayException(ErrorCode.ITO_PRODUCT_ORDER_ERROR);
							}
							Map<String, Object> dataMap = new HashMap<String, Object>();
							dataMap.put("payQrcodeUrl", alipayQrcodeUrl);
							dataMap.put("payType", ConstIto.PAYTYPE_ALIPAY);
							dataMap.put("orderSn", order.getOrderSn());
							return JsonViewBuilderUtil.buildJsonView(JsonResultBuilderUtil.buildSuccessJson(dataMap));
	//					}
					}else if(order.getPayType()==ConstIto.PAYTYPE_SELF){//APP支付
						//检查邮寄信息
						checkOrderPostInfo(order);
						order.setCreateTime(new Date());//订单创建时间
						
						logger.info("APP下单的订单信息!["+orderJson+"]");
						
						//直接生成订单
						int result = itoProductOrderService.save(order);
						if(result>0){
							Map<String, Object> dataMap = new HashMap<String, Object>();
							dataMap.put("payType", ConstIto.PAYTYPE_SELF);
							dataMap.put("orderSn", order.getOrderSn());
							return JsonViewBuilderUtil.buildJsonView(JsonResultBuilderUtil.buildSuccessJson(dataMap));
						}
					}else{
						logger.error("不支持的订单类型["+order.getPayType()+"]");
						throw new GeekwayException(ErrorCode.ITO_PRODUCT_ORDER_PAYTYPE_ERROR);
					}
				}
			}
		}
		logger.error("下单失败");
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
		
		//获取propValue的map，供构造order中的skuName
		HashMap<Integer, ItoSkuPropValue> propValueMap = itoSkuPropValueService.queryMap();
		//根据propName动态计算sku显示name，TODO与edit时进行合并
		String skuPropName = itoSku.getPropertiesName();
		String[] skuPropNameArray = skuPropName.split(";");
		StringBuilder sb = new StringBuilder();
		if(skuPropNameArray!=null&&skuPropNameArray.length>0){
			
			for(String skuPropItem: skuPropNameArray){
				String skuPropValueIdStr = skuPropItem.substring(skuPropItem.lastIndexOf(":")+1);
				String skuPropValueName = "错误";
				ItoSkuPropValue propValue = propValueMap.get(Integer.valueOf(skuPropValueIdStr));
				if(propValue!=null){
					skuPropValueName = propValue.getName();
				}
				sb.append(skuPropValueName+"+");
			}
		}
		if(sb.length()>0)sb.setLength(sb.length()-1);
		postOrder.setSkuName(sb.toString());
		
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
			if(StringUtils.isBlank(postOrder.getPostEmail())){//收货人email未填写
				throw new GeekwayException(ErrorCode.ITO_PRODUCT_ORDER_POST_EMAIL_ERROR);
			}
			return true;
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
