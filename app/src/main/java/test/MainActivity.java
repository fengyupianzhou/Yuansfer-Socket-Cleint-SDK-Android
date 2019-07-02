package test;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.method.ScrollingMovementMethod;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import com.yuansfer.client.R;
import com.yuansfer.client.business.request.PushAmountRequest;
import com.yuansfer.client.business.response.BaseResponse;
import com.yuansfer.client.socket.PosClientManager;
import com.yuansfer.client.socket.listener.IConnectStateListener;
import com.yuansfer.client.socket.listener.IMsgReplyListener;
import com.yuansfer.client.socket.listener.ISessionListener;
import com.yuansfer.client.util.LogUtils;

import org.apache.mina.core.session.IoSession;

public class MainActivity extends AppCompatActivity {
    TextView tvRet;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        tvRet = findViewById(R.id.tv_ret);
        tvRet.setMovementMethod(ScrollingMovementMethod.getInstance());
        final EditText etIP = findViewById(R.id.edt_ip);
        final EditText etPort = findViewById(R.id.edt_port);
        findViewById(R.id.btn1).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                PosClientManager.getInstance().startDeviceConnect(MainActivity.this,
                        etIP.getText().toString(), Integer.parseInt(etPort.getText().toString()));
            }
        });
        findViewById(R.id.btn2).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                PosClientManager.getInstance().stopDeviceConnect(MainActivity.this);
            }
        });
        findViewById(R.id.btn3).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                sendAndReceiveMsg();
            }
        });
        findViewById(R.id.btn4).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                PosClientManager.getInstance().showMessage("Welcome Yuansfer POS device");
            }
        });
        PosClientManager.getInstance().setOnConnectStateListener(new IConnectStateListener() {
            @Override
            public void onDeviceConnected() {
                tvRet.setText("Socket服务已连接\n");
            }

            @Override
            public void onDeviceDisconnected() {
                tvRet.setText("Socket服务已关闭\n");
            }
        });
        PosClientManager.getInstance().setOnSessionListener(new ISessionListener() {
            @Override
            public void onSessionAdd(IoSession session) {
                tvRet.append(String.format("Socket客户端已连接服务器%s\n", session.getRemoteAddress()));
            }

            @Override
            public void onSessionRemove(IoSession session) {
                tvRet.append(String.format("Socket客户端已退出服务器%s\n", session.getRemoteAddress()));
            }

            @Override
            public void onMessageSent(IoSession session, Object msg) {
                tvRet.append(String.format("Socket客户端发送了消息：%s\n", msg.toString()));
            }

            @Override
            public void onMessageSendFail(Object msg, String reason) {
                tvRet.append(String.format("Socket客户发送消息失败：%s\n", reason));
            }

            @Override
            public void onMessageReceive(IoSession session, Object msg) {
                tvRet.append(String.format("Socket客户端收到服务端发来的消息：%s\n", msg.toString()));
            }
        });
    }

    private void sendAndReceiveMsg() {
        PosClientManager.getInstance().sendMessage(new PushAmountRequest(0.01), new IMsgReplyListener<BaseResponse>() {
            @Override
            public void onSuccess(BaseResponse response) {
                LogUtils.d("这里没有调用是因为PushAmountRequest不需要反馈");
            }

            @Override
            public void onFail(String error) {

            }
        });
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
    }

}
