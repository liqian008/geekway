package com.bruce.geekway.admin.controller.klh;

import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.bruce.geekway.model.KlhWallImage;
import com.bruce.geekway.service.klh.IKlhWallImageService;

/**
 * 某个wallImage下的wallImage操作
 * @author liqian
 *
 */
@Controller
@RequestMapping("/klh")
public class KlhWallImageController {

	@Autowired
	private IKlhWallImageService klhWallImageService;
	
	@RequestMapping("/wallImageList")
	public String wallImageList(Model model, HttpServletRequest request) {
		String servletPath = request.getRequestURI();
		model.addAttribute("servletPath", servletPath);
		
		List<KlhWallImage> wallImageList = klhWallImageService.queryAll();
		model.addAttribute("wallImageList", wallImageList);
		return "klh/wallImageList";
	}
	
	/**
	 * 编辑WallImage信息
	 * @param model
	 * @param request
	 * @param wallImageId
	 * @param wallImageId
	 * @return
	 */
	@RequestMapping("/wallImageEdit")
	public String wallImageEdit(Model model, HttpServletRequest request, int wallImageId) {
		String servletPath = request.getRequestURI();
		model.addAttribute("servletPath", servletPath);
		
		KlhWallImage wallImage = klhWallImageService.loadById(wallImageId);
		if(wallImage!=null){
			model.addAttribute("wallImage", wallImage);
		}
		return "klh/wallImageEdit";
	}
	
	/**
	 * 修改单个wallImage信息
	 * @param model
	 * @param klhWallImage
	 * @param request
	 * @return
	 */
	@RequestMapping(value = "/saveWallImage", method = RequestMethod.POST)
	public String saveWallImagePropValue(Model model, KlhWallImage klhWallImage, HttpServletRequest request) {
		String servletPath = request.getRequestURI();
		model.addAttribute("servletPath", servletPath);
		
		int result = 0;
		
		Date currentTime = new Date();
		
		if(klhWallImage!=null&&klhWallImage.getId()!=null&&klhWallImage.getId()>0){
			klhWallImage.setUpdateTime(currentTime);
			result = klhWallImageService.updateById(klhWallImage);
		}
		
		model.addAttribute("redirectUrl", "./wallImageList");
		return "forward:/home/operationRedirect";
	}
	
	/**
	 * 删除wallImage
	 * @param model
	 * @param wallImageId
	 * @param request
	 * @return
	 */
	@RequestMapping(value = "/delWallImage")
	public String delWallImageOption(Model model, int wallImageId, HttpServletRequest request) {
		String servletPath = request.getRequestURI();
		model.addAttribute("servletPath", servletPath);
		
		int result = klhWallImageService.deleteById(wallImageId);
		
		model.addAttribute("redirectUrl", "./wallImageList");
		return "forward:/home/operationRedirect"; 
	}
	
}
