package com.yuansfer.client.business.request;


import com.yuansfer.client.business.BizIds;

public class GetServerTimeRequest extends BaseSocketRequest {
    @Override
    public String getBizId() {
        return BizIds.GetServerTime.name();
    }
}
