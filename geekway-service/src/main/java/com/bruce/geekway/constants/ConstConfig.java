package com.bruce.geekway.constants;

import org.apache.commons.lang3.BooleanUtils;
import org.apache.commons.lang3.math.NumberUtils;

import com.bruce.geekway.utils.ConfigUtil;

public interface ConstConfig {
	
	/*是否启用redis*/
	public static final boolean REDIS_ENABLE = BooleanUtils.toBoolean(ConfigUtil.getString("redis_enable"));
	/*是否启用memcache*/
	public static final boolean MEMC_ENABLE = BooleanUtils.toBoolean(ConfigUtil.getString("memc_enable"));

	//分布式的服务器编号index, 从1开始
	public static final String SERVER_INDEX = ConfigUtil.getString("server_index");
	
	/*默认每页显示的条数*/
	public static final int PAGE_SIZE_DEFAULT = NumberUtils.toInt(ConfigUtil.getString("page_size_default"), 20);

	
	/* contextPath */
	public static final String CONTEXT_PATH = ConfigUtil.getString("contextPath");
	
	/* 静态资源的域名 */
	public static final String RESOURCE_DOMAIN = ConfigUtil.getString("resource_domain");
	
	/* 文件系统的基本路径, 通常为$localDir/staticFile */
	public static final String UPLOAD_PATH_BASE = ConfigUtil.getString("upload_path_base");
	
	/* 本地文件系统的url前缀，补齐后续路径&文件名则可成为真正能访问的文件*/
	public static final String UPLOAD_URL_BASE = ConfigUtil.getString("upload_url_base");
	/* 完整的url前缀*/
	public static final String UPLOAD_URL_FULL = RESOURCE_DOMAIN + CONTEXT_PATH + UPLOAD_URL_BASE;
	
	/*七牛开发者的appkey和secretkey*/
	public static final String UPLOAD_QINIU_APP_KEY= ConfigUtil.getString("upload_qiniu_appkey");
	public static final String UPLOAD_QINIU_SECRET_KEY = ConfigUtil.getString("upload_qiniu_secretkey");
	
	/*七牛的bucket*/
	public static final String UPLOAD_QINIU_BUCKET = ConfigUtil.getString("upload_qiniu_bucket");
	/*七牛的bucket*/
	public static final String UPLOAD_QINIU_BIND_DOMAIN = ConfigUtil.getString("upload_qiniu_bind_domain");
	
	
	/*默认微信分享的标题*/
	public static final String WX_SHARE_TITLE_DEFAULT =  ConfigUtil.getString("wx_share_title_default");
	/*默认微信分享的内容*/
	public static final String WX_SHARE_CONTENT_DEFAULT =  ConfigUtil.getString("wx_share_content_default");
	/*默认微信分享的iconUrl*/
	public static final String WX_SHARE_ICON_URL_DEFAULT =  ConfigUtil.getString("wx_share_icon_url_default");
	/*默认微信分享的链接*/
	public static final String WX_SHARE_LINK_DEFAULT =  ConfigUtil.getString("wx_share_link_default");
}
