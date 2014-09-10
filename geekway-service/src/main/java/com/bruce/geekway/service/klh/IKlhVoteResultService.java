package com.bruce.geekway.service.klh;

import java.util.List;

import com.bruce.foundation.service.IFoundationService;
import com.bruce.geekway.model.KlhVoteResult;
import com.bruce.geekway.model.KlhVoteResultCriteria;

public interface IKlhVoteResultService extends IFoundationService<KlhVoteResult, Integer, KlhVoteResultCriteria> {

	public List<KlhVoteResult> queryByVoteId(int voteId);

}