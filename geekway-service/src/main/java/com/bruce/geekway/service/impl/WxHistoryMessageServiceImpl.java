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
import com.bruce.geekway.model.wx.message.CustomMessage;
import com.bruce.geekway.model.wx.message.NewsMessage;
import com.bruce.geekway.model.wx.message.TextMessage;
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
	
	/**
	 * 记录用户发送来的微信消息
	 * @return
	 */
	@Override
	public int logRequestMessage(BaseRequest requestMessage, String fullMessage) {
		WxHistoryMessage message = new WxHistoryMessage();
		message.setFullMessage(fullMessage);
		message.setInbox((short) 0);
		message.setMsgId(requestMessage.getMsgId());
		message.setOpenId(requestMessage.getFromUserName());
		message.setMsgType(requestMessage.getMsgType());
		message.setCreateTime(new Date());
		
		long sentTimeMillis = System.currentTimeMillis();
		try{
			long seconds = Long.parseLong(requestMessage.getCreateTime());
			sentTimeMillis = seconds*1000;
		}catch(Exception e){
		}
		message.setSendTime(new Date(sentTimeMillis));
		
		if(requestMessage instanceof TextRequest){//文本消息
			message.setContent(((TextRequest)requestMessage).getContent());
		}else if(requestMessage instanceof ImageRequest){//图片消息
			message.setContent("发来了一条图片消息");
			message.setMediaId(((ImageRequest)requestMessage).getMediaId());
			message.setPicUrl(((ImageRequest)requestMessage).getPicUrl());
		}else if(requestMessage instanceof VoiceRequest){//语音消息
			message.setContent("发来了一条语音消息");
			message.setMediaId(((VoiceRequest)requestMessage).getMediaId());
			message.setFormat(((VoiceRequest)requestMessage).getFormat());
		}else if(requestMessage instanceof VideoRequest){//视频消息
			message.setContent("发来了一条视频消息");
			message.setMediaId(((VideoRequest)requestMessage).getMediaId());
			message.setThumbMediaId(((VideoRequest)requestMessage).getThumbMediaId());
		}else if(requestMessage instanceof LocationRequest){//lbs消息
			message.setContent("发来了一条位置消息");
		}else{
			message.setContent("发来了一条消息");
		}
		return save(message);
	}

	/**
	 * 记录自动回复给用户的微信消息(replyType=0)
	 * @return
	 */
	@Override
	public int logResponseMessage(BaseResponse wxResponse, String fullMessage) {
		if(wxResponse!=null){
			WxHistoryMessage message = new WxHistoryMessage();
			message.setReplyType((short) 0);
			message.setFullMessage(fullMessage);
			message.setOpenId(wxResponse.getToUserName());
			message.setInbox((short) 1);
			Date currentTime = new Date();
			message.setSendTime(currentTime);
			message.setCreateTime(currentTime);
			
			if(wxResponse instanceof TextResponse){//文本回复
				message.setMsgType("text");
				message.setContent(((TextResponse)wxResponse).getContent());
			}else if(wxResponse instanceof NewsResponse){//文本图文回复
				message.setMsgType("news");
				message.setContent("回复了一条图文消息");
			}
			return save(message);
		}
		return 0;
	}
	
	/**
	 * 记录客服回复给用户的微信消息(replyType=1)
	 * @return
	 */
	public int logCustomReplyMessage(CustomMessage customMessage, String fullMessage){
		if(customMessage!=null){
			WxHistoryMessage message = new WxHistoryMessage();
			message.setReplyType((short) 1);
			message.setFullMessage(fullMessage);
			message.setOpenId(customMessage.getTouser());
			message.setInbox((short) 1);
			Date currentTime = new Date();
			message.setSendTime(currentTime);
			message.setCreateTime(currentTime);
			if(customMessage instanceof TextMessage){//文本回复
				message.setMsgType("text");
				message.setContent(((TextMessage)customMessage).getText().getContent());
			}else if(customMessage instanceof NewsMessage){//客服回复文本
				message.setMsgType("news");
				message.setContent("客服回复了一条图文消息");
			}
			
			//以下消息类型暂时不支持
			/*
			else if(customMessage instanceof ImageMessage){//客服回复图文
				message.setMsgType("image");
				message.setContent("客服回复了一条图片消息");
				message.setMediaId(((ImageMessage)customMessage).getImage().getMedia_id());
			}else if(customMessage instanceof VoiceMessage){//客服回复语音
				message.setMsgType("voice");
				message.setContent("客服回复了一条语音消息");
				message.setMediaId(((VoiceMessage)customMessage).getMedia_id());
			}else if(customMessage instanceof VideoMessage){//客服回复视频
				message.setMsgType("video");
				message.setContent("客服回复了一条视频消息");
				message.setMediaId(((VideoMessage)customMessage).getMedia_id());
				message.setMediaId(((VideoMessage)customMessage).getThumb_media_id());
			}else if(customMessage instanceof MusicMessage){//客服回复视频
				message.setMsgType("video");
				message.setContent("客服回复了一条音乐消息");
				message.setMediaId(((MusicMessage)customMessage).getMedia_id());
				message.setMediaId(((MusicMessage)customMessage).getThumb_media_id());
			}else{
				
			}
			*/
			return save(message);
		}
		return 0;
	}
	
	@Override
	public WxHistoryMessage queryUserLastestMessage(String userOpenId) {
		WxHistoryMessageCriteria criteria = new WxHistoryMessageCriteria();
		criteria.createCriteria().andOpenIdEqualTo(userOpenId).andInboxEqualTo((short) 0);
		criteria.setOrderByClause(" id desc");
		criteria.setLimitRows(1);
		List<WxHistoryMessage> historyMessageList = queryByCriteria(criteria);
		if(historyMessageList!=null&&historyMessageList.size()>0){
			return historyMessageList.get(0);
		}
		return null;
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