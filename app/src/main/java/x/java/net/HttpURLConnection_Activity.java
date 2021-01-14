package x.java.net;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;

import com.bysong.android.apidemo.R;

import org.bbs.android.log.Log;

import java.io.BufferedInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

public class HttpURLConnection_Activity extends AppCompatActivity {

    private static final String TAG = HttpURLConnection_Activity.class.getSimpleName();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_http_u_r_l_connection_);
    }

    public void get(View view) {
        new Thread(){
            @Override
            public void run() {
                super.run();
                URL url = null;
                try {
                    url = new URL("https://www.android.com/");
                    HttpURLConnection urlConnection = (HttpURLConnection) url.openConnection();
                    try {
                        InputStream in = new BufferedInputStream(urlConnection.getInputStream());
                        readStream(in);
                    } finally {
                        urlConnection.disconnect();
                    }
                } catch (MalformedURLException e) {
                    e.printStackTrace();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }.start();
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