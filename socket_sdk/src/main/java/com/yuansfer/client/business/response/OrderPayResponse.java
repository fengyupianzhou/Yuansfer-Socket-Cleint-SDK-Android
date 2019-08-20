package com.yuansfer.client.business.response;


import com.yuansfer.client.business.bean.OrderPayBean;

/**
 * @author fly
 * @date 2019-08-16
 * @desc 预下单支付成功响应
 */
public class OrderPayResponse extends BaseResponse {

    private OrderPayBean orderPay;

    public OrderPayResponse(String requestId) {
        super(requestId);
    }

    public OrderPayBean getOrderPay() {
        return orderPay;
    }

    public void setOrderPay(OrderPayBean orderPay) {
        this.orderPay = orderPay;
    }
}
