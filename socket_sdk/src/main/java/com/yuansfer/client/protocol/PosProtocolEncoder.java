package com.yuansfer.client.protocol;

import org.apache.mina.core.buffer.IoBuffer;
import org.apache.mina.core.session.IoSession;
import org.apache.mina.filter.codec.ProtocolEncoder;
import org.apache.mina.filter.codec.ProtocolEncoderOutput;

import java.nio.charset.Charset;

/**
 * @Author Fly-Android
 * @CreateDate 2019/6/27 9:47
 * @Desciption Socket消息编码器
 */
public class PosProtocolEncoder implements ProtocolEncoder {

    private Charset mCharset;

    public PosProtocolEncoder() {
        this.mCharset = Charset.defaultCharset();
    }

    public PosProtocolEncoder(Charset charset) {
        this.mCharset = charset;
    }

    @Override
    public void encode(IoSession ioSession, Object o, ProtocolEncoderOutput out) throws Exception {
        PosMessage socketMessage = (PosMessage) o;
        IoBuffer ioBuffer = IoBuffer.allocate(socketMessage.getLength());
        ioBuffer.setAutoExpand(true);
        ioBuffer.putInt(socketMessage.getLength());
        ioBuffer.put(socketMessage.getFlag());
        if (socketMessage.getContent() != null) {
            ioBuffer.put(socketMessage.getContent().getBytes(mCharset));
        }
        ioBuffer.flip();
        out.write(ioBuffer);
    }

    @Override
    public void dispose(IoSession ioSession) throws Exception {
    }

}
