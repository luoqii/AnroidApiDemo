package x.java.net;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;

import com.bysong.android.apidemo.R;

import org.bbs.android.log.Log;

import java.net.InetAddress;
import java.net.NetworkInterface;
import java.net.SocketException;
import java.net.UnknownHostException;
import java.util.Enumeration;

public class NetworkInterface_Activity extends AppCompatActivity {

    private static final String TAG = NetworkInterface_Activity.class.getSimpleName();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_network_interface_);
    }

    public void getInterfaceAddresses(View view) {
        try {
            Enumeration<NetworkInterface> interfaces = NetworkInterface.getNetworkInterfaces();
            while (interfaces.hasMoreElements()) {
                Log.d(TAG, "interface:" + interfaces.nextElement());
            }
        } catch (SocketException e) {
            e.printStackTrace();
        }
    }

    public void address(View view) {
        new Thread(){
            @Override
            public void run() {
                super.run();
                doAddress(null);
            }
        }.start();
    }

    public void doAddress(View view) {
        InetAddress localhost = null;
        try {
            localhost = InetAddress.getByName("localhost");
            Log.d(TAG, "localhot:" + localhost);

            InetAddress a = InetAddress.getByName("www.cnblogs.com");
            Log.d(TAG, "address:" + a);
            // www.baidu.com/14.215.177.38
            // www.acfun.cn/117.21.225.193
            // www.bilibili.com/119.3.70.188
            // www.cnblogs.com/101.37.113.127

            InetAddress localHost = InetAddress.getLocalHost();
            Log.d(TAG, "localHost:" + localHost);

            String address = localHost.getHostAddress();
            String hostName = localHost.getHostName();

            Log.d(TAG, "address:" + address + " hostName:" + hostName);

            a = InetAddress.getByName("www.android.com");
            Log.d(TAG, "address:" + a);
        } catch (UnknownHostException e) {
            e.printStackTrace();
        }
    }
}