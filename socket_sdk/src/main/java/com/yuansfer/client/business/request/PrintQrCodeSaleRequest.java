package com.yuansfer.client.business.request;

import com.yuansfer.client.business.BizIds;

/**
 * @author fly
 * @date 2019-08-20
 * @desc 二维码打印请求
 */
public class PrintQrCodeSaleRequest extends BaseRequest {

    private String cashierNo;
    private String supplierUserLoginId;
    private String supplierPayTime;
    private String exchangeRate;
    private String currency;
    private String paymentChannelValue;
    private String supplierTransId;
    private String transactionNo = "-";
    private double amount;
    private double tax;
    private double taxRemovedAmount;
    private double tip;
    private double convenientFee;
    private double convenientFeeRemovedAmount;
    private double refundAmount;
    private double netReceivable;

    public String getCashierNo() {
        return cashierNo;
    }

    public void setCashierNo(String cashierNo) {
        this.cashierNo = cashierNo;
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

    public String getExchangeRate() {
        return exchangeRate;
    }

    public void setExchangeRate(String exchangeRate) {
        this.exchangeRate = exchangeRate;
    }

    public String getCurrency() {
        return currency;
    }

    public void setCurrency(String currency) {
        this.currency = currency;
    }

    public double getAmount() {
        return amount;
    }

    public void setAmount(double amount) {
        this.amount = amount;
    }

    public double getTax() {
        return tax;
    }

    public void setTax(double tax) {
        this.tax = tax;
    }

    public double getTaxRemovedAmount() {
        return taxRemovedAmount;
    }

    public void setTaxRemovedAmount(double taxRemovedAmount) {
        this.taxRemovedAmount = taxRemovedAmount;
    }

    public double getTip() {
        return tip;
    }

    public void setTip(double tip) {
        this.tip = tip;
    }

    public double getConvenientFeeRemovedAmount() {
        return convenientFeeRemovedAmount;
    }

    public void setConvenientFeeRemovedAmount(double convenientFeeRemovedAmount) {
        this.convenientFeeRemovedAmount = convenientFeeRemovedAmount;
    }

    public double getRefundAmount() {
        return refundAmount;
    }

    public void setRefundAmount(double refundAmount) {
        this.refundAmount = refundAmount;
    }

    public String getPaymentChannelValue() {
        return paymentChannelValue;
    }

    public void setPaymentChannelValue(String paymentChannelValue) {
        this.paymentChannelValue = paymentChannelValue;
    }

    public String getSupplierTransId() {
        return supplierTransId;
    }

    public void setSupplierTransId(String supplierTransId) {
        this.supplierTransId = supplierTransId;
    }

    public String getTransactionNo() {
        return transactionNo;
    }

    public void setTransactionNo(String transactionNo) {
        this.transactionNo = transactionNo;
    }

    public double getConvenientFee() {
        return convenientFee;
    }

    public void setConvenientFee(double convenientFee) {
        this.convenientFee = convenientFee;
    }

    public double getNetReceivable() {
        return netReceivable;
    }

    public void setNetReceivable(double netReceivable) {
        this.netReceivable = netReceivable;
    }

    @Override
    public String getBizId() {
        return BizIds.PrintQrCodeSale.name();
    }

}
