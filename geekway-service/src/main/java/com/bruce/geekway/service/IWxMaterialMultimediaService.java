package com.bruce.geekway.service;

import java.util.List;

import com.bruce.foundation.service.IFoundationPagingService;
import com.bruce.geekway.model.WxMaterialMultimedia;
import com.bruce.geekway.model.WxMaterialMultimediaCriteria;

/**
 * 多媒体素材(图片、语音)service
 * @author liqian
 *
 */
public interface IWxMaterialMultimediaService extends IFoundationPagingService<WxMaterialMultimedia, Integer, WxMaterialMultimediaCriteria>{

	/*文本素材*/
	public static final Short MATERIAL_TYPE_TEXT = 0;
	/*单图文素材*/
	public static final Short MATERIAL_TYPE_ARTICLE = 1;
	/*多图文素材*/
	public static final Short MATERIAL_TYPE_NEWS = 2;
	/*图片素材*/
	public static final Short MATERIAL_TYPE_IMAGE = 3;
	/*语音素材*/
	public static final Short MATERIAL_TYPE_VOICE = 4;
	
	/*根据id加载图片素材*/
	public WxMaterialMultimedia loadImageById(Integer id);
	
	/*根据id加载语音素材*/
	public WxMaterialMultimedia loadVoiceById(Integer id);
	
	
	/*图片素材列表*/
	public List<WxMaterialMultimedia> queryImageMaterials();
	
	/*语音素材列表*/
	public List<WxMaterialMultimedia> queryVoiceMaterials();
	
	/*查询command对应的多媒体素材*/
//	public WxMaterialMultimedia queryByCommandId(int commandId);
	
}