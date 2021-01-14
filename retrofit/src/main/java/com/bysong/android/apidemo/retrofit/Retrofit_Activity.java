package com.bysong.android.apidemo.retrofit;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;

import java.lang.annotation.Retention;

import retrofit2.Retrofit;

public class Retrofit_Activity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_retrofit_);

    }

    public void get_dynamic_header(View view) {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("https://api.github.com/")
                .build();
        IDemoInterface interfacee = retrofit.create(IDemoInterface.class);

        interfacee.get_dynamic_header("auth");
    }
}