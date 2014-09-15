package com.bruce.geekway.service.pay.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import com.bruce.geekway.dao.mapper.WxPayComplaintMapper;
import com.bruce.geekway.model.WxPayComplaint;
import com.bruce.geekway.model.WxPayComplaintCriteria;
import com.bruce.geekway.service.pay.IWxPayComplaintService;

public class WxPayComplaintServiceImpl implements IWxPayComplaintService {

	@Autowired
	private WxPayComplaintMapper wxPayComplaintMapper;

	@Override
	public int save(WxPayComplaint t) {
		return wxPayComplaintMapper.insertSelective(t);
	}

	@Override
	public int updateById(WxPayComplaint t) {
		return wxPayComplaintMapper.updateByPrimaryKeySelective(t);
	}

	@Override
	public int updateByCriteria(WxPayComplaint t, WxPayComplaintCriteria criteria) {
		return wxPayComplaintMapper.updateByExampleSelective(t, criteria);
	}

	@Override
	public int deleteById(Integer id) {
		return wxPayComplaintMapper.deleteByPrimaryKey(id);
	}

	@Override
	public int deleteByCriteria(WxPayComplaintCriteria criteria) {
		return wxPayComplaintMapper.deleteByExample(criteria);
	}

	@Override
	public WxPayComplaint loadById(Integer id) {
		return wxPayComplaintMapper.selectByPrimaryKey(id);
	}

	@Override
	public List<WxPayComplaint> queryAll() {
		return queryAll(null);
	}

	@Override
	public List<WxPayComplaint> queryAll(String orderByClause) {
		WxPayComplaintCriteria criteria = new WxPayComplaintCriteria();
		criteria.createCriteria();
		criteria.setOrderByClause(orderByClause);
		return queryByCriteria(criteria);
	}

	@Override
	public List<WxPayComplaint> queryByCriteria(WxPayComplaintCriteria criteria) {
		return wxPayComplaintMapper.selectByExample(criteria);
	}

	@Override
	public int markFinish(String openId, String feedbackId) {
		return markDealStatus(openId, feedbackId, (short)0);
	}
	
	@Override
	public int markWait4Confirm(String openId, String feedbackId) {
		return markDealStatus(openId, feedbackId, (short)2);
	}
	
	/**
	 * 
	 * @param openId
	 * @param feedbackId
	 * @param dealStatus 0为处理完毕，1为新投诉，2为沟通完毕&提交标记，等待用户确认
	 * @return
	 */
	private int markDealStatus(String openId, String feedbackId, short dealStatus) {
		WxPayComplaintCriteria criteria = new WxPayComplaintCriteria();
		criteria.createCriteria().andOpenIdEqualTo(openId).andFeedbackIdEqualTo(feedbackId);
		WxPayComplaint t = new WxPayComplaint();
		t.setDealStatus(dealStatus);
		return updateByCriteria(t, criteria);
	}

	public WxPayComplaintMapper getWxPayComplaintMapper() {
		return wxPayComplaintMapper;
	}

	public void setWxPayComplaintMapper(WxPayComplaintMapper wxPayComplaintMapper) {
		this.wxPayComplaintMapper = wxPayComplaintMapper;
	}


	
}