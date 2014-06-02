package com.bruce.geekway.service.klh;

import java.util.List;

import com.bruce.geekway.model.KlhUserScoreHistory;
import com.bruce.geekway.service.IBaseService;

public interface IKlhUserScoreHistoryService extends IBaseService<KlhUserScoreHistory, Integer> {

	public List<KlhUserScoreHistory> queryByUserId(int userId);

}