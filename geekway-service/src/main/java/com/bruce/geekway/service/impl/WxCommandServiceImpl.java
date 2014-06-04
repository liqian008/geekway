package com.bruce.geekway.service.impl;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bruce.geekway.dao.IWxCommandDao;
import com.bruce.geekway.dao.IWxMaterialNewsDao;
import com.bruce.geekway.model.WxCommand;
import com.bruce.geekway.model.WxMaterialNews;
import com.bruce.geekway.service.IWxCommandService;

@Service
public class WxCommandServiceImpl implements IWxCommandService{
	
	@Autowired
	private IWxCommandDao wxCommandDao;
	@Autowired
	private IWxMaterialNewsDao wxMaterialNewsDao;
	
	
	@Override
	public int save(WxCommand t) {
		return wxCommandDao.save(t);
	}

	@Override
	public int updateById(WxCommand t) {
		return wxCommandDao.updateById(t);
	}

	@Override
	public int deleteById(Integer id) {
		return wxCommandDao.deleteById(id);
	}

	@Override
	public WxCommand loadById(Integer id) {
		return wxCommandDao.loadById(id);
	}

	@Override
	public List<WxCommand> queryAll() {
		return wxCommandDao.queryAll();
	}
	
//	@Deprecated
//	public WxCommand loadByCode(String textCode){
//		return wxCommandDao.loadByCode(textCode);
//	}
	
	public WxCommand loadByCommandType(short commandType, String command){
		return wxCommandDao.loadByCommandType(commandType, command);
	}
	
	/**
	 * 加载或新建，确保返回有效的command数据
	 */
	@Override
	public WxCommand loadOrSave(short commandType, String command){
		WxCommand commandBean = loadByCommandType(commandType, command);
		if(commandBean==null||commandBean.getId()==null){
			commandBean = new WxCommand();
			commandBean.setCommand(command);
			commandBean.setCommandType(commandType);
			commandBean.setCreateTime(new Date());
			commandBean.setPublishStatus((short) 0);
			commandBean.setStatus((short) 0);
			int result = save(commandBean);
			if(result <=0){
				return null;
			}
		}
		return commandBean;
	}
	
	

	/**
	 * 变更对应的单图文Id
	 * @param commandId
	 * @param articleId
	 * @return
	 */
	public int updateMaterialArticle(int commandId, int articleId){
		return wxCommandDao.updateMaterialArticle(commandId, articleId, (short) 2);
	}
	
	/**
	 * 变更对应的多图文Id
	 * @param commandId
	 * @param newsId
	 * @return
	 */
	public int updateMaterialNews(int commandId, int newsId){
		WxMaterialNews materialNews = wxMaterialNewsDao.loadById(newsId);
		if(materialNews!=null&&materialNews.getId()!=null){
			short rowLimit = 4;//分页的默认值
			if(materialNews.getRowLimit()!=null&&materialNews.getRowLimit()>0){//使用多图中的rowLimit配置
				rowLimit = materialNews.getRowLimit();
			}
			int result = wxCommandDao.updateMaterialNews(commandId, newsId, rowLimit, (short) 3);
			
			return result; 
		}
		return 0;
	}
	
	
	/**
	 * 查询materialId对应的关键词列表
	 */
	public List<WxCommand> queryCommandsByMaterialId(int materialId){
		return wxCommandDao.queryCommandsByMaterialId(materialId);
	}
	

	public IWxCommandDao getWxCommandDao() {
		return wxCommandDao;
	}

	public void setWxCommandDao(IWxCommandDao wxCommandDao) {
		this.wxCommandDao = wxCommandDao;
	}

}