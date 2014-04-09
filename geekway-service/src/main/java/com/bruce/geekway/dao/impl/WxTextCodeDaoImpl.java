package com.bruce.geekway.dao.impl;

import java.util.List;

import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.bruce.geekway.dao.IWxTextCodeDao;
import com.bruce.geekway.dao.mapper.WxTextCodeMapper;
import com.bruce.geekway.model.WxTextCode;
import com.bruce.geekway.model.WxTextCodeCriteria;


@Repository
public class WxTextCodeDaoImpl implements IWxTextCodeDao, InitializingBean {

     @Autowired
    private WxTextCodeMapper wxTextCodeMapper;

    @Override
    public int save(WxTextCode t) {
        return wxTextCodeMapper.insert(t);
    }

    @Override
    public int updateById(WxTextCode t) {
        return wxTextCodeMapper.updateByPrimaryKey(t);
    }

    @Override
    public int deleteById(Integer id) {
        return wxTextCodeMapper.deleteByPrimaryKey(id);
    }

    @Override
    public WxTextCode loadById(Integer id) {
        return wxTextCodeMapper.selectByPrimaryKey(id);
    }

    @Override
    public List<WxTextCode> queryAll() {
        return wxTextCodeMapper.selectByExample(null);
    }

    @Override
    public List<WxTextCode> fallLoadList(Integer tailId, int limit) {
        return null;
    }
    
    @Override
	public WxTextCode loadByCode(String textCode) {
    	WxTextCodeCriteria criteria = new WxTextCodeCriteria();
    	criteria.createCriteria().andKeyCodeEqualTo(textCode);
    	List<WxTextCode> textCodeList =  wxTextCodeMapper.selectByExample(criteria);
    	if(textCodeList!=null&&textCodeList.size()>0){
    		return textCodeList.get(0);
    	}
    	return null;
	}
    

    @Override
    public void afterPropertiesSet() throws Exception {

    }

	public WxTextCodeMapper getWxTextCodeMapper() {
		return wxTextCodeMapper;
	}

	public void setWxTextCodeMapper(WxTextCodeMapper wxTextCodeMapper) {
		this.wxTextCodeMapper = wxTextCodeMapper;
	}

	


}
