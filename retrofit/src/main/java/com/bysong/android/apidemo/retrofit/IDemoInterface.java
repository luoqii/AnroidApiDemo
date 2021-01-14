package com.bysong.android.apidemo.retrofit;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Header;

public interface IDemoInterface {


    @GET("user")
    Call<Void> get_dynamic_header(@Header("Authorization") String authorization);
}
