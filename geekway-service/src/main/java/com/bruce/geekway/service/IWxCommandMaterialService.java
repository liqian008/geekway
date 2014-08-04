package com.bruce.geekway.service;

import com.bruce.foundation.service.IFoundationPagingService;
import com.bruce.geekway.model.WxCommandMaterial;
import com.bruce.geekway.model.WxCommandMaterialCriteria;

public interface IWxCommandMaterialService extends IFoundationPagingService<WxCommandMaterial, Integer, WxCommandMaterialCriteria> {
	
	public int delete(int commandId, int materialId);
	
	public int deleteByCommandId(int commandId);
	
	public int deleteByMaterialId(int materialId);
	
	
	/** 
	 * 置顶
	 * @param commandId
	 * @param materialId
	 * @return
	 */
	public int topCommandMaterial(int commandId, int materialId);
	
}
