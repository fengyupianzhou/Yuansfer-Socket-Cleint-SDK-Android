package com.yuansfer.client.protocol;

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
public class PosProtocolCodecFactory implements ProtocolCodecFactory {

    private final PosProtocolEncoder mSocketEncoder;
    private final PosProtocolDecoder mSocketDecoder;

    public PosProtocolCodecFactory() {
        this(Charset.forName("UTF-8"));
    }

    public PosProtocolCodecFactory(Charset charset) {
        mSocketEncoder = new PosProtocolEncoder(charset);
        mSocketDecoder = new PosProtocolDecoder(charset);
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
