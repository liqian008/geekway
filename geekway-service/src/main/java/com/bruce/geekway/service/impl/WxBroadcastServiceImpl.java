package com.bruce.geekway.service.impl;

import java.util.List;

import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bruce.foundation.model.paging.PagingResult;
import com.bruce.geekway.dao.mapper.WxBroadcastMapper;
import com.bruce.geekway.model.WxBroadcast;
import com.bruce.geekway.model.WxBroadcastCriteria;
import com.bruce.geekway.service.IWxBroadcastService;

/**
 * 
 * @author liqian
 *
 */
@Service
public class WxBroadcastServiceImpl implements IWxBroadcastService, InitializingBean {

	@Autowired
	private WxBroadcastMapper wxBroadcastMapper;

	@Override
	public int save(WxBroadcast t) {
		return wxBroadcastMapper.insertSelective(t);
	}

	@Override
	public int updateById(WxBroadcast t) {
		return wxBroadcastMapper.updateByPrimaryKeySelective(t);
	}

	@Override
	public int updateByCriteria(WxBroadcast t, WxBroadcastCriteria criteria) {
		return wxBroadcastMapper.updateByExampleSelective(t, criteria);
	}

	@Override
	public int deleteById(Integer id) {
		return wxBroadcastMapper.deleteByPrimaryKey(id);
	}

	@Override
	public int deleteByCriteria(WxBroadcastCriteria criteria) {
		return wxBroadcastMapper.deleteByExample(criteria);
	}

	@Override
	public WxBroadcast loadById(Integer id) {
		return wxBroadcastMapper.selectByPrimaryKey(id);
	}

	@Override
	public List<WxBroadcast> queryAll() {
		return wxBroadcastMapper.selectByExample(null);
	}

	@Override
	public List<WxBroadcast> queryAll(String orderByClause) {
		WxBroadcastCriteria criteria = new WxBroadcastCriteria();
		criteria.createCriteria();
		criteria.setOrderByClause(orderByClause);
		return wxBroadcastMapper.selectByExample(null);
	}

	@Override
	public List<WxBroadcast> queryByCriteria(WxBroadcastCriteria criteria) {
		return wxBroadcastMapper.selectByExample(criteria);
	}
	
	/**
	 * 根据回调的群发结果，更新数据
	 */
	@Override
	public int broadcastNofify(String msgID, int totalCount, int filterCount, int sentCount, int errorCount) {
		WxBroadcast boardcast = new WxBroadcast();
		boardcast.setTotalCount(totalCount);
		boardcast.setFilterCount(filterCount);
		boardcast.setSentCount(sentCount);
		boardcast.setErrorCount(errorCount);
		boardcast.setStatus((short)1);
		
		WxBroadcastCriteria criteria = new WxBroadcastCriteria();
		criteria.createCriteria().andMsgIdEqualTo(msgID);
		return wxBroadcastMapper.updateByExampleSelective(boardcast, criteria);
	}

	

	@Override
	public List<WxBroadcast> fallloadByCriteria(int pageSize, WxBroadcastCriteria criteria) {
		return null;
	}

	@Override
	public PagingResult<WxBroadcast> pagingByCriteria(int pageNo, int pageSize, WxBroadcastCriteria criteria) {
		return null;
	}
	

	@Override
	public void afterPropertiesSet() throws Exception {

	}

	public WxBroadcastMapper getWxBroadcastMapper() {
		return wxBroadcastMapper;
	}

	public void setWxBroadcastMapper(WxBroadcastMapper wxBroadcastMapper) {
		this.wxBroadcastMapper = wxBroadcastMapper;
	}


}