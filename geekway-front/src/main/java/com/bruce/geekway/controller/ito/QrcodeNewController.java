package com.bruce.geekway.controller.ito;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang3.BooleanUtils;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.bruce.geekway.model.WxDefaultReply;
import com.bruce.geekway.model.ito.json.ItoWwjQrcodeResult;
import com.bruce.geekway.service.ito.IItoQrcodeService;
import com.bruce.geekway.utils.JsonUtil;
import com.bruce.geekway.utils.JsonViewBuilderUtil;
import com.bruce.geekway.utils.ResponseBuilderUtil;
import com.bruce.geekway.utils.WxHttpUtil;

/**
 * 二维码的图片
 * @author liqian
 *
 */
@Controller
@RequestMapping(value={"ito"})
public class QrcodeNewController {

	private static final Logger logger = LoggerFactory.getLogger("ItoQrcodeLogger");
	
	/*用户请求二维码的次数*/
	private static final String KEY_USER_APPLIED_TIME = "user_appled_time";
//	/*用户qrcode二维码数组的key*/
//	private static final String KEY_USER_QRCODES = "user_qrcodes";
	
	/*用户关注后生成的二维码key*/
	private static final String KEY_USER_SURSCRIBE_QRCODE = "user_subscribe_qrcode";
	/*用户注册后生成的二维码key*/
	private static final String KEY_USER_REGISTER_QRCODE = "user_register_qrcode";
	
	/*用户关注新天地分店后生成的二维码key*/
	private static final String KEY_USER_SURSCRIBE_XTD_QRCODE = "user_subscribe_xtd_qrcode";

	@Autowired
	private IItoQrcodeService itoQrcodeService;
	
	/**
	 * 发放的二维码处理（第一家分店）
	 * @param model
	 * @param request
	 * @return
	 */
	@RequestMapping(value = "/gameQrcodes")
	public String gameQrcodes(Model model, HttpServletRequest request, HttpServletResponse response) {
		boolean alreadyReged = false;//默认为未注册
		String subscribedQrcodeUrl = null;
		String regedQrcodeUrl = null;
		
		//检查cookie中的
		Cookie[] cookies = request.getCookies();
		if(cookies!=null&&cookies.length>0){
			for(Cookie cookie: cookies){
				//检查登录与否
				if(ItoUserController.KEY_USER_REGISTERED.equals(cookie.getName())){
					String userRegisterStr = cookie.getValue();
					if(!StringUtils.isEmpty(userRegisterStr)){//注册过
						alreadyReged = true;
					}
					continue;
				}
				
				if(KEY_USER_SURSCRIBE_QRCODE.equals(cookie.getName())){
					String qrcodesStr = cookie.getValue();
					if(!StringUtils.isBlank(qrcodesStr)){//有数据
						subscribedQrcodeUrl = qrcodesStr;
						continue;
					}
				}
				
				if(KEY_USER_REGISTER_QRCODE.equals(cookie.getName())){
					String qrcodesStr = cookie.getValue();
					if(!StringUtils.isBlank(qrcodesStr)){//有数据
						regedQrcodeUrl = qrcodesStr;
						continue;
					}
				}
			}
		}
		
		//改用ajax方式load图片
		
		model.addAttribute("alreadyReged", alreadyReged);//是否注册过
		model.addAttribute("subscribedQrcodeUrl", subscribedQrcodeUrl);
		model.addAttribute("regedQrcodeUrl", regedQrcodeUrl);
		return "ito/lotteryQrcode/qrcodes";
	}
	
	
	
	/**
	 * 新天地分店的二维码发放
	 * @param model
	 * @param request
	 * @return
	 */
	@RequestMapping(value = "/gameQrcodesXtd")
	public String gameQrcodesXtd(Model model, HttpServletRequest request, HttpServletResponse response) {
		logger.info("新天地店接入请求");
		String subscribedQrcodeUrl = null;
		
		//检查cookie中的
		Cookie[] cookies = request.getCookies();
		if(cookies!=null&&cookies.length>0){
			for(Cookie cookie: cookies){
				//TODO 修改cookie的key
				if(KEY_USER_SURSCRIBE_XTD_QRCODE.equals(cookie.getName())){
					String qrcodesStr = cookie.getValue();
					if(!StringUtils.isBlank(qrcodesStr)){//有数据
						subscribedQrcodeUrl = qrcodesStr;
						continue;
					}
				}
			}
		}
		//
		model.addAttribute("subscribedQrcodeUrl", subscribedQrcodeUrl);
		return "ito/lotteryQrcode/qrcodesXtd";
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
		model.addAttribute("regedQrcodeUrl", regedQrcodeUrl);
		return "ito/lotteryQrcode/regedQrcode";
	}
	
	
	
	/**
	 * ajax方式获取关注第一家分店时的二维码
	 * @param model
	 * @return
	 */
	@RequestMapping(value = "/getSubscribedQrcode.json")
	public ModelAndView getSubscribedQrcode(Model model, HttpServletRequest request, HttpServletResponse response) {
		Boolean isDebug = (Boolean)request.getSession().getAttribute(ItoController.DEBUG_FLAG);
		String subscribeQrcodeUrl = itoQrcodeService.getQrcodeUrl(BooleanUtils.isTrue(isDebug));
		if(subscribeQrcodeUrl!=null){
			//重新写入cookie
			Cookie cookie = new Cookie(KEY_USER_SURSCRIBE_QRCODE, subscribeQrcodeUrl);
			cookie.setMaxAge(999999999);
			response.addCookie(cookie);
			//返回成功响应
			Map<String, String> dataMap = new HashMap<String, String>();
			dataMap.put("subscribeQrcodeUrl", subscribeQrcodeUrl);
			return JsonViewBuilderUtil.buildJsonView(ResponseBuilderUtil.buildSuccessJson(dataMap));
		}
		return JsonViewBuilderUtil.SUBMIT_FAILED_VIEW;
	}
	
	
	/**
	 * ajax方式获取关注新天地分店时的二维码
	 * @param model
	 * @return
	 */
	@RequestMapping(value = "/getSubscribedXtdQrcode.json")
	public ModelAndView getSubscribedXtdQrcode(Model model, HttpServletRequest request, HttpServletResponse response) {
		Boolean isDebug = (Boolean)request.getSession().getAttribute(ItoController.DEBUG_FLAG);
		String subscribeQrcodeUrl = itoQrcodeService.getQrcodeUrl(BooleanUtils.isTrue(isDebug));
		if(subscribeQrcodeUrl!=null){
			//重新写入cookie
			Cookie cookie = new Cookie(KEY_USER_SURSCRIBE_XTD_QRCODE, subscribeQrcodeUrl);
			cookie.setMaxAge(999999999);
			response.addCookie(cookie);
			//返回成功响应
			Map<String, String> dataMap = new HashMap<String, String>();
			dataMap.put("subscribeQrcodeUrl", subscribeQrcodeUrl);
			return JsonViewBuilderUtil.buildJsonView(ResponseBuilderUtil.buildSuccessJson(dataMap));
		}
		return JsonViewBuilderUtil.SUBMIT_FAILED_VIEW;
	}
	
	
	
	
	/**
	 * ajax方式获取注册后奖励的二维码
	 * @param model
	 * @return
	 */
	@RequestMapping(value = "/getRegedQrcode.json")
	public ModelAndView getRegedQrcode(Model model, HttpServletRequest request, HttpServletResponse response) {
		Boolean isDebug = (Boolean)request.getSession().getAttribute(ItoController.DEBUG_FLAG);
		String regedQrcodeUrl = itoQrcodeService.getQrcodeUrl(BooleanUtils.isTrue(isDebug));
		if(regedQrcodeUrl!=null){
			//重新写入cookie
			Cookie cookie = new Cookie(KEY_USER_REGISTER_QRCODE, regedQrcodeUrl);
			cookie.setMaxAge(999999999);
			response.addCookie(cookie);
			//返回成功响应
			Map<String, String> dataMap = new HashMap<String, String>();
			dataMap.put("regedQrcodeUrl", regedQrcodeUrl);
			return JsonViewBuilderUtil.buildJsonView(ResponseBuilderUtil.buildSuccessJson(dataMap));
		}
		return JsonViewBuilderUtil.SUBMIT_FAILED_VIEW;
	}
	
//	/**
//	 * 
//	 * @return
//	 */
//	private String getQrcodeUrl(boolean isDebug){
//		String qrcodeUrl = null;
////		WxDefaultReply wxDefaultReply = wxDefaultReplyService.
//		
//		WxDefaultReply defaultReply = wxDefaultReplyService.loadById(1);
//		
//		boolean isOpen = defaultReply!=null&&Short.valueOf("99").equals(defaultReply.getStatus());//99时开启二维码
//		if(isOpen&&!isDebug){
//			boolean isTest = false; 
//			if(isTest){
//				return getMockQrcodeUrl();
//			}
//			//从第三方服务获取二维码&展示给用户
//			String wwjUrl = "http://www.itocases.com:90/api/QRCode";
//			String result = WxHttpUtil.getRequest(wwjUrl, null);
//			if(!StringUtils.isBlank(result)){
//				ItoWwjQrcodeResult wwjResult = JsonUtil.gson.fromJson(result, ItoWwjQrcodeResult.class);
//				if(wwjResult!=null&&wwjResult.getErrorCode()==0){
//					qrcodeUrl = wwjResult.getImageUrl();
//					logger.info("从发码中心获取新二维码成功!["+qrcodeUrl+"]");
//				}
//			}else{
//				logger.error("从发码中心获取新二维码失败!");
//			}
//		}
//		return qrcodeUrl;
//	}

	
	/**
	 * 消费二维码的回调
	 * @param model
	 * @return
	 */
	@RequestMapping(value = "/qrcodeConsumeNotify")//, method=RequestMethod.POST
	public ModelAndView qrcodeConsumeNotify(String qrcodeKey, String qrcodeUrl, String sign, HttpServletRequest request) {
		
		logger.info("发码中心回调的消费参数。["+request.getParameterMap()+"]");
		logger.info("发码中心回调的消费记录。["+qrcodeKey+"]["+qrcodeUrl+"]["+sign+"]");
		//TODO 检查签名
		//更新二维码使用状态
		int result = 1; 
		if(result>0){
			return JsonViewBuilderUtil.SUBMIT_SUCCESS_VIEW;
		}
		return JsonViewBuilderUtil.SUBMIT_FAILED_VIEW;
	}
	
	
	
	private static String getMockQrcodeUrl(){
		return "http://qr.liantu.com/api.php?text=29|40|10|74|C8|D8|4E|B3|88|44|9B|06|42|8F|7C|18|CF|DF|F1|81|70|4E|4C|05|E8|CC|56|77|ED|22|12|E4|E0|D7|9F|77|36|06|5E|1A";
	}
	
}
