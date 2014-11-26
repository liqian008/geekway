package com.bruce.geekway.service.product.impl;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bruce.geekway.dao.mapper.WxProductVoucherMapper;
import com.bruce.geekway.model.WxProductVoucher;
import com.bruce.geekway.model.WxProductVoucherCriteria;
import com.bruce.geekway.model.enumeration.GeekwayEnum;
import com.bruce.geekway.service.product.IWxProductVoucherService;

@Service
public class WxProductVoucherServiceImpl implements IWxProductVoucherService {

	@Autowired
	private WxProductVoucherMapper wxProductVoucherMapper;

	@Override
	public int save(WxProductVoucher t) {
		return wxProductVoucherMapper.insertSelective(t);
	}

	@Override
	public int updateById(WxProductVoucher t) {
		return wxProductVoucherMapper.updateByPrimaryKeySelective(t);
	}

	@Override
	public int updateByCriteria(WxProductVoucher t, WxProductVoucherCriteria criteria) {
		return wxProductVoucherMapper.updateByExampleSelective(t, criteria);
	}

	@Override
	public int deleteById(Long id) {
		return wxProductVoucherMapper.deleteByPrimaryKey(id);
	}

	@Override
	public int deleteByCriteria(WxProductVoucherCriteria criteria) {
		return wxProductVoucherMapper.deleteByExample(criteria);
	}

	@Override
	public WxProductVoucher loadById(Long id) {
		return wxProductVoucherMapper.selectByPrimaryKey(id);
	}

	@Override
	public List<WxProductVoucher> queryAll() {
		return queryAll(null);
	}

	@Override
	public List<WxProductVoucher> queryAll(String orderByClause) {
		WxProductVoucherCriteria criteria = new WxProductVoucherCriteria();
		criteria.createCriteria();
		criteria.setOrderByClause(orderByClause);
		return queryByCriteria(criteria);
	}

	@Override
	public List<WxProductVoucher> queryByCriteria(WxProductVoucherCriteria criteria) {
		return wxProductVoucherMapper.selectByExample(criteria);
	}
	
	@Override
	public int countByCriteria(WxProductVoucherCriteria criteria) {
		return wxProductVoucherMapper.countByExample(criteria);
	}
	
	@Override
	public List<WxProductVoucher> fallLoadUserVoucherList(String userOpenId, long voucherTailId, int limit) {
		WxProductVoucherCriteria criteria = new WxProductVoucherCriteria();
		WxProductVoucherCriteria.Criteria subCriteria =  criteria.createCriteria();
		subCriteria.andUserOpenIdEqualTo(userOpenId);
		if(voucherTailId>0){
			subCriteria.andIdLessThan(voucherTailId);
		}
		criteria.setLimitRows(limit);
		criteria.setOrderByClause(" id desc");
		return queryByCriteria(criteria);
	}
	
	public List<WxProductVoucher> queryUserAvailableVoucherList(String userOpenId, int limit){
		WxProductVoucherCriteria criteria = new WxProductVoucherCriteria();
		WxProductVoucherCriteria.Criteria subCriteria = criteria.createCriteria();
		subCriteria.andUserOpenIdEqualTo(userOpenId).andStatusEqualTo(GeekwayEnum.ProductVoucherStatusEnum.AVAILABLE.getStatus());
		criteria.setLimitRows(limit);
		criteria.setOrderByClause(" id desc");
		return queryByCriteria(criteria);
	}
	
	
	@Override
	public WxProductVoucher loadUserVoucherById(String userOpenId, long voucherId) {
		WxProductVoucherCriteria criteria = new WxProductVoucherCriteria();
		criteria.createCriteria().andIdEqualTo(voucherId).andUserOpenIdEqualTo(userOpenId);
		List<WxProductVoucher> voucherList =  queryByCriteria(criteria);
		if(voucherList!=null&&voucherList.size()>0){
			return voucherList.get(0);
		}
		return null;
	}
	
	public boolean verifyVoucher(String userOpenId, long voucherId){
		WxProductVoucherCriteria criteria = new WxProductVoucherCriteria();
		criteria.createCriteria().andIdEqualTo(voucherId).andUserOpenIdEqualTo(userOpenId).andStatusEqualTo((short) 1);
		return wxProductVoucherMapper.countByExample(criteria) >=1;
	}
	

	@Override
	public int changeStatus(String userOpenId, long voucherId, short status) {
		WxProductVoucherCriteria criteria = new WxProductVoucherCriteria();
		criteria.createCriteria().andIdEqualTo(voucherId).andUserOpenIdEqualTo(userOpenId);
		WxProductVoucher productVoucher = new WxProductVoucher();
		productVoucher.setStatus(status);
		return updateByCriteria(productVoucher, criteria);
	}


	@Override
	public WxProductVoucher applyVoucher(String userOpenId) {
		WxProductVoucher voucher = new WxProductVoucher();
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
	
	
	public WxProductVoucherMapper getWxProductVoucherMapper() {
		return wxProductVoucherMapper;
	}

	public void setWxProductVoucherMapper(WxProductVoucherMapper wxPayProductVoucherMapper) {
		this.wxProductVoucherMapper = wxPayProductVoucherMapper;
	}


}