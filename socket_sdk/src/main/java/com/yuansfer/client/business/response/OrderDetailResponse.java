package com.yuansfer.client.business.response;


import com.yuansfer.client.business.bean.OrderDetailBean;

public class OrderDetailResponse extends BaseResponse {

    private OrderDetailBean orderDetail;

    public OrderDetailResponse(String requestId) {
        super(requestId);
    }

    public OrderDetailResponse(String requestId, int ret, String msg) {
        super(requestId, ret, msg);
    }

    public OrderDetailBean getOrderDetail() {
        return orderDetail;
    }

    public void setOrderDetail(OrderDetailBean orderDetail) {
        this.orderDetail = orderDetail;
    }
}
