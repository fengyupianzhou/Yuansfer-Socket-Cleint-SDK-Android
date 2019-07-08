package com.yuansfer.client.listener;

import com.yuansfer.client.business.response.BaseResponse;

/**
 * @Author Fly-Android
 * @CreateDate 2019/7/1 12:10
 * @Desciption 请求响应监听器
 */
public interface IMsgReplyListener<R extends BaseResponse> {

    /**
     * 响应成功
     *
     * @param response 消息实体
     */
    void onSuccess(R response);

    /**
     * 响应失败
     *
     * @param error 失败原由
     */
    void onFail(String error);
}
