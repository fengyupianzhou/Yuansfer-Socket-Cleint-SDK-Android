package com.yuansfer.client;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * @Author Fly-Android
 * @CreateDate 2019/6/24 18:16
 * @Desciption Socket配置
 */
public class SocketConfig implements Parcelable {

    public static final int DEFAULT_PORT = 6189;   //默认端口
    public static final int DEFAULT_IDLE_TIME = 30;    //空间时间间隔
    public static final int DEFAULT_READ_BUFFER_SIZE = 2048; //缓冲区大小
    private String remoteAddress;
    private int remotePort;
    private int idleTime;
    private int bufferSize;

    private SocketConfig(SocketConfigBuilder builder) {
        this.remoteAddress = builder.remoteAddress;
        this.remotePort = builder.remotePort;
        this.idleTime = builder.idleTime;
        this.bufferSize = builder.bufferSize;
    }

    protected SocketConfig(Parcel in) {
        remoteAddress = in.readString();
        remotePort = in.readInt();
        idleTime = in.readInt();
        bufferSize = in.readInt();
    }

    public static class SocketConfigBuilder {
        private String remoteAddress;
        private int remotePort = DEFAULT_PORT;
        private int idleTime = DEFAULT_IDLE_TIME;
        private int bufferSize = DEFAULT_READ_BUFFER_SIZE;

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

        public SocketConfig build() {
            return new SocketConfig(this);
        }

    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(remoteAddress);
        dest.writeInt(remotePort);
        dest.writeInt(idleTime);
        dest.writeInt(bufferSize);
    }

    @Override
    public int describeContents() {
        return 0;
    }

    public static final Creator<SocketConfig> CREATOR = new Creator<SocketConfig>() {
        @Override
        public SocketConfig createFromParcel(Parcel in) {
            return new SocketConfig(in);
        }

        @Override
        public SocketConfig[] newArray(int size) {
            return new SocketConfig[size];
        }
    };

    public String getRemoteAddress() {
        return remoteAddress;
    }

    public void setRemoteAddress(String remoteAddress) {
        this.remoteAddress = remoteAddress;
    }

    public int getRemotePort() {
        return remotePort;
    }

    public void setRemotePort(int remotePort) {
        this.remotePort = remotePort;
    }

    public int getIdleTime() {
        return idleTime;
    }

    public void setIdleTime(int idleTime) {
        this.idleTime = idleTime;
    }

    public int getBufferSize() {
        return bufferSize;
    }

    public void setBufferSize(int bufferSize) {
        this.bufferSize = bufferSize;
    }
}
