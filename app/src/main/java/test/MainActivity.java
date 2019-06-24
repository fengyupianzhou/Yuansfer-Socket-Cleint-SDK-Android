package test;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.yuansfer.client.R;
import com.yuansfer.client.SocketClientConnector;
import com.yuansfer.client.SocketConfig;

public class MainActivity extends AppCompatActivity {
    SocketClientConnector clientConnector;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        SocketConfig config = new SocketConfig.SocketConfigBuilder().setRemoteAddress("192.168.1.129").setRemotePort(6189).build();
        clientConnector = new SocketClientConnector(config);
        clientConnector.startConnection();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        clientConnector.stopConnection();
    }

}
