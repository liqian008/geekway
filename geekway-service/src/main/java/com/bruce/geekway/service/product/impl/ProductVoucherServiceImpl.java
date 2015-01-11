package com.bruce.geekway.service.product.impl;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bruce.geekway.dao.mapper.ProductVoucherMapper;
import com.bruce.geekway.model.ProductVoucher;
import com.bruce.geekway.model.ProductVoucherCriteria;
import com.bruce.geekway.model.enumeration.GeekwayEnum;
import com.bruce.geekway.service.product.IProductVoucherService;

@Service
public class ProductVoucherServiceImpl implements IProductVoucherService {

	@Autowired
	private ProductVoucherMapper productVoucherMapper;

	@Override
	public int save(ProductVoucher t) {
		return productVoucherMapper.insertSelective(t);
	}

	@Override
	public int updateById(ProductVoucher t) {
		return productVoucherMapper.updateByPrimaryKeySelective(t);
	}

	@Override
	public int updateByCriteria(ProductVoucher t, ProductVoucherCriteria criteria) {
		return productVoucherMapper.updateByExampleSelective(t, criteria);
	}

	@Override
	public int deleteById(Long id) {
		return productVoucherMapper.deleteByPrimaryKey(id);
	}

	@Override
	public int deleteByCriteria(ProductVoucherCriteria criteria) {
		return productVoucherMapper.deleteByExample(criteria);
	}

	@Override
	public ProductVoucher loadById(Long id) {
		return productVoucherMapper.selectByPrimaryKey(id);
	}

	@Override
	public List<ProductVoucher> queryAll() {
		return queryAll(null);
	}

	@Override
	public List<ProductVoucher> queryAll(String orderByClause) {
		ProductVoucherCriteria criteria = new ProductVoucherCriteria();
		criteria.createCriteria();
		criteria.setOrderByClause(orderByClause);
		return queryByCriteria(criteria);
	}

	@Override
	public List<ProductVoucher> queryByCriteria(ProductVoucherCriteria criteria) {
		return productVoucherMapper.selectByExample(criteria);
	}
	
	@Override
	public int countByCriteria(ProductVoucherCriteria criteria) {
		return productVoucherMapper.countByExample(criteria);
	}
	
	@Override
	public List<ProductVoucher> fallLoadUserVoucherList(String userOpenId, long voucherTailId, int limit) {
		ProductVoucherCriteria criteria = new ProductVoucherCriteria();
		ProductVoucherCriteria.Criteria subCriteria =  criteria.createCriteria();
		subCriteria.andUserOpenIdEqualTo(userOpenId);
		if(voucherTailId>0){
			subCriteria.andIdLessThan(voucherTailId);
		}
		criteria.setLimitRows(limit);
		criteria.setOrderByClause(" id desc");
		return queryByCriteria(criteria);
	}
	
	public List<ProductVoucher> queryUserAvailableVoucherList(String userOpenId, int limit){
		ProductVoucherCriteria criteria = new ProductVoucherCriteria();
		ProductVoucherCriteria.Criteria subCriteria = criteria.createCriteria();
		subCriteria.andUserOpenIdEqualTo(userOpenId).andStatusEqualTo(GeekwayEnum.ProductVoucherStatusEnum.AVAILABLE.getStatus());
		criteria.setLimitRows(limit);
		criteria.setOrderByClause(" id desc");
		return queryByCriteria(criteria);
	}
	
	
	@Override
	public ProductVoucher loadUserVoucherById(String userOpenId, long voucherId) {
		ProductVoucherCriteria criteria = new ProductVoucherCriteria();
		criteria.createCriteria().andIdEqualTo(voucherId).andUserOpenIdEqualTo(userOpenId);
		List<ProductVoucher> voucherList =  queryByCriteria(criteria);
		if(voucherList!=null&&voucherList.size()>0){
			return voucherList.get(0);
		}
		return null;
	}
	
	public boolean verifyVoucher(String userOpenId, long voucherId){
		ProductVoucherCriteria criteria = new ProductVoucherCriteria();
		criteria.createCriteria().andIdEqualTo(voucherId).andUserOpenIdEqualTo(userOpenId).andStatusEqualTo((short) 1);
		return productVoucherMapper.countByExample(criteria) >=1;
	}
	

	@Override
	public int changeStatus(String userOpenId, long voucherId, short status) {
		ProductVoucherCriteria criteria = new ProductVoucherCriteria();
		criteria.createCriteria().andIdEqualTo(voucherId).andUserOpenIdEqualTo(userOpenId);
		ProductVoucher productVoucher = new ProductVoucher();
		productVoucher.setStatus(status);
		return updateByCriteria(productVoucher, criteria);
	}


	@Override
	public ProductVoucher applyVoucher(String userOpenId) {
		ProductVoucher voucher = new ProductVoucher();
		voucher.setUserOpenId(userOpenId);
		Date currentTime = new Date();
		voucher.setStatus(GeekwayEnum.ProductVoucherStatusEnum.AVAILABLE.getStatus());
		//TODO 配置文件化
		voucher.setPrice(5d);//5元优惠券
		voucher.setVoucherCode("");//展示用，无具体意义
		voucher.setCreateTime(currentTime);
		int result =  save(voucher);
		if(result>0){
			return voucher;
		}
		return null;
	}

	public ProductVoucherMapper getProductVoucherMapper() {
		return productVoucherMapper;
	}

	public void setProductVoucherMapper(ProductVoucherMapper productVoucherMapper) {
		this.productVoucherMapper = productVoucherMapper;
	}
	

}