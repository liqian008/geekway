package com.bruce.geekway.service.impl.klh;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bruce.geekway.dao.klh.IKlhVoteResultDao;
import com.bruce.geekway.model.KlhSetting;
import com.bruce.geekway.model.KlhUserScoreLog;
import com.bruce.geekway.model.KlhVote;
import com.bruce.geekway.model.KlhVoteResult;
import com.bruce.geekway.model.KlhVoteResultCriteria;
import com.bruce.geekway.service.klh.IKlhSettingService;
import com.bruce.geekway.service.klh.IKlhUserScoreLogService;
import com.bruce.geekway.service.klh.IKlhVoteResultService;
import com.bruce.geekway.service.klh.IKlhVoteService;
import com.bruce.geekway.utils.DateUtil;

@Service
public class KlhVoteResultServiceImpl implements IKlhVoteResultService{
	
	@Autowired
	private IKlhVoteResultDao klhVoteResultDao;
	@Autowired
	private IKlhVoteService klhVoteVoteService;
	@Autowired
	private IKlhSettingService klhSettingService;
	@Autowired
	private IKlhUserScoreLogService klhUserScoreLogService;
	
	@Override
	public int save(KlhVoteResult t) {
		return klhVoteResultDao.save(t);
	}

	@Override
	public int updateById(KlhVoteResult t) {
		return klhVoteResultDao.updateById(t);
	}

	@Override
	public int deleteById(Integer id) {
		return klhVoteResultDao.deleteById(id);
	}

	@Override
	public KlhVoteResult loadById(Integer id) {
		return klhVoteResultDao.loadById(id);
	}

	@Override
	public List<KlhVoteResult> queryAll() {
		return klhVoteResultDao.queryAll();
	}
	
	@Override
	public List<KlhVoteResult> queryByVoteId(int voteId) {
		return klhVoteResultDao.queryByVoteId(voteId);
	}
	
	/**
	 * 投票
	 * @param userOpenId
	 * @param voteId
	 * @param voteOptionId
	 * @return
	 */
	public int vote(String userOpenId, int voteId, int[] voteOptionIds) {

		//检查投票状态
		KlhVote vote = klhVoteVoteService.loadById(voteId);
		if (vote != null && vote.getId() > 0) {
			//TODO 检查是否可以重复投票
			
			//检查单选多选
			if(vote.getMaxPickLimit()!=null&&vote.getMaxPickLimit()>=voteOptionIds.length){
				//保存投票结果
				Date currentTime = new Date();
				for (int voteOptionId : voteOptionIds) {
					KlhVoteResult voteResult = new KlhVoteResult();
					voteResult.setVoteId(voteId);
					voteResult.setVoteOptionId(voteOptionId);
					voteResult.setCreateTime(currentTime);
					klhVoteResultDao.save(voteResult);
				}
	
				// 积分变更
				KlhSetting klhSetting = klhSettingService.loadKlhSetting();
				if (klhSetting != null && klhSetting.getVoteScore() != null
						&& klhSetting.getVoteScore() > 0) {
					// 增加积分记录
					int voteScore = klhSetting.getVoteScore();
					KlhUserScoreLog userScoreLog = new KlhUserScoreLog();
					userScoreLog.setUserOpenId(userOpenId);
					userScoreLog.setScoreChange(voteScore);
					userScoreLog.setCreateTime(currentTime);
					userScoreLog.setReason("用户投票，增加【" + voteScore + "】积分, "
							+ DateUtil.DATE_FORMAT_YMDHMS.format(currentTime));
					klhUserScoreLogService.save(userScoreLog);
					return 1;
				}
			}
		}
		return 0;
	}
	
	

	public IKlhVoteResultDao getKlhVoteResultDao() {
		return klhVoteResultDao;
	}

	public void setKlhVoteResultDao(IKlhVoteResultDao klhVoteResultDao) {
		this.klhVoteResultDao = klhVoteResultDao;
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

	@Override
	public int updateByCriteria(KlhVoteResult t, KlhVoteResultCriteria criteria) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int deleteByCriteria(KlhVoteResultCriteria criteria) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public List<KlhVoteResult> queryAll(String orderByClause) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<KlhVoteResult> queryByCriteria(KlhVoteResultCriteria criteria) {
		// TODO Auto-generated method stub
		return null;
	}
	
	
	
}