package com.bruce.geekway.service.klh;

import java.util.List;

import com.bruce.geekway.model.KlhVoteResult;
import com.bruce.geekway.service.IBaseService;

public interface IKlhVoteResultService extends IBaseService<KlhVoteResult, Integer> {

	public List<KlhVoteResult> queryByVoteId(int voteId);

}