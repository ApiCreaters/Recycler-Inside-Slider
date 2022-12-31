package com.example.retrofitandrecycler.ApiClient;

import com.example.retrofitandrecycler.Interface.MyApi;

import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class RetrofitClient {

    private static RetrofitClient instance = null;
    private MyApi myApi;
    private String baseUrl = "https://pixabay.com/api/";


    private RetrofitClient() {
        OkHttpClient okHttpClient = new OkHttpClient.Builder().build();
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(baseUrl)
                .addConverterFactory(GsonConverterFactory.create())
                .client(okHttpClient)
                .build();

        myApi = retrofit.create(MyApi.class);

    }


    public static synchronized RetrofitClient getInstance() {
        if (instance == null) {
            instance = new RetrofitClient();
        }

        return instance;
    }

    public MyApi getMyApi() {
        return myApi;
    }

}
