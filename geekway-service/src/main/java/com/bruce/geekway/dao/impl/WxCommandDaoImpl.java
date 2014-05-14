package com.bruce.geekway.dao.impl;

import java.util.List;

import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.bruce.geekway.dao.IWxCommandDao;
import com.bruce.geekway.dao.mapper.WxCommandMapper;
import com.bruce.geekway.model.WxCommand;
import com.bruce.geekway.model.WxCommandCriteria;


@Repository 
public class WxCommandDaoImpl implements IWxCommandDao, InitializingBean {

     @Autowired
    private WxCommandMapper wxCommandMapper;

    @Override
    public int save(WxCommand t) {
        return wxCommandMapper.insert(t);
    }

    @Override
    public int updateById(WxCommand t) {
        return wxCommandMapper.updateByPrimaryKeySelective(t);
    }

    @Override
    public int deleteById(Integer id) {
        return wxCommandMapper.deleteByPrimaryKey(id);
    }

    @Override
    public WxCommand loadById(Integer id) {
        return wxCommandMapper.selectByPrimaryKey(id);
    }

    @Override
    public List<WxCommand> queryAll() {
        return wxCommandMapper.selectByExample(null);
    }

    @Override
    public List<WxCommand> fallLoadList(Integer tailId, int limit) {
        return null;
    }
    
    @Deprecated
    @Override
	public WxCommand loadByCode(String command) {
    	WxCommandCriteria criteria = new WxCommandCriteria();
    	criteria.createCriteria().andCommandEqualTo(command);
    	List<WxCommand> commandList =  wxCommandMapper.selectByExample(criteria);
    	if(commandList!=null&&commandList.size()>0){
    		return commandList.get(0);
    	}
    	return null;
	}
    
    @Override
	public WxCommand loadByCommandType(short commandType, String command) { 
    	WxCommandCriteria criteria = new WxCommandCriteria();
    	criteria.createCriteria().andCommandTypeEqualTo(commandType).andCommandEqualTo(command);
    	List<WxCommand> commandList =  wxCommandMapper.selectByExample(criteria);
    	if(commandList!=null&&commandList.size()>0){
    		return commandList.get(0);
    	}
    	return null;
	}
    
    
    @Override
	public int updateMaterialArticle(int commandId, int articleId, short materialType) {
    	WxCommandCriteria criteria = new WxCommandCriteria();
    	criteria.createCriteria().andIdEqualTo(commandId).andMaterialTypeEqualTo(materialType);
    	WxCommand command = new WxCommand();
    	command.setMaterialId(articleId);
    	return wxCommandMapper.updateByExampleSelective(command, criteria);
	}

	@Override
	public int updateMaterialNews(int commandId, int newsId, short rowLimit, short materialType) {
		WxCommandCriteria criteria = new WxCommandCriteria();
    	criteria.createCriteria().andIdEqualTo(commandId).andMaterialTypeEqualTo(materialType);
    	WxCommand command = new WxCommand();
    	command.setMaterialId(newsId);
//    	command.setRowLimit(rowLimit);
    	return wxCommandMapper.updateByExampleSelective(command, criteria);
	}

    @Override
    public void afterPropertiesSet() throws Exception {
    }

	public WxCommandMapper getWxCommandMapper() {
		return wxCommandMapper;
	}

	public void setWxCommandMapper(WxCommandMapper wxCommandMapper) {
		this.wxCommandMapper = wxCommandMapper;
	}


}
