package com.bruce.geekway.admin.controller.geekway;

import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.bruce.foundation.admin.controller.BaseController;
import com.bruce.geekway.model.WxMaterialMultimedia;
import com.bruce.geekway.service.IWxMaterialMultimediaService;

/**
 * 多媒体管理controller
 * @author liqian
 *
 */
@Controller
@RequestMapping("/geekway")
public class GeekwayMaterialMultimediaController extends BaseController {

	@Autowired
	private IWxMaterialMultimediaService wxMaterialMultimediaService;
	
	/**
	 * 图片素材列表
	 * @param model
	 * @param request
	 * @return
	 */
	@RequestMapping("/materialImageList")
	public String materialImageList(Model model, HttpServletRequest request) {
		String servletPath = request.getRequestURI();
		model.addAttribute("servletPath", servletPath);

		List<WxMaterialMultimedia> materialMultimediaList = wxMaterialMultimediaService.queryImageMaterials();
		model.addAttribute("materialMultimediaList", materialMultimediaList);
		return "material/materialMultimediaList";
	}
	
	/**
	 * 语音素材列表
	 * @param model
	 * @param request
	 * @return
	 */
	@RequestMapping("/materialVoiceList")
	public String materialVoiceList(Model model, HttpServletRequest request) {
		String servletPath = request.getRequestURI();
		model.addAttribute("servletPath", servletPath);

		List<WxMaterialMultimedia> materialMultimediaList = wxMaterialMultimediaService.queryVoiceMaterials();
		model.addAttribute("materialMultimediaList", materialMultimediaList);
		return "material/materialMultimediaList";
	}

	/**
	 * 新增图片素材
	 * @param model
	 * @param request
	 * @return
	 */
	@RequestMapping("/materialImageAdd")
	public String materialMultimediaAdd(Model model, WxMaterialMultimedia materialMultimedia, HttpServletRequest request) {
		String servletPath = request.getRequestURI();
		model.addAttribute("servletPath", servletPath);

		materialMultimedia.setMaterialType(IWxMaterialMultimediaService.MATERIAL_TYPE_IMAGE);//1为图文素材
		model.addAttribute("materialMultimedia", materialMultimedia);
		
		return "material/materialMultimediaEdit";
	}
	
	/**
	 * 新增语音素材
	 * @param model
	 * @param materialMultimedia
	 * @param request
	 * @return
	 */
	@RequestMapping("/materialVoiceAdd")
	public String materialTextAdd(Model model, WxMaterialMultimedia materialMultimedia, HttpServletRequest request) {
		String servletPath = request.getRequestURI();
		model.addAttribute("servletPath", servletPath);
		
		materialMultimedia.setMaterialType(IWxMaterialMultimediaService.MATERIAL_TYPE_VOICE);//0为纯文本素材
		model.addAttribute("materialMultimedia", materialMultimedia);
		
		return "material/materialMultimediaEdit";
	}

	@RequestMapping("/materialMultimediaEdit")
	public String materialMultimediaEdit(Model model, HttpServletRequest request, int materialId) {
		String servletPath = request.getRequestURI();
		model.addAttribute("servletPath", servletPath);

		WxMaterialMultimedia materialMultimedia = wxMaterialMultimediaService.loadById(materialId);
		if(materialMultimedia!=null){
			model.addAttribute("materialMultimedia", materialMultimedia);
			
			//TODO 查询引用了该素材的command列表
//			List<WxCommand> commandList = wxCommandService.queryCommandsByMaterialId(articleId);
//			model.addAttribute("commandList", commandList);
		}
		return "material/materialMultimediaEdit";
	}

	@RequestMapping(value = "/saveMaterialMultimedia", method = RequestMethod.POST)
	public String saveMaterialMultimedia(Model model, WxMaterialMultimedia materialMultimedia, HttpServletRequest request) {
		String servletPath = request.getRequestURI();
		model.addAttribute("servletPath", servletPath);
		
		int result = 0;
		Date currentTime = new Date();
		materialMultimedia.setUpdateTime(currentTime);
		
		if(materialMultimedia!=null&&materialMultimedia.getId()!=null&&materialMultimedia.getId()>0){
			result = wxMaterialMultimediaService.updateById(materialMultimedia);
		}else{
			materialMultimedia.setCreateTime(currentTime);
			result = wxMaterialMultimediaService.save(materialMultimedia);
		}
		String redirectUrl = "./materialImageList";
		Short materialType = materialMultimedia.getMaterialType();
		if(IWxMaterialMultimediaService.MATERIAL_TYPE_VOICE.equals(materialType)){
			redirectUrl = "./materialVoiceList";
		}
		model.addAttribute("redirectUrl", redirectUrl);
		return "forward:/home/operationRedirect";
	}
	
	

	@RequestMapping("/delMaterialMultimedia")
	public String delMaterialMultimedia(Model model, int materialId) {
		
		//TODO删除资源的关联

		//删除实体&关联
		wxMaterialMultimediaService.deleteById(materialId);

		model.addAttribute("redirectUrl", "./materialMultimediaList");
		return "forward:/home/operationRedirect";
	}
	

}
