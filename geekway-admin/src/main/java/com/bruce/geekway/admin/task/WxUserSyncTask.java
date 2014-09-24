package com.bruce.geekway.admin.task;


import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.bruce.geekway.model.WxUser;
import com.bruce.geekway.model.wx.json.response.WxUserInfoResult;
import com.bruce.geekway.service.IWxUserService;
import com.bruce.geekway.service.mp.WxMpUserService;

@Component
public class WxUserSyncTask implements Runnable{

	@Autowired
	private WxMpUserService wxMpUserService;
	@Autowired
	private IWxUserService wxUserService;
	
	private boolean running;
	
	@Override
	public void run() {
		
		if(!running){
			System.out.println("==========wxMpUserService====="+wxMpUserService+"=======wxUserService========="+wxUserService);
			System.out.println("=================sync mp userinfo from wx");
			try{
				List<WxUser> userList = wxUserService.getUserListBySyncStatus((short) 0);
				if(userList!=null&&userList.size()>0){
					for(WxUser user: userList){
						String userOpenId = user.getOpenId();
						WxUserInfoResult userInfoResult =  wxMpUserService.getUser(userOpenId);
						//有效数据
						if(userInfoResult!=null&&userInfoResult.getErrcode()==0){
							user.setOpenId(userOpenId);
							user.setNickname(userInfoResult.getNickname());
							user.setCity(userInfoResult.getCity());
							user.setCountry(userInfoResult.getCountry());
							user.setProvince(userInfoResult.getProvince());
							user.setHeadImgUrl(userInfoResult.getHeadimgurl());
							user.setLanguage(userInfoResult.getLanguage());
							user.setSubscribeStatus(userInfoResult.getSubscribe());
							user.setSex(userInfoResult.getSex());
							user.setUpdateTime(new Date());
							
							user.setSyncStatus((short) 1);
							int result = wxUserService.updateById(user);
						}
						//休息5秒，避免被微信当做恶意攻击
						Thread.sleep(5000);
					}
				}
			}catch(Exception e){
				running = false;
			}
		}
		running = false;
		
		
		//TODO 放在线程中执行
//		WxUserInfoResult userInfoResult =  wxUserService.getUser(openId);
//		if(userInfoResult!=null){
//			WxMpUser wxMpUser = new WxMpUser();
//			wxMpUser.setOpenId(openId);
//			wxMpUser.setNickname(userInfoResult.getNickname());
//			wxMpUser.setCity(userInfoResult.getCity());
//			wxMpUser.setCountry(userInfoResult.getCountry());
//			wxMpUser.setProvince(userInfoResult.getProvince());
//			wxMpUser.setHeadImgUrl(userInfoResult.getHeadimgurl());
//			wxMpUser.setLanguage(userInfoResult.getLanguage());
//			wxMpUser.setSubscribeStatus(userInfoResult.getSubscribe());
//			wxMpUser.setSex(userInfoResult.getSex());
//			wxMpUser.setCreateTime(new Date());
//			return save(wxMpUser);
//		}	
//		return 0;
		
		
		
	}
	
	
}
