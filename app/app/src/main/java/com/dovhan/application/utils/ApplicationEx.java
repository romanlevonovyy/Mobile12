package com.dovhan.application.utils;

import android.app.Application;

import com.dovhan.application.api.FoodMachineApi;
import com.google.firebase.auth.FirebaseAuth;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class ApplicationEx extends Application {

    private FirebaseAuth mAuth;
    private FoodMachineApi foodMachineApi;

    @Override
    public void onCreate() {
        super.onCreate();

        mAuth = FirebaseAuth.getInstance();
        foodMachineApi = createApiClient();
    }

    public FirebaseAuth getAuth() {
        return mAuth;
    }

    public FoodMachineApi getFoodMachineApi() {
        return foodMachineApi;
    }

    private FoodMachineApi createApiClient() {
        final Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("https://us-central1-fisrt-application.cloudfunctions.net/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        return retrofit.create(FoodMachineApi.class);
    }
}
