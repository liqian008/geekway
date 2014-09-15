package com.bruce.geekway.service.pay;

import com.bruce.foundation.service.IFoundationService;
import com.bruce.geekway.model.WxPayComplaint;
import com.bruce.geekway.model.WxPayComplaintCriteria;

public interface IWxPayComplaintService extends IFoundationService<WxPayComplaint, Integer, WxPayComplaintCriteria> {
	
	/**
	 * 标记为待用户确认
	 * @param feedbackId
	 * @return
	 */
	public int markWait4Confirm(String openId, String feedbackId);
	
	/**
	 * 标记为全部完成
	 * @param feedbackId
	 * @return
	 */
	public int markFinish(String openId, String feedbackId);
	
}