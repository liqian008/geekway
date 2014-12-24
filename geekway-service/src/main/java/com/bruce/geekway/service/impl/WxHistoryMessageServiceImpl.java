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
import com.bruce.geekway.model.wx.WxEventTypeEnum;
import com.bruce.geekway.model.wx.message.CustomMessage;
import com.bruce.geekway.model.wx.message.ImageMessage;
import com.bruce.geekway.model.wx.message.NewsMessage;
import com.bruce.geekway.model.wx.message.TextMessage;
import com.bruce.geekway.model.wx.message.VoiceMessage;
import com.bruce.geekway.model.wx.request.BaseRequest;
import com.bruce.geekway.model.wx.request.ClickEventRequest;
import com.bruce.geekway.model.wx.request.EventRequest;
import com.bruce.geekway.model.wx.request.ImageRequest;
import com.bruce.geekway.model.wx.request.LocationRequest;
import com.bruce.geekway.model.wx.request.TextRequest;
import com.bruce.geekway.model.wx.request.VideoRequest;
import com.bruce.geekway.model.wx.request.VoiceRequest;
import com.bruce.geekway.model.wx.response.BaseResponse;
import com.bruce.geekway.model.wx.response.ImageResponse;
import com.bruce.geekway.model.wx.response.NewsResponse;
import com.bruce.geekway.model.wx.response.TextResponse;
import com.bruce.geekway.service.IWxHistoryMessageService;
import com.bruce.geekway.utils.JsonUtil;

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
		message.setSentTime(new Date(sentTimeMillis));
		
		if(requestMessage instanceof TextRequest){//文本消息
			message.setContent(((TextRequest)requestMessage).getContent());
			return save(message);
		}else if(requestMessage instanceof ImageRequest){//图片消息
			message.setContent("发来了一条图片消息");
			message.setMediaId(((ImageRequest)requestMessage).getMediaId());
			message.setPicUrl(((ImageRequest)requestMessage).getPicUrl());
			return save(message);
		}else if(requestMessage instanceof VoiceRequest){//语音消息
			message.setContent("发来了一条语音消息");
			message.setMediaId(((VoiceRequest)requestMessage).getMediaId());
			message.setFormat(((VoiceRequest)requestMessage).getFormat());
			return save(message);
		}else if(requestMessage instanceof VideoRequest){//视频消息
			message.setContent("发来了一条视频消息");
			message.setMediaId(((VideoRequest)requestMessage).getMediaId());
			message.setThumbMediaId(((VideoRequest)requestMessage).getThumbMediaId());
			return save(message);
		}else if(requestMessage instanceof LocationRequest){//lbs消息
			message.setContent("发来了一条位置消息");
			return save(message);
		}else if(requestMessage instanceof EventRequest){//事件类的消息
			
			if(WxEventTypeEnum.SUBSCRIBE.equals(((EventRequest) requestMessage).getEvent())){//订阅消息
				message.setContent("首次关注公众账户");
				return save(message);
			}else if(WxEventTypeEnum.RESUBSCRIBE.equals(((EventRequest) requestMessage).getEvent())){//订阅消息
				message.setContent("重复关注公众账户");
				return save(message);
			}else if(WxEventTypeEnum.UNSUBSCRIBE.equals(((EventRequest) requestMessage).getEvent())){//订阅消息
				message.setContent("取消关注公众账户");
				return save(message);
			}else if(WxEventTypeEnum.CLICK.equals(((EventRequest) requestMessage).getEvent())){//点击消息
				message.setContent("点击菜单【"+((EventRequest)requestMessage).getEventKey()+"】");
				return save(message);
			}
		}else{
			//do nothing
		}
		return 0;
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
			message.setSentTime(currentTime);
			message.setCreateTime(currentTime);
			
			if(wxResponse instanceof TextResponse){//自动回复文本
				message.setMsgType("text");
				message.setContent("自动回复文本: "+((TextResponse)wxResponse).getContent());
			}else if(wxResponse instanceof NewsResponse){//自动回复图文
				
				int articleCount = ((NewsResponse)wxResponse).getArticleCount();
				message.setMsgType("news");
				if(articleCount>1){
					message.setContent("自动回复了一条多图文消息");	
				}else{
					message.setContent("自动回复了一条单图文消息");
				}
				message.setPicUrl(((NewsResponse)wxResponse).getArticles().get(0).getPicUrl());
				message.setDigest(((NewsResponse)wxResponse).getArticles().get(0).getTitle());
			}else if(wxResponse instanceof ImageResponse){//自动回复图片
				message.setMsgType("image");
				message.setContent("自动回复了一条图片消息");
				message.setMediaId(((ImageResponse)wxResponse).getMediaId());
			}
			return save(message);
		}
		return 0;
	}
	
	/**
	 * 记录客服回复给用户的微信消息(replyType=1)
	 * @return
	 */
	public int logCustomReplyMessage(CustomMessage customMessage){
		if(customMessage!=null){
			WxHistoryMessage message = new WxHistoryMessage();
			message.setReplyType((short) 1);
			message.setFullMessage(JsonUtil.gson.toJson(customMessage));
			message.setOpenId(customMessage.getTouser());
			message.setInbox((short) 1);
			Date currentTime = new Date();
			message.setSentTime(currentTime);
			message.setCreateTime(currentTime);
			if(customMessage instanceof TextMessage){//文本回复
				message.setMsgType("text");
				message.setContent("客服回复文本消息: "+((TextMessage)customMessage).getText().getContent());
			}else if(customMessage instanceof NewsMessage){//客服回复图文
				message.setMsgType("news");
				if(((NewsMessage) customMessage).getNews().getArticles().size()>1){
					message.setContent("客服回复了一条多图文消息");
				}else{
					message.setContent("客服回复了一条单图文消息");
				}
				message.setPicUrl(((NewsMessage)customMessage).getNews().getArticles().get(0).getPicurl());
				message.setDigest(((NewsMessage)customMessage).getNews().getArticles().get(0).getTitle());
				
			}else if(customMessage instanceof ImageMessage){//客服回复图片
				message.setMsgType("image");
				message.setContent("客服回复了一条图片消息");
				message.setMediaId(((ImageMessage)customMessage).getImage().getMedia_id());
			}
			
			//以下消息类型暂时不支持
			/*
			 * else if(customMessage instanceof VoiceMessage){//客服回复语音
				message.setMsgType("voice");
				message.setContent("客服回复了一条语音消息");
				message.setMediaId(((VoiceMessage)customMessage).getVoice().getMedia_id());
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

	@Override
	public int countByCriteria(WxHistoryMessageCriteria criteria) {
		// TODO Auto-generated method stub
		return 0;
	}


}