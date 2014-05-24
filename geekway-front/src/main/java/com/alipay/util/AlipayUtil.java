package com.alipay.util;

import java.util.HashMap;
import java.util.Map;

import com.alipay.config.AlipayConfig;
import com.alipay.model.json.BizData;
import com.alipay.model.json.GoodsInfo;
import com.bruce.geekway.model.ItoProductOrder;
import com.bruce.geekway.model.ItoSku;
import com.bruce.geekway.utils.JsonUtil;
import com.bruce.geekway.utils.ItoOrderUtil;

public class AlipayUtil {

	public String buildAlipayQrCode(ItoProductOrder itoProductOrder, ItoSku itoSku) {
		String bizData = buildBizData(itoProductOrder, itoSku);
		return buildAlipayQrCode(bizData);
	}
	
	/**
	 * 提交订单信息，换取支付宝的支付二维码
	 * @param bizData
	 * @return
	 */
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
	 * @param itoProductOrder
	 * @return
	 */
	private String buildBizData(ItoProductOrder itoProductOrder, ItoSku itoSku) {
		
		BizData bizData = new BizData();
		bizData.setTrade_type("2");//支付类型，2为担保支付
		bizData.setNeed_address("T");
		bizData.setMemo("");
		//支付完毕后的通知url
		bizData.setReturn_url("");//商户生成二维码且用户使用了二 维码,创建了一笔交易,支付宝 通过该路径通知商户系统下订
		bizData.setNotify_url(ItoOrderUtil.getAlipayNotifyUrl(itoProductOrder.getOrderSn()));
		//构造支付宝所需的商品信息
		GoodsInfo goodsInfo = new GoodsInfo();
		bizData.setGoods_info(goodsInfo);
		
//		goodsInfo.setPrice(itoSku.getPrice());
		goodsInfo.setId(String.valueOf(itoSku.getId()));
		goodsInfo.setName(itoSku.getName());
		goodsInfo.setPrice(String.valueOf(itoSku.getPrice()));
		goodsInfo.setSku_title(itoSku.getPropertiesName());

		//单产品情况下，无需构造goodsInfo的sku信息
		
		return JsonUtil.gson.toJson(bizData);
	}

}
