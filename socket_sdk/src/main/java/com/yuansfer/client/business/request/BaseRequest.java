package com.yuansfer.client.business.request;

import java.util.UUID;

/**
 * @Author Fly-Android
 * @CreateDate 2019/6/27 16:32
 * @Desciption socket业务请求
 */
public abstract class BaseRequest {

    protected String requestId = UUID.randomUUID().toString();
    //请求是否需要响应
    protected boolean needResponse = true;

    public boolean isNeedResponse() {
        return needResponse;
    }

    public final String getRequestId() {
        return requestId;
    }

    /**
     * 业务标识，类似接口名称
     *
     * @return
     */
    public abstract String getBizId();

}
