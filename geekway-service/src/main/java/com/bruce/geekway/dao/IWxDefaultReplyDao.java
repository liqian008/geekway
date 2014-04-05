package com.bruce.geekway.dao;

import java.util.List;

import com.bruce.geekway.model.WxDefaultReply;

public interface IWxDefaultReplyDao extends IBaseDao<WxDefaultReply, Integer> {
	
    public List<WxDefaultReply> loadDefaultReply(int id);
    
}
