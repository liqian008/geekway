package com.bruce.geekway.constants;

import com.bruce.geekway.utils.ConfigUtil;

public interface ConstUpload {
	
	/*七牛开发者的appkey和secretkey*/
	public static final String UPLOAD_QINIU_APP_KEY= ConfigUtil.getString("upload_qiniu_appkey");
	public static final String UPLOAD_QINIU_SECRET_KEY = ConfigUtil.getString("upload_qiniu_secretkey");
	/*七牛的bucket*/
	public static final String UPLOAD_QINIU_BUCKET = ConfigUtil.getString("upload_qiniu_bucket");
}
