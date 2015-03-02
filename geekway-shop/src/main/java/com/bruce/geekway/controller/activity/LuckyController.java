package com.bruce.geekway.controller.activity;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.bruce.geekway.annotation.NeedAuthorize;
import com.bruce.geekway.annotation.NeedAuthorize.AuthorizeScope;
import com.bruce.geekway.constants.ConstFront;
import com.bruce.geekway.model.WxWebUser;
import com.bruce.geekway.model.exception.CachedException;
import com.bruce.geekway.service.IWxWebUserService;

/**
 * 活动
 * @author liqian
 *
 */
@RequestMapping(value = "/activities/")
@Controller
public class LuckyController{
	
	@Autowired
	private IWxWebUserService wxWebUserService;
	
	private static final Logger logger = LoggerFactory.getLogger(LuckyController.class);
	
	
	@RequestMapping(value = "/loverMatchIntro")
	public String loverMatchIntro(Model model, String friendOpenId, HttpServletRequest request) {
		if(logger.isDebugEnabled()){
			logger.debug("进入情侣匹配度首页");
		}
		if(StringUtils.isNotBlank(friendOpenId)){
			WxWebUser friendWebUser = null;
			try {
				friendWebUser = wxWebUserService.loadCachedByOpenId(friendOpenId);
			} catch (CachedException e) {
				e.printStackTrace();
			}
			if(friendWebUser!=null&&friendWebUser.getId()!=null){
				model.addAttribute("friendWebUser", friendWebUser);
			}
		}
		return "activities/loverMatch/intro";
	}
	
	/**
	 * 匹配度测试
	 * @param model
	 * @param request
	 * @return
	 */
	@NeedAuthorize(AuthorizeScope=AuthorizeScope.WX_SNSAPI_USERINFO)
	@RequestMapping(value = "/loverMatch")
	public String loverMatch(Model model, String friendOpenId, HttpServletRequest request) {
		if(logger.isDebugEnabled()){
			logger.debug("进入情侣匹配度测试页");
		}
		WxWebUser myWebUser = (WxWebUser) request.getAttribute(ConstFront.CURRENT_USER);
		if(myWebUser!=null&&StringUtils.isNotBlank(myWebUser.getOpenId())){
			boolean followed = hasFollowed(myWebUser.getOpenId());//检查用户是否已关注
			if(followed){//关注者才能参加
				if(StringUtils.isNotBlank(friendOpenId)){
					if(!myWebUser.getOpenId().equals(friendOpenId)){//点是的时自己的链接
						WxWebUser friendWebUser = null;
						try {
							friendWebUser = wxWebUserService.loadCachedByOpenId(friendOpenId);
						} catch (CachedException e) {
							e.printStackTrace();
						}
						if(friendWebUser!=null&&friendWebUser.getId()!=null){
							model.addAttribute("friendWebUser", friendWebUser);
//							int luckyNumber = getLoverMatchResult(myWebUser, friendWebUser);
//							model.addAttribute("luckyNumber", luckyNumber);
						}
					}
				}
				return "activities/loverMatch/match";
			}
		}else{
			if(logger.isErrorEnabled()){
				logger.error("用户登录数据异常");
			}
		}
		return "error";
	}
	
	@RequestMapping(value = "/loverMatchResult")
	public String loverMatchResult(Model model, String openId1, String openId2, HttpServletRequest request) {
		if(logger.isDebugEnabled()){
			logger.debug("查看情侣匹配度测试结果");
		}
		WxWebUser wxWebUser1 = null;
		WxWebUser wxWebUser2 = null;
		try {
			wxWebUser1 = wxWebUserService.loadCachedByOpenId(openId1);
			wxWebUser2 = wxWebUserService.loadCachedByOpenId(openId2);
		} catch (CachedException e) {
			e.printStackTrace();
		} 
		if(wxWebUser1!=null&&wxWebUser2!=null){
			model.addAttribute("wxWebUser1", wxWebUser1);
			model.addAttribute("wxWebUser2", wxWebUser2);
			
//				int luckyNumber = getLoverMatchResult(wxWebUser1, wxWebUser2);
//				model.addAttribute("luckyNumber", luckyNumber);
			return "activities/loverMatch/matchResult";
		}
		return "error";
	}
	
//	private boolean matchAvailable(String openId) {
//		return true;
//	}

	
	/**
	 * 情人节抽奖
	 * @param model
	 * @param request
	 * @return
	 */
	@NeedAuthorize(AuthorizeScope=AuthorizeScope.WX_SNSAPI_BASE)
	@RequestMapping(value = "/loverLottery")
	public String loverLottery(Model model, HttpServletRequest request) {
		if(logger.isDebugEnabled()){
			logger.debug("进入抽奖");
		}
		WxWebUser wxWebUser = (WxWebUser) request.getAttribute(ConstFront.CURRENT_USER);
		if(wxWebUser!=null&&StringUtils.isNotBlank(wxWebUser.getOpenId())){
			//检查用户是否已参与过
			boolean available = lotteryAvailable(wxWebUser.getOpenId());
			if(available){
				boolean followed = hasFollowed(wxWebUser.getOpenId());//检查用户是否已关注
				if(followed){//关注者才能参加
					return "lucky/loverLottery";
				}
			}
		}else{
			if(logger.isErrorEnabled()){
				logger.error("用户登录数据异常");
			}
		}
		return "error";
	}
	
	private int getLoverMatchResult(WxWebUser wxWebUser1, WxWebUser wxWebUser2){
		if(wxWebUser1!=null&&StringUtils.isNotBlank(wxWebUser1.getOpenId())&&wxWebUser2!=null&&StringUtils.isNotBlank(wxWebUser2.getOpenId())){
			String openId1=wxWebUser1.getOpenId();
			String openId2=wxWebUser2.getOpenId();
			if(openId1!=null&&openId2!=null){
				List<String> openIdList = Arrays.asList(openId1, openId2);
				Collections.sort(openIdList);
				int hashcode = Math.abs((openId1+openId2).hashCode());
				return hashcode%16;
			}
		}
		return 0;
	}
	

	private boolean hasFollowed(String openId) {
		return true;
	}

	private boolean lotteryAvailable(String openId) {
		return true;
	}
	
}
