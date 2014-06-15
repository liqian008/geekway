package com.bruce.geekway.controller.klh;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

import org.apache.commons.lang3.StringUtils;
import org.dom4j.DocumentException;
import org.dom4j.DocumentHelper;
import org.dom4j.Element;

import com.bruce.geekway.model.klh.KlhEdbOrder;
import com.bruce.geekway.model.klh.KlhEdbOrderItem;
import com.edb.util.Util;


public class EdbApiUtil {
	
	/**
	 * 可乐惠在易店宝的配置
	 */
	private static final String URL = "http://vip98.edb05.com/rest/index.aspx";
	private static final String APPKEY = "50d7e716";
	private static final String SECRET = "0cf93fd75cb94e00888bd9c5b0458873";
	private static final String TOKEN = "1ce5be0f9b10480db2ec1758fb3169e6";
	private static final String DBHOST = "edb_a73409";

	private static final String FORMAT = "XML";

	/**
	 * 
	 * @param method
	 * @param field
	 * @param dataMap
	 * @return
	 */
	private static String invokeEdb(String method, String field, TreeMap<String, String> dataMap){
		TreeMap<String, String> apiParamsMap = new TreeMap<String, String>();
		apiParamsMap.put("dbhost", DBHOST);
		apiParamsMap.put("format", FORMAT);
		apiParamsMap.put("method", method);// "edbTradeGet"
		apiParamsMap.put("slencry", "0");
		apiParamsMap.put("ip", "117.79.148.228");
		apiParamsMap.put("appkey", APPKEY);
		apiParamsMap.put("v", "2.0");
		
		if(dataMap!=null&&dataMap.size()>0){
			apiParamsMap.putAll(dataMap);
		}
		
		
//		apiParamsMap.put("fields",
//				"is_success,response_Code,response_Msg,field");
		apiParamsMap.put("fields",field);
		String timestamp = new SimpleDateFormat("yyyyMMddHHmm")
				.format(new Date());
		apiParamsMap.put("timestamp", timestamp);
		// apiParamsMap.put("timestamp", "201406101340");

		String sign = getSign(apiParamsMap);
		String result = getResult(apiParamsMap, sign);
		return result;
	}
	
	
	/**
	 * 订单列表
	 * @return
	 */
	public static List<KlhEdbOrder> edbTradeGet() {
		List<KlhEdbOrder> edbOrderList = new ArrayList<KlhEdbOrder>();
		TreeMap<String, String> dataMap = new TreeMap<String, String>();
		dataMap.put("date_type", "建档日期");
		dataMap.put("begin_time", "2014-06-01");
		dataMap.put("end_time", "2014-06-13");
		dataMap.put("page_size", "20");
		
		String methodName = "edbTradeGet";
//		String fields =  "storage_id,tid,transaction_id,customer_id,distributor_id,shop_name,out_tid,out_pay_tid,voucher_id,shopid,serial_num,order_channel,order_from,buyer_id,buyer_name,type,status,abnormal_status,merge_status,receiver_name,receiver_mobile,phone,province,city,district,address,post,email,is_bill,invoice_name,invoice_situation,invoice_title,invoice_type,invoice_content,pro_totalfee,order_totalfee,reference_price_paid,invoice_fee,cod_fee,other_fee,refund_totalfee,discount_fee,discount,channel_disfee,merchant_disfee,order_disfee,commission_fee,is_cod,point_pay,cost_point,point,superior_point,royalty_fee,external_point,express_no,express,express_coding,online_express,sending_type,real_income_freight,real_pay_freight,gross_weight,gross_weight_freight,net_weight_freight,freight_explain,total_weight,tid_net_weight,tid_time,pay_time,get_time,order_creater,business_man,payment_received_operator,payment_received_time,review_orders_operator,review_orders_time,finance_review_operator,finance_review_time,advance_printer,printer,print_time,is_print,adv_distributer,adv_distribut_time,distributer,distribut_time,is_inspection,inspecter,inspect_time,cancel_operator,cancel_time,revoke_cancel_er,revoke_cancel_time,packager,pack_time,weigh_operator,weigh_time,book_delivery_time,delivery_operator,delivery_time,locker,lock_time,book_file_time,file_operator,file_time,finish_time,modity_time,is_promotion,promotion_plan,out_promotion_detail,good_receive_time,receive_time,verificaty_time,enable_inte_sto_time,enable_inte_delivery_time,alipay_id,alipay_status,pay_mothed,pay_status,platform_status,rate,currency,delivery_status,buyer_message,service_remarks,inner_lable,distributor_mark,system_remarks,other_remarks,message,message_time,is_stock,related_orders,related_orders_type,import_mark,delivery_name,is_new_customer,distributor_level,cod_service_fee,express_col_fee,product_num,sku,item_num,single_num,flag_color,is_flag,taobao_delivery_order_status,taobao_delivery_status,taobao_delivery_method,order_process_time,is_break,breaker,break_time,break_explain,plat_send_status,plat_type,is_adv_sale,provinc_code,city_code,area_code,express_code,last_returned_time,last_refund_time,deliver_centre,deliver_station,is_pre_delivery_notice,jd_delivery_time,Sorting_code,cod_settlement_vouchernumber,total_num,child_storage_id,child_tid,child_pro_detail_code,child_pro_name,child_specification,child_barcode,child_combine_barcode,child_iscancel,child_isscheduled,child_stock_situation,child_isbook_pro,child_iscombination,child_isgifts,child_gift_num,child_book_storage,child_pro_num,child_send_num,child_refund_num,child_refund_renum,child_inspection_num,child_timeinventory,child_cost_price,child_sell_price,child_average_price,child_original_price,child_sys_price,child_ferght,child_item_discountfee,child_inspection_time,child_weight,child_shopid,child_out_tid,child_out_proid,child_out_prosku,child_proexplain,child_buyer_memo,child_seller_remark,child_distributer,child_distribut_time,child_second_barcode,child_product_no,child_brand_number,child_brand_name,child_book_inventory,child_product_specification,child_discount_amount,child_credit_amount,child_MD5_encryption";
		String fields =  "storage_id,tid,transaction_id,customer_id,distributor_id,shop_name,out_tid,out_pay_tid,voucher_id,shopid,serial_num,order_channel,order_from,buyer_id,buyer_name,type,status,abnormal_status,merge_status,receiver_name,receiver_mobile,phone,province,city,district,address,post,email,is_bill,invoice_name,invoice_situation,invoice_title,invoice_type,invoice_content,pro_totalfee,order_totalfee,reference_price_paid,invoice_fee,cod_fee,other_fee,refund_totalfee,discount_fee,discount,channel_disfee,merchant_disfee,order_disfee,commission_fee,is_cod,point_pay,cost_point,point,superior_point,royalty_fee,external_point,express_no,express,express_coding,online_express,sending_type,real_income_freight,real_pay_freight,gross_weight,gross_weight_freight,net_weight_freight,freight_explain,total_weight,tid_net_weight,tid_time,pay_time,get_time,order_creater,business_man,payment_received_operator,payment_received_time,review_orders_operator,review_orders_time,finance_review_operator,finance_review_time,advance_printer,printer,print_time,is_print,adv_distributer,adv_distribut_time,distributer,distribut_time,is_inspection,inspecter,inspect_time,cancel_operator,cancel_time,revoke_cancel_er,revoke_cancel_time,packager,pack_time,weigh_operator,weigh_time,book_delivery_time,delivery_operator,delivery_time,locker,lock_time,book_file_time,file_operator,file_time,finish_time,modity_time,is_promotion,promotion_plan,out_promotion_detail,good_receive_time,receive_time,verificaty_time,enable_inte_sto_time,enable_inte_delivery_time,alipay_id,alipay_status,pay_mothed,pay_status,platform_status,rate,currency,delivery_status,buyer_message,service_remarks,inner_lable,distributor_mark,system_remarks,other_remarks,message,message_time,is_stock,related_orders,related_orders_type,import_mark,delivery_name,is_new_customer,distributor_level,cod_service_fee,express_col_fee,product_num,sku,item_num,single_num,flag_color,is_flag,taobao_delivery_order_status,taobao_delivery_status,taobao_delivery_method,order_process_time,is_break,breaker,break_time,break_explain,plat_send_status,plat_type,is_adv_sale,provinc_code,city_code,area_code,express_code,last_returned_time,last_refund_time,deliver_centre,deliver_station,is_pre_delivery_notice,jd_delivery_time,Sorting_code,cod_settlement_vouchernumber,total_num,child_storage_id,child_tid,child_pro_detail_code,child_pro_name,child_specification,child_barcode,child_combine_barcode,child_iscancel,child_isscheduled,child_stock_situation,child_isbook_pro,child_iscombination,child_isgifts,child_gift_num,child_book_storage,child_pro_num,child_send_num,child_refund_num,child_refund_renum,child_inspection_num,child_timeinventory,child_cost_price,child_sell_price,child_average_price,child_original_price,child_sys_price,child_ferght,child_item_discountfee,child_inspection_time,child_weight,child_shopid,child_out_tid,child_out_proid,child_out_prosku,child_proexplain,child_buyer_memo,child_seller_remark,child_distributer,child_distribut_time,child_second_barcode,child_product_no,child_brand_number,child_brand_name,child_book_inventory,child_product_specification,child_discount_amount,child_credit_amount,child_MD5_encryption";
		
		String result =  invokeEdb(methodName, fields, dataMap);
		System.out.println(result);
		if(!StringUtils.isBlank(result)){
			try {
				Element rootElement = DocumentHelper.parseText(result).getRootElement();
				List<Element> rowsList = rootElement.elements("Rows");
				if(rowsList!=null&&rowsList.size()>0){
					for(Element element: rowsList){
//						System.out.println("receiver_mobile: "+element.elementText("receiver_mobile"));
						String mobile = element.elementText("receiver_mobile");
						if(!StringUtils.isBlank(mobile)){//手机号不为空情况下
							String platType = element.elementText("plat_type");
							String phone = element.elementText("phone");
							String shopName = element.elementText("shop_name");
							String orderChannel = element.elementText("order_channel");
							String express = element.elementText("express");
							String expressNo = element.elementText("express_no");
							String expressCoding = element.elementText("express_coding");
							String receiverName = element.elementText("receiver_name");
							String referencePricePaid = element.elementText("reference_price_paid");
							String tidTime = element.elementText("tid_time");
							String payStatus = element.elementText("pay_status");
							String deliveryStatus = element.elementText("delivery_status");
							String type = element.elementText("type");
							String status = element.elementText("status");
							
							
							KlhEdbOrder edbOrder = new KlhEdbOrder();
							edbOrder.setPlatType(platType);
							edbOrder.setPhone(phone);
							edbOrder.setShopName(shopName);
							edbOrder.setOrderChannel(orderChannel);
							edbOrder.setReceiverName(receiverName);
							edbOrder.setExpress(express);
							edbOrder.setExpressNo(expressNo);
							edbOrder.setExpressCoding(expressCoding);
							edbOrder.setReferencePricePaid(referencePricePaid);
							edbOrder.setTidTime(tidTime);
							edbOrder.setPayStatus(payStatus);
							edbOrder.setDeliveryStatus(deliveryStatus);
							edbOrder.setType(type);
							edbOrder.setStatus(status);
							
							//商品清单
							
							List<Element> itemList = element.element("tid_item").elements("Item");
							if(itemList!=null&&itemList.size()>0){
								List<KlhEdbOrderItem> edbOrderItemList = new ArrayList<KlhEdbOrderItem>();
								for(Element itemElement: itemList){
									KlhEdbOrderItem orderItem = new KlhEdbOrderItem();
									orderItem.setProName(itemElement.elementText("pro_name"));
									orderItem.setSellPrice(itemElement.elementText("sell_price"));
									edbOrderItemList.add(orderItem);
								}
								edbOrder.setEdbOrderItemList(edbOrderItemList);
							}
							edbOrderList.add(edbOrder);
						}
					}
				}
			} catch (DocumentException e) {
				e.printStackTrace();
			}
		}
		return edbOrderList;
	}

	private static String getResult(TreeMap<String, String> treeMap, String sign) {
		StringBuilder temp = new StringBuilder();
		for (Iterator<Map.Entry<String, String>> it = treeMap.entrySet()
				.iterator(); it.hasNext();) {
			Map.Entry<String, String> e = it.next();
			temp.append("&").append(e.getKey()).append("=")
					.append(e.getValue());
		}
		temp.append("&sign").append("=").append(sign);
		System.out.println("请求的url：" + URL + "?" + temp.substring(1));
		return Util.getResult(URL, temp.substring(1));
	}

	private static String getSign(TreeMap<String, String> treeMap) {
		TreeMap<String, String> treeMap1 = new TreeMap<String, String>();
		treeMap1.putAll(treeMap);
		treeMap1.put("appscret", SECRET);
		treeMap1.put("token", TOKEN);
		return Util.md5Signature(treeMap1, APPKEY);
	}
	
	
	
	public static void main(String[] args) {
		edbTradeGet();
//		System.out.println(edbTradeGet());
	}
}
