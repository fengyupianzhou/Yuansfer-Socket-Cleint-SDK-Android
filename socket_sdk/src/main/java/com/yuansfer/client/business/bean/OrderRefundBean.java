package com.yuansfer.client.business.bean;

/**
 * @author fly
 * @date 2019-08-19
 * @desc 退款订单
 */
public class OrderRefundBean {

    /**
     * 退款金额
     */
    private double amount;
    /**
     * 创建时间
     */
    private long createTime;
    /**
     * 交易时间
     */
    private long paymentTime;
    /**
     * 商家账号
     */
    private String cashierNo;
    /**
     * 第三方交易订单号
     */
    private String supplierTransId;
    /**
     * 第三方交易账户号
     */
    private String supplierUserLoginId;
    /**
     * 结算时间
     */
    private String supplierPayTime;
    /**
     * 退款订单号
     */
    private String transactionNo;
    /**
     * 原始交易订单号
     */
    private String originalTransactionNo;
    /**
     * 支付渠道值
     */
    private String paymentChannelValue;

    public double getAmount() {
        return amount;
    }

    public void setAmount(double amount) {
        this.amount = amount;
    }

    public long getCreateTime() {
        return createTime;
    }

    public void setCreateTime(long createTime) {
        this.createTime = createTime;
    }

    public long getPaymentTime() {
        return paymentTime;
    }

    public void setPaymentTime(long paymentTime) {
        this.paymentTime = paymentTime;
    }

    public String getCashierNo() {
        return cashierNo;
    }

    public void setCashierNo(String cashierNo) {
        this.cashierNo = cashierNo;
    }

    public String getSupplierTransId() {
        return supplierTransId;
    }

    public void setSupplierTransId(String supplierTransId) {
        this.supplierTransId = supplierTransId;
    }

    public String getSupplierUserLoginId() {
        return supplierUserLoginId;
    }

    public void setSupplierUserLoginId(String supplierUserLoginId) {
        this.supplierUserLoginId = supplierUserLoginId;
    }

    public String getSupplierPayTime() {
        return supplierPayTime;
    }

    public void setSupplierPayTime(String supplierPayTime) {
        this.supplierPayTime = supplierPayTime;
    }

    public String getTransactionNo() {
        return transactionNo;
    }

    public void setTransactionNo(String transactionNo) {
        this.transactionNo = transactionNo;
    }

    public String getOriginalTransactionNo() {
        return originalTransactionNo;
    }

    public void setOriginalTransactionNo(String originalTransactionNo) {
        this.originalTransactionNo = originalTransactionNo;
    }

    public String getPaymentChannelValue() {
        return paymentChannelValue;
    }

    public void setPaymentChannelValue(String paymentChannelValue) {
        this.paymentChannelValue = paymentChannelValue;
    }

    @Override
    public String toString() {
        return "OrderRefundBean{" +
                "amount=" + amount +
                ", createTime=" + createTime +
                ", paymentTime=" + paymentTime +
                ", cashierNo='" + cashierNo + '\'' +
                ", supplierTransId='" + supplierTransId + '\'' +
                ", supplierUserLoginId='" + supplierUserLoginId + '\'' +
                ", supplierPayTime='" + supplierPayTime + '\'' +
                ", transactionNo='" + transactionNo + '\'' +
                ", originalTransactionNo='" + originalTransactionNo + '\'' +
                ", paymentChannelValue='" + paymentChannelValue + '\'' +
                '}';
    }
}
