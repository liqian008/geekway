package com.bruce.geekway.utils;

import org.apache.commons.lang3.StringUtils;

import com.bruce.foundation.model.share.GenericSharedInfo;
import com.bruce.geekway.constants.ConstConfig;

/**
 * 微信分享util类
 * @author liqian
 * 
 */
public class WxShareUtil {

	/**
	 * 默认分享内容
	 */
	public static final GenericSharedInfo.WxSharedInfo WX_SHARE_INFO_DEFAULT = new GenericSharedInfo.WxSharedInfo(ConstConfig.WX_SHARE_TITLE_DEFAULT, ConstConfig.WX_SHARE_CONTENT_DEFAULT,
			ConstConfig.WX_SHARE_ICON_URL_DEFAULT, ConstConfig.WX_SHARE_LINK_DEFAULT);

	public static GenericSharedInfo.WxSharedInfo wxShare(String wxShareTile, String wxShareContent, String wxShareIconUrl, String wxShareLink) {

		if (!StringUtils.isBlank(wxShareTile) && !StringUtils.isBlank(wxShareContent) && !StringUtils.isBlank(wxShareIconUrl) && !StringUtils.isBlank(wxShareLink)) {
			return new GenericSharedInfo.WxSharedInfo(wxShareTile, wxShareContent, wxShareIconUrl, wxShareLink);
		}
		return WX_SHARE_INFO_DEFAULT;
	}

}
