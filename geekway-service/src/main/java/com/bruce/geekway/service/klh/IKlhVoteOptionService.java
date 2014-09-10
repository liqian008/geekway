package com.bruce.geekway.service.klh;

import java.util.List;

import com.bruce.foundation.service.IFoundationService;
import com.bruce.geekway.model.KlhVoteOption;
import com.bruce.geekway.model.KlhVoteOptionCriteria;

public interface IKlhVoteOptionService extends IFoundationService<KlhVoteOption, Integer, KlhVoteOptionCriteria> {

	public List<KlhVoteOption> queryByVoteId(int voteId);

}