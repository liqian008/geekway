package com.bruce.geekway.service.product.impl;

import java.util.Date;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;

import com.bruce.foundation.model.paging.PagingResult;
import com.bruce.geekway.constants.ConstConfig;
import com.bruce.geekway.constants.ConstMemc;
import com.bruce.geekway.dao.mapper.ProductOrderMapper;
import com.bruce.geekway.model.ProductOrder;
import com.bruce.geekway.model.ProductOrderCriteria;
import com.bruce.geekway.model.ProductOrderItem;
import com.bruce.geekway.model.UserAddress;
import com.bruce.geekway.model.enumeration.GeekwayEnum;
import com.bruce.geekway.model.exception.ErrorCode;
import com.bruce.geekway.model.exception.GeekwayException;
import com.bruce.geekway.service.product.IDeliveryTemplateService;
import com.bruce.geekway.service.product.IProductOrderItemService;
import com.bruce.geekway.service.product.IProductOrderService;
import com.bruce.geekway.service.product.IProductVoucherService;
import com.bruce.geekway.service.product.IUserAddressService;
import com.bruce.geekway.utils.OrderUtil;

public class ProductOrderServiceImpl implements IProductOrderService {

	@Autowired
	private ProductOrderMapper productOrderMapper;
	@Autowired
	private IProductOrderItemService productOrderItemService;
	@Autowired
	private IProductVoucherService productVoucherService;
	@Autowired
	private IUserAddressService userAddressService;
	@Autowired
	private IDeliveryTemplateService deliveryTemplateService;
	
	private static final Logger logger = LoggerFactory.getLogger(ProductOrderServiceImpl.class);
	
	@Override
	public int save(ProductOrder t) {
		return productOrderMapper.insertSelective(t);
	}

	@Override
	public int updateById(ProductOrder t) {
		return productOrderMapper.updateByPrimaryKeySelective(t);
	}

	@Override
	public int updateByCriteria(ProductOrder t, ProductOrderCriteria criteria) {
		return productOrderMapper.updateByExampleSelective(t, criteria);
	}

	@Override
	public int deleteById(Long id) {
		return productOrderMapper.deleteByPrimaryKey(id);
	}

	@Override
	public int deleteByCriteria(ProductOrderCriteria criteria) {
		return productOrderMapper.deleteByExample(criteria);
	}

	@Override
	public ProductOrder loadById(Long id) {
		return productOrderMapper.selectByPrimaryKey(id);
	}

	@Override
	public List<ProductOrder> queryAll() {
		return queryAll(null);
	}

	@Override
	public List<ProductOrder> queryAll(String orderByClause) {
		ProductOrderCriteria criteria = new ProductOrderCriteria();
		criteria.createCriteria();
		criteria.setOrderByClause(orderByClause);
		return queryByCriteria(criteria);
	}

	@Override
	public List<ProductOrder> queryByCriteria(ProductOrderCriteria criteria) {
		return productOrderMapper.selectByExample(criteria);
	}
	
	@Override
	public int countByCriteria(ProductOrderCriteria criteria) {
		return productOrderMapper.countByExample(criteria);
	}
	
	@Override
	public List<ProductOrder> fallloadByCriteria(int pageSize,
			ProductOrderCriteria criteria) {
		return null;
	}

	@Override
	public PagingResult<ProductOrder> pagingByCriteria(int pageNo,
			int pageSize, ProductOrderCriteria criteria) {
		pageNo = pageNo<=0?1:pageNo;//确保pageNo合法
		pageSize = pageNo<=0?ConstConfig.PAGE_SIZE_DEFAULT:pageSize;//确保pageSize合法
		int offset = (pageNo-1)*pageSize;
		
		//构造查询条件
		if(criteria==null){
			criteria = new ProductOrderCriteria();
		}
		
		criteria.setLimitOffset(offset);
		criteria.setLimitRows(pageSize);
		
		int count = productOrderMapper.countByExample(criteria);
		List<ProductOrder> dataList = productOrderMapper.selectByExample(criteria);
		//返回分页数据
		return new PagingResult<ProductOrder>(pageNo, pageSize, count, dataList);
	}
	
	
	@Override
	public List<ProductOrder> queryUserOrders(String userOpenId, short orderType, int pageNo, int pageSize, boolean fallload) {
		pageNo = pageNo<=0?1:pageNo;//确保pageNo合法
		pageSize = pageNo<=0?ConstConfig.PAGE_SIZE_DEFAULT:pageSize;//确保pageSize合法
		int offset = (pageNo-1)*pageSize;
		
		ProductOrderCriteria criteria = new ProductOrderCriteria();
		ProductOrderCriteria.Criteria subCriteria = criteria.createCriteria();
		subCriteria.andUserOpenIdEqualTo(userOpenId);
		if(orderType!=0){
			subCriteria.andStatusGreaterThan(GeekwayEnum.ProductOrderStatusEnum.SUBMITED.getStatus());
		}else{
			subCriteria.andStatusEqualTo(GeekwayEnum.ProductOrderStatusEnum.SUBMITED.getStatus());
		}
		criteria.setLimitOffset(offset);
		if(fallload){//如果是瀑布流方式加载，需要多加一行，以便于判断是否有下一页
			criteria.setLimitRows(pageSize+1);
		}else{
			criteria.setLimitRows(pageSize);
		}
		criteria.setOrderByClause(" id desc");
		return queryByCriteria(criteria);
	}
	
	
	@Override
	@Cacheable(value=ConstMemc.MEMCACHE_CACHE_VALUE, key="'userOpenId-'+#userOpenId+'orderType-'+#orderType", condition="#pageNo<=1")
	public List<ProductOrder> queryCachedUserOrders(String userOpenId, short orderType, int pageNo, int pageSize, boolean fallload) {
		logger.debug("fallload userOrderList from db. [userOpenId:"+userOpenId+", orderType:"+orderType+", pageNo:"+pageNo+", pageSize:"+pageSize+"]");
		return queryUserOrders(userOpenId, orderType, pageNo, pageSize, fallload);
	}
	
	/**
	 * 收到支付通知
	 */
	@Override
	public int markNotifyReceived(short payType, String outTradeNo, String transactionId) {
		ProductOrderCriteria criteria = new ProductOrderCriteria();
		criteria.createCriteria().andOutTradeNoEqualTo(outTradeNo).andStatusEqualTo(GeekwayEnum.ProductOrderStatusEnum.SUBMITED.getStatus());
		
		ProductOrder productOrder = new ProductOrder();
		productOrder.setPayType(payType);
		productOrder.setTransactionId(transactionId);
		productOrder.setStatus(GeekwayEnum.ProductOrderStatusEnum.PAYED.getStatus());
		return updateByCriteria(productOrder, criteria);
	}
	
	/**
	 * 标记为等待发货
	 */
	@Override
	public int markWaitingDelivery(String outTradeNo) {
		ProductOrderCriteria criteria = new ProductOrderCriteria();
		criteria.createCriteria().andOutTradeNoEqualTo(outTradeNo).andStatusEqualTo(GeekwayEnum.ProductOrderStatusEnum.PAYED.getStatus());
		;
		
		ProductOrder productOrder = new ProductOrder();
		productOrder.setStatus(GeekwayEnum.ProductOrderStatusEnum.WAITING_DELIVER.getStatus());
		return updateByCriteria(productOrder, criteria);
	} 

	/**
	 * 标记为已发货
	 */
	@Override
	public int markDelivered(String outTradeNo, short deliverType, String deliverSn) {
		ProductOrderCriteria criteria = new ProductOrderCriteria();
		criteria.createCriteria().andOutTradeNoEqualTo(outTradeNo).andStatusEqualTo(GeekwayEnum.ProductOrderStatusEnum.WAITING_DELIVER.getStatus());
		
		ProductOrder productOrder = new ProductOrder();
		productOrder.setDeliverTime(new Date());
		productOrder.setDeliverSn(deliverSn);
		productOrder.setDeliverType(deliverType);
		productOrder.setStatus(GeekwayEnum.ProductOrderStatusEnum.DELIVERED.getStatus());
		return updateByCriteria(productOrder, criteria);
	}
	
	@Override
	public ProductOrder loadByUserTradeNo(String userOpenId, String outTradeNo) {
		ProductOrderCriteria criteria = new ProductOrderCriteria();
		criteria.createCriteria().andOutTradeNoEqualTo(outTradeNo).andUserOpenIdEqualTo(userOpenId);
		List<ProductOrder> productOrderList = queryByCriteria(criteria);
		if(productOrderList!=null&&productOrderList.size()>0){
			return productOrderList.get(0);
		}
		return null;
	}
	
	@Override
	public ProductOrder loadByTradeNo(String outTradeNo) {
		ProductOrderCriteria criteria = new ProductOrderCriteria();
		criteria.createCriteria().andOutTradeNoEqualTo(outTradeNo);
		List<ProductOrder> productOrderList = queryByCriteria(criteria);
		if(productOrderList!=null&&productOrderList.size()>0){
			return productOrderList.get(0);
		}
		return null;
	}
	
	@Override
	public ProductOrder loadUserOrderInfo(String userOpenId, long orderId, String tradeNo) {
		ProductOrderCriteria criteria = new ProductOrderCriteria();
		criteria.createCriteria().andIdEqualTo(orderId).andUserOpenIdEqualTo(userOpenId).andOutTradeNoEqualTo(tradeNo);
		List<ProductOrder> productOrderList = queryByCriteria(criteria);
		if(productOrderList!=null&&productOrderList.size()>0){
			return productOrderList.get(0);
		}
		return null;
	}
	
	
	@Override
	public ProductOrder loadByTransactionId(String transactionId) {
		ProductOrderCriteria criteria = new ProductOrderCriteria();
		criteria.createCriteria().andTransactionIdEqualTo(transactionId);
		List<ProductOrder> productOrderList = queryByCriteria(criteria);
		if(productOrderList!=null&&productOrderList.size()>0){
			return productOrderList.get(0);
		}
		return null;
	}

	
	/**
	 * 生成订单，供用户进行支付
	 */
	@Override
	public synchronized int createOrder(ProductOrder productOrder, UserAddress addressInfo, List<ProductOrderItem> orderItemList) {
		if(productOrder==null){
			throw new GeekwayException(ErrorCode.WX_PRODUCT_ORDER_CREATE_ERROR);
		}
		Date currentTime = new Date();
		String tradeNo = OrderUtil.generateOrderSn(currentTime);
		//保存订单
		productOrder.setOutTradeNo(tradeNo);
		
		double productTotalFee = 0;
		int totalBuyAmount = 0;
		if(orderItemList!=null&&orderItemList.size()>0){
			for(ProductOrderItem orderItem:orderItemList){
				double itemTotalPrice = orderItem.getTotalFee();//单品总金额
				productTotalFee = productTotalFee + itemTotalPrice;
				totalBuyAmount = totalBuyAmount + orderItem.getAmount();
				orderItem.setOutTradeNo(tradeNo);
				productOrderItemService.save(orderItem);
			}
		}
		productOrder.setTotalFee(productTotalFee);
		//总数量
		
		double transportFee = deliveryTemplateService.calcDeliveryFee(0, productTotalFee, totalBuyAmount, "", addressInfo.getPostProvince(), addressInfo.getPostCity());
		//优惠券抵扣费用
		double voucherFee = 0;//voucher==null?0:voucher.getPrice();
		//折扣（用于后台改价）
		double discountFee = 0;
		
		productOrder.setTitle("");//TODO
		productOrder.setProductFee(productTotalFee);
		productOrder.setVoucherFee(voucherFee);//折扣费用
		productOrder.setDiscountFee(discountFee);//折扣费用
		productOrder.setTransportFee(transportFee);//运费
		double totalFee = productTotalFee - voucherFee + transportFee;
		productOrder.setTotalFee(totalFee);//总费用
		productOrder.setStatus(GeekwayEnum.ProductOrderStatusEnum.SUBMITED.getStatus());//预支付状态
		productOrder.setCreateTime(currentTime);
		//组装邮寄地址
		populatePostInfo(productOrder, addressInfo);
		
		int result = save(productOrder);
		
		//下单不扣减库存（支付成功后才扣减）
		
		//标记优惠码状态为正在使用
//		if(productOrder.getVoucherId()!=null&&productOrder.getVoucherId()>0){
//			result = productVoucherService.changeStatus(productOrder.getUserOpenId(), productOrder.getVoucherId(), GeekwayEnum.ProductVoucherStatusEnum.USED.getStatus());
//		}
		if(addressInfo!=null&&result>0){
			//保存用户邮寄地址信息
			addressInfo.setUserOpenId(productOrder.getUserOpenId());
			addressInfo.setCreateTime(currentTime);
			userAddressService.save(addressInfo);
		}
		return result;
	}
	
	
	
	/**
	 * 将用户地址信息填写到订单对象中
	 * @param productOrder
	 * @param addressInfo
	 */
	private void populatePostInfo(ProductOrder productOrder, UserAddress addressInfo) {
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

	public ProductOrderMapper getProductOrderMapper() {
		return productOrderMapper;
	}

	public void setProductOrderMapper(ProductOrderMapper productOrderMapper) {
		this.productOrderMapper = productOrderMapper;
	}

	public IProductOrderItemService getProductOrderItemService() {
		return productOrderItemService;
	}

	public void setProductOrderItemService(
			IProductOrderItemService productOrderItemService) {
		this.productOrderItemService = productOrderItemService;
	}

	public IProductVoucherService getProductVoucherService() {
		return productVoucherService;
	}

	public void setProductVoucherService(
			IProductVoucherService productVoucherService) {
		this.productVoucherService = productVoucherService;
	}

	public IUserAddressService getUserAddressService() {
		return userAddressService;
	}

	public void setUserAddressService(IUserAddressService userAddressService) {
		this.userAddressService = userAddressService;
	}

	public IDeliveryTemplateService getDeliveryTemplateService() {
		return deliveryTemplateService;
	}

	public void setDeliveryTemplateService(
			IDeliveryTemplateService deliveryTemplateService) {
		this.deliveryTemplateService = deliveryTemplateService;
	}
	
	
	
}
