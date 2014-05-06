package com.bruce.geekway.service;

import java.util.List;

import com.bruce.geekway.model.WxMaterial;

public interface IWxMaterialService extends IBaseService<WxMaterial, Integer>{

	public List<WxMaterial> queryMaterialsByCommandId(int moduleId);
	
	public List<WxMaterial> queryMaterialsByCommandId(int moduleId, int limit);

	public List<WxMaterial> queryMaterialsOutCommandId(int moduleId);
	
	
}