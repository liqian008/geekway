package com.bruce.geekway.service.impl.klh;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bruce.geekway.dao.klh.IKlhUserProfileDao;
import com.bruce.geekway.model.KlhSetting;
import com.bruce.geekway.model.KlhUserProfile;
import com.bruce.geekway.model.KlhUserProfileCriteria;
import com.bruce.geekway.model.KlhUserScoreLog;
import com.bruce.geekway.service.klh.IKlhSettingService;
import com.bruce.geekway.service.klh.IKlhUserProfileService;
import com.bruce.geekway.service.klh.IKlhUserScoreLogService;
import com.bruce.geekway.utils.DateUtil;

@Service
public class KlhUserProfileServiceImpl implements IKlhUserProfileService{
	
	
	
	@Autowired
	private IKlhUserProfileDao klhUserProfileDao;
	@Autowired
	private IKlhSettingService klhSettingService;
	@Autowired
	private IKlhUserScoreLogService klhUserScoreLogService;
	
	@Override
	public int save(KlhUserProfile t) {
		return klhUserProfileDao.save(t);
	}

	@Override
	public int updateById(KlhUserProfile t) {
		return klhUserProfileDao.updateById(t);
	}

	@Override
	public int deleteById(Integer id) {
		return klhUserProfileDao.deleteById(id);
	}

	@Override
	public KlhUserProfile loadById(Integer id) {
		return klhUserProfileDao.loadById(id);
	}

	@Override
	public List<KlhUserProfile> queryAll() {
		return klhUserProfileDao.queryAll();
	}
	
	public KlhUserProfile loadByOpenid(String openid){
		return klhUserProfileDao.loadByOpenid(openid);
	}
	
	@Override
	public int bindProfile(String userOpenId, KlhUserProfile userProfile) {
		int result = save(userProfile);
		if(result>0){
			//加载设置项，以确定是否有积分变更
			KlhSetting klhSetting = klhSettingService.loadKlhSetting();
			if(klhSetting!=null&&klhSetting.getBindScore()>0){
				Date currentTime = new Date();
				//增加积分记录
				int bindScore = klhSetting.getBindScore();
				KlhUserScoreLog userScoreLog = new KlhUserScoreLog();
				userScoreLog.setUserOpenId(userOpenId);
				userScoreLog.setScoreChange(bindScore);
				userScoreLog.setCreateTime(currentTime);
				userScoreLog.setReason("绑定用户信息，增加【"+bindScore+"】积分, "+DateUtil.DATE_FORMAT_YMDHMS.format(currentTime));
				return klhUserScoreLogService.save(userScoreLog);
			}
		}
		return 0;
	}

	public IKlhUserProfileDao getKlhUserProfileDao() {
		return klhUserProfileDao;
	}

	public void setKlhUserProfileDao(IKlhUserProfileDao klhUserProfileDao) {
		this.klhUserProfileDao = klhUserProfileDao;
	}

	public IKlhSettingService getKlhSettingService() {
		return klhSettingService;
	}

	public void setKlhSettingService(IKlhSettingService klhSettingService) {
		this.klhSettingService = klhSettingService;
	}

	public IKlhUserScoreLogService getKlhUserScoreLogService() {
		return klhUserScoreLogService;
	}

	public void setKlhUserScoreLogService(IKlhUserScoreLogService klhUserScoreLogService) {
		this.klhUserScoreLogService = klhUserScoreLogService;
	}

	@Override
	public int updateByCriteria(KlhUserProfile t,
			KlhUserProfileCriteria criteria) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int deleteByCriteria(KlhUserProfileCriteria criteria) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public List<KlhUserProfile> queryAll(String orderByClause) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<KlhUserProfile> queryByCriteria(KlhUserProfileCriteria criteria) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public int countByCriteria(KlhUserProfileCriteria criteria) {
		// TODO Auto-generated method stub
		return 0;
	}
	
}
