package com.bruce.geekway.service.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bruce.foundation.model.paging.PagingResult;
import com.bruce.geekway.constants.ConstConfig;
import com.bruce.geekway.dao.mapper.WxBroadcastMapper;
import com.bruce.geekway.model.WxBroadcast;
import com.bruce.geekway.model.WxBroadcastCriteria;
import com.bruce.geekway.model.WxMaterialArticle;
import com.bruce.geekway.model.WxMaterialMultimedia;
import com.bruce.geekway.model.wx.json.response.WxBroadcastResult;
import com.bruce.geekway.service.IWxBroadcastService;
import com.bruce.geekway.service.IWxMaterialArticleService;
import com.bruce.geekway.service.IWxMaterialMultimediaService;
import com.bruce.geekway.service.mp.WxMpBroadcastService;

/**
 * 
 * @author liqian
 *
 */
@Service
public class WxBroadcastServiceImpl implements IWxBroadcastService, InitializingBean {

	//private static final String WX_NEWS_UPLOAD_API = ConfigUtil.getString("weixinmp_news_upload_url");
	
	@Autowired
	private WxBroadcastMapper wxBroadcastMapper;
	@Autowired
	private IWxMaterialMultimediaService wxMaterialMultimediaService;
	@Autowired
	private IWxMaterialArticleService wxMaterialArticleService;
	@Autowired
	private WxMpBroadcastService wxMpBroadcastService;

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
		return queryAll(null);
	}

	@Override
	public List<WxBroadcast> queryAll(String orderByClause) {
		WxBroadcastCriteria criteria = new WxBroadcastCriteria();
		criteria.createCriteria();
		criteria.setOrderByClause(orderByClause);
		return queryByCriteria(criteria);
	}

	@Override
	public List<WxBroadcast> queryByCriteria(WxBroadcastCriteria criteria) {
		return wxBroadcastMapper.selectByExample(criteria);
	}
	

	@Override
	public int countByCriteria(WxBroadcastCriteria criteria) {
		return wxBroadcastMapper.countByExample(criteria);
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
	public WxBroadcastResult broadcastMaterialText(String content) {
		if(content!=null){
			WxBroadcastResult broadcastResult =  wxMpBroadcastService.broadcastText(content);
			
			if(broadcastResult!=null&&broadcastResult.getErrcode()==0){
				//构造群发记录
				WxBroadcast broadcastLog = new WxBroadcast();
				broadcastLog.setMessageType("");//文本
				broadcastLog.setStatus((short) 0);//提交发送任务后的状态为0
				broadcastLog.setContent(content);
				broadcastLog.setMsgId(broadcastResult.getMsg_id());
				broadcastLog.setCreateTime(new Date());
				//save到群发历史记录
				save(broadcastLog);
				return broadcastResult;
			}
		}
		return null;
	}


	@Override
	public WxBroadcastResult broadcastMaterialArticle(int materialId) {
		//构造图文&上传生成mediaId
		WxMaterialArticle materialArticle = wxMaterialArticleService.loadById(materialId);
		
		if(materialArticle!=null){
			List<WxMaterialArticle> articleList = new ArrayList<WxMaterialArticle>();
			String mediaId = uploadWxNews(articleList);//upload & getMediaId
			
			WxBroadcastResult broadcastResult =  wxMpBroadcastService.broadcastNews(mediaId);
			if(broadcastResult!=null&&broadcastResult.getErrcode()==0){
				//构造群发记录
				WxBroadcast broadcastLog = new WxBroadcast();
				broadcastLog.setMessageType("");//单图文
				broadcastLog.setStatus((short) 0);//提交发送任务后的状态为0
				broadcastLog.setMediaId(mediaId);
				broadcastLog.setMsgId(broadcastResult.getMsg_id());
				broadcastLog.setCreateTime(new Date());
				//save到群发历史记录
				save(broadcastLog);
				return broadcastResult;
			}
			
		}
		return null;
	}

	@Override
	public WxBroadcastResult broadcastMaterialNews(int materialId) {
		//构造图文&上传生成mediaId
		List<WxMaterialArticle> articleList = wxMaterialArticleService.queryMaterialArticlesByNewsId(materialId);
		if(articleList!=null&&articleList.size()>0){
			String mediaId = uploadWxNews(articleList);
			WxBroadcastResult broadcastResult =  wxMpBroadcastService.broadcastNews(mediaId);
			if(broadcastResult!=null&&broadcastResult.getErrcode()==0){
				//构造群发记录
				WxBroadcast broadcastLog = new WxBroadcast();
				broadcastLog.setMessageType("");//多图文
				broadcastLog.setStatus((short) 0);//提交发送任务后的状态为0
				broadcastLog.setMediaId(mediaId);
				broadcastLog.setMsgId(broadcastResult.getMsg_id());
				broadcastLog.setCreateTime(new Date());
				//save到群发历史记录
				save(broadcastLog);
				return broadcastResult;
			}
		}
		return null;
	}

	@Override
	public WxBroadcastResult broadcastMaterialImage(int materialId) {
		WxMaterialMultimedia materialImage = wxMaterialMultimediaService.loadImageById(materialId);
		if(materialImage!=null&&materialImage.getMediaId()!=null){
			WxBroadcastResult broadcastResult =  wxMpBroadcastService.broadcastImage(materialImage.getMediaId());
			if(broadcastResult!=null&&broadcastResult.getErrcode()==0){
				//构造群发记录
				WxBroadcast broadcastLog = new WxBroadcast();
				broadcastLog.setMessageType("");//图片
				broadcastLog.setStatus((short) 0);//提交发送任务后的状态为0
				broadcastLog.setMediaId(materialImage.getMediaId());
				broadcastLog.setMsgId(broadcastResult.getMsg_id());
				broadcastLog.setCreateTime(new Date());
				//save到群发历史记录
				save(broadcastLog);
				return broadcastResult;
			}
		}
		return null;
	}

	/**
	 * 上传图文数据生成mediaId
	 * @param articleList
	 * @return
	 */
	private String uploadWxNews(List<WxMaterialArticle> articleList) {
		String mediaId = null;
		if(articleList!=null&&articleList.size()>0){
			mediaId = "";
		}
		return mediaId;
	}


	@Override
	public List<WxBroadcast> fallloadByCriteria(int pageSize, WxBroadcastCriteria criteria) {
		return null;
	}

	@Override
	public PagingResult<WxBroadcast> pagingByCriteria(int pageNo, int pageSize, WxBroadcastCriteria criteria) {
		pageNo = pageNo<=0?1:pageNo;//确保pageNo合法
		pageSize = pageNo<=0?ConstConfig.PAGE_SIZE_DEFAULT:pageSize;//确保pageSize合法
		int offset = (pageNo-1)*pageSize;
		
		//构造查询条件
		if(criteria==null){
			criteria = new WxBroadcastCriteria();
		}
		
		criteria.setLimitOffset(offset);
		criteria.setLimitRows(pageSize);
		
		int count = wxBroadcastMapper.countByExample(criteria);
		List<WxBroadcast> dataList = wxBroadcastMapper.selectByExample(criteria);
		//返回分页数据
		return new PagingResult<WxBroadcast>(pageNo, pageSize, count, dataList);
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