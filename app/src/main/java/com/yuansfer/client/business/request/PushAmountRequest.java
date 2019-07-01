package com.yuansfer.client.business.request;


import com.yuansfer.client.business.BizIds;

/**
 * @Author Fly-Android
 * @CreateDate 2019/7/1 10:21
 * @Desciption 向server发起金额推送
 */
public class PushAmountRequest extends BaseSocketRequest {

    private double amount;

    public PushAmountRequest(double amount) {
        this.amount = amount;
        this.needResponse = false;
    }

    public double getAmount() {
        return amount;
    }

    public void setAmount(double amount) {
        this.amount = amount;
    }

    @Override
    public String getBizId() {
        return BizIds.PushAmount.name();
    }

}
