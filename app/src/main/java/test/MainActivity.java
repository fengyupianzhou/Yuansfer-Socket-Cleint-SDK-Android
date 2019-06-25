package test;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.method.MovementMethod;
import android.text.method.ScrollingMovementMethod;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import com.yuansfer.client.ISessionListener;
import com.yuansfer.client.ISocketListener;
import com.yuansfer.client.R;
import com.yuansfer.client.SocketClientConnector;
import com.yuansfer.client.SocketClientManager;
import com.yuansfer.client.SocketClientService;
import com.yuansfer.client.SocketConfig;

import org.apache.mina.core.service.IoService;
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
                SocketClientService.startService(MainActivity.this,
                        etIP.getText().toString(), Integer.parseInt(etPort.getText().toString()));
            }
        });
        findViewById(R.id.btn2).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SocketClientService.stopService(MainActivity.this);
            }
        });
        findViewById(R.id.btn3).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SocketClientManager.getInstance().sendMessage("I am socket client");
            }
        });
        SocketClientManager.getInstance().setOnSocketListener(new ISocketListener() {
            @Override
            public void onSocketStart(IoService service) {
                tvRet.append("Socket服务已连接\n");
            }

            @Override
            public void onSocketStop(IoService service) {
                tvRet.append("Socket服务已关闭\n");
            }
        });
        SocketClientManager.getInstance().setOnSessionListener(new ISessionListener() {
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

    @Override
    protected void onDestroy() {
        super.onDestroy();
    }

}
