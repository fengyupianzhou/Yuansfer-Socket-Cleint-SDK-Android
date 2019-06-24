package com.yuansfer.client;

import android.os.HandlerThread;

import org.apache.mina.core.future.ConnectFuture;
import org.apache.mina.core.service.IoHandlerAdapter;
import org.apache.mina.core.service.IoService;
import org.apache.mina.core.service.IoServiceListener;
import org.apache.mina.core.session.IdleStatus;
import org.apache.mina.core.session.IoSession;
import org.apache.mina.filter.codec.ProtocolCodecFilter;
import org.apache.mina.filter.codec.textline.TextLineCodecFactory;
import org.apache.mina.filter.logging.LoggingFilter;
import org.apache.mina.transport.socket.nio.NioSocketConnector;

import java.net.InetSocketAddress;
import java.nio.charset.Charset;

/**
 * @Author Fly-Android
 * @CreateDate 2019/6/24 18:22
 * @Desciption Socket Client连接器
 */
public class SocketClientConnector extends IoHandlerAdapter implements IoServiceListener {

    private NioSocketConnector mSocketConnector;
    private SocketConnectorThread mSocketThread;

    private class SocketConnectorThread extends HandlerThread {

        public SocketConnectorThread() {
            super(SocketConnectorThread.class.getSimpleName());
        }

        @Override
        protected void onLooperPrepared() {
            super.onLooperPrepared();
            ConnectFuture connectFuture = mSocketConnector.connect();
            connectFuture.awaitUninterruptibly();
            IoSession session = connectFuture.getSession();
        }

        public void disconnect() {
            mSocketConnector.dispose();
            quit();
        }

    }

    public SocketClientConnector(SocketConfig config) {
        initConnector(config);
    }

    private void initConnector(SocketConfig config) {
        mSocketConnector = new NioSocketConnector();
        mSocketConnector.setDefaultRemoteAddress(new InetSocketAddress(config.getRemoteAddress(), config.getRemotePort()));
        mSocketConnector.getSessionConfig().setBothIdleTime(config.getIdleTime());
        mSocketConnector.getSessionConfig().setReadBufferSize(config.getBufferSize());
        mSocketConnector.getFilterChain().addLast("logging", new LoggingFilter());
        mSocketConnector.getFilterChain().addLast("codec", new ProtocolCodecFilter(new TextLineCodecFactory(Charset.forName("UTF-8"))));
        mSocketConnector.setHandler(this);
        mSocketConnector.addListener(this);
    }

    public void startConnection() {
        if (mSocketThread != null) {
            mSocketThread.disconnect();
            mSocketThread = null;
        }
        mSocketThread = new SocketConnectorThread();
        mSocketThread.start();
    }

    public void stopConnection() {
        if (mSocketThread != null) {
            mSocketThread.disconnect();
        }
    }

    @Override
    public void sessionCreated(IoSession session) throws Exception {
        super.sessionCreated(session);
    }

    @Override
    public void sessionOpened(IoSession session) throws Exception {
        super.sessionOpened(session);
    }

    @Override
    public void sessionClosed(IoSession session) throws Exception {
        super.sessionClosed(session);
    }

    @Override
    public void sessionIdle(IoSession session, IdleStatus status) throws Exception {
        super.sessionIdle(session, status);
    }

    @Override
    public void exceptionCaught(IoSession session, Throwable cause) throws Exception {
        super.exceptionCaught(session, cause);
    }

    @Override
    public void messageReceived(IoSession session, Object message) throws Exception {
        super.messageReceived(session, message);
    }

    @Override
    public void messageSent(IoSession session, Object message) throws Exception {
        super.messageSent(session, message);
    }

    @Override
    public void serviceActivated(IoService ioService) throws Exception {

    }

    @Override
    public void serviceIdle(IoService ioService, IdleStatus idleStatus) throws Exception {

    }

    @Override
    public void serviceDeactivated(IoService ioService) throws Exception {

    }

    @Override
    public void sessionDestroyed(IoSession ioSession) throws Exception {

    }

}
