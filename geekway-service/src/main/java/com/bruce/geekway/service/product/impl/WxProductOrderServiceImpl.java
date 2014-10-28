package com.bruce.geekway.service.product.impl;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bruce.foundation.model.paging.PagingResult;
import com.bruce.geekway.dao.mapper.WxProductOrderMapper;
import com.bruce.geekway.model.WxProductOrder;
import com.bruce.geekway.model.WxProductOrderCriteria;
import com.bruce.geekway.model.WxProductOrder;
import com.bruce.geekway.model.WxProductOrderCriteria;
import com.bruce.geekway.model.WxProductOrderItem;
import com.bruce.geekway.model.WxUserAddress;
import com.bruce.geekway.model.exception.ErrorCode;
import com.bruce.geekway.model.exception.GeekwayException;
import com.bruce.geekway.service.product.IWxDeliveryTemplateService;
import com.bruce.geekway.service.product.IWxProductOrderItemService;
import com.bruce.geekway.service.product.IWxProductOrderService;
import com.bruce.geekway.service.product.IWxProductVoucherService;
import com.bruce.geekway.service.product.IWxUserAddressService;
import com.bruce.geekway.utils.OrderUtil;

@Service
public class WxProductOrderServiceImpl implements IWxProductOrderService {

	@Autowired
	private WxProductOrderMapper wxProductOrderMapper;
	@Autowired
	private IWxProductOrderItemService wxProductOrderItemService;
	@Autowired
	private IWxProductVoucherService wxProductVoucherService;
	@Autowired
	private IWxUserAddressService wxUserAddressService;
	@Autowired
	private IWxDeliveryTemplateService wxDeliveryTemplateService;
	
	
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
	public List<WxProductOrder> fallloadByCriteria(int pageSize,
			WxProductOrderCriteria criteria) {
		return null;
	}

	@Override
	public PagingResult<WxProductOrder> pagingByCriteria(int pageNo,
			int pageSize, WxProductOrderCriteria criteria) {
		pageNo = pageNo<=0?1:pageNo;//确保pageNo合法
		pageSize = pageNo<=0?20:pageSize;//确保pageSize合法
		int offset = (pageNo-1)*pageSize;
		
		//构造查询条件
		if(criteria==null){
			criteria = new WxProductOrderCriteria();
		}
		
		criteria.setLimitOffset(offset);
		criteria.setLimitRows(pageSize);
		
		int count = wxProductOrderMapper.countByExample(criteria);
		List<WxProductOrder> dataList = wxProductOrderMapper.selectByExample(criteria);
		//返回分页数据
		return new PagingResult<WxProductOrder>(pageNo, pageSize, count, dataList);
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
	public int createOrder(WxProductOrder productOrder, WxUserAddress addressInfo, List<WxProductOrderItem> orderItemList) {
		if(productOrder==null){
			throw new GeekwayException(ErrorCode.WX_PRODUCT_ORDER_CREATE_ERROR);
		}
		Date currentTime = new Date();
		String tradeNo = OrderUtil.generateOrderSn4Wx();
		//保存订单
		productOrder.setOutTradeNo(tradeNo);
		
		double productTotalFee = 0;
		int totalBuyAmount = 0;
		if(orderItemList!=null&&orderItemList.size()>0){
			for(WxProductOrderItem orderItem:orderItemList){
				double itemTotalPrice = orderItem.getTotalFee();//单品总金额
				productTotalFee = productTotalFee + itemTotalPrice;
				totalBuyAmount = totalBuyAmount + orderItem.getAmount();
				orderItem.setOutTradeNo(tradeNo);
				wxProductOrderItemService.save(orderItem);
			}
		}
		productOrder.setTotalFee(productTotalFee);
		//总数量
		
		double transportFee = wxDeliveryTemplateService.calcDeliveryFee(0, productTotalFee, totalBuyAmount, "", addressInfo.getPostProvince(), addressInfo.getPostCity());
		//折扣费用
		double discountFee = 0;//voucher==null?0:voucher.getPrice();
		
		productOrder.setTitle("");//TODO
		productOrder.setProductFee(productTotalFee);
		productOrder.setDiscountFee(discountFee);//折扣费用
		productOrder.setTransportFee(transportFee);//运费
		double totalFee = productTotalFee - discountFee + transportFee;
		productOrder.setTotalFee(totalFee);//总费用
		productOrder.setStatus(IWxProductOrderService.StatusEnum.SUBMITED.getStatus());//预支付状态
		productOrder.setCreateTime(currentTime);
		//组装邮寄地址
		populatePostInfo(productOrder, addressInfo);
		
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
	
	
	
	/**
	 * 将用户地址信息填写到订单对象中
	 * @param productOrder
	 * @param addressInfo
	 */
	private void populatePostInfo(WxProductOrder productOrder, WxUserAddress addressInfo) {
		if(productOrder!=null&&addressInfo!=null){
			productOrder.setPostName(addressInfo.getPostName());
			productOrder.setPostMobile(addressInfo.getPostMobile());
			productOrder.setPostCode(addressInfo.getPostCode());
			
			productOrder.setPostProvince(addressInfo.getPostProvince());
			productOrder.setPostCity(addressInfo.getPostCity());
			productOrder.setPostCountries(addressInfo.getPostCountries());
			
			productOrder.setPostAddressDetailInfo(addressInfo.getPostAddressDetailInfo());
			productOrder.setPostNationalCode(addressInfo.getPostNationalCode());
		}
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

	public IWxProductOrderItemService getWxProductOrderItemService() {
		return wxProductOrderItemService;
	}

	public void setWxProductOrderItemService(
			IWxProductOrderItemService wxProductOrderItemService) {
		this.wxProductOrderItemService = wxProductOrderItemService;
	}

	public IWxDeliveryTemplateService getWxDeliveryTemplateService() {
		return wxDeliveryTemplateService;
	}

	public void setWxDeliveryTemplateService(
			IWxDeliveryTemplateService wxDeliveryTemplateService) {
		this.wxDeliveryTemplateService = wxDeliveryTemplateService;
	}

	
	
}
