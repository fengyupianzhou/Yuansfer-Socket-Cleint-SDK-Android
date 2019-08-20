package com.yuansfer.client.business.request;

import com.yuansfer.client.business.BizIds;

/**
 * @author fly
 * @date 2019-08-19
 * @desc 退款请求
 */
public class OrderRefundRequest extends BaseRequest {

    private String transactionNo;
    private String refundAdmAccId;
    private String refundAdmPassword;
    private double refundAmount;

    public String getTransactionNo() {
        return transactionNo;
    }

    public void setTransactionNo(String transactionNo) {
        this.transactionNo = transactionNo;
    }

    public String getRefundAdmAccId() {
        return refundAdmAccId;
    }

    public void setRefundAdmAccId(String refundAdmAccId) {
        this.refundAdmAccId = refundAdmAccId;
    }

    public String getRefundAdmPassword() {
        return refundAdmPassword;
    }

    public void setRefundAdmPassword(String refundAdmPassword) {
        this.refundAdmPassword = refundAdmPassword;
    }

    public double getRefundAmount() {
        return refundAmount;
    }

    public void setRefundAmount(double refundAmount) {
        this.refundAmount = refundAmount;
    }

    @Override
    public String getBizId() {
        return BizIds.OrderRefund.name();
    }

}
