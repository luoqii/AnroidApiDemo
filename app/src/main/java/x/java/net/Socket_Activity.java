package x.java.net;

import android.os.Bundle;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;

import com.bysong.android.apidemo.R;

import org.bbs.android.log.Log;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.InetAddress;
import java.net.Socket;

public class Socket_Activity extends AppCompatActivity {

    private static final String TAG = Socket_Activity.class.getSimpleName();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_socket_interface_);
    }


    public void connect(View view) {
        new Thread(){
            @Override
            public void run() {
                super.run();
                doConnect(null);
            }
        }.start();
    }

    public void doConnect(View view) {
        Socket client = null;
        try {
            InetAddress a = InetAddress.getByName("www.android.com");
            client = new Socket(a,443);
            InputStream input = client.getInputStream();
            OutputStream outputStream = client.getOutputStream();
            outputStream.write("来自客户端发送的消息: Connect Success!!!".getBytes());

            // 流对象释放
            outputStream.close();

            readStream(input);

            // 套接字释放
            client.close();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    private void readStream(InputStream in) {
        byte[] data = new byte[8082];

        try {
            in.read(data);
        } catch (Exception e) {
            e.printStackTrace();
        }

        Log.d(TAG, "data:" + new String(data));
    }
}