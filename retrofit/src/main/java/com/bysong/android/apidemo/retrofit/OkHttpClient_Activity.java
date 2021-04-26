package com.bysong.android.apidemo.retrofit;

import androidx.appcompat.app.AppCompatActivity;

import android.content.ContentResolver;
import android.os.Bundle;
import android.view.View;

import org.bbs.android.log.Log;

import java.io.IOException;

import okhttp3.MediaType;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

public class OkHttpClient_Activity extends AppCompatActivity {

    private static final String TAG = OkHttpClient_Activity.class.getSimpleName();
    OkHttpClient client;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ok_http_client_);


        client = new OkHttpClient();
    }

    public void get(View view) throws IOException {
        new Thread(){
            @Override
            public void run() {
                super.run();
                get("https://raw.github.com/square/okhttp/master/README.md");
            }
        }.start();
    }

    String get(String url) {
        String json = "{" +
                "\"serverid\": {" +
                " \"method\": \"getStrongPoi\"" +
                "    }," +
                "\"param\": {" +
                "        \"oem\": \"vivo\"," +
                "        \"mpk\": \"482370110870b873144bba9927528e4b\"," +
                "         \"keyword\": \"天安门\"," +
                "         \"longitude\": 116.31926283901," +
                "         \"latitude\": 39.912580492388," +
                "         \"cityId\": 131," +
                "         \"coordType\": \"gcj02ll\"" +
                "   }" +
                "}";
        RequestBody body = RequestBody.create(MediaType.parse("text/plain"),  json.toString());
        url = "https://newclient.map.baidu.com/client/infopass/infopass/api/";
        Request request = new Request.Builder()
                .url(url)
                //.addHeader("Content-Type", "application/json")
                .post(body)
                .build();

        try {
            try (Response response = client.newCall(request).execute()) {
                Log.d(TAG, "response:" + response);
                int code =  response.code();
                MediaType mimeType = response.body().contentType();
                byte[] result = response.body().bytes();
                Log.d(TAG, "code:" + code);
                Log.d(TAG, "mimetype:" + mimeType);
                Log.d(TAG, "result:" + new String(result));
                return response.body().string();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        return null;
    }
}