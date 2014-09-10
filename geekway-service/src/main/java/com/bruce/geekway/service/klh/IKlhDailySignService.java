package com.bruce.geekway.service.klh;

import com.bruce.foundation.service.IFoundationService;
import com.bruce.geekway.model.KlhDailySign;
import com.bruce.geekway.model.KlhDailySignCriteria;

public interface IKlhDailySignService extends IFoundationService<KlhDailySign, Integer, KlhDailySignCriteria> {
	
	
	/**
	 * 签到
	 * @param userOpenId
	 * @return
	 */
	public int sign(String userOpenId);
	
//	/**
//	 * 判断今日是否签到过
//	 * @param userOpenId
//	 * @param today
//	 * @return
//	 */
//	public boolean hasSigned(String userOpenId, String today);
	
	
}