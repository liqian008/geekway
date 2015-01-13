package com.bruce.geekway.service.mp;

import java.io.File;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;

import com.bruce.foundation.util.JsonUtil;
import com.bruce.geekway.constants.ConstWeixin;
import com.bruce.geekway.model.wx.WxMediaTypeEnum;
import com.bruce.geekway.model.wx.json.response.WxMediaUploadResult;
import com.bruce.geekway.service.IWxAccessTokenService;
import com.bruce.geekway.utils.HttpUtil;

/**
 * 微信多媒体上传service(mp包下的service均为对weixin api的封装)
 * @author liqian
 *
 */
public class WxMpMediaUploadService extends WxBaseService {

	
	
	private static final String HTTP_CONTENT_TYPE_IMAGE = "image/jpeg";
	
//	@Autowired
//	private WxMpTokenService mpTokenService;
	@Autowired
	private IWxAccessTokenService wxAccessTokenService;

	/**
	 * 上传图片
	 * @param data
	 * @return
	 */
	public WxMediaUploadResult uploadImage(File mediaFile) {
		return uploadMedia(WxMediaTypeEnum.IMAGE, HTTP_CONTENT_TYPE_IMAGE, mediaFile);
	}
	
	/**
	 * 上传缩略图文件
	 * @param mediaFile
	 * @return
	 */
	public WxMediaUploadResult uploadThumb(File mediaFile) {
		return uploadMedia(WxMediaTypeEnum.THUMB, HTTP_CONTENT_TYPE_IMAGE, mediaFile);
	}
	
	/**
	 * 上传语音
	 * @param mediaFile
	 * @return
	 */
	public WxMediaUploadResult uploadVoice(File mediaFile) {
		return uploadMedia(WxMediaTypeEnum.VOICE, HTTP_CONTENT_TYPE_IMAGE, mediaFile);
	}

//	
//	public WxMediaUploadResult uploadVideo(byte[] data) {
//		return uploadMedia(WxMediaTypeEnum.VIDEO, contentType, data);
//	}
	
	/**
	 * 上传资源
	 * 注意事项：
	 * 图片（image）: 1M，支持JPG格式
	 * 语音（voice）：2M，播放长度不超过60s，支持AMR\MP3格式
	 * 视频（video）：10MB，支持MP4格式
	 * 缩略图（thumb）：64KB，支持JPG格式
	 * @param typeEnum
	 * @param contentType
	 * @param data
	 * @return
	 */
	private WxMediaUploadResult uploadMedia(WxMediaTypeEnum typeEnum, String contentType, File file) {
		String accessToken = wxAccessTokenService.getCachedAccessToken();
		
		Map<String, String> params = buildAccessTokenParams(accessToken);
		params.put("type", typeEnum.toString());
		
		String uploadResultStr = HttpUtil.postMultipartRequest(ConstWeixin.WX_MEDIA_UPLOAD_API, params, file, contentType);
		if(uploadResultStr!=null){
			return JsonUtil.gson.fromJson(uploadResultStr, WxMediaUploadResult.class);
		}else{
			return null;
		}
	}
	
//	public WxMpTokenService getMpTokenService() {
//		return mpTokenService;
//	}
//
//	public void setMpTokenService(WxMpTokenService mpTokenService) {
//		this.mpTokenService = mpTokenService;
//	}

	public IWxAccessTokenService getWxAccessTokenService() {
		return wxAccessTokenService;
	}

	public void setWxAccessTokenService(IWxAccessTokenService wxAccessTokenService) {
		this.wxAccessTokenService = wxAccessTokenService;
	}

	
}

