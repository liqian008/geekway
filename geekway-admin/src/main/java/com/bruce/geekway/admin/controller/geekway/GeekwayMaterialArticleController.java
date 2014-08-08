package com.bruce.geekway.admin.controller.geekway;

import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.bruce.foundation.admin.controller.BaseController;
import com.bruce.geekway.model.WxCommand;
import com.bruce.geekway.model.WxCommandMaterial;
import com.bruce.geekway.model.WxMaterialArticle;
import com.bruce.geekway.service.IWxCommandMaterialService;
import com.bruce.geekway.service.IWxCommandService;
import com.bruce.geekway.service.IWxMaterialArticleService;

/**
 * 图文管理controller
 * @author liqian
 *
 */
@Controller
@RequestMapping("/geekway")
public class GeekwayMaterialArticleController extends BaseController {

	@Autowired
	private IWxMaterialArticleService wxMaterialArticleService;
	@Autowired
	private IWxCommandMaterialService wxCommandMaterialService;
	@Autowired
	private IWxCommandService wxCommandService;

	
	@RequestMapping("/materialArticleList")
	public String materialArticleList(Model model, HttpServletRequest request) {
		String servletPath = request.getRequestURI();
		model.addAttribute("servletPath", servletPath);

		List<WxMaterialArticle> materialArticleList = wxMaterialArticleService.queryAll();
		model.addAttribute("materialArticleList", materialArticleList);
		return "material/materialArticleList";
	}


	/**
	 * 新增图文素材
	 * @param model
	 * @param request
	 * @return
	 */
	@RequestMapping("/materialArticleAdd")
	public String materialArticleAdd(Model model, WxMaterialArticle materialArticle, HttpServletRequest request) {
		String servletPath = request.getRequestURI();
		model.addAttribute("servletPath", servletPath);

		materialArticle.setMaterialType((short) 1);//1为图文素材
		model.addAttribute("materialArticle", materialArticle);
		
		return "material/materialArticleAdd";
	}
	
	/**
	 * 新增文本素材
	 * @param model
	 * @param materialArticle
	 * @param request
	 * @return
	 */
	@RequestMapping("/materialTextAdd")
	public String materialTextAdd(Model model, WxMaterialArticle materialArticle, HttpServletRequest request) {
		String servletPath = request.getRequestURI();
		model.addAttribute("servletPath", servletPath);

		materialArticle.setMaterialType((short) 0);//0为纯文本素材
		model.addAttribute("materialArticle", materialArticle);
		
//		return "material/materialTextAdd";
		return "material/materialArticleAdd";
	}
	

	@RequestMapping("/materialArticleEdit")
	public String materialArticleEdit(Model model, HttpServletRequest request, int articleId) {
		String servletPath = request.getRequestURI();
		model.addAttribute("servletPath", servletPath);

		WxMaterialArticle materialArticle = wxMaterialArticleService.loadById(articleId);
		if(materialArticle!=null){
			model.addAttribute("materialArticle", materialArticle);
			
			//TODO 查询引用了该素材的command列表
			List<WxCommand> commandList = wxCommandService.queryCommandsByMaterialId(articleId);
			model.addAttribute("commandList", commandList);
		}
		return "material/materialArticleEdit";
	}

	@RequestMapping(value = "/saveMaterialArticle", method = RequestMethod.POST)
	public String saveMaterialArticle(Model model, WxMaterialArticle materialArticle, HttpServletRequest request) {
		String servletPath = request.getRequestURI();
		model.addAttribute("servletPath", servletPath);

		int result = 0;

		Date currentTime = new Date();
		materialArticle.setUpdateTime(currentTime);
		materialArticle.setSubscribeStatus((short) 0);//设置为普通素材（非关注素材）
		if (materialArticle != null && materialArticle.getId() != null && materialArticle.getId()>0) {//更新素材内容操作
			result = wxMaterialArticleService.updateById(materialArticle);
		} else {//新增素材操作
			materialArticle.setCreateTime(currentTime);
			result = wxMaterialArticleService.save(materialArticle);
			
			String command = request.getParameter("command");
			if(!StringUtils.isBlank(command)){//用户输入了关键词
				String[] commandTypeArray = request.getParameterValues("commandTypes");
				if(commandTypeArray!=null&&commandTypeArray.length>0){
					for(String commandTypeStr: commandTypeArray){
						short commandType = Short.parseShort(commandTypeStr);
						//查询相应command是否存在，不存在则创建
						WxCommand commandBean = wxCommandService.loadOrSave(commandType, command);
						if(commandBean!=null){
							//插入中间表记录
							WxCommandMaterial commandMaterial = new WxCommandMaterial();
							commandMaterial.setCommandId(commandBean.getId());
							commandMaterial.setMaterialId(materialArticle.getId());
							commandMaterial.setCreateTime(currentTime);
							commandMaterial.setTopTime(currentTime);
							wxCommandMaterialService.save(commandMaterial);
						}
					}
				}
			}
		}
		model.addAttribute("redirectUrl", "./materialArticleList");
		return "forward:/home/operationRedirect";
	}

	@RequestMapping("/delMaterialArticle")
	public String delMaterialArticle(Model model, int articleId) {
		
		// 删除资源的关联
		wxCommandMaterialService.deleteByMaterialId(articleId);

		//删除实体&关联
		wxMaterialArticleService.deleteById(articleId);

		model.addAttribute("redirectUrl", "./materialArticleList");
		return "forward:/home/operationRedirect";
	}
	

}
