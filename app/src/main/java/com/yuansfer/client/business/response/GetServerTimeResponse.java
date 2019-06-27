package com.yuansfer.client.business.response;

public class GetServerTimeResponse extends BaseSocketReponse {

    private String datetime;

    public String getDatetime() {
        return datetime;
    }

    public void setDatetime(String datetime) {
        this.datetime = datetime;
    }
}
