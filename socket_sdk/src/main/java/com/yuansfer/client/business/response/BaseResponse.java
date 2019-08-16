package com.yuansfer.client.business.response;

import com.yuansfer.client.util.ResultCode;

/**
 * @Author Fly-Android
 * @CreateDate 2019/6/27 16:29
 * @Desciption socket请求响应
 */
public class BaseResponse {

    //状态码
    protected int retCode;
    //状态信息
    protected String retMsg = "success";
    //请求标识
    protected String requestId;

    public BaseResponse(String requestId) {
        this(requestId, ResultCode.REQUEST_SUCCESS, null);
    }

    public BaseResponse(String requestId, int ret, String msg) {
        this.retCode = ret;
        this.requestId = requestId;
        if (msg != null) {
            this.retMsg = msg;
        }
    }

    public String getRequestId() {
        return requestId;
    }

    public void setRequestId(String requestId) {
        this.requestId = requestId;
    }

    public int getRetCode() {
        return retCode;
    }

    public void setRetCode(int retCode) {
        this.retCode = retCode;
    }

    public String getRetMsg() {
        return retMsg;
    }

    public void setRetMsg(String retMsg) {
        this.retMsg = retMsg;
    }
}
