package com.bruce.geekway.service;

import com.bruce.geekway.model.WxCommandMaterial;

public interface IWxCommandMaterialService extends IBaseService<WxCommandMaterial, Integer>{

	public int delete(int commandId, int materialId); 
	
	public int deleteByCommandId(int commandId);
	
	public int deleteByMaterialId(int materialId); 
	
}