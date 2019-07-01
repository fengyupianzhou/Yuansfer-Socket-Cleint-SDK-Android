package com.yuansfer.client.business.request;

/**
 * @Author Fly-Android
 * @CreateDate 2019/6/27 16:32
 * @Desciption socket业务请求
 */
public class BaseSocketRequest {

    //请求是否需要响应
    protected boolean needResponse = true;
    protected String bizId;

    public BaseSocketRequest() {
        bizId = getBizId();
    }

    public boolean isNeedResponse() {
        return needResponse;
    }

    //业务ID号,类似请求地址
    public String getBizId() {
        return null;
    }

}
