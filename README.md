## Yuansfer-Socket-Client-SDK-Android
Yuansfer-Socket-Client-SDK-Android是使用socket通信方式与Yuansfer的POS设备进行点对点通信的Android Libary

### 快速接入
* 添加依赖
```
dependencies {
    ...
    implementation 'com.yuansfer.sdk:socket-client:0.2.0'
}
```

### 使用方式
1. 在AndroidManifest.xml文件中声明权限和Service组件
```
<uses-permission android:name="android.permission.ACCESS_NETWORK_STATE" />
<uses-permission android:name="android.permission.INTERNET" />

<service
    android:name="com.yuansfer.client.service.PosClientService"
    android:exported="false">
            <intent-filter>
                <action android:name="com.android.service.action.PosClientService" />
            </intent-filter>
 </service>
```
2. 监听连接状态，可监听连接成功或断开连接
```
PosClientManager.getInstance().setOnConnectStateListener(new IConnectStateListener() {
            @Override
            public void onDeviceConnected() {

            }

            @Override
            public void onDeviceDisconnected() {

            }
        });

```
3. 监听会话状态或消息发送接收(未处理解析)，包含json内容，只监听消息到达传入AbstractMsgReceivedListener类实现
```
PosClientManager.getInstance().setOnSessionListener(new ISessionListener() {
    @Override
    public void onSessionAdd(PIOSession session) {

    }

    @Override
    public void onSessionRemove(PIOSession session) {

    }

    @Override
    public void onMessageSent(PIOSession session, Object msg) {

    }

    @Override
    public void onMessageSendFail(Object msg, String reason) {

    }

    @Override
    public void onMessageReceive(PIOSession session, Object msg) {

    }
});

```
4. 发起连接
```
PosClientManager.getInstance().startDeviceConnect（Context context, String ip,  int port）
```
5. 向POS N5设备发起显示文本消息，可测试是否连接成功
```
 PosClientManager.getInstance().showMessage("show message on yuansfer pos terminal");
```
6. 向POS N5 发起请求/响应消息，请求对象包含是否需要返回标志位，比如发起预下单到POS N5设备
```
PosClientManager.getInstance().sendMessage(new OrderPayRequest("商户流水号", amount)
                , new IMsgReplyListener<OrderPayResponse>() {
                    @Override
                    public void onSuccess(OrderPayResponse response) {
                        tvRet.append("支付成功，订单号：" + response.getTransactionNo() + "\n");
                    }

                    @Override
                    public void onFail(int errCode, String errMsg) {
                        tvRet.append("支付失败：" + errMsg + "\n");
                    }
                });

```
7. 断开连接
```
PosClientManager.getInstance().stopDeviceConnect(Context context)
```

### 其它说明
* API请求调用是通过PosClientManager.getInstance().sendMessage()发起
，传入不同的Request后和回调监听即可，具体详见API说明文档。

### 版本日志

#### 0.2.0
- 新增订单查询接口
- 新增交易退款接口
- 新增信用卡消费内容打印接口
- 新增微信/支付宝消费内容打印接口

#### 0.1.3
- 新增预下单支付接口，响应支付成功/失败

#### 0.1.0
- 项目初始化
- 初步实现socket间的长连接通信

