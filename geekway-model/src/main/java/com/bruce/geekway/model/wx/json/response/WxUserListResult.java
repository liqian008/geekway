package com.bruce.geekway.model.wx.json.response;

import java.util.List;

/**
 * 关注着列表
 * 
 * @author jianqing.cai@qq.com, https://github.com/caijianqing/weixinmp4java/
 */
public class WxUserListResult extends WxJsonResult {

	/** 关注该公众账号的总用户数 */
	public Integer total;

	/** 拉取的OPENID个数，最大值为10000 */
	public Integer count;

	/** 列表数据，OPENID的列表 */
	public OpenIdList data;

	/** 拉取列表的后一个用户的OPENID */
	public String next_openid;

	public Integer getTotal() {
		return total;
	}

	public void setTotal(Integer total) {
		this.total = total;
	}

	public Integer getCount() {
		return count;
	}

	public void setCount(Integer count) {
		this.count = count;
	}

	public OpenIdList getData() {
		return data;
	}

	public void setData(OpenIdList data) {
		this.data = data;
	}

	public String getNext_openid() {
		return next_openid;
	}

	public void setNext_openid(String next_openid) {
		this.next_openid = next_openid;
	}

	@Override
	public String toString() {
		return "Users [total=" + total + ", count=" + count + ", data=" + data
				+ ", next_openid=" + next_openid + "]";
	}

	/**
	 * OpenId集合
	 * 
	 * @author jianqing.cai@qq.com,
	 *         https://github.com/caijianqing/weixinmp4java/, 2014-2-24
	 *         下午5:14:30
	 */
	public static class OpenIdList {

		/** OPENID 集合 */
		private List<String> openid;

		public List<String> getOpenid() {
			return openid;
		}

		public void setOpenid(List<String> openid) {
			this.openid = openid;
		}

		@Override
		public String toString() {
			return "OpenIdList [openid=" + openid + "]";
		}
	}

}
