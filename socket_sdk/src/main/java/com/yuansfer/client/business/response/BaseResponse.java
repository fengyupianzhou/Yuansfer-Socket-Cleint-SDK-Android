package com.yuansfer.client.business.response;

/**
 * @Author Fly-Android
 * @CreateDate 2019/6/27 16:29
 * @Desciption socket请求响应
 */
public class BaseResponse {

    public static final int SUCCESS = 0;
    public static final int ERROR = -1;
    //业务接口是否成功
    protected int ret = SUCCESS;
    //提示
    protected String msg = "success";
    //请求标识
    protected String requestId;

    public String getRequestId() {
        return requestId;
    }

    public void setRequestId(String requestId) {
        this.requestId = requestId;
    }

    public int getRet() {
        return ret;
    }

    public void setRet(int ret) {
        this.ret = ret;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }
}
