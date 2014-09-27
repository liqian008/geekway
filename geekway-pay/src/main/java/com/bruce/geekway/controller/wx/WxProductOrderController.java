package com.bruce.geekway.controller.wx;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.SortedMap;
import java.util.TreeMap;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.bruce.geekway.annotation.NeedAuthorize;
import com.bruce.geekway.constants.ConstFront;
import com.bruce.geekway.constants.ConstWeixin;
import com.bruce.geekway.model.WxProductOrder;
import com.bruce.geekway.model.WxProductSku;
import com.bruce.geekway.model.WxProductVoucher;
import com.bruce.geekway.model.WxUserAddress;
import com.bruce.geekway.model.exception.ErrorCode;
import com.bruce.geekway.model.exception.GeekwayException;
import com.bruce.geekway.model.wx.json.response.WxOauthTokenResult;
import com.bruce.geekway.model.wx.pay.WxOrderAddressJsObj;
import com.bruce.geekway.model.wx.pay.WxPayItemJsObj;
import com.bruce.geekway.service.mp.WxMpOauthService;
import com.bruce.geekway.service.product.IWxProductOrderService;
import com.bruce.geekway.service.product.IWxProductService;
import com.bruce.geekway.service.product.IWxProductSkuService;
import com.bruce.geekway.service.product.IWxProductVoucherService;
import com.bruce.geekway.service.product.IWxUserAddressService;
import com.bruce.geekway.utils.DateUtil;
import com.bruce.geekway.utils.JsonUtil;
import com.bruce.geekway.utils.OrderUtil;
import com.bruce.geekway.utils.RequestUtil;
import com.bruce.geekway.utils.ResponseBuilderUtil;
import com.bruce.geekway.utils.WxAuthUtil;

/**
 * 订单controller
 * @author liqian
 *
 */
@Controller
public class WxProductOrderController {
	
	@Autowired
	private IWxProductService wxProductService;
	@Autowired
	private IWxProductSkuService wxProductSkuService;
	@Autowired
	private IWxProductVoucherService wxProductVoucherService;
	@Autowired
	private IWxProductOrderService wxProductOrderService;
	@Autowired
	private IWxUserAddressService wxUserAddressService;
	@Autowired
	private WxMpOauthService wxMpOauthService;
	
	private static final Logger logger = LoggerFactory.getLogger(WxProductOrderController.class);

	/**
	 * 不使用购物车，直接点击购买（呈现所购商品&列出优惠券&订单价格）
	 * @param model
	 * @param productSkuId
	 * @param request
	 * @return
	 */
	@NeedAuthorize
	@RequestMapping(value = "/buyNow")
	public String buyNow(Model model, @RequestParam(required=false)String code, int productSkuId, int amount, HttpServletRequest request) {
		
		//获取userAccessToken，用于获取微信的共享地址address
		String userAccessToken = null;

		if(StringUtils.isNotBlank(code)){//oauth回调后
			//根据code换取openId
			WxOauthTokenResult oauthResult = wxMpOauthService.getOauthAccessToken(code);
			System.out.println("oauthResult: "+JsonUtil.gson.toJson(oauthResult));
			if(oauthResult!=null){
				String userOpenId = oauthResult.getOpenid();
				userAccessToken = oauthResult.getAccess_token();
				System.out.println("OAUTH openId: "+userOpenId);
				//ResponseUtil.addCookie(response, ConstFront.COOKIE_KEY_WX_OPENID, userOpenId); 
			}
		}
		
		WxProductSku productSku = wxProductSkuService.loadById(productSkuId);
		//检查订单的有效性
		checkOrder(productSku, amount);
		
		model.addAttribute("productSku", productSku);
		model.addAttribute("amount", amount);
		model.addAttribute("totalPrice", amount*productSku.getPrice());
		
		String userOpenId = "1";
		int limit = 3;
		//加载优惠券列表&供用户选择使用
		List<WxProductVoucher> availableVoucherList = wxProductVoucherService.queryUserAvailableVoucherList(userOpenId, limit);
		model.addAttribute("availableVoucherList", availableVoucherList);
		
		//获取当前页面url，用于构造地址签名
		String currentUrl = request.getRequestURL().toString()+"&"+request.getQueryString();
		System.out.println("userAccessToken: "+userAccessToken);
		WxOrderAddressJsObj orderAddressJsObj = buildWxOrderAddressJsObj(userAccessToken, currentUrl);
		model.addAttribute("orderAddressJsObj", orderAddressJsObj);
		
		return "order/buyNow";
	}

	@Deprecated
	@RequestMapping("address")
	public String address(Model model, String token, HttpServletRequest request) {
		//获取当前页面url，用于构造地址签名
		String queryString = request.getQueryString();
		String currentUrl = request.getRequestURL().toString();
		if(StringUtils.isNotBlank(queryString)){
			currentUrl = currentUrl + "&" + queryString;
		}
		System.out.println("userAccessToken: "+token);
		System.out.println("currentUrl: " + currentUrl);
		WxOrderAddressJsObj orderAddressJsObj = buildWxOrderAddressJsObj(token, currentUrl);
		model.addAttribute("orderAddressJsObj", orderAddressJsObj);
		return "order/address";
	}
	
	
	/**
	 * 确认地址&金额后，提交订单(生成预支付的订单号)，此步之前应该已经获取了商户的地址
	 * @param model
	 * @param request
	 */
	@NeedAuthorize
	@RequestMapping(value = "/submitOrder.json")
	public ModelAndView submitOrder(Model model, int productSkuId, int amount, @RequestParam(required=false, defaultValue="0")long voucherId, 
			WxUserAddress addressInfo, HttpServletRequest request) {
		String userOpenId = "1234";//(String) request.getAttribute(ConstFront.CURRENT_USER);//获取用户信息
		checkUserOpenId(userOpenId);
		
		//检查地址的有效性
		checkAddress(addressInfo);
		WxProductVoucher voucher = null;
		if(voucherId>0){
			//验证优惠券有效性
			voucher = wxProductVoucherService.loadUserVoucherById(userOpenId, voucherId);
			checkUserVoucher(voucher);
		}
		//加载商品信息
		WxProductSku productSku = wxProductSkuService.loadById(productSkuId);
		//检查订单的有效性
		checkOrder(productSku, amount);
		
		//构造预购买的订单数据
		Date currentTime = new Date();
		WxProductOrder productOrder = new WxProductOrder();
		productOrder.setUserOpenId(userOpenId);//用户身份
		productOrder.setVoucherId(voucherId);//优惠券id
		
		double productFee = productSku.getPrice() * amount;//商品金额
		double discountFee = voucher==null?0:voucher.getPrice();
		double transportFee = 0;
		
		productOrder.setProductFee(productFee);
		productOrder.setDiscountFee(discountFee);//折扣费用
		productOrder.setTransportFee(transportFee);//运费
		double totalFee = productFee - discountFee + transportFee;
		productOrder.setTotalFee(totalFee);//总费用
		productOrder.setStatus((short) 0);//预支付状态
		productOrder.setCreateTime(currentTime);
		//组装邮寄地址
		populatePostInfo(productOrder, addressInfo);
		//保存订单
		int result = wxProductOrderService.createOrder(productOrder, addressInfo);
		if(result>0){
//			Map<String, String> dataMap = new HashMap<String, String>();
//			dataMap.put("outTradeNo", productOrder.getOutTradeNo());
//			dataMap.put("orderId", String.valueOf(productOrder.getId()));
			return ResponseBuilderUtil.buildJsonView(ResponseBuilderUtil.buildSuccessJson("123"));
		}
		return ResponseBuilderUtil.buildJsonView(ResponseBuilderUtil.buildErrorJson(ErrorCode.WX_PRODUCT_ORDER_CREATE_ERROR));
	}

	
	/**
	 * 订单详情
	 * @param model
	 * @param orderId
	 * @param tradeNo
	 * @param request
	 * @return
	 */
	@NeedAuthorize
	@RequestMapping(value = "/orderInfo")
	public String orderInfo(Model model, long orderId, String tradeNo,  HttpServletRequest request) {
		String userOpenId = (String) request.getAttribute(ConstFront.CURRENT_USER);//获取用户信息
		
		//加载商品信息
		WxProductOrder orderInfo = wxProductOrderService.loadByTradeNo(tradeNo);
		model.addAttribute("orderInfo", orderInfo);
		//检查订单状态
		
		//如果是未付款状态，需要构造支付js对象
		//再开始构造微信所需的订单对象
		String remoteIp = RequestUtil.getRemoteIP(request);
		WxPayItemJsObj wxPayJsObj = buildWxPayJsObj(remoteIp);
		model.addAttribute("wxPayJsObj", wxPayJsObj);
		
		//需要在页面中增加地址的处理
		return "order/orderInfo";
	}
	
	
	/**
	 * 
	 * @param clientIp
	 * @return
	 */
	private WxPayItemJsObj buildWxPayJsObj(String clientIp) {
		String banktype = "WX";
		String productName = "包笑公堂-爆破镖";// 商品名称信息，这里由测试网页填入。
		String fee_type = "1";// 费用类型，这里1为默认的人民币
		String input_charset = "UTF-8";// 字符集，可以使用GBK或者UTF-8
		String notify_url = "http://wxmp.1758.com/wxpay/jspayNotify";// 支付成功后将通知该地址
		String out_trade_no = OrderUtil.generateOrderSn4Wx();// 订单号，商户需要保证该字段对于本商户的唯一性, 长度<32
		String spbill_create_ip = clientIp;// 用户浏览器的ip，这个需要在前端获取
		String total_fee = "1";// 总金额。

		SortedMap<String, String> packageMap = new TreeMap<String, String>();
		packageMap.put("bank_type", banktype);
		packageMap.put("body", productName);
		packageMap.put("fee_type", fee_type);
		packageMap.put("input_charset", input_charset);
		packageMap.put("notify_url", notify_url);
		packageMap.put("out_trade_no", out_trade_no);
		packageMap.put("spbill_create_ip", spbill_create_ip);
		packageMap.put("total_fee", total_fee);
		
		//生成package的串
		String finalPackage = WxAuthUtil.packageSign(packageMap);
		
		String timestamp = String.valueOf(DateUtil.getUnixTime(new Date()));
		String noncestr = WxAuthUtil.createNoncestr();
		
		SortedMap<String, String> paySignMap = new TreeMap<String, String>();
		//无需传入appid，签名算法中会自动填充
		paySignMap.put("appkey", ConstWeixin.WX_PAY_SIGN_KEY);//外部需传入appkey
		paySignMap.put("timestamp", timestamp);
		paySignMap.put("noncestr", noncestr);
		paySignMap.put("package", finalPackage);
		//生成paySign
		String paySign = WxAuthUtil.paySign(paySignMap);
		
		//构造支付js对象，传到前端，供调起微信支付
		WxPayItemJsObj itemJsObj = new WxPayItemJsObj();
		itemJsObj.setAppId(ConstWeixin.WX_APP_ID);
		itemJsObj.setTimeStamp(timestamp);
		itemJsObj.setNonceStr(noncestr);
		itemJsObj.setPackageValue(finalPackage);
		itemJsObj.setPaySign(paySign);
		return itemJsObj;
	}
	
	/**
	 * 构造地址对象
	 * @param userAccessToken
	 * @param currentUrl
	 * @return
	 */
	private WxOrderAddressJsObj buildWxOrderAddressJsObj(String userAccessToken, String currentUrl) {
		
		String timestamp = String.valueOf(DateUtil.getUnixTime(new Date()));
		String noncestr = WxAuthUtil.createNoncestr();
		
		SortedMap<String, String> addressSignMap = new TreeMap<String, String>();
		//无需传入appid，签名算法中会自动填充
		addressSignMap.put("timestamp", timestamp);
		addressSignMap.put("noncestr", noncestr);
		addressSignMap.put("url", currentUrl);
		addressSignMap.put("accessToken", userAccessToken);
		
		//生成addressSign
		String addressSign = WxAuthUtil.paySign(addressSignMap);
		
		//构造支付js对象，传到前端，供调起微信支付
		WxOrderAddressJsObj addressJsObj = new WxOrderAddressJsObj();
		addressJsObj.setAppId(ConstWeixin.WX_APP_ID);
		addressJsObj.setTimeStamp(timestamp);
		addressJsObj.setNonceStr(noncestr);
		addressJsObj.setAddrSign(addressSign);
//		System.out.println("addressJsObj: "+JsonUtil.gson.toJson(addressJsObj));
		return addressJsObj;
	}
	

	/**
	 * 验证地址的有效性
	 * @param userAddress
	 */
	private void checkUserOpenId(String userOpenId) {
		if(StringUtils.isBlank(userOpenId)){
			//用户身份为空
			throw new GeekwayException(ErrorCode.USER_ERROR);
		}
	}
	
	/**
	 * 检查订单有效性
	 * @param amount
	 * @param productSku
	 */
	private void checkOrder(WxProductSku productSku, int amount) {
		if(productSku==null||productSku.getId()==null){
			//加载商品失败
			throw new GeekwayException(ErrorCode.WX_PRODUCT_ORDER_BASIC_PARAM_ERROR);
		}
		if(productSku.getPrice()==null||productSku.getPrice()<=0){
			//商品价格错误，不能购买
			throw new GeekwayException(ErrorCode.WX_PRODUCT_ORDER_BUY_DATA_ERROR);
		}
		if(amount<=0){
			//购买数量无效
			throw new GeekwayException(ErrorCode.WX_PRODUCT_ORDER_BUY_AMOUNT_ERROR);
		}
		if(productSku.getAmount()==null||productSku.getAmount()<=0||productSku.getAmount()<amount){
			//商品库存不足，无法购买
			throw new GeekwayException(ErrorCode.WX_PRODUCT_ORDER_EMPTY_ITEM_ERROR);
		}
	}
	
	/**
	 * 验证地址的有效性
	 * @param userAddress
	 */
	private void checkAddress(WxUserAddress userAddress) {
		if(userAddress==null){
			//地址对象为空
			throw new GeekwayException(ErrorCode.WX_PRODUCT_ORDER_BASIC_PARAM_ERROR);
		}
		if(StringUtils.isBlank(userAddress.getPostName())){
			//收件人姓名错误
			throw new GeekwayException(ErrorCode.WX_PRODUCT_ORDER_POST_NAME_ERROR);
		}
		if(StringUtils.isBlank(userAddress.getPostMobile())){
			//收件人手机错误
			throw new GeekwayException(ErrorCode.WX_PRODUCT_ORDER_POST_MOBILE_ERROR);
		}
//		if(StringUtils.isBlank(userAddress.getPostAddress())){
//			//收件人地址信息错误
//			throw new GeekwayException(ErrorCode.WX_PRODUCT_ORDER_POST_ADDRESS_ERROR);
//		}
		//postCode、nationalCode、省、市、县等信息不做检查
	}
	
	
	
	/**
	 * 验证地址的有效性
	 * @param userAddress
	 */
	private void checkUserVoucher(WxProductVoucher voucher) {
		if(voucher==null){
			//地址对象为空
			throw new GeekwayException(ErrorCode.WX_PRODUCT_ORDER_VOUCHER_ERROR);
		}
		if(voucher.getStatus()==null||voucher.getStatus()!=1){
			//优惠券不可用
			throw new GeekwayException(ErrorCode.WX_PRODUCT_ORDER_VOUCHER_UNAVAILABLE_ERROR);
		}
		if(voucher.getExpireTime()==null||voucher.getExpireTime().getTime()<System.currentTimeMillis()){
			//优惠券已过期
			throw new GeekwayException(ErrorCode.WX_PRODUCT_ORDER_VOUCHER_EXPIRED_ERROR);
		}
	}
	
	

	private void populatePostInfo(WxProductOrder productOrder, WxUserAddress addressInfo) {
		productOrder.setPostName(addressInfo.getPostName());
		productOrder.setPostMobile(addressInfo.getPostMobile());
		productOrder.setPostAddress(addressInfo.getPostAddress());
		productOrder.setPostCode(addressInfo.getPostCode());
		productOrder.setPostNationalCode(addressInfo.getNationalCode()); 
	}
}
