package com.bruce.geekway.controller.ito;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang3.StringUtils;
import org.dom4j.DocumentException;
import org.dom4j.DocumentHelper;
import org.dom4j.Element;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.alipay.model.AlipayNotifyData;
import com.alipay.model.AlipayReturnData;
import com.alipay.model.json.AlipayReturnErrorCodeEnum;
import com.alipay.model.json.AlipayReturnResult;
import com.alipay.util.AlipayNotify;
import com.bruce.geekway.constants.ConstIto;
import com.bruce.geekway.model.ItoProductOrder;
import com.bruce.geekway.model.exception.ErrorCode;
import com.bruce.geekway.model.exception.GeekwayException;
import com.bruce.geekway.service.IWxMaterialArticleService;
import com.bruce.geekway.service.ito.IItoProductOrderService;
import com.bruce.geekway.utils.JsonViewBuilderUtil;

/**
 * Handles requests for the application home page.
 */
@Controller
@RequestMapping(value={"api"})
public class AlipayOrderController {

	private static final SimpleDateFormat SDF = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
	
	private static final Logger logger = LoggerFactory.getLogger("ItoAppOrderLogger");
	
	
	@Autowired
	private IWxMaterialArticleService wxMaterialArticleService;
	@Autowired
	private IItoProductOrderService itoProductOrderService;

	
	/**
	 * alipay的支付后的回调
	 * @param model
	 * @param orderSn
	 * @param orderSnSig
	 * @return
	 */
	@RequestMapping(value = "/order/alipayReturn")
	public String alipayReturn(Model model, AlipayReturnData alipayReturnData, HttpServletRequest request) {
		
		System.out.println("=========alipayReturn=====1====");
		
		//获取支付宝POST过来反馈信息
		Map<String,String> params = new HashMap<String,String>();
		Map requestParams = request.getParameterMap();
		logger.info("Alipay下单的订单请求!["+requestParams+"]");
		for (Iterator iter = requestParams.keySet().iterator(); iter.hasNext();) {
			String name = (String) iter.next();
			String[] values = (String[]) requestParams.get(name);
			String valueStr = "";
			for (int i = 0; i < values.length; i++) {
				valueStr = (i == values.length - 1) ? valueStr + values[i] : valueStr + values[i] + ",";
			}
			//乱码解决，这段代码在出现乱码时使用。如果mysign和sign不相等也可以使用这段代码转化
			//valueStr = new String(valueStr.getBytes("ISO-8859-1"), "gbk");
			params.put(name, valueStr);
		}
		
		System.out.println("=========alipayReturn=====2====");
//		if(true){//验证成功
		if(AlipayNotify.verify(params)){//验证成功
			System.out.println("=========alipayReturn=====3====");
			if(alipayReturnData!=null){
				System.out.println("=========alipayReturn=====4====");
				//在ito系统内生成新订单
				
				String address = alipayReturnData.getProv() + "-" + alipayReturnData.getCity() + "-" + alipayReturnData.getArea() + "-" + alipayReturnData.getAddress();

				ItoProductOrder alipayProductOrder = new ItoProductOrder();
				alipayProductOrder.setPayType(ConstIto.PAYTYPE_ALIPAY);
				//二维码链接
				alipayProductOrder.setQrcodeUrl(alipayReturnData.getQrcode());
				//商品名称
				alipayProductOrder.setTitle(alipayReturnData.getGoods_name());
				//sku名称
				alipayProductOrder.setSkuName(alipayReturnData.getSku_name());
				//数量
				alipayProductOrder.setNum(alipayReturnData.getQuantity());
				System.out.println("=========alipayProductOrder.getNum========="+alipayProductOrder.getNum());
				
				//价格
				alipayProductOrder.setTotalPrice(alipayReturnData.getPrice());
				System.out.println("=========alipayProductOrder.getTotalPrice========="+alipayProductOrder.getTotalPrice());
				//买家
				alipayProductOrder.setPostName(alipayReturnData.getBuyer_name());
				//邮编
				alipayProductOrder.setPostCode(alipayReturnData.getPost_code());
				//邮寄地址
				alipayProductOrder.setPostAddress(address);
				//联系电话
				alipayProductOrder.setPostMobile(alipayReturnData.getPhone());
				//支付状态
				alipayProductOrder.setPayStatus(ConstIto.PAYSTATUS_ALIPAY_APPLIED);//支付宝已下单
				//物流状态
				alipayProductOrder.setPostStatus((short)0);//未邮寄
				alipayProductOrder.setCreateTime(new Date());
				System.out.println("=========alipayReturn=====5====");
				//创建订单
				int result = itoProductOrderService.save(alipayProductOrder);
				System.out.println("=========alipayReturn=====6====");
				if(result==1){//订单新建成功
					System.out.println("=========alipayReturn=====7====");
					AlipayReturnResult alipayReturnResult = new AlipayReturnResult(null, alipayProductOrder.getOrderSn());
					System.out.println("=========alipayReturn=====alipayReturnResult===="+alipayReturnResult.toString());
					model.addAttribute("alipayReturnResult", alipayReturnResult.toString());
					return "ito/alipay/returnResult";
				}
			}
		}
		System.out.println("=========alipayReturn=====8====");
		AlipayReturnResult alipayReturnResult = new AlipayReturnResult(AlipayReturnErrorCodeEnum.OUT_SYSTEM_ERROR, null);
		model.addAttribute("alipayReturnResult", alipayReturnResult.toString());
		return "ito/alipay/returnResult";
	}
	
	
	/**
	 * alipay的支付后的回调
	 * @param model
	 * @param orderSn
	 * @param orderSnSig
	 * @return
	 */
	@RequestMapping(value = "/order/alipayNotify")
	public String alipayNotify(Model model, String notify_data, String sign, HttpServletRequest request) {
		System.out.println("=========alipayNotify=====1====");
		
		System.out.println("=========notify_data========="+notify_data);
		//获取支付宝POST过来反馈信息
		Map<String,String> params = new HashMap<String,String>();
		Map requestParams = request.getParameterMap();
		logger.info("Alipay订单状态变更的请求!["+requestParams+"]");
		for (Iterator iter = requestParams.keySet().iterator(); iter.hasNext();) {
			String name = (String) iter.next();
			String[] values = (String[]) requestParams.get(name);
			String valueStr = "";
			for (int i = 0; i < values.length; i++) {
				valueStr = (i == values.length - 1) ? valueStr + values[i] : valueStr + values[i] + ",";
			}
			//乱码解决，这段代码在出现乱码时使用。如果mysign和sign不相等也可以使用这段代码转化
			//valueStr = new String(valueStr.getBytes("ISO-8859-1"), "gbk");
			params.put(name, valueStr);
		}
		
		//获取支付宝的通知返回参数，可参考技术文档中页面跳转同步通知参数列表(以下仅供参考)//
		//参数格式请参见技术文档7.2章节
		System.out.println("=========alipayNotify=====2====");
		
//		if(true){//验证成功
		if(AlipayNotify.verify(params)){//验证成功
				System.out.println("=========alipayNotify=====3====");
			AlipayNotifyData notifyData = parseNotifyData(notify_data);
			//获取ito订单号
			String orderSn = notifyData.getOut_trade_no();
			System.out.println("=========alipayNotify=====4====");
			
			//检查订单合法性
			ItoProductOrder order = itoProductOrderService.loadByOrderSn(orderSn, ConstIto.PAYTYPE_ALIPAY); 
			System.out.println("=========alipayNotify=====5====");
			if(order!=null){
				System.out.println("=========alipayNotify=====6====");
				if(order.getPayStatus()==ConstIto.PAYSTATUS_ALIPAY_FINISHED){//已经支付完毕，无需再次支付
					System.out.println("=========alipayNotify=====7====");
				}else{//根据支付宝的回调，更新订单状态
					System.out.println("=========alipayNotify=====8====");
					ItoProductOrder updated = new ItoProductOrder();
					updated.setId(order.getId());
					updated.setPayStatus(ConstIto.PAYSTATUS_ALIPAY_FINISHED);//支付宝已支付
					System.out.println("=========alipayNotify=====9====");
					int submitResult = itoProductOrderService.changeOrderStatus(updated);
					System.out.println("=========alipayNotify=====10====");
					if(submitResult>0){
						System.out.println("=========alipayNotify=====11====");
						model.addAttribute("alipayNotifyResult", "success");
					}
				}
			}else{
				//订单不存在
				System.out.println("=========alipayNotify=====12====");
				
			}
		}
		//——请根据您的业务逻辑来编写程序（以上代码仅作参考）——
		System.out.println("=========alipayNotify=====13====");
		return "ito/alipay/notifyResult";
	}
	
	
	/**
	 * 根据返回的notify_data数据构造对象
	 * @param notifyData
	 * @return
	 */
	private AlipayNotifyData parseNotifyData(String notifyDataXml) {
		try {
			Element eleRoot = DocumentHelper.parseText(notifyDataXml).getRootElement();
			
			AlipayNotifyData notifyData = new AlipayNotifyData();
			
			notifyData.setPartner(strVal(eleRoot, "partner"));
			notifyData.setTrade_no(strVal(eleRoot, "trade_no"));
			notifyData.setOut_trade_no(strVal(eleRoot, "out_trade_no"));
			notifyData.setTotal_fee(doubleVal(eleRoot, "total_fee"));
			notifyData.setTrade_status(strVal(eleRoot, "trade_status"));
			notifyData.setBuyer_email(strVal(eleRoot, "buyer_email"));
			
			//下单时间
			String gmtCreateStr = strVal(eleRoot, "gmt_create");
			//付款时间
			String gmtPaymentStr = strVal(eleRoot, "gmt_payment");
			notifyData.setGmt_create(SDF.parse(gmtCreateStr));
			notifyData.setGmt_payment(SDF.parse(gmtPaymentStr));
			return notifyData;
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		//返回支付错误
		throw new GeekwayException(ErrorCode.ITO_PRODUCT_ORDER_ALIPAY_NOTIFY_ERROR);
	}

	/**
	 * 展示订单信息，供用户确认(需求中已取消该流程)
	 * @param model
	 * @param orderSn
	 * @param orderHash
	 * @return
	 */
	@Deprecated
	@RequestMapping(value = "/order/{orderSn}")
	public String orderDisplay(Model model, @PathVariable String orderSn, String sig) {
		//检查提交的参数
		if(StringUtils.isBlank(orderSn)||StringUtils.isBlank(sig)){
			return "ito/error";
		}
		
		//根据订单号加载信息
		ItoProductOrder itoProductOrder = itoProductOrderService.loadByOrderSn(orderSn);
		if(itoProductOrder!=null&&itoProductOrder.getPayType()!=null&&itoProductOrder.getPayType()!=1){
			//判断订单类型&状态
			model.addAttribute("sig", sig);
			model.addAttribute("productOrder", itoProductOrder);
			if(itoProductOrder.getPayStatus()==1){//异常流程，订单已经完成
				//直接展示订单内容
				return "ito/orderDisplay";
			}else{//正常流程，供用户确认&提交订单
				return "ito/orderConfirm";
			}
		}
		return "ito/error";
	}
	
	
	/**
	 * 确认后&提交订单(需求中已取消该流程)
	 * @param model
	 * @return
	 */
	@Deprecated
	@RequestMapping(value = "/submitOrder.json")
	public ModelAndView submitOrder(String orderSn, String sig, String postName, String postCode, String postAddress, String postMobile){
		//检查请求合法性
		if(orderSn==null||sig==null){
			throw new GeekwayException(ErrorCode.SYSTEM_ERROR);
		}else{
			//更新订单状态（邮寄信息，订单状态）
			ItoProductOrder order = itoProductOrderService.loadByOrderSn(orderSn); 
			if(order!=null&&order.getPayType()==0){
				ItoProductOrder updated = new ItoProductOrder();
				updated.setId(order.getId());
				updated.setPostName(postName);
				updated.setPostCode(postCode);
				updated.setPostAddress(postAddress);
				updated.setPostMobile(postMobile);
				
				updated.setPayStatus((short)1);//已下单确认
				updated.setPostStatus((short)0);//未邮寄
				int submitResult = itoProductOrderService.changeOrderStatus(order);
				if(submitResult>0){
					return JsonViewBuilderUtil.SUBMIT_SUCCESS_VIEW;
				}
			}
		}
		throw new GeekwayException(ErrorCode.SYSTEM_ERROR);
	}
	
	
	private static String strVal(Element ele, String name) {
		return ele.element(name).getStringValue();
	}
	
	private static Double doubleVal(Element ele, String name) {
		return Double.valueOf(ele.element(name).getStringValue());
	}
}
