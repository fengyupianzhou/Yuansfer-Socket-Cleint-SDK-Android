package com.yuansfer.client.business.response;

/**
 * @author fly
 * @date 2019-08-16
 * @desc 预下单支付成功响应
 */
public class OrderPayResponse extends BaseResponse {

    /**
     * 交易订单号
     */
    private String transactionNo;
    /**
     * 交易金额
     */
    private double amount;
    /**
     * 订单创建时间
     */
    private long createTime;
    /**
     * 支付时间
     */
    private long paymentTime;
    /**
     * 货币单位
     */
    private String currency;
    /**
     * 货率
     */
    private String exchangeRate;
    /**
     * 支付渠道
     */
    private String vendor;
    /**
     * 第三方支付时间
     */
    private String supplierPayTime;
    /**
     * 第三方支付单号
     */
    private String supplierTransId;
    /**
     * 第三方支付用户ID
     */
    private String supplierUserLoginId;

    public OrderPayResponse(String requestId) {
        super(requestId);
    }

    public String getTransactionNo() {
        return transactionNo;
    }

    public void setTransactionNo(String transactionNo) {
        this.transactionNo = transactionNo;
    }

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

    public String getCurrency() {
        return currency;
    }

    public void setCurrency(String currency) {
        this.currency = currency;
    }

    public String getExchangeRate() {
        return exchangeRate;
    }

    public void setExchangeRate(String exchangeRate) {
        this.exchangeRate = exchangeRate;
    }

    public String getVendor() {
        return vendor;
    }

    public void setVendor(String vendor) {
        this.vendor = vendor;
    }

    public String getSupplierPayTime() {
        return supplierPayTime;
    }

    public void setSupplierPayTime(String supplierPayTime) {
        this.supplierPayTime = supplierPayTime;
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

    @Override
    public String toString() {
        return "OrderPayResponse{" +
                "transactionNo='" + transactionNo + '\'' +
                ", amount=" + amount +
                ", createTime=" + createTime +
                ", paymentTime=" + paymentTime +
                ", currency='" + currency + '\'' +
                ", exchangeRate='" + exchangeRate + '\'' +
                ", vendor='" + vendor + '\'' +
                ", supplierPayTime='" + supplierPayTime + '\'' +
                ", supplierTransId='" + supplierTransId + '\'' +
                ", supplierUserLoginId='" + supplierUserLoginId + '\'' +
                '}';
    }
}
