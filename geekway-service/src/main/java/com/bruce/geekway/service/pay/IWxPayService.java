package com.bruce.geekway.service.pay;

import java.util.Date;
import java.util.List;
import java.util.SortedMap;
import java.util.TreeMap;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.bruce.foundation.util.DateUtil;
import com.bruce.geekway.constants.ConstWeixin;
import com.bruce.geekway.model.WxPayAlarm;
import com.bruce.geekway.model.WxPayComplaint;
import com.bruce.geekway.model.WxPayNotifyOrder;
import com.bruce.geekway.model.WxProductOrder;
import com.bruce.geekway.model.WxProductOrderItem;
import com.bruce.geekway.model.exception.ErrorCode;
import com.bruce.geekway.model.exception.GeekwayException;
import com.bruce.geekway.model.wx.json.response.WxJsonResult;
import com.bruce.geekway.model.wx.pay.WxComplaintNotify;
import com.bruce.geekway.model.wx.pay.WxDeliverInfo;
import com.bruce.geekway.model.wx.pay.WxOrderQueryRequest;
import com.bruce.geekway.model.wx.pay.WxPayAlarmNotify;
import com.bruce.geekway.model.wx.pay.WxPayNotifyOrderRequest;
import com.bruce.geekway.service.pay.mp.WxMpPayService;
import com.bruce.geekway.service.product.IWxProductOrderItemService;
import com.bruce.geekway.service.product.IWxProductOrderService;
import com.bruce.geekway.service.product.IWxProductSkuService;
import com.bruce.geekway.utils.WxAuthUtil;

/**
 * 微信支付service
 * @author liqian
 *
 */
public class IWxPayService{
	
	@Autowired
	private IWxPayNotifyOrderService wxPayNotifyOrderService;
	@Autowired
	private IWxPayAlarmService wxPayAlarmService;
	@Autowired
	private IWxPayComplaintService wxPayComplaintService;
	@Autowired
	private WxMpPayService wxMpPayService;
	@Autowired
	private IWxProductOrderService wxProductOrderService;
	@Autowired
	private IWxProductOrderItemService wxProductOrderItemService;
	@Autowired
	private IWxProductSkuService wxProductSkuService;
	
	
	/**
	 * 支付成功，记录微信订单流水表，且更新系统订单状态+库存（事务操作）
	 * @param wxNotifyOrder 
	 * @return
	 */
	@Transactional(propagation=Propagation.REQUIRES_NEW)
	public int receiveWxOrder(WxPayNotifyOrderRequest wxOrderRequest){
		if(wxOrderRequest==null){
			throw new GeekwayException(ErrorCode.SYSTEM_ERROR);
		}
		
		int result = 0;
		//检查签名是否合法
		boolean signValid = true;
		if(signValid){
			//重复订单的检查
			
			WxPayNotifyOrder wxNotifyOrder = WxPayNotifyOrderRequest.convert2WxPayNotifyOrder(wxOrderRequest);
			if(wxNotifyOrder!=null){//处理微信支付成功的业务
				
				//保存微信订单流水表
				result = wxPayNotifyOrderService.save(wxNotifyOrder);
				//加载订单数据
				String outTradeNo = wxNotifyOrder.getOutTradeNo();
				WxProductOrder productOrder = wxProductOrderService.loadByTradeNo(outTradeNo);
				if(productOrder!=null&&productOrder.getId()!=null){//有效的订单信息
					List<WxProductOrderItem> orderItemList = wxProductOrderItemService.queryByTradeNo(outTradeNo);
					//遍历单条订单，扣减sku商品的库存数
					if(orderItemList!=null&&orderItemList.size()>0){
						for(WxProductOrderItem orderItem: orderItemList){
							long productSkuId = orderItem.getProductSkuId();
							int amount = orderItem.getAmount();
							//执行扣减
							result = wxProductSkuService.reduceStock(productSkuId, amount);
						}
					}
					// 更新订单表中的订单状态为支付完毕
					result = wxProductOrderService.changeOrderStatus(outTradeNo, IWxProductOrderService.StatusEnum.PAYED.getStatus());
				}
			}
		}
		return result;
	}
	
	
	/**
	 * 查询微信订单
	 * @param outTradeNo
	 * @return
	 */
	public int queryWxOrder(String outTradeNo){
		
		SortedMap<String, String> packageMap = new TreeMap<String, String>();
		packageMap.put("out_trade_no", outTradeNo);
		packageMap.put("partner", ConstWeixin.WX_PAY_PARTERN_ID);
		
		String packageValue = WxAuthUtil.packageSign(packageMap);
		String timestamp = String.valueOf(DateUtil.getUnixTime(new Date()));
		
		WxOrderQueryRequest orderRequest= new WxOrderQueryRequest();
		orderRequest.setAppid(ConstWeixin.WX_APP_ID);
		orderRequest.setTimestamp(timestamp);
		orderRequest.setPackageValue(packageValue);
		orderRequest.setSign_method("SHA1");
		
		SortedMap<String, String> paySignMap = new TreeMap<String, String>();
		paySignMap.put("appid", outTradeNo);
		paySignMap.put("package", packageValue);
		paySignMap.put("timestamp", timestamp);
		//使用paySign签名生成appSignature
		String appSignature = WxAuthUtil.paySign(paySignMap);
		
		orderRequest.setApp_signature(appSignature);
		
		//提交微信查询
		WxJsonResult result = wxMpPayService.queryOrder(orderRequest);
		if(result!=null){
			//微信提交成功，解析返回结果
		}
		return 0;
		
		
	}
	
	
	/**
	 * 接收微信的投诉消息
	 * @return
	 */
	public int receiverWxComplaint(WxComplaintNotify wxComplateRequest){
		int result = 0;
		if(wxComplateRequest!=null){
			String msgType = wxComplateRequest.getMsgType();
			String feedbackId = wxComplateRequest.getFeedbackId();
			String openId = wxComplateRequest.getOpenId();
			
			boolean isFirst = true;//判断是否是新投诉，还是投诉后续的反馈，默认为新投诉
			if(!"request".equalsIgnoreCase(msgType)){
				isFirst = false;
			}
			//新增投诉
			WxPayComplaint wxComplaint = new WxPayComplaint();
			wxComplaint.setIsFirst(isFirst?(short)1:(short)0);//是否为投诉
			wxComplaint.setFeedbackId(wxComplateRequest.getFeedbackId());
			wxComplaint.setOpenId(wxComplateRequest.getOpenId());
			wxComplaint.setExtInfo(wxComplateRequest.getExtInfo());
			wxComplaint.setMsgType(wxComplateRequest.getMsgType());
			wxComplaint.setReason(wxComplateRequest.getReason());
			wxComplaint.setSolution(wxComplateRequest.getSolution());
			wxComplaint.setTransId(wxComplateRequest.getTransId());
			//TODO timestamp
			wxComplaint.setExtInfo(wxComplateRequest.getExtInfo());
			wxComplaint.setCreateTime(new Date());
			result = wxPayComplaintService.save(wxComplaint);
			
			if("confirm".equals(msgType)){//用户同意消除投诉
				//更新投诉状态为已处理完毕
				wxPayComplaintService.markFinish(openId, feedbackId);
			}else if("reject".equals(msgType)){//用户不同意消除投诉
				//TODO 继续沟通
			}
		}
		return result;
	}
	
	
	/**
	 * 接收微信的告警消息
	 * @return 
	 */
	public int receiverWxAlarm(WxPayAlarmNotify alarmRequest){
		if(alarmRequest!=null){
			WxPayAlarm wxPayAlarm = new WxPayAlarm();
			
			wxPayAlarm.setErrorType(alarmRequest.getErrorType());
			wxPayAlarm.setDescription(alarmRequest.getDescription());
			wxPayAlarm.setAlarmContent(alarmRequest.getAlarmContent());
			//TODO timestamp
			wxPayAlarm.setCreateTime(new Date());
			
			int result = wxPayAlarmService.save(wxPayAlarm);
			return result;
		}
		return 0;
	}
	
	/**
	 * 发货
	 * @return
	 */
	public int dealWxDeliver(WxDeliverInfo deliverInfo){
		if(deliverInfo!=null){
			//提交微信处理
			WxJsonResult jsonResult = wxMpPayService.deliverNotify(deliverInfo);
			if(jsonResult!=null){
				//微信提交成功后，更改db中的状态
				int result = wxProductOrderService.changeOrderStatus(deliverInfo.out_trade_no, IWxProductOrderService.StatusEnum.DELIVERED.getStatus());
				return result;
			}
		}
		return 0;
	}
	
	/**
	 * 向微信提交用户维权的处理结果
	 * @return
	 */
	public int dealWxComplaint(String openId, String feedbackId){
		//提交微信
		WxJsonResult wxResult = wxMpPayService.dealComplaint(openId, feedbackId);
		if(wxResult!=null){
			//微信提交成功后，更改db中的状态
			return wxPayComplaintService.markWait4Confirm(openId, feedbackId);
		}
		return 0;
	}
	
	
	/**
	 * 查询订单状态
	 * @return
	 */
	public int queryWxOrderStatus(){
		return 0;
	}


	public IWxPayNotifyOrderService getWxPayNotifyOrderService() {
		return wxPayNotifyOrderService;
	}


	public void setWxPayNotifyOrderService(IWxPayNotifyOrderService wxPayNotifyOrderService) {
		this.wxPayNotifyOrderService = wxPayNotifyOrderService;
	}


	public IWxPayAlarmService getWxPayAlarmService() {
		return wxPayAlarmService;
	}


	public void setWxPayAlarmService(IWxPayAlarmService wxPayAlarmService) {
		this.wxPayAlarmService = wxPayAlarmService;
	}


	public IWxPayComplaintService getWxPayComplaintService() {
		return wxPayComplaintService;
	}


	public void setWxPayComplaintService(
			IWxPayComplaintService wxPayComplaintService) {
		this.wxPayComplaintService = wxPayComplaintService;
	}


	public WxMpPayService getWxMpPayService() {
		return wxMpPayService;
	}


	public void setWxMpPayService(WxMpPayService wxMpPayService) {
		this.wxMpPayService = wxMpPayService;
	}
	
	
	
	
	
}
