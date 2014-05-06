package com.bruce.geekway.dao;

import java.util.List;

import com.bruce.geekway.model.WxMaterial;

public interface IWxMaterialDao extends IBaseDao<WxMaterial, Integer> {
	
    public List<WxMaterial> queryMaterialsByCommandId(int commandId);
    
    public List<WxMaterial> queryMaterialsByCommandId(int commandId, int limit);

    public List<WxMaterial> queryMaterialsOutCommandId(int commandId);

    
}
