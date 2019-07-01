package com.yuansfer.client.socket;

import android.content.Context;
import android.util.ArrayMap;

import com.google.gson.Gson;
import com.yuansfer.client.business.request.BaseRequest;
import com.yuansfer.client.business.response.BaseResponse;
import com.yuansfer.client.socket.listener.IResponseListener;
import com.yuansfer.client.socket.listener.ISessionListener;
import com.yuansfer.client.socket.listener.ISocketListener;
import com.yuansfer.client.socket.protocol.SocketMessage;
import com.yuansfer.client.service.PosClientService;

import org.apache.mina.core.service.IoService;
import org.apache.mina.core.session.IoSession;

import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;

/**
 * @Author Fly-Android
 * @CreateDate 2019/7/1 12:04
 * @Desciption Socket 连接及会话管理器
 */
public class PosClientManager {

    private static PosClientManager sInstance;
    private IoSession mSession;
    private ISocketListener mSocketListener;
    private ISessionListener mSessionListener;
    private ArrayMap<String, IResponseListener> mRespListenerMap;
    private Gson mGson;

    private PosClientManager() {
        mGson = new Gson();
        mRespListenerMap = new ArrayMap<>();
    }

    public static PosClientManager getInstance() {
        if (sInstance == null) {
            synchronized (PosClientManager.class) {
                if (sInstance == null) {
                    sInstance = new PosClientManager();
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
     * @param request 请求消息
     * @return 是否发送成功
     */
    public <T extends BaseRequest> boolean sendMessage(T request) {
        if (isConnSuccess()) {
            return mSession.write(SocketMessage.obtain(mGson.toJson(request))).isWritten();
        } else {
            mSessionListener.onMessageSendFail(SocketMessage.obtain(mGson.toJson(request)), "not found server session");
            return false;
        }
    }

    /**
     * 发送消息
     *
     * @param request  请求消息
     * @param listener 响应回调
     * @return 是否发送成功
     */
    public <T extends BaseRequest, R extends BaseResponse> boolean sendMessage(T request, IResponseListener<R> listener) {
        if (isConnSuccess()) {
            if (listener != null && request.isNeedResponse()) {
                //需要server反馈时加入回调集合
                mRespListenerMap.put(request.getRequestId(), listener);
            }
            return mSession.write(SocketMessage.obtain(mGson.toJson(request))).isWritten();
        } else {
            mSessionListener.onMessageSendFail(SocketMessage.obtain(mGson.toJson(request)), "not found server session");
            if (listener != null) {
                listener.onFail("not found server session");
            }
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

    /**
     * 启动连接
     *
     * @param context    Context
     * @param remoteAddr 远程地址
     */
    public void startSocketConnect(Context context, String remoteAddr) {
        PosClientService.startService(context, remoteAddr);
    }

    /**
     * 启动连接
     *
     * @param context    Context
     * @param remoteAddr 远程地址
     * @param remotePort 远程端口
     */
    public void startSocketConnect(Context context, String remoteAddr, int remotePort) {
        PosClientService.startService(context, remoteAddr, remotePort);
    }

    /**
     * 启动连接
     *
     * @param context Context
     * @param config  配置项
     */
    public void startSocketConnect(Context context, ConnectConfig config) {
        PosClientService.startService(context, config);
    }

    /**
     * 停止连接
     *
     * @param context Context
     */
    public void stopSocketConnect(Context context) {
        PosClientService.stopService(context);
    }

    /**
     * 监听Socket连接状态
     *
     * @param socketListener
     */
    public void setOnSocketListener(ISocketListener socketListener) {
        this.mSocketListener = socketListener;
    }

    /**
     * 监听会话状态
     *
     * @param sessionListener
     */
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
        IResponseListener responseListener = null;
        if (mSessionListener != null) {
            mSessionListener.onMessageReceive(session, message);
        }
        try {
            if (message instanceof SocketMessage) {
                SocketMessage socketMsg = (SocketMessage) message;
                BaseResponse response = mGson.fromJson(socketMsg.getContent(), BaseResponse.class);
                responseListener = mRespListenerMap.remove(response.getRequestId());
                if (responseListener != null) {
                    if (response.getRet() == BaseResponse.SUCCESS) {
                        responseListener.onSuccess(mGson.fromJson(socketMsg.getContent(), genGenericInstance(responseListener.getClass())));
                    } else {
                        responseListener.onFail(response.getMsg());
                    }
                }
            }
        } catch (Exception e) {
            if (responseListener != null) {
                responseListener.onFail(e.getMessage());
            }
            e.printStackTrace();
        }
    }

    <V extends BaseResponse> Class<V> genGenericInstance(Class clazz) throws ClassNotFoundException {
        Class<V> response;
        final Type[] types = clazz.getGenericInterfaces();
        if (types == null || types.length == 0 || !(types[0] instanceof ParameterizedType)) {
            return null;
        }
        final ParameterizedType type = (ParameterizedType) types[0];
        final String actArgs = type.getActualTypeArguments()[0].toString();
        if (actArgs.startsWith("class ")) {
            response = (Class<V>) Class.forName(actArgs.substring("class ".length()));
        } else {
            response = (Class<V>) Class.forName(actArgs);
        }
        return response;
    }

}
