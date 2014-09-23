package com.bruce.geekway.model;

import java.util.Date;

public class WxPayNotifyOrder {
    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column wx_pay_notify_order.id
     *
     * @mbggenerated Tue Sep 23 18:09:55 CST 2014
     */
    private Integer id;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column wx_pay_notify_order.open_id
     *
     * @mbggenerated Tue Sep 23 18:09:55 CST 2014
     */
    private String openId;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column wx_pay_notify_order.is_subsuribed
     *
     * @mbggenerated Tue Sep 23 18:09:55 CST 2014
     */
    private Short isSubsuribed;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column wx_pay_notify_order.bank_type
     *
     * @mbggenerated Tue Sep 23 18:09:55 CST 2014
     */
    private String bankType;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column wx_pay_notify_order.bank_billno
     *
     * @mbggenerated Tue Sep 23 18:09:55 CST 2014
     */
    private String bankBillno;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column wx_pay_notify_order.fee_type
     *
     * @mbggenerated Tue Sep 23 18:09:55 CST 2014
     */
    private Integer feeType;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column wx_pay_notify_order.notify_id
     *
     * @mbggenerated Tue Sep 23 18:09:55 CST 2014
     */
    private String notifyId;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column wx_pay_notify_order.transaction_id
     *
     * @mbggenerated Tue Sep 23 18:09:55 CST 2014
     */
    private String transactionId;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column wx_pay_notify_order.out_trade_no
     *
     * @mbggenerated Tue Sep 23 18:09:55 CST 2014
     */
    private String outTradeNo;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column wx_pay_notify_order.attach
     *
     * @mbggenerated Tue Sep 23 18:09:55 CST 2014
     */
    private String attach;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column wx_pay_notify_order.time_end
     *
     * @mbggenerated Tue Sep 23 18:09:55 CST 2014
     */
    private Date timeEnd;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column wx_pay_notify_order.transport_fee
     *
     * @mbggenerated Tue Sep 23 18:09:55 CST 2014
     */
    private Integer transportFee;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column wx_pay_notify_order.product_fee
     *
     * @mbggenerated Tue Sep 23 18:09:55 CST 2014
     */
    private Integer productFee;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column wx_pay_notify_order.discount
     *
     * @mbggenerated Tue Sep 23 18:09:55 CST 2014
     */
    private Integer discount;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column wx_pay_notify_order.total_fee
     *
     * @mbggenerated Tue Sep 23 18:09:55 CST 2014
     */
    private Integer totalFee;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column wx_pay_notify_order.raw_data
     *
     * @mbggenerated Tue Sep 23 18:09:55 CST 2014
     */
    private String rawData;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column wx_pay_notify_order.create_time
     *
     * @mbggenerated Tue Sep 23 18:09:55 CST 2014
     */
    private Date createTime;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column wx_pay_notify_order.update_time
     *
     * @mbggenerated Tue Sep 23 18:09:55 CST 2014
     */
    private Date updateTime;

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column wx_pay_notify_order.id
     *
     * @return the value of wx_pay_notify_order.id
     *
     * @mbggenerated Tue Sep 23 18:09:55 CST 2014
     */
    public Integer getId() {
        return id;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column wx_pay_notify_order.id
     *
     * @param id the value for wx_pay_notify_order.id
     *
     * @mbggenerated Tue Sep 23 18:09:55 CST 2014
     */
    public void setId(Integer id) {
        this.id = id;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column wx_pay_notify_order.open_id
     *
     * @return the value of wx_pay_notify_order.open_id
     *
     * @mbggenerated Tue Sep 23 18:09:55 CST 2014
     */
    public String getOpenId() {
        return openId;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column wx_pay_notify_order.open_id
     *
     * @param openId the value for wx_pay_notify_order.open_id
     *
     * @mbggenerated Tue Sep 23 18:09:55 CST 2014
     */
    public void setOpenId(String openId) {
        this.openId = openId;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column wx_pay_notify_order.is_subsuribed
     *
     * @return the value of wx_pay_notify_order.is_subsuribed
     *
     * @mbggenerated Tue Sep 23 18:09:55 CST 2014
     */
    public Short getIsSubsuribed() {
        return isSubsuribed;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column wx_pay_notify_order.is_subsuribed
     *
     * @param isSubsuribed the value for wx_pay_notify_order.is_subsuribed
     *
     * @mbggenerated Tue Sep 23 18:09:55 CST 2014
     */
    public void setIsSubsuribed(Short isSubsuribed) {
        this.isSubsuribed = isSubsuribed;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column wx_pay_notify_order.bank_type
     *
     * @return the value of wx_pay_notify_order.bank_type
     *
     * @mbggenerated Tue Sep 23 18:09:55 CST 2014
     */
    public String getBankType() {
        return bankType;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column wx_pay_notify_order.bank_type
     *
     * @param bankType the value for wx_pay_notify_order.bank_type
     *
     * @mbggenerated Tue Sep 23 18:09:55 CST 2014
     */
    public void setBankType(String bankType) {
        this.bankType = bankType;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column wx_pay_notify_order.bank_billno
     *
     * @return the value of wx_pay_notify_order.bank_billno
     *
     * @mbggenerated Tue Sep 23 18:09:55 CST 2014
     */
    public String getBankBillno() {
        return bankBillno;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column wx_pay_notify_order.bank_billno
     *
     * @param bankBillno the value for wx_pay_notify_order.bank_billno
     *
     * @mbggenerated Tue Sep 23 18:09:55 CST 2014
     */
    public void setBankBillno(String bankBillno) {
        this.bankBillno = bankBillno;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column wx_pay_notify_order.fee_type
     *
     * @return the value of wx_pay_notify_order.fee_type
     *
     * @mbggenerated Tue Sep 23 18:09:55 CST 2014
     */
    public Integer getFeeType() {
        return feeType;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column wx_pay_notify_order.fee_type
     *
     * @param feeType the value for wx_pay_notify_order.fee_type
     *
     * @mbggenerated Tue Sep 23 18:09:55 CST 2014
     */
    public void setFeeType(Integer feeType) {
        this.feeType = feeType;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column wx_pay_notify_order.notify_id
     *
     * @return the value of wx_pay_notify_order.notify_id
     *
     * @mbggenerated Tue Sep 23 18:09:55 CST 2014
     */
    public String getNotifyId() {
        return notifyId;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column wx_pay_notify_order.notify_id
     *
     * @param notifyId the value for wx_pay_notify_order.notify_id
     *
     * @mbggenerated Tue Sep 23 18:09:55 CST 2014
     */
    public void setNotifyId(String notifyId) {
        this.notifyId = notifyId;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column wx_pay_notify_order.transaction_id
     *
     * @return the value of wx_pay_notify_order.transaction_id
     *
     * @mbggenerated Tue Sep 23 18:09:55 CST 2014
     */
    public String getTransactionId() {
        return transactionId;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column wx_pay_notify_order.transaction_id
     *
     * @param transactionId the value for wx_pay_notify_order.transaction_id
     *
     * @mbggenerated Tue Sep 23 18:09:55 CST 2014
     */
    public void setTransactionId(String transactionId) {
        this.transactionId = transactionId;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column wx_pay_notify_order.out_trade_no
     *
     * @return the value of wx_pay_notify_order.out_trade_no
     *
     * @mbggenerated Tue Sep 23 18:09:55 CST 2014
     */
    public String getOutTradeNo() {
        return outTradeNo;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column wx_pay_notify_order.out_trade_no
     *
     * @param outTradeNo the value for wx_pay_notify_order.out_trade_no
     *
     * @mbggenerated Tue Sep 23 18:09:55 CST 2014
     */
    public void setOutTradeNo(String outTradeNo) {
        this.outTradeNo = outTradeNo;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column wx_pay_notify_order.attach
     *
     * @return the value of wx_pay_notify_order.attach
     *
     * @mbggenerated Tue Sep 23 18:09:55 CST 2014
     */
    public String getAttach() {
        return attach;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column wx_pay_notify_order.attach
     *
     * @param attach the value for wx_pay_notify_order.attach
     *
     * @mbggenerated Tue Sep 23 18:09:55 CST 2014
     */
    public void setAttach(String attach) {
        this.attach = attach;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column wx_pay_notify_order.time_end
     *
     * @return the value of wx_pay_notify_order.time_end
     *
     * @mbggenerated Tue Sep 23 18:09:55 CST 2014
     */
    public Date getTimeEnd() {
        return timeEnd;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column wx_pay_notify_order.time_end
     *
     * @param timeEnd the value for wx_pay_notify_order.time_end
     *
     * @mbggenerated Tue Sep 23 18:09:55 CST 2014
     */
    public void setTimeEnd(Date timeEnd) {
        this.timeEnd = timeEnd;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column wx_pay_notify_order.transport_fee
     *
     * @return the value of wx_pay_notify_order.transport_fee
     *
     * @mbggenerated Tue Sep 23 18:09:55 CST 2014
     */
    public Integer getTransportFee() {
        return transportFee;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column wx_pay_notify_order.transport_fee
     *
     * @param transportFee the value for wx_pay_notify_order.transport_fee
     *
     * @mbggenerated Tue Sep 23 18:09:55 CST 2014
     */
    public void setTransportFee(Integer transportFee) {
        this.transportFee = transportFee;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column wx_pay_notify_order.product_fee
     *
     * @return the value of wx_pay_notify_order.product_fee
     *
     * @mbggenerated Tue Sep 23 18:09:55 CST 2014
     */
    public Integer getProductFee() {
        return productFee;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column wx_pay_notify_order.product_fee
     *
     * @param productFee the value for wx_pay_notify_order.product_fee
     *
     * @mbggenerated Tue Sep 23 18:09:55 CST 2014
     */
    public void setProductFee(Integer productFee) {
        this.productFee = productFee;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column wx_pay_notify_order.discount
     *
     * @return the value of wx_pay_notify_order.discount
     *
     * @mbggenerated Tue Sep 23 18:09:55 CST 2014
     */
    public Integer getDiscount() {
        return discount;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column wx_pay_notify_order.discount
     *
     * @param discount the value for wx_pay_notify_order.discount
     *
     * @mbggenerated Tue Sep 23 18:09:55 CST 2014
     */
    public void setDiscount(Integer discount) {
        this.discount = discount;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column wx_pay_notify_order.total_fee
     *
     * @return the value of wx_pay_notify_order.total_fee
     *
     * @mbggenerated Tue Sep 23 18:09:55 CST 2014
     */
    public Integer getTotalFee() {
        return totalFee;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column wx_pay_notify_order.total_fee
     *
     * @param totalFee the value for wx_pay_notify_order.total_fee
     *
     * @mbggenerated Tue Sep 23 18:09:55 CST 2014
     */
    public void setTotalFee(Integer totalFee) {
        this.totalFee = totalFee;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column wx_pay_notify_order.raw_data
     *
     * @return the value of wx_pay_notify_order.raw_data
     *
     * @mbggenerated Tue Sep 23 18:09:55 CST 2014
     */
    public String getRawData() {
        return rawData;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column wx_pay_notify_order.raw_data
     *
     * @param rawData the value for wx_pay_notify_order.raw_data
     *
     * @mbggenerated Tue Sep 23 18:09:55 CST 2014
     */
    public void setRawData(String rawData) {
        this.rawData = rawData;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column wx_pay_notify_order.create_time
     *
     * @return the value of wx_pay_notify_order.create_time
     *
     * @mbggenerated Tue Sep 23 18:09:55 CST 2014
     */
    public Date getCreateTime() {
        return createTime;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column wx_pay_notify_order.create_time
     *
     * @param createTime the value for wx_pay_notify_order.create_time
     *
     * @mbggenerated Tue Sep 23 18:09:55 CST 2014
     */
    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column wx_pay_notify_order.update_time
     *
     * @return the value of wx_pay_notify_order.update_time
     *
     * @mbggenerated Tue Sep 23 18:09:55 CST 2014
     */
    public Date getUpdateTime() {
        return updateTime;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column wx_pay_notify_order.update_time
     *
     * @param updateTime the value for wx_pay_notify_order.update_time
     *
     * @mbggenerated Tue Sep 23 18:09:55 CST 2014
     */
    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }
}