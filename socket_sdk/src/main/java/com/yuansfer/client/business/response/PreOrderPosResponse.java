package com.yuansfer.client.business.response;

/**
 * @author fly
 * @date 2019-08-16
 * @desc 预下单响应
 */
public class PreOrderPosResponse extends BaseResponse {

    private String transactionNo;

    public PreOrderPosResponse(String requestId) {
        super(requestId);
    }

    public String getTransactionNo() {
        return transactionNo;
    }

    public void setTransactionNo(String transactionNo) {
        this.transactionNo = transactionNo;
    }

}
