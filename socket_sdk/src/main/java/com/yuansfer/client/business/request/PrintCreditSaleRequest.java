package com.yuansfer.client.business.request;

import com.yuansfer.client.business.BizIds;

/**
 * @author fly
 * @date 2019-08-20
 * @desc 信用卡打印请求
 */
public class PrintCreditSaleRequest extends BaseRequest {

    private String userLoginId;
    private String aid = "-";
    private String tsi = "-";
    private String tvr = "-";
    private String entryModel = "-";
    private String credType = "-";
    private String authCode = "-";
    private String credDebitType;
    private String supplierTransactionNo;
    private String supplierPayTime;
    private String currency;
    private double amount;
    private double tip;
    private double tax;
    private double taxRemovedAmount;
    private double convenientFee;
    private double convenientFeeRemovedAmount;

    public String getUserLoginId() {
        return userLoginId;
    }

    public void setUserLoginId(String userLoginId) {
        this.userLoginId = userLoginId;
    }

    public String getAid() {
        return aid;
    }

    public void setAid(String aid) {
        this.aid = aid;
    }

    public String getTsi() {
        return tsi;
    }

    public void setTsi(String tsi) {
        this.tsi = tsi;
    }

    public String getTvr() {
        return tvr;
    }

    public void setTvr(String tvr) {
        this.tvr = tvr;
    }

    public String getEntryModel() {
        return entryModel;
    }

    public void setEntryModel(String entryModel) {
        this.entryModel = entryModel;
    }

    public String getCredType() {
        return credType;
    }

    public void setCredType(String credType) {
        this.credType = credType;
    }

    public String getAuthCode() {
        return authCode;
    }

    public void setAuthCode(String authCode) {
        this.authCode = authCode;
    }

    public String getCredDebitType() {
        return credDebitType;
    }

    public void setCredDebitType(String credDebitType) {
        this.credDebitType = credDebitType;
    }

    public String getSupplierTransactionNo() {
        return supplierTransactionNo;
    }

    public void setSupplierTransactionNo(String supplierTransactionNo) {
        this.supplierTransactionNo = supplierTransactionNo;
    }

    public String getSupplierPayTime() {
        return supplierPayTime;
    }

    public void setSupplierPayTime(String supplierPayTime) {
        this.supplierPayTime = supplierPayTime;
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

    public double getConvenientFee() {
        return convenientFee;
    }

    public void setConvenientFee(double convenientFee) {
        this.convenientFee = convenientFee;
    }

    public double getConvenientFeeRemovedAmount() {
        return convenientFeeRemovedAmount;
    }

    public void setConvenientFeeRemovedAmount(double convenientFeeRemovedAmount) {
        this.convenientFeeRemovedAmount = convenientFeeRemovedAmount;
    }

    @Override
    public String getBizId() {
        return BizIds.PrintCreditSale.name();
    }

}
