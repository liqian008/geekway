
package com.bruce.geekway.service.product.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bruce.foundation.model.paging.PagingResult;
import com.bruce.geekway.constants.ConstConfig;
import com.bruce.geekway.dao.mapper.WxProductMapper;
import com.bruce.geekway.model.WxProduct;
import com.bruce.geekway.model.WxProductCriteria;
import com.bruce.geekway.service.product.IWxProductService;
import com.bruce.geekway.service.product.IWxProductSkuService;

@Service
public class WxProductServiceImpl implements IWxProductService {

	@Autowired
	private WxProductMapper wxProductMapper;
	@Autowired
	private IWxProductSkuService wxProductSkuService;
	

	@Override
	public int save(WxProduct t) {
		return wxProductMapper.insertSelective(t);
	}

	@Override
	public int updateById(WxProduct t) {
		return wxProductMapper.updateByPrimaryKeySelective(t);
	}

	@Override
	public int updateByCriteria(WxProduct t, WxProductCriteria criteria) {
		return wxProductMapper.updateByExampleSelective(t, criteria);
	}

	@Override
	public int deleteById(Integer id) {
		return wxProductMapper.deleteByPrimaryKey(id);
	}

	@Override
	public int deleteByCriteria(WxProductCriteria criteria) {
		return wxProductMapper.deleteByExample(criteria);
	}

	@Override
	public WxProduct loadById(Integer id) {
		return wxProductMapper.selectByPrimaryKey(id);
	}

	@Override
	public List<WxProduct> queryAll() {
		return queryAll(null);
	}

	@Override
	public List<WxProduct> queryAll(String orderByClause) {
		WxProductCriteria criteria = new WxProductCriteria();
		criteria.createCriteria();
		criteria.setOrderByClause(orderByClause);
		return queryByCriteria(criteria);
	}

	@Override
	public List<WxProduct> queryByCriteria(WxProductCriteria criteria) {
		return wxProductMapper.selectByExample(criteria);
	}
	

	@Override
	public List<WxProduct> fallloadByCriteria(int pageSize,
			WxProductCriteria criteria) {
		return null;
	}

	@Override
	public PagingResult<WxProduct> pagingByCriteria(int pageNo, int pageSize,
			WxProductCriteria criteria) {
		pageNo = pageNo<=0?1:pageNo;//确保pageNo合法
		pageSize = pageNo<=0?ConstConfig.PAGE_SIZE_DEFAULT:pageSize;//确保pageSize合法
		int offset = (pageNo-1)*pageSize;
		
		//构造查询条件
		if(criteria==null){
			criteria = new WxProductCriteria();
		}
		
		criteria.setLimitOffset(offset);
		criteria.setLimitRows(pageSize);
		
		int count = wxProductMapper.countByExample(criteria);
		List<WxProduct> dataList = wxProductMapper.selectByExample(criteria);
		//返回分页数据
		return new PagingResult<WxProduct>(pageNo, pageSize, count, dataList);
	}
	
	

	@Override
	public List<WxProduct> queryAvailableList() {
		WxProductCriteria criteria = new WxProductCriteria();
		criteria.createCriteria().andStatusEqualTo((short) 1);
		return wxProductMapper.selectByExample(criteria);
	}
	
	
	
	@Override
	public List<WxProduct> queryProductsByTagId(int tagId) {
		return wxProductMapper.queryProductsByTagId(tagId); 
	}

	@Override
	public List<WxProduct> queryProductsOutTagId(int tagId) {
		return wxProductMapper.queryProductsOutTagId(tagId); 
	}

	

//	@Transactional(propagation=Propagation.REQUIRED)
//	@Override
//	public int txTest() {
//		Date currentTime = new Date();
//		WxProduct product = new WxProduct();
//		product.setId(1);
//		product.setCreateTime(currentTime);
//		save(product);
//		
//		WxProductSku sku = new WxProductSku();
//		sku.setId(1);
//		sku.setProductId(1);
//		sku.setCreateTime(currentTime);
//		wxProductSkuService.save(sku);
//		
//		return 0;
//	}

	
	public WxProductMapper getWxProductMapper() {
		return wxProductMapper;
	}

	public void setWxProductMapper(WxProductMapper wxPayProductMapper) {
		this.wxProductMapper = wxPayProductMapper;
	}

	public IWxProductSkuService getWxProductSkuService() {
		return wxProductSkuService;
	}

	public void setWxProductSkuService(IWxProductSkuService wxProductSkuService) {
		this.wxProductSkuService = wxProductSkuService;
	}

	
	
}