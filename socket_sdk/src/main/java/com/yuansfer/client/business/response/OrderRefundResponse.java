package com.yuansfer.client.business.response;


import com.yuansfer.client.business.bean.OrderRefundBean;

/**
 * @author fly
 * @date 2019-08-19
 * @desc 订单退款响应
 */
public class OrderRefundResponse extends BaseResponse {

    private OrderRefundBean orderRefund;

    public OrderRefundResponse(String requestId) {
        super(requestId);
    }

    public OrderRefundResponse(String requestId, int ret, String msg) {
        super(requestId, ret, msg);
    }

    public OrderRefundBean getOrderRefund() {
        return orderRefund;
    }

    public void setOrderRefund(OrderRefundBean orderRefund) {
        this.orderRefund = orderRefund;
    }

}
