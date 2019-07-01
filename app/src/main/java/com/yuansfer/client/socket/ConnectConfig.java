package com.yuansfer.client.socket;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * @Author Fly-Android
 * @CreateDate 2019/6/24 18:16
 * @Desciption Socket配置
 */
public class ConnectConfig implements Parcelable {

    public static final int DEFAULT_PORT = 6189;   //默认端口
    public static final int DEFAULT_IDLE_TIME = 30;    //空间时间间隔
    public static final int DEFAULT_READ_BUFFER_SIZE = 2048; //缓冲区大小
    public static final int DEFAULT_CONN_TIMEOUT = 15; //连接超时时间
    public static final int MAX_RETRY_CONN_TIMES = 10; //最大重连次数
    private String remoteAddress;
    private int remotePort;
    private int idleTime;
    private int connTimeout;
    private int bufferSize;
    private int retryConnTimes;

    private ConnectConfig(SocketConfigBuilder builder) {
        this.remoteAddress = builder.remoteAddress;
        this.remotePort = builder.remotePort;
        this.idleTime = builder.idleTime;
        this.bufferSize = builder.bufferSize;
        this.connTimeout = builder.connTimeout;
        this.retryConnTimes = builder.retryConnTimes;
    }

    protected ConnectConfig(Parcel in) {
        remoteAddress = in.readString();
        remotePort = in.readInt();
        idleTime = in.readInt();
        bufferSize = in.readInt();
        connTimeout = in.readInt();
        retryConnTimes = in.readInt();
    }

    public static class SocketConfigBuilder {
        private String remoteAddress;
        private int remotePort = DEFAULT_PORT;
        private int idleTime = DEFAULT_IDLE_TIME;
        private int bufferSize = DEFAULT_READ_BUFFER_SIZE;
        private int connTimeout = DEFAULT_CONN_TIMEOUT;
        private int retryConnTimes = MAX_RETRY_CONN_TIMES;

        public SocketConfigBuilder setRemoteAddress(String remoteAddress) {
            this.remoteAddress = remoteAddress;
            return this;
        }

        public SocketConfigBuilder setRemotePort(int remotePort) {
            this.remotePort = remotePort;
            return this;
        }

        public SocketConfigBuilder setIdleTime(int idleTime) {
            this.idleTime = idleTime;
            return this;
        }

        public SocketConfigBuilder setBufferSize(int bufferSize) {
            this.bufferSize = bufferSize;
            return this;
        }

        public SocketConfigBuilder setConnTimeout(int connTimeout) {
            this.connTimeout = connTimeout;
            return this;
        }

        public SocketConfigBuilder setRetryConnTimes(int retryConnTimes) {
            this.retryConnTimes = retryConnTimes;
            return this;
        }

        public ConnectConfig build() {
            return new ConnectConfig(this);
        }

    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(remoteAddress);
        dest.writeInt(remotePort);
        dest.writeInt(idleTime);
        dest.writeInt(bufferSize);
        dest.writeInt(connTimeout);
        dest.writeInt(retryConnTimes);
    }

    @Override
    public int describeContents() {
        return 0;
    }

    public static final Creator<ConnectConfig> CREATOR = new Creator<ConnectConfig>() {
        @Override
        public ConnectConfig createFromParcel(Parcel in) {
            return new ConnectConfig(in);
        }

        @Override
        public ConnectConfig[] newArray(int size) {
            return new ConnectConfig[size];
        }
    };

    public String getRemoteAddress() {
        return remoteAddress;
    }

    public int getRemotePort() {
        return remotePort;
    }

    public int getIdleTime() {
        return idleTime;
    }

    public int getBufferSize() {
        return bufferSize;
    }

    public int getConnTimeout() {
        return connTimeout;
    }

    public int getRetryConnTimes() {
        return retryConnTimes;
    }

}
