package com.bruce.geekway.controller.klh;


import java.net.URLDecoder;
import java.util.Date;
import java.util.List;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.bruce.geekway.data.PagingData;
import com.bruce.geekway.model.KlhUserProfile;
import com.bruce.geekway.model.KlhWallImage;
import com.bruce.geekway.service.klh.IKlhWallImageLogService;
import com.bruce.geekway.service.klh.IKlhWallImageService;
import com.bruce.geekway.utils.JsonViewBuilderUtil;
import com.bruce.geekway.utils.KlhUtil;
/**
 * 可乐惠相片墙
 * @author liqian
 *
 */
@Controller
@RequestMapping(value={"klh"})
public class KlhWallController {
	
	private static final int PAGE_SIZE = 20;
	
	private static final String KLH_WALLIMAGE_LIKE_STATUS= "klh_wallimage_like_";
	private static final String KLH_WALIMAGE_MY_NICKNAME= "klh_wallimage_my_nickname";

	
	@Autowired
	private IKlhWallImageService klhWallImageService;
	@Autowired
	private IKlhWallImageLogService klhWallImageLogService;
	

	@RequestMapping(value = "/latestWallImages")
	public String latestWallImages(Model model, @RequestParam(value="pageNo", defaultValue="1") int pageNo, HttpServletRequest request) {
		if (!KlhUtil.sessionValid(request)) {// 页面流程
			//跳转auth界面
			return KlhUtil.redirectToOauth(model);
		}
		
		Cookie[] cookies = request.getCookies();
		int tabType = 0;
		List<KlhWallImage> wallImageList = null;
		PagingData<KlhWallImage> wallImagePaging = klhWallImageService.pagingLatestImages(pageNo, PAGE_SIZE);
		if(wallImagePaging!=null&&wallImagePaging.getPagingData()!=null){
			wallImageList = wallImagePaging.getPagingData();
			if(wallImageList!=null&&wallImageList.size()>0){
				for(KlhWallImage wallImage: wallImageList){
					int wallImageId = wallImage.getId();
					boolean hasLiked = hasLike(wallImageId, cookies);
					wallImage.setHasLike(hasLiked);
				}
				
				if(wallImagePaging.getTotalPage()>pageNo){
					model.addAttribute("nextPage", pageNo+1);
				}
			}
		}
		model.addAttribute("wallImageList", wallImageList);
		model.addAttribute("tabType", tabType);
		return "klh/wallImageList";
	}
	
	@RequestMapping(value = "/hotestWallImages")
	public String hotestWallImages(Model model, @RequestParam(value="pageNo", defaultValue="1") int pageNo, HttpServletRequest request) {
		if (!KlhUtil.sessionValid(request)) {// 页面流程
			//跳转auth界面
			return KlhUtil.redirectToOauth(model);
		}
		
		Cookie[] cookies = request.getCookies();
		int tabType = 1;
		List<KlhWallImage> wallImageList = null;
		PagingData<KlhWallImage> wallImagePaging = klhWallImageService.pagingHotestImages(pageNo, PAGE_SIZE);
		if(wallImagePaging!=null&&wallImagePaging.getPagingData()!=null){
			wallImageList = wallImagePaging.getPagingData();
			if(wallImageList!=null&&wallImageList.size()>0){
				for(KlhWallImage wallImage: wallImageList){
					int wallImageId = wallImage.getId();
					boolean hasLiked = hasLike(wallImageId, cookies);
					wallImage.setHasLike(hasLiked);
				}
				if(wallImagePaging.getTotalPage()>pageNo){
					model.addAttribute("nextPage", pageNo+1);
				}
			}
		}
		model.addAttribute("wallImageList", wallImageList);
		model.addAttribute("tabType", tabType);
		return "klh/wallImageList";
	}
	
	@RequestMapping(value = "/imagePreview")
	public String imagePreview(Model model, int wallImageId, String imgUrl, HttpServletRequest request) {
		if (!KlhUtil.sessionValid(request)) {// 页面流程
			//跳转auth界面
			return KlhUtil.redirectToOauth(model);
		}
		
		KlhUserProfile sessionUserProfile = (KlhUserProfile) request.getSession().getAttribute("sessionUserProfile");
		//增加浏览记录
		klhWallImageService.increaseBrowse(wallImageId, sessionUserProfile.getUserOpenId());
		model.addAttribute("imgUrl", imgUrl);
		return "klh/wallImagePreview";
	}
	
	
	@RequestMapping(value = "/newImage")
	public String newImage(Model model, HttpServletRequest request) {
		if (!KlhUtil.sessionValid(request)) {// 页面流程
			//跳转auth界面
			return KlhUtil.redirectToOauth(model);
		}
		
		//检查cookie中的
		Cookie[] cookies = request.getCookies();
		if(cookies!=null&&cookies.length>0){
			for(Cookie cookie: cookies){
				//检查是否填写过手机号码
				if(KLH_WALIMAGE_MY_NICKNAME.equals(cookie.getName())){
					String nickname = cookie.getValue();
					model.addAttribute("nickname", URLDecoder.decode(nickname));
					break;
				}
			}
		}
		return "klh/wallNewImage";
	}
	
	@RequestMapping(value = "/saveWallImage", method=RequestMethod.POST)
	public String saveWallImage(Model model, KlhWallImage wallImage, HttpServletRequest request, HttpServletResponse response) {
		if (!KlhUtil.sessionValid(request)) {// 页面流程
			//跳转auth界面
			return KlhUtil.redirectToOauth(model);
		}
		
		if(wallImage!=null){
			wallImage.setLikeCount(0);
			wallImage.setCreateTime(new Date());
			wallImage.setStatus((short)1);//发布状态
			int result = klhWallImageService.save(wallImage);
			if(result>0){
				//将nickname写入cookie
				Cookie cookie = new Cookie(KLH_WALIMAGE_MY_NICKNAME,  java.net.URLEncoder.encode(wallImage.getNickname()));
				cookie.setMaxAge(999999999);
				response.addCookie(cookie);
			}
		}
		return "redirect:./latestWallImages";
	}
	
	/**
	 * 照片墙的like
	 * @param model
	 * @param wallImageId
	 * @param request
	 * @param response
	 * @return
	 */
	@RequestMapping(value = "/like.json")
	public ModelAndView like(Model model, int wallImageId, HttpServletRequest request, HttpServletResponse response) {
		//判断之前是否like过
		boolean hasLike = false;
		//检查cookie中的
		Cookie[] cookies = request.getCookies();
		if(cookies!=null&&cookies.length>0){
			for(Cookie cookie: cookies){
				if(getImageCookieKey(wallImageId).equals(cookie.getName())){
					hasLike = true;
					break;
				}
			}
		}
		
		if(!hasLike){//如果之前未like过
			KlhUserProfile sessionUserProfile = (KlhUserProfile) request.getSession().getAttribute("sessionUserProfile");
			//增加like记录
			int result = klhWallImageService.increaseLike(wallImageId, sessionUserProfile.getUserOpenId());
			if(result>0){
				//重新写入cookie
				Cookie cookie = new Cookie(getImageCookieKey(wallImageId), "true");
				cookie.setMaxAge(999999999);
				response.addCookie(cookie);
				return JsonViewBuilderUtil.SUBMIT_SUCCESS_VIEW;
			}
		}
		return JsonViewBuilderUtil.SUBMIT_FAILED_VIEW;
	}
	
	
	private boolean hasLike(int wallImageId, Cookie[] cookies){
		boolean hasLike = false;
		if(cookies!=null&&cookies.length>0){
			for(Cookie cookie: cookies){
				if(getImageCookieKey(wallImageId).equals(cookie.getName())){
					hasLike = true;
					break;
				}
			}
		}
		return hasLike;//检查cookie中的
	}
	
	/**
	 * 照片墙like过的key
	 * @param wallImageId
	 * @return
	 */
	private static String getImageCookieKey(int wallImageId){
		return KLH_WALLIMAGE_LIKE_STATUS + wallImageId;
	}

}
