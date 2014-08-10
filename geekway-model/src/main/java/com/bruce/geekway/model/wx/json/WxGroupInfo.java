package com.bruce.geekway.model.wx.json;

import java.io.Serializable;
import java.util.List;

/**
 * 分组信息
 * 
 * @author jianqing.cai@qq.com, https://github.com/caijianqing/weixinmp4java/
 */
public class WxGroupInfo implements Serializable {

	private static final long serialVersionUID = 1L;

	/** 分组信息 */
	public Group group = new Group();

	@Override
	public String toString() {
		return "Group [group=" + group + "]";
	}

	/**
	 * 分组信息
	 * 
	 * @author jianqing.cai@qq.com,
	 *         https://github.com/caijianqing/weixinmp4java/, 2014-2-24
	 *         下午4:59:02
	 */
	public static class Group implements Serializable {

		private static final long serialVersionUID = 1L;

		/** 分组id，由微信分配 */
		public Integer id;

		/** 分组名字，UTF8编码 */
		public String name;

		/** 分组内用户数量 */
		public Integer count;

		public Integer getId() {
			return id;
		}

		public void setId(Integer id) {
			this.id = id;
		}

		public String getName() {
			return name;
		}

		public void setName(String name) {
			this.name = name;
		}

		public Integer getCount() {
			return count;
		}

		public void setCount(Integer count) {
			this.count = count;
		}

		@Override
		public String toString() {
			return "Group [id=" + id + ", name=" + name + ", count=" + count + "]";
		}

	}

	/** 分组列表 */
	public class Groups implements Serializable {

		private static final long serialVersionUID = 1L;

		/** 消息类型 */
		public List<Group> groups;

		public List<Group> getGroups() {
			return groups;
		}

		public void setGroups(List<Group> groups) {
			this.groups = groups;
		}

		@Override
		public String toString() {
			return "Groups [groups=" + groups + "]";
		}

	}

	/** 用户与分组的关系信息 */
	public static class UserGroup implements Serializable {

		private static final long serialVersionUID = 1L;

		/** 用户的OpenID */
		public String openid;

		/** 用户所属的groupid */
		public Integer groupid;

		/** 移动到这个分组 */
		public Integer to_groupid;

		/** 返回国家地区语言版本，zh_CN 简体，zh_TW 繁体，en 英语 */
		public LANG lang;

		public String getOpenid() {
			return openid;
		}

		public void setOpenid(String openid) {
			this.openid = openid;
		}

		public Integer getGroupid() {
			return groupid;
		}

		public void setGroupid(Integer groupid) {
			this.groupid = groupid;
		}

		public Integer getTo_groupid() {
			return to_groupid;
		}

		public void setTo_groupid(Integer to_groupid) {
			this.to_groupid = to_groupid;
		}

		public LANG getLang() {
			return lang;
		}

		public void setLang(LANG lang) {
			this.lang = lang;
		}

		@Override
		public String toString() {
			return "UserGroup [openid=" + openid + ", groupid=" + groupid + ", to_groupid=" + to_groupid + ", lang=" + lang + "]";
		}

	}

	/** 语言版本 */
	public static enum LANG {
		zh_CN, zh_TW, en;
	}
}
