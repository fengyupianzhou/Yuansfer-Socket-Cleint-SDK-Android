package com.yuansfer.client.business.request;

import com.yuansfer.client.business.BizIds;

/**
 * @author fly
 * @date 2019-08-20
 * @desc 订单详情
 */
public class OrderDetailRequest extends BaseRequest {

    private String transactionNo;

    public OrderDetailRequest(String transactionNo) {
        this.transactionNo = transactionNo;
    }

    public String getTransactionNo() {
        return transactionNo;
    }

    public void setTransactionNo(String transactionNo) {
        this.transactionNo = transactionNo;
    }

    @Override
    public String getBizId() {
        return BizIds.OrderDetail.name();
    }
}
