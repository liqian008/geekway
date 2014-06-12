package com.bruce.geekway.controller.ito;

import java.util.Date;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.math.NumberUtils;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.bruce.geekway.model.ItoSystemStatus;
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
public class QrcodeController {
	
	/*用户请求二维码的次数*/
	private static final String USER_APPLIED_TIME = "user_appled_time";
	
	/**
	 * 
	 * @param model
	 * @param request
	 * @return
	 */
	@RequestMapping(value = "/lotteryQrcode")
	public String lotteryQrcode(Model model, HttpServletRequest request, HttpServletResponse response) {
		int userApplyedTime = 0;
		//检查cookie中的
		Cookie[] cookies = request.getCookies();
		if(cookies!=null&&cookies.length>0){
			for(Cookie cookie: cookies){
				if(USER_APPLIED_TIME.equals(cookie.getName())){
					userApplyedTime = NumberUtils.toInt(cookie.getValue(), 0);
					System.out.println("======cookied userApplyedTime==="+userApplyedTime);
				}
			}
		}
		
		//重新写入cookie
		userApplyedTime++;
		Cookie cookie = new Cookie(USER_APPLIED_TIME, String.valueOf(userApplyedTime));
		cookie.setMaxAge(999999999);
		response.addCookie(cookie);
		
		if(userApplyedTime>2){//2次免费次数已经用光
			model.addAttribute("errorMsg", "对不起，您的两次免费游戏机会已全部用完，感谢您的参与！");
			return "ito/lotteryQrcode/error";
		}else{
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
			model.addAttribute("qrcodeUrl", qrcodeUrl);
			model.addAttribute("userApplyedTime", userApplyedTime);
			return "ito/lotteryQrcode/success";
		}
	}
	
	
	/**
	 * 消费二维码的回调
	 * @param model
	 * @return
	 */
	@RequestMapping(value = "/qrcodeConsumeNotify")//, method=RequestMethod.POST
	public ModelAndView qrcodeConsumeNotify(String qrcodeKey, String qrcodeUrl, String sign) {
		//TODO 检查签名
		
		int result = 1;//itoSystemStatusService.save(systemStatus); 
		if(result>0){
			return JsonViewBuilderUtil.SUBMIT_SUCCESS_VIEW;
		}
		return JsonViewBuilderUtil.SUBMIT_FAILED_VIEW;
	}
	
}
