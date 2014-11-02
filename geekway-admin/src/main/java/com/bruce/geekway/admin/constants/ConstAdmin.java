package com.bruce.geekway.admin.constants;

import org.apache.commons.lang3.math.NumberUtils;

import com.bruce.geekway.utils.ConfigUtil;

public interface ConstAdmin {
	
	/*默认每页显示的条数*/
	public static final int PAGE_SIZE_DEFAULT = NumberUtils.toInt(ConfigUtil.getString("page_size_default"), 20);

}
