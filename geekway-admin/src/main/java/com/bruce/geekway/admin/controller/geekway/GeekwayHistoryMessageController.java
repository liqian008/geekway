package com.bruce.geekway.admin.controller.geekway;

import java.util.HashMap;
import java.util.List;
import java.util.Map; 

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.bruce.geekway.model.WxHistoryMessage;
import com.bruce.geekway.model.WxHistoryMessageCriteria;
import com.bruce.geekway.model.WxMpUser;
import com.bruce.geekway.service.IWxHistoryMessageService;
import com.bruce.geekway.service.IWxMpUserService;
import com.bruce.geekway.utils.DateUtil;

/**
 * 消息群发controller
 * @author liqian
 *
 */
@Controller
@RequestMapping("/geekway") 
public class GeekwayHistoryMessageController {

	@Autowired
	private IWxHistoryMessageService wxHistoryMessageService;
	@Autowired
	private IWxMpUserService wxMpUserService;
	
	/**
	 * 历史消息列表
	 * @param model
	 * @param request
	 * @return
	 */
	@RequestMapping("/historyMessageList")
	public String historyMessageList(Model model, HttpServletRequest request) {
		String servletPath = request.getRequestURI();
		model.addAttribute("servletPath", servletPath);
		
//		List<WxHistoryMessage> historyMessageList = wxHistoryMessageService.queryAll(" id desc");
		
		WxHistoryMessageCriteria criteria = new WxHistoryMessageCriteria();
		WxHistoryMessageCriteria.Criteria subCriteria = criteria.createCriteria();
		subCriteria.andInboxEqualTo((short) 0);
		//TODO 时间范围
		//TODO 可视消息
		criteria.setOrderByClause(" id desc");
		List<WxHistoryMessage> historyMessageList = wxHistoryMessageService.queryByCriteria(criteria);
		
		if(historyMessageList!=null&&historyMessageList.size()>0){
			//获取个人资料
			Map<String, WxMpUser> userMap = new HashMap<String, WxMpUser>();
			for(WxHistoryMessage message: historyMessageList){
				String userOpenId = message.getOpenId();
				if(!StringUtils.isBlank(userOpenId)){
					WxMpUser mpUser = userMap.get(userOpenId);//取缓存对象中的对象
					if(mpUser==null){//用户对象未被缓存，需要从db中获取
						mpUser = wxMpUserService.loadByOpenId(userOpenId);
					}
					if(mpUser!=null){
						message.setMpUser(mpUser);
					}
				}
			}
			model.addAttribute("historyMessageList", historyMessageList);
		}
		return "geekway/historyMessageList";
	}
	
	/**
	 * 用户的对话消息
	 * @param model
	 * @param request
	 * @return
	 */
	@RequestMapping("/historyMessageDialog")
	public String historyMessageDialog(Model model, String openId,  HttpServletRequest request) {
		String servletPath = request.getRequestURI();
		model.addAttribute("servletPath", servletPath);
		
		WxMpUser chatUser = wxMpUserService.loadByOpenId(openId);
		if(chatUser!=null){
			model.addAttribute("openId", openId);
			
			//检查是否可以给用户回复消息
			boolean customReply = false;
			WxHistoryMessage userLatestMessage = wxHistoryMessageService.queryUserLastestMessage(openId);
			if(userLatestMessage!=null&& (System.currentTimeMillis() - userLatestMessage.getSendTime().getTime() < DateUtil.TIME_UNIT_DAY*2) ){
				//距离用户上次回复48小时内，才能进行回复
				customReply = true;
			}
			model.addAttribute("customReply", customReply);
			
			List<WxHistoryMessage> userMessageList = wxHistoryMessageService.queryAll(" id desc");
			model.addAttribute("userMessageList", userMessageList);
			return "geekway/historyMessageDialog";
		}else{
			request.setAttribute("message", "选定用户不存在");
			return "forward:/home/operationResult";
		}
	}
	
}
