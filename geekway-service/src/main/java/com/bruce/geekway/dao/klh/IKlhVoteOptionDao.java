package com.bruce.geekway.dao.klh;

import java.util.List;

import com.bruce.geekway.dao.IBaseDao;
import com.bruce.geekway.model.KlhVoteOption;

public interface IKlhVoteOptionDao extends IBaseDao<KlhVoteOption, Integer> {

	public List<KlhVoteOption> queryByVoteId(int voteId); 

}
