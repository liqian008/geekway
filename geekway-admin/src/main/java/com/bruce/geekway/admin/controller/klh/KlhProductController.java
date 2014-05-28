package com.bruce.geekway.admin.controller.klh;

import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.bruce.geekway.model.KlhProduct;
import com.bruce.geekway.service.klh.IKlhProductService;

/**
 * 某个product下的product操作
 * @author liqian
 *
 */
@Controller
@RequestMapping("/klh")
public class KlhProductController {

	@Autowired
	private IKlhProductService klhProductService;
	
	@RequestMapping("/productList")
	public String productList(Model model, HttpServletRequest request) {
		String servletPath = request.getRequestURI();
		model.addAttribute("servletPath", servletPath);
		
		List<KlhProduct> productList = klhProductService.queryAll();
		model.addAttribute("productList", productList);
		return "klh/productList";
	}
	
	
	@RequestMapping("/productAdd")
	public String productAdd(Model model, KlhProduct product, HttpServletRequest request) {
		String servletPath = request.getRequestURI();
		model.addAttribute("servletPath", servletPath);
		
		model.addAttribute("product", product);
		return "klh/productEdit";
	}
	
	
	/**
	 * 编辑Product信息
	 * @param model
	 * @param request
	 * @param productId
	 * @param productId
	 * @return
	 */
	@RequestMapping("/productEdit")
	public String productEdit(Model model, HttpServletRequest request, int productId) {
		String servletPath = request.getRequestURI();
		model.addAttribute("servletPath", servletPath);
		
		KlhProduct product = klhProductService.loadById(productId);
		if(product!=null){
			model.addAttribute("product", product);
		}
		return "klh/productEdit";
	}
	
	/**
	 * 保存单个product信息
	 * @param model
	 * @param klhProduct
	 * @param request
	 * @return
	 */
	@RequestMapping(value = "/saveProduct", method = RequestMethod.POST)
	public String saveProductPropValue(Model model, KlhProduct klhProduct, HttpServletRequest request) {
		String servletPath = request.getRequestURI();
		model.addAttribute("servletPath", servletPath);
		
		int result = 0;
		
		Date currentTime = new Date();
		
		if(klhProduct!=null&&klhProduct.getId()!=null&&klhProduct.getId()>0){
			klhProduct.setUpdateTime(currentTime);
			result = klhProductService.updateById(klhProduct);
		}else{
			klhProduct.setCreateTime(currentTime);
			result = klhProductService.save(klhProduct);
		}
		
		model.addAttribute("redirectUrl", "./productList");
		return "forward:/home/operationRedirect";
	}
	
	/**
	 * 删除product
	 * @param model
	 * @param productId
	 * @param request
	 * @return
	 */
	@RequestMapping(value = "/delProduct")
	public String delProductOption(Model model, int productId, HttpServletRequest request) {
		String servletPath = request.getRequestURI();
		model.addAttribute("servletPath", servletPath);
		
		int result = klhProductService.deleteById(productId);
		
		model.addAttribute("redirectUrl", "./productList");
		return "forward:/home/operationRedirect"; 
	}
	
}
