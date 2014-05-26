package com.bruce.geekway.controller.ito;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

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
@RequestMapping(value={"m"})
public class AlipayOrderController {
	
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
	@RequestMapping(value = "/order/alipayNotify")
	public String alipayNotify(Model model, String orderSn, String sig, HttpServletRequest request) {
		//获取支付宝POST过来反馈信息
		Map<String,String> params = new HashMap<String,String>();
		Map requestParams = request.getParameterMap();
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
		//通知业务参数
		String notify_data = "";//new String(request.getParameter("notify_data").getBytes("ISO-8859-1"),"UTF-8");
		//参数格式请参见技术文档7.2章节

		//获取支付宝的通知返回参数，可参考技术文档中页面跳转同步通知参数列表(以上仅供参考)//
		
		if(AlipayNotify.verify(params)){//验证成功
			//——请根据您的业务逻辑来编写程序（以下代码仅作参考）——
			
			//检查请求合法性
			if(orderSn!=null&&sig!=null){
				//根据支付宝的回调，更新订单状态
				ItoProductOrder order = itoProductOrderService.loadByOrderSn(orderSn, ConstIto.PAYTYPE_ALIPAY); 
				if(order!=null){
					if(order.getPayStatus()==ConstIto.PAYSTATUS_ALIPAY_FINISHED){//已经支付完毕，无需再次支付
						return "failed";
					}else{
						ItoProductOrder updated = new ItoProductOrder();
						updated.setId(order.getId());
						updated.setPayStatus(ConstIto.PAYSTATUS_ALIPAY_FINISHED);//支付宝已支付
						
						int submitResult = itoProductOrderService.changeOrderStatus(order);
						if(submitResult>0){
							return "success";
						}
					}
				}
			}
			
			//判断是否在商户网站中已经做过了这次通知返回的处理
				//如果没有做过处理，那么执行商户的业务程序
				//如果有做过处理，那么不执行商户的业务程序
				
//			out.println("success");	//请不要修改或删除

			//——请根据您的业务逻辑来编写程序（以上代码仅作参考）——
		}
		return "failed";
	}
	
//	/**
//	 * 展示订单信息，供用户确认(需求中已取消该流程)
//	 * @param model
//	 * @param orderSn
//	 * @param orderHash
//	 * @return
//	 */
//	@Deprecated
//	@RequestMapping(value = "/order/{orderSn}")
//	public String orderDisplay(Model model, @PathVariable String orderSn, String sig) {
//		//检查提交的参数
//		if(StringUtils.isBlank(orderSn)||StringUtils.isBlank(sig)){
//			return "ito/error";
//		}
//		
//		//根据订单号加载信息
//		ItoProductOrder itoProductOrder = itoProductOrderService.loadByOrderSn(orderSn);
//		if(itoProductOrder!=null&&itoProductOrder.getPayType()!=null&&itoProductOrder.getPayType()!=1){
//			//判断订单类型&状态
//			model.addAttribute("sig", sig);
//			model.addAttribute("productOrder", itoProductOrder);
//			if(itoProductOrder.getPayStatus()==1){//异常流程，订单已经完成
//				//直接展示订单内容
//				return "ito/orderDisplay";
//			}else{//正常流程，供用户确认&提交订单
//				return "ito/orderConfirm";
//			}
//		}
//		return "ito/error";
//	}
//	
//	
//	/**
//	 * 确认后&提交订单(需求中已取消该流程)
//	 * @param model
//	 * @return
//	 */
//	@Deprecated
//	@RequestMapping(value = "/submitOrder.json")
//	public ModelAndView submitOrder(String orderSn, String sig, String postName, String postCode, String postAddress, String postMobile){
//		//检查请求合法性
//		if(orderSn==null||sig==null){
//			throw new GeekwayException(ErrorCode.SYSTEM_ERROR);
//		}else{
//			//更新订单状态（邮寄信息，订单状态）
//			ItoProductOrder order = itoProductOrderService.loadByOrderSn(orderSn); 
//			if(order!=null&&order.getPayType()==0){
//				ItoProductOrder updated = new ItoProductOrder();
//				updated.setId(order.getId());
//				updated.setPostName(postName);
//				updated.setPostCode(postCode);
//				updated.setPostAddress(postAddress);
//				updated.setPostMobile(postMobile);
//				
//				updated.setPayStatus((short)1);//已下单确认
//				updated.setPostStatus((short)0);//未邮寄
//				int submitResult = itoProductOrderService.changeOrderStatus(order);
//				if(submitResult>0){
//					return JsonViewBuilderUtil.SUBMIT_SUCCESS_VIEW;
//				}
//			}
//		}
//		throw new GeekwayException(ErrorCode.SYSTEM_ERROR);
//	}
	
	
	
}
