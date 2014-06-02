package com.bruce.geekway.dao.klh;

import java.util.Date;

import com.bruce.geekway.dao.IBaseDao;
import com.bruce.geekway.model.KlhDailySign;

public interface IKlhDailySignDao extends IBaseDao<KlhDailySign, Integer> {
	
	/**
	 * 判断今日是否签到过
	 * @param userOpenId
	 * @param today
	 * @return
	 */
	public boolean hasSigned(String userOpenId, Date date);
	
	
}
