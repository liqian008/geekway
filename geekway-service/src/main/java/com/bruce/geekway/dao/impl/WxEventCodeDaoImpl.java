package com.bruce.geekway.dao.impl;

import java.util.List;

import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.bruce.geekway.dao.IWxEventCodeDao;
import com.bruce.geekway.dao.mapper.WxEventCodeMapper;
import com.bruce.geekway.model.WxEventCode;
import com.bruce.geekway.model.WxEventCodeCriteria;


@Repository 
public class WxEventCodeDaoImpl implements IWxEventCodeDao, InitializingBean {

     @Autowired
    private WxEventCodeMapper wxEventCodeMapper;

    @Override
    public int save(WxEventCode t) {
        return wxEventCodeMapper.insert(t);
    }

    @Override
    public int updateById(WxEventCode t) {
        return wxEventCodeMapper.updateByPrimaryKeySelective(t);
    }

    @Override
    public int deleteById(Integer id) {
        return wxEventCodeMapper.deleteByPrimaryKey(id);
    }

    @Override
    public WxEventCode loadById(Integer id) {
        return wxEventCodeMapper.selectByPrimaryKey(id);
    }

    @Override
    public List<WxEventCode> queryAll() {
        return wxEventCodeMapper.selectByExample(null);
    }

    @Override
    public List<WxEventCode> fallLoadList(Integer tailId, int limit) {
        return null;
    }
    
    @Deprecated
    @Override
	public WxEventCode loadByCode(String eventCode) {
    	WxEventCodeCriteria criteria = new WxEventCodeCriteria();
    	criteria.createCriteria().andEventCodeEqualTo(eventCode);
    	List<WxEventCode> eventCodeList =  wxEventCodeMapper.selectByExample(criteria);
    	if(eventCodeList!=null&&eventCodeList.size()>0){
    		return eventCodeList.get(0);
    	}
    	return null;
	}
    
    @Override
	public WxEventCode loadByTypeCode(short eventType, String eventCode) { 
    	WxEventCodeCriteria criteria = new WxEventCodeCriteria();
    	criteria.createCriteria().andEventTypeEqualTo(eventType).andEventCodeEqualTo(eventCode);
    	List<WxEventCode> eventCodeList =  wxEventCodeMapper.selectByExample(criteria);
    	if(eventCodeList!=null&&eventCodeList.size()>0){
    		return eventCodeList.get(0);
    	}
    	return null;
	}

    @Override
    public void afterPropertiesSet() throws Exception {
    }

	public WxEventCodeMapper getWxEventCodeMapper() {
		return wxEventCodeMapper;
	}

	public void setWxEventCodeMapper(WxEventCodeMapper wxEventCodeMapper) {
		this.wxEventCodeMapper = wxEventCodeMapper;
	}

	


}
