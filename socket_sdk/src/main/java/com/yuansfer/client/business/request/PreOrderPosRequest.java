package com.yuansfer.client.business.request;


import com.yuansfer.client.business.BizIds;

/**
 * @author fly
 * @date 2019-08-16
 * @desc 向server发起金额推送
 */
public class PreOrderPosRequest extends BaseRequest {

    private String reference;
    private double amount;

    public PreOrderPosRequest(String reference, double amount) {
        this.reference = reference;
        this.amount = amount;
    }

    public String getReference() {
        return reference;
    }

    public void setReference(String reference) {
        this.reference = reference;
    }

    public double getAmount() {
        return amount;
    }

    public void setAmount(double amount) {
        this.amount = amount;
    }

    @Override
    public String getBizId() {
        return BizIds.PreOrderPos.name();
    }

    @Override
    public String toString() {
        return "PreOrderPosRequest{" +
                "reference='" + reference + '\'' +
                ", amount=" + amount +
                '}';
    }

}
