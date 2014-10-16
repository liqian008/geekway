package com.bruce.geekway.model.wx.pay;

import java.util.List;

/**
 * 邮费模板对象
 * 
 * @author liqian
 * 
 */
public class WxDeliveryTemplate {
	/* 模板id */
	private int id;
	/* 模板名称 */
	private String name;
	/* 支付方式(0-买家承担运费, 1-卖家承担运费) */
	private int assumer;
	/* 计费单位(0-按件计费, 1-按重量计费, 2-按体积计费，目前只支持按件计费，默认为0) */
	private int valuation;
	/* 邮费配置 */
	private List<TopFee> topFeeList;

	public WxDeliveryTemplate(int id, String name, int assumer, int valuation,
			List<TopFee> topFeeList) {
		super();
		this.id = id;
		this.name = name;
		this.assumer = assumer;
		this.valuation = valuation;
		this.topFeeList = topFeeList;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getAssumer() {
		return assumer;
	}

	public void setAssumer(int assumer) {
		this.assumer = assumer;
	}

	public int getValuation() {
		return valuation;
	}

	public void setValuation(int valuation) {
		this.valuation = valuation;
	}

	public List<TopFee> getTopFeeList() {
		return topFeeList;
	}

	public void setTopFeeList(List<TopFee> topFeeList) {
		this.topFeeList = topFeeList;
	}

	public static class TopFee {
		private int deliveryType;
		private String deliveryName;
		private NormalFee normalFee;
		private List<CustomFee> customFeeList;

		public TopFee(int deliveryType, String deliveryName,
				NormalFee normalFee, List<CustomFee> customFeeList) {
			super();
			this.deliveryType = deliveryType;
			this.deliveryName = deliveryName;
			this.normalFee = normalFee;
			this.customFeeList = customFeeList;
		}

		public int getDeliveryType() {
			return deliveryType;
		}

		public void setDeliveryType(int deliveryType) {
			this.deliveryType = deliveryType;
		}

		public String getDeliveryName() {
			return deliveryName;
		}

		public void setDeliveryName(String deliveryName) {
			this.deliveryName = deliveryName;
		}

		public NormalFee getNormalFee() {
			return normalFee;
		}

		public void setNormalFee(NormalFee normalFee) {
			this.normalFee = normalFee;
		}

		public List<CustomFee> getCustomFeeList() {
			return customFeeList;
		}

		public void setCustomFeeList(List<CustomFee> customFeeList) {
			this.customFeeList = customFeeList;
		}

	}

	public static class NormalFee {
		private int startStandards;
		private double startFees;
		private int addStandards;
		private double addFees;

		public NormalFee(int startStandards, double startFees, int addStandards,
				double addFees) {
			super();
			this.startStandards = startStandards;
			this.startFees = startFees;
			this.addStandards = addStandards;
			this.addFees = addFees;
		}

		public double getStartStandards() {
			return startStandards;
		}

		public void setStartStandards(int startStandards) {
			this.startStandards = startStandards;
		}

		public double getStartFees() {
			return startFees;
		}

		public void setStartFees(int startFees) {
			this.startFees = startFees;
		}

		public int getAddStandards() {
			return addStandards;
		}

		public void setAddStandards(int addStandards) {
			this.addStandards = addStandards;
		}

		public double getAddFees() {
			return addFees;
		}

		public void setAddFees(double addFees) {
			this.addFees = addFees;
		}
	}

	public static class CustomFee extends NormalFee {
		private String destCountry;
		private String destProvince;
		private String destCity;

		public CustomFee(int startStandards, double startFees, int addStandards,
				double addFees, String destCountry, String destProvince,
				String destCity) {
			super(startStandards, startFees, addStandards, addFees);
			this.destCountry = destCountry;
			this.destProvince = destProvince;
			this.destCity = destCity;
		}

		public String getDestCountry() {
			return destCountry;
		}

		public void setDestCountry(String destCountry) {
			this.destCountry = destCountry;
		}

		public String getDestProvince() {
			return destProvince;
		}

		public void setDestProvince(String destProvince) {
			this.destProvince = destProvince;
		}

		public String getDestCity() {
			return destCity;
		}

		public void setDestCity(String destCity) {
			this.destCity = destCity;
		}
	}

}
