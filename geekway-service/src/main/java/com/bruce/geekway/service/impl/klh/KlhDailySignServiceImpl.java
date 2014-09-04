package com.bruce.geekway.service.impl.klh;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bruce.geekway.dao.klh.IKlhDailySignDao;
import com.bruce.geekway.model.KlhDailySign;
import com.bruce.geekway.model.KlhSetting;
import com.bruce.geekway.model.KlhUserScoreLog;
import com.bruce.geekway.service.klh.IKlhDailySignService;
import com.bruce.geekway.service.klh.IKlhSettingService;
import com.bruce.geekway.service.klh.IKlhUserScoreLogService;
import com.bruce.geekway.utils.DateUtil;

@Service
public class KlhDailySignServiceImpl implements IKlhDailySignService{
	
	@Autowired
	private IKlhDailySignDao klhDailySignDao;
	@Autowired
	private IKlhSettingService klhSettingService;
	@Autowired
	private IKlhUserScoreLogService klhUserScoreLogService;
	
	@Override
	public int save(KlhDailySign t) {
		return klhDailySignDao.save(t);
	}

	@Override
	public int updateById(KlhDailySign t) {
		return klhDailySignDao.updateById(t);
	}

	@Override
	public int deleteById(Integer id) {
		return klhDailySignDao.deleteById(id);
	}

	@Override
	public KlhDailySign loadById(Integer id) {
		return klhDailySignDao.loadById(id);
	}

	@Override
	public List<KlhDailySign> queryAll() {
		return klhDailySignDao.queryAll();
	}
	
	
	/**
	 * 
	 */
	@Override
	public int sign(String userOpenId){
		Date currentTime = new Date();
		System.out.println("=========1=========");
		if(!klhDailySignDao.hasSigned(userOpenId, currentTime)){
			System.out.println("=========2=========");
			
			KlhDailySign dailySign = new KlhDailySign();
			dailySign.setUserOpenId(userOpenId);
			dailySign.setSignDate(currentTime);
			dailySign.setCreateTime(currentTime);
			int result = klhDailySignDao.save(dailySign);
			if(result>0){
				System.out.println("=========3=========");
				
				//积分变更
				KlhSetting klhSetting = klhSettingService.loadKlhSetting();
				if(klhSetting!=null&&klhSetting.getSignScore()!=null&&klhSetting.getSignScore()>0){
					System.out.println("=========4=========");
					
					//增加积分记录
					int signScore = klhSetting.getSignScore();
					KlhUserScoreLog userScoreLog = new KlhUserScoreLog();
					userScoreLog.setUserOpenId(userOpenId);
					userScoreLog.setScoreChange(signScore);
					userScoreLog.setCreateTime(currentTime);
					userScoreLog.setReason("用户签到，增加【"+signScore+"】积分");
					return klhUserScoreLogService.save(userScoreLog);
				}
			}
		}
		System.out.println("=========5=========");
		
		return 0;
	}
	
//	/**
//	 * 判断今日是否签到过
//	 * @param userOpenId
//	 * @param today
//	 * @return
//	 */
//	public boolean hasSigned(String userOpenId, String today){
//		return klhDailySignDao.hasSigned(userOpenId, today);
//	}
	

	public IKlhDailySignDao getKlhDailySignDao() {
		return klhDailySignDao;
	}

	public void setKlhDailySignDao(IKlhDailySignDao klhDailySignDao) {
		this.klhDailySignDao = klhDailySignDao;
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
	
	
	
}
