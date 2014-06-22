package com.bruce.geekway.controller.ito;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.bruce.geekway.model.ItoProductBg;
import com.bruce.geekway.model.ItoSystemStatus;
import com.bruce.geekway.service.ito.IItoProductBgService;
import com.bruce.geekway.service.ito.IItoSystemStatusService;
import com.bruce.geekway.utils.JsonResultBuilderUtil;
import com.bruce.geekway.utils.JsonViewBuilderUtil;


/**
 * 系统状态监察
 * @author liqian
 *
 */
@Controller
@RequestMapping(value={"api"})
public class ItoController {
	
	private static final Logger logger = LoggerFactory.getLogger("ItoAppLogger");
	
	
	@Autowired
	private IItoSystemStatusService itoSystemStatusService;
	@Autowired
	private IItoProductBgService itoProductBgService;
	
	/**
	 * 系统状态
	 * @param model
	 * @return
	 */
	@RequestMapping(value = "/running.json")
	public ModelAndView running(String deviceId) {
		
		logger.info("App客户端["+deviceId+"]发送运行状态");
		
		ItoSystemStatus systemStatus=  new ItoSystemStatus();
		Date currentTime = new Date();
		systemStatus.setDeviceId(deviceId);
		systemStatus.setCreateTime(currentTime);
		int result =  itoSystemStatusService.save(systemStatus);
		if(result>0){
			return JsonViewBuilderUtil.SUBMIT_SUCCESS_VIEW;
		}
		return JsonViewBuilderUtil.SUBMIT_FAILED_VIEW;
	}
	
	
	/**
	 * 图片配置列表，2为支付宝，3为ap支付
	 * @param model
	 * @return
	 */
	@RequestMapping(value = "/productBgList.json")
	public ModelAndView productList() {
		
		logger.info("App客户端获取图片配置数据");
		
		List<ItoProductBg> productBgList = itoProductBgService.queryAll();
		
		if(productBgList!=null&&productBgList.size()>0){
			for(int i=productBgList.size()-1;i>=0;i--){
				ItoProductBg productBg = productBgList.get(i);
				if(productBg.getId()!=null&&productBg.getId()==1){
					productBgList.remove(productBg);
				}
			}
		}
		//返回数据
		Map<String, Object> dataMap = new HashMap<String, Object>();
		dataMap.put("productBgList", productBgList);
		return JsonViewBuilderUtil.buildJsonView(JsonResultBuilderUtil.buildSuccessJson(dataMap));
	}
}
