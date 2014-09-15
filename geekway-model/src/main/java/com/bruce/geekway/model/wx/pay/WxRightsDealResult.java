package com.bruce.geekway.model.wx.pay;

/**
 * 用户维权的处理结果对象(与用户沟通过后，才能构造改对象并发回至微信)
 * @author liqian
 *
 */
public class WxRightsDealResult {

	public String openid;
	public String trans_id;
	public String out_trans_id;

}
