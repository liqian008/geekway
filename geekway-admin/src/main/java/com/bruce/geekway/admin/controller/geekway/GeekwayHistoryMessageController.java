package com.bruce.geekway.admin.controller.geekway;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.bruce.geekway.constants.ConstWeixin;
import com.bruce.geekway.model.WxHistoryMessage;
import com.bruce.geekway.model.WxHistoryMessageCriteria;
import com.bruce.geekway.model.WxUser;
import com.bruce.geekway.service.IWxHistoryMessageService;
import com.bruce.geekway.service.IWxUserService;
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
	private IWxUserService userrService;
	
	/**
	 * 历史消息列表
	 * @param model
	 * @param interval 间隔天数
	 * @param request
	 * @return
	 */
	@RequestMapping("/historyMessageList")
	public String historyMessageList(Model model, @RequestParam(defaultValue="1")int interval, HttpServletRequest request) {
		String servletPath = request.getRequestURI();
		model.addAttribute("servletPath", servletPath);
		
		model.addAttribute("interval", interval);
		Date queryTimeFrom = DateUtil.calcDatetime(new Date(), 0-interval);
		
		WxHistoryMessageCriteria criteria = new WxHistoryMessageCriteria();
		WxHistoryMessageCriteria.Criteria subCriteria = criteria.createCriteria();
		subCriteria.andInboxEqualTo((short) 0).andIsSystemMsgEqualTo((short) 0).andSentTimeGreaterThan(queryTimeFrom);
		criteria.setOrderByClause(" id desc");
		List<WxHistoryMessage> historyMessageList = wxHistoryMessageService.queryByCriteria(criteria);
		
		if(historyMessageList!=null&&historyMessageList.size()>0){
			//获取个人资料
			Map<String, WxUser> userMap = new HashMap<String, WxUser>();
			for(WxHistoryMessage historyMessage: historyMessageList){
				String userOpenId = historyMessage.getOpenId();
				if(!StringUtils.isBlank(userOpenId)){
					WxUser mpUser = userMap.get(userOpenId);//取缓存对象中的对象
					if(mpUser==null){//用户对象未被缓存，需要从db中获取
						mpUser = userrService.loadByOpenId(userOpenId);
					}
					if(mpUser!=null){
						historyMessage.setUser(mpUser);
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
	public String historyMessageDialog(Model model, @RequestParam(defaultValue="1")int interval, String openId,  HttpServletRequest request) {
		String servletPath = request.getRequestURI();
		model.addAttribute("servletPath", servletPath);
		
		
		model.addAttribute("interval", interval);
		Date queryTimeFrom = DateUtil.calcDatetime(new Date(), 0-interval);
		
		WxUser chatUser = userrService.loadByOpenId(openId);
		if(chatUser!=null){
			
			model.addAttribute("chatUser", chatUser);
			model.addAttribute("accountDefaultAvatar", ConstWeixin.DEFAULT_WEIXIN_ACCOUNT_AVATAR_URL);
			
			//检查是否可以给用户回复消息
			boolean customReply = false;
			WxHistoryMessage userLatestMessage = wxHistoryMessageService.queryUserLastestMessage(openId);
			if(userLatestMessage!=null&& (System.currentTimeMillis() - userLatestMessage.getSentTime().getTime() < DateUtil.TIME_UNIT_DAY*2) ){
				//距离用户上次回复48小时内，才能进行回复
				customReply = true;
			}
			model.addAttribute("customReply", customReply);
			
			
			
			WxHistoryMessageCriteria criteria = new WxHistoryMessageCriteria();
			WxHistoryMessageCriteria.Criteria subCriteria = criteria.createCriteria();
			subCriteria.andIsSystemMsgEqualTo((short) 0).andOpenIdEqualTo(openId).andSentTimeGreaterThan(queryTimeFrom);
			criteria.setOrderByClause(" id desc");
			List<WxHistoryMessage> userMessageList = wxHistoryMessageService.queryByCriteria(criteria);
			
//			List<WxHistoryMessage> userMessageList = wxHistoryMessageService.queryAll(" id desc");
			model.addAttribute("userMessageList", userMessageList);
			return "geekway/historyMessageDialog";
		}else{
			request.setAttribute("message", "选定用户不存在");
			return "forward:/home/operationResult";
		}
	}
	
}
