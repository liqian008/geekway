package com.bruce.geekway.model.enumeration;


public class GeekwayEnum {
	
	public static enum ProductOrderStatusEnum{
		//可能还需要其他流程，如维权，退款等
		SUBMITED((short)10, "待支付"), PAYED((short)20, "已支付"), WAITING_DELIVER((short)30, "待发货"), DELIVERED((short)40, "已发货"), COMPLETED((short)100, "完成"), UNKNOWN((short)0, "关闭");
		
		private short status;
		private String name;
		ProductOrderStatusEnum(short status, String name){
			this.status = status;
			this.name = name;
		}

		public ProductOrderStatusEnum valueOf(short status){
			ProductOrderStatusEnum[] statusArray  = ProductOrderStatusEnum.values();
			for(ProductOrderStatusEnum orderStatus : statusArray){
				if(status == orderStatus.status){
					return orderStatus;
				}
			}
			return null;
		}
		
		public short getStatus() {
			return status;
		}
		
		public String getName() {
			return name;
		}
		
	}
	
	
	public static enum ProductVoucherStatusEnum{
		//可能还需要其他流程，如维权，退款等
		UNAVAILABLE((short)0, "不可用"), AVAILABLE((short)1, "可用"), USED((short)2, "已使用"),  FORBIDDEN((short)3, "封禁");
		
		private short status;
		private String name;
		ProductVoucherStatusEnum(short status, String name){
			this.status = status;
			this.name = name;
		}

		public ProductVoucherStatusEnum valueOf(short status){
			ProductVoucherStatusEnum[] statusArray  = ProductVoucherStatusEnum.values();
			for(ProductVoucherStatusEnum statusEnum : statusArray){
				if(status == statusEnum.status){
					return statusEnum;
				}
			}
			return null;
		}
		
		public short getStatus() {
			return status;
		}
		
		public String getName() {
			return name;
		}
	}
	
}
