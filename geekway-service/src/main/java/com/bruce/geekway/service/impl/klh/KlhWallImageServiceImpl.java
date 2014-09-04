package com.bruce.geekway.service.impl.klh;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bruce.geekway.dao.klh.IKlhWallImageDao;
import com.bruce.geekway.data.PagingData;
import com.bruce.geekway.model.KlhDailySign;
import com.bruce.geekway.model.KlhSetting;
import com.bruce.geekway.model.KlhUserScoreLog;
import com.bruce.geekway.model.KlhWallImage;
import com.bruce.geekway.service.klh.IKlhSettingService;
import com.bruce.geekway.service.klh.IKlhUserScoreLogService;
import com.bruce.geekway.service.klh.IKlhWallImageLogService;
import com.bruce.geekway.service.klh.IKlhWallImageService;

@Service
public class KlhWallImageServiceImpl implements IKlhWallImageService{
	
	@Autowired
	private IKlhWallImageDao klhWallImageDao;
	@Autowired
	private IKlhWallImageLogService klhWallImageLogService;
	@Autowired
	private IKlhSettingService klhSettingService;
	@Autowired
	private IKlhUserScoreLogService klhUserScoreLogService;
	
	@Override
	public int save(KlhWallImage t) {
		return klhWallImageDao.save(t);
	}

	@Override
	public int updateById(KlhWallImage t) {
		return klhWallImageDao.updateById(t);
	}

	@Override
	public int deleteById(Integer id) {
		return klhWallImageDao.deleteById(id);
	}

	@Override
	public KlhWallImage loadById(Integer id) {
		return klhWallImageDao.loadById(id);
	}

	@Override
	public List<KlhWallImage> queryAll() {
		return klhWallImageDao.queryAll();
	}
	
	
	@Override
	public int increaseLike(int wallImageId, String userOpenId) {
		KlhWallImage wallImage =klhWallImageDao.loadById(wallImageId);
		if(wallImage!=null){
			KlhWallImage updateWallImage = new KlhWallImage();
			updateWallImage.setId(wallImageId);
			updateWallImage.setLikeCount(wallImage.getLikeCount()+1);
			//赞操作日志记录
			klhWallImageLogService.increaseLike(wallImageId);
			
			//增加积分记录
			KlhSetting klhSetting = klhSettingService.loadKlhSetting();
			if(klhSetting!=null&&klhSetting.getLikeScore()!=null){
				int likeScore = klhSetting.getLikeScore();
				KlhUserScoreLog userScoreLog = new KlhUserScoreLog();
				userScoreLog.setUserOpenId(userOpenId);
				userScoreLog.setScoreChange(likeScore);
				userScoreLog.setCreateTime(new Date());
				userScoreLog.setReason("赞了照片墙照片，增加【"+likeScore+"】积分");
				klhUserScoreLogService.save(userScoreLog);
			}
			return klhWallImageDao.updateById(updateWallImage);
		}
		return 0;
	}
	
	@Override
	public int increaseBrowse(int wallImageId, String userOpenId) {
		//赞操作日志记录
		return klhWallImageLogService.increaseBrowse(wallImageId);
	}
	
	
	public PagingData<KlhWallImage> pagingLatestImages(int pageNo, int pageSize){
		return klhWallImageDao.pagingLatestImages(pageNo, pageSize);
	}

	public PagingData<KlhWallImage> pagingHotestImages(int pageNo, int pageSize){
		return klhWallImageDao.pagingHotestImages(pageNo, pageSize);
	}
	
	/**
	 * 
	 */
	@Override
	public int postWallImage(KlhWallImage wallImage, String userOpenId){
		int result = save(wallImage);
		if(result>0){
			//积分变更
			KlhSetting klhSetting = klhSettingService.loadKlhSetting();
			if(klhSetting!=null&&klhSetting.getPostScore()!=null){
				//增加积分记录
				int postScore = klhSetting.getPostScore();
				KlhUserScoreLog userScoreLog = new KlhUserScoreLog();
				userScoreLog.setUserOpenId(userOpenId);
				userScoreLog.setScoreChange(postScore);
				userScoreLog.setCreateTime(new Date());
				userScoreLog.setReason("发布照片，增加【"+postScore+"】积分");
				return klhUserScoreLogService.save(userScoreLog);
			}
		}
		return 0;
	}

	public IKlhWallImageDao getKlhWallImageDao() {
		return klhWallImageDao;
	}

	public void setKlhWallImageDao(IKlhWallImageDao klhWallImageDao) {
		this.klhWallImageDao = klhWallImageDao;
	}

	public IKlhWallImageLogService getKlhWallImageLogService() {
		return klhWallImageLogService;
	}

	public void setKlhWallImageLogService(IKlhWallImageLogService klhWallImageLogService) {
		this.klhWallImageLogService = klhWallImageLogService;
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

	public void setKlhUserScoreLogService(
			IKlhUserScoreLogService klhUserScoreLogService) {
		this.klhUserScoreLogService = klhUserScoreLogService;
	}

//	@Override
//	public List<KlhWallImage> queryLatestImages(int pageSize) {
//		return klhWallImageDao.queryLatestImages(pageSize);
//	}
//
//	@Override
//	public List<KlhWallImage> queryHotestImages(int pageSize) {
//		return klhWallImageDao.queryHotestImages(pageSize);
//	}
	
	
}