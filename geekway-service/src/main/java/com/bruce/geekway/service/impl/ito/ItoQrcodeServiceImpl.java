package com.bruce.geekway.service.impl.ito;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bruce.geekway.model.WxDefaultReply;
import com.bruce.geekway.model.ito.json.ItoWwjQrcodeResult;
import com.bruce.geekway.service.IWxDefaultReplyService;
import com.bruce.geekway.service.ito.IItoQrcodeService;
import com.bruce.geekway.utils.JsonUtil;
import com.bruce.geekway.utils.WxHttpUtil;

@Service
public class ItoQrcodeServiceImpl implements IItoQrcodeService{
	
	
	private static final Logger logger = LoggerFactory.getLogger(ItoQrcodeServiceImpl.class);
	
	@Autowired
	private IWxDefaultReplyService defaultReplyService;
	
	/**
	 * 
	 * @return
	 */
	@Override
	public String getQrcodeUrl(boolean isDebug){
		String qrcodeUrl = null;
		WxDefaultReply defaultReply = defaultReplyService.loadById(1);
		
		boolean isOpen = defaultReply!=null&&Short.valueOf("99").equals(defaultReply.getStatus());//99时开启二维码
		if(isOpen&&!isDebug){
			//从第三方服务获取二维码&展示给用户
			String wwjUrl = "http://www.itocases.com:90/api/QRCode";
			String result = WxHttpUtil.getRequest(wwjUrl, null);
			if(!StringUtils.isBlank(result)){
				ItoWwjQrcodeResult wwjResult = JsonUtil.gson.fromJson(result, ItoWwjQrcodeResult.class);
				if(wwjResult!=null&&wwjResult.getErrorCode()==0){
					qrcodeUrl = wwjResult.getImageUrl();
					logger.info("从发码中心获取新二维码成功!["+qrcodeUrl+"]");
				}
			}else{
				logger.error("从发码中心获取新二维码失败!");
			}
		}
		return qrcodeUrl;
	}

	public IWxDefaultReplyService getDefaultReplyService() {
		return defaultReplyService;
	}

	public void setDefaultReplyService(IWxDefaultReplyService defaultReplyService) {
		this.defaultReplyService = defaultReplyService;
	}

	
	
}