package com.yuansfer.client.business.request;

import com.yuansfer.client.business.BizIds;

/**
 * @Author Fly-Android
 * @CreateDate 2019/7/2 10:44
 * @Desciption 显示文本消息
 */
public class ShowMessageRequest extends BaseRequest {

    private String message;

    public ShowMessageRequest(String message) {
        this.message = message;
        this.needResponse = false;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    @Override
    public String getBizId() {
        return BizIds.ShowMessage.name();
    }

}
