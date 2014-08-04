package com.bruce.geekway.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bruce.geekway.dao.mapper.WxDefaultReplyMapper;
import com.bruce.geekway.model.WxCommandCriteria;
import com.bruce.geekway.model.WxDefaultReply;
import com.bruce.geekway.model.WxDefaultReplyCriteria;
import com.bruce.geekway.service.IWxDefaultReplyService;

@Service
public class WxDefaultReplyServiceImpl implements IWxDefaultReplyService {

	@Autowired
	private WxDefaultReplyMapper wxDefaultReplyMapper;

	@Override
	public int save(WxDefaultReply t) {
		return wxDefaultReplyMapper.insertSelective(t);
	}

	@Override
	public int updateById(WxDefaultReply t) {
		return wxDefaultReplyMapper.updateByPrimaryKeySelective(t);
	}

	@Override
	public int updateByCriteria(WxDefaultReply t, WxDefaultReplyCriteria criteria) {
		return wxDefaultReplyMapper.updateByExampleSelective(t, criteria);
	}

	@Override
	public int deleteById(Integer id) {
		return wxDefaultReplyMapper.deleteByPrimaryKey(id);
	}

	@Override
	public int deleteByCriteria(WxDefaultReplyCriteria criteria) {
		return wxDefaultReplyMapper.deleteByExample(criteria);
	}

	@Override
	public WxDefaultReply loadById(Integer id) {
		return wxDefaultReplyMapper.selectByPrimaryKey(id);
	}

	@Override
	public List<WxDefaultReply> queryAll() {
		return wxDefaultReplyMapper.selectByExample(null);
	}

	@Override
	public List<WxDefaultReply> queryAll(String orderByClause) {
		WxCommandCriteria criteria = new WxCommandCriteria();
		criteria.createCriteria();
		criteria.setOrderByClause(orderByClause);
		return wxDefaultReplyMapper.selectByExample(null);
	}

	@Override
	public List<WxDefaultReply> queryByCriteria(WxDefaultReplyCriteria criteria) {
		return wxDefaultReplyMapper.selectByExample(criteria);
	}
	
	

	public WxDefaultReplyMapper getWxDefaultReplyMapper() {
		return wxDefaultReplyMapper;
	}

	public void setWxDefaultReplyMapper(WxDefaultReplyMapper wxDefaultReplyMapper) {
		this.wxDefaultReplyMapper = wxDefaultReplyMapper;
	}

}