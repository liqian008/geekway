package com.bruce.geekway.controller.wx;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.bruce.geekway.model.WxProductCart;
import com.bruce.geekway.model.WxProductCart.CartProductSku;
import com.bruce.geekway.model.WxProduct;
import com.bruce.geekway.model.WxProductSku;
import com.bruce.geekway.model.WxProductSkuCriteria;
import com.bruce.geekway.service.product.IWxProductService;
import com.bruce.geekway.service.product.IWxProductSkuService;
import com.bruce.geekway.utils.CartUtil;
import com.bruce.geekway.utils.ResponseUtil;

/**
 * 购物车controller
 * @author liqian
 *
 */
@RequestMapping(value="/cart")
@Controller
public class WxProductCartController {
	
	@Autowired
	private IWxProductService wxProductService;
	@Autowired
	private IWxProductSkuService wxProductSkuService;
	
	private static final Logger logger = LoggerFactory.getLogger(WxProductCartController.class);

	
	/**
	 * 购物车
	 * @param model
	 * @param request
	 * @param response
	 * @return
	 */
	@RequestMapping(value = "/")
	public String cart(Model model, HttpServletRequest request, HttpServletResponse response) {
		//加载购物车中的
		WxProductCart cart = CartUtil.loadCartFromCookie(request, response);
		if(cart!=null){
			Map<Integer, Integer> cartItemMap = cart.getItemMap();
			if(cartItemMap!=null&&cartItemMap.size()>0){
				//加载购物车中的商品
				Set<Integer> productSkuIdSet = cartItemMap.keySet();
				if(productSkuIdSet!=null&&productSkuIdSet.size()>0){
					WxProductSkuCriteria criteria = new WxProductSkuCriteria();
					criteria.createCriteria().andIdIn(new ArrayList<Integer>(productSkuIdSet));
					List<WxProductSku> productSkuList =  wxProductSkuService.queryByCriteria(criteria);
					if(productSkuList!=null&&productSkuList.size()>0){
						List<CartProductSku> cartItemList =  new ArrayList<CartProductSku>();
						for(WxProductSku productSku: productSkuList){
							cartItemList.add(new CartProductSku(productSku, cartItemMap.get(productSku.getId())));
						}
						model.addAttribute("cartItemList", cartItemList);
					}
				}
			}
		}
		return "cart/cart";
	}
	
	
	/**
	 * 添加到购物车
	 * @param model
	 * @param productSkuId
	 * @param buyAmount
	 * @param request
	 * @param response
	 * @return
	 */
	@RequestMapping(value = "/addToCart")
	public String addToCart(Model model, int productSkuId, int buyAmount, HttpServletRequest request, HttpServletResponse response) {
		WxProductCart cart = CartUtil.loadCartFromCookie(request, response);
		if(cart!=null){
			//购物车中添加商品
			cart.addItem(productSkuId, buyAmount);
			CartUtil.writeCartCookie(cart, response);
		}
		return ResponseUtil.getRedirectString("./");
	}
	
	
	/**
	 * 从购物车中移除
	 * @param model
	 * @param productSkuId
	 * @param request
	 * @param response
	 * @return
	 */
	@RequestMapping(value = "/removeFromCart")
	public String removeFromCart(Model model, int productSkuId, HttpServletRequest request, HttpServletResponse response) {
		WxProductCart cart = CartUtil.loadCartFromCookie(request, response);
		if(cart!=null){
			//购物车中移除商品
			cart.removeItem(productSkuId);
			CartUtil.writeCartCookie(cart, response);
		}
		return ResponseUtil.getRedirectString("./");
	}
	
	
	/**
	 * 查看购物车中的商品信息（以供修改数量）
	 * @param model
	 * @param productSkuId
	 * @param request
	 * @param response
	 * @return
	 */
	@RequestMapping(value = "/cartItem")
	public String cartItem(Model model, int productSkuId, HttpServletRequest request, HttpServletResponse response) {
		WxProductCart cart =  CartUtil.loadCartFromCookie(request, response);
		if(cart!=null){
			Map<Integer, Integer> cartItemMap = cart.getItemMap();
			if(cartItemMap!=null&&cartItemMap.size()>0){
				//加载购物车中的商品
				Integer buyAmount = cartItemMap.get(productSkuId); 
				if(buyAmount!=null){
					WxProductSku productSku = wxProductSkuService.loadById(productSkuId);
					if(productSku!=null){
						WxProduct product = wxProductService.loadById(productSku.getProductId());
						if(product!=null){
							List<String> productPicList = CartUtil.buildProductPicList(product);
							model.addAttribute("productPicList", productPicList);
						}
						
						model.addAttribute("product", product);
						model.addAttribute("cartItem", new CartProductSku(productSku, buyAmount));
					}
				}
			}
		}
		return "cart/cartItem";
	}
	
	/**
	 * 修改购物车中的商品信息
	 * @param model
	 * @param productSkuId
	 * @param buyAmount
	 * @param request
	 * @param response
	 * @return
	 */
	@RequestMapping(value = "/modifyCartItem")
	public String modifyCartItem(Model model, int productSkuId, int buyAmount, HttpServletRequest request, HttpServletResponse response) {
		WxProductCart cart = CartUtil.loadCartFromCookie(request, response);
		if(cart!=null){
			cart.addItem(productSkuId, buyAmount);
			CartUtil.writeCartCookie(cart, response);
		}
		return ResponseUtil.getRedirectString("./");
	}
	
	/**
	 * 修改购物车中的商品信息
	 * @param model
	 * @param productSkuId
	 * @param buyAmount
	 * @param request
	 * @param response
	 * @return
	 */
	@RequestMapping(value = "/clearCart")
	public String clearCart(Model model, HttpServletRequest request, HttpServletResponse response) {
		CartUtil.clearCartCookie(response);
		return ResponseUtil.getRedirectString("./");
	}
	
	
}
