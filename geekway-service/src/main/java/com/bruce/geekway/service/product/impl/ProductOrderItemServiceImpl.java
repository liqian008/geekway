package com.bruce.geekway.service.product.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import com.bruce.geekway.dao.mapper.ProductOrderItemMapper;
import com.bruce.geekway.model.ProductOrderItem;
import com.bruce.geekway.model.ProductOrderItemCriteria;
import com.bruce.geekway.service.product.IProductOrderItemService;

public class ProductOrderItemServiceImpl implements IProductOrderItemService {

	@Autowired
	private ProductOrderItemMapper productOrderItemMapper;

	@Override
	public int save(ProductOrderItem t) {
		return productOrderItemMapper.insertSelective(t);
	}

	@Override
	public int updateById(ProductOrderItem t) {
		return productOrderItemMapper.updateByPrimaryKeySelective(t);
	}

	@Override
	public int updateByCriteria(ProductOrderItem t, ProductOrderItemCriteria criteria) {
		return productOrderItemMapper.updateByExampleSelective(t, criteria);
	}

	@Override
	public int deleteById(Long id) {
		return productOrderItemMapper.deleteByPrimaryKey(id);
	}

	@Override
	public int deleteByCriteria(ProductOrderItemCriteria criteria) {
		return productOrderItemMapper.deleteByExample(criteria);
	}

	@Override
	public ProductOrderItem loadById(Long id) {
		return productOrderItemMapper.selectByPrimaryKey(id);
	}

	@Override
	public List<ProductOrderItem> queryAll() {
		return queryAll(null);
	}

	@Override
	public List<ProductOrderItem> queryAll(String orderByClause) {
		ProductOrderItemCriteria criteria = new ProductOrderItemCriteria();
		criteria.createCriteria();
		criteria.setOrderByClause(orderByClause);
		return queryByCriteria(criteria);
	}

	@Override
	public List<ProductOrderItem> queryByCriteria(ProductOrderItemCriteria criteria) {
		return productOrderItemMapper.selectByExample(criteria);
	}
	

	@Override
	public int countByCriteria(ProductOrderItemCriteria criteria) {
		return productOrderItemMapper.countByExample(criteria);
	}
	
	
	@Override
	public List<ProductOrderItem> queryByTradeNo(String outTradeNo) {
		ProductOrderItemCriteria criteria = new ProductOrderItemCriteria();
		criteria.createCriteria().andOutTradeNoEqualTo(outTradeNo);
		return queryByCriteria(criteria);
	}

	public ProductOrderItemMapper getProductOrderItemMapper() {
		return productOrderItemMapper;
	}

	public void setProductOrderItemMapper(
			ProductOrderItemMapper productOrderItemMapper) {
		this.productOrderItemMapper = productOrderItemMapper;
	}
	
	
	
	
	
}
