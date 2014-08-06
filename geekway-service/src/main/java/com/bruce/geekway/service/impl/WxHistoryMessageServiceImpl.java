package com.bruce.geekway.service.impl;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bruce.foundation.model.paging.PagingResult;
import com.bruce.geekway.dao.mapper.WxHistoryMessageMapper;
import com.bruce.geekway.model.WxHistoryMessage;
import com.bruce.geekway.model.WxHistoryMessageCriteria;
import com.bruce.geekway.model.wx.request.BaseRequest;
import com.bruce.geekway.model.wx.request.ImageRequest;
import com.bruce.geekway.model.wx.request.LocationRequest;
import com.bruce.geekway.model.wx.request.TextRequest;
import com.bruce.geekway.model.wx.request.VideoRequest;
import com.bruce.geekway.model.wx.request.VoiceRequest;
import com.bruce.geekway.model.wx.response.BaseResponse;
import com.bruce.geekway.model.wx.response.NewsResponse;
import com.bruce.geekway.model.wx.response.TextResponse;
import com.bruce.geekway.service.IWxHistoryMessageService;

/**
 * 微信历史消息（需要记录接入消息与回复消息）
 * @author liqian
 *
 */
@Service
public class WxHistoryMessageServiceImpl implements IWxHistoryMessageService, InitializingBean {

	@Autowired
	private WxHistoryMessageMapper wxHistoryMessageMapper;

	@Override
	public int save(WxHistoryMessage t) {
		return wxHistoryMessageMapper.insertSelective(t);
	}

	@Override
	public int updateById(WxHistoryMessage t) {
		return wxHistoryMessageMapper.updateByPrimaryKeySelective(t);
	}

	@Override
	public int updateByCriteria(WxHistoryMessage t, WxHistoryMessageCriteria criteria) {
		return wxHistoryMessageMapper.updateByExampleSelective(t, criteria);
	}

	@Override
	public int deleteById(Integer id) {
		return wxHistoryMessageMapper.deleteByPrimaryKey(id);
	}

	@Override
	public int deleteByCriteria(WxHistoryMessageCriteria criteria) {
		return wxHistoryMessageMapper.deleteByExample(criteria);
	}

	@Override
	public WxHistoryMessage loadById(Integer id) {
		return wxHistoryMessageMapper.selectByPrimaryKey(id);
	}

	@Override
	public List<WxHistoryMessage> queryAll() {
		return queryAll(null);
	}

	@Override
	public List<WxHistoryMessage> queryAll(String orderByClause) {
		WxHistoryMessageCriteria criteria = new WxHistoryMessageCriteria();
		criteria.createCriteria();
		criteria.setOrderByClause(orderByClause);
		return queryByCriteria(criteria);
	}

	@Override
	public List<WxHistoryMessage> queryByCriteria(WxHistoryMessageCriteria criteria) {
		return wxHistoryMessageMapper.selectByExample(criteria);
	}
	
	@Override
	public int logRequestMessage(BaseRequest requestMessage, String fullMessage) {
		WxHistoryMessage message = new WxHistoryMessage();
		message.setFullMessage(fullMessage);
		message.setInbox((short) 0);
		message.setMsgId(requestMessage.getMsgId());
		message.setOpenId(requestMessage.getToUserName());
		message.setMsgType(requestMessage.getMsgType());
		message.setCreateTime(new Date());
		//TODO log sendTime
		
		if(requestMessage instanceof TextRequest){//文本消息
			message.setContent(((TextRequest)requestMessage).getContent());
		}else if(requestMessage instanceof ImageRequest){//文本消息
			message.setMediaId(((ImageRequest)requestMessage).getMediaId());
			message.setPicUrl(((ImageRequest)requestMessage).getPicUrl());
		}else if(requestMessage instanceof VoiceRequest){//语音消息
			message.setMediaId(((VoiceRequest)requestMessage).getMediaId());
			message.setFormat(((VoiceRequest)requestMessage).getFormat());
		}else if(requestMessage instanceof VideoRequest){//视频消息
			message.setMediaId(((VideoRequest)requestMessage).getMediaId());
			message.setThumbMediaId(((VideoRequest)requestMessage).getThumbMediaId());
		}else if(requestMessage instanceof LocationRequest){//lbs消息
			//do nothing
		}else{
			
		}
		return save(message);
	}

	@Override
	public int logResponseMessage(BaseResponse responseMessage, String fullMessage) {
		WxHistoryMessage message = new WxHistoryMessage();
		message.setFullMessage(fullMessage);
		message.setOpenId(responseMessage.getToUserName());
		message.setInbox((short) 1);
		Date currentTime = new Date();
		message.setSendTime(currentTime);
		message.setCreateTime(currentTime);
		
		if(responseMessage instanceof TextResponse){//文本回复
			message.setMsgType("text");
			message.setContent(((TextResponse)responseMessage).getContent());
		}else if(responseMessage instanceof NewsResponse){//文本图文回复
			message.setMsgType("news");
		}
		return save(message);
	}
	
	
	@Override
	public List<WxHistoryMessage> fallloadByCriteria(int pageSize, WxHistoryMessageCriteria criteria) {
		return null;
	}

	@Override
	public PagingResult<WxHistoryMessage> pagingByCriteria(int pageNo, int pageSize, WxHistoryMessageCriteria criteria) {
		return null;
	}
	
	
	@Override
	public void afterPropertiesSet() throws Exception {

	}

	public WxHistoryMessageMapper getWxHistoryMessageMapper() {
		return wxHistoryMessageMapper;
	}

	public void setWxHistoryMessageMapper(
			WxHistoryMessageMapper wxHistoryMessageMapper) {
		this.wxHistoryMessageMapper = wxHistoryMessageMapper;
	}

	


}