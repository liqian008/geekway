package com.bruce.geekway.dao.klh;

import java.util.List;

import com.bruce.geekway.dao.IBaseDao;
import com.bruce.geekway.model.KlhUserScoreHistory;

public interface IKlhUserScoreHistoryDao extends IBaseDao<KlhUserScoreHistory, Integer> {

	public List<KlhUserScoreHistory> queryByUserId(int userId);

	public int queryUserScore(int userId);

}
