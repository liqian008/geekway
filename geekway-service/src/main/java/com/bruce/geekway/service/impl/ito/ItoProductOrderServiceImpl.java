package com.bruce.geekway.service.impl.ito;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bruce.geekway.constants.ConstIto;
import com.bruce.geekway.dao.ito.IItoProductOrderDao;
import com.bruce.geekway.model.ItoProductOrder;
import com.bruce.geekway.model.ItoProductOrderCriteria;
import com.bruce.geekway.service.ito.IItoProductOrderService;

@Service
public class ItoProductOrderServiceImpl implements IItoProductOrderService{
	
	
	private static final SimpleDateFormat SDF = new SimpleDateFormat("yyyyMMddHHmmss");
	
	@Autowired
	private IItoProductOrderDao itoProductOrderDao;
	
	@Override
	public int save(ItoProductOrder t) {
		if(t!=null){//生成订单号
			t.setOrderSn(generateOrderSn());
			return itoProductOrderDao.save(t);
		}
		return 0;
	}

	@Override
	public int updateById(ItoProductOrder t) {
		return itoProductOrderDao.updateById(t);
	}

	@Override
	public int deleteById(Integer id) {
		return itoProductOrderDao.deleteById(id);
	}

	@Override
	public ItoProductOrder loadById(Integer id) {
		return itoProductOrderDao.loadById(id);
	}

	@Override
	public List<ItoProductOrder> queryAll() {
		return itoProductOrderDao.queryAll();
	}
	
	/**
	 * 查询来自支付宝的订单列表
	 * @return
	 */
	public List<ItoProductOrder> queryAlipayOrderList(){
		return itoProductOrderDao.queryOrderListByPayType(ConstIto.PAYTYPE_ALIPAY);
	}
	
	/**
	 * 查询来自线下支付的订单列表
	 * @return
	 */
	public List<ItoProductOrder> querySelfOrderList(){
		return itoProductOrderDao.queryOrderListByPayType(ConstIto.PAYTYPE_SELF);
	}
	
	
	@Override
	public ItoProductOrder loadByOrderSn(String orderSn) {
		return itoProductOrderDao.loadByOrderSn(orderSn);
	}
	
	@Override
	public ItoProductOrder loadByOrderSn(String orderSn, short payType) {
		return itoProductOrderDao.loadByOrderSn(orderSn, payType);
	}
	
	@Override
	public int changeOrderStatus(ItoProductOrder order) {
		return itoProductOrderDao.updateById(order);
	}
	
	
	@Override
	public String generateOrderSn() {
		String uuid = UUID.randomUUID().toString().replace("-", "");
		String orderTimeStr = SDF.format(new Date());
		return orderTimeStr +"_"+ uuid;
	}
	

	public IItoProductOrderDao getItoProductOrderDao() {
		return itoProductOrderDao;
	}

	public void setItoProductOrderDao(IItoProductOrderDao itoProductOrderDao) {
		this.itoProductOrderDao = itoProductOrderDao;
	}

	@Override
	public int updateByCriteria(ItoProductOrder t,
			ItoProductOrderCriteria criteria) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int deleteByCriteria(ItoProductOrderCriteria criteria) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public List<ItoProductOrder> queryAll(String orderByClause) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<ItoProductOrder> queryByCriteria(
			ItoProductOrderCriteria criteria) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public int countByCriteria(ItoProductOrderCriteria criteria) {
		// TODO Auto-generated method stub
		return 0;
	}

	
//	public static void main(String[] args) {
//		ItoProductOrderServiceImpl x = new ItoProductOrderServiceImpl();
//		System.out.println(x.generateOrderSn());
//	}
	
	
}