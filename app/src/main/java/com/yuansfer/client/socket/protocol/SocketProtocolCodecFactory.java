package com.yuansfer.client.socket.protocol;

import org.apache.mina.core.session.IoSession;
import org.apache.mina.filter.codec.ProtocolCodecFactory;
import org.apache.mina.filter.codec.ProtocolDecoder;
import org.apache.mina.filter.codec.ProtocolEncoder;

import java.nio.charset.Charset;

/**
 * @Author Fly-Android
 * @CreateDate 2019/6/27 10:12
 * @Desciption 自定义编解码工厂
 */
public class SocketProtocolCodecFactory implements ProtocolCodecFactory {

    private final SocketProtocolEncoder mSocketEncoder;
    private final SocketProtocolDecoder mSocketDecoder;

    public SocketProtocolCodecFactory() {
        this(Charset.forName("UTF-8"));
    }

    public SocketProtocolCodecFactory(Charset charset) {
        mSocketEncoder = new SocketProtocolEncoder(charset);
        mSocketDecoder = new SocketProtocolDecoder(charset);
    }

    @Override
    public ProtocolEncoder getEncoder(IoSession ioSession) throws Exception {
        return mSocketEncoder;
    }

    @Override
    public ProtocolDecoder getDecoder(IoSession ioSession) throws Exception {
        return mSocketDecoder;
    }

}
