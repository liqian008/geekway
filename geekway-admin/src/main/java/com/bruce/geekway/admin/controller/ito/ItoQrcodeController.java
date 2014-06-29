package com.bruce.geekway.admin.controller.ito;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.bruce.geekway.model.ItoProductBg;
import com.bruce.geekway.model.ItoSlider;
import com.bruce.geekway.model.ito.json.ItoWwjQrcodeResult;
import com.bruce.geekway.service.ito.IItoProductBgService;
import com.bruce.geekway.utils.JsonUtil;
import com.bruce.geekway.utils.WxHttpUtil;

/**
 * 补生成二维码
 * @author liqian
 *
 */
@Controller
@RequestMapping("/ito")
public class ItoQrcodeController {


	@RequestMapping("/qrcodeInput")
	public String qrcodeInput(Model model, HttpServletRequest request) {
		String servletPath = request.getRequestURI();
		model.addAttribute("servletPath", servletPath);
		return "ito/qrcodeInput";
	}
	
	/**
	 * 补救生成二维码
	 * @param model
	 * @param request
	 * @param number
	 * @return
	 */
	@RequestMapping(value="/displayQrcodes", method=RequestMethod.POST)
	public String displayQrcodes(Model model, HttpServletRequest request, @RequestParam(required=true) int number) {
		String servletPath = request.getRequestURI();
		model.addAttribute("servletPath", servletPath);
		
		List<String> qrcodeList = new ArrayList<String>();
		for(int i=0;i<number;i++){
			try {
				String qrcodeUrl = getQrcodeUrl();
				qrcodeList.add(qrcodeUrl);
			} catch (Exception e) {
				break;
			}
		}
		model.addAttribute("qrcodeList", qrcodeList);
		return "ito/qrcodeList";
	}
	
	
	/**
	 * 
	 * @return
	 * @throws Exception 
	 */
	private static String getQrcodeUrl() throws Exception{
		//从第三方服务获取二维码&展示给用户
		String qrcodeUrl = null;
		String wwjUrl = "http://itocases.eicp.net:8733/design_time_addresses/qrgamecontrollerservice/qrurl";
		String result = WxHttpUtil.sendGetRequest(wwjUrl, null);
		if(!StringUtils.isBlank(result)){
			ItoWwjQrcodeResult wwjResult = JsonUtil.gson.fromJson(result, ItoWwjQrcodeResult.class);
			if(wwjResult!=null&&wwjResult.getErrorCode()==0){
				qrcodeUrl = wwjResult.getImageUrl();
			}
		}else{
			throw new Exception();
		}
		
		return qrcodeUrl;
	}
}
