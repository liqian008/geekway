package com.bruce.geekway.service.klh;

import java.util.List;

import com.bruce.foundation.service.IFoundationService;
import com.bruce.geekway.model.KlhUserScoreLog;
import com.bruce.geekway.model.KlhUserScoreLogCriteria;

public interface IKlhUserScoreLogService extends IFoundationService<KlhUserScoreLog, Integer, KlhUserScoreLogCriteria> {

	public List<KlhUserScoreLog> queryByUserOpenId(String userOpenId);
	
//	public int queryTotalScoreByUserOpenId(String userOpenId);
	
	public int queryCurrentScoreByUserOpenId(String userOpenId);

}