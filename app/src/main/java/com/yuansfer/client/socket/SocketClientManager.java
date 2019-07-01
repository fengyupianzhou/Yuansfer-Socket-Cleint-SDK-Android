package com.yuansfer.client.socket;

import android.content.Context;

import com.google.gson.Gson;
import com.yuansfer.client.business.request.BaseRequest;
import com.yuansfer.client.socket.listener.ISessionListener;
import com.yuansfer.client.socket.listener.ISocketListener;
import com.yuansfer.client.socket.protocol.SocketMessage;
import com.yuansfer.client.service.SocketClientService;

import org.apache.mina.core.service.IoService;
import org.apache.mina.core.session.IoSession;

public class SocketClientManager {

    private static SocketClientManager sInstance;
    private IoSession mSession;
    private ISocketListener mSocketListener;
    private ISessionListener mSessionListener;
    private static final Gson GSON = new Gson();

    private SocketClientManager() {
    }

    public static SocketClientManager getInstance() {
        if (sInstance == null) {
            synchronized (SocketClientManager.class) {
                if (sInstance == null) {
                    sInstance = new SocketClientManager();
                }
            }
        }
        return sInstance;
    }

    /**
     * 是否已与服务端连接
     *
     * @return
     */
    public boolean isConnSuccess() {
        return mSession != null && mSession.isConnected();
    }

    /**
     * 发送消息
     *
     * @param t
     * @return
     */
    public <T extends BaseRequest> boolean sendMessage(T t) {
        if (isConnSuccess()) {
            return mSession.write(SocketMessage.obtain(GSON.toJson(t))).isWritten();
        } else {
            mSessionListener.onMessageSendFail(SocketMessage.obtain(GSON.toJson(t)), "not found server session");
            return false;
        }
    }

    /**
     * 保存连接session
     *
     * @param session
     */
    public void saveSession(IoSession session) {
        mSession = session;
    }

    /**
     * 移除连接session
     */
    void deleteSession() {
        if (mSession != null) {
            mSession.closeOnFlush();
            mSession = null;
        }
    }

    public void startSocketConnect(Context context, String remoteAddr) {
        SocketClientService.startService(context, remoteAddr);
    }

    public void startSocketConnect(Context context, String remoteAddr, int remotePort) {
        SocketClientService.startService(context, remoteAddr, remotePort);
    }

    public void startSocketConnect(Context context, SocketConfig config) {
        SocketClientService.startService(context, config);
    }

    public void stopSocketConnect(Context context) {
        SocketClientService.stopService(context);
    }

    public void setOnSocketListener(ISocketListener socketListener) {
        this.mSocketListener = socketListener;
    }

    public void setOnSessionListener(ISessionListener sessionListener) {
        this.mSessionListener = sessionListener;
    }

    void notifySocketCreated(IoService service) {
        if (mSocketListener != null) {
            mSocketListener.onSocketStart(service);
        }
    }

    void notifySocketDestroyed(IoService service) {
        mSession = null;
        if (mSocketListener != null) {
            mSocketListener.onSocketStop(service);
        }
    }

    void notifySessionCreated(IoSession session) {
        if (mSession == null) {
            mSession = session;
        }
        if (mSessionListener != null) {
            mSessionListener.onSessionAdd(session);
        }
    }

    void notifySessionDestroyed(IoSession session) {
        mSession = null;
        if (mSessionListener != null) {
            mSessionListener.onSessionRemove(session);
        }
    }

    void notifySessionMessageSent(IoSession session, Object message) {
        if (mSessionListener != null) {
            mSessionListener.onMessageSent(session, message);
        }
    }

    void notifySessionMessageReceive(IoSession session, Object message) {
        if (mSessionListener != null) {
            mSessionListener.onMessageReceive(session, message);
        }
    }

}
