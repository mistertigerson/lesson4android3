package com.example.lesson4android3.remote;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class RetrofitClient {
    private static HerokuAPI api;

    public static HerokuAPI getApi(){
        if (api == null){
            api  = provideRetrofit();
        }
        return api;
    }

    private static HerokuAPI provideRetrofit (){
        return new Retrofit.Builder()
                .baseUrl("https://android-3-mocker.herokuapp.com/")
                .addConverterFactory(GsonConverterFactory.create())
                .build()
                .create(HerokuAPI.class);

    }
}
