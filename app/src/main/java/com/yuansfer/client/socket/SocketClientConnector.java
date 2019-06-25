package com.yuansfer.client.socket;

import android.os.Handler;
import android.os.HandlerThread;
import android.os.Looper;
import android.os.Message;
import android.util.Log;

import com.yuansfer.client.utils.LogUtils;

import org.apache.mina.core.service.IoHandlerAdapter;
import org.apache.mina.core.service.IoService;
import org.apache.mina.core.service.IoServiceListener;
import org.apache.mina.core.session.IdleStatus;
import org.apache.mina.core.session.IoSession;
import org.apache.mina.filter.codec.ProtocolCodecFilter;
import org.apache.mina.filter.codec.textline.TextLineCodecFactory;
import org.apache.mina.filter.logging.LoggingFilter;
import org.apache.mina.transport.socket.nio.NioSocketConnector;

import java.nio.charset.Charset;

/**
 * @Author Fly-Android
 * @CreateDate 2019/6/24 18:22
 * @Desciption Socket Client连接器
 */
public class SocketClientConnector extends IoHandlerAdapter implements IoServiceListener {

    private static final int SESSION_ADD_WHAT = 0;
    private static final int SESSION_REMOVE_WHAT = SESSION_ADD_WHAT + 1;
    private static final int SESSION_MSG_SENT_WHAT = SESSION_REMOVE_WHAT + 1;
    private static final int SESSION_MSG_RECEIVE_WHAT = SESSION_MSG_SENT_WHAT + 1;
    private static final int SOCKET_CONN_OPEN_WHAT = SESSION_MSG_RECEIVE_WHAT + 1;
    private static final int SOCKET_CONN_CLOSE_WHAT = SOCKET_CONN_OPEN_WHAT + 1;
    private NioSocketConnector mSocketConnector;
    private RetryConnectHandler mConnectHandler;
    private SocketConfig mSocketConfig;
    private static Handler sHandler = new Handler(Looper.getMainLooper()) {
        @Override
        public void handleMessage(Message msg) {
            switch (msg.what) {
                case SOCKET_CONN_OPEN_WHAT:
                    SocketClientManager.getInstance().notifySocketCreated((IoService) msg.obj);
                    break;
                case SOCKET_CONN_CLOSE_WHAT:
                    SocketClientManager.getInstance().notifySocketDestroyed((IoService) msg.obj);
                    break;
                case SESSION_ADD_WHAT:
                    SocketClientManager.getInstance().notifySessionCreated(((SessionMsgObj) msg.obj).session);
                    break;
                case SESSION_REMOVE_WHAT:
                    SocketClientManager.getInstance().notifySessionDestroyed(((SessionMsgObj) msg.obj).session);
                    break;
                case SESSION_MSG_SENT_WHAT:
                    SocketClientManager.getInstance().notifySessionMessageSent(((SessionMsgObj) msg.obj).session, ((SessionMsgObj) msg.obj).message);
                    break;
                case SESSION_MSG_RECEIVE_WHAT:
                    SocketClientManager.getInstance().notifySessionMessageReceive(((SessionMsgObj) msg.obj).session, ((SessionMsgObj) msg.obj).message);
                    break;
            }
        }
    };

    private static class SessionMsgObj {
        public IoSession session;
        public Object message;

        public static SessionMsgObj obtain(IoSession session) {
            SessionMsgObj obj = new SessionMsgObj();
            obj.session = session;
            return obj;
        }

        public static SessionMsgObj obtain(IoSession session, Object message) {
            SessionMsgObj obj = new SessionMsgObj();
            obj.session = session;
            obj.message = message;
            return obj;
        }
    }

    public SocketClientConnector(SocketConfig config) {
        initConnector(config);
    }

    /**
     * 初始化connector
     *
     * @param config
     */
    private void initConnector(SocketConfig config) {
        mSocketConfig = config;
        mSocketConnector = new NioSocketConnector();
        mSocketConnector.setConnectTimeoutMillis(1000 * config.getConnTimeout());
        mSocketConnector.getSessionConfig().setBothIdleTime(config.getIdleTime());
        mSocketConnector.getSessionConfig().setReadBufferSize(config.getBufferSize());
        mSocketConnector.getFilterChain().addLast("logging", new LoggingFilter());
        mSocketConnector.getFilterChain().addLast("codec", new ProtocolCodecFilter(new TextLineCodecFactory(Charset.forName("UTF-8"))));
        mSocketConnector.setHandler(this);
        mSocketConnector.addListener(this);
    }

    /**
     * 开始连接
     */
    public void startConnection() {
        if (mSocketConnector.isDisposed()) {
            initConnector(mSocketConfig);
        }
        sendQuitMessageIfNeed();
        HandlerThread handlerThread = new HandlerThread("SocketClientConnector");
        handlerThread.start();
        mConnectHandler = new RetryConnectHandler(mSocketConnector, mSocketConfig.getRemoteAddress()
                , mSocketConfig.getRemotePort(), mSocketConfig.getRetryConnTimes(), handlerThread.getLooper());
        mConnectHandler.sendEmptyMessage(RetryConnectHandler.INIT_WHAT);
        mConnectHandler.sendEmptyMessage(RetryConnectHandler.CONN_WHAT);
    }

    /**
     * 断开连接
     */
    public void dismissConnection() {
        sendQuitMessageIfNeed();
        SocketClientManager.getInstance().deleteSession();
        mSocketConnector.dispose();
    }

    /**
     * 发送线程退出消息如还在运行
     */
    private void sendQuitMessageIfNeed() {
        if (mConnectHandler != null
                && !mConnectHandler.isThreadClosed()) {
            mConnectHandler.sendEmptyMessage(RetryConnectHandler.DIS_WHAT);
            mConnectHandler = null;
        }
    }

    @Override
    public void serviceActivated(IoService ioService) throws Exception {
        LogUtils.d("serviceActivated");
        sHandler.obtainMessage(SOCKET_CONN_OPEN_WHAT, ioService).sendToTarget();
    }

    @Override
    public void serviceIdle(IoService ioService, IdleStatus idleStatus) throws Exception {
        LogUtils.d("serviceActivated");
    }

    @Override
    public void serviceDeactivated(IoService ioService) throws Exception {
        LogUtils.d("serviceDeactivated");
        sHandler.obtainMessage(SOCKET_CONN_CLOSE_WHAT, ioService).sendToTarget();
        if (!ioService.isDisposed()) {
            //非主动关闭连接断开后再次连接
            startConnection();
        }
    }

    @Override
    public void sessionCreated(IoSession session) throws Exception {
        super.sessionCreated(session);
        LogUtils.d("sessionCreated");
    }

    @Override
    public void sessionOpened(IoSession session) throws Exception {
        super.sessionOpened(session);
        LogUtils.d("sessionOpened");
        SocketClientManager.getInstance().saveSession(session);
        sHandler.obtainMessage(SESSION_ADD_WHAT, SessionMsgObj.obtain(session)).sendToTarget();
    }

    @Override
    public void sessionClosed(IoSession session) throws Exception {
        super.sessionClosed(session);
        LogUtils.d("sessionClosed");
        SocketClientManager.getInstance().deleteSession();
        sHandler.obtainMessage(SESSION_REMOVE_WHAT, SessionMsgObj.obtain(session)).sendToTarget();
    }

    @Override
    public void sessionDestroyed(IoSession session) throws Exception {
        LogUtils.d("sessionDestroyed");
    }

    @Override
    public void sessionIdle(IoSession session, IdleStatus status) throws Exception {
        super.sessionIdle(session, status);
        LogUtils.d("sessionIdle");
    }

    @Override
    public void exceptionCaught(IoSession session, Throwable cause) throws Exception {
        super.exceptionCaught(session, cause);
        LogUtils.d("sessionDestroyed" + Log.getStackTraceString(cause));
    }

    @Override
    public void messageReceived(IoSession session, Object message) throws Exception {
        super.messageReceived(session, message);
        LogUtils.d("messageReceived");
        sHandler.obtainMessage(SESSION_MSG_RECEIVE_WHAT, SessionMsgObj.obtain(session, message)).sendToTarget();
    }

    @Override
    public void messageSent(IoSession session, Object message) throws Exception {
        super.messageSent(session, message);
        LogUtils.d("messageSent");
        sHandler.obtainMessage(SESSION_MSG_SENT_WHAT, SessionMsgObj.obtain(session, message)).sendToTarget();
    }

}
