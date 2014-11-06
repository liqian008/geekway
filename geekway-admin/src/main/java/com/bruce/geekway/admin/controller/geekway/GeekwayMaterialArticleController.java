package com.bruce.geekway.admin.controller.geekway;

import java.util.Date;
import java.util.HashMap;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.bruce.foundation.admin.controller.BaseController;
import com.bruce.foundation.model.paging.PagingResult;
import com.bruce.geekway.constants.ConstConfig;
import com.bruce.geekway.model.WxMaterialArticle;
import com.bruce.geekway.model.WxMaterialArticleCriteria;
import com.bruce.geekway.service.IWxCommandService;
import com.bruce.geekway.service.IWxMaterialArticleService;
import com.bruce.geekway.service.IWxMaterialNewsArticleService;

/**
 * 图文管理controller
 * @author liqian
 *
 */
@Controller
@RequestMapping("/geekway")
public class GeekwayMaterialArticleController extends BaseController {

	private static final int pageSize = ConstConfig.PAGE_SIZE_DEFAULT;

	
	@Autowired
	private IWxMaterialArticleService wxMaterialArticleService;
	@Autowired
	private IWxCommandService wxCommandService;
	@Autowired
	private IWxMaterialNewsArticleService wxMaterialNewsArticleService;

	
	/**
	 * 分页方式查询
	 * @param model
	 * @param pageNo
	 * @param pageSize
	 * @param request
	 * @return
	 */
	@SuppressWarnings("unchecked")
	@RequestMapping("/materialArticlePaging")
	public String materialArticlePaging(Model model, @RequestParam(defaultValue="1")int pageNo, HttpServletRequest request) {
		String servletPath = request.getRequestURI();
		model.addAttribute("servletPath", servletPath);
		
		model.addAttribute("pageNo", pageNo);
		
		WxMaterialArticleCriteria criteria = new WxMaterialArticleCriteria();
		criteria.setOrderByClause(" id desc");
		
		PagingResult<WxMaterialArticle> materialArticlePagingData = wxMaterialArticleService.pagingByCriteria(pageNo, pageSize  , criteria);
		if(materialArticlePagingData!=null){
			materialArticlePagingData.setRequestUri(request.getRequestURI()); 
			
			HashMap<String, Object> queryMap = new HashMap<String, Object>();
			queryMap.putAll(request.getParameterMap());
			materialArticlePagingData.setQueryMap(queryMap);
			model.addAttribute("materialArticlePagingData", materialArticlePagingData);
		}
		return "material/materialArticleListPaging";
	}
	
	
	
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

//		materialArticle.setMaterialType((short) 1);//1为图文素材
		model.addAttribute("materialArticle", materialArticle);
		
		return "material/materialArticleEdit";
	}
	
//	/**
//	 * 新增文本素材
//	 * @param model
//	 * @param materialArticle
//	 * @param request
//	 * @return
//	 */
//	@RequestMapping("/materialTextAdd")
//	public String materialTextAdd(Model model, WxMaterialArticle materialArticle, HttpServletRequest request) {
//		String servletPath = request.getRequestURI();
//		model.addAttribute("servletPath", servletPath);
//
//		materialArticle.setMaterialType((short) 0);//0为纯文本素材
//		model.addAttribute("materialArticle", materialArticle);
//		
////		return "material/materialTextAdd";
//		return "material/materialArticleAdd";
//	}
	

	@RequestMapping("/materialArticleEdit")
	public String materialArticleEdit(Model model, HttpServletRequest request, int articleId) {
		String servletPath = request.getRequestURI();
		model.addAttribute("servletPath", servletPath);

		WxMaterialArticle materialArticle = wxMaterialArticleService.loadById(articleId);
		if(materialArticle!=null){
			model.addAttribute("materialArticle", materialArticle);
			
//			//TODO 查询引用了该素材的command列表
//			List<WxCommand> commandList = wxCommandService.queryCommandsByMaterialId(articleId);
//			model.addAttribute("commandList", commandList);
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
		
		if(materialArticle!=null&&materialArticle.getId()!=null&&materialArticle.getId()>0){
			result = wxMaterialArticleService.updateById(materialArticle);
		}else{
			materialArticle.setCreateTime(currentTime);
			result = wxMaterialArticleService.save(materialArticle);
		}
		
		model.addAttribute("redirectUrl", "./materialArticleList");
		return "forward:/home/operationRedirect";
	}

	@RequestMapping("/delMaterialArticle")
	public String delMaterialArticle(Model model, int articleId) {
		
		//删除多图文资源的关联
		wxMaterialNewsArticleService.deleteByNewsId(articleId);
		
		//删除实体&关联
		wxMaterialArticleService.deleteById(articleId);

		model.addAttribute("redirectUrl", "./materialArticlePaging");
		return "forward:/home/operationRedirect";
	}

}
