package com.bruce.geekway.controller.wx;

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
import com.bruce.geekway.annotation.NeedAuthorize.AuthorizeStrategy;
import com.bruce.geekway.constants.ConstFront;
import com.bruce.geekway.model.WxWebUser;
import com.bruce.geekway.service.mp.WxMpOauthService;
import com.bruce.geekway.service.product.IProductOrderService;
import com.bruce.geekway.service.product.IProductService;
import com.bruce.geekway.service.product.IProductVoucherService;

/**
 * 个人订单
 * @author liqian
 *
 */
@RequestMapping(value = "/lucky")
@Controller
public class LuckyController{
	
	@Autowired
	private IProductService productService;
	@Autowired
	private WxMpOauthService wxMpOauthService;
	@Autowired
	private IProductVoucherService productVoucherService;
	@Autowired
	private IProductOrderService productOrderService;
	
	private static final Logger logger = LoggerFactory.getLogger(WxProductController.class);
	
	
	/**
	 * 匹配度测试
	 * @param model
	 * @param request
	 * @return
	 */
	@NeedAuthorize(AuthorizeScope=AuthorizeScope.WX_SNSAPI_USERINFO, authorizeStrategy=AuthorizeStrategy.COOKIE_DENY)
	@RequestMapping(value = "/loverMatch")
	public String loverMatch(Model model, HttpServletRequest request) {
		if(logger.isDebugEnabled()){
			logger.debug("进入情侣匹配度测试");
		}
		WxWebUser wxWebUser = (WxWebUser) request.getAttribute(ConstFront.CURRENT_USER);
		if(wxWebUser!=null&&StringUtils.isNotBlank(wxWebUser.getOpenId())){
			//检查用户是否已参与过
			boolean available = matchAvailable(wxWebUser.getOpenId());
			if(available){
				boolean followed = hasFollowed(wxWebUser.getOpenId());//检查用户是否已关注
				if(followed){//关注者才能参加
					return "lucky/loverMatch";
				}
			}else{//加载详情信息
				
			}
		}else{
			if(logger.isErrorEnabled()){
				logger.error("用户登录数据异常");
			}
		}
		return "error";
	}
	
	private boolean matchAvailable(String openId) {
		return true;
	}

	
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

	private boolean hasFollowed(String openId) {
		return true;
	}

	private boolean lotteryAvailable(String openId) {
		return true;
	}
	
}
