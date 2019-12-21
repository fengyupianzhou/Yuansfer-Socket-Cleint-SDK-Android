package com.yuansfer.client.protocol;


/**
 * @Author Fly-Android
 * @CreateDate 2019/6/27 9:23
 * @Desciption 消息包
 */
public class PosMessage {

    //心跳消息flag
    public static final byte HEART_BEAT_FLAG = (byte) 0xFF;
    //普通消息flag
    public static final byte COMMON_MSG_FLAG = (byte) 0x00;
    //消息头长度（1个int+1个byte）
    public static final int SOCKET_HEAD_LENGTH = 5;
    //消息包总长度
    private int mLength;
    //标志位,作一些消息类型区分
    private byte mFlag;
    //消息体内容
    private String mContent;

    private PosMessage(byte flag, String content) {
        this.mFlag = flag;
        this.mContent = content;
        mLength = SOCKET_HEAD_LENGTH + (content == null ? 0 : content.getBytes().length);
    }

    public int getLength() {
        return mLength;
    }

    public byte getFlag() {
        return mFlag;
    }

    public void setFlag(byte mFlag) {
        this.mFlag = mFlag;
    }

    public String getContent() {
        return mContent;
    }

    public void setContent(String mContent) {
        this.mContent = mContent;
    }

    public static PosMessage obtain(String content) {
        return new PosMessage(COMMON_MSG_FLAG, content);
    }

    public static PosMessage obtain(byte flag, String content) {
        return new PosMessage(flag, content);
    }

    @Override
    public String toString() {
        return "PosMessage{" +
                "mLength=" + mLength +
                ", mFlag=" + mFlag +
                ", mContent='" + mContent + '\'' +
                '}';
    }

}
