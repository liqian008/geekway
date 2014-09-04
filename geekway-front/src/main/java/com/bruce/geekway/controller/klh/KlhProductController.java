package com.bruce.geekway.controller.klh;


import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.bruce.geekway.model.KlhProduct;
import com.bruce.geekway.model.KlhUserProfile;
import com.bruce.geekway.model.KlhUserScoreLog;
import com.bruce.geekway.service.klh.IKlhProductService;
import com.bruce.geekway.service.klh.IKlhUserScoreLogService;
import com.bruce.geekway.utils.DateUtil;
import com.bruce.geekway.utils.KlhUtil;

/**
 * Handles requests for the application home page.
 */
@Controller
@RequestMapping(value={"klh"})
public class KlhProductController {
	
	@Autowired
	private IKlhProductService klhProductService;
	@Autowired
	private IKlhUserScoreLogService klhUserScoreLogService;
	
	/**
	 * 兑换产品列表
	 * @param model
	 * @return
	 */
	@RequestMapping(value = "/scoreProductList")
	public String productList(Model model, HttpServletRequest request) {
//		if(!KlhUtil.sessionValid(request)){// 页面流程
//			//TODO 跳转auth界面
//			return KlhUtil.redirectToOauth(model);
//		}
		
		
		List<KlhProduct> productList =  klhProductService.queryAvailableProducts();
		if(productList!=null&&productList.size()>0){
			model.addAttribute("productList", productList);
		}
		return "klh/scoreProductList";
	}
	
	/**
	 * 兑换产品详情
	 * @param model
	 * @return
	 */
	@RequestMapping(value = "/scoreProductInfo")
	public String productInfo(Model model, int productId, HttpServletRequest request) {
//		if(!KlhUtil.sessionValid(request)){// 页面流程
//			//TODO 跳转auth界面
//			return KlhUtil.redirectToOauth(model);
//		}
		
		KlhProduct productInfo = klhProductService.loadById(productId);
		if(productInfo!=null&&productInfo.getId()>0){
			model.addAttribute("productInfo", productInfo);
			
			KlhUserProfile sessionUserProfile = (KlhUserProfile) request.getSession().getAttribute("sessionUserProfile");
			int userScore = klhUserScoreLogService.queryCurrentScoreByUserOpenId(sessionUserProfile.getUserOpenId());
			model.addAttribute("userScore", userScore);
		}
		return "klh/scoreProductInfo";
	}
	
	
//	/**
//	 * 确认兑换物品（输入邮寄地址等）
//	 * @param model
//	 * @return
//	 */
//	@RequestMapping(value = "/applyInfo", method=RequestMethod.POST)
//	public String applyInfo(Model model, int productId, HttpServletRequest request) {
//		if(!KlhUtil.sessionValid(request)){// 页面流程
//			//TODO 跳转auth界面
//			return KlhUtil.redirectToOauth(model);
//		}
//		//TODO 检查用户积分
//		KlhProduct productInfo = klhProductService.loadById(productId);
//		if(productInfo!=null&&productInfo.getId()>0&&productInfo.getStatus()!=null&&productInfo.getStatus()==1){//兑换产品可用
//			Integer leftNum = productInfo.getLeftNum();//库存数
//			if(leftNum!=null&&leftNum>0){
//				model.addAttribute("productInfo", productInfo);
//				return "klh/applyInfo";
//			}else{
//				//库存不足，不可兑换
//			}
//		}
//		return "";
//	}
	
	/**
	 * 兑换产品
	 * @param model
	 * @return
	 */
	@RequestMapping(value = "/productApply", method=RequestMethod.POST)
	public synchronized String productApply(Model model, int productId, String postName, String postMobile, String postAddress, String postCode, HttpServletRequest request) {
		if(!KlhUtil.sessionValid(request)){// 页面流程
			//TODO 跳转auth界面
			return KlhUtil.redirectToOauth(model);
		}
		
		//TODO 扣减用户积分
		KlhProduct productInfo = klhProductService.loadById(productId);
		if(productInfo!=null&&productInfo.getId()>0&&productInfo.getStatus()!=null&&productInfo.getStatus()==1){//兑换产品可用
			Integer leftNum = productInfo.getLeftNum() ;
			if(leftNum!=null && leftNum>0){//可以兑换
				Integer productScore = productInfo.getScore();
				KlhUserProfile sessionUserProfile = (KlhUserProfile) request.getSession().getAttribute("sessionUserProfile");
				int userScore = klhUserScoreLogService.queryCurrentScoreByUserOpenId(sessionUserProfile.getUserOpenId());
				if(productScore!=null&&productScore<userScore){//可以兑换
					Date currentTime = new Date();
					//扣减用户积分
					KlhUserScoreLog scoreLog = new KlhUserScoreLog();
					scoreLog.setUserOpenId(sessionUserProfile.getUserOpenId());
					scoreLog.setScoreChange(0-productScore);
					scoreLog.setCreateTime(currentTime);
					scoreLog.setReason("兑换积分产品【"+productInfo.getTitle()+"】，扣减【"+productScore+"】积分");
					int result =  klhUserScoreLogService.save(scoreLog);
					
					// 扣减库存数量
					if(result>0){
						productInfo.setLeftNum(leftNum-1);
						klhProductService.updateById(productInfo);
					}
				}
				return "redirect:./scoreHome";
			}else{
				//不可兑换
			}
		}
		return "redirect:./scoreHome";
	}
}
