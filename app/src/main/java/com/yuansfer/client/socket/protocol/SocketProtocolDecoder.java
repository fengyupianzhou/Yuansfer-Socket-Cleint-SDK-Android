package com.yuansfer.client.socket.protocol;

import org.apache.mina.core.buffer.IoBuffer;
import org.apache.mina.core.session.IoSession;
import org.apache.mina.filter.codec.CumulativeProtocolDecoder;
import org.apache.mina.filter.codec.ProtocolDecoderOutput;

import java.nio.charset.Charset;

/**
 * @Author Fly-Android
 * @CreateDate 2019/6/27 9:48
 * @Desciption socket消息解码器
 */
public class SocketProtocolDecoder extends CumulativeProtocolDecoder {

    private Charset mCharset;

    public SocketProtocolDecoder() {
        mCharset = Charset.defaultCharset();
    }

    public SocketProtocolDecoder(Charset charset) {
        this.mCharset = charset;
    }

    @Override
    protected boolean doDecode(IoSession ioSession, IoBuffer in, ProtocolDecoderOutput out) throws Exception {
        if (in.remaining() < SocketMessage.SOCKET_HEAD_LENGTH) {
            //可读长度小于包头长度不读取
            return false;
        }
        if (in.remaining() > 1) {
            in.mark();
            int length = in.getInt(in.position());
            if (in.remaining() < length - SocketMessage.SOCKET_HEAD_LENGTH) {
                //可读长度小于总长度-包头长度则停止拆包
                in.reset();
                return false;
            } else {
                //重置并读取一条完整记录
                in.reset();
                byte[] bytes = new byte[length];
                in.get(bytes, 0, length);
                byte flag = bytes[4];
                String content = new String(bytes, SocketMessage.SOCKET_HEAD_LENGTH
                        , length - SocketMessage.SOCKET_HEAD_LENGTH, mCharset);
                out.write(SocketMessage.obtain(flag, content));
                //若还有数据则粘包再次回调
                return in.remaining() > 0;
            }
        }
        return false;
    }

}
