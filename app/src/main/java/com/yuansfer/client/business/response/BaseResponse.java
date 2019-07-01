package com.yuansfer.client.business.response;

/**
 * @Author Fly-Android
 * @CreateDate 2019/6/27 16:29
 * @Desciption socket请求响应
 */
public class BaseResponse {

    //业务接口是否成功
    protected boolean isSuccess;
    //响应提示
    protected String msg;

    public boolean isSuccess() {
        return isSuccess;
    }

    public void setSuccess(boolean success) {
        isSuccess = success;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }
}
