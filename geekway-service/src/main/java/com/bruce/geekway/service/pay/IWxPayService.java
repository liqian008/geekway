package com.bruce.geekway.service.pay;

import org.springframework.beans.factory.annotation.Autowired;

import com.bruce.geekway.model.WxPayAlarm;
import com.bruce.geekway.model.WxPayComplaint;
import com.bruce.geekway.model.WxPayOrderInfo;
import com.bruce.geekway.model.wx.pay.WxOrderInfo;
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
	public int receiverOrder(WxOrderInfo wxOrderInfo){
		
		//检查签名
		
		
		
		
		//保存订单，TODO 检查重复订单
		WxPayOrderInfo orderInfo = new WxPayOrderInfo();
		int result = wxPayOrderInfoService.save(orderInfo);
		
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
	 * 接收微信的维权消息
	 * @return
	 */
	public int receiverComplaint(WxPayComplaint wxComplaint){
		if(wxComplaint!=null){
			int result = wxPayComplaintService.save(wxComplaint);
			return result;
		}
		return 0;
	}
	
	
	/**
	 * 接收微信的告警消息
	 * @return 
	 */
	public int receiverAlarm(WxPayAlarm wxAlarm){
		if(wxAlarm!=null){
			int result = wxPayAlarmService.save(wxAlarm);
			return result;
		}
		return 0;
	}
	
	/**
	 * 发货
	 * @return
	 */
	public int deliver(){
		//提交微信
		int result = wxMpPayService.deliverNotify(null);
		if(result>0){
			//微信提交成功后，更改db中的状态
		}
		return result;
	}
	
	/**
	 * 向微信提交用户维权的处理结果
	 * @return
	 */
	public int dealComplaint(int userOpenId){
		//提交微信
		int result = wxMpPayService.dealComplaint();
		if(result>0){
			//微信提交成功后，更改db中的状态
		}
		return result;
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
	
	
	
}