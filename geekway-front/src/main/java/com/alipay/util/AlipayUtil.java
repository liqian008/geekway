package com.alipay.util;

import java.util.HashMap;
import java.util.Map;

import com.alipay.config.AlipayConfig;
import com.alipay.model.json.BizData;
import com.bruce.geekway.model.ItoProductOrder;
import com.bruce.geekway.utils.JsonUtil;

public class AlipayUtil {

	public String buildAlipayQrCode(ItoProductOrder productOrder) {
		String bizData = buildBizData(productOrder);
		return buildAlipayQrCode(bizData);
	}

	public String buildAlipayQrCode(String bizData) {
		// 接口调用时间
		String timestamp = UtilDate.getDateFormatter();
		// 格式为：yyyy-MM-dd HH:mm:ss

		// 动作
		String method = "add";
		// 创建商品二维码
		// 业务类型
		String biz_type = "1";
		// 目前只支持1
		// 业务数据
		// String biz_data = new
		// String(request.getParameter("WIDbiz_data").getBytes("ISO-8859-1"),
		// "UTF-8");
		String biz_data = bizData; // 格式：JSON 大字符串，详见技术文档4.2.1章节

		// ////////////////////////////////////////////////////////////////////////////////

		// 把请求参数打包成数组
		Map<String, String> sParaTemp = new HashMap<String, String>();
		sParaTemp.put("service", "alipay.mobile.qrcode.manage");
		sParaTemp.put("partner", AlipayConfig.partner);
		sParaTemp.put("_input_charset", AlipayConfig.input_charset);
		sParaTemp.put("timestamp", timestamp);
		sParaTemp.put("method", method);
		sParaTemp.put("biz_type", biz_type);
		sParaTemp.put("biz_data", biz_data);

		// 建立请求
		// String sHtmlText = AlipaySubmit.buildRequest("", "", sParaTemp);

		return null;
	}

	/**
	 * 根据order信息构造bizData
	 * @param productOrder
	 * @return
	 */
	private String buildBizData(ItoProductOrder productOrder) {
		
		BizData bizData = new BizData();
		
		return JsonUtil.gson.toJson(bizData);
		
	}

}
