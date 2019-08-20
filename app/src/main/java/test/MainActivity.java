package test;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.text.method.ScrollingMovementMethod;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.yuansfer.app.R;
import com.yuansfer.client.business.request.OrderPayRequest;
import com.yuansfer.client.business.request.OrderRefundRequest;
import com.yuansfer.client.business.response.OrderPayResponse;
import com.yuansfer.client.business.response.OrderRefundResponse;
import com.yuansfer.client.connect.PosClientManager;
import com.yuansfer.client.connect.PIOSession;
import com.yuansfer.client.listener.AbstractMsgReceivedListener;
import com.yuansfer.client.listener.IConnectStateListener;
import com.yuansfer.client.listener.IMsgReplyListener;
import com.yuansfer.client.listener.ISessionListener;


public class MainActivity extends AppCompatActivity {
    TextView tvRet;
    EditText etTransactionNo;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        tvRet = findViewById(R.id.tv_ret);
        tvRet.setMovementMethod(ScrollingMovementMethod.getInstance());
        etTransactionNo = findViewById(R.id.edt_transaction);
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
                preOrder(0.01);
            }
        });
        findViewById(R.id.btn4).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                PosClientManager.getInstance().showMessage("show message on yuansfer pos terminal");
            }
        });
        findViewById(R.id.btn5).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (TextUtils.isEmpty(etTransactionNo.getText().toString())) {
                    Toast.makeText(MainActivity.this, "请输入订单号", Toast.LENGTH_LONG).show();
                    return;
                }
                orderRefund();
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
            public void onSessionAdd(PIOSession session) {
                tvRet.append(String.format("Socket客户端已连接服务器%s\n", session.getRemoteAddress()));
            }

            @Override
            public void onSessionRemove(PIOSession session) {
                tvRet.append(String.format("Socket客户端已退出服务器%s\n", session.getRemoteAddress()));
            }

            @Override
            public void onMessageSent(PIOSession session, Object msg) {
                tvRet.append(String.format("Socket客户端发送了消息：%s\n", msg.toString()));
            }

            @Override
            public void onMessageSendFail(Object msg, String reason) {
                tvRet.append(String.format("Socket客户发送消息失败：%s\n", reason));
            }

            @Override
            public void onMessageReceive(PIOSession session, Object msg) {
                tvRet.append(String.format("Socket客户端收到服务端发来的消息：%s\n", msg.toString()));
            }
        });
        PosClientManager.getInstance().setOnSessionListener(new AbstractMsgReceivedListener() {
            @Override
            public void onMessageReceive(PIOSession session, Object msg) {

            }
        });
    }

    private void preOrder(double amount) {
        PosClientManager.getInstance().sendMessage(new OrderPayRequest("32", amount)
                , new IMsgReplyListener<OrderPayResponse>() {
                    @Override
                    public void onSuccess(OrderPayResponse response) {
                        tvRet.append("支付成功，订单号：" + response.getOrderPay().getTransactionNo() + "\n");
                    }

                    @Override
                    public void onFail(int errCode, String errMsg) {
                        tvRet.append("支付失败：" + errMsg + "\n");
                    }
                });
    }

    private void orderRefund() {
        OrderRefundRequest request = new OrderRefundRequest();
        request.setTransactionNo(etTransactionNo.getText().toString());
        request.setRefundAdmAccId("3000140017");
        request.setRefundAdmPassword("111111");
        request.setRefundAmount(0.01);
        PosClientManager.getInstance().sendMessage(request, new IMsgReplyListener<OrderRefundResponse>() {
            @Override
            public void onSuccess(OrderRefundResponse response) {
                tvRet.append("退款成功，订单号：" + response.getOrderRefund().getTransactionNo() + "\n");
            }

            @Override
            public void onFail(int errorCode, String errorMsg) {
                tvRet.append("退款失败：" + errorMsg + "\n");
            }
        });
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
    }

}
