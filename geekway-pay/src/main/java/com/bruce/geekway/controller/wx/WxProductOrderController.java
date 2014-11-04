package com.bruce.geekway.controller.wx;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.SortedMap;
import java.util.TreeMap;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.bruce.foundation.util.DateUtil;
import com.bruce.geekway.annotation.NeedAuthorize;
import com.bruce.geekway.annotation.NeedAuthorize.AuthorizeStrategy;
import com.bruce.geekway.constants.ConstFront;
import com.bruce.geekway.constants.ConstWeixin;
import com.bruce.geekway.model.WxProductCart.CartProductSku;
import com.bruce.geekway.model.WxProductOrder;
import com.bruce.geekway.model.WxProductOrderItem;
import com.bruce.geekway.model.WxProductSku;
import com.bruce.geekway.model.WxProductSkuCriteria;
import com.bruce.geekway.model.WxProductVoucher;
import com.bruce.geekway.model.WxUserAddress;
import com.bruce.geekway.model.exception.ErrorCode;
import com.bruce.geekway.model.exception.GeekwayException;
import com.bruce.geekway.model.wx.pay.WxOrderAddressJsObj;
import com.bruce.geekway.model.wx.pay.WxPayItemJsObj;
import com.bruce.geekway.service.mp.WxMpOauthService;
import com.bruce.geekway.service.product.IWxDeliveryTemplateService;
import com.bruce.geekway.service.product.IWxProductOrderItemService;
import com.bruce.geekway.service.product.IWxProductOrderService;
import com.bruce.geekway.service.product.IWxProductService;
import com.bruce.geekway.service.product.IWxProductSkuService;
import com.bruce.geekway.service.product.IWxProductVoucherService;
import com.bruce.geekway.service.product.IWxUserAddressService;
import com.bruce.geekway.utils.CartUtil;
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
	private IWxProductOrderItemService wxProductOrderItemService;
	@Autowired
	private IWxUserAddressService wxUserAddressService;
	@Autowired
	private IWxDeliveryTemplateService wxDeliveryTemplateService;
	@Autowired
	private WxMpOauthService wxMpOauthService;
	
	private static final Logger logger = LoggerFactory.getLogger(WxProductOrderController.class);

	
//	@RequestMapping(value = "/tests")
//	public String tests(Model model, HttpServletRequest request) {
//		
//		return "cart/test";
//	}
//	
//	@RequestMapping(value = "/test1.json")
//	public ModelAndView test1(Model model, int[] totalAmount, int[] productSkuId, HttpServletRequest request) {
//		
//		System.out.println(totalAmount);
//		System.out.println(productSkuId);
//		
//		return ResponseBuilderUtil.buildJsonView(ResponseBuilderUtil.buildSuccessJson("123"));
//	}
	
	
	
//	/**
//	 * 不使用购物车，直接点击购买（呈现所购商品&列出优惠券&订单价格）
//	 * 强制用户使用oauth，以刷最新的userToken，用于获取地址信息
//	 * @param model
//	 * @param productSkuId
//	 * @param buyAmount
//	 * @param request
//	 * @return
//	 */
//	@NeedAuthorize(authorizeStrategy=AuthorizeStrategy.COOKIE_DENY)
//	@RequestMapping(value = "/buyNow")
//	public String buyNow(Model model, @RequestParam(required=false)String code, int productSkuId, int buyAmount, HttpServletRequest request, HttpServletResponse response) {
//		String userOpenId = (String) request.getAttribute(ConstFront.CURRENT_USER);
//		//userAccessToken，用于获取微信的共享地址address
//		String userAccessToken = (String) request.getAttribute(ConstFront.CURRENT_USER_ACCESS_TOKEN);
//		if(logger.isDebugEnabled()){
//			logger.debug("进入[购买信息页面] code: "+code+", debug模式: "+ConstWeixin.WX_OAUTH_DEBUG);
//			logger.debug("进入[购买信息页面] userOpenId: "+userOpenId+", userAccessToken: "+userAccessToken);
//		}
//		
//		WxProductSku productSku = wxProductSkuService.loadById(productSkuId);
//		//检查订单的有效性
//		checkOrder(productSku, buyAmount);
//		
//		List<CartProductSku> cartItemList = new ArrayList<CartProductSku>();
//		cartItemList.add(new CartProductSku(productSku, buyAmount));
//		
//		model.addAttribute("cartItemList", cartItemList);
//		
//		int limit = 3;
//		//加载优惠券列表&供用户选择使用
//		List<WxProductVoucher> availableVoucherList = wxProductVoucherService.queryUserAvailableVoucherList(userOpenId, limit);
//		model.addAttribute("availableVoucherList", availableVoucherList);
//		
//		//获取当前页面url，用于构造地址签名
//		String currentUrl = request.getRequestURL().toString()+"&"+request.getQueryString();
//		System.out.println("userAccessToken: "+userAccessToken);
//		WxOrderAddressJsObj orderAddressJsObj = buildWxOrderAddressJsObj(userAccessToken, currentUrl);
//		model.addAttribute("orderAddressJsObj", orderAddressJsObj);
//		
//		return "order/buy";
//	}
	
	
	/**
	 * 结算清单（呈现所购商品&订单价格， 兼容购物车和直接购买）
	 * 强制用户使用oauth，以刷最新的userToken，用于获取地址信息
	 * @param model
	 * @param productSkuId
	 * @param cartBuy 来自购物车中的结算请求（生成订单后需要清空购物车）
	 * @param request
	 * @return
	 */
	@NeedAuthorize(authorizeStrategy=AuthorizeStrategy.COOKIE_DENY)
	@RequestMapping(value = "/buy")
	public String buy(Model model, @RequestParam(required=false)String code, int productSkuId[], int buyAmount[], @RequestParam(required=false, defaultValue="false")boolean cartBuy, HttpServletRequest request, HttpServletResponse response) {
		String userOpenId = (String) request.getAttribute(ConstFront.CURRENT_USER);
		//userAccessToken，用于获取微信的共享地址address
		String userAccessToken = (String) request.getAttribute(ConstFront.CURRENT_USER_ACCESS_TOKEN);
		if(logger.isDebugEnabled()){
			logger.debug("进入[购买信息页面] code: "+code+", debug模式: "+ConstWeixin.WX_OAUTH_DEBUG);
			logger.debug("进入[购买信息页面] userOpenId: "+userOpenId+", userAccessToken: "+userAccessToken);
		}
		
		//TODO 检查请求参数的正确性
		WxProductSkuCriteria criteria = new WxProductSkuCriteria();
		
		List<Integer> skuIdList = new ArrayList<Integer>();
		if(productSkuId!=null&&productSkuId.length>0){//有效的购物数据
			
			Map<Integer, Integer> cartItemMap = new HashMap<Integer, Integer>();
			for(int i=0; i<productSkuId.length; i++){
				skuIdList.add(productSkuId[i]);
				cartItemMap.put(productSkuId[i], buyAmount[i]);
			}
			//加载商品列表
			criteria.createCriteria().andIdIn(skuIdList);
			List<WxProductSku> productSkuList =  wxProductSkuService.queryByCriteria(criteria);
			if(productSkuList!=null&&productSkuList.size()>0){
				double totalFee = 0;
				List<CartProductSku> cartItemList = new ArrayList<CartProductSku>();
				for(WxProductSku productSku: productSkuList){
					int itemBuyAmount = cartItemMap.get(productSku.getId()); 
					//检查订单的有效性（库存等）
					checkOrder(productSku, itemBuyAmount);
					double itemTotalFees = itemBuyAmount*productSku.getPrice(); //金额
					totalFee = totalFee+itemTotalFees;
					cartItemList.add(new CartProductSku(productSku, itemBuyAmount));
				}
				model.addAttribute("cartItemList", cartItemList);
				model.addAttribute("totalFee", totalFee);
			}
			//标记为从购物车购买的
			model.addAttribute("cartBuy", cartBuy);
			
			//获取当前页面url，用于构造地址签名
			String currentUrl = request.getRequestURL().toString()+"&"+request.getQueryString();
			System.out.println("userAccessToken: "+userAccessToken);
			WxOrderAddressJsObj orderAddressJsObj = buildWxOrderAddressJsObj(userAccessToken, currentUrl);
			model.addAttribute("orderAddressJsObj", orderAddressJsObj);
		}
		return "order/buy";
	}
	
	/**
	 * 计算所需的运费
	 * @param model
	 * @param request
	 */
	@NeedAuthorize
	@RequestMapping(value = "/calcDeliverFee.json")
	public ModelAndView calcDeliverFee(Model model, double totalProductFee, int totalAmount, String country, String province, String city, HttpServletRequest request) {
		//计算运费(单位:分)
		double deliveryFee = wxDeliveryTemplateService.calcDeliveryFee(100004, totalProductFee, totalAmount, country, province, city);
		Map<String, Double> dataMap = new HashMap<String, Double>();
		dataMap.put("deliveryFee", deliveryFee);
		return ResponseBuilderUtil.buildJsonView(ResponseBuilderUtil.buildSuccessJson(dataMap));
	}
	
	/**
	 * 确认地址&金额后，提交订单(生成预支付的订单号)，此步之前应该已经获取了商户的地址
	 * @param model
	 * @param request
	 */
	@NeedAuthorize
	@RequestMapping(value = "/submitOrder.json")
	public ModelAndView submitOrder(Model model, int productSkuId[], int buyAmount[], @RequestParam(required = false, defaultValue = "0") long voucherId, @RequestParam(required=false, defaultValue="false")boolean cartBuy, HttpServletRequest request,  HttpServletResponse response) {
		Date currentTime = new Date();
		String userOpenId = (String) request.getAttribute(ConstFront.CURRENT_USER);//获取用户信息
		checkUserOpenId(userOpenId);
		
		WxProductVoucher voucher = null;
		if(voucherId>0){
			//验证优惠券有效性
			voucher = wxProductVoucherService.loadUserVoucherById(userOpenId, voucherId);
			checkUserVoucher(voucher);
		}
		
//		double productTotalFee = 0;
//		int totalBuyAmount = 0;
		//TODO 检查请求参数的正确性
		List<WxProductOrderItem> orderItemList = new ArrayList<WxProductOrderItem>();
		if(productSkuId!=null&&productSkuId.length>0){
			for(int i=0;i<productSkuId.length;i++){
				//加载商品信息
				WxProductSku productSku = wxProductSkuService.loadById(productSkuId[i]);
				//检查订单的有效性
				checkOrder(productSku, buyAmount[i]);
				
				WxProductOrderItem orderItem = new WxProductOrderItem();
				orderItem.setProductId(productSku.getProductId());
				orderItem.setProductSkuId(productSku.getId());
				orderItem.setProductName(productSku.getName());
				orderItem.setProductPicUrl(productSku.getSkuPicUrl());
				orderItem.setItemFee(productSku.getPrice());
				orderItem.setTotalFee(productSku.getPrice() * buyAmount[i]);
				orderItem.setCreateTime(currentTime);
				orderItem.setAmount(buyAmount[i]);
				
				orderItemList.add(orderItem);
			}
		}
		//检查地址的有效性&计算相应的邮费
		WxUserAddress addressInfo = populateAddressInfoFromRequest(request);
		checkDeliveryAddress(addressInfo);
		
		//构造预购买的订单数据
		WxProductOrder productOrder = new WxProductOrder();
		productOrder.setUserOpenId(userOpenId);//用户身份
		
		productOrder.setVoucherId(voucherId);//优惠券id
		
		//提交订单
		int result = wxProductOrderService.createOrder(productOrder, addressInfo, orderItemList);
		if(result>0){
			if(cartBuy){//来自购物车的结算，需要清空购物车
				CartUtil.clearCartCookie(response);
			}
			
			Map<String, String> dataMap = new HashMap<String, String>();
			dataMap.put("tradeNo", productOrder.getOutTradeNo());
			dataMap.put("orderId", String.valueOf(productOrder.getId()));
			return ResponseBuilderUtil.buildJsonView(ResponseBuilderUtil.buildSuccessJson(dataMap));
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
	public String orderInfo(Model model, long orderId, String tradeNo, HttpServletRequest request, HttpServletResponse response) {
		String userOpenId =  (String) request.getAttribute(ConstFront.CURRENT_USER);//获取用户信息
		checkUserOpenId(userOpenId);
		
		//加载商品信息
		WxProductOrder orderInfo = wxProductOrderService.loadByTradeNo(tradeNo);
		model.addAttribute("orderInfo", orderInfo);
		
		//加载订单中商品列表
		List<WxProductOrderItem> orderItemList = wxProductOrderItemService.queryByTradeNo(tradeNo);
		model.addAttribute("orderItemList", orderItemList);
		
		//检查订单状态
		
		boolean notPayed = IWxProductOrderService.StatusEnum.SUBMITED.getStatus() == orderInfo.getStatus();
		if(notPayed){//如果是未付款状态，需要构造支付js对象
			//再开始构造微信所需的订单对象
			String remoteIp = RequestUtil.getRemoteIP(request);
			WxPayItemJsObj wxPayJsObj = buildWxPayJsObj(orderInfo, remoteIp);
			model.addAttribute("wxPayJsObj", wxPayJsObj);
		}
		//需要在页面中增加地址的处理
		return "order/orderInfo";
	}
	
	
	/**
	 * 构造用于支付的js对象
	 * @param clientIp
	 * @return
	 */
	private WxPayItemJsObj buildWxPayJsObj(WxProductOrder orderInfo, String clientIp) {
		String banktype = "WX";
		String productName = orderInfo.getTitle();// 商品名称信息，这里由测试网页填入。
		String fee_type = "1";// 费用类型，这里1为默认的人民币
		String input_charset = "UTF-8";// 字符集，可以使用GBK或者UTF-8
		String notify_url = "http://wxmp.1758.com/wxpay/jspayNotify";// 支付成功后将通知该地址
		String out_trade_no = OrderUtil.generateOrderSn4Wx();// 订单号，商户需要保证该字段对于本商户的唯一性, 长度<32
		String spbill_create_ip = clientIp;// 用户浏览器的ip，这个需要在前端获取
		String product_fee = String.valueOf(orderInfo.getProductFee());// 商品金额
		String transport_fee = String.valueOf(orderInfo.getTransportFee());// 运费
		String total_fee = String.valueOf(orderInfo.getTotalFee());// 总金额

		SortedMap<String, String> packageMap = new TreeMap<String, String>();
		packageMap.put("bank_type", banktype);
		packageMap.put("body", productName);
		packageMap.put("fee_type", fee_type);
		packageMap.put("input_charset", input_charset);
		packageMap.put("notify_url", notify_url);
		packageMap.put("out_trade_no", out_trade_no);
		packageMap.put("spbill_create_ip", spbill_create_ip);
		packageMap.put("total_fee", total_fee);
		packageMap.put("transport_fee", transport_fee);
		packageMap.put("product_fee", product_fee);
		
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
	 * @param stock
	 * @param productSku
	 */
	private void checkOrder(WxProductSku productSku, int buyAmount) {
		if(productSku==null||productSku.getId()==null){
			//加载商品失败
			throw new GeekwayException(ErrorCode.WX_PRODUCT_ORDER_BASIC_PARAM_ERROR);
		}
		if(productSku.getPrice()==null||productSku.getPrice()<=0){
			//商品价格错误，不能购买
			throw new GeekwayException(ErrorCode.WX_PRODUCT_ORDER_BUY_DATA_ERROR);
		}
		if(buyAmount<=0){
			//购买数量无效
			throw new GeekwayException(ErrorCode.WX_PRODUCT_ORDER_BUY_AMOUNT_ERROR);
		}
		if(productSku.getStock()==null||productSku.getStock()<=0||productSku.getStock()<buyAmount){
			//商品库存不足，无法购买
			throw new GeekwayException(ErrorCode.WX_PRODUCT_ORDER_EMPTY_ITEM_ERROR);
		}
	}
	
//	/**
//	 * 计算邮费
//	 * @param productSku
//	 * @param buyAmount
//	 */
//	private double calcDeliveryFee(WxProductSku productSku, HttpServletRequest request) {
//		if(productSku==null||productSku.getId()==null||productSku.getDeliveryTemplateId()==null){
//			//加载商品失败
//			throw new GeekwayException(ErrorCode.WX_PRODUCT_ORDER_BASIC_PARAM_ERROR);
//		}
//		String country = "";
//		String province = request.getParameter("postProvince");
//		String city = request.getParameter("postCity");
//		return wxDeliveryTemplateService.calcDeliveryFee(productSku.getDeliveryTemplateId(), 0, country, province, city);
//	}
	
	/**
	 * 检查地址&计算邮费
	 * @param productSku
	 * @param userAddress
	 * @return
	 */
	private void checkDeliveryAddress(WxUserAddress userAddress) {
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
	 * 验证优惠券的有效性
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
	
	/**
	 * 从request中组装用户地址信息
	 * @param request
	 * @return
	 */
	private WxUserAddress populateAddressInfoFromRequest(HttpServletRequest request) {
		if(request!=null){
			WxUserAddress addressInfo = new WxUserAddress();
			addressInfo.setPostName(request.getParameter("postName"));
			addressInfo.setPostMobile(request.getParameter("postMobile"));
			addressInfo.setPostCode(request.getParameter("postCode"));
			addressInfo.setPostProvince(request.getParameter("postProvince"));
			addressInfo.setPostCity(request.getParameter("postCity"));
			addressInfo.setPostCountries(request.getParameter("postCountries"));
			addressInfo.setPostAddressDetailInfo(request.getParameter("postAddressDetailInfo"));
			addressInfo.setPostNationalCode(request.getParameter("postNationalCode"));
			return addressInfo;
		}
		return null;
	}

	
}