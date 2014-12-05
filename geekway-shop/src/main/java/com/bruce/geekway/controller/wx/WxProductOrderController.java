package com.bruce.geekway.controller.wx;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
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
import com.bruce.foundation.util.JsonUtil;
import com.bruce.geekway.annotation.NeedAuthorize;
import com.bruce.geekway.annotation.NeedAuthorize.AuthorizeScope;
import com.bruce.geekway.annotation.NeedAuthorize.AuthorizeStrategy;
import com.bruce.geekway.constants.ConstFront;
import com.bruce.geekway.constants.ConstPay;
import com.bruce.geekway.constants.ConstWeixin;
import com.bruce.geekway.model.WxProductCart.CartProductSku;
import com.bruce.geekway.model.WxProductOrder;
import com.bruce.geekway.model.WxProductOrderItem;
import com.bruce.geekway.model.WxProductSku;
import com.bruce.geekway.model.WxProductSkuCriteria;
import com.bruce.geekway.model.WxProductVoucher;
import com.bruce.geekway.model.WxUserAddress;
import com.bruce.geekway.model.WxWebUser;
import com.bruce.geekway.model.enumeration.GeekwayEnum;
import com.bruce.geekway.model.exception.ErrorCode;
import com.bruce.geekway.model.exception.GeekwayException;
import com.bruce.geekway.model.wx.json.response.WxUserInfoResult;
import com.bruce.geekway.model.wx.pay.WxOrderAddressJsObj;
import com.bruce.geekway.model.wx.pay.WxPayItemJsObj;
import com.bruce.geekway.service.IWxWebUserService;
import com.bruce.geekway.service.mp.WxMpOauthService;
import com.bruce.geekway.service.product.IWxDeliveryTemplateService;
import com.bruce.geekway.service.product.IWxProductOrderItemService;
import com.bruce.geekway.service.product.IWxProductOrderService;
import com.bruce.geekway.service.product.IWxProductService;
import com.bruce.geekway.service.product.IWxProductSkuService;
import com.bruce.geekway.service.product.IWxProductVoucherService;
import com.bruce.geekway.service.product.IWxUserAddressService;
import com.bruce.geekway.utils.CartUtil;
import com.bruce.geekway.utils.RequestUtil;
import com.bruce.geekway.utils.ResponseBuilderUtil;
import com.bruce.geekway.utils.ResponseUtil;
import com.bruce.geekway.utils.ShopLinkUtil;
import com.bruce.geekway.utils.WxAuthUtil;
import com.bruce.geekway.utils.WxShareUtil;

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
	private IWxWebUserService wxWebUserService;
	@Autowired
	private IWxUserAddressService wxUserAddressService;
	@Autowired
	private IWxDeliveryTemplateService wxDeliveryTemplateService;
	@Autowired
	private WxMpOauthService wxMpOauthService;
	
	private static final Logger logger = LoggerFactory.getLogger(WxProductOrderController.class);

	
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
	public String buy(Model model, @RequestParam(required=false)String code, int productSkuId[], int buyAmount[], @RequestParam(required=false, defaultValue="0")int cartBuy, HttpServletRequest request, HttpServletResponse response) {
//		String userOpenId = (String) request.getAttribute(ConstFront.CURRENT_USER);
		
		WxWebUser wxWebUser = (WxWebUser) request.getAttribute(ConstFront.CURRENT_USER);
		
		//userAccessToken，用于获取微信的共享地址address
		String userAccessToken = (String) request.getAttribute(ConstFront.CURRENT_USER_ACCESS_TOKEN);
		if(logger.isDebugEnabled()){
			logger.debug("进入[购买信息页面] code: "+code+", debug模式: "+ConstWeixin.WX_OAUTH_DEBUG);
			logger.debug("进入[购买信息页面] userOpenId: "+wxWebUser.getOpenId()+", userAccessToken: "+userAccessToken);
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
	public ModelAndView submitOrder(Model model, int productSkuId[], int buyAmount[], 
			@RequestParam(required=false, defaultValue="0")int cartBuy, 
			@RequestParam(required=false, defaultValue="1")boolean selfPay, 
			HttpServletRequest request,  HttpServletResponse response) {
		Date currentTime = new Date();
		WxWebUser wxWebUser = (WxWebUser) request.getAttribute(ConstFront.CURRENT_USER);
		String userOpenId = wxWebUser.getOpenId();
//		String userUnionId = wxWebUser.getUnionId();
		checkUserOpenId(userOpenId);
//		checkUserOpenId(userUnionId);
		
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
				orderItem.setProductPicUrl(productSku.getSkuPic1Url());
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
		productOrder.setUserOpenId(userOpenId);//下单人的用户身份
//		productOrder.setUserUnionId(userUnionId);//下单人的unionId
		
//		productOrder.setVoucherId(null);//不使用优惠券
		
		//提交订单
		int result = wxProductOrderService.createOrder(productOrder, addressInfo, orderItemList);
		if(result>0){
			if(cartBuy==1){//来自购物车的结算，需要清空购物车
				CartUtil.clearCartCookie(response);
			}
			
			Map<String, Object> dataMap = new HashMap<String, Object>();
			if(selfPay){//如果是自己支付的
				//立刻构造微信所需的支付JS对象，供用户支付
				String remoteIp = RequestUtil.getRemoteIP(request);
				WxPayItemJsObj wxPayJsObj = buildWxPayJsObj(productOrder, remoteIp);
				dataMap.put("wxPayJsObj", wxPayJsObj);
				dataMap.put("tradeNo", productOrder.getOutTradeNo());
				dataMap.put("payer", "self");//自己支付
			}else{//找人代付
				dataMap.put("tradeNo", productOrder.getOutTradeNo());
				dataMap.put("payer", "diaosi");//找吊死备胎支付
			}
			return ResponseBuilderUtil.buildJsonView(ResponseBuilderUtil.buildSuccessJson(dataMap));
		}
		return ResponseBuilderUtil.buildJsonView(ResponseBuilderUtil.buildErrorJson(ErrorCode.WX_PRODUCT_ORDER_CREATE_ERROR));
	}

	
	/**
	 * 订单详情
	 * @param model 
	 * @param tradeNo
	 * @param request
	 * @return
	 */
	@NeedAuthorize
	@RequestMapping(value = "/orderInfo")
	public String orderInfo(Model model, String tradeNo, HttpServletRequest request, HttpServletResponse response) {
		WxWebUser wxWebUser = (WxWebUser) request.getAttribute(ConstFront.CURRENT_USER);
		String userOpenId = wxWebUser.getOpenId();
		checkUserOpenId(userOpenId);
		
		//加载商品信息
		WxProductOrder orderInfo = wxProductOrderService.loadByUserTradeNo(userOpenId, tradeNo);
		model.addAttribute("orderInfo", orderInfo);
		
		//加载订单中商品列表
		List<WxProductOrderItem> orderItemList = wxProductOrderItemService.queryByTradeNo(tradeNo);
		model.addAttribute("orderItemList", orderItemList);
		
		//检查订单状态
		boolean notPayed = GeekwayEnum.ProductOrderStatusEnum.SUBMITED.getStatus() == orderInfo.getStatus();
		if(notPayed){//如果是未付款状态，需要构造支付js对象(与在buy流程中不同，为保证速度，不在页面中使用ajax构造，而是直接在controller中就构造好)
			String remoteIp = RequestUtil.getRemoteIP(request);
			WxPayItemJsObj wxPayJsObj = buildWxPayJsObj(orderInfo, remoteIp);
			model.addAttribute("wxPayJsObj", wxPayJsObj);
		}
		return "order/orderInfo";
	}
	
	
	/**
	 * 准备分享让土豪购买的请求（需使用scope为SNSAPI_USERINFO，以加载下单人的昵称&头像）
	 * @param model
	 * @param tradeNo
	 * @param request
	 * @return
	 */
	@NeedAuthorize(AuthorizeScope=AuthorizeScope.WX_SNSAPI_USERINFO, authorizeStrategy=AuthorizeStrategy.COOKIE_DENY)
	@RequestMapping(value = "/orderInfoShare")
	public String orderInfoShare(Model model, String tradeNo, HttpServletRequest request, HttpServletResponse response) {
		WxWebUser wxWebUser = (WxWebUser) request.getAttribute(ConstFront.CURRENT_USER);
		String userOpenId = wxWebUser.getOpenId();
		checkUserOpenId(userOpenId);
		
		//userAccessToken，用于获取个人资料（昵称&头像）
		String userAccessToken = (String) request.getAttribute(ConstFront.CURRENT_USER_ACCESS_TOKEN);
		logger.debug("orderInfoShare中的accessToken: "+ userAccessToken);
		//oauth获取个人资料
		WxUserInfoResult wxUserInfoResult = wxMpOauthService.getOAuthUserinfo(userAccessToken, userOpenId);
		logger.debug("oauth wxUserInfoResult: "+ wxUserInfoResult);
		
		if(wxUserInfoResult!=null&&wxUserInfoResult.getErrcode()==0){//正确的响应 
			wxWebUser = buildOAuthWebUser(wxUserInfoResult);

			if(wxWebUser!=null){
				//重写cookie
				String webUserCookie = JsonUtil.gson.toJson(wxWebUser);
				try {
					webUserCookie = URLEncoder.encode(webUserCookie, "utf-8");
				} catch (UnsupportedEncodingException e) {
					throw new GeekwayException(ErrorCode.SYSTEM_ERROR);
				}
				logger.debug("userCookie json: "+ webUserCookie);
				
				//用户表saveOrUpdate
				wxWebUserService.save(wxWebUser);
				
				ResponseUtil.addCookie(response, ConstFront.COOKIE_KEY_WX_USER, webUserCookie);
				request.setAttribute(ConstFront.CURRENT_USER, wxWebUser);
				
				//加载订单信息
				WxProductOrder orderInfo = wxProductOrderService.loadByUserTradeNo(userOpenId, tradeNo);
				
				//TODO 检查商品完整性
				model.addAttribute("orderInfo", orderInfo);
				
				//加载订单中商品列表
				List<WxProductOrderItem> orderItemList = wxProductOrderItemService.queryByTradeNo(tradeNo);
				model.addAttribute("orderItemList", orderItemList);
				
				return "order/orderInfoShare";
			}
		}
		throw new GeekwayException(ErrorCode.SYSTEM_ERROR);
	}
	
	/**
	 * oauth返回webUser对象
	 * @param wxUserInfoResult
	 * @return
	 */
	private WxWebUser buildOAuthWebUser(WxUserInfoResult wxUserInfoResult) {
		WxWebUser webUser = new WxWebUser();
		webUser.setOpenId(wxUserInfoResult.getOpenid());
		webUser.setUnionId(wxUserInfoResult.getUnionid());
		webUser.setNickname(wxUserInfoResult.getNickname());
		webUser.setCountry(wxUserInfoResult.getCountry());
		webUser.setProvince(wxUserInfoResult.getProvince());
		webUser.setCity(wxUserInfoResult.getCity());
		webUser.setHeadImgUrl(wxUserInfoResult.getHeadimgurl());
		webUser.setSex(wxUserInfoResult.getSex());
		
		return webUser;
	}

	/**
	 * 代付条款页面，分享后回流的url，在此知会当前登录用户是给别人付款（点击确定后，才会进入真正的支付页）
	 * @param model
	 * @param tradeNo
	 * @param request
	 * @return
	 */
	@NeedAuthorize
	@RequestMapping(value = "/payForAngleTerms")
	public String payForAngleTerms(Model model, String tradeNo, HttpServletRequest request, HttpServletResponse response) {
		WxWebUser hostWebUser = (WxWebUser) request.getAttribute(ConstFront.CURRENT_USER);
		String hostOpenId = hostWebUser.getOpenId();
		checkUserOpenId(hostOpenId);
		
		//TODO 参数签名，用于保护订单不被泄漏
		
		//加载订单信息
		WxProductOrder orderInfo = wxProductOrderService.loadByTradeNo(tradeNo);
		request.setAttribute("orderInfo", orderInfo);
		
		boolean isHost = hostOpenId.equals(orderInfo.getUserOpenId());
		if(isHost){//如果是自己打开的
			//则直接转到订单详情页
			return ResponseUtil.getRedirectString("orderInfo?tradeNo="+orderInfo.getOutTradeNo());
		}else{//非自己的订单（代付订单，需要替别人结账）
			//查询下单用户信息（页面进行提示，避免购买错误）
			WxWebUser orderOwnerWebUser = wxWebUserService.loadByOpenId(orderInfo.getUserOpenId()); 
			request.setAttribute("orderOwnerWebUser", orderOwnerWebUser);//展示在页面上，以提醒用户付款对象
			
			return "order/payForAngleTerms";
		}
		
	}
	
	
	/**
	 * 进入代付订单页面，查看的是女神的订单信息
	 * @param model
	 * @param tradeNo
	 * @param request
	 * @return
	 */
	@NeedAuthorize(AuthorizeScope=AuthorizeScope.WX_SNSAPI_USERINFO)
	@RequestMapping(value = "/payForAngle")
	public String payForAngle(Model model, String tradeNo, HttpServletRequest request, HttpServletResponse response) {
		WxWebUser wxWebUser = (WxWebUser) request.getAttribute(ConstFront.CURRENT_USER);
		String hostOpenId = wxWebUser.getOpenId();
		checkUserOpenId(hostOpenId);
		
		//加载订单信息
		WxProductOrder orderInfo = wxProductOrderService.loadByTradeNo(tradeNo);
		
		boolean isHost = hostOpenId.equals(orderInfo.getUserOpenId());
		if(isHost){//如果是自己打开的
			//则直接转到订单详情页
			return ResponseUtil.getRedirectString("orderInfo?tradeNo="+orderInfo.getOutTradeNo());
		}else{//非自己的订单（代付订单，需要替别人结账）
			//查询下单用户信息（页面进行提示，避免购买错误）

			//检查订单状态（未支付时才可以支付）
			short orderStatus = orderInfo.getStatus();
			if(GeekwayEnum.ProductOrderStatusEnum.SUBMITED.getStatus()!=orderStatus){
				//来晚了，订单已经被人抢着支付了
				//TODO throw new GeekwayException(errorCode);
			}
			//TODO 增加超时限制（n天以上的不准代付？）
			
			//检查商品完整性
			model.addAttribute("orderInfo", orderInfo);
			
			//查询下单用户信息
			WxWebUser orderOwnerWebUser = wxWebUserService.loadByOpenId(orderInfo.getUserOpenId()); 
			request.setAttribute("orderOwnerWebUser", orderOwnerWebUser);//展示在页面上，以提醒用户付款对象
			
			
			//加载订单中商品列表
			List<WxProductOrderItem> orderItemList = wxProductOrderItemService.queryByTradeNo(tradeNo);
			model.addAttribute("orderItemList", orderItemList);
			
			//未付款状态，需要构造支付js对象
			String remoteIp = RequestUtil.getRemoteIP(request);
			WxPayItemJsObj wxPayJsObj = buildWxPayJsObj(orderInfo, remoteIp);
			model.addAttribute("wxPayJsObj", wxPayJsObj);
			
			return "order/payForAngle";
		}
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
		String notify_url = ConstPay.NOTIFY_URL_JS_WXPAY;;// 支付成功后将通知该地址
		String out_trade_no = orderInfo.getOutTradeNo();// 订单号，商户需要保证该字段对于本商户的唯一性, 长度<32
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
