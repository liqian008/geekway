package com.bruce.geekway.admin.controller.geekway;

import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.bruce.baseAdmin.controller.BaseController;
import com.bruce.geekway.constant.ConstConfig;
import com.bruce.geekway.model.WxCommand;
import com.bruce.geekway.model.WxCommandMaterial;
import com.bruce.geekway.model.WxMaterialArticle;
import com.bruce.geekway.service.IWxCommandMaterialService;
import com.bruce.geekway.service.IWxCommandService;
import com.bruce.geekway.service.IWxMaterialArticleService;

/**
 * 用户订阅时的素材管理
 * @author liqian
 *
 */
@Controller
@RequestMapping("/geekway")
public class GeekwaySubscribedMaterialController extends BaseController {

	@Autowired
	private IWxMaterialArticleService wxMaterialArticleService;
	@Autowired
	private IWxCommandMaterialService wxCommandMaterialService;
	@Autowired
	private IWxCommandService wxCommandService;

	
	@RequestMapping("/subscribedMaterialList")
	public String subscribedMaterialList(Model model, HttpServletRequest request) {
		String servletPath = request.getRequestURI();
		model.addAttribute("servletPath", servletPath);

		List<WxMaterialArticle> subscribedMaterialList = wxMaterialArticleService.querySubscribedMaterials();
		model.addAttribute("subscribedMaterialList", subscribedMaterialList);

		return "geekway/subscribedMaterialList";
	}

	/**
	 * 新增图文素材
	 * @param model
	 * @param request
	 * @return
	 */
	@RequestMapping("/subscribedMaterialAdd")
	public String subscribedMaterialAdd(Model model, WxMaterialArticle subscribedMaterial, HttpServletRequest request) {
		String servletPath = request.getRequestURI();
		model.addAttribute("servletPath", servletPath);
		
		model.addAttribute("subscribedMaterial", subscribedMaterial);
		
		return "geekway/subscribedMaterialEdit";
	}
	

	@RequestMapping("/subscribedMaterialEdit")
	public String subscribedMaterialEdit(Model model, HttpServletRequest request, int articleId) {
		String servletPath = request.getRequestURI();
		model.addAttribute("servletPath", servletPath);

		WxMaterialArticle subscribedMaterial = wxMaterialArticleService.loadById(articleId);
		if(subscribedMaterial!=null){
			model.addAttribute("subscribedMaterial", subscribedMaterial);
		}
		return "geekway/subscribedMaterialEdit";
	}
	

	@RequestMapping("/delSubscribedMaterial")
	public String delSubscribedMaterial(Model model, int articleId) {
		// 删除资源的关联
		wxCommandMaterialService.deleteByMaterialId(articleId);

		//删除实体&关联
		wxMaterialArticleService.deleteById(articleId);

		model.addAttribute("redirectUrl", "./subscribedMaterialList");
		return "forward:/home/operationRedirect";
	}
	
	
	/**
	 * 关注时的素材
	 * @param model
	 * @param subscribedMaterial
	 * @param request
	 * @return
	 */
	@RequestMapping(value = "/saveSubscribedMaterial", method = RequestMethod.POST)
	public String saveSubscribedMaterial(Model model, WxMaterialArticle subscribedMaterial, HttpServletRequest request) {
		String servletPath = request.getRequestURI();
		model.addAttribute("servletPath", servletPath);

		int result = 0;
		
		Date currentTime = new Date();
		subscribedMaterial.setUpdateTime(currentTime);
		if (subscribedMaterial != null && subscribedMaterial.getId() != null && subscribedMaterial.getId()>0) {//更新素材内容操作
			result = wxMaterialArticleService.updateById(subscribedMaterial);
		} else {//新增素材操作
			subscribedMaterial.setCreateTime(currentTime);
			result = wxMaterialArticleService.save(subscribedMaterial);
			
			String command = ConstConfig.RE_SUBSCRIBE;//默认为重复关注
			if(subscribedMaterial.getSubscribeStatus()==1){//用户首次关注
				command = ConstConfig.NEW_SUBSCRIBE;
			}
			
			//查询相应command是否存在，不存在则创建
			WxCommand commandBean = wxCommandService.loadOrSave((short) 3, command);
//			if(commandBean!=null){
//				//插入中间表记录
//				WxCommandMaterial commandMaterial = new WxCommandMaterial();
//				commandMaterial.setCommandId(commandBean.getId());
//				commandMaterial.setMaterialId(subscribedMaterial.getId());
//				commandMaterial.setCreateTime(currentTime);
//				commandMaterial.setTopTime(currentTime);
//				wxCommandMaterialService.save(commandMaterial);
//			}
		}
		model.addAttribute("redirectUrl", "./subscribedMaterialList");
		return "forward:/home/operationRedirect";
	}
	
	

}
