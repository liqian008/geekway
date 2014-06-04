package com.bruce.geekway.dao;

import com.bruce.geekway.model.WxCommandMaterial;

public interface IWxCommandMaterialDao extends IBaseDao<WxCommandMaterial, Integer> {
	
	public int delete(int commandId, int deleteByMaterialId);
	
	public int deleteByCommandId(int commandId); 

	public int deleteByMaterialId(int materialId);
	
	public int topCommandMaterial(int commandId, int materialId);
	
}
