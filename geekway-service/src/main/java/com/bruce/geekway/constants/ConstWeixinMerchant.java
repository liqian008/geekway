package com.bruce.geekway.constants;

import com.bruce.geekway.utils.ConfigUtil;

/**
 * 微信小店的接口常量
 * @author liqian
 *
 */
@Deprecated
public interface ConstWeixinMerchant {
	
	/*以下是微信小店api的商品管理接口*/
	/*创建商品api*/
	public static final String WX_PRODUCT_CREATE_URL = ConfigUtil.getString("weixinmp_oauth_redirect_proxy_url");
	/*删除商品api*/
	public static final String WX_PRODUCT_DEL_URL = ConfigUtil.getString("weixinmp_oauth_redirect_proxy_url");
	/*修改商品api*/
	public static final String WX_PRODUCT_MODIFY_URL = ConfigUtil.getString("weixinmp_oauth_redirect_proxy_url");
	/*查询商品api*/
	public static final String WX_PRODUCT_QUERY_URL = ConfigUtil.getString("weixinmp_oauth_redirect_proxy_url");
	/*获取指定状态的所有商品	*/
	public static final String WX_PRODUCT_QUERYBYSTATUS_URL = ConfigUtil.getString("weixinmp_oauth_redirect_proxy_url");
	/*商品上下架api*/
	public static final String WX_PRODUCT_STATUS_URL = ConfigUtil.getString("weixinmp_oauth_redirect_proxy_url");
	
	/*获取指定分类的所有子分类api*/
	public static final String WX_PRODUCT_SUBCATEGORYS_URL = ConfigUtil.getString("weixinmp_oauth_redirect_proxy_url");
	/*获取指定子分类的所有SKU api*/
	public static final String WX_PRODUCT_SUBCATEGORY_SKUS_URL = ConfigUtil.getString("weixinmp_oauth_redirect_proxy_url");
	/*获取指定分类的所有属性	 api*/
	public static final String WX_PRODUCT_CATEGORY_PROPERTY_URL = ConfigUtil.getString("weixinmp_oauth_redirect_proxy_url");
	
		
	/*以下是微信小店的库存管理接口*/
	/*增加库存 api*/
	public static final String WX_PRODUCT_ADD_STOCK_URL = ConfigUtil.getString("weixinmp_oauth_redirect_proxy_url");
	/*减少库存 api*/
	public static final String WX_PRODUCT_REDUCE_STOCK_URL = ConfigUtil.getString("weixinmp_oauth_redirect_proxy_url");
	
	
	/*以下是微信小店的邮费管理接口*/
	/*增加邮费模板 api*/
	public static final String WX_PRODUCT_ADD_POSTFEE_TEMPLATE_URL = ConfigUtil.getString("weixinmp_oauth_redirect_proxy_url");
	/*删除邮费模板 api*/
	public static final String WX_PRODUCT_DEL_POSTFEE_TEMPLATE_URL = ConfigUtil.getString("weixinmp_oauth_redirect_proxy_url");
	/*修改邮费模板 api*/
	public static final String WX_PRODUCT_MODIFY_POSTFEE_TEMPLATE_URL = ConfigUtil.getString("weixinmp_oauth_redirect_proxy_url");
	/*获取指定ID的邮费模板 api*/
	public static final String WX_PRODUCT_LOAD_POSTFEE_URL = ConfigUtil.getString("weixinmp_oauth_redirect_proxy_url");
	/*获取所有邮费模板 api*/
	public static final String WX_PRODUCT_QUERY_ALL_POSTFEES_URL = ConfigUtil.getString("weixinmp_oauth_redirect_proxy_url");
	
	
	/*以下是微信小店的分组管理接口*/
	/*增加分组 api*/
	public static final String WX_PRODUCT_ADD_GROUP_URL = ConfigUtil.getString("weixinmp_oauth_redirect_proxy_url");
	/*删除分组 api*/
	public static final String WX_PRODUCT_DEL_GROUP_URL = ConfigUtil.getString("weixinmp_oauth_redirect_proxy_url");
	/*修改分组属性 api*/
	public static final String WX_PRODUCT_MODIFY_GROUP_URL = ConfigUtil.getString("weixinmp_oauth_redirect_proxy_url");
	/*修改分组商品 api*/
	public static final String WX_PRODUCT_MODIFY_BY_GROUP_URL = ConfigUtil.getString("weixinmp_oauth_redirect_proxy_url");
	/*根据分组ID获取分组信息 api*/
	public static final String WX_PRODUCT_LOAD_GROUP_URL = ConfigUtil.getString("weixinmp_oauth_redirect_proxy_url");
	/*获取所有分组 api*/
	public static final String WX_PRODUCT_QUERY_ALL_GROUPS_URL = ConfigUtil.getString("weixinmp_oauth_redirect_proxy_url");
	
	/*以下是微信小店的货架管理接口*/
	/*增加货架 api*/
	public static final String WX_PRODUCT_ADD_SHELF_URL = ConfigUtil.getString("weixinmp_oauth_redirect_proxy_url");
	/*删除货架 api*/
	public static final String WX_PRODUCT_DEL_SHELF_URL = ConfigUtil.getString("weixinmp_oauth_redirect_proxy_url");
	/*修改货架 api*/
	public static final String WX_PRODUCT_MODIFY_SHELF_URL = ConfigUtil.getString("weixinmp_oauth_redirect_proxy_url");
	/*获取所有货架 api*/
	public static final String WX_PRODUCT_QUERY_ALL_SHELFS_URL = ConfigUtil.getString("weixinmp_oauth_redirect_proxy_url");
	/*根据货架ID获取货架信息 api*/
	public static final String WX_PRODUCT_LOAD_SHELF_URL = ConfigUtil.getString("weixinmp_oauth_redirect_proxy_url");
	/*开发者将自己的页面作为货架 api*/
	//todo nothing
	
	/*以下是微信小店的订单管理接口*/
	/*在用户在微信中付款成功后，微信服务器会将订单付款通知推送到开发者在公众平台网站中设置的回调URL（在开发模式中设置）中，如未设置回调URL，则获取不到该事件推送*/
	
	/*根据订单ID获取订单详情 api*/
	public static final String WX_PRODUCT_LOAD_ORDER_URL = ConfigUtil.getString("weixinmp_oauth_redirect_proxy_url");
	/*根据订单状态/创建时间获取订单详情 api*/
	public static final String WX_PRODUCT_QUERY_BY_FILTER_URL = ConfigUtil.getString("weixinmp_oauth_redirect_proxy_url");
	/*设置订单发货信息 api*/
	public static final String WX_PRODUCT_SET_DELIVERY_URL = ConfigUtil.getString("weixinmp_oauth_redirect_proxy_url");
	/*关闭订单 api*/
	public static final String WX_PRODUCT_CLOSE_URL = ConfigUtil.getString("weixinmp_oauth_redirect_proxy_url");
	
}
