package com.example.yaroslav.codeforcesapi;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface Service_Rating {
    @GET("/api/user.rating")
    Call<RatingChangeList> getList(
            @Query("handle") String name
    );
}
