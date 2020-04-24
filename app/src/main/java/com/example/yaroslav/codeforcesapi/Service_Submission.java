package com.example.yaroslav.codeforcesapi;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface Service_Submission {
    @GET("/api/user.status")
    Call<SubmissionList> getList(
            @Query("handle") String name
    );
}
