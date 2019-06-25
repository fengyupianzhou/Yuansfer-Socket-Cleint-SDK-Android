package com.yuansfer.client;

import android.os.Handler;
import android.os.Looper;
import android.os.Message;

import org.apache.mina.core.future.ConnectFuture;
import org.apache.mina.core.session.IoSession;
import org.apache.mina.transport.socket.nio.NioSocketConnector;

import java.net.InetSocketAddress;

/**
 * @Author Fly-Android
 * @CreateDate 2019/6/25 15:19
 * @Desciption 连接处理器
 */
public class RetryConnectHandler extends Handler implements Runnable {

    public static final int INIT_WHAT = 0;
    public static final int CONN_WHAT = INIT_WHAT + 1;
    public static final int DIS_WHAT = CONN_WHAT + 1;
    private static final String TAG = RetryConnectHandler.class.getSimpleName();
    private NioSocketConnector mSocketConnector;
    private String mRemoteAddress;
    private int mRemotePort;
    private int mRetryConnTimes;
    private int mConnTime = 0;

    public RetryConnectHandler(NioSocketConnector connector
            , String address, int port, int retryConnTime, Looper looper) {
        super(looper);
        mSocketConnector = connector;
        mRemoteAddress = address;
        mRemotePort = port;
        mRetryConnTimes = retryConnTime;
    }

    @Override
    public void handleMessage(Message msg) {
        super.handleMessage(msg);
        if (msg.what == INIT_WHAT) {
            initAddressPort();
        } else if (msg.what == CONN_WHAT) {
            connectServer();
        } else if (msg.what == DIS_WHAT) {
            disConnectServer();
        }
    }

    /**
     * 初始化Server地址和端口
     */
    private synchronized void initAddressPort() {
        mSocketConnector.setDefaultRemoteAddress(new InetSocketAddress(mRemoteAddress, mRemotePort));
    }

    /**
     * 连接服务器，多次重连
     */
    private synchronized void connectServer() {
        LogUtils.d(TAG, String.format("第%d次开始连接服务器...", ++mConnTime));
        try {
            ConnectFuture connectFuture = mSocketConnector.connect();
            connectFuture.awaitUninterruptibly();
            IoSession session = connectFuture.getSession();
            if (session.isConnected()) {
                LogUtils.d(TAG, "连接成功");
                SocketClientManager.getInstance().saveSession(session);
                //安全退出Looper线程
                getLooper().quitSafely();
            } else {
                waitNextConnect("session is not connected");
            }
        } catch (Exception e) {
            waitNextConnect(e.toString());
        }
    }

    /**
     * 退出循环条件和线程
     */
    private synchronized void disConnectServer() {
        LogUtils.d(TAG, "关闭socket连接");
        mConnTime = mRetryConnTimes;
        //安全退出Thread
        getLooper().quitSafely();
    }

    /**
     * 等待下次重连
     */
    private synchronized void waitNextConnect(String reason) {
        LogUtils.d(TAG, reason + "->连接失败，5秒后重连...");
        postDelayed(this, 5_000);
    }

    @Override
    public void run() {
        if (mConnTime < mRetryConnTimes) {
            sendEmptyMessage(CONN_WHAT);
        } else {
            getLooper().quitSafely();
            LogUtils.d(TAG, "连接失败，退出重连");
        }
    }

}