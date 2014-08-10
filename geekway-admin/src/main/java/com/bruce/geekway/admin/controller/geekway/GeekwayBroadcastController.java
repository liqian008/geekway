package com.bruce.geekway.admin.controller.geekway;

import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.bruce.geekway.model.WxBroadcast;
import com.bruce.geekway.model.data.JsonResultBean;
import com.bruce.geekway.model.exception.ErrorCode;
import com.bruce.geekway.model.wx.json.response.WxJsonResult;
import com.bruce.geekway.model.wx.message.TextMessage;
import com.bruce.geekway.service.IWxBroadcastService;
import com.bruce.geekway.utils.ConfigUtil;
import com.bruce.geekway.utils.JsonResultBuilderUtil;

/**
 * 消息群发controller
 * @author liqian
 *
 */
@Controller
@RequestMapping("/geekway") 
public class GeekwayBroadcastController {

	private static final String WX_ACCOUNT_TYPE = ConfigUtil.getString("weixinmp_account_type");
	
	
	
	@Autowired
	private IWxBroadcastService wxBroadcastService;
	
	/**
	 * 群发记录列表
	 * @param model
	 * @param request
	 * @return
	 */
	@RequestMapping("/broadcastList")
	public String broadcastList(Model model, HttpServletRequest request) {
		String servletPath = request.getRequestURI();
		model.addAttribute("servletPath", servletPath);
		
		
//		boolean isSenior = false;
		//判断是否是服务号（只有服务号才支持群发功能）
		if("senior".equalsIgnoreCase(WX_ACCOUNT_TYPE)){
			List<WxBroadcast> broadcastList = wxBroadcastService.queryAll();
			model.addAttribute("broadcastList", broadcastList);
			return "geekway/broadcastList";
		}else{
			request.setAttribute("message", "只有服务号才支持群发功能");
			return "forward:/home/operationResult";
		}
	}
	
	/**
	 * 创建群发任务
	 * @param model
	 * @param request
	 * @return
	 */
	@RequestMapping("/broadcastAdd")
	public String broadcastAdd(Model model, WxBroadcast broadcast, HttpServletRequest request) {
		String servletPath = request.getRequestURI();
		model.addAttribute("servletPath", servletPath);
		if("senior".equalsIgnoreCase(WX_ACCOUNT_TYPE)){
			model.addAttribute("broadcast", broadcast);
			return "geekway/broadcastEdit";
		}else{
			request.setAttribute("message", "只有服务号才支持群发功能");
			return "forward:/home/operationResult";
		}
		
	}
	
	@ResponseBody
	@RequestMapping(value = "/mpBroadcastText.json", method = RequestMethod.POST)
	public JsonResultBean mpBroadcastArticle(Model model, String content, HttpServletRequest request) {
		return broadcastMaterialMedia(0, 0, content);
	}
	
	@ResponseBody
	@RequestMapping(value = "/mpBroadcastArticle.json", method = RequestMethod.POST)
	public JsonResultBean mpBroadcastArticle(Model model, int materialId, HttpServletRequest request) {
		return broadcastMaterialMedia(1, materialId, null);
	}
	
	@ResponseBody
	@RequestMapping(value = "/mpBroadcastNews.json", method = RequestMethod.POST)
	public JsonResultBean mpBroadcastNews(Model model, int materialId, HttpServletRequest request) {
		return broadcastMaterialMedia(2, materialId, null);
	}
	
	@ResponseBody
	@RequestMapping(value = "/mpBroadcastImage.json", method = RequestMethod.POST)
	public JsonResultBean mpBroadcastImage(Model model, int materialId, HttpServletRequest request) {
		return broadcastMaterialMedia(3, materialId, null);
	}
	
	/**
	 * 群发消息
	 * @param model
	 * @param broadcast
	 * @param request
	 * @return
	 */
	private JsonResultBean broadcastMaterialMedia(int materialType, int materialId, String content) {
		if("senior".equalsIgnoreCase(WX_ACCOUNT_TYPE)){
			//TODO 检查每月发送<4次
			boolean canBroadcast = true;
			if(!canBroadcast){
				//超过本月群发次数（4次），无法再次广播
				return JsonResultBuilderUtil.buildErrorJson(ErrorCode.WX_BROADCAST_OVERLOAD);
			}else{
				if(materialType==0){//群发文本
					wxBroadcastService.broadcastMaterialArticle(materialId);
				}else if(materialType==1){//群发单图文
					wxBroadcastService.broadcastMaterialNews(materialId);
				}else if(materialType==2){//群发多图文
					wxBroadcastService.broadcastMaterialNews(materialId);
				}else if(materialType==3){//群发图片
					wxBroadcastService.broadcastMaterialImage(materialId);
				}
			}
		}else{
			//只有服务号才支持群发功能
			return JsonResultBuilderUtil.buildErrorJson(ErrorCode.WX_BROADCAST_NEED_SENIOR);
		}
		//"微信群发消息失败"
		return JsonResultBuilderUtil.buildErrorJson(ErrorCode.SYSTEM_ERROR);
		
	}
	
	
}
