package com.bruce.geekway.admin.controller.geekway;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.bruce.geekway.constants.ConstWeixin;
import com.bruce.geekway.service.IWxCommandService;

/**
 * 普通指令controller
 * @author liqian
 *
 */
@Controller
@RequestMapping("/geekway") 
public class GeekwaySetingsController {

	@Autowired
	private IWxCommandService wxCommandService;
	
	
	/**
	 * 接入配置
	 * @param model
	 * @param request
	 * @return
	 */
	@RequestMapping("/wxSettings")
	public String wxSettings(Model model, HttpServletRequest request) {
		String servletPath = request.getRequestURI();
		model.addAttribute("servletPath", servletPath);
		
		model.addAttribute("devmodeUrl", ConstWeixin.WX_DEV_MODE_URL);
		model.addAttribute("devmodeToken", ConstWeixin.WX_DEV_MODE_TOKEN);//接入的token
		model.addAttribute("qrcodeUrl", ConstWeixin.WX_MP_ACCOUNT_QRCODE_URL);
		
		//账户的名称，头像以及唯一接入key
		
		//该账户的appkey，secretkey
		
		//该账户支付的paysignKey及财付通账户信息等
		
		//选择前端模板风格
		
		return "geekway/wxSettings";
	}
	
}
