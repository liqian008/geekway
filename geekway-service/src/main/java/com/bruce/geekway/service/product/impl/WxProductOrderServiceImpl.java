package com.bruce.geekway.service.product.impl;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bruce.geekway.dao.mapper.WxProductOrderMapper;
import com.bruce.geekway.model.WxProductOrder;
import com.bruce.geekway.model.WxProductOrderCriteria;
import com.bruce.geekway.model.WxUserAddress;
import com.bruce.geekway.model.exception.ErrorCode;
import com.bruce.geekway.model.exception.GeekwayException;
import com.bruce.geekway.service.product.IWxProductOrderService;
import com.bruce.geekway.service.product.IWxProductVoucherService;
import com.bruce.geekway.service.product.IWxUserAddressService;
import com.bruce.geekway.utils.OrderUtil;

@Service
public class WxProductOrderServiceImpl implements IWxProductOrderService {

	@Autowired
	private WxProductOrderMapper wxProductOrderMapper;
	@Autowired
	private IWxProductVoucherService wxProductVoucherService;
	@Autowired
	private IWxUserAddressService wxUserAddressService;
	
	
	@Override
	public int save(WxProductOrder t) {
		return wxProductOrderMapper.insertSelective(t);
	}

	@Override
	public int updateById(WxProductOrder t) {
		return wxProductOrderMapper.updateByPrimaryKeySelective(t);
	}

	@Override
	public int updateByCriteria(WxProductOrder t, WxProductOrderCriteria criteria) {
		return wxProductOrderMapper.updateByExampleSelective(t, criteria);
	}

	@Override
	public int deleteById(Long id) {
		return wxProductOrderMapper.deleteByPrimaryKey(id);
	}

	@Override
	public int deleteByCriteria(WxProductOrderCriteria criteria) {
		return wxProductOrderMapper.deleteByExample(criteria);
	}

	@Override
	public WxProductOrder loadById(Long id) {
		return wxProductOrderMapper.selectByPrimaryKey(id);
	}

	@Override
	public List<WxProductOrder> queryAll() {
		return queryAll(null);
	}

	@Override
	public List<WxProductOrder> queryAll(String orderByClause) {
		WxProductOrderCriteria criteria = new WxProductOrderCriteria();
		criteria.createCriteria();
		criteria.setOrderByClause(orderByClause);
		return queryByCriteria(criteria);
	}

	@Override
	public List<WxProductOrder> queryByCriteria(WxProductOrderCriteria criteria) {
		return wxProductOrderMapper.selectByExample(criteria);
	}
	
	@Override
	public List<WxProductOrder> fallLoadUserOrderList(String userOpenId, long orderTailId, int limit) {
		WxProductOrderCriteria criteria = new WxProductOrderCriteria();
		WxProductOrderCriteria.Criteria subCriteria =  criteria.createCriteria();
		subCriteria.andUserOpenIdEqualTo(userOpenId); 
		if(orderTailId>0){
			subCriteria.andIdLessThan(orderTailId);
		}
		criteria.setLimitRows(limit);
		criteria.setOrderByClause(" id desc");
		return queryByCriteria(criteria);
	}
	
	
	@Override
	public int changeOrderStatus(String outTradeNo, short status) {
		WxProductOrderCriteria criteria = new WxProductOrderCriteria();
		criteria.createCriteria().andOutTradeNoEqualTo(outTradeNo);
		
		WxProductOrder wxProductOrder = new WxProductOrder();
		wxProductOrder.setStatus(status);
		return updateByCriteria(wxProductOrder, criteria);
	}
	
	
	@Override
	public WxProductOrder loadByTradeNo(String outTradeNo) {
		WxProductOrderCriteria criteria = new WxProductOrderCriteria();
		criteria.createCriteria().andOutTradeNoEqualTo(outTradeNo);
		List<WxProductOrder> productOrderList = queryByCriteria(criteria);
		if(productOrderList!=null&&productOrderList.size()>0){
			return productOrderList.get(0);
		}
		return null;
	}
	
	@Override
	public WxProductOrder loadUserOrderInfo(String userOpenId, long orderId, String tradeNo) {
		WxProductOrderCriteria criteria = new WxProductOrderCriteria();
		criteria.createCriteria().andIdEqualTo(orderId).andUserOpenIdEqualTo(userOpenId).andOutTradeNoEqualTo(tradeNo);
		List<WxProductOrder> productOrderList = queryByCriteria(criteria);
		if(productOrderList!=null&&productOrderList.size()>0){
			return productOrderList.get(0);
		}
		return null;
	}
	
	/**
	 * 生成订单，供用户进行支付
	 */
	@Override
	public int createOrder(WxProductOrder productOrder, WxUserAddress addressInfo) {
		if(productOrder==null){
			throw new GeekwayException(ErrorCode.WX_PRODUCT_ORDER_CREATE_ERROR);
		}
		Date currentTime = new Date();
		String tradeNo = OrderUtil.generateOrderSn4Wx();
		//保存订单
		productOrder.setOutTradeNo(tradeNo);
		int result = save(productOrder);
		//下单不扣减库存（支付成功后才扣减）
		
		
		//标记优惠码状态为正在使用
		if(productOrder.getVoucherId()!=null&&productOrder.getVoucherId()>0){
			result = wxProductVoucherService.changeStatus(productOrder.getUserOpenId(), productOrder.getVoucherId(), IWxProductVoucherService.StatusEnum.USED.getStatus());
		}
		if(addressInfo!=null&&result>0){
			//保存用户邮寄地址信息
			addressInfo.setUserOpenId(productOrder.getUserOpenId());
			addressInfo.setCreateTime(currentTime);
			wxUserAddressService.save(addressInfo);
		}
		return result;
	}

	public WxProductOrderMapper getWxProductOrderMapper() {
		return wxProductOrderMapper;
	}

	public void setWxProductOrderMapper(WxProductOrderMapper wxPayProductOrderMapper) {
		this.wxProductOrderMapper = wxPayProductOrderMapper;
	}

	public IWxProductVoucherService getWxProductVoucherService() {
		return wxProductVoucherService;
	}

	public void setWxProductVoucherService(IWxProductVoucherService wxProductVoucherService) {
		this.wxProductVoucherService = wxProductVoucherService;
	}

	public IWxUserAddressService getWxUserAddressService() {
		return wxUserAddressService;
	}

	public void setWxUserAddressService(IWxUserAddressService wxUserAddressService) {
		this.wxUserAddressService = wxUserAddressService;
	}

}
