package com.bruce.geekway.admin.task;


import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.bruce.geekway.dao.IWxMpUserDao;
import com.bruce.geekway.model.WxMpUser;
import com.bruce.geekway.model.wx.json.response.WxUserInfoResult;
import com.bruce.geekway.service.mp.WxUserService;

@Component
public class WxUserSyncTask implements Runnable{

	@Autowired
	private WxUserService wxUserService;
	
	@Autowired
	private IWxMpUserDao wxMpUserDao;
	
	private boolean running;
	
	@Override
	public void run() {
		
		if(!running){
			System.out.println("==========wxMpUserDao====="+wxMpUserDao+"=======wxUserService========="+wxUserService);
			System.out.println("=================sync mp userinfo from wx");
			try{
				List<WxMpUser> mpUserList = wxMpUserDao.getUserListBySyncStatus((short) 0);
				if(mpUserList!=null&&mpUserList.size()>0){
					for(WxMpUser mpUser: mpUserList){
						String userOpenId = mpUser.getOpenId();
						WxUserInfoResult userInfoResult =  wxUserService.getUser(userOpenId);
						
						mpUser.setOpenId(userOpenId);
						mpUser.setNickname(userInfoResult.getNickname());
						mpUser.setCity(userInfoResult.getCity());
						mpUser.setCountry(userInfoResult.getCountry());
						mpUser.setProvince(userInfoResult.getProvince());
						mpUser.setHeadImgUrl(userInfoResult.getHeadimgurl());
						mpUser.setLanguage(userInfoResult.getLanguage());
						mpUser.setSubscribeStatus(userInfoResult.getSubscribe());
						mpUser.setSex(userInfoResult.getSex());
						mpUser.setUpdateTime(new Date());
						
						mpUser.setSyncStatus((short) 1);
						
						int result = wxMpUserDao.updateById(mpUser);
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
