package com.bruce.geekway.dao.impl;

import java.util.List;

import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.bruce.geekway.dao.IWxMpUserDao;
import com.bruce.geekway.dao.mapper.WxMpUserMapper;
import com.bruce.geekway.model.WxMpUserCriteria;
import com.bruce.geekway.model.WxMpUser;


@Repository 
public class WxMpUserDaoImpl implements IWxMpUserDao, InitializingBean {

     @Autowired
    private WxMpUserMapper wxMpUserMapper;

    @Override
    public int save(WxMpUser t) {
        return wxMpUserMapper.insert(t);
    }

    @Override
    public int updateById(WxMpUser t) {
        return wxMpUserMapper.updateByPrimaryKeySelective(t);
    }

    @Override
    public int deleteById(Integer id) {
        return wxMpUserMapper.deleteByPrimaryKey(id);
    }

    @Override
    public WxMpUser loadById(Integer id) {
        return wxMpUserMapper.selectByPrimaryKey(id);
    }

    @Override
    public List<WxMpUser> queryAll() {
        return wxMpUserMapper.selectByExample(null);
    }

    @Override
    public List<WxMpUser> fallLoadList(Integer tailId, int limit) {
        return null;
    }
    
//    @Override
//	public int insertOrUpdate(WxMpUser wxMpUser) {
////    	if(wxMpUser!=null&&wxMpUser.getId()!=null){//检查用户是否已经存在
////	    	WxMpUserCriteria criteria = new WxMpUserCriteria();
////	        criteria.createCriteria().andOpenIdEqualTo(wxMpUser.getOpenId());
////			//return wxMpUserMapper.updateByExampleSelective(wxMpUser, criteria);
////	        //TODO saveOrUpdate
////	        return 0;
////    	}
////		return 0;
//    	return save(wxMpUser);
//	}


	@Override
	public List<WxMpUser> getUserListBySyncStatus(short syncStatus) {
		WxMpUserCriteria criteria = new WxMpUserCriteria();
		//条件为已订阅但未同步
		criteria.createCriteria().andSubscribeStatusEqualTo((short) 1).andSyncStatusEqualTo(syncStatus);
		return wxMpUserMapper.selectByExample(criteria);
	}
	
	
	@Override
	public int updateUserSubscribeStatus(String userOpenId, short subscribeStatus){
		WxMpUser mpUser = new WxMpUser();
		mpUser.setSubscribeStatus(subscribeStatus);//未订阅为0，订阅为0
		
		WxMpUserCriteria criteria = new WxMpUserCriteria();
		criteria.createCriteria().andOpenIdEqualTo(userOpenId);
		return wxMpUserMapper.updateByExampleSelective(mpUser, criteria);
	}
	
	/**
	 * 
	 */
	public WxMpUser loadByOpenId(String userOpenId){
		WxMpUserCriteria criteria = new WxMpUserCriteria();
		criteria.createCriteria().andOpenIdEqualTo(userOpenId);
		List<WxMpUser> mpUserList = wxMpUserMapper.selectByExample(criteria);
		if(mpUserList!=null&&mpUserList.size()>0){
			return mpUserList.get(0);
		}
		return null;
	}

    
    @Override
    public void afterPropertiesSet() throws Exception {
    }

	public WxMpUserMapper getWxMpUserMapper() {
		return wxMpUserMapper;
	}

	public void setWxMpUserMapper(WxMpUserMapper wxMpUserMapper) {
		this.wxMpUserMapper = wxMpUserMapper;
	}

}
