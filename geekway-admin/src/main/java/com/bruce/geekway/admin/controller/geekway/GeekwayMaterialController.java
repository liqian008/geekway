package com.bruce.geekway.admin.controller.geekway;

import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.bruce.baseAdmin.controller.BaseController;
import com.bruce.geekway.model.WxMaterial;
import com.bruce.geekway.service.IUploadService;
import com.bruce.geekway.service.IWxCommandMaterialService;
import com.bruce.geekway.service.IWxMaterialService;

@Controller
@RequestMapping("/geekway")
public class GeekwayMaterialController extends BaseController{

	@Autowired
	private IWxMaterialService wxMaterialService;
	@Autowired
	private IWxCommandMaterialService wxCommandMaterialService;
	@Autowired
	private IUploadService uploadService;
	
	@RequestMapping("/materialList")
	public String materialList(Model model, HttpServletRequest request) {
		String servletPath = request.getRequestURI();
		model.addAttribute("servletPath", servletPath);

		List<WxMaterial> materialList = wxMaterialService.queryAll();
		model.addAttribute("materialList", materialList);
		return "geekway/materialList";
	}

	@RequestMapping("/materialAdd")
	public String materialAdd(Model model, WxMaterial material, @RequestParam(value="type", required=false, defaultValue="1") short materialType,
			HttpServletRequest request) {
		String servletPath = request.getRequestURI();
		model.addAttribute("servletPath", servletPath);

		model.addAttribute("material", material);
		model.addAttribute("materialType", materialType);
		
		return "geekway/materialEdit";
	}

	@RequestMapping("/materialEdit")
	public String materialEdit(Model model, HttpServletRequest request,
			int materialId) {
		String servletPath = request.getRequestURI();
		model.addAttribute("servletPath", servletPath);

		WxMaterial material = wxMaterialService.loadById(materialId);
		model.addAttribute("material", material);
		return "geekway/materialEdit";
	}

	@RequestMapping(value = "/saveMaterial", method = RequestMethod.POST)
	public String saveMaterial(Model model, WxMaterial material,
			HttpServletRequest request) {
		String servletPath = request.getRequestURI();
		model.addAttribute("servletPath", servletPath);

		int result = 0;

		Date currentTime = new Date();
		material.setUpdateTime(currentTime);
		if (material != null && material.getId() != null && material.getId() > 0) {
			result = wxMaterialService.updateById(material);
		} else {
			material.setCreateTime(currentTime);
			result = wxMaterialService.save(material);
		}

		model.addAttribute("redirectUrl", "./materialList");
		return "forward:/home/operationRedirect";
	}
	
	
	@RequestMapping("/delMaterial")
	public String delMaterial(Model model,  int materialId) {
		
		//删除资源的关联
		wxCommandMaterialService.deleteByCommandId(materialId);
			
		//删除menu实体
		wxMaterialService.deleteById(materialId);
		
		model.addAttribute("redirectUrl", "./materialList");
		return "forward:/home/operationRedirect";
	}
	
	
}
