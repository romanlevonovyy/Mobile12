package com.example.lab1;

import android.app.Application;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class ApplicationEx extends Application {

    private ApiService apiService;

    public void onCreate()  {
        super.onCreate();
        apiService = createApiService();
    }

    public ApiService getApiService(){
        return apiService;
    }

    public ApiService createApiService(){
         final Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("https://us-central1-androidlab-d5015.cloudfunctions.net/hospital/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        return retrofit.create(ApiService.class);
    }
}