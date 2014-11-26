package com.bruce.geekway.service.pay;

import com.bruce.foundation.service.IFoundationPagingService;
import com.bruce.geekway.model.WxPayComplaint;
import com.bruce.geekway.model.WxPayComplaintCriteria;

public interface IWxPayComplaintService extends IFoundationPagingService<WxPayComplaint, Integer, WxPayComplaintCriteria> {
	
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
	
	
//	/*分页方式进行条件查询(用于后台管理)*/
//	public PagingResult<WxPayComplaint> pagingLatestComplaint(int pageNo, int pageSize, WxPayComplaintCriteria criteria);

}