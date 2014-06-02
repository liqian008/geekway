package com.bruce.geekway.service.klh;

import java.util.List;

import com.bruce.geekway.model.KlhUserScoreLog;
import com.bruce.geekway.service.IBaseService;

public interface IKlhUserScoreLogService extends IBaseService<KlhUserScoreLog, Integer> {

	public List<KlhUserScoreLog> queryByUserOpenId(String userOpenId);

}