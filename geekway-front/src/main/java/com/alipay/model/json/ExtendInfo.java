package com.alipay.model.json;


/**
 * 该内容可空，暂时废弃
 * @author liqian
 *
 */
@Deprecated
public class ExtendInfo {

	/* 单个购买上限 */
	private String single_limit;
	/* 单用户购买上限 */
	private String user_limit;
	/* 商户名称 */
	private String logo_name;
	
	public ExtendInfo(){
		super();
		single_limit = "1";
	}
	
//	public ExtendInfo(String single_limit, String user_limit, String logo_name){
//		this.single_limit = single_limit;
//		this.user_limit = user_limit;
//		this.logo_name = logo_name;
//	}
	

	public String getSingle_limit() {
		return single_limit;
	}

	public void setSingle_limit(String single_limit) {
		this.single_limit = single_limit;
	}

	public String getUser_limit() {
		return user_limit;
	}

	public void setUser_limit(String user_limit) {
		this.user_limit = user_limit;
	}

	public String getLogo_name() {
		return logo_name;
	}

	public void setLogo_name(String logo_name) {
		this.logo_name = logo_name;
	}

}
