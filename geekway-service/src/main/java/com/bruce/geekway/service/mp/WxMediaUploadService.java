package com.bruce.geekway.service.mp;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bruce.geekway.model.wx.WxMediaTypeEnum;
import com.bruce.geekway.model.wx.json.response.WxMediaUploadResult;
import com.bruce.geekway.utils.ConfigUtil;
import com.bruce.geekway.utils.JsonUtil;
import com.bruce.geekway.utils.WxHttpUtil;

/**
 * 微信多媒体上传service
 * @author liqian
 *
 */
@Service
public class WxMediaUploadService extends WxBaseService {

	private static final String WX_MEDIA_UPLOAD_API = ConfigUtil.getString("weixinmp_media_upload_url");
	@Autowired
	private WxMpTokenService mpTokenService;

	/**
	 * 上传图片
	 * @param data
	 * @return
	 */
	public WxMediaUploadResult uploadImage(byte[] data) {
		return uploadMedia(WxMediaTypeEnum.IMAGE, data);
	}
	
	public WxMediaUploadResult uploadVoice(byte[] data) {
		return uploadMedia(WxMediaTypeEnum.VOICE, data);
	}
	
	public WxMediaUploadResult uploadThumb(byte[] data) {
		return uploadMedia(WxMediaTypeEnum.THUMB, data);
	}
	
	public WxMediaUploadResult uploadVideo(byte[] data) {
		return uploadMedia(WxMediaTypeEnum.VIDEO, data);
	}
	
	/**
	 * 上传资源
	 * 注意事项：
	 * 图片（image）: 1M，支持JPG格式
	 * 语音（voice）：2M，播放长度不超过60s，支持AMR\MP3格式
	 * 视频（video）：10MB，支持MP4格式
	 * 缩略图（thumb）：64KB，支持JPG格式
	 * @param typeEnum
	 * @param data
	 * @return
	 */
	private WxMediaUploadResult uploadMedia(WxMediaTypeEnum typeEnum, byte[] data) {
		String accessToken = mpTokenService.getMpAccessToken();
		Map<String, String> params = WxHttpUtil.buildAccessTokenParams(accessToken);
		params.put("type", typeEnum.toString());
		
		String uploadResultStr = WxHttpUtil.postMultipartRequest(WX_MEDIA_UPLOAD_API, params, data);
		if(uploadResultStr!=null){
			return JsonUtil.gson.fromJson(uploadResultStr, WxMediaUploadResult.class);
		}else{
			return null;
		}
	}
	
	
	
	public WxMpTokenService getMpTokenService() {
		return mpTokenService;
	}

	public void setMpTokenService(WxMpTokenService mpTokenService) {
		this.mpTokenService = mpTokenService;
	}

}
