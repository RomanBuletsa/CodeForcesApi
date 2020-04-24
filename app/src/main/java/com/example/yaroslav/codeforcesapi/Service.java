package com.example.yaroslav.codeforcesapi;

import retrofit2.Call;
import retrofit2.http.GET;

public interface Service {
    @GET("/api/user.ratedList?activeOnly=true")
    Call<ItemResponse> getItems();
}
