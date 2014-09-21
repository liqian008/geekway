package com.bruce.geekway.controller.wx;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.SortedMap;
import java.util.TreeMap;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.bruce.geekway.constants.ConstWeixin;
import com.bruce.geekway.model.WxProduct;
import com.bruce.geekway.model.WxProductCategory;
import com.bruce.geekway.model.WxProductSku;
import com.bruce.geekway.model.WxProductTag;
import com.bruce.geekway.model.WxSkuPropValue;
import com.bruce.geekway.model.exception.ErrorCode;
import com.bruce.geekway.model.wx.pay.WxPayItemJsObj;
import com.bruce.geekway.service.product.IWxProductCategoryService;
import com.bruce.geekway.service.product.IWxProductService;
import com.bruce.geekway.service.product.IWxProductSkuService;
import com.bruce.geekway.service.product.IWxProductTagService;
import com.bruce.geekway.service.product.IWxSkuPropValueService;
import com.bruce.geekway.utils.DateUtil;
import com.bruce.geekway.utils.JsonUtil;
import com.bruce.geekway.utils.OrderUtil;
import com.bruce.geekway.utils.ProductHtmlUtils;
import com.bruce.geekway.utils.RequestUtil;
import com.bruce.geekway.utils.ResponseBuilderUtil;
import com.bruce.geekway.utils.WxAuthUtil;

@Controller
public class WxProductController {
	
	@Autowired
	private IWxProductCategoryService wxProductCategoryService;
	@Autowired
	private IWxProductTagService wxProductTagService;
	@Autowired
	private IWxProductService wxProductService;
	@Autowired
	private IWxProductSkuService wxProductSkuService;
	@Autowired
	private IWxSkuPropValueService wxSkuPropValueService;
	
	private static final Logger logger = LoggerFactory.getLogger(WxProductController.class);

	
	/**
	 * 首页
	 * @param model
	 * @param request
	 * @return
	 */
	@RequestMapping(value = {"/","/index"})
	public String index(Model model, HttpServletRequest request) {
		return "product/index";
	}
	
	/**
	 * 根据分类加载商品
	 * @param model
	 * @param request
	 * @return
	 */
	@RequestMapping(value = "products/c-{categoryId}")
	public String productListByCategory(Model model, @PathVariable int categoryId, HttpServletRequest request) {
		WxProductCategory productCategory = wxProductCategoryService.loadById(categoryId);
		model.addAttribute("productCategory", productCategory);
		return "product/productListByCategory";
	}
	
	/**
	 * 根据Tag加载商品
	 * @param model
	 * @param request
	 * @return
	 */
	@RequestMapping(value = "products/t-{tagId}")
	public String productListByTag(Model model, @PathVariable int tagId, HttpServletRequest request) {
		WxProductTag productTag = wxProductTagService.loadById(tagId);
		model.addAttribute("productTag", productTag);
		return "product/productListByTag";
	}
	
	
	/**
	 * 根据商品分类列出商品
	 * @param model
	 * @param request
	 * @return
	 */
	@RequestMapping(value = "moreProducts.json")
	public ModelAndView moreProducts(HttpServletRequest request, @RequestParam("categoryId") int categoryId, @RequestParam("tailId") int tailId) {
	    if(logger.isDebugEnabled()){
            logger.debug("ajax加载更多专辑，tailId: "+tailId);
        }
	    int limit = 2;
		List<WxProductSku> productSkuList = null;
	    if(logger.isDebugEnabled()){
            logger.debug("根据商品分类查询");
        }
		productSkuList = wxProductSkuService.fallLoadCategoryProductSkuList(categoryId, tailId, limit + 1);

		int nextTailId = 0;
		if (productSkuList == null || productSkuList.size() == 0) {
			if (logger.isDebugEnabled()) {
				logger.debug("无更多商品");
			}
			return ResponseBuilderUtil.buildJsonView(ResponseBuilderUtil.buildErrorJson(ErrorCode.SYSTEM_NO_MORE_DATA));
		} else {
			if (productSkuList.size() > limit) {// 查询数据超过limit，含分页内容
				// 移除最后一个元素
				productSkuList.remove(limit);
				nextTailId = productSkuList.get(limit - 1).getProductId();//取productId
				if(logger.isDebugEnabled()){
	                logger.debug("还有更多商品，tailId： "+nextTailId);
	            }
			}
			String productListHtml = ProductHtmlUtils.buildFallLoadHtml(productSkuList);
			Map<String, String> dataMap = new HashMap<String, String>();
			dataMap.put("html", productListHtml);
			dataMap.put("tailId", String.valueOf(nextTailId));
			return ResponseBuilderUtil.buildJsonView(ResponseBuilderUtil.buildSuccessJson(dataMap));
		}
	}
	
	
	/**
	 * 商品详细信息
	 * @param model
	 * @param request
	 * @return
	 */
	@RequestMapping(value = "/product/{productId}")
	public String productInfo(Model model, @PathVariable int productId, HttpServletRequest request) {
		return productInfo(model, productId, 0, request);
	}
	
	/**
	 * 商品详细信息
	 * @param model
	 * @param request
	 * @return
	 */
	@RequestMapping(value = "/product/{productId}/${productSkuId}")
	public String productInfo(Model model, @PathVariable int productId, @PathVariable int productSkuId, HttpServletRequest request) {
		WxProduct product = wxProductService.loadById(productId);
		model.addAttribute("product", product);
		
		WxProductSku currentProductSku = null;
		
		//加载productSku，用于构造json的map，使得前端切换时取出相应的数据
		List<WxProductSku> productSkuList = wxProductSkuService.queryAllByProductId(productId);
		if(productSkuList!=null&&productSkuList.size()>0){
			SortedMap<String, String> productSkuMap = new TreeMap<String, String>();
			for(WxProductSku productSku: productSkuList){
				//置空无用的字段（json序列化时不需要）
				productSku.setCreateTime(null);
				productSku.setUpdateTime(null);
				productSku.setDescription(null);
				
				//设定第一条或选定的那条数据为current
				if((productSkuId==0 && currentProductSku==null)||(productSkuId>0 && productSkuId == productSku.getId())){
					currentProductSku = productSku;//当前指定的sku商品
				}
				String key = productSku.getPropertiesName();
				String value = JsonUtil.gson.toJson(productSku);
				productSkuMap.put(key, value);
			}
			model.addAttribute("productSkuMap", productSkuMap);

			//解析当前sku商品对应的属性值，以便在前端高亮显示
			if(currentProductSku!=null){
				String property = currentProductSku.getPropertiesName();
				Map<String, String> selectedSkuValueMap = parseSkuProperties(property);
				model.addAttribute("selectedSkuValueMap", selectedSkuValueMap);
			}
		}
		
		//获取该商品sku属性值列表，展示以便用户选择
		List<WxSkuPropValue> skuPropValueList =  wxSkuPropValueService.querySkuPropValueListByProductId(productId);
		//计算该商品的sku矩阵
		if(skuPropValueList!=null&&skuPropValueList.size()>0){
			Map<Integer, List<WxSkuPropValue>> skuGroupMap = new TreeMap<Integer, List<WxSkuPropValue>>();
			for(WxSkuPropValue skuPropValue: skuPropValueList){
				int skuPropId = skuPropValue.getSkuPropId();
				List<WxSkuPropValue> skuPropValueGroupList = skuGroupMap.get(skuPropId);
				if(skuPropValueGroupList==null || skuPropValueGroupList.size()<=0){//不存在则初始化（默认值1）
					skuPropValueGroupList = new ArrayList<WxSkuPropValue>();
					skuGroupMap.put(skuPropId, skuPropValueGroupList);
				} 
				skuPropValueGroupList.add(skuPropValue);
			}
			model.addAttribute("skuGroupMap", skuGroupMap);
		}
		
		
		return "product/productInfo";
	}

	private Map<String, String> parseSkuProperties(String property) {
		Map<String, String> skuValueMap = new TreeMap<String, String>();
		String[] skuCodeArray = property.split(";");
		if(skuCodeArray!=null&&skuCodeArray.length>0){
			for(String skuCode: skuCodeArray){
				String[] skuCodeValueArray = skuCode.split(":");
				if(skuCodeValueArray!=null&&skuCodeValueArray.length==2){
					skuValueMap.put(skuCodeValueArray[0], skuCodeValueArray[1]);
				}
			}
		}
		return skuValueMap;
	}
	
	
	
	
	/**
	 * 确认订单（此步需要用户选择邮寄地址）
	 * @param model
	 * @param productId
	 * @param request
	 * @return
	 */
	@RequestMapping(value = "/confirmOrder")
	public String confirmOrder(Model model, int productId,  HttpServletRequest request) {
		//加载商品信息
		WxProduct productInfo = wxProductService.loadById(productId);
		//需要在页面中增加地址的处理
		return "product/confirmOrder";
	}
	
	
	/**
	 * 确认地址&金额后，提交订单(生成预支付的订单号)，此步之前应该已经获取了商户的地址
	 * @param model
	 * @param request
	 */
	@RequestMapping(value = "/submitOrder")
	public String submitOrder(Model model, int productId,  HttpServletRequest request) {
		//加载商品信息
		WxProduct productInfo = wxProductService.loadById(productId);
		//检查商品状态
		boolean valid = true;
		if(valid){
			//先根据订单内容生成本地的预支付订单对象
			
			
			//再开始构造微信所需的订单对象
			String remoteIp = RequestUtil.getRemoteIP(request);
			WxPayItemJsObj wxPayJsObj = buildWxPayJsObj(remoteIp);
			model.addAttribute("wxPayJsObj", wxPayJsObj);
		}
		return "product/orderConfirm";
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
		//无需传入appid和appkey，签名算法中会自动填充
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
		itemJsObj.setSignType("SHA1");
		itemJsObj.setPaySign(paySign);
		return itemJsObj;
	}

//	/**
//	 * 生成微信支付的package
//	 * @return
//	 */
//	private String getWxPayPackage(SortedMap<String, String> paramMap) {
//		//首先第一步：对原串进行签名，注意这里不要对任何字段进行编码。这里是将参数按照key=value进行字典排序后组成下面的字符串,在这个字符串最后拼接上key=XXXX。由于这里的字段固定，因此只需要按照这个顺序进行排序即可。
//		//文档描述： 对所有传入参数按照字段名的 ASCII 码从小到大排序(字典序) 后,使用 URL 键值 对的格式(即 key1=value1&key2=value2...)拼接成字符串 string1,注意:值为空的参数不参与签名;
//		String packageText = WxAuthUtil.formatWxPayPackageText(paramMap);//得到string1
//		//文档描述： 在string1 最后拼接上key=paternerKey得到stringSignTemp 字符串,并对stringSignTemp进行md5运算 ,再将得到的字符串所有字符转换为大写,得到sign值signValue。
//		packageText = packageText+"&key="+ConstWeixin.WX_PAY_SIGN_KEY;
//		String md5Package = Md5Util.md5Encode(packageText).toUpperCase();//得到signValue
//		//然后第二步，对每个参数进行url转码
//
//		//然后进行最后一步，这里按照key＝value除了sign外进行字典序排序后组成下列的字符串,最后再串接sign=value
//		String encodedPackage = WxAuthUtil.formatWxPayUrlEncodeText(paramMap, true);
//		String finalPackage = encodedPackage + "&sign=" + md5Package;
//		return finalPackage;
//	}
//	
//	
//	/**
//	 * 生成微信支付的paysign
//	 * @return
//	 */
//	private String getWxPaySign(String packageText) {
//		String timestamp = String.valueOf(DateUtil.getUnixTime(new Date()));
//		String noncestr = timestamp;
//		
//		//第一步，对所有需要传入的参数加上appkey作一次key＝value字典序的排序
//		TreeMap<String, String> paramMap = new TreeMap<String, String>();
//		paramMap.put("appid", ConstWeixin.WX_APP_ID);
//		paramMap.put("appkey", ConstWeixin.WX_PAY_SIGN_KEY);
//		paramMap.put("noncestr", noncestr);
//		paramMap.put("package", packageText);
//		paramMap.put("timestamp", timestamp);
//		
//		String signText = WxAuthUtil.formatWxPayPackageText(paramMap);
//		
//		String finalSign = Sha1Util.getSha1(signText);
//		return finalSign;
//	}
	
	
}
