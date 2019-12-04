package com.example.lab1;

import java.util.List;
import retrofit2.Call;
import retrofit2.http.GET;

public interface ApiService {

    @GET("solar")
    Call<List<Hospital>> getHospitalData();
}