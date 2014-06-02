package com.bruce.geekway.dao.klh;

import java.util.List;

import com.bruce.geekway.dao.IBaseDao;
import com.bruce.geekway.model.KlhVoteResult;

public interface IKlhVoteResultDao extends IBaseDao<KlhVoteResult, Integer> {

	public List<KlhVoteResult> queryByVoteId(int voteId); 

}
