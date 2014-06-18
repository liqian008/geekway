package com.bruce.geekway.controller.ito;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.math.NumberUtils;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.bruce.geekway.model.ito.json.ItoWwjQrcodeResult;
import com.bruce.geekway.utils.JsonUtil;
import com.bruce.geekway.utils.JsonViewBuilderUtil;
import com.bruce.geekway.utils.WxHttpUtil;

/**
 * 二维码的图片
 * @author liqian
 *
 */
@Controller
@RequestMapping(value={"ito"})
public class QrcodeNewController {
	
	/*用户请求二维码的次数*/
	private static final String KEY_USER_APPLIED_TIME = "user_appled_time";
//	/*用户qrcode二维码数组的key*/
//	private static final String KEY_USER_QRCODES = "user_qrcodes";
	
	/*用户关注后生成的二维码key*/
	private static final String KEY_USER_SURSCRIBE_QRCODE = "user_subscribe_qrcode";
	/*用户注册后生成的二维码key*/
	private static final String KEY_USER_REGISTER_QRCODE = "user_register_qrcode";
	
	
	/**
	 * 
	 * @param model
	 * @param request
	 * @return
	 */
	@RequestMapping(value = "/gameQrcodes")
	public String gameQrcodes(Model model, HttpServletRequest request, HttpServletResponse response) {
		String subscribedQrcodeUrl = null;
		String regedQrcodeUrl = null;
		
		//检查cookie中的
		Cookie[] cookies = request.getCookies();
		if(cookies!=null&&cookies.length>0){
			for(Cookie cookie: cookies){
				if(KEY_USER_SURSCRIBE_QRCODE.equals(cookie.getName())){
					String qrcodesStr = cookie.getValue();
					if(!StringUtils.isBlank(qrcodesStr)){//有数据
						subscribedQrcodeUrl = qrcodesStr;
					}
				}
				
				if(KEY_USER_REGISTER_QRCODE.equals(cookie.getName())){
					String qrcodesStr = cookie.getValue();
					if(!StringUtils.isBlank(qrcodesStr)){//有数据
						regedQrcodeUrl = qrcodesStr;
					}
				}
			}
		}
		if(StringUtils.isBlank(subscribedQrcodeUrl)){//创建新cookie
			subscribedQrcodeUrl = getQrcodeUrl();
			if(subscribedQrcodeUrl!=null){
				//重新写入cookie
				Cookie cookie = new Cookie(KEY_USER_SURSCRIBE_QRCODE, subscribedQrcodeUrl);
				cookie.setMaxAge(999999999);
				response.addCookie(cookie);
			}
		}
		
		model.addAttribute("subscribedQrcodeUrl", subscribedQrcodeUrl);
		model.addAttribute("regedQrcodeUrl", regedQrcodeUrl);
		return "ito/lotteryQrcode/qrcodes";
	}
	
	
	/**
	 * 注册完成后奖励的二维码
	 * @param model
	 * @param request
	 * @return
	 */
	@RequestMapping(value = "/regedQrcode")
	public String regedQrcode(Model model, HttpServletRequest request, HttpServletResponse response) {
		String regedQrcodeUrl = null;
//		boolean newRegister = false;
		//检查cookie中的
		Cookie[] cookies = request.getCookies();
		if(cookies!=null&&cookies.length>0){
			for(Cookie cookie: cookies){
				if(KEY_USER_REGISTER_QRCODE.equals(cookie.getName())){
					String qrcodesStr = cookie.getValue();
					if(!StringUtils.isBlank(qrcodesStr)){//注册后奖励的二维码为空
						regedQrcodeUrl = qrcodesStr;
					}
				}
			}
		}
		
		if(StringUtils.isBlank(regedQrcodeUrl)){//创建新cookie
			regedQrcodeUrl = getQrcodeUrl();
			if(regedQrcodeUrl!=null){
				//重新写入cookie
				Cookie cookie = new Cookie(KEY_USER_REGISTER_QRCODE, regedQrcodeUrl);
				cookie.setMaxAge(999999999);
				response.addCookie(cookie);
			}
		}
		
		model.addAttribute("regedQrcodeUrl", regedQrcodeUrl);
		return "ito/lotteryQrcode/regedQrcode";
	}
	
	
	/**
	 * 
	 * @return
	 */
	private static String getQrcodeUrl(){
		boolean isTest = false;
		if(isTest){
			return getMockQrcodeUrl();
		}
		
		//从第三方服务获取二维码&展示给用户
		String qrcodeUrl = null;
		
		String wwjUrl = "http://itocases.eicp.net:8733/design_time_addresses/qrgamecontrollerservice/qrurl";
		String result = WxHttpUtil.sendGetRequest(wwjUrl, null);
		if(!StringUtils.isBlank(result)){
			ItoWwjQrcodeResult wwjResult = JsonUtil.gson.fromJson(result, ItoWwjQrcodeResult.class);
			if(wwjResult!=null&&wwjResult.getErrorCode()==0){
				qrcodeUrl = wwjResult.getImageUrl();
			}
		}
		return qrcodeUrl;
	}

	
	private static String getMockQrcodeUrl(){
		return "http://qr.liantu.com/api.php?text=29|40|10|74|C8|D8|4E|B3|88|44|9B|06|42|8F|7C|18|CF|DF|F1|81|70|4E|4C|05|E8|CC|56|77|ED|22|12|E4|E0|D7|9F|77|36|06|5E|1A";
	}
	
}
