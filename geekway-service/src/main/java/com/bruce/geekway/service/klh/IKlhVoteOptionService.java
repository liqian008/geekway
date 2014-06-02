package com.bruce.geekway.service.klh;

import java.util.List;

import com.bruce.geekway.model.KlhVoteOption;
import com.bruce.geekway.service.IBaseService;

public interface IKlhVoteOptionService extends IBaseService<KlhVoteOption, Integer> {

	public List<KlhVoteOption> queryByVoteId(int voteId);

}