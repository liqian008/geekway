package com.bruce.geekway.service.pay;

import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;

import com.bruce.geekway.model.WxPayAlarm;
import com.bruce.geekway.model.WxPayComplaint;
import com.bruce.geekway.model.WxPayOrderInfo;
import com.bruce.geekway.model.wx.json.response.WxJsonResult;
import com.bruce.geekway.model.wx.pay.WxComplaintRequest;
import com.bruce.geekway.model.wx.pay.WxDeliverInfo;
import com.bruce.geekway.model.wx.pay.WxPayOrderRequest;
import com.bruce.geekway.model.wx.pay.WxPayAlarmRequest;
import com.bruce.geekway.service.pay.mp.WxMpPayService;

/**
 * 微信支付service
 * @author liqian
 *
 */
public class IWxPayService{
	
	@Autowired
	private IWxPayOrderInfoService wxPayOrderInfoService;
	@Autowired
	private IWxPayAlarmService wxPayAlarmService;
	@Autowired
	private IWxPayComplaintService wxPayComplaintService;
	@Autowired
	private WxMpPayService wxMpPayService;
	
	
	/**
	 * 支付成功，保存订单信息，修改库存
	 * @param wxOrderInfo 
	 * @return
	 */
	public int receiverOrder(WxPayOrderRequest wxOrderInfo){
		
		int result = 0;
		
		//检查签名是否合法
		boolean signValid = true;
		if(signValid){
			//保存订单，TODO 检查重复订单
			WxPayOrderInfo orderInfo = WxPayOrderRequest.convert2WxPayOrderInfo(wxOrderInfo);
			if(orderInfo!=null){
				result = wxPayOrderInfoService.save(orderInfo);
			}
		}
		return result;
	}
	
	
	/**
	 * 查询微信订单
	 * @return
	 */
	public int queryWxOrder(){
		return 0;
	}
	
	
	/**
	 * 接收微信的投诉消息
	 * @return
	 */
	public int receiverComplaint(WxComplaintRequest wxComplateRequest){
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
				
			}
		}
		return result;
	}
	
	
	/**
	 * 接收微信的告警消息
	 * @return 
	 */
	public int receiverAlarm(WxPayAlarmRequest alarmRequest){
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
	public int dealDeliver(WxDeliverInfo deliverInfo){
		//提交微信
		WxJsonResult result = wxMpPayService.deliverNotify(deliverInfo);
		if(result!=null){
			//微信提交成功后，更改db中的状态
		}
		return 0;
	}
	
	/**
	 * 向微信提交用户维权的处理结果
	 * @return
	 */
	public int dealComplaint(String openId, String feedbackId){
		//提交微信
		WxJsonResult wxResult = wxMpPayService.dealComplaint(openId, feedbackId);
		if(wxResult!=null){
			//微信提交成功后，更改db中的状态
			return wxPayComplaintService.markWait4Confirm(openId, feedbackId);
		}
		return 0;
	}
	
	
	/**
	 * 查询用户地址
	 * @return
	 */
	public int queryUserAddress(int userOpenId){
		return 0;
	}
	
	/**
	 * 查询订单状态
	 * @return
	 */
	public int queryOrderStatus(){
		return 0;
	}


	public IWxPayOrderInfoService getWxPayOrderInfoService() {
		return wxPayOrderInfoService;
	}


	public void setWxPayOrderInfoService(
			IWxPayOrderInfoService wxPayOrderInfoService) {
		this.wxPayOrderInfoService = wxPayOrderInfoService;
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