package com.yuansfer.client.business.bean;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * @author fly
 * @date 2019-08-20
 * @desc 订单交易详情
 */
public class OrderDetailBean implements Parcelable {

    private String transactionNo;
    private double amount;
    private double refundAmount;
    private double netReceivable;
    private String exchangeRate;
    private String alipayUserLoginId;
    private String refundInfo;
    private String supplierPayTime;
    private int hasRefund;
    private String supplierTransactionNo;
    private String transactionReferNo;
    private String currency;
    private double tax;
    private double convenientFee;
    private double taxRemovedAmount;
    private double convenientFeeRemovedAmount;
    private double tip;
    private String cashierNo;
    private String paymentChannelValue;
    /**
     * amount : 0.01
     * convenientFee : 0
     * convenientFeeRemovedAmount : 0
     * netReceivable : 0.01
     * refundAmount : 0
     * tax : 0
     * taxRemovedAmount : 0
     * tip : 0
     * userLoginId : no userLoginId Info
     * vendor : wechatpay
     * voidAmount : 0
     */

    private String userLoginId;
    private String vendor;
    private double voidAmount;
    /**
     * amount : 0.01
     * convenientFee : 0
     * convenientFeeRemovedAmount : 0
     * exchangeRate : null
     * hasVoid : 0
     * netReceivable : 0.01
     * refundAmount : 0
     * tax : 0
     * taxRemovedAmount : 0
     * tip : 0
     * tsysExtraInfo : {"tvr":null,"tsi":null,"aid":null}
     * voidAmount : 0
     */

    private int hasVoid;
    private TsysExtraInfoBean tsysExtraInfo;
    private int authFlag = 0; // 预授权标志 0非预授权订单 1预授权订单未扣款 2预授权订单已扣款 3预授权订单取消扣款；不传值按照0处理
    private double authAmount; // 预授权可扣款金额，预授权订单只能扣款一次
    private String batched; //订单状态，针对credit有值，pending/completed

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

    public double getRefundAmount() {
        return refundAmount;
    }

    public void setRefundAmount(double refundAmount) {
        this.refundAmount = refundAmount;
    }

    public double getNetReceivable() {
        return netReceivable;
    }

    public void setNetReceivable(double netReceivable) {
        this.netReceivable = netReceivable;
    }

    public String getExchangeRate() {
        return exchangeRate;
    }

    public void setExchangeRate(String exchangeRate) {
        this.exchangeRate = exchangeRate;
    }

    public String getAlipayUserLoginId() {
        return alipayUserLoginId;
    }

    public void setAlipayUserLoginId(String alipayUserLoginId) {
        this.alipayUserLoginId = alipayUserLoginId;
    }

    public String getRefundInfo() {
        return refundInfo;
    }

    public void setRefundInfo(String refundInfo) {
        this.refundInfo = refundInfo;
    }

    public String getSupplierPayTime() {
        return supplierPayTime;
    }

    public void setSupplierPayTime(String supplierPayTime) {
        this.supplierPayTime = supplierPayTime;
    }

    public int getHasRefund() {
        return hasRefund;
    }

    public void setHasRefund(int hasRefund) {
        this.hasRefund = hasRefund;
    }

    public String getSupplierTransactionNo() {
        return supplierTransactionNo;
    }

    public void setSupplierTransactionNo(String supplierTransactionNo) {
        this.supplierTransactionNo = supplierTransactionNo;
    }

    public String getTransactionReferNo() {
        return transactionReferNo;
    }

    public void setTransactionReferNo(String transactionReferNo) {
        this.transactionReferNo = transactionReferNo;
    }

    public String getCurrency() {
        return currency;
    }

    public void setCurrency(String currency) {
        this.currency = currency;
    }

    public double getTax() {
        return tax;
    }

    public void setTax(double tax) {
        this.tax = tax;
    }

    public double getConvenientFee() {
        return convenientFee;
    }

    public void setConvenientFee(double convenientFee) {
        this.convenientFee = convenientFee;
    }

    public double getTaxRemovedAmount() {
        return taxRemovedAmount;
    }

    public void setTaxRemovedAmount(double taxRemovedAmount) {
        this.taxRemovedAmount = taxRemovedAmount;
    }

    public double getConvenientFeeRemovedAmount() {
        return convenientFeeRemovedAmount;
    }

    public void setConvenientFeeRemovedAmount(double convenientFeeRemovedAmount) {
        this.convenientFeeRemovedAmount = convenientFeeRemovedAmount;
    }

    public double getTip() {
        return tip;
    }

    public void setTip(double tip) {
        this.tip = tip;
    }

    public String getCashierNo() {
        return cashierNo;
    }

    public void setCashierNo(String cashierNo) {
        this.cashierNo = cashierNo;
    }

    public String getPaymentChannelValue() {
        return paymentChannelValue;
    }

    public void setPaymentChannelValue(String paymentChannelValue) {
        this.paymentChannelValue = paymentChannelValue;
    }

    public OrderDetailBean() {
    }

    public String getUserLoginId() {
        return userLoginId;
    }

    public void setUserLoginId(String userLoginId) {
        this.userLoginId = userLoginId;
    }

    public String getVendor() {
        return vendor;
    }

    public void setVendor(String vendor) {
        this.vendor = vendor;
    }

    public double getVoidAmount() {
        return voidAmount;
    }

    public void setVoidAmount(double voidAmount) {
        this.voidAmount = voidAmount;
    }

    public int getHasVoid() {
        return hasVoid;
    }

    public void setHasVoid(int hasVoid) {
        this.hasVoid = hasVoid;
    }

    public TsysExtraInfoBean getTsysExtraInfo() {
        return tsysExtraInfo;
    }

    public void setTsysExtraInfo(TsysExtraInfoBean tsysExtraInfo) {
        this.tsysExtraInfo = tsysExtraInfo;
    }

    public int getAuthFlag() {
        return authFlag;
    }

    public void setAuthFlag(int authFlag) {
        this.authFlag = authFlag;
    }

    public double getAuthAmount() {
        return authAmount;
    }

    public void setAuthAmount(double authAmount) {
        this.authAmount = authAmount;
    }

    public String getBatched() {
        return batched;
    }

    public void setBatched(String batched) {
        this.batched = batched;
    }

    public static class TsysExtraInfoBean implements Parcelable {
        /**
         * tvr : null
         * tsi : null
         * aid : null
         */

        private String tvr;
        private String tsi;
        private String aid;
        private String entryModel;
        private String credType;
        private String authCode;
        private String signPicUrl;
        private String credDebitType;

        public String getTvr() {
            return tvr;
        }

        public void setTvr(String tvr) {
            this.tvr = tvr;
        }

        public String getTsi() {
            return tsi;
        }

        public void setTsi(String tsi) {
            this.tsi = tsi;
        }

        public String getAid() {
            return aid;
        }

        public void setAid(String aid) {
            this.aid = aid;
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

        public String getSignPicUrl() {
            return signPicUrl;
        }

        protected TsysExtraInfoBean(Parcel in) {
            this.tvr = in.readString();
            this.tsi = in.readString();
            this.aid = in.readString();
            this.entryModel = in.readString();
            this.credType = in.readString();
            this.credDebitType = in.readString();
            this.authCode = in.readString();
            this.signPicUrl = in.readString();
        }

        public String getCredDebitType() {
            return credDebitType;
        }

        public void setSignPicUrl(String signPicUrl) {
            this.signPicUrl = signPicUrl;
        }


        @Override
        public int describeContents() {
            return 0;
        }

        public void setCredDebitType(String credDebitType) {
            this.credDebitType = credDebitType;
        }

        public TsysExtraInfoBean() {
        }

        @Override
        public void writeToParcel(Parcel dest, int flags) {
            dest.writeString(this.tvr);
            dest.writeString(this.tsi);
            dest.writeString(this.aid);
            dest.writeString(this.entryModel);
            dest.writeString(this.credType);
            dest.writeString(this.credDebitType);
            dest.writeString(this.authCode);
            dest.writeString(this.signPicUrl);
        }

        public static final Creator<TsysExtraInfoBean> CREATOR = new Creator<TsysExtraInfoBean>() {
            @Override
            public TsysExtraInfoBean createFromParcel(Parcel source) {
                return new TsysExtraInfoBean(source);
            }

            @Override
            public TsysExtraInfoBean[] newArray(int size) {
                return new TsysExtraInfoBean[size];
            }
        };
    }


    @Override
    public int describeContents() {
        return 0;
    }

    protected OrderDetailBean(Parcel in) {
        this.transactionNo = in.readString();
        this.amount = in.readDouble();
        this.refundAmount = in.readDouble();
        this.netReceivable = in.readDouble();
        this.exchangeRate = in.readString();
        this.alipayUserLoginId = in.readString();
        this.refundInfo = in.readString();
        this.supplierPayTime = in.readString();
        this.hasRefund = in.readInt();
        this.supplierTransactionNo = in.readString();
        this.transactionReferNo = in.readString();
        this.currency = in.readString();
        this.tax = in.readDouble();
        this.convenientFee = in.readDouble();
        this.taxRemovedAmount = in.readDouble();
        this.convenientFeeRemovedAmount = in.readDouble();
        this.tip = in.readDouble();
        this.cashierNo = in.readString();
        this.paymentChannelValue = in.readString();
        this.userLoginId = in.readString();
        this.vendor = in.readString();
        this.voidAmount = in.readDouble();
        this.hasVoid = in.readInt();
        this.tsysExtraInfo = in.readParcelable(TsysExtraInfoBean.class.getClassLoader());
        this.authFlag = in.readInt();
        this.authAmount = in.readDouble();
        this.batched = in.readString();
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(this.transactionNo);
        dest.writeDouble(this.amount);
        dest.writeDouble(this.refundAmount);
        dest.writeDouble(this.netReceivable);
        dest.writeString(this.exchangeRate);
        dest.writeString(this.alipayUserLoginId);
        dest.writeString(this.refundInfo);
        dest.writeString(this.supplierPayTime);
        dest.writeInt(this.hasRefund);
        dest.writeString(this.supplierTransactionNo);
        dest.writeString(this.transactionReferNo);
        dest.writeString(this.currency);
        dest.writeDouble(this.tax);
        dest.writeDouble(this.convenientFee);
        dest.writeDouble(this.taxRemovedAmount);
        dest.writeDouble(this.convenientFeeRemovedAmount);
        dest.writeDouble(this.tip);
        dest.writeString(this.cashierNo);
        dest.writeString(this.paymentChannelValue);
        dest.writeString(this.userLoginId);
        dest.writeString(this.vendor);
        dest.writeDouble(this.voidAmount);
        dest.writeInt(this.hasVoid);
        dest.writeParcelable(this.tsysExtraInfo, flags);
        dest.writeInt(this.authFlag);
        dest.writeDouble(this.authAmount);
        dest.writeString(this.batched);
    }

    public static final Creator<OrderDetailBean> CREATOR = new Creator<OrderDetailBean>() {
        @Override
        public OrderDetailBean createFromParcel(Parcel source) {
            return new OrderDetailBean(source);
        }

        @Override
        public OrderDetailBean[] newArray(int size) {
            return new OrderDetailBean[size];
        }
    };

    @Override
    public String toString() {
        return "OrderDetailBean{" +
                "transactionNo='" + transactionNo + '\'' +
                ", amount=" + amount +
                ", refundAmount=" + refundAmount +
                ", netReceivable=" + netReceivable +
                ", exchangeRate='" + exchangeRate + '\'' +
                ", alipayUserLoginId='" + alipayUserLoginId + '\'' +
                ", refundInfo='" + refundInfo + '\'' +
                ", supplierPayTime='" + supplierPayTime + '\'' +
                ", hasRefund=" + hasRefund +
                ", supplierTransactionNo='" + supplierTransactionNo + '\'' +
                ", transactionReferNo='" + transactionReferNo + '\'' +
                ", currency='" + currency + '\'' +
                ", tax=" + tax +
                ", convenientFee=" + convenientFee +
                ", taxRemovedAmount=" + taxRemovedAmount +
                ", convenientFeeRemovedAmount=" + convenientFeeRemovedAmount +
                ", tip=" + tip +
                ", cashierNo='" + cashierNo + '\'' +
                ", paymentChannelValue='" + paymentChannelValue + '\'' +
                ", userLoginId='" + userLoginId + '\'' +
                ", vendor='" + vendor + '\'' +
                ", voidAmount=" + voidAmount +
                ", hasVoid=" + hasVoid +
                ", tsysExtraInfo=" + tsysExtraInfo +
                ", authFlag=" + authFlag +
                ", authAmount=" + authAmount +
                ", batched='" + batched + '\'' +
                '}';
    }
}
