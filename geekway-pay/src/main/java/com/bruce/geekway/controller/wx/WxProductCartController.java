package com.bruce.geekway.controller.wx;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.bruce.foundation.util.CookieUtils;
import com.bruce.foundation.util.DateUtil;
import com.bruce.foundation.util.JsonUtil;
import com.bruce.geekway.constants.ConstFront;
import com.bruce.geekway.model.WxProductCart;
import com.bruce.geekway.model.WxProductCart.CartProductSku;
import com.bruce.geekway.model.WxProductSku;
import com.bruce.geekway.model.WxProductSkuCriteria;
import com.bruce.geekway.service.product.IWxProductService;
import com.bruce.geekway.service.product.IWxProductSkuService;
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
		WxProductCart cart = loadCartFromCookie(request, response);
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
		return "order/cart";
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
		WxProductCart cart = loadCartFromCookie(request, response);
		if(cart!=null){
			//购物车中添加商品
			cart.addItem(productSkuId, buyAmount);
			writeCartCookie(cart, response);
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
		WxProductCart cart = loadCartFromCookie(request, response);
		if(cart!=null){
			//购物车中移除商品
			cart.removeItem(productSkuId);
			writeCartCookie(cart, response);
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
		WxProductCart cart = loadCartFromCookie(request, response);
		if(cart!=null){
			Map<Integer, Integer> cartItemMap = cart.getItemMap();
			if(cartItemMap!=null&&cartItemMap.size()>0){
				//加载购物车中的商品
				Integer buyAmount = cartItemMap.get(productSkuId);
				if(buyAmount!=null){
					WxProductSku productSku = wxProductSkuService.loadById(productSkuId);
					if(productSku!=null){
						model.addAttribute(new CartProductSku(productSku, cartItemMap.get(productSku.getId())));
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
		WxProductCart cart = loadCartFromCookie(request, response);
		if(cart!=null){
			cart.addItem(productSkuId, buyAmount);
			writeCartCookie(cart, response);
		}
		return ResponseUtil.getRedirectString("./");
	}
	
	
	/**
	 * 从cookie中加载购物车
	 * @param request
	 * @return
	 */
	private WxProductCart loadCartFromCookie(HttpServletRequest request, HttpServletResponse response) {
		WxProductCart cart = null;
		//检查cookie中的购物车信息
		String cartJson = CookieUtils.getCookie(request, ConstFront.COOKIE_KEY_WX_PRODUCT_CART);
		if(!StringUtils.isBlank(cartJson)){
			try{
				cart = JsonUtil.gson.fromJson(cartJson, WxProductCart.class);
			}catch(Exception e){
			}
		}
		if(cart==null){
			cart = new WxProductCart();
			writeCartCookie(cart, response);
		}
		return cart;
	}


	private void writeCartCookie(WxProductCart productCart, HttpServletResponse response) {
		if(productCart!=null){
			//将购物车写入cookie
			Cookie cookie = new Cookie(ConstFront.COOKIE_KEY_WX_PRODUCT_CART, JsonUtil.gson.toJson(productCart));
			cookie.setMaxAge((int)DateUtil.TIME_UNIT_WEEK);//超时时间
			response.addCookie(cookie);
		}
	}
}
