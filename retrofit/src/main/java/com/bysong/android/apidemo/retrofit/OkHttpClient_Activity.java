package com.bysong.android.apidemo.retrofit;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;

import java.io.IOException;

import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

public class OkHttpClient_Activity extends AppCompatActivity {

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
        Request request = new Request.Builder()
                .url(url)
                .build();

        try {
            try (Response response = client.newCall(request).execute()) {
                return response.body().string();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        return null;
    }
}