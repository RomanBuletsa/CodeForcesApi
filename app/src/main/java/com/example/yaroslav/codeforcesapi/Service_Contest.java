package com.example.yaroslav.codeforcesapi;

import retrofit2.Call;
import retrofit2.http.GET;

public interface Service_Contest {
    @GET("/api/contest.list")
    Call<ContestResponse> getContests();
}
