package com.bruce.geekway.dao.klh;

import java.util.List;

import com.bruce.geekway.dao.IBaseDao;
import com.bruce.geekway.model.KlhUserScoreLog;

public interface IKlhUserScoreLogDao extends IBaseDao<KlhUserScoreLog, Integer> {

	public List<KlhUserScoreLog> queryByUserOpenId(String userOpenId);


}
