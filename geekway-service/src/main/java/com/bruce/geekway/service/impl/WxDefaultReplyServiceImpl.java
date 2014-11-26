package com.bruce.geekway.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bruce.foundation.model.paging.PagingResult;
import com.bruce.geekway.constants.ConstConfig;
import com.bruce.geekway.dao.mapper.WxDefaultReplyMapper;
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
		return queryAll(null);
	}

	@Override
	public List<WxDefaultReply> queryAll(String orderByClause) {
		WxDefaultReplyCriteria criteria = new WxDefaultReplyCriteria();
		criteria.createCriteria();
		criteria.setOrderByClause(orderByClause);
		return queryByCriteria(criteria);
	}

	@Override
	public List<WxDefaultReply> queryByCriteria(WxDefaultReplyCriteria criteria) {
		return wxDefaultReplyMapper.selectByExample(criteria);
	}
	
	@Override
	public int countByCriteria(WxDefaultReplyCriteria criteria) {
		return wxDefaultReplyMapper.countByExample(criteria);
	}
	
	@Override
	public List<WxDefaultReply> fallloadByCriteria(int pageSize, WxDefaultReplyCriteria criteria) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public PagingResult<WxDefaultReply> pagingByCriteria(int pageNo, int pageSize, WxDefaultReplyCriteria criteria) {
		pageNo = pageNo<=0?1:pageNo;//确保pageNo合法
		pageSize = pageNo<=0?ConstConfig.PAGE_SIZE_DEFAULT:pageSize;//确保pageSize合法
		int offset = (pageNo-1)*pageSize;
		
		//构造查询条件
		if(criteria==null){
			criteria = new WxDefaultReplyCriteria();
		}
		
		criteria.setLimitOffset(offset);
		criteria.setLimitRows(pageSize);
		
		int count = wxDefaultReplyMapper.countByExample(criteria);
		List<WxDefaultReply> dataList = wxDefaultReplyMapper.selectByExample(criteria);
		//返回分页数据
		return new PagingResult<WxDefaultReply>(pageNo, pageSize, count, dataList);
	}
	
	

	public WxDefaultReplyMapper getWxDefaultReplyMapper() {
		return wxDefaultReplyMapper;
	}

	public void setWxDefaultReplyMapper(WxDefaultReplyMapper wxDefaultReplyMapper) {
		this.wxDefaultReplyMapper = wxDefaultReplyMapper;
	}

	
	

}