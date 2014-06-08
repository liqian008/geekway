package com.alipay.model.json;

/**
 * 支付宝returnUrl的返回json
 * 
 * @author liqian
 * 
 */
public class AlipayReturnResult {

	// private String is_success;
	// private String error_code;
	private String out_trade_no;
	private AlipayReturnErrorCodeEnum errorCodeEnum;

	public AlipayReturnResult(AlipayReturnErrorCodeEnum errorCodeEnum, String out_trade_no) {
		// super();
		// if(errorCodeEnum==null){
		// is_success = "T";
		// this.out_trade_no = out_trade_no;
		// error_code = null;
		// }else{
		// is_success = "F";
		// this.out_trade_no = null;
		// error_code= errorCodeEnum.toString();
		// System.out.println(errorCodeEnum);
		// }
		this.errorCodeEnum = errorCodeEnum;
		this.out_trade_no = out_trade_no;
	}

	public String getOut_trade_no() {
		return out_trade_no;
	}

	public void setOut_trade_no(String out_trade_no) {
		this.out_trade_no = out_trade_no;
	}

	public AlipayReturnErrorCodeEnum getErrorCodeEnum() {
		return errorCodeEnum;
	}

	public void setErrorCodeEnum(AlipayReturnErrorCodeEnum errorCodeEnum) {
		this.errorCodeEnum = errorCodeEnum;
	}

	@Override
	public String toString() {
		StringBuilder sb = new StringBuilder();
		if(errorCodeEnum==null){
			sb.append("{is_success:\"T\",out_trade_no:\""+out_trade_no+"\"}");
		}else{
			sb.append("{is_success:\"F\",error_code:\""+errorCodeEnum.toString()+"\"}");
		}
		return sb.toString();
	}

	// public static void main(String[] args) {
	// AlipayReturnResult result = new
	// AlipayReturnResult(AlipayReturnErrorCodeEnum.CREATE_TRADE_FAILURE, "");
	// }
}
