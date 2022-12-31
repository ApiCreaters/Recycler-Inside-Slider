package com.example.retrofitandrecycler.Interface;

import com.example.retrofitandrecycler.Model.Respons;

import retrofit2.Call;
import retrofit2.Response;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface MyApi {

    @GET("?key=8003210-6df7aad0c179184ba4b447bc2")
    Call<Respons> getImages(@Query("page") int page,
                            @Query("per_page") int per_page);
}
