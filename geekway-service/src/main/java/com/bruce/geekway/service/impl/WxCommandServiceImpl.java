package com.bruce.geekway.service.impl;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bruce.foundation.model.paging.PagingResult;
import com.bruce.geekway.dao.mapper.WxCommandMapper;
import com.bruce.geekway.model.WxCommand;
import com.bruce.geekway.model.WxCommandCriteria;
import com.bruce.geekway.service.IWxCommandService;
//import com.bruce.geekway.dao.IWxMaterialNewsDao;
//import com.bruce.geekway.model.WxMaterialNews;

@Service
public class WxCommandServiceImpl implements IWxCommandService {

	@Autowired
	private WxCommandMapper wxCommandMapper;

	@Override
	public int save(WxCommand t) {
		return wxCommandMapper.insertSelective(t);
	}

	@Override
	public int updateById(WxCommand t) {
		return wxCommandMapper.updateByPrimaryKeySelective(t);
	}

	@Override
	public int updateByCriteria(WxCommand t, WxCommandCriteria criteria) {
		return wxCommandMapper.updateByExampleSelective(t, criteria);
	}

	@Override
	public int deleteById(Integer id) {
		return wxCommandMapper.deleteByPrimaryKey(id);
	}

	@Override
	public int deleteByCriteria(WxCommandCriteria criteria) {
		return wxCommandMapper.deleteByExample(criteria);
	}

	@Override
	public WxCommand loadById(Integer id) {
		return wxCommandMapper.selectByPrimaryKey(id);
	}

	@Override
	public List<WxCommand> queryAll() {
		return queryAll(null);
	}

	@Override
	public List<WxCommand> queryAll(String orderByClause) {
		WxCommandCriteria criteria = new WxCommandCriteria();
		criteria.createCriteria();
		criteria.setOrderByClause(orderByClause);
		return wxCommandMapper.selectByExample(criteria);
	}

	@Override
	public List<WxCommand> queryByCriteria(WxCommandCriteria criteria) {
		return wxCommandMapper.selectByExample(criteria);
	}

	public WxCommand loadByCommandType(short commandType, String command) {
		WxCommandCriteria criteria = new WxCommandCriteria();
		criteria.createCriteria().andCommandTypeEqualTo(commandType).andCommandEqualTo(command);
		List<WxCommand> commandList = wxCommandMapper.selectByExample(criteria);
		if (commandList != null && commandList.size() > 0) {
			return commandList.get(0);
		}
		return null;
	}

//	/**
//	 * 加载或新建，确保返回有效的command数据
//	 */
//	@Override
//	public synchronized WxCommand loadOrSave(short commandType, String command) {
//		WxCommand commandBean = loadByCommandType(commandType, command);
//		if (commandBean == null || commandBean.getId() == null) {
//			commandBean = new WxCommand();
//			commandBean.setCommand(command);
//			commandBean.setCommandType(commandType);
//			commandBean.setCreateTime(new Date());
//			commandBean.setPublishStatus((short) 0);
//			commandBean.setStatus((short) 0);
//			int result = save(commandBean);
//			if (result <= 0) {
//				return null;
//			}
//		}
//		return commandBean;
//	}
	
	
	/**
	 * 查询普通0,1（非订阅指令2,3）的指令列表
	 */
	public List<WxCommand> queryGeneralCommandList(){
		WxCommandCriteria criteria = new WxCommandCriteria();
		criteria.createCriteria().andCommandTypeLessThan((short) 2);//0和1为普通指令，2和3为关注指令
		return queryByCriteria(criteria);
	}
	
	@Override
	public WxCommand loadByCommand(short commandType, String command) {
		WxCommandCriteria criteria = new WxCommandCriteria();
		criteria.createCriteria().andCommandTypeEqualTo(commandType).andCommandEqualTo(command);
		List<WxCommand> commandList = wxCommandMapper.selectByExample(criteria);
		if (commandList != null && commandList.size() > 0) {
			return commandList.get(0);
		}
		return null;
	}
	
	public WxCommand loadNewSubscribedCommand(){
		WxCommandCriteria criteria = new WxCommandCriteria();
		criteria.createCriteria().andCommandTypeEqualTo((short) 2);
		List<WxCommand> commandList = wxCommandMapper.selectByExample(criteria);
		if (commandList != null && commandList.size() > 0) {
			return commandList.get(0);
		}
		return null;
	}
	
	public WxCommand loadReSubscribedCommand(){
		WxCommandCriteria criteria = new WxCommandCriteria();
		criteria.createCriteria().andCommandTypeEqualTo((short) 3);
		List<WxCommand> commandList = wxCommandMapper.selectByExample(criteria);
		if (commandList != null && commandList.size() > 0) {
			return commandList.get(0);
		}
		return null;
	}


	@Override
	public List<WxCommand> fallloadByCriteria(int pageSize, WxCommandCriteria criteria) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public PagingResult<WxCommand> pagingByCriteria(int pageNo, int pageSize, WxCommandCriteria criteria) {
		// TODO Auto-generated method stub
		return null;
	}

	public WxCommandMapper getWxCommandMapper() {
		return wxCommandMapper;
	}

	public void setWxCommandMapper(WxCommandMapper wxCommandMapper) {
		this.wxCommandMapper = wxCommandMapper;
	}



}