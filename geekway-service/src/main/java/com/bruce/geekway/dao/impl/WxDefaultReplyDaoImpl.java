package com.bruce.geekway.dao.impl;

import java.util.List;

import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.bruce.geekway.dao.IWxDefaultReplyDao;
import com.bruce.geekway.dao.mapper.WxDefaultReplyMapper;
import com.bruce.geekway.model.WxDefaultReply;


@Repository
public class WxDefaultReplyDaoImpl implements IWxDefaultReplyDao, InitializingBean {

     @Autowired
    private WxDefaultReplyMapper wxDefaultReplyMapper;

    @Override
    public int save(WxDefaultReply t) {
        return wxDefaultReplyMapper.insert(t);
    }

    @Override
    public int updateById(WxDefaultReply t) {
        return wxDefaultReplyMapper.updateByPrimaryKeySelective(t);
    }

    @Override
    public int deleteById(Integer id) {
        return wxDefaultReplyMapper.deleteByPrimaryKey(id);
    }

    @Override
    public WxDefaultReply loadById(Integer id) {
        return wxDefaultReplyMapper.selectByPrimaryKey(id);
    }

    @Override
    public List<WxDefaultReply> queryAll() {
        return wxDefaultReplyMapper.selectByExample(null);
    }

    @Override
    public List<WxDefaultReply> fallLoadList(Integer tailId, int limit) {
        return null;
    }

    @Override
    public void afterPropertiesSet() throws Exception {
    	
    }
    
    @Override
	public List<WxDefaultReply> loadDefaultReply(int id) {
		// TODO Auto-generated method stub
		return null;
	}

	public WxDefaultReplyMapper getWxDefaultReplyMapper() {
		return wxDefaultReplyMapper;
	}

	public void setWxDefaultReplyMapper(WxDefaultReplyMapper wxDefaultReplyMapper) {
		this.wxDefaultReplyMapper = wxDefaultReplyMapper;
	}

	

}
