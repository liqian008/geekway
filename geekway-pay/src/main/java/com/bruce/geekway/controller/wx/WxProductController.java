package com.bruce.geekway.controller.wx;

import java.util.Date;
import java.util.SortedMap;
import java.util.TreeMap;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.bruce.geekway.constants.ConstWeixin;
import com.bruce.geekway.service.product.IWxProductService;
import com.bruce.geekway.utils.DateUtil;
import com.bruce.geekway.utils.Md5Util;
import com.bruce.geekway.utils.Sha1Util;
import com.bruce.geekway.utils.WxAuthUtil;

/**
 * Handles requests for the application home page.
 */
@Controller
@RequestMapping("/wx")
public class WxProductController {
	
	@Autowired
	private IWxProductService wxProductService;
	
	/**
	 * 产品信息
	 * @param model
	 * @param request
	 * @return
	 */
	@RequestMapping(value = "/productInfo")
	public String productInfo(Model model, HttpServletRequest request) {
		return "wx/productInfo";
	}
	
	/**
	 * 提交订单
	 * @param model
	 * @param request
	 */
	@RequestMapping(value = "/postOrder", method=RequestMethod.POST)
	public String postOrder(Model model, int productId,  HttpServletRequest request) {
		//加载产品信息
		//WxPayProduct productInfo = wxProductService.loadById(productId);
		//检查产品状态
		boolean valid = true;
		if(valid){
			
			SortedMap<String, String> paramMap = new TreeMap<String, String>();
			
			String wxPayPackageText = getWxPayPackage(paramMap);
			String wxPaySign = getWxPaySign(wxPayPackageText);
			String timestamp = String.valueOf(DateUtil.getUnixTime(new Date()));
			String nonceStr = timestamp;
			
			model.addAttribute("appId", ConstWeixin.WX_APP_ID);
			model.addAttribute("timeStamp", timestamp);
			model.addAttribute("nonceStr", nonceStr);
			model.addAttribute("package", wxPayPackageText);
			model.addAttribute("signType", "sha1");
			model.addAttribute("paySign", wxPaySign);
		}
		return "wxpay/wxJsPay";
	}
	
	/**
	 * 生成微信支付的package
	 * @return
	 */
	private String getWxPayPackage(SortedMap<String, String> paramMap) {
		//首先第一步：对原串进行签名，注意这里不要对任何字段进行编码。这里是将参数按照key=value进行字典排序后组成下面的字符串,在这个字符串最后拼接上key=XXXX。由于这里的字段固定，因此只需要按照这个顺序进行排序即可。
		//文档描述： 对所有传入参数按照字段名的 ASCII 码从小到大排序(字典序) 后,使用 URL 键值 对的格式(即 key1=value1&key2=value2...)拼接成字符串 string1,注意:值为空的参数不参与签名;
		String packageText = WxAuthUtil.combineWxPayPackageText(paramMap);//得到string1
		//文档描述： 在string1 最后拼接上key=paternerKey得到stringSignTemp 字符串,并对stringSignTemp进行md5运算 ,再将得到的字符串所有字符转换为大写,得到sign值signValue。
		packageText = packageText+"&key="+ConstWeixin.WX_PAY_SIGN_KEY;
		String md5Package = Md5Util.md5Encode(packageText).toUpperCase();//得到signValue
		//然后第二步，对每个参数进行url转码

		//然后进行最后一步，这里按照key＝value除了sign外进行字典序排序后组成下列的字符串,最后再串接sign=value
		String encodedPackage = WxAuthUtil.combineWxPayUrlEncodeText(paramMap, true);
		String finalPackage = encodedPackage + "&sign=" + md5Package;
		return finalPackage;
	}
	
	
	/**
	 * 生成微信支付的paysign
	 * @return
	 */
	private String getWxPaySign(String packageText) {
		String timestamp = String.valueOf(DateUtil.getUnixTime(new Date()));
		String noncestr = timestamp;
		
		//第一步，对所有需要传入的参数加上appkey作一次key＝value字典序的排序
		TreeMap<String, String> paramMap = new TreeMap<String, String>();
		paramMap.put("appid", ConstWeixin.WX_APP_ID);
		paramMap.put("appkey", ConstWeixin.WX_PAY_SIGN_KEY);
		paramMap.put("noncestr", noncestr);
		paramMap.put("package", packageText);
		paramMap.put("timestamp", timestamp);
		
		String signText = WxAuthUtil.combineWxPayPackageText(paramMap);
		
		String finalSign = Sha1Util.getSha1(signText);
		return finalSign;
	}
	
	
}
