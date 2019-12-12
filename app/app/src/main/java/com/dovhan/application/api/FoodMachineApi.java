package com.dovhan.application.api;

import com.dovhan.application.entities.FoodMachine;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;

public interface FoodMachineApi {
    @GET("vendings")
    Call<List<FoodMachine>> getFoodMachines();
}
